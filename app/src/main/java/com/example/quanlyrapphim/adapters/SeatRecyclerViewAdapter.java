package com.example.quanlyrapphim.adapters;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.models.Account;
import com.example.quanlyrapphim.models.Seat;
import com.example.quanlyrapphim.utils.RecyclerViewItemEventListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SeatRecyclerViewAdapter extends RecyclerView.Adapter<SeatRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Seat> seats;
    private int numColumn;
    private RecyclerViewItemEventListener clickListener;

    public SeatRecyclerViewAdapter(Context context, ArrayList<Seat> seats, int numColumn) {
        this.context = context;
        this.seats = seats;
        this.numColumn = numColumn;
    }

    @NonNull
    @Override
    public SeatRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.seat_card, viewGroup, false);

        return new SeatRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.label.setText(Seat.getLabel(i, numColumn));
        if (seats.get(i).getStatus() == 0) {
            holder.card.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#dddddd")));
        }
        if (seats.get(i).getStatus() == 1) {
            holder.card.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#ffaaaa")));
        }
        if (seats.get(i).getStatus() == 2) {
            holder.card.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor("#aaffaa")));
        }
        holder.card.setOnClickListener(view -> {
            clickListener.onItemEvent(i);
        });
    }

    @Override
    public int getItemCount() {
        return seats.size();
    }

    public void setOnClickListener(RecyclerViewItemEventListener listener) {
        clickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView label;
        MaterialCardView card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.seat_card_label);
            card = (MaterialCardView)itemView;
        }
    }


}