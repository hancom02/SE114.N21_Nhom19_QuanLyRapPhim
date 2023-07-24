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
import com.example.quanlyrapphim.models.DropdownTextField;
import com.example.quanlyrapphim.utils.RecyclerViewItemEventListener;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DropdownTextRecyclerViewAdapter extends RecyclerView.Adapter<DropdownTextRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<DropdownTextField> dropdownTextFields;

    public DropdownTextRecyclerViewAdapter(Context context, ArrayList<DropdownTextField> dropdownTextFields) {
        this.context = context;
        this.dropdownTextFields = dropdownTextFields;
    }

    @NonNull
    @Override
    public DropdownTextRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.field_dropdown_text_item, viewGroup, false);

        return new DropdownTextRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, new String[] {"test 1", "test 2"} );
        holder.fieldNameDropdown.setAdapter(spinnerAdapter);
        holder.fieldContentText.setText(dropdownTextFields.get(i).getContentText());

    }

    @Override
    public int getItemCount() {
        return dropdownTextFields.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fieldContentText;
        Spinner fieldNameDropdown;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fieldContentText = itemView.findViewById(R.id.fieldContentText);
            fieldNameDropdown = itemView.findViewById(R.id.fieldNameDropdown);

        }
    }


}