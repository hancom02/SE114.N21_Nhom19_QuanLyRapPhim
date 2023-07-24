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

public class EditFreshmentScreenFragment extends Fragment{

    private MaterialButton editRefreshmentBtn;
    private MaterialButton saveRefreshmentBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_refreshment_screen, container, false);

        editRefreshmentBtn = view.findViewById(R.id.edit_refreshment_btn_edit);
        editRefreshmentBtn.setVisibility(View.VISIBLE);

        saveRefreshmentBtn = view.findViewById(R.id.edit_refreshment_btn_save);
        editRefreshmentBtn.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        editRefreshmentBtn = view.findViewById(R.id.edit_refreshment_btn_edit);
//        editRefreshmentBtn.setVisibility(View.GONE);
//
//        saveRefreshmentBtn = view.findViewById(R.id.edit_refreshment_btn_save);
//        editRefreshmentBtn.setVisibility(View.VISIBLE);



//        addCinemaRoomBtn = view.findViewById(R.id.item_cinemaroom_spinner_bt_add);
//        addCinemaRoomBtn.setVisibility(View.VISIBLE);
    }


}
