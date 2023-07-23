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
import com.example.quanlyrapphim.models.TextInputField;
import com.example.quanlyrapphim.utils.RecyclerViewItemEventListener;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TextInputRecyclerViewAdapter extends RecyclerView.Adapter<TextInputRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<TextInputField> textInputFields;

    public TextInputRecyclerViewAdapter(Context context, ArrayList<TextInputField> textInputFields) {
        this.context = context;
        this.textInputFields = textInputFields;
    }

    @NonNull
    @Override
    public TextInputRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.filed_text_input_item, viewGroup, false);

        return new TextInputRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.fieldName.setText(textInputFields.get(i).getName());
        holder.fieldContent.setText(textInputFields.get(i).getContent());

    }

    @Override
    public int getItemCount() {
        return textInputFields.size();
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