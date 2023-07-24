package com.example.quanlyrapphim.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.quanlyrapphim.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends AppCompatActivity {

    private NavController navController;
    private BottomNavigationView bottomNavBar;
    private MaterialToolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // find ui
        toolbar = findViewById(R.id.toolbar);
        bottomNavBar = findViewById(R.id.bottom_nav_bar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);

        // config navigation controller
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        // config app bar
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.ticketScreenFragment, R.id.filmScreenFragment, R.id.staffScreenFragment, R.id.cinemaRoomScreenFragment, R.id.refreshmentScreenFragment, R.id.timeSlotScreenFragment, R.id.showTimeScreenGroup).setOpenableLayout(drawerLayout).build();

        // config bottom navigation bar
        NavigationUI.setupWithNavController(bottomNavBar, navController);

        // config tool bar (top app bar)
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

        // config drawer navigation
        NavigationUI.setupWithNavController(navView, navController);

        // handle remove item in nav bar when not admin
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
}