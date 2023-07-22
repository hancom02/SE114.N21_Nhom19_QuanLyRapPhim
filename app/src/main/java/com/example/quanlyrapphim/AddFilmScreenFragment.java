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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.Date;

class CreateFilm {
    private String name;
    private String type;
    private String country;
    private String cast;
    private String content;
    private Date releaseDate;

    public CreateFilm(String name, String type, String country, String cast, String content, Date releaseDate) {
        this.name = name;
        this.type = type;
        this.country = country;
        this.cast = cast;
        this.content = content;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCountry() {
        return country;
    }

    public String getCast() {
        return cast;
    }

    public String getContent() {
        return content;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
}

public class AddFilmScreenFragment extends Fragment {

    private TextInputEditText inputName;
    private TextInputEditText inputType;
    private TextInputEditText inputCountry;
    private TextInputEditText inputCast;
    private TextInputEditText inputContent;
    private TextInputEditText inputDateRelease;
    private ImageView imvImage;
    private MaterialButton btnRemoveImage;
    private MaterialButton btnAdd;
    private Date pickedReleaseDate; // store date from picker
    private MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Ngày công chiếu").build();
    ActivityResultLauncher<Intent> chooseImageActivityResultLauncher;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                            if (null != selectedImageUri) {
                                // update the preview image in the layout
                                if (imvImage != null) {
                                    imvImage.setImageURI(selectedImageUri);
                                    btnRemoveImage.setVisibility(View.VISIBLE);
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
        return inflater.inflate(R.layout.fragment_add_film_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get views
        inputName = view.findViewById(R.id.add_film_screen_input_name);
        inputType = view.findViewById(R.id.add_film_screen_input_type);
        inputCountry = view.findViewById(R.id.add_film_screen_input_country);
        inputCast = view.findViewById(R.id.add_film_screen_input_cast);
        inputContent = view.findViewById(R.id.add_film_screen_input_content);
        inputDateRelease = view.findViewById(R.id.add_film_screen_input_release_date);
        imvImage = view.findViewById(R.id.add_film_screen_imv_image);
        btnAdd = view.findViewById(R.id.add_film_screen_btn_add);
        btnRemoveImage = view.findViewById(R.id.add_film_screen_btn_remove_image);


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

        // handle create film
        btnAdd.setOnClickListener(v -> {

            CreateFilm createFilm = new CreateFilm(
                    inputName.getText().toString(),
                    inputType.getText().toString(),
                    inputCountry.getText().toString(),
                    inputCast.getText().toString(),
                    inputContent.getText().toString(),
                    pickedReleaseDate
            );
            db.collection("films")
                    .add(createFilm)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getActivity(), "Thêm phim thành công!", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).navigate(R.id.filmScreenFragment);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
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
        if (imvImage != null) {
            imvImage.setImageURI(null);
        }
        if (btnRemoveImage != null) {
            btnRemoveImage.setVisibility(View.INVISIBLE);
        }
    }

}