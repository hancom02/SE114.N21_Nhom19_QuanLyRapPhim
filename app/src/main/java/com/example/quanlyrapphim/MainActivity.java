package com.example.quanlyrapphim;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnFilmList;
    private Button btnCustomerList;
    private Button btnStaffList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFilmList = findViewById(R.id.btnFilmList);
        btnFilmList.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, FilmListActivity.class));
        });

        btnCustomerList = findViewById(R.id.btnCustomerList);
        btnCustomerList.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, CustomerListActivity.class));
        });

        btnStaffList = findViewById(R.id.btnStaffList);
        btnStaffList.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, StaffListActivity.class));
        });
    }
}