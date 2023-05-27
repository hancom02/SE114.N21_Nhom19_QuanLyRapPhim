package com.example.quanlyrapphim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnFilmList;
    private Button btnCustomerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFilmList = findViewById(R.id.btnFilmList);
        btnFilmList.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, FilmListActivity.class));
        });

        btnFilmList = findViewById(R.id.btnCustomerList);
        btnFilmList.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, CustomerListActivity.class));
        });
    }
}