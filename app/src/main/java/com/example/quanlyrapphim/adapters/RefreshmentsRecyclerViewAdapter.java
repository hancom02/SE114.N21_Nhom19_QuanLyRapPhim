package com.example.quanlyrapphim.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.models.Refreshment;
import com.example.quanlyrapphim.utils.RecyclerViewItemEventListener;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class RefreshmentsRecyclerViewAdapter extends RecyclerView.Adapter<RefreshmentsRecyclerViewAdapter.MyViewHolder> {

    private Activity context;
    ArrayList<Refreshment> refreshmentArrayList;
    private RecyclerViewItemEventListener detailClickListener;
    private RecyclerViewItemEventListener editClickListener;
    private RecyclerViewItemEventListener deleteClickListener;

    public RefreshmentsRecyclerViewAdapter(Activity context, ArrayList<Refreshment> refreshmentArrayList1) {
        this.context = context;
        this.refreshmentArrayList = refreshmentArrayList1;
    }

    @NonNull
    @Override
    public RefreshmentsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newViewHolder = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.refreshment_card, parent, false);
        return new RefreshmentsRecyclerViewAdapter.MyViewHolder(newViewHolder);
    }
    @Override
    public void onBindViewHolder(@NonNull RefreshmentsRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(refreshmentArrayList.get(position).getName());

        holder.btnEdit.setOnClickListener(view -> {
            editClickListener.onItemEvent(position);
        });
        holder.btnDelete.setOnClickListener(view -> {
            deleteClickListener.onItemEvent(position);
        });
    }

    @Override
    public int getItemCount() {
        return refreshmentArrayList.size();
    }

    public void setOnEditClickListener(RecyclerViewItemEventListener listener) {
        editClickListener = listener;
    }
    public void setOnDeleteClickListener(RecyclerViewItemEventListener listener) {
        deleteClickListener = listener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvName;
        TextView tvPrice;
        MaterialButton btnEdit;
        MaterialButton btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.refreshment_card_img);
            tvName = itemView.findViewById(R.id.refreshment_card_tv_name);
            tvPrice = itemView.findViewById(R.id.refreshment_card_tv_price);
            btnEdit = itemView.findViewById(R.id.refreshment_card_btn_edit);
            btnDelete = itemView.findViewById(R.id.refreshment_card_btn_delete);
        }
    }
}
