package com.example.quanlyrapphim;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StaffListActivity extends AppCompatActivity {

    private ArrayList<Staff> staffs = new ArrayList<Staff>();
    private RecyclerView staffRecycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_list);
        initStaff();

        staffRecycleView = findViewById(R.id.list_staff_recycle_view);
        StaffRecyclerViewAdapter adapter = new StaffRecyclerViewAdapter(this, staffs);
        adapter.setOnDeleteClickListener(i -> {
            ConfirmDeleteDialog confirmDeleteDialog = new ConfirmDeleteDialog();
            confirmDeleteDialog.setDeleteListener(() -> {
                Toast.makeText(StaffListActivity.this, "Deleted item at " + i, Toast.LENGTH_SHORT).show();
            });
            confirmDeleteDialog.setCancelListener(() -> {
                Toast.makeText(StaffListActivity.this, "Cancel deleted item at " + i, Toast.LENGTH_SHORT).show();
            });
            confirmDeleteDialog.show(getSupportFragmentManager(), "dialog");
        });
        adapter.setOnEditClickListener(i -> {
            Toast.makeText(StaffListActivity.this, "Edit item at " + i, Toast.LENGTH_SHORT).show();
        });
        staffRecycleView.setAdapter(adapter);
        staffRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }
    void initStaff() {
        staffs.add(new Staff("Nguyen Van A"));
        staffs.add(new Staff("Le Van A"));
        staffs.add(new Staff("Nguyen Thi ABC"));
        staffs.add(new Staff("Ly Thi D"));
        staffs.add(new Staff("Phan Thi EF"));
        staffs.add(new Staff("Tran Van GH"));
    }
}


class StaffRecyclerViewAdapter extends RecyclerView.Adapter<StaffRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Staff> staffs;
    private OnDeleteClickListener deleteListener;
    private OnEditClickListener editClickListener;

    public StaffRecyclerViewAdapter(Context context, ArrayList<Staff> staffs) {
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
        holder.btnEdit.setOnClickListener(view -> {
            editClickListener.onEditClick(i);
        });
        holder.btnDelete.setOnClickListener(view -> {
            deleteListener.onDeleteClick(i);
        });
    }

    @Override
    public int getItemCount() {
        return staffs.size();
    }

    void setOnDeleteClickListener(OnDeleteClickListener listener) {
        deleteListener = listener;
    }

    void setOnEditClickListener(OnEditClickListener listener) {
        editClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageButton btnDelete;
        ImageButton btnEdit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.staffName);
            btnDelete = itemView.findViewById(R.id.btnDeleteStaff);
            btnEdit = itemView.findViewById(R.id.btnEditStaff);
        }
    }

    interface OnDeleteClickListener {
        void onDeleteClick(int i);
    }

    interface OnEditClickListener {
        void onEditClick(int i);
    }

}

class Staff {
    private String name;

    public Staff(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}