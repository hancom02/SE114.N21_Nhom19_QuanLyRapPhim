package com.example.quanlyrapphim.adapters;

import android.app.Activity;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.models.Refreshment;
import com.example.quanlyrapphim.models.TimeSlot;
import com.example.quanlyrapphim.utils.RecyclerViewItemEventListener;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class TimeSlotRecyclerViewAdapter extends RecyclerView.Adapter<TimeSlotRecyclerViewAdapter.MyViewHolder> {

    private Activity context;
    ArrayList<TimeSlot> timeSlotArrayList;
    private RecyclerViewItemEventListener editClickListener;
    private RecyclerViewItemEventListener deleteClickListener;

    public TimeSlotRecyclerViewAdapter(Activity context, ArrayList<TimeSlot> timeSlotArrayList) {
        this.context = context;
        this.timeSlotArrayList = timeSlotArrayList;
    }

    @NonNull
    @Override
    public TimeSlotRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newViewHolder = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.time_slot_card, parent, false);
        return new TimeSlotRecyclerViewAdapter.MyViewHolder(newViewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvStart.setText(timeSlotArrayList.get(position).getStart());
        holder.tvEnd.setText(timeSlotArrayList.get(position).getEnd());

//        holder.tvStart.setText("9:00");
//        holder.tvEnd.setText("11:00");

        holder.btnEdit.setOnClickListener(view -> {
            editClickListener.onItemEvent(position);
        });
        holder.btnDelete.setOnClickListener(view -> {
            deleteClickListener.onItemEvent(position);
        });
    }

    @Override
    public int getItemCount() {
        return timeSlotArrayList.size();
    }
    public void setOnEditClickListener(RecyclerViewItemEventListener listener) {
        editClickListener = listener;
    }
    public void setOnDeleteClickListener(RecyclerViewItemEventListener listener) {
        deleteClickListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvStart;
        TextView tvEnd;
        MaterialButton btnEdit;
        MaterialButton btnDelete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStart = itemView.findViewById(R.id.time_slot_card_tv_start);
            tvEnd = itemView.findViewById(R.id.time_slot_card_tv_end);
            btnEdit = itemView.findViewById(R.id.timeslot_card_btn_edit);
            btnDelete = itemView.findViewById(R.id.time_slot_card_btn_delete);
        }
    }
}
