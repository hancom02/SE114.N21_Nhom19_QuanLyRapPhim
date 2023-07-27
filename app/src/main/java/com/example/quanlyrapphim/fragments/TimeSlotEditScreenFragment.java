package com.example.quanlyrapphim.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.models.CinemaRoom;
import com.example.quanlyrapphim.models.TimeSlot;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TimeSlotEditScreenFragment extends Fragment {
    private String argTimeSlotId;

    TextInputEditText etStart;
    TextInputEditText etEnd;
    MaterialButton btnSave;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            argTimeSlotId = getArguments().getString("argTimeSlotId");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_time_slot_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etStart = view.findViewById(R.id.frg_timeslot_edit_et_start);
        etEnd = view.findViewById(R.id.frg_timeslot_edit_et_end);
        btnSave = view.findViewById(R.id.frg_timeslot_detail_btn_save);

        DocumentReference docRef = db.collection("timeSlots").document(argTimeSlotId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("TIME_SLOT_DETAIL", "docSnapshot: " + document.getData());
                        TimeSlot timeSlot = document.toObject(TimeSlot.class);
                        etStart.setText(timeSlot.getStart());
                        etEnd.setText(timeSlot.getEnd());

                    } else {
                        Log.d("TIME_SLOT_DETAIL", "No docSnapshoot", task.getException());
                    }
                } else {
                    Log.d("TIME_SLOT_DETAIL", "get TIME_SLOT_DETAIL fail: ", task.getException());
                }
            }
        });

        btnSave.setOnClickListener(v -> {
            if (
                    etStart.getText().toString() == "" || etEnd.getText().toString() == ""
            ) {
                Toast.makeText(getActivity(), "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

//            TimeSlot timeSlot = new TimeSlot(etStart.getText().toString(), etEnd.getText().toString());
            Map<String,Object> updateMap = new HashMap<>();
            updateMap.put("start", etStart.getText().toString());
            updateMap.put("end", etEnd.getText().toString());

            docRef.update(updateMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(getActivity(), " SUSSCESSFUL UPDATE TIMESLOT!", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.timeSlotScreenFragment);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "FAILURE UPDATE TIMESLOT", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
