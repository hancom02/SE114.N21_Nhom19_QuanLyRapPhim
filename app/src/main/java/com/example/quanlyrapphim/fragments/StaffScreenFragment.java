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
import com.example.quanlyrapphim.adapters.StaffRecyclerViewAdapter;
import com.example.quanlyrapphim.models.Employee;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StaffScreenFragment extends Fragment {

    private ArrayList<Employee> employees = new ArrayList<>();
    private RecyclerView employeeRecyclerView;
    private FloatingActionButton btnAddEmployee;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEmployees();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_staff_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        employeeRecyclerView = view.findViewById(R.id.employee_recycle_view);
        btnAddEmployee = view.findViewById(R.id.staff_screen_btn_add_staff);

        StaffRecyclerViewAdapter adapter = new StaffRecyclerViewAdapter(getActivity(), employees);
        adapter.setOnDeleteClickListener(i -> {
            Toast.makeText(getActivity(), "Deleted item at " + i, Toast.LENGTH_SHORT).show();
        });
        adapter.setOnEditClickListener(i -> {
            Toast.makeText(getActivity(), "Edit item at " + i, Toast.LENGTH_SHORT).show();
        });
        employeeRecyclerView.setAdapter(adapter);
        employeeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnAddEmployee.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.addStaffScreenFragment);
        });
    }

    private void initEmployees() {
        employees.add(new Employee("Midnight Whispers", "minhchau@gmail.com","https://images.unsplash.com/photo-1590179068383-b9c69aacebd3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZmlsbSUyMHBvc3RlcnxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        employees.add(new Employee("Shattered Dreams of Yesterday", "chauminh@gmail.com", "https://images.unsplash.com/photo-1578655858279-e17d055eafd0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTF8fGZpbG0lMjBwaG90b2dyYXBoeXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        employees.add(new Employee("I don't want to code in java!", "asfasdf@fasd.cd", "https://images.unsplash.com/photo-1543487945-139a97f387d5?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8cG9zdGVyfGVufDB8fDB8fHww&auto=format&fit=crop&w=500&q=60"));
        employees.add(new Employee("The Enigma's Hidden Legacy", "asfasdf@fasd.cd","https://images.unsplash.com/photo-1583407723467-9b2d22504831?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjB8fHBvc3RlcnxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        employees.add(new Employee("Serendipity's Dance", "asfasdf@fasd.cd", "https://plus.unsplash.com/premium_photo-1668051042204-038187cac123?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8YW5pbWV8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=500&q=60"));
        employees.add(new Employee("Midnight Whispers", "minhchau@gmail.com","https://images.unsplash.com/photo-1590179068383-b9c69aacebd3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZmlsbSUyMHBvc3RlcnxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        employees.add(new Employee("Shattered Dreams of Yesterday", "chauminh@gmail.com", "https://images.unsplash.com/photo-1578655858279-e17d055eafd0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTF8fGZpbG0lMjBwaG90b2dyYXBoeXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        employees.add(new Employee("I don't want to code in java!", "asfasdf@fasd.cd", "https://images.unsplash.com/photo-1543487945-139a97f387d5?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8cG9zdGVyfGVufDB8fDB8fHww&auto=format&fit=crop&w=500&q=60"));
        employees.add(new Employee("The Enigma's Hidden Legacy", "asfasdf@fasd.cd","https://images.unsplash.com/photo-1583407723467-9b2d22504831?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjB8fHBvc3RlcnxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        employees.add(new Employee("Serendipity's Dance", "asfasdf@fasd.cd", "https://plus.unsplash.com/premium_photo-1668051042204-038187cac123?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8YW5pbWV8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=500&q=60"));
        employees.add(new Employee("Midnight Whispers", "minhchau@gmail.com","https://images.unsplash.com/photo-1590179068383-b9c69aacebd3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZmlsbSUyMHBvc3RlcnxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        employees.add(new Employee("Shattered Dreams of Yesterday", "chauminh@gmail.com", "https://images.unsplash.com/photo-1578655858279-e17d055eafd0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTF8fGZpbG0lMjBwaG90b2dyYXBoeXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        employees.add(new Employee("I don't want to code in java!", "asfasdf@fasd.cd", "https://images.unsplash.com/photo-1543487945-139a97f387d5?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8cG9zdGVyfGVufDB8fDB8fHww&auto=format&fit=crop&w=500&q=60"));
        employees.add(new Employee("The Enigma's Hidden Legacy", "asfasdf@fasd.cd","https://images.unsplash.com/photo-1583407723467-9b2d22504831?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjB8fHBvc3RlcnxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
        employees.add(new Employee("Serendipity's Dance", "asfasdf@fasd.cd", "https://plus.unsplash.com/premium_photo-1668051042204-038187cac123?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8YW5pbWV8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=500&q=60"));
    }
}