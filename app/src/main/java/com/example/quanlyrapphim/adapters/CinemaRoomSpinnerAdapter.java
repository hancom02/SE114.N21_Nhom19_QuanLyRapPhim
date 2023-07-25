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
import com.squareup.picasso.Picasso;

import java.util.List;

public class CinemaRoomSpinnerAdapter extends ArrayAdapter<CinemaRoom> {

    public CinemaRoomSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<CinemaRoom> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cinemaromm_card_spinner,parent,false);


        TextView tvCinemaRoom = convertView.findViewById(R.id.cinemarom_card_spinner_tv);

        CinemaRoom cinemaRoom = <CinemaRoom> this.getItem(position);

        tvCinemaRoom.setText(cinemaRoom.getName());
        tvCinemaRoom.setSelected(true);

        return convertView;
    }

    @Override
    public int getPosition(@Nullable CinemaRoom item) {
        return super.getPosition(item);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public CinemaRoom getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cinemaromm_card_spinner,parent,false);


        TextView tvCinemaRoom = convertView.findViewById(R.id.cinemarom_card_spinner_tv);

        CinemaRoom cinemaRoom = <CinemaRoom> this.getItem(position);
        if (cinemaRoom != null) {
            tvCinemaRoom.setText(cinemaRoom.getName());
        }
        else
            Toast.makeText(parent.getContext(), "CinemaRoom get view null",Toast.LENGTH_LONG).show();

        return convertView;
    }

    @Override
    public void add(@Nullable CinemaRoom object) {
        super.add(object);
    }
}
