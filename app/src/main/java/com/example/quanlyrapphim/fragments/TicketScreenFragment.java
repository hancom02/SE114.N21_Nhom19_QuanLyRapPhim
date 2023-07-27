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
import com.example.quanlyrapphim.adapters.ShowTimeInBookingRecyclerViewAdapter;
import com.example.quanlyrapphim.models.CinemaRoom;
import com.example.quanlyrapphim.models.Film;
import com.example.quanlyrapphim.models.Seat;
import com.example.quanlyrapphim.models.ShowTimeUI;
import com.example.quanlyrapphim.models.ShowTime_;
import com.example.quanlyrapphim.models.TimeSlot;
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

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TicketScreenFragment extends Fragment {




    private ArrayList<Seat> seats = new ArrayList<>();
    private ArrayList<ShowTime_> showTimes = new ArrayList<>();
    private ShowTime_ selectedShowTime;
    private ArrayList<ShowTimeUI> showTimeUIS = new ArrayList<>();
    private RecyclerView seatRecyclerView;
    private RecyclerView filmRecyclerView;
    private RecyclerView showTimeRecyclerView;
    private ArrayList<Film> allFilms = new ArrayList<>();
    private LinearLayout selectedFilmGroup;
    private MaterialButton removeFilmBtn;
    private ImageView selectedFilmImage;
    private TextView selectedFilmName;
    private Film selectedFilm;
    private TextInputEditText inputDate;
    private Date selectedDate;
    SeatRecyclerViewAdapter seatAdapter;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Chọn ngày chiếu").build();

    Map<String, TimeSlot> timeSlotMap = new HashMap<String, TimeSlot>();
    Map<String, CinemaRoom> roomMap = new HashMap<String, CinemaRoom>();
    Map<String, Film> filmMap = new HashMap<String, Film>();
    private ShowTimeInBookingRecyclerViewAdapter showTimeAdapter;


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
        seatRecyclerView = view.findViewById(R.id.ticket_screen_seat_recycler_view);
        filmRecyclerView = view.findViewById(R.id.ticket_screen_film_recycler_view);
        selectedFilmGroup = view.findViewById(R.id.ticket_screen_selected_film_group);
        removeFilmBtn = view.findViewById(R.id.ticket_screen_remove_film);
        selectedFilmImage = view.findViewById(R.id.ticket_screen_selected_film_image);
        selectedFilmName = view.findViewById(R.id.ticket_screen_selected_film_name);
        inputDate = view.findViewById(R.id.ticket_screen_input_date);
        showTimeRecyclerView = view.findViewById(R.id.ticket_screen_show_time_recycler_view);

        seatAdapter = new SeatRecyclerViewAdapter(getActivity(), seats, 10);
        seatAdapter.setOnClickListener(i -> {
            if (seats.get(i).getStatus() == 1) {
                return;
            }
            if (seats.get(i).getStatus() == 0) {
                seats.get(i).setStatus(2);
            }
            else if (seats.get(i).getStatus() == 2) {
                seats.get(i).setStatus(0);
            }
            seatAdapter.notifyItemChanged(i);
        });
        seatRecyclerView.setAdapter(seatAdapter);
        seatRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 10));


        showTimeAdapter = new ShowTimeInBookingRecyclerViewAdapter(getActivity(), showTimeUIS);
        // set click card
        showTimeAdapter.setOnClickListener(i -> {
            ///

        });
        // set adapter
        showTimeRecyclerView.setAdapter(showTimeAdapter);
        LinearLayoutManager layoutManagerShowTime
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        showTimeRecyclerView.setLayoutManager(layoutManagerShowTime);


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
                            filmMap=new HashMap<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d("GET_FILM", document.getId() + " => " + document.getData());
                                Film film = document.toObject(Film.class);
                                film.setId(document.getId());
                                filmMap.put(document.getId(), film);
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


        // Get timeSlot from db
        db.collection("timeSlots")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            timeSlotMap = new HashMap<String, TimeSlot>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d("GET_FILM", document.getId() + " => " + document.getData());
                                TimeSlot timeSlot = document.toObject(TimeSlot.class);
                                timeSlotMap.put(document.getId(), timeSlot);
                            }

                        } else {
                            Log.d("GET_FILM", "Error getting documents: ", task.getException());
                        }
                    }
                });

        // Get CinemaRoom from db
        db.collection("theaters")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            roomMap = new HashMap<String, CinemaRoom>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d("GET_FILM", document.getId() + " => " + document.getData());
                                CinemaRoom room = document.toObject(CinemaRoom.class);
                                roomMap.put(document.getId(), room);
                            }

                        } else {
                            Log.d("GET_FILM", "Error getting documents: ", task.getException());
                        }
                    }
                });

        // Get Show time from db
        db.collection("showtimes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            showTimes = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d("GET_FILM", document.getId() + " => " + document.getData());
                                ShowTime_ showTime = document.toObject(ShowTime_.class);
                                showTime.id = document.getId();
                                showTimes.add(showTime);
                            }

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
        showTimeUIS.clear();
        for (ShowTime_ s: showTimes) {
            showTimeUIS.add(new ShowTimeUI(filmMap.get(s.filmId).getName(), s.day, timeSlotMap.get(s.timeSlotId).getStart(), roomMap.get(s.threaterId).getName(), s.price));
        }
        showTimeAdapter.notifyDataSetChanged();
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