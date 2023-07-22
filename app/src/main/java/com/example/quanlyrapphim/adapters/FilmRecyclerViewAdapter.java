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

public class FilmRecyclerViewAdapter extends RecyclerView.Adapter<FilmRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Film> films;
    private RecyclerViewItemEventListener deleteClickListener;
    private RecyclerViewItemEventListener editClickListener;
    private RecyclerViewItemEventListener clickListener;

    public FilmRecyclerViewAdapter(Context context, ArrayList<Film> films) {
        this.context = context;
        this.films = films;
    }

    @NonNull
    @Override
    public FilmRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.film_card, viewGroup, false);

        return new FilmRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.tvName.setText(films.get(i).getName());
        Picasso.get().load(films.get(i).getImage()).into(holder.imgvImage);
        holder.btnDelete.setOnClickListener(view -> {
            deleteClickListener.onItemEvent(i);
        });
        holder.btnEdit.setOnClickListener(view -> {
            editClickListener.onItemEvent(i);
        });
        holder.card.setOnClickListener(view -> {
            clickListener.onItemEvent(i);
        });
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public void setOnDeleteClickListener(RecyclerViewItemEventListener listener) {
        deleteClickListener = listener;
    }

    public void setOnEditClickListener(RecyclerViewItemEventListener listener) {
        editClickListener = listener;
    }
    public void setOnClickListener(RecyclerViewItemEventListener listener) {
        clickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imgvImage;
        MaterialButton btnDelete;
        MaterialButton btnEdit;
        View card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.film_card_name);
            imgvImage = itemView.findViewById(R.id.film_card_image);
            btnDelete = itemView.findViewById(R.id.film_card_btn_delete);
            btnEdit = itemView.findViewById(R.id.film_card_btn_edit);
            card = itemView;
        }
    }


}