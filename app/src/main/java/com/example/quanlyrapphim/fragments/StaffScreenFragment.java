package com.example.quanlyrapphim.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.adapters.FilmRecyclerViewAdapter;
import com.example.quanlyrapphim.adapters.StaffRecyclerViewAdapter;
import com.example.quanlyrapphim.models.Account;
import com.example.quanlyrapphim.utils.ConfirmDeleteDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class StaffScreenFragment extends Fragment {

    private ArrayList<Account> staffs = new ArrayList<>();
    private RecyclerView staffRecyclerView;
    private FloatingActionButton btnAddEmployee;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ConfirmDeleteDialog confirmDeleteDialog = new ConfirmDeleteDialog();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_staff_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        staffRecyclerView = view.findViewById(R.id.staff_recycle_view);
        btnAddEmployee = view.findViewById(R.id.staff_screen_btn_add_staff);


        // Get staff from db
        db.collection("accounts")
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            staffs = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("GET_ACCOUNT", document.getId() + " => " + document.getData());
                                Account account = document.toObject(Account.class);
                                account.setId(document.getId());

                                Log.i("GET_ACCOUNT", account.getRole());

                                // check if account is staff
                                if (account.getRole().equals("staff")) {

                                    staffs.add(account);
                                }
                            }


                            // SET TO VIEW

                            StaffRecyclerViewAdapter adapter = new StaffRecyclerViewAdapter(getActivity(), staffs);

                            // set delete btn
                            adapter.setOnDeleteClickListener(i -> {

                                confirmDeleteDialog.setDeleteListener(new ConfirmDeleteDialog.OnDeleteListener() {
                                    @Override
                                    public void onDeleteClick() {
                                        String id = staffs.get(i).getId();

                                        // HANDLE DELETE STAFF
                                        db.collection("accounts").document(id)
                                                .delete()
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(getActivity(), "Xoá nhân viên thành công", Toast.LENGTH_SHORT).show();

                                                        // delete in ui
                                                        staffs.remove(i);
                                                        adapter.notifyDataSetChanged();
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

                            // set edit btn
                            adapter.setOnEditClickListener(i -> {
                                //Toast.makeText(getActivity(), "Edit item at " + i, Toast.LENGTH_SHORT).show();

                            });

                            // set click card
//                            adapter.setOnClickListener(i -> {
//                                Bundle bundle = new Bundle();
//                                bundle.putString("filmId", staffs.get(i).getId());
//                                Navigation.findNavController(view).navigate(R.id.action_filmScreenFragment_to_filmDetailScreenFragment, bundle);
//                            });

                            // set adapter
                            staffRecyclerView.setAdapter(adapter);
                            staffRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                        } else {
                            Log.d("GET_FILM", "Error getting documents: ", task.getException());
                        }
                    }
                });

        btnAddEmployee.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.addStaffScreenFragment);
        });
    }


}