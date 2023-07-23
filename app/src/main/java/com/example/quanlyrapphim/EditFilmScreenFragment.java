package com.example.quanlyrapphim;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.quanlyrapphim.models.Film;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class EditFilmScreenFragment extends Fragment {

    // ARG
    private String argFilmId;

    // VIEW
    private TextInputEditText inputName;
    private TextInputEditText inputType;
    private TextInputEditText inputCountry;
    private TextInputEditText inputCast;
    private TextInputEditText inputContent;
    private TextInputEditText inputDateRelease;
    private ImageView imvImage;
    private MaterialButton btnRemoveImage;
    private MaterialButton btnUpdate;
    private MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Ngày công chiếu").build();
    private LinearLayout loading;

    // OTHER
    ActivityResultLauncher<Intent> chooseImageActivityResultLauncher;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private Uri filePath = null;
    private boolean isImageModified;
    private Date pickedReleaseDate; // store date from picker


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            argFilmId = getArguments().getString("filmId");
        }

        chooseImageActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();

                            // Get uir and set image
                            Uri selectedImageUri = data.getData();
                            filePath = selectedImageUri;
                            if (null != selectedImageUri) {
                                // update the preview image in the layout
                                if (imvImage != null) {
                                    imvImage.setImageURI(selectedImageUri);
                                    btnRemoveImage.setVisibility(View.VISIBLE);
                                    isImageModified = true;
                                }
                            }
                        }
                    }
                });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_film_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("EDIT_FILM", argFilmId);

        // Get views
        inputName = view.findViewById(R.id.edit_film_screen_input_name);
        inputType = view.findViewById(R.id.edit_film_screen_input_type);
        inputCountry = view.findViewById(R.id.edit_film_screen_input_country);
        inputCast = view.findViewById(R.id.edit_film_screen_input_cast);
        inputContent = view.findViewById(R.id.edit_film_screen_input_content);
        inputDateRelease = view.findViewById(R.id.edit_film_screen_input_release_date);
        imvImage = view.findViewById(R.id.edit_film_screen_imv_image);
        btnUpdate = view.findViewById(R.id.edit_film_screen_btn_update);
        btnRemoveImage = view.findViewById(R.id.edit_film_screen_btn_remove_image);
        loading = view.findViewById(R.id.edit_film_screen_loading);

        // Show date picker
        inputDateRelease.setOnClickListener(v -> {
            if (!datePicker.isAdded()) {
                datePicker.show(getParentFragmentManager(), "DATE_PICKER");
            }
            datePicker.addOnPositiveButtonClickListener(selection -> {
                        inputDateRelease.setText(datePicker.getHeaderText());
                        pickedReleaseDate = new Date((Long) selection);
                    }
            );
        });

        // Show image picker activity
        imvImage.setOnClickListener(v -> {
            this.chooseImage();
        });

        // Remove image
        btnRemoveImage.setOnClickListener(v -> {
            this.removeImage();
        });

        // Load data to field
        DocumentReference docRef = db.collection("films").document(argFilmId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("FILM_DETAIL", "DocumentSnapshot data: " + document.getData());
                        Film film = document.toObject(Film.class);
                        inputName.setText(film.getName());
                        inputCast.setText(film.getCast());
                        inputContent.setText(film.getContent());
                        inputCountry.setText(film.getCountry());
                        inputType.setText(film.getType());

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        inputDateRelease.setText(dateFormat.format(film.getReleaseDate()));
                        pickedReleaseDate = film.getReleaseDate();

                        Picasso.get().load(film.getImage()).into(imvImage);
                        btnRemoveImage.setVisibility(View.VISIBLE);


                    } else {
                        Log.d("FILM_DETAIL", "No such document");
                    }
                } else {
                    Log.d("FILM_DETAIL", "get failed with ", task.getException());
                }
            }
        });


        // handle update film
        btnUpdate.setOnClickListener(v -> {

            if (
                    inputName.getText().toString() == "" ||
                    inputType.getText().toString() == "" ||
                    inputCountry.getText().toString() == "" ||
                    inputCast.getText().toString() == "" ||
                    inputContent.getText().toString() == "" ||
                    pickedReleaseDate == null
            ) {
                Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String,Object> updateMap = new HashMap<>();
            updateMap.put("name", inputName.getText().toString());
            updateMap.put("type", inputType.getText().toString());
            updateMap.put("country", inputCountry.getText().toString());
            updateMap.put("cast", inputCast.getText().toString());
            updateMap.put("content", inputContent.getText().toString());
            updateMap.put("releaseDate", pickedReleaseDate);
            Film updateFilm = new Film();

            // NOT MODIFIED IMAGE
            if (!isImageModified) {
                // UPDATE FILM IN FIRESTORE
                db.collection("films").document(argFilmId)
                        .update(updateMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity(), "Cập nhật phim thành công!", Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(view).navigate(R.id.filmScreenFragment);
                                loading.setVisibility(View.GONE);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                            }
                        });
                return;
            }


            // UPLOAD IMAGE AND CREATE FILM
            loading.setVisibility(View.VISIBLE);

            // Defining the child of storageReference
            StorageReference ref = storage.getReference().child("films/" + UUID.randomUUID().toString());

            // UPLOAD IMAGE
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Image uploaded successfully

                            // GET URL
                            taskSnapshot.getMetadata().getReference().getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String imageUrl = uri.toString();
                                            updateFilm.setImage(imageUrl);

                                            // CREATE FILM IN FIRESTORE
                                            db.collection("films")
                                                    .add(updateFilm)
                                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                        @Override
                                                        public void onSuccess(DocumentReference documentReference) {
                                                            Toast.makeText(getActivity(), "Thêm phim thành công!", Toast.LENGTH_SHORT).show();
                                                            Navigation.findNavController(view).navigate(R.id.filmScreenFragment);
                                                            loading.setVisibility(View.GONE);
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(getActivity(), "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                                                            loading.setVisibility(View.GONE);
                                                        }
                                                    });
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getActivity(), "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                                            loading.setVisibility(View.GONE);
                                        }
                                    });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error, Image not uploaded
                            Toast.makeText(getActivity(), "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                        }
                    });
        });


    }

    void chooseImage() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        chooseImageActivityResultLauncher.launch(i);
    }


    void removeImage() {
        filePath = null;
        if (imvImage != null) {
            imvImage.setImageURI(null);
        }
        if (btnRemoveImage != null) {
            btnRemoveImage.setVisibility(View.INVISIBLE);
        }
    }
}