package com.example.quanlyrapphim.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.adapters.FilmInBookingRecyclerViewAdapter;
import com.example.quanlyrapphim.adapters.FilmRecyclerViewAdapter;
import com.example.quanlyrapphim.adapters.SeatRecyclerViewAdapter;
import com.example.quanlyrapphim.models.Film;
import com.example.quanlyrapphim.models.Seat;
import com.example.quanlyrapphim.utils.ConfirmDeleteDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class TicketScreenFragment extends Fragment {

    private ArrayList<Seat> seats = new ArrayList<>();
    private RecyclerView seatRecyclerView;
    private RecyclerView filmRecyclerView;
    private ArrayList<Film> allFilms = new ArrayList<>();
    private LinearLayout selectedFilmGroup;
    private MaterialButton removeFilmBtn;
    private ImageView selectedFilmImage;
    private TextView selectedFilmName;
    private Film selectedFilm;
    private TextInputEditText inputDate;
    private Date selectedDate;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Chọn ngày chiếu").build();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSeats();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket_screen, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // view
//        seatRecyclerView = view.findViewById(R.id.ticket_screen_seat_recycler_view);
//        SeatRecyclerViewAdapter adapter = new SeatRecyclerViewAdapter(getActivity(), seats, 10);
//        seatRecyclerView.setAdapter(adapter);
//        seatRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 10));
        filmRecyclerView = view.findViewById(R.id.ticket_screen_film_recycler_view);
        selectedFilmGroup = view.findViewById(R.id.ticket_screen_selected_film_group);
        removeFilmBtn = view.findViewById(R.id.ticket_screen_remove_film);
        selectedFilmImage = view.findViewById(R.id.ticket_screen_selected_film_image);
        selectedFilmName = view.findViewById(R.id.ticket_screen_selected_film_name);
        inputDate = view.findViewById(R.id.ticket_screen_input_date);

        // Show date picker
        inputDate.setOnClickListener(v -> {
            if (!datePicker.isAdded()) {
                datePicker.show(getParentFragmentManager(), "DATE_PICKER");
            }
            datePicker.addOnPositiveButtonClickListener(selection -> {
                        inputDate.setText(datePicker.getHeaderText());
                        selectedDate = new Date((Long) selection);
                    }
            );
        });

        selectedFilmGroup.setVisibility(View.GONE);
        removeFilmBtn.setOnClickListener(v -> {
            onRemoveFilm();
        });

        // Get films from db
        db.collection("films")
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            allFilms = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d("GET_FILM", document.getId() + " => " + document.getData());
                                Film film = document.toObject(Film.class);
                                film.setId(document.getId());
                                allFilms.add(film);
                            }


                            // SET TO VIEW

                            FilmInBookingRecyclerViewAdapter adapter = new FilmInBookingRecyclerViewAdapter(getActivity(), allFilms);


                            // set click card
                            adapter.setOnClickListener(i -> {
                                selectedFilm = allFilms.get(i);
                                onSelectFilm(selectedFilm);


                            });

                            // set adapter
                            filmRecyclerView.setAdapter(adapter);
                            LinearLayoutManager layoutManager
                                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                            filmRecyclerView.setLayoutManager(layoutManager);

                        } else {
                            Log.d("GET_FILM", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    void onSelectFilm(Film film) {
        filmRecyclerView.setVisibility(View.GONE);
        selectedFilmGroup.setVisibility(View.VISIBLE);
        Picasso.get().load(film.getImage()).into(selectedFilmImage);
        selectedFilmName.setText(film.getName());
    }

    void onRemoveFilm() {
        selectedFilm = null;
        filmRecyclerView.setVisibility(View.VISIBLE);
        selectedFilmGroup.setVisibility(View.GONE);
    }

    void initSeats() {
        for (int i = 0; i < 50; i++) {
            seats.add(new Seat(i % 3));
        }
    }

}