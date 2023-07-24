package com.example.quanlyrapphim.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.models.ShowTime;
import com.example.quanlyrapphim.utils.RecyclerViewItemEventListener;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class ShowTimeRecyclerViewAdapter extends RecyclerView.Adapter<ShowTimeRecyclerViewAdapter.MyViewHolder> {
    private Activity context;
    ArrayList<ShowTime> showTimeArrayList;
    private RecyclerViewItemEventListener editClickListener;
    private RecyclerViewItemEventListener deleteClickListener;

    public ShowTimeRecyclerViewAdapter(Activity context, ArrayList<ShowTime> showTimeArrayList) {
        this.context = context;
        this.showTimeArrayList = showTimeArrayList;
    }

    @NonNull
    @Override
    public ShowTimeRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newViewHolder = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.show_time_card, parent, false);
        return new ShowTimeRecyclerViewAdapter.MyViewHolder(newViewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowTimeRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvFilm.setText(showTimeArrayList.get(position).getFilm().getName());
        holder.tvTime1.setText(showTimeArrayList.get(position).getTimeSlot().getStart());
        holder.tvCinemas1.setText(showTimeArrayList.get(position).getCinemaRoom().getName());

        holder.btnEdit.setOnClickListener(view -> {
            editClickListener.onItemEvent(position);
        });
        holder.btnDelete.setOnClickListener(view -> {
            deleteClickListener.onItemEvent(position);
        });
    }

    @Override
    public int getItemCount() {
        return showTimeArrayList.size();
    }
    public void setOnEditClickListener(RecyclerViewItemEventListener listener) {
        editClickListener = listener;
    }
    public void setOnDeleteClickListener(RecyclerViewItemEventListener listener) {
        deleteClickListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvFilm;
        TextView tvTime1;
        TextView tvCinemas1;
        MaterialButton btnEdit;
        MaterialButton btnDelete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFilm = itemView.findViewById(R.id.show_time_card_tv_film);
            tvTime1 = itemView.findViewById(R.id.show_time_card_tv_time1);
            tvCinemas1 = itemView.findViewById(R.id.show_time_card_tv_cinema1);
            btnEdit = itemView.findViewById(R.id.show_time_card_btn_edit);
            btnDelete = itemView.findViewById(R.id.show_time_card_btn_delete);
        }
    }
}
