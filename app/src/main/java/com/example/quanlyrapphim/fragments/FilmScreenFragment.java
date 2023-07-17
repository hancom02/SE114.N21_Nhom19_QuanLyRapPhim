package com.example.quanlyrapphim.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.adapters.FilmRecyclerViewAdapter;
import com.example.quanlyrapphim.models.Film;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FilmScreenFragment extends Fragment {
    private ArrayList<Film> films = new ArrayList<>();
    private RecyclerView filmRecyclerView;
    private FloatingActionButton btnAddFilm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFilms();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        filmRecyclerView = view.findViewById(R.id.film_recycle_view);
        btnAddFilm = view.findViewById(R.id.film_screen_btn_add_film);

        FilmRecyclerViewAdapter adapter = new FilmRecyclerViewAdapter(getActivity(), films);
        adapter.setOnDeleteClickListener(i -> {
            Toast.makeText(getActivity(), "Deleted item at " + i, Toast.LENGTH_SHORT).show();
        });
        adapter.setOnEditClickListener(i -> {
            Toast.makeText(getActivity(), "Edit item at " + i, Toast.LENGTH_SHORT).show();
        });
        filmRecyclerView.setAdapter(adapter);
        filmRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnAddFilm.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_filmScreenFragment_to_addFilmScreenFragment);
        });
    }

    private void initFilms() {
        films.add(new Film("Midnight Whispers", "https://images.unsplash.com/photo-1590179068383-b9c69aacebd3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZmlsbSUyMHBvc3RlcnxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        films.add(new Film("Shattered Dreams of Yesterday", "https://images.unsplash.com/photo-1578655858279-e17d055eafd0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTF8fGZpbG0lMjBwaG90b2dyYXBoeXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        films.add(new Film("Echoes of Forgotten Memories - I don't want to code in java!", "https://images.unsplash.com/photo-1543487945-139a97f387d5?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8cG9zdGVyfGVufDB8fDB8fHww&auto=format&fit=crop&w=500&q=60"));
        films.add(new Film("The Enigma's Hidden Legacy", "https://images.unsplash.com/photo-1583407723467-9b2d22504831?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjB8fHBvc3RlcnxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        films.add(new Film("Serendipity's Dance of Fate", "https://plus.unsplash.com/premium_photo-1668051042204-038187cac123?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8YW5pbWV8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=500&q=60"));
    }
}