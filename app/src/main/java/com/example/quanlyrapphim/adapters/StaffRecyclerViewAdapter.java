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
import com.example.quanlyrapphim.models.Account;
import com.example.quanlyrapphim.utils.RecyclerViewItemEventListener;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StaffRecyclerViewAdapter extends RecyclerView.Adapter<StaffRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Account> staffs;
    private RecyclerViewItemEventListener deleteClickListener;
    private RecyclerViewItemEventListener editClickListener;
    private RecyclerViewItemEventListener clickListener;

    public StaffRecyclerViewAdapter(Context context, ArrayList<Account> staffs) {
        this.context = context;
        this.staffs = staffs;
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
        holder.tvName.setText(staffs.get(i).getName());
        holder.tvEmail.setText(staffs.get(i).getEmail());
        Picasso.get().load(staffs.get(i).getAvatar()).into(holder.imgAvatar);
        holder.btnDelete.setOnClickListener(view -> {
            deleteClickListener.onItemEvent(i);
        });
        holder.btnEdit.setOnClickListener(view -> {
            editClickListener.onItemEvent(i);
        });
        holder.card.setOnClickListener(view -> {
            clickListener.onItemEvent(i);
        });
    }

    @Override
    public int getItemCount() {
        return staffs.size();
    }

    public void setOnDeleteClickListener(RecyclerViewItemEventListener listener) {
        deleteClickListener = listener;
    }

    public void setOnEditClickListener(RecyclerViewItemEventListener listener) {
        editClickListener = listener;
    }
    public void setOnClickListener(RecyclerViewItemEventListener listener) {
        clickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvEmail;
        ImageView imgAvatar;
        MaterialButton btnDelete;
        MaterialButton btnEdit;
        View card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.staff_card_name);
            tvEmail = itemView.findViewById(R.id.staff_card_email);
            imgAvatar = itemView.findViewById(R.id.staff_card_avatar);
            btnDelete = itemView.findViewById(R.id.staff_card_btn_delete);
            btnEdit = itemView.findViewById(R.id.staff_card_btn_edit);
            card = itemView;
        }
    }


}