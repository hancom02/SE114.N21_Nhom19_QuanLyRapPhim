package com.example.quanlyrapphim.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.adapters.RefreshmentsRecyclerViewAdapter;
import com.example.quanlyrapphim.adapters.TimeSlotRecyclerViewAdapter;
import com.example.quanlyrapphim.models.CinemaRoom;
import com.example.quanlyrapphim.models.Refreshment;
import com.example.quanlyrapphim.models.TimeSlot;
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

public class TimeSlotScreenFragment extends Fragment {

    private ArrayList<TimeSlot> timeSlotArrayList = new ArrayList<>();
    private RecyclerView timeSlotRecyclerView;
    private FloatingActionButton addTimeSlotBtn;
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
        return inflater.inflate(R.layout.fragment_time_slot_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        timeSlotRecyclerView = view.findViewById(R.id.frmt_timeslot_rv_listTimeSlot);
        addTimeSlotBtn = view.findViewById(R.id.frmt_timeslot_bt_addTimeSlot);

        db.collection("timeSlots").orderBy("start", Query.Direction.ASCENDING)
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        timeSlotArrayList = new ArrayList<TimeSlot>();
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                            Log.d("GET TIMESLOT", documentSnapshot.getId() + documentSnapshot.getData());

                            TimeSlot timeSlot = documentSnapshot.toObject(TimeSlot.class);
                            timeSlot.setId(documentSnapshot.getId());
                            timeSlotArrayList.add(timeSlot);
                        }

                        TimeSlotRecyclerViewAdapter adapter = new TimeSlotRecyclerViewAdapter(getActivity(), timeSlotArrayList);

                        adapter.setOnDeleteClickListener(i -> {
//                                Toast.makeText(getActivity(), "Deleted item at " + i, Toast.LENGTH_SHORT).show();
                            confirmDeleteDialog.setDeleteListener(new ConfirmDeleteDialog.OnDeleteListener() {
                                @Override
                                public void onDeleteClick() {
                                    String id = timeSlotArrayList.get(i).getId();

                                    // HANDLE DELETE FILM
                                    db.collection("timeSlots").document(id)
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(getActivity(), "Xoá khung giờ thành công", Toast.LENGTH_SHORT).show();

                                                // delete in ui
                                                timeSlotArrayList.remove(i);
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
                        adapter.setOnEditClickListener(i -> {
//                                Toast.makeText(getActivity(), "Edit item at " + i, Toast.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();
                            bundle.putString("argTimeSlotId", timeSlotArrayList.get(i).getId());
                            Navigation.findNavController(view).navigate(R.id.action_timeSlotScreenFragment_to_editTimeSlotScreenFragment, bundle);
                        });
                        timeSlotRecyclerView.setAdapter(adapter);
                        timeSlotRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    } else {
                        Log.d("GET_CINEMAROOM","ERROR IN GET CINEMAROOM", task.getException());
                    }
                }
            });

        addTimeSlotBtn.setOnClickListener(v -> {
//            Toast.makeText(getActivity(), "Nav to add refreshment screen", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigate(R.id.action_timeSlotScreenFragment_to_addTimeSlotScreenFragment);
        });
    }

}