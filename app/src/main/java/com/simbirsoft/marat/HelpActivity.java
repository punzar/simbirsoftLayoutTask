package com.simbirsoft.marat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HelpActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if (currentFragment == null) {
            setFragment(new HelpFragment());
            bottomNavigationView.setSelectedItemId(R.id.bottom_nav_help);
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.bottom_nav_search: {
                setFragment(new SearchFragment());
                break;
            }
            case R.id.bottom_nav_help: {
                setFragment(new HelpFragment());
                break;
            }
            case R.id.bottom_nav_history: {
                break;
            }
            case R.id.bottom_nav_profile:
                setFragment(new ProfileFragment());
                break;
        }
        return true;
    }

}