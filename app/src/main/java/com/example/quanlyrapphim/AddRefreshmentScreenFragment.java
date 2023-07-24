package com.example.quanlyrapphim;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class AddRefreshmentScreenFragment extends Fragment {

    private MaterialButton addRefreshmentBtn;
//    private MaterialButton saveRefreshmentBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        addCinemaRoomBtn = addCinemaRoomBtn.findViewById(R.id.item_cinemaroom_spinner_bt_add);
//        addCinemaRoomBtn.setVisibility(View.VISIBLE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_refreshment_screen, container, false);

        addRefreshmentBtn = view.findViewById(R.id.add_refreshment_btn_add);
        addRefreshmentBtn.setVisibility(View.VISIBLE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



//        addCinemaRoomBtn = view.findViewById(R.id.item_cinemaroom_spinner_bt_add);
//        addCinemaRoomBtn.setVisibility(View.VISIBLE);
    }
}
