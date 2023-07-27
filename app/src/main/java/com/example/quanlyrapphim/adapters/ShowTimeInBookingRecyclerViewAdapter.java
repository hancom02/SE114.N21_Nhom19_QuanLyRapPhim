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
import com.example.quanlyrapphim.models.Film;
import com.example.quanlyrapphim.models.ShowTimeUI;
import com.example.quanlyrapphim.utils.RecyclerViewItemEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ShowTimeInBookingRecyclerViewAdapter extends RecyclerView.Adapter<ShowTimeInBookingRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<ShowTimeUI> showtimes;
    private RecyclerViewItemEventListener clickListener;

    public ShowTimeInBookingRecyclerViewAdapter(Context context, ArrayList<ShowTimeUI> showtimes) {
        this.context = context;
        this.showtimes = showtimes;
    }

    @NonNull
    @Override
    public ShowTimeInBookingRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.show_time_card_in_booking, viewGroup, false);

        return new ShowTimeInBookingRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.film.setText(showtimes.get(i).filmName);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.date.setText(dateFormat.format(showtimes.get(i).date));
        holder.time.setText(showtimes.get(i).time);
        holder.theater.setText(showtimes.get(i).theaterName);
        holder.price.setText(showtimes.get(i).price + "");
        holder.card.setOnClickListener(view -> {
            clickListener.onItemEvent(i);
        });
    }

    @Override
    public int getItemCount() {
        return showtimes.size();
    }

    public void setOnClickListener(RecyclerViewItemEventListener listener) {
        clickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView film;
        TextView date;
        TextView time;
        TextView theater;
        TextView price;
        View card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            film = itemView.findViewById(R.id.show_time_card_in_booking_film);
            date = itemView.findViewById(R.id.show_time_card_in_booking_date);
            time = itemView.findViewById(R.id.show_time_card_in_booking_time);
            theater = itemView.findViewById(R.id.show_time_card_in_booking_theater);
            price = itemView.findViewById(R.id.show_time_card_in_booking_price);
            card = itemView;
        }
    }


}