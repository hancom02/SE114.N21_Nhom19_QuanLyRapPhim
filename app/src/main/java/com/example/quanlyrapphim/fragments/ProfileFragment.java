package com.example.quanlyrapphim.fragments;

import android.os.Bundle;
import android.util.Log;

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
import com.example.quanlyrapphim.adapters.ProfileRecyclerViewAdapter;
import com.example.quanlyrapphim.models.Field;
import com.example.quanlyrapphim.models.Film;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    private ArrayList<Field> fields = new ArrayList<>();
    private RecyclerView fieldRecyclerView;

    private ProfileRecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFields();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initFields();
        super.onViewCreated(view, savedInstanceState);
        fieldRecyclerView = view.findViewById(R.id.profile_recycler_view);

        ProfileRecyclerViewAdapter adapter = new ProfileRecyclerViewAdapter(getActivity(), fields);

        fieldRecyclerView.setAdapter(adapter);
        fieldRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }

    private void initFields() {
        fields.add(new Field("Ho Ten", "Bui Thi Hoang Giang"));
        fields.add(new Field("Email", "a.@gmail.com"));
        fields.add(new Field("Ma nhan vien", "21520794"));
        fields.add(new Field("Dia chi", "khong noi"));
        fields.add(new Field("Gioi tinh", "Nu"));
        fields.add(new Field("Chuc vu", "Nhan vien"));
//        adapter.notifyDataSetChanged();
    }
}