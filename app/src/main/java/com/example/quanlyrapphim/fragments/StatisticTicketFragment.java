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
import com.example.quanlyrapphim.adapters.TextDropdownRecyclerViewAdapter;
import com.example.quanlyrapphim.adapters.DropdownTextRecyclerViewAdapter;
import com.example.quanlyrapphim.adapters.TextInputRecyclerViewAdapter;
import com.example.quanlyrapphim.models.Field;
import com.example.quanlyrapphim.models.Film;
import com.example.quanlyrapphim.models.TextDropdownField;
import com.example.quanlyrapphim.models.TextInputField;
import com.example.quanlyrapphim.models.DropdownTextField;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatisticTicketFragment extends Fragment {

    private List<Integer> recyclerViewIds = Arrays.asList(
            R.id.booking_ticket_text_input_recycler_view,
            R.id.booking_ticket_text_dropdown_recycler_view,
            R.id.booking_ticket_dropdown_text_recycler_view
    );

    private int currentRecyclerViewIndex = 0;


    private void setupRecyclerViewScrollListener(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (!recyclerView.canScrollVertically(1)) {
                    // RecyclerView cuộn hết, hiển thị RecyclerView tiếp theo
                    showNextRecyclerView();
                }
            }
        });
    }

    private void showNextRecyclerView() {
//        if (currentRecyclerViewIndex < recyclerViewIds.size() - 1) {
//            RecyclerView currentRecyclerView = view.findViewById(recyclerViewIds.get(currentRecyclerViewIndex));
//            currentRecyclerView.setVisibility(View.GONE);
//
//            currentRecyclerViewIndex++;
//
//            RecyclerView nextRecyclerView = view.findViewById(recyclerViewIds.get(currentRecyclerViewIndex));
//            nextRecyclerView.setVisibility(View.VISIBLE);
//
//            setupRecyclerViewScrollListener(nextRecyclerView);
//        }
    }









    private ArrayList<TextInputField> textInputFields = new ArrayList<>();
    private ArrayList<TextDropdownField> textDropdownFields = new ArrayList<>();
    private ArrayList<DropdownTextField> dropdownTextFields = new ArrayList<>();


    private RecyclerView textInputRecyclerView;
    private RecyclerView textDropdownRecyclerView;
    private RecyclerView dropdownTextRecyclerView;



    private TextInputRecyclerViewAdapter textInputAdapter;
    private TextDropdownRecyclerViewAdapter textDropdownAdapter;
    private TextDropdownRecyclerViewAdapter dropdownTextAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFields();


        for (int i = 0; i < recyclerViewIds.size(); i++) {
            if (i != currentRecyclerViewIndex) {
//                RecyclerView hiddenRecyclerView = view.findViewById(recyclerViewIds.get(i));
//                hiddenRecyclerView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_booking_ticket, container, false);

        View view = inflater.inflate(R.layout.fragment_statistic_movie, container, false);
        textInputRecyclerView = view.findViewById(R.id.booking_ticket_text_input_recycler_view);
        textDropdownRecyclerView = view.findViewById(R.id.booking_ticket_text_dropdown_recycler_view);
        dropdownTextRecyclerView = view.findViewById(R.id.booking_ticket_dropdown_text_recycler_view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initFields();
//        initFieldsTextDropdown();
        super.onViewCreated(view, savedInstanceState);
        textInputRecyclerView = view.findViewById(R.id.booking_ticket_text_input_recycler_view);
        textDropdownRecyclerView = view.findViewById(R.id.booking_ticket_text_dropdown_recycler_view);
        dropdownTextRecyclerView = view.findViewById(R.id.booking_ticket_dropdown_text_recycler_view);



        TextInputRecyclerViewAdapter adapter = new TextInputRecyclerViewAdapter(getActivity(), textInputFields);
        TextDropdownRecyclerViewAdapter tDadapter = new TextDropdownRecyclerViewAdapter(getActivity(), textDropdownFields);
        DropdownTextRecyclerViewAdapter dTadapter = new DropdownTextRecyclerViewAdapter(getActivity(), dropdownTextFields);

        textInputRecyclerView.setAdapter(adapter);
        textDropdownRecyclerView.setAdapter(tDadapter);
        dropdownTextRecyclerView.setAdapter(dTadapter);

        textInputRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        textDropdownRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dropdownTextRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initFields() {


//        textDropdownFields.add(new TextDropdownField("Suat chieu", "14h00 - 15h45"));
//        textDropdownFields.add(new TextDropdownField("Ghe", "H1, H2"));

        textInputFields.add(new TextInputField("Ma ve", "Bich Huyen"));
//        textInputFields.add(new TextInputField("Phim", "Doraemon"));

        dropdownTextFields.add(new DropdownTextField("Email", "a.@gmail.com"));
        dropdownTextFields.add(new DropdownTextField("Email", "a.@gmail.com"));

//        adapter.notifyDataSetChanged();
    }
    private void initFieldsTextDropdown() {
//        textDropdownFields.add(new TextDropdownField("Email", "a.@gmail.com"));


//        adapter.notifyDataSetChanged();
    }
}