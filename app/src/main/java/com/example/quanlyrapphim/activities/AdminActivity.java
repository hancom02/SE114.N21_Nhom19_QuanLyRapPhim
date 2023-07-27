package com.example.quanlyrapphim.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.models.Account;
import com.example.quanlyrapphim.utils.GetCurrentAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity implements GetCurrentAccount {

    private NavController navController;
    private BottomNavigationView bottomNavBar;
    private MaterialToolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private AppBarConfiguration appBarConfiguration;

    private Account account;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // find ui
        toolbar = findViewById(R.id.toolbar);
        bottomNavBar = findViewById(R.id.bottom_nav_bar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.admin_nav_view);

        // config navigation controller
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        // config app bar
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.ticketScreenFragment, R.id.filmScreenFragment, R.id.staffScreenFragment, R.id.cinemaRoomScreenFragment, R.id.refreshmentScreenFragment, R.id.timeSlotScreenFragment, R.id.showTimeScreenGroup, R.id.tempBooking, R.id.statisticTicketFragment,R.id.profileFragment).setOpenableLayout(drawerLayout).build();

        // config bottom navigation bar
        NavigationUI.setupWithNavController(bottomNavBar, navController);

        // config tool bar (top app bar)
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

        // config drawer navigation
        NavigationUI.setupWithNavController(navView, navController);


        // get account id
        Intent intent = getIntent();
        String accountId = intent.getStringExtra("accountId");
//        if (!user.equals("admin")) {
//            bottomNavBar.getMenu().removeItem(R.id.adminFragment);
//        }

        Log.i("ACCOUNT_ID", accountId);

        // GET ACCOUNT
        DocumentReference docRef = db.collection("accounts").document(accountId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        account = document.toObject(Account.class);

                        // set drawer infor
                        View headerView = navView.getHeaderView(0);

                        TextView navName = (TextView) headerView.findViewById(R.id.drawer_header_name);
                        navName.setText(account.getName());
                        TextView navEmail = (TextView) headerView.findViewById(R.id.drawer_header_email);
                        navEmail.setText(account.getEmail());
                        ImageView navAvatar = (ImageView) headerView.findViewById(R.id.drawer_header_avatar);
                        Picasso.get().load(account.getAvatar()).into(navAvatar);
                        MaterialCardView navCard = (MaterialCardView) headerView.findViewById(R.id.drawer_header_card);
                        navCard.setOnClickListener(v -> {
                            navController.navigateUp();
                            navController.navigate(R.id.filmScreenGroup);
                        });
                        MaterialButton btnLogout = (MaterialButton) headerView.findViewById(R.id.drawer_header_logout);
                        btnLogout.setOnClickListener(v-> {
                            Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                            startActivity(intent);
                            AdminActivity.this.finish();
                            return;
                        });
                    } else {
                        Log.d("FILM_DETAIL", "No such document");
                    }
                } else {
                    Log.d("FILM_DETAIL", "get failed with ", task.getException());
                }
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    public Account getAccount() {
        return account;
    }
}