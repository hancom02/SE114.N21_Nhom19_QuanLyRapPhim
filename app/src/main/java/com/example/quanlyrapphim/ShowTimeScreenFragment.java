package com.example.quanlyrapphim;

import android.app.Activity;
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

import com.example.quanlyrapphim.adapters.ShowTimeRecyclerViewAdapter;
import com.example.quanlyrapphim.adapters.TimeSlotRecyclerViewAdapter;
import com.example.quanlyrapphim.models.CinemaRoom;
import com.example.quanlyrapphim.models.Film;
import com.example.quanlyrapphim.models.ShowTime;
import com.example.quanlyrapphim.models.TimeSlot;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ShowTimeScreenFragment extends Fragment {
    private Activity context;
    private ArrayList<ShowTime> showTimeArrayList = new ArrayList<>();
    private RecyclerView showTimeRecyclerView;
    private FloatingActionButton addShowTimeBtn;

//    public ShowTimeScreenFragment(Activity context, ArrayList<ShowTime> showTimeArrayList) {
//        this.context = context;
//        this.showTimeArrayList = showTimeArrayList;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initShowTime();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_time_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showTimeRecyclerView = view.findViewById(R.id.frmt_show_time_rv_list);
        addShowTimeBtn = view.findViewById(R.id.frmt_show_time_btn_add);

        ShowTimeRecyclerViewAdapter adapter = new ShowTimeRecyclerViewAdapter(getActivity(), showTimeArrayList);

        adapter.setOnDeleteClickListener(i -> {
            Toast.makeText(getActivity(), "Deleted item at " + i, Toast.LENGTH_SHORT).show();
        });
        adapter.setOnEditClickListener(i -> {
            Toast.makeText(getActivity(), "Edit item at " + i, Toast.LENGTH_SHORT).show();
//            Navigation.findNavController(view).navigate(R.id.action_refreshmentScreenFragment_to_editRefreshmentScreenFragment);
        });
        showTimeRecyclerView.setAdapter(adapter);
        showTimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addShowTimeBtn.setOnClickListener(v -> {
//            Toast.makeText(getActivity(), "Nav to add refreshment screen", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigate(R.id.action_showTimeScreenFragment_to_addShowTimeFragmentScreen);
        });
    }

    private void initShowTime() {
        showTimeArrayList.add(new ShowTime(new Film("Như Ý Truyện", "https://images.unsplash.com/photo-1590179068383-b9c69aacebd3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZmlsbSUyMHBvc3RlcnxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"), new CinemaRoom("Rạp 5", "Normal", 10, 12), new TimeSlot("15:30", "18:00")));
        showTimeArrayList.add(new ShowTime(new Film("Qủy quyệt", "https://images.unsplash.com/photo-1590179068383-b9c69aacebd3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZmlsbSUyMHBvc3RlcnxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"), new CinemaRoom("Rạp 5", "Normal", 10, 12), new TimeSlot("15:30", "18:00")));
        showTimeArrayList.add(new ShowTime(new Film("Cung Tỏa Trầm Hương", "https://images.unsplash.com/photo-1590179068383-b9c69aacebd3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZmlsbSUyMHBvc3RlcnxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"), new CinemaRoom("Rạp 5", "Normal", 10, 12), new TimeSlot("15:30", "18:00")));
        showTimeArrayList.add(new ShowTime(new Film("Conan 6", "https://images.unsplash.com/photo-1590179068383-b9c69aacebd3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZmlsbSUyMHBvc3RlcnxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"), new CinemaRoom("Rạp 5", "Normal", 10, 12), new TimeSlot("15:30", "18:00")));
        showTimeArrayList.add(new ShowTime(new Film("Thanh gươm diệt quỷ", "https://images.unsplash.com/photo-1590179068383-b9c69aacebd3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZmlsbSUyMHBvc3RlcnxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"), new CinemaRoom("Rạp 5", "Normal", 10, 12), new TimeSlot("15:30", "18:00")));

    }

}