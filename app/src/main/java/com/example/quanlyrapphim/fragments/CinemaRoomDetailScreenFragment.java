package com.example.quanlyrapphim.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quanlyrapphim.R;
import com.google.android.material.button.MaterialButton;

public class DetailCinemaRoomScreenFragment extends Fragment {
    private MaterialButton editCinemaRoomBtn;
//    private MaterialButton addCinemaRoomBtn;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        editCinemaRoomBtn = editCinemaRoomBtn.findViewById(R.id.item_cinemaroom_spinner_bt_edit);
//        editCinemaRoomBtn.setVisibility(View.GONE);
//
//        addCinemaRoomBtn = addCinemaRoomBtn.findViewById(R.id.item_cinemaroom_spinner_bt_add);
//        addCinemaRoomBtn.setVisibility(View.GONE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cinemaroom_detail_screen, container, false);

        editCinemaRoomBtn = view.findViewById(R.id.frg_cinemaroom_detail_btn_edit);
        editCinemaRoomBtn.setVisibility(View.VISIBLE);

//        addCinemaRoomBtn = view.findViewById(R.id.item_cinemaroom_spinner_bt_add);
//        addCinemaRoomBtn.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        editCinemaRoomBtn = view.findViewById(R.id.item_cinemaroom_spinner_bt_edit);
//        editCinemaRoomBtn.setVisibility(View.GONE);
//
//        addCinemaRoomBtn = view.findViewById(R.id.item_cinemaroom_spinner_bt_add);
//        addCinemaRoomBtn.setVisibility(View.GONE);
    }
}
