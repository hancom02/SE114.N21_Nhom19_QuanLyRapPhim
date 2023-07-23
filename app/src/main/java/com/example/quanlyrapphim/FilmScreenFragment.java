package com.example.quanlyrapphim;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quanlyrapphim.adapters.FilmRecyclerViewAdapter;
import com.example.quanlyrapphim.models.Film;
import com.example.quanlyrapphim.utils.ConfirmDeleteDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FilmScreenFragment extends Fragment {
    private ArrayList<Film> films = new ArrayList<>();
    private RecyclerView filmRecyclerView;
    private FloatingActionButton btnAddFilm;
    ConfirmDeleteDialog confirmDeleteDialog = new ConfirmDeleteDialog();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


        // Get films from db
        db.collection("films")
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            films = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d("GET_FILM", document.getId() + " => " + document.getData());
                                Film film = document.toObject(Film.class);
                                film.setId(document.getId());
                                films.add(film);
                            }


                            // SET TO VIEW
                            
                            FilmRecyclerViewAdapter adapter = new FilmRecyclerViewAdapter(getActivity(), films);
                            
                            // set delete btn
                            adapter.setOnDeleteClickListener(i -> {

                                confirmDeleteDialog.setDeleteListener(new ConfirmDeleteDialog.OnDeleteListener() {
                                    @Override
                                    public void onDeleteClick() {
                                        String id = films.get(i).getId();
                                        
                                        // HANDLE DELETE FILM
                                        db.collection("films").document(id)
                                                .delete()
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(getActivity(), "Xoá phim thành công", Toast.LENGTH_SHORT).show();

                                                        // delete in ui
                                                        films.remove(i);
                                                        adapter.notifyDataSetChanged();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(getActivity(), "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                                                    }
                                                });


                                    }
                                });

                                if (!confirmDeleteDialog.isAdded()) {
                                    confirmDeleteDialog.show(getParentFragmentManager(), "DeleteDialog");
                                }
                            });
                            
                            // set edit btn
                            adapter.setOnEditClickListener(i -> {
                                //Toast.makeText(getActivity(), "Edit item at " + i, Toast.LENGTH_SHORT).show();
                                Bundle bundle = new Bundle();
                                bundle.putString("filmId", films.get(i).getId());
                                Navigation.findNavController(view).navigate(R.id.action_filmScreenFragment_to_editFilmScreenFragment, bundle);
                            });
                            
                            // set click card
                            adapter.setOnClickListener(i -> {
                                Bundle bundle = new Bundle();
                                bundle.putString("filmId", films.get(i).getId());
                                Navigation.findNavController(view).navigate(R.id.action_filmScreenFragment_to_filmDetailScreenFragment, bundle);
                            });
                            
                            // set adapter
                            filmRecyclerView.setAdapter(adapter);
                            filmRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                        } else {
                            Log.d("GET_FILM", "Error getting documents: ", task.getException());
                        }
                    }
                });




        btnAddFilm.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_filmScreenFragment_to_addFilmScreenFragment);
        });
    }
}