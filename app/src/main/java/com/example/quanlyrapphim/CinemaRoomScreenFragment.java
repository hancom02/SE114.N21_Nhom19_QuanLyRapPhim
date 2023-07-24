package com.example.quanlyrapphim;

import android.os.Bundle;
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

import com.example.quanlyrapphim.adapters.CinemaRoomRecyclerViewAdapter;
import com.example.quanlyrapphim.models.CinemaRoom;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CinemaRoomScreenFragment extends Fragment {
    private ArrayList<CinemaRoom> cinemaRoomArrayList = new ArrayList<>();
    private RecyclerView cinemaRecyclerView;
    private FloatingActionButton addCinemaRoomBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCinemas();
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

        CinemaRoomRecyclerViewAdapter adapter = new CinemaRoomRecyclerViewAdapter(getActivity(), cinemaRoomArrayList);
        adapter.setOnDetailClickListener(i -> {
//            Toast.makeText(getActivity(), "Detail cinema room at " + i, Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigate(R.id.action_cinemaRoomScreenFragment_to_detailCinemaRoomScreenFragment);
        });
        adapter.setOnEditClickListener(i -> {
            Toast.makeText(getActivity(), "Edit cinema room at " + i, Toast.LENGTH_SHORT).show();
        });
        adapter.setOnDeleteClickListener(i -> {
            Toast.makeText(getActivity(), "Delete cinema room at " + i, Toast.LENGTH_SHORT).show();
        });
//
        cinemaRecyclerView.setAdapter(adapter);
        cinemaRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
