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

public class StaffActivity extends AppCompatActivity {

    private MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.show(1, true);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.property_1_ticket));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.property_1_user));



        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        break;
                    case 2:
                        break;
                }
                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                Fragment fragment = null;

                switch (model.getId()){
                    case 1:
                        fragment = new SelectMovieFragment();
                        break;
                    case 2:
                        fragment = new BookingTicketFragment();
                        break;
                }
                loadFragment(fragment);
                return null;
            }
        });
        bottomNavigation.show(2,true);
//        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
//            @Override
//            public Unit invoke(MeowBottomNavigation.Model model) {
//                // YOUR CODES
//                switch (model.getId()){
//
//                }
//                return null;
//            }
//        });
    }
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, fragment, null)
                .commit();
    }
}