package com.example.quanlyrapphim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private BottomNavigationView bottomNavBar;
    private MaterialToolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find ui
        toolbar = findViewById(R.id.toolbar);
        bottomNavBar = findViewById(R.id.bottom_nav_bar);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        // config bottom navigation bar
        NavigationUI.setupWithNavController(bottomNavBar, navController);

        // todo: handle remove item in nav bar when not admin
//        Intent intent = getIntent();
//        String user = intent.getStringExtra("user");
//        if (!user.equals("admin")) {
//            bottomNavBar.getMenu().removeItem(R.id.adminFragment);
//        }

        // config tool bar (top app bar)
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.ticketScreenFragment, R.id.customerScreenFragment).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
    }
}