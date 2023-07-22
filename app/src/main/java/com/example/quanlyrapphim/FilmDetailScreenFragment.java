package com.example.quanlyrapphim;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class FilmDetailScreenFragment extends Fragment {

    private String argFilmName;
    private ImageView imvImage;
    private TextView tvName;
    private TextView tvType;
    private TextView tvCountry;
    private TextView tvReleaseDate;
    private TextView tvCast;
    private TextView tvContent;
    private MaterialButton btnEdit;
    private MaterialButton btnDelete;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            argFilmName = getArguments().getString("filmName");
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

        tvName.setText(argFilmName);
    }
}