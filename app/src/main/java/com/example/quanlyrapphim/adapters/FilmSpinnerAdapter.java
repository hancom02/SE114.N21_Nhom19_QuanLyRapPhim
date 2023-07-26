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
import com.example.quanlyrapphim.models.Film;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmSpinnerAdapter extends ArrayAdapter<Film> {
    public FilmSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Film> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_card_spinner_selected,parent,false);


        TextView tvFilm = convertView.findViewById(R.id.film_card_spinner_dropdown_tv_name);

        Film film = this.getItem(position);

        tvFilm.setText(film.getName());
        tvFilm.setSelected(true);

        return convertView;
    }

    @Override
    public int getPosition(@Nullable Film item) {
        return super.getPosition(item);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Film getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_card_spinner,parent,false);

        ImageView imgFilm = convertView.findViewById(R.id.film_card_spinner_image);
        TextView tvFilm = convertView.findViewById(R.id.film_card_spinner_tv_name);

        Film film = this.getItem(position);
        if (film != null) {
            Picasso.get().load(film.getImage()).into(imgFilm);
            tvFilm.setText(film.getName());
        }
        else
            Toast.makeText(parent.getContext(), "Film get view null",Toast.LENGTH_LONG).show();

        return convertView;
    }

    @Override
    public void add(@Nullable Film object) {
        super.add(object);
    }
}
