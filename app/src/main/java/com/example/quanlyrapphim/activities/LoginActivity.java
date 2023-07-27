package com.example.quanlyrapphim.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.models.Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText email;
    private EditText password;
    private ArrayList<Account> allAccounts = new ArrayList<>();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.login_activity_btn_login);
        email = findViewById(R.id.login_activity_email);
        password = findViewById(R.id.login_activity_password);

        // GET ALL ACCOUNT
        db.collection("accounts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            allAccounts = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Account account = document.toObject(Account.class);
                                account.setId(document.getId());

                                allAccounts.add(account);
                            }
                        }
                    }
                });



        btnLogin.setOnClickListener(v -> {

            // simple validate
            if (
                    email.getText().toString().equals("") || password.getText().toString().equals("")
            ) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check correct account
            for (Account account: allAccounts) {
                if (account.getEmail().equals(email.getText().toString()) && account.getPassword().equals(password.getText().toString())) {

                    // todo: check admin or staff
                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    intent.putExtra("accountId", account.getId());
                    intent.putExtra("accountRole", account.getRole());
                    startActivity(intent);
                    LoginActivity.this.finish();
                    return;
                }
            }

            Toast.makeText(this, "Email hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();


        });
    }
}