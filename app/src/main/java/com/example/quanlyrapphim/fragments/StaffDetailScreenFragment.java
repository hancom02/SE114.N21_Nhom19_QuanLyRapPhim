package com.example.quanlyrapphim.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.models.Account;
import com.example.quanlyrapphim.models.Film;
import com.example.quanlyrapphim.utils.ConfirmDeleteDialog;
import com.example.quanlyrapphim.utils.GetCurrentAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;


public class StaffDetailScreenFragment extends Fragment {

    // ARG
    private String argStaffId;

    // VIEW
    private ImageView avatar;
    private TextView email;
    private TextView birthday;
    private TextView name;
    private TextView gender;
    private MaterialButton btnDelete;
    ConfirmDeleteDialog confirmDeleteDialog = new ConfirmDeleteDialog();

    // OTHER

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            argStaffId = getArguments().getString("staffId");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_staff_detail_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // get views
        avatar = view.findViewById(R.id.staff_detail_screen_avatar);
        name = view.findViewById(R.id.staff_detail_screen_name);
        email = view.findViewById(R.id.staff_detail_screen_email);
        gender = view.findViewById(R.id.staff_detail_screen_gender);
        birthday = view.findViewById(R.id.staff_detail_screen_birthday);
        btnDelete = view.findViewById(R.id.staff_detail_screen_btn_delete);

        // todo: test get account
        // GetCurrentAccount a = (GetCurrentAccount) getActivity();
        // Account acc = a.getAccount();

        // Get from db
        DocumentReference docRef = db.collection("accounts").document(argStaffId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        Account account = document.toObject(Account.class);
                        name.setText(account.getName());
                        email.setText(account.getEmail());
                        gender.setText(account.getGender());
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        birthday.setText(dateFormat.format(account.getBirthday()));
                        Picasso.get().load(account.getAvatar()).into(avatar);


                    } else {
                        Log.d("FILM_DETAIL", "No such document");
                    }
                } else {
                    Log.d("FILM_DETAIL", "get failed with ", task.getException());
                }
            }
        });

        btnDelete.setOnClickListener(v -> {
            confirmDeleteDialog.setDeleteListener(new ConfirmDeleteDialog.OnDeleteListener() {
                @Override
                public void onDeleteClick() {

                    // HANDLE DELETE FILM
                    db.collection("accounts").document(argStaffId)
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getActivity(), "Xoá nhân viên thành công", Toast.LENGTH_SHORT).show();
                                    Navigation.findNavController(view).navigate(R.id.staffScreenFragment);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(), "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });

            if (!confirmDeleteDialog.isAdded()) {
                confirmDeleteDialog.show(getParentFragmentManager(), "DeleteDialog");
            }
        });
    }
}