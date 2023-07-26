package com.example.quanlyrapphim.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.models.Film;
import com.example.quanlyrapphim.utils.RecyclerViewItemEventListener;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FilmInBookingRecyclerViewAdapter extends RecyclerView.Adapter<FilmInBookingRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Film> films;
    private RecyclerViewItemEventListener clickListener;

    public FilmInBookingRecyclerViewAdapter(Context context, ArrayList<Film> films) {
        this.context = context;
        this.films = films;
    }

    @NonNull
    @Override
    public FilmInBookingRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.film_card_in_booking, viewGroup, false);

        return new FilmInBookingRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.tvName.setText(films.get(i).getName());
        Picasso.get().load(films.get(i).getImage()).into(holder.imgvImage);
        holder.card.setOnClickListener(view -> {
            clickListener.onItemEvent(i);
        });
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public void setOnClickListener(RecyclerViewItemEventListener listener) {
        clickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imgvImage;
        View card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.film_card_in_booking_name);
            imgvImage = itemView.findViewById(R.id.film_card_in_booking_image);
            card = itemView;
        }
    }


}