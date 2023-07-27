package com.example.quanlyrapphim.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.models.CreateCinemaRoom;
import com.example.quanlyrapphim.models.CreateTimeSlot;
import com.example.quanlyrapphim.models.TimeSlot;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class TimeSlotAddScreenFragment extends Fragment {
    private TextInputEditText etStart;
    private TextInputEditText etEnd;
    private MaterialButton btnAdd;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_slot_add, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etStart = view.findViewById(R.id.frg_timeslot_add_et_start);
        etEnd = view.findViewById(R.id.frg_timeslot_add_et_end);
        btnAdd = view.findViewById(R.id.frg_timeslot_add_btn_add);

        btnAdd.setOnClickListener(v -> {
            if (
                    etStart.getText().toString() == "" ||
                            etEnd.getText().toString() == ""
            ) {
                Toast.makeText(getActivity(), "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            CreateTimeSlot createTimeSlot = new CreateTimeSlot(
                    etStart.getText().toString(),
                    etEnd.getText().toString()
            );

            //Thêm TimeSlot vao FireStore
            CollectionReference timeSlotsRef = FirebaseFirestore.getInstance().collection("timeSlots");
            timeSlotsRef.add(createTimeSlot)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getActivity(), "Thêm TimeSlot thành công!", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).navigate(R.id.timeSlotScreenFragment);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Thêm TimeSlot thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    });
        } );

    }
}
