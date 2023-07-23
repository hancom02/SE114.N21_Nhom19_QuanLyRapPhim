package com.example.quanlyrapphim.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.models.TextDropdownField;
import com.example.quanlyrapphim.utils.RecyclerViewItemEventListener;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TextDropdownRecyclerViewAdapter extends RecyclerView.Adapter<TextDropdownRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<TextDropdownField> textDropdownFields;

    public TextDropdownRecyclerViewAdapter(Context context, ArrayList<TextDropdownField> textDropdownFields) {
        this.context = context;
        this.textDropdownFields = textDropdownFields;
    }

    @NonNull
    @Override
    public TextDropdownRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.field_text_dropdown_item, viewGroup, false);

        return new TextDropdownRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.fieldNameText.setText(textDropdownFields.get(i).getNameText());
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, new String[] {"test 1", "test 2"} );
        holder.fieldContentDropdown.setAdapter(spinnerAdapter);

    }

    @Override
    public int getItemCount() {
        return textDropdownFields.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fieldNameText;
//        TextView fieldContentDropdown;
        Spinner fieldContentDropdown;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fieldNameText = itemView.findViewById(R.id.fieldNameText);
            fieldContentDropdown = itemView.findViewById(R.id.fieldContentDropdown);

        }
    }


}