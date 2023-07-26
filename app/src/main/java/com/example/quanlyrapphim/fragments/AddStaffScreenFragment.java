package com.example.quanlyrapphim.fragments;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.models.CreateAccount;
import com.example.quanlyrapphim.models.CreateFilm;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

public class AddStaffScreenFragment extends Fragment {

    private TextInputEditText inputEmail;
    private TextInputEditText inputPassword;
    private TextInputEditText inputName;
    private TextInputEditText inputBirthday;
    private MaterialButton btnRemoveAvatar;
    private MaterialButtonToggleGroup btnGenderGroup;
    private MaterialButton btnAdd;
    private ImageView imvAvatar;
    private String gender = null;
    private Date birthday;
    private MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Ngày sinh").build();
    private LinearLayout loading;

    ActivityResultLauncher<Intent> chooseImageActivityResultLauncher;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private Uri filePath = null;

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
                            filePath = selectedImageUri;
                            if (null != selectedImageUri) {
                                // update the preview image in the layout
                                if (imvAvatar != null) {
                                    imvAvatar.setImageURI(selectedImageUri);
                                    btnRemoveAvatar.setVisibility(View.VISIBLE);
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
        return inflater.inflate(R.layout.fragment_add_staff_screen, container, false);
        
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        
        // Get views
        inputName = view.findViewById(R.id.add_staff_screen_input_name);
        inputEmail = view.findViewById(R.id.add_staff_screen_input_email);
        inputPassword = view.findViewById(R.id.add_staff_screen_input_password);
        inputBirthday = view.findViewById(R.id.add_staff_screen_input_birthday);
        imvAvatar = view.findViewById(R.id.add_staff_screen_imv_avatar);
        btnAdd = view.findViewById(R.id.add_staff_screen_btn_add);
        btnRemoveAvatar = view.findViewById(R.id.add_staff_screen_btn_remove_avatar);
        btnGenderGroup = view.findViewById(R.id.add_staff_screen_input_gender);
        loading = view.findViewById(R.id.add_staff_screen_loading);

        // Show date picker
        inputBirthday.setOnClickListener(v -> {
            if (!datePicker.isAdded()) {
                datePicker.show(getParentFragmentManager(), "DATE_PICKER");
            }
            datePicker.addOnPositiveButtonClickListener(selection -> {
                        inputBirthday.setText(datePicker.getHeaderText());
                        birthday = new Date((Long) selection);
                    }
            );
        });

        // Show image picker activity
        imvAvatar.setOnClickListener(v -> {
            this.chooseImage();
        });

        // Remove image
        btnRemoveAvatar.setOnClickListener(v -> {
            this.removeImage();
        });

        // Pick gender
        btnGenderGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                Button btnSelected = view.findViewById(checkedId);
                gender = btnSelected.getText().toString();
                Toast.makeText(getActivity(), gender, Toast.LENGTH_SHORT).show();
            }
        });


        // handle create account
        btnAdd.setOnClickListener(v -> {

            if (
                    inputName.getText().toString() == "" ||
                            inputEmail.getText().toString() == "" ||
                            inputPassword.getText().toString() == "" ||
                            gender == null ||
                            birthday == null
            ) {
                Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidEmail(inputEmail.getText().toString())) {
                Toast.makeText(getActivity(), "Email không hợp lệ!", Toast.LENGTH_SHORT).show();
                return;
            }


            CreateAccount createAccount = new CreateAccount(
                    inputEmail.getText().toString(),
                    inputPassword.getText().toString(),
                    inputName.getText().toString(),
                    birthday,
                    gender,
                    "staff",
                    FieldValue.serverTimestamp()
            );

            loading.setVisibility(View.VISIBLE);

            // Defining the child of storageReference
            StorageReference ref = storage.getReference().child("avatars/" + UUID.randomUUID().toString());

            // HAS AVATAR
            if (filePath != null) {

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
                                                createAccount.setAvatar(imageUrl);

                                                // CREATE FILM IN FIRESTORE
                                                db.collection("accounts")
                                                        .add(createAccount)
                                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                            @Override
                                                            public void onSuccess(DocumentReference documentReference) {
                                                                Toast.makeText(getActivity(), "Thêm nhân viên thành công!", Toast.LENGTH_SHORT).show();
                                                                Navigation.findNavController(view).navigate(R.id.staffScreenFragment);
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

            }
            else {
                // CREATE FILM IN FIRESTORE
                db.collection("account")
                        .add(createAccount)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getActivity(), "Thêm nhân viên thành công!", Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(view).navigate(R.id.staffScreenFragment);
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
        if (imvAvatar != null) {
            imvAvatar.setImageURI(null);
        }
        if (btnRemoveAvatar != null) {
            btnRemoveAvatar.setVisibility(View.INVISIBLE);
        }
    }

    private boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}