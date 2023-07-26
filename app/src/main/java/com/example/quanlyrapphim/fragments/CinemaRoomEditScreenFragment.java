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

public class CinemaRoomEditScreenFragment extends Fragment {

    private String argCinemaRoomId;

    private TextInputEditText etName;
    private TextInputEditText etRowSeats;
    private TextInputEditText etColumnSeats;
    private MaterialButton saveBtn;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            argCinemaRoomId = getArguments().getString("cinemaRoomId");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cinemaroom_edit_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etName = view.findViewById(R.id.frg_cinemaroom_edit_et_id);
        etRowSeats = view.findViewById(R.id.frg_cinemaroom_edit_et_row);
        etColumnSeats = view.findViewById(R.id.item_spinner_cinemaroom_et_seats);
        saveBtn = view.findViewById(R.id.frg_cinemaroom_detail_btn_save);

        DocumentReference docRef = db.collection("theaters").document(argCinemaRoomId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("CINEMAROOM_DETAIL", "docSnapshot: " + document.getData());
                        CinemaRoom cinemaRoom = document.toObject(CinemaRoom.class);
                        etName.setText(cinemaRoom.getName());
                        etRowSeats.setText(Integer.toString((int)cinemaRoom.getSeatsRow()));
                        etColumnSeats.setText(Integer.toString((int)cinemaRoom.getSeatsColumn()));

                    } else {
                        Log.d("CINEMAROOM_DETAIL", "No docSnapshoot", task.getException());
                    }
                } else {
                    Log.d("CINEMAROOM_DETAIL", "get CINEMAROOM_DETAIL fail: ", task.getException());
                }
            }
        });

        saveBtn.setOnClickListener(v -> {
            if (
                    etName.getText().toString() == "" ||
                    etRowSeats.getText().toString() == "" ||
                    etColumnSeats.getText().toString() == ""
            ) {
                Toast.makeText(getActivity(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String,Object> updateMap = new HashMap<>();
            updateMap.put("seatsColumn", Integer.parseInt(etColumnSeats.getText().toString()));
            updateMap.put("seatsRow", Integer.parseInt(etRowSeats.getText().toString()));

            docRef.update(updateMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(getActivity(), " SUSSCESSFUL UPDATE CINEMAROOOM", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.cinemaRoomScreenFragment);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "FAILURE UPDATE CINEAROOM", Toast.LENGTH_SHORT).show();
                }
            });

        });
    }
}
