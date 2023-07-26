package com.example.quanlyrapphim.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.adapters.FilmRecyclerViewAdapter;
import com.example.quanlyrapphim.adapters.SeatRecyclerViewAdapter;
import com.example.quanlyrapphim.models.Seat;

import java.util.ArrayList;

public class TicketScreenFragment extends Fragment {

    private ArrayList<Seat> seats = new ArrayList<>();
    private RecyclerView seatRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSeats();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket_screen, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // view
        seatRecyclerView = view.findViewById(R.id.ticket_screen_seat_recycler_view);
        SeatRecyclerViewAdapter adapter = new SeatRecyclerViewAdapter(getActivity(), seats, 10);
        seatRecyclerView.setAdapter(adapter);
        seatRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 10));
    }

    void initSeats() {
        for (int i = 0; i < 90; i++) {
            seats.add(new Seat(i%3));
        }
    }

}