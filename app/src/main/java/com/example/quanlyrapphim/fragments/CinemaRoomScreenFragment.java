package com.example.quanlyrapphim.fragments;

import android.app.DownloadManager;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.adapters.CinemaRoomRecyclerViewAdapter;
import com.example.quanlyrapphim.models.CinemaRoom;
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

public class CinemaRoomScreenFragment extends Fragment {
    private ArrayList<CinemaRoom> cinemaRoomArrayList = new ArrayList<>();
    private RecyclerView cinemaRecyclerView;
    private FloatingActionButton addCinemaRoomBtn;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ConfirmDeleteDialog confirmDeleteDialog = new ConfirmDeleteDialog();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cinemaroom_screen, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cinemaRecyclerView = view.findViewById(R.id.frmt_cinemaroom_rv_listRoom);

        addCinemaRoomBtn = view.findViewById(R.id.frmt_cinemaroom_bt_addRoom);

        db.collection("theaters")
                .orderBy("name", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            cinemaRoomArrayList = new ArrayList<CinemaRoom>();
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                                Log.d("GET CINEMAROOM", documentSnapshot.getId() + documentSnapshot.getData());

                                CinemaRoom cinemaRoom = documentSnapshot.toObject(CinemaRoom.class);
                                cinemaRoom.setId(documentSnapshot.getId());
                                cinemaRoomArrayList.add(cinemaRoom);
                            }

                            // SET CINEMAROOM LIST TO VIEW
                            CinemaRoomRecyclerViewAdapter adapter = new CinemaRoomRecyclerViewAdapter(getActivity(), cinemaRoomArrayList);

                            adapter.setOnDetailClickListener(i -> {
//                          Toast.makeText(getActivity(), "Detail cinema room at " + i, Toast.LENGTH_SHORT).show();
                                Bundle bundle = new Bundle();
                                bundle.putString("cinemaRoomId", cinemaRoomArrayList.get(i).getId());
                                Navigation.findNavController(view).navigate(R.id.action_cinemaRoomScreenFragment_to_detailCinemaRoomScreenFragment, bundle);
                            });

                            adapter.setOnEditClickListener(i -> {
//                                Toast.makeText(getActivity(), "Edit cinema room at " + i, Toast.LENGTH_SHORT).show();
                                Bundle bundle = new Bundle();
                                bundle.putString("cinemaRoomId", cinemaRoomArrayList.get(i).getId());
                                Navigation.findNavController(view).navigate(R.id.action_cinemaRoomScreenFragment_to_editCinemaRoomScreenFragment, bundle);

                            });

                            adapter.setOnDeleteClickListener(i -> {
//                                Toast.makeText(getActivity(), "Delete cinema room at " + i, Toast.LENGTH_SHORT).show();

                                confirmDeleteDialog.setDeleteListener(new ConfirmDeleteDialog.OnDeleteListener() {
                                    @Override
                                    public void onDeleteClick() {
                                        String id = cinemaRoomArrayList.get(i).getId();

                                        // HANDLE DELETE FILM
                                        db.collection("theaters").document(id)
                                                .delete()
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(getActivity(), "Xoá phim thành công", Toast.LENGTH_SHORT).show();

                                                        // delete in ui
                                                        cinemaRoomArrayList.remove(i);
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
//
                            cinemaRecyclerView.setAdapter(adapter);
                            cinemaRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                        }
                        else {
                            Log.d("GET_CINEMAROOM","ERROR IN GET CINEMAROOM", task.getException());
                        }
                    }
                });


        addCinemaRoomBtn.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_cinemaRoomScreenFragment_to_addCinemaRoomScreenFragment);
        });
    }
    private void initCinemas() {
        cinemaRoomArrayList.add(new CinemaRoom("Phòng chiếu 1", "VIP", 10, 12));
        cinemaRoomArrayList.add(new CinemaRoom("Phòng chiếu 2", "Normal", 15, 12));
        cinemaRoomArrayList.add(new CinemaRoom("Phòng chiếu 3", "VIP", 10, 10));
        cinemaRoomArrayList.add(new CinemaRoom("Phòng chiếu 4", "Normal", 10, 10));
        cinemaRoomArrayList.add(new CinemaRoom("Phòng chiếu 5", "Normal", 10, 10));
    }
}
