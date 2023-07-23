package com.example.quanlyrapphim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.models.Field;
import com.example.quanlyrapphim.models.TextInputField;
import com.example.quanlyrapphim.models.DropdownTextField;
import com.example.quanlyrapphim.models.TextDropdownField;

import java.util.ArrayList;

public class InforFieldRecyclerViewAdapter extends RecyclerView.Adapter<InforFieldRecyclerViewAdapter.MyViewHolder> {

    private final int text_input = 0;
    private final int text_dropdown = 1;
    private final int dropdown_text = 2;

    private Context context;
    private ArrayList<Field> fields;



    public InforFieldRecyclerViewAdapter(Context context, ArrayList<Field> fields) {
        this.context = context;
        this.fields = fields;
    }

    public int getItemViewType(int position) {
        if (position == 0)
            return text_input;
        else if (position == 1) {
            return text_dropdown;

        }
        return dropdown_text;
    }

    @NonNull
    @Override
    public InforFieldRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup
                                                                                 viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        switch (viewType) {
            case text_input:
                view = inflater.inflate(R.layout.filed_text_input_item, viewGroup, false);
//                return new TextInputViewHolder(view, viewType);
            case text_dropdown:
                view = inflater.inflate(R.layout.field_text_dropdown_item, viewGroup, false);
//                return new TextDropdownViewHolder(view, viewType);
            case dropdown_text:
                view = inflater.inflate(R.layout.field_dropdown_text_item, viewGroup, false);
//                return new DropdownTextViewHolder(view, viewType);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
//        return new InforFieldRecyclerViewAdapter.MyViewHolder(view, viewType);

    }


    @Override
    public void onBindViewHolder(@NonNull InforFieldRecyclerViewAdapter.MyViewHolder holder,
                                 int position) {
        int viewType = getItemViewType(position);
        switch (holder.Holderid) {
            case 0:
//                TextInputField textInputField = (TextInputField) fields.get(position);
//                TextInputViewHolder textInputHolder = (TextInputViewHolder) holder;
//                textInputHolder.fieldName.setText(textInputField.getName());
//                textInputHolder.fieldContent.setText(textInputField.getContent());
//                break;
//            case 1:
//                TextDropdownField textDropdownField = (TextDropdownField) fields.get(position);
//                TextDropdownViewHolder textDropdownHolder = (TextDropdownViewHolder) holder;
//                textDropdownHolder.fieldNameText.setText(textDropdownField.getNameText());
//                textDropdownHolder.fieldContentDropdown.setText(textDropdownField.getContentDropdown());
//                break;
//            case 2:
//                DropdownTextField dropdownTextField = (DropdownTextField) fields.get(position);
//                DropdownTextViewHolder dropdownTextHolder = (DropdownTextViewHolder) holder;
//                dropdownTextHolder.fieldNameDropdown.setText(dropdownTextField.getNameDropdown());
//                dropdownTextHolder.fieldContentText.setText(dropdownTextField.getContentText());
//                break;
            default:
                throw new IllegalArgumentException("Invalid view type");
        }

    }

    @Override
    public int getItemCount() {
        return fields.size() + 2;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        int Holderid;
        TextView fieldName;
        EditText fieldContent;

        TextView fieldNameText;
        Spinner fieldContentDropdown;

        Spinner fieldNameDropdown;
        TextView fieldContentText;
        public MyViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            switch (viewType){
                case text_input:
                    Holderid = 0;
                    fieldName = itemView.findViewById(R.id.fieldName);
                    fieldContent = itemView.findViewById(R.id.fieldContent);
                case text_dropdown:
                    Holderid = 1;
                    fieldNameText = itemView.findViewById(R.id.fieldNameText);
                    fieldContentDropdown = itemView.findViewById(R.id.fieldContentDropdown);
                case dropdown_text:
                    Holderid = 2;
                    fieldNameDropdown = itemView.findViewById(R.id.fieldNameDropdown);
                    fieldContentText = itemView.findViewById(R.id.fieldContentText);

            }
        }
    }

//    class TextInputViewHolder extends RecyclerView.ViewHolder {
//        TextView fieldName;
//        EditText fieldContent;
//
//        public TextInputViewHolder(@NonNull View itemView) {
//            super(itemView);
//            fieldName = itemView.findViewById(R.id.fieldName);
//            fieldContent = itemView.findViewById(R.id.fieldContent);
//
//        }
//    }
//
//    class TextDropdownViewHolder extends RecyclerView.ViewHolder {
//        TextView fieldNameText;
//        TextView fieldContentDropdown;
//
//        public TextDropdownViewHolder(@NonNull View itemView) {
//            super(itemView);
//            fieldNameText = itemView.findViewById(R.id.fieldNameText);
//            fieldContentDropdown = itemView.findViewById(R.id.fieldContentDropdown);
//
//        }
//    }
//
//    class DropdownTextViewHolder extends RecyclerView.ViewHolder {
//        TextView fieldNameDropdown;
//        TextView fieldContentText;
//
//        public DropdownTextViewHolder(@NonNull View itemView) {
//            super(itemView);
//            fieldNameDropdown = itemView.findViewById(R.id.fieldNameDropdown);
//            fieldContentText = itemView.findViewById(R.id.fieldContentText);
//
//        }
//    }


}