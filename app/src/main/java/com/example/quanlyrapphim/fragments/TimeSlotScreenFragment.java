package com.example.quanlyrapphim.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.adapters.RefreshmentsRecyclerViewAdapter;
import com.example.quanlyrapphim.adapters.TimeSlotRecyclerViewAdapter;
import com.example.quanlyrapphim.models.Refreshment;
import com.example.quanlyrapphim.models.TimeSlot;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TimeSlotScreenFragment extends Fragment {

    private ArrayList<TimeSlot> timeSlotArrayList = new ArrayList<>();
    private RecyclerView timeSlotRecyclerView;
    private FloatingActionButton addTimeSlotBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTimeSlot();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_slot_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        timeSlotRecyclerView = view.findViewById(R.id.frmt_timeslot_rv_listTimeSlot);
        addTimeSlotBtn = view.findViewById(R.id.frmt_timeslot_bt_addTimeSlot);

        TimeSlotRecyclerViewAdapter adapter = new TimeSlotRecyclerViewAdapter(getActivity(), timeSlotArrayList);

        adapter.setOnDeleteClickListener(i -> {
            Toast.makeText(getActivity(), "Deleted item at " + i, Toast.LENGTH_SHORT).show();
        });
        adapter.setOnEditClickListener(i -> {
            Toast.makeText(getActivity(), "Edit item at " + i, Toast.LENGTH_SHORT).show();
//            Navigation.findNavController(view).navigate(R.id.action_refreshmentScreenFragment_to_editRefreshmentScreenFragment);
        });
        timeSlotRecyclerView.setAdapter(adapter);
        timeSlotRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addTimeSlotBtn.setOnClickListener(v -> {
//            Toast.makeText(getActivity(), "Nav to add refreshment screen", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigate(R.id.action_timeSlotScreenFragment_to_addTimeSlotScreenFragment);
        });
    }

    private void initTimeSlot() {
        timeSlotArrayList.add(new TimeSlot("8:20", "11:00"));
        timeSlotArrayList.add(new TimeSlot("9:20", "12:00"));
        timeSlotArrayList.add(new TimeSlot("10:20", "13:00"));
        timeSlotArrayList.add(new TimeSlot("11:20", "14:00"));
        timeSlotArrayList.add(new TimeSlot("12:20", "15:00"));
    }
}