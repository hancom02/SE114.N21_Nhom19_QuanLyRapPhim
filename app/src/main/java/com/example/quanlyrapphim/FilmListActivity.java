package com.example.quanlyrapphim;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.time.Instant;
import java.util.ArrayList;

public class FilmListActivity extends AppCompatActivity {

    private ArrayList<Film> films = new ArrayList<>();
    private RecyclerView filmRecyclerView;
    private ImageButton btnAddFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_list);
        initFilms();

        filmRecyclerView = findViewById(R.id.list_film_recycle_view);
        btnAddFilm = findViewById(R.id.btnAddFilm);

        FilmRecyclerViewAdapter adapter = new FilmRecyclerViewAdapter(this, films);
        adapter.setOnDeleteClickListener(i -> {
            Toast.makeText(FilmListActivity.this, "Deleted item at " + i, Toast.LENGTH_SHORT).show();
        });
        adapter.setOnEditClickListener(i -> {
            Toast.makeText(FilmListActivity.this, "Edit item at " + i, Toast.LENGTH_SHORT).show();
        });
        filmRecyclerView.setAdapter(adapter);
        filmRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAddFilm.setOnClickListener(view -> {
            startActivity(new Intent(FilmListActivity.this, AddFilmActivity.class));
        });

    }

    private void initFilms() {
        films.add(new Film("Midnight Whispers in the Dark", "https://picsum.photos/536/354"));
        films.add(new Film("Shattered Dreams of Yesterday", "https://picsum.photos/536/354"));
        films.add(new Film("Echoes of Forgotten Memories", "https://picsum.photos/536/354"));
        films.add(new Film("The Enigma's Hidden Legacy", "https://picsum.photos/536/354"));
        films.add(new Film("Serendipity's Dance of Fate", "https://picsum.photos/536/354"));
    }
}

class FilmRecyclerViewAdapter extends RecyclerView.Adapter<FilmRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Film> films;
    private OnDeleteClickListener deleteListener;
    private OnEditClickListener editClickListener;

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
            deleteListener.onDeleteClick(i);
        });
        holder.btnEdit.setOnClickListener(view -> {
            editClickListener.onEditClick(i);
        });
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    void setOnDeleteClickListener(OnDeleteClickListener listener) {
        deleteListener = listener;
    }

    void setOnEditClickListener(OnEditClickListener listener) {
        editClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imgvImage;
        ImageButton btnDelete;
        ImageButton btnEdit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.filmName);
            imgvImage = itemView.findViewById(R.id.filmImage);
            btnDelete = itemView.findViewById(R.id.btnDeleteFilm);
            btnEdit = itemView.findViewById(R.id.btnEditFilm);
        }
    }

    interface OnDeleteClickListener {
        void onDeleteClick(int i);
    }

    interface OnEditClickListener {
        void onEditClick(int i);
    }

}

class Film {
    private String name;
    private String image;

    public Film(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}