package com.example.quanlyrapphim.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.models.CinemaRoom;
import com.example.quanlyrapphim.utils.RecyclerViewItemEventListener;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class CinemaRoomRecyclerViewAdapter extends RecyclerView.Adapter<CinemaRoomRecyclerViewAdapter.ViewHolder> {
    private Activity context;
    ArrayList<CinemaRoom> cinemaRoomList;
    private RecyclerViewItemEventListener detailClickListener;
    private RecyclerViewItemEventListener editClickListener;
    private RecyclerViewItemEventListener deleteClickListener;

//    CinemaRoomSpinerAdapter cinemaRoomSpinerAdapter;

    public CinemaRoomRecyclerViewAdapter(Activity context, ArrayList<CinemaRoom> cinemaRooms) {
        this.context = context;
        this.cinemaRoomList = cinemaRooms;

    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        MaterialButton btDetail;
        MaterialButton btEdit;
        MaterialButton btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.item_cinemaroom_tv_name_room);
            btDetail = itemView.findViewById(R.id.item_cinemaroom_bt_chiTiet);
            btEdit = itemView.findViewById(R.id.item_cinemaroom_bt_edit);
            btDelete = itemView.findViewById(R.id.item_cinemaroom_bt_delete);

        }
    }
    @NonNull
    @Override
    public CinemaRoomRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View newViewHolder = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.cinemaroom_card, parent, false);
        return new CinemaRoomRecyclerViewAdapter.ViewHolder(newViewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaRoomRecyclerViewAdapter.ViewHolder holder, int position) {
//        CinemaRoom newCinemaRoom = this.cinemaRoomList.get(position);
//        cinemaRoomSpinerAdapter = new CinemaRoomSpinerAdapter()
        holder.tvName.setText(cinemaRoomList.get(position).getName());
        holder.btDetail.setOnClickListener(view -> {
            detailClickListener.onItemEvent(position);
        });
        holder.btEdit.setOnClickListener(view -> {
            editClickListener.onItemEvent(position);
        });
        holder.btDelete.setOnClickListener(view -> {
            deleteClickListener.onItemEvent(position);
        });
    }

    @Override
    public int getItemCount() {
        return cinemaRoomList.size();
    }
    public void setOnDetailClickListener(RecyclerViewItemEventListener listener) {
        detailClickListener = listener;
    }
    public void setOnEditClickListener(RecyclerViewItemEventListener listener) {
        editClickListener = listener;
    }
    public void setOnDeleteClickListener(RecyclerViewItemEventListener listener) {
        deleteClickListener = listener;
    }

}
