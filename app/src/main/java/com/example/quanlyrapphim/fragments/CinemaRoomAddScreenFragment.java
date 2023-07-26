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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class CinemaRoomAddScreenFragment extends Fragment {
    private TextInputEditText etName;
    private TextInputEditText etRowSeats;
    private TextInputEditText etColumnSeats;
    private MaterialButton addCinemaRoomBtn;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cinemaroom_add_screen, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etName = view.findViewById(R.id.frg_cinemaroom_add_et_name);
        etRowSeats = view.findViewById(R.id.frg_cinemaroom_add_et_row);
        etColumnSeats = view.findViewById(R.id.item_spinner_cinemaroom_et_seats);
        addCinemaRoomBtn = view.findViewById(R.id.frg_cinemaroom_add_btn_add);

        addCinemaRoomBtn.setOnClickListener(v -> {
            if (
                    etColumnSeats.getText().toString() == "" ||
                            etRowSeats.getText().toString() == "" ||
                            etName.getText().toString() == ""
            ) {
                Toast.makeText(getActivity(), "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            CreateCinemaRoom createCinemaRoom = new CreateCinemaRoom(
                    etName.getText().toString(),
                    Integer.parseInt(etRowSeats.getText().toString()),
                    Integer.parseInt(etColumnSeats.getText().toString())
            );

            //Thêm CinemaRoom vao FireStore
            CollectionReference cinemaRoomsRef = FirebaseFirestore.getInstance().collection("theaters");
            cinemaRoomsRef.add(createCinemaRoom)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getActivity(), "Thêm CinameRoom thành công!", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).navigate(R.id.cinemaRoomScreenFragment);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Thêm CinameRoom thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    });
        } );
    }
}

