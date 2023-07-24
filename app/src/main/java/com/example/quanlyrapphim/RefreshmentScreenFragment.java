package com.example.quanlyrapphim;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quanlyrapphim.adapters.RefreshmentsRecyclerViewAdapter;
import com.example.quanlyrapphim.models.Refreshment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RefreshmentScreenFragment extends Fragment {

    private ArrayList<Refreshment> refreshmentArrayList = new ArrayList<>();
    private RecyclerView refreshmentRecyclerView;
    private FloatingActionButton addRefreshmentBtn;

//    public RefreshmentScreenFragment() {
//        // Required empty public constructor
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRefreshment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_refreshment_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        refreshmentRecyclerView = view.findViewById(R.id.frmt_refreshment_rv_listRefreshment);
        addRefreshmentBtn = view.findViewById(R.id.frmt_refreshment_bt_addRefreshment);

        RefreshmentsRecyclerViewAdapter adapter = new RefreshmentsRecyclerViewAdapter(getActivity(), refreshmentArrayList);

        adapter.setOnDeleteClickListener(i -> {
            Toast.makeText(getActivity(), "Deleted item at " + i, Toast.LENGTH_SHORT).show();
        });
        adapter.setOnEditClickListener(i -> {
//            Toast.makeText(getActivity(), "Edit item at " + i, Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigate(R.id.action_refreshmentScreenFragment_to_editRefreshmentScreenFragment);
        });
        refreshmentRecyclerView.setAdapter(adapter);
        refreshmentRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        addRefreshmentBtn.setOnClickListener(v -> {
//            Toast.makeText(getActivity(), "Nav to add refreshment screen", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigate(R.id.action_refreshmentScreenFragment_to_addRefreshmentScreenFragment);
        });
    }

    private void initRefreshment() {
        refreshmentArrayList.add(new Refreshment("small_popcorn", "Bắp nhỏ", 35000));
        refreshmentArrayList.add(new Refreshment("small_popcorn2", "Bắp nhỏ", 35000));
        refreshmentArrayList.add(new Refreshment("small_popcorn3", "Bắp nhỏ", 35000));
        refreshmentArrayList.add(new Refreshment("small_popcorn4", "Bắp nhỏ", 35000));
        refreshmentArrayList.add(new Refreshment("small_popcorn5", "Bắp nhỏ", 35000));
        refreshmentArrayList.add(new Refreshment("small_popcorn6", "Bắp nhỏ", 35000));
        refreshmentArrayList.add(new Refreshment("small_popcorn6", "Bắp nhỏ", 35000));
        refreshmentArrayList.add(new Refreshment("small_popcorn6", "Bắp nhỏ", 35000));
        refreshmentArrayList.add(new Refreshment("small_popcorn6", "Bắp nhỏ", 35000));
        refreshmentArrayList.add(new Refreshment("small_popcorn6", "Bắp nhỏ", 35000));
        refreshmentArrayList.add(new Refreshment("small_popcorn6", "Bắp nhỏ", 35000));

    }
}