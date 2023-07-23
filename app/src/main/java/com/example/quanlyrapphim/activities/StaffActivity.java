package com.example.quanlyrapphim.activities;

import com.example.quanlyrapphim.R;
import com.example.quanlyrapphim.fragments.BookingFoodFragment;
import com.example.quanlyrapphim.fragments.BookingTicketFragment;
import com.example.quanlyrapphim.fragments.ProfileFragment;
import com.example.quanlyrapphim.fragments.StatisticTicketFragment;
import com.example.quanlyrapphim.fragments.SelectMovieFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.Toast;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.quanlyrapphim.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class StaffActivity extends AppCompatActivity {

//    private MeowBottomNavigation bottomNavigation;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_staff);
//
//        bottomNavigation = findViewById(R.id.bottomNavigation);
//
//        bottomNavigation.show(1, true);
//
//        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.property_1_ticket));
//        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.property_1_customer));
//        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.property_1_statistic));
//        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.property_1_user));
//
//
//
//        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
//            @Override
//            public Unit invoke(MeowBottomNavigation.Model model) {
//                switch (model.getId()){
//                    case 1:
//                        break;
//                    case 2:
//                        break;
//                    case 3:
//                        break;
//                    case 4:
//                        break;
//                }
//                return null;
//            }
//        });
//
//        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
//            @Override
//            public Unit invoke(MeowBottomNavigation.Model model) {
//                Fragment fragment = new SelectMovieFragment();
//
//                switch (model.getId()){
//                    case 1:
//                        fragment = new SelectMovieFragment();
//                        break;
//                    case 2:
//                        fragment = new BookingTicketFragment();
//                        break;
//                    case 3:
//                        fragment = new StatisticTicketFragment();
//                        break;
//                    case 4:
//                        fragment = new ProfileFragment();
//                        break;
//                }
//                loadFragment(fragment);
//                return null;
//            }
//        });
//        bottomNavigation.show(2,true);
//
//    }
//    private void loadFragment(Fragment fragment) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment, fragment, null)
//                .commit();
//    }


    private NavController navController;
    private BottomNavigationView bottomNavBar;
    private MaterialToolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        // find ui
        toolbar = findViewById(R.id.toolbar);
        bottomNavBar = findViewById(R.id.bottom_nav_bar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);

        // config navigation controller
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        // config app bar
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.filmScreenFragment, R.id.filmScreenFragment, R.id.employeeScreenFragment).setOpenableLayout(drawerLayout).build();

        // config bottom navigation bar
        NavigationUI.setupWithNavController(bottomNavBar, navController);

        // config tool bar (top app bar)
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

        // config drawer navigation
        NavigationUI.setupWithNavController(navView, navController);

        // todo: handle remove item in nav bar when not admin
//        Intent intent = getIntent();
//        String user = intent.getStringExtra("user");
//        if (!user.equals("admin")) {
//            bottomNavBar.getMenu().removeItem(R.id.adminFragment);
//        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}



