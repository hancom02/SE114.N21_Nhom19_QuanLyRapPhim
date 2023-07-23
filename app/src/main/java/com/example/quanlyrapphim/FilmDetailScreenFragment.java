package com.example.quanlyrapphim;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyrapphim.models.Film;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

public class FilmDetailScreenFragment extends Fragment {

    private String argFilmId;
    private ImageView imvImage;
    private TextView tvName;
    private TextView tvType;
    private TextView tvCountry;
    private TextView tvReleaseDate;
    private TextView tvCast;
    private TextView tvContent;
    private MaterialButton btnEdit;
    private MaterialButton btnDelete;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            argFilmId = getArguments().getString("filmId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film_detail_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // GET VIEW
        imvImage = view.findViewById(R.id.film_detail_screen_image);
        tvName = view.findViewById(R.id.film_detail_screen_name);
        tvType = view.findViewById(R.id.film_detail_screen_type);
        tvReleaseDate = view.findViewById(R.id.film_detail_screen_release_date);
        tvCast = view.findViewById(R.id.film_detail_screen_cast);
        tvCountry = view.findViewById(R.id.film_detail_screen_country);
        tvContent = view.findViewById(R.id.film_detail_screen_content);
        btnEdit = view.findViewById(R.id.film_detail_screen_btn_edit);
        btnDelete = view.findViewById(R.id.film_detail_screen_btn_delete);

        // Get from db
        DocumentReference docRef = db.collection("films").document(argFilmId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("FILM_DETAIL", "DocumentSnapshot data: " + document.getData());
                        Film film = document.toObject(Film.class);
                        tvName.setText(film.getName());
                        tvCast.setText(film.getCast());
                        tvContent.setText(film.getContent());
                        tvCountry.setText(film.getCountry());
                        tvType.setText(film.getType());
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        tvReleaseDate.setText(dateFormat.format(film.getReleaseDate()));
                        Picasso.get().load(film.getImage()).into(imvImage);


                    } else {
                        Log.d("FILM_DETAIL", "No such document");
                    }
                } else {
                    Log.d("FILM_DETAIL", "get failed with ", task.getException());
                }
            }
        });


        // Set btn action
        btnEdit.setOnClickListener(v -> {
            if (argFilmId != null) {
                Bundle bundle = new Bundle();
                bundle.putString("filmId", argFilmId);
                Navigation.findNavController(view).navigate(R.id.editFilmScreenFragment, bundle);
            }
        });
    }
}