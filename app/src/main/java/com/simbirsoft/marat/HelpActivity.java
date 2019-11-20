package com.simbirsoft.marat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HelpActivity extends AppCompatActivity {
    int mSelectedButton = -1;
    BottomNavigationView bottomNavigationView;
    boolean isGone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(getBottomNavigationListener());

        if (savedInstanceState != null) {
            isGone = savedInstanceState.getBoolean("ISGONE");
            if (!isGone) {

                mSelectedButton = savedInstanceState.getInt("BNV");
                getBottomStateFromIntent(mSelectedButton);

            } else {
                Intent intent = getIntent();
                mSelectedButton = intent.getIntExtra("selected_btn", -1);
                getBottomStateFromIntent(mSelectedButton);
                isGone = false;
            }
        } else {

            Intent intent = getIntent();

            mSelectedButton = intent.getIntExtra("selected_btn", -1);
            getBottomStateFromIntent(mSelectedButton);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("BNV", mSelectedButton);
        outState.putBoolean("ISGONE", isGone);
    }

    private void getBottomStateFromIntent(int selectedBtnNum) {
        switch (selectedBtnNum) {
            case 1: {
                SearchFragment searchFragment = new SearchFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, searchFragment);
                fragmentTransaction.commit();
                bottomNavigationView.setSelectedItemId(R.id.bottom_nav_search);
                break;
            }
            case 2: {
                HelpFragment helpFragment = new HelpFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, helpFragment);
                fragmentTransaction.commit();
                bottomNavigationView.setSelectedItemId(R.id.bottom_nav_help);
                break;
            }
            case -1:
                bottomNavigationView.setSelectedItemId(R.id.bottom_nav_help);
                break;
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener getBottomNavigationListener() {
        return new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_nav_search: {
                        SearchFragment searchFragment = new SearchFragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout, searchFragment);
                        fragmentTransaction.commit();
                        mSelectedButton = 1;
                        break;
                    }
                    case R.id.bottom_nav_help: {
                        HelpFragment helpFragment = new HelpFragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout, helpFragment);
                        fragmentTransaction.commit();
                        mSelectedButton = 2;
                        break;
                    }
                    case R.id.bottom_nav_history: {
                        break;
                    }
                    case R.id.bottom_nav_profile:
                        Intent intent = new Intent(HelpActivity.this, ProfileActivity.class);
                        isGone = true;
                        startActivity(intent);
                        break;
                }
                return true;
            }
        };


    }

}
