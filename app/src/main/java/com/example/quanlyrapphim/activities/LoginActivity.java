package com.example.quanlyrapphim.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.quanlyrapphim.viewmodels.MainActivity;
import com.example.quanlyrapphim.R;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {
    private MaterialButton btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = findViewById(R.id.login_activity_btn_login);
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
            startActivity(intent);
            LoginActivity.this.finish();
        });
    }
}