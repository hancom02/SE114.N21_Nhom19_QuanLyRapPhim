package com.example.quanlyrapphim.fragments;

import android.accounts.AccountManagerFuture;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.models.CinemaRoom;
import com.example.quanlyrapphim.models.Equipment;
import com.example.quanlyrapphim.utils.ConfirmDeleteDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.common.reflect.TypeToken;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Map;

public class CinemaRoomDetailScreenFragment extends Fragment {
    // Root CinemaRoom
    private String argCinemaRoomId;
    private TextView tvName;
    private TextView tvRowSeats;
    private TextView tvColumnSeats;
//    private TextView tvProjector;
//    private TextView tvScreen;
//    private TextView tvLamp;
//    private TextView tvSpeaker;
    private ArrayList<Equipment> equipmentArrayList = new ArrayList<>();
    private MaterialButton editCinemaRoomBtn;
    private MaterialButton deleteCinemaRoomBtn;
    ConfirmDeleteDialog confirmDeleteDialog = new ConfirmDeleteDialog();

    //GET FIREBASE
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

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
        View view = inflater.inflate(R.layout.fragment_cinemaroom_detail_screen, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName = view.findViewById(R.id.frg_cinemaroom_detail_tv_cinemaroom_id);
        tvRowSeats = view.findViewById(R.id.frg_cinemaroom_detail_tv_cinemaroom_row);
        tvColumnSeats = view.findViewById(R.id.frg_cinemaroom_detail_tv_cinemaroom_column);
//        tvProjector = view.findViewById(R.id.frg_cinemaroom_detail_tv_cinemaroom_projector);
//        tvScreen = view.findViewById(R.id.frg_cinemaroom_detail_tv_cinemaroom_screen);
//        tvSpeaker = view.findViewById(R.id.frg_cinemaroom_detail_tv_cinemaroom_speaker);
//        tvLamp = view.findViewById(R.id.frg_cinemaroom_detail_tv_cinemaroom_lamp);

        editCinemaRoomBtn = view.findViewById(R.id.frg_cinemaroom_detail_btn_edit);
        deleteCinemaRoomBtn = view.findViewById(R.id.frg_cinemaroom_detail_btn_delete);

        //Get Detail CinemaRoom from Firebase
        DocumentReference docFef = db.collection("theaters").document(argCinemaRoomId);

//        DocumentReference docRefEquipment = db.collection("equipments").get();


//
//        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
//
//            Log.d("GET CINEMAROOM", documentSnapshot.getId() + documentSnapshot.getData());
//
//            CinemaRoom cinemaRoom = documentSnapshot.toObject(CinemaRoom.class);
//            cinemaRoom.setId(documentSnapshot.getId());
//            cinemaRoomArrayList.add(cinemaRoom);
//        }
        docFef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot docSnapshot = task.getResult();
                    if (docSnapshot.exists()) {
                        CinemaRoom cinemaRoom = docSnapshot.toObject(CinemaRoom.class);

                        Log.d("CINEMAROOM_DETAIL", "DocSnapshot: " + docSnapshot.getData());

//                        Map<String, Object> equipmentMap = docSnapshot.get("equipments", Map.class);
//                        Map<String, Object> equipmentMap = docSnapshot.get("equipments", new TypeToken<Map<String, Object>>() {}.getType());
                        ArrayList<String> equipmentIdList = cinemaRoom.getEquipmentIds();
//
                        if (equipmentIdList != null) {
                            // Lấy danh sách các ID của equipment
//                            ArrayList<String> equipmentIds = new ArrayList<>(equipmentIdList.keySet());
                            ArrayList<String> equipmentNames = new ArrayList<>();

                            for (String equipmentId : equipmentIdList) {
                                DocumentReference equipmentRef = db.collection("equipments").document(equipmentId);
                                equipmentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()) {
                                            // Lấy dữ liệu của equipment tại đây
                                            String equipmentName = documentSnapshot.getString("name");
                                            equipmentNames.add(equipmentName);
                                            // Xử lý dữ liệu của equipment tại đây
                                        }
                                    }
                                });
                            }

                            // Duyệt qua từng ID và lấy dữ liệu của equipment tương ứng
//                            for (String equipmentId : equipmentIds) {
//                                DocumentReference equipmentRef = db.collection("equipments").document(equipmentId);
//                                equipmentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                                    @Override
//                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                                        if (documentSnapshot.exists()) {
//                                            // Lấy dữ liệu của equipment tại đây
//                                            // int equipmentQuantity = documentSnapshot.getLong("quantity").intValue();
//
//                                            String equipmentName = documentSnapshot.getString("name");
//                                            equipmentNames.add(equipmentName);
////                                            tvProjector.setText(documentSnapshot.getString("name"));
////
////                                            tvScreen.setText("Screen xx");
////                                            tvLamp.setText("Lamp xx");
////                                            tvSpeaker.setText("Speaker xx");
//                                            // Xử lý dữ liệu của equipment tại đây
//                                        }
//                                    }
//                                });
//                            }
                            StringBuilder sb = new StringBuilder();
                            for (String s : equipmentNames) {
                                sb.append(s).append(", ");
                            }

                            Log.d("DETAIL_CINEMAROOM", "sg: " + sb.toString());

                            // Kiểm tra xem chuỗi kết quả có ít nhất 1 phần tử hay không
                            if (sb.length() > 0 && sb.charAt(sb.length() - 2) == ',') {
                                // Loại bỏ dấu phẩy và khoảng trắng cuối cùng của chuỗi
                                sb.deleteCharAt(sb.length() - 2);
                            }

//                            tvProjector.setText(sb.toString());

                        }

                        tvName.setText(cinemaRoom.getName());

//                        tvRowSeats.setText("12");
//                        tvColumnSeats.setText("15");

//                        tvProjector.setText("Projector xx");
//                        tvScreen.setText("Screen xx");
//                        tvLamp.setText("Lamp xx");
//                        tvSpeaker.setText("Speaker xx");

                        tvRowSeats.setText(Integer.toString((int) cinemaRoom.getSeatsRow()));
                        tvColumnSeats.setText(Integer.toString((int)cinemaRoom.getSeatsColumn()));

//                        tvProjector.setText(cinemaRoom.getProjector().getName());
//                        tvScreen.setText(cinemaRoom.getScreen().getName());
//                        tvLamp.setText(cinemaRoom.getLamp().getName());
//                        tvSpeaker.setText(cinemaRoom.getSpeaker().getName());
                    } else {
                        Log.d("CINEMAROOM_DETAIL", "No docSnapshot");
                    }
                } else {
                    Log.d("CINEMAROOM_DETAIL", "get fail task: " + task.getException());
                }
            }
        });

        //Set EditBtn Event
        editCinemaRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (argCinemaRoomId != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("cinemaRoomId", argCinemaRoomId);
                    Navigation.findNavController(view).navigate(R.id.editCinemaRoomScreenFragment, bundle);
                }
            }
        });

        deleteCinemaRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDeleteDialog.setDeleteListener(new ConfirmDeleteDialog.OnDeleteListener() {
                    @Override
                    public void onDeleteClick() {
                        db.collection("theaters").document(argCinemaRoomId)
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getActivity(), "Xóa phòng chiếu thành công", Toast.LENGTH_SHORT).show();
                                        Navigation.findNavController(view).navigate(R.id.cinemaRoomScreenFragment);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getActivity(), "LỐI: XÓA PHÒNG CHIẾU KHÔNG THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });

                if(!confirmDeleteDialog.isAdded()) {
                    confirmDeleteDialog.show(getParentFragmentManager(), "DeleteDialog");
                }
            }
        });

    }
}
