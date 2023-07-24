package com.example.quanlyrapphim.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.models.Employee;
import com.example.quanlyrapphim.utils.RecyclerViewItemEventListener;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StaffRecyclerViewAdapter extends RecyclerView.Adapter<StaffRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Employee> employees;
    private RecyclerViewItemEventListener deleteClickListener;
    private RecyclerViewItemEventListener editClickListener;

    public StaffRecyclerViewAdapter(Context context, ArrayList<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public StaffRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.staff_card, viewGroup, false);

        return new StaffRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.tvName.setText(employees.get(i).getName());
        Picasso.get().load(employees.get(i).getAvatar()).into(holder.imgAvatar);
        holder.btnDelete.setOnClickListener(view -> {
            deleteClickListener.onItemEvent(i);
        });
        holder.btnEdit.setOnClickListener(view -> {
            editClickListener.onItemEvent(i);
        });
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public void setOnDeleteClickListener(RecyclerViewItemEventListener listener) {
        deleteClickListener = listener;
    }

    public void setOnEditClickListener(RecyclerViewItemEventListener listener) {
        editClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvEmail;
        ImageView imgAvatar;
        MaterialButton btnDelete;
        MaterialButton btnEdit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.employee_card_name);
            tvEmail = itemView.findViewById(R.id.employee_card_email);
            imgAvatar = itemView.findViewById(R.id.employee_card_avatar);
            btnDelete = itemView.findViewById(R.id.employee_card_btn_delete);
            btnEdit = itemView.findViewById(R.id.employee_card_btn_edit);
        }
    }


}