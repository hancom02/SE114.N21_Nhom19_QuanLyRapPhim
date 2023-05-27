package com.example.quanlyrapphim;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomerListActivity extends AppCompatActivity {

    private ArrayList<Customer> customers = new ArrayList<Customer>();
    private RecyclerView customerRecycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        initCustomer();

        customerRecycleView = findViewById(R.id.list_customer_recycle_view);
        CustomerRecyclerViewAdapter adapter = new CustomerRecyclerViewAdapter(this, customers);
        adapter.setOnDeleteClickListener(i -> {
            ConfirmDeleteDialog confirmDeleteDialog = new ConfirmDeleteDialog();
            confirmDeleteDialog.setDeleteListener(() -> {
                Toast.makeText(CustomerListActivity.this, "Deleted item at " + i, Toast.LENGTH_SHORT).show();
            });
            confirmDeleteDialog.setCancelListener(() -> {
                Toast.makeText(CustomerListActivity.this, "Cancel deleted item at " + i, Toast.LENGTH_SHORT).show();
            });
            confirmDeleteDialog.show(getSupportFragmentManager(), "fdasdf");
        });
        adapter.setOnEditClickListener(i -> {
            Toast.makeText(CustomerListActivity.this, "Edit item at " + i, Toast.LENGTH_SHORT).show();
        });
        customerRecycleView.setAdapter(adapter);
        customerRecycleView.setLayoutManager(new LinearLayoutManager(this));

    }

    void initCustomer() {
        customers.add(new Customer("Nguyen Van A"));
        customers.add(new Customer("Le Van A"));
        customers.add(new Customer("Nguyen Thi ABC"));
        customers.add(new Customer("Ly Thi D"));
        customers.add(new Customer("Phan Thi EF"));
        customers.add(new Customer("Tran Van GH"));
    }
}


class CustomerRecyclerViewAdapter extends RecyclerView.Adapter<CustomerRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Customer> customers;
    private OnDeleteClickListener deleteListener;
    private OnEditClickListener editClickListener;

    public CustomerRecyclerViewAdapter(Context context, ArrayList<Customer> customers) {
        this.context = context;
        this.customers = customers;
    }

    @NonNull
    @Override
    public CustomerRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.customer_card, viewGroup, false);

        return new CustomerRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.tvName.setText(customers.get(i).getName());
        holder.btnEdit.setOnClickListener(view -> {
            editClickListener.onEditClick(i);
        });
        holder.btnDelete.setOnClickListener(view -> {
            deleteListener.onDeleteClick(i);
        });
    }

    @Override
    public int getItemCount() {
        return customers.size();
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
            tvName = itemView.findViewById(R.id.customerName);
            btnDelete = itemView.findViewById(R.id.btnDeleteCustomer);
            btnEdit = itemView.findViewById(R.id.btnEditCustomer);
        }
    }

    interface OnDeleteClickListener {
        void onDeleteClick(int i);
    }

    interface OnEditClickListener {
        void onEditClick(int i);
    }

}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}