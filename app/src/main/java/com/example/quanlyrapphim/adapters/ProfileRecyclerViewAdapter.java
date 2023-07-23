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
import com.example.quanlyrapphim.models.Field;
import com.example.quanlyrapphim.utils.RecyclerViewItemEventListener;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfileRecyclerViewAdapter extends RecyclerView.Adapter<ProfileRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Field> fields;

    public ProfileRecyclerViewAdapter(Context context, ArrayList<Field> fields) {
        this.context = context;
        this.fields = fields;
    }

    @NonNull
    @Override
    public ProfileRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.field_infor_profile_item, viewGroup, false);

        return new ProfileRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.fieldName.setText(fields.get(i).getName());
        holder.fieldContent.setText(fields.get(i).getContent());

    }

    @Override
    public int getItemCount() {
        return fields.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fieldName;
        TextView fieldContent;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fieldName = itemView.findViewById(R.id.fieldName);
            fieldContent = itemView.findViewById(R.id.fieldContent);

        }
    }


}