package com.example.quanlyrapphim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.models.CinemaRoom;
import com.example.quanlyrapphim.models.Film;
import com.example.quanlyrapphim.models.TimeSlot;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TimeSlotSpinnerAdapter extends ArrayAdapter<TimeSlot> {

    public TimeSlotSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<TimeSlot> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_slot_card_spinner,parent,false);


        TextView tvStart = convertView.findViewById(R.id.time_slot_card_spinner_tv_start);
        TextView tvEnd = convertView.findViewById(R.id.time_slot_card_spinner_tv_end);

        TimeSlot timeSlot = <TimeSlot> this.getItem(position);

        tvStart.setText(timeSlot.getStart());
        tvEnd.setText(timeSlot.getEnd());

        return convertView;
    }

    @Override
    public int getPosition(@Nullable TimeSlot item) {
        return super.getPosition(item);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public TimeSlot getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_slot_card_spinner,parent,false);

        TextView tvStart = convertView.findViewById(R.id.time_slot_card_spinner_tv_start);
        TextView tvEnd = convertView.findViewById(R.id.time_slot_card_spinner_tv_end);

        TimeSlot timeSlot = <TimeSlot> this.getItem(position);
        if (timeSlot != null) {
            tvStart.setText(timeSlot.getStart());
            tvEnd.setText(timeSlot.getEnd());
        }
        else
            Toast.makeText(parent.getContext(), "TimeSlot get view null",Toast.LENGTH_LONG).show();

        return convertView;
    }

    @Override
    public void add(@Nullable TimeSlot object) {
        super.add(object);
    }
}
