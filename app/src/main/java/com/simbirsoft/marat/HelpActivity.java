package com.simbirsoft.marat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HelpActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(getBottomNavigationListener());

        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_help);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener getBottomNavigationListener() {
        return new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bottom_nav_search:{
                        SearchFragment searchFragment = new SearchFragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout, searchFragment);
                        fragmentTransaction.commit();
                        break;
                    }
                    case R.id.bottom_nav_help: {
                        HelpFragment helpFragment = new HelpFragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout, helpFragment);
                        fragmentTransaction.commit();
                        break;
                    }
                    case R.id.bottom_nav_history: {
                        break;
                    }
                    case R.id.bottom_nav_profile:
                        Intent intent = new Intent(HelpActivity.this, ProfileActivity.class);
                        startActivity(intent);
                }
                return true;
            }
        };


    }

}
