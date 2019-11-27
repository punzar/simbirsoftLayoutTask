package com.simbirsoft.marat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

public class HelpActivity extends AppCompatActivity implements ProfileFragment.IPhotoPath {
    int mSelectedButton = -1;
    BottomNavigationView bottomNavigationView;
    //boolean isGone = false;

    SearchFragment searchFragment;
    HelpFragment helpFragment;
    ProfileFragment profileFragment;
    int saveState;
    String mPhotoPath;

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        if (fragment instanceof ProfileFragment){
            ProfileFragment profileFragment = (ProfileFragment) fragment;
            profileFragment.setPhotoPathListener(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        searchFragment = new SearchFragment();
        helpFragment = new HelpFragment();
        profileFragment = new ProfileFragment();

        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(getBottomNavigationListener());
        if (savedInstanceState != null) {
            bottomNavigationView.setSelectedItemId(saveState);
        } else {
            bottomNavigationView.setSelectedItemId(R.id.bottom_nav_help);
        }


//        if (savedInstanceState != null) {
//
//                mSelectedButton = savedInstanceState.getInt("BNV");
//                getBottomStateFromIntent(mSelectedButton);
//
//        } else {
//
//
//            getBottomStateFromIntent(mSelectedButton);
//        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("BNV", mSelectedButton);
        saveState = bottomNavigationView.getSelectedItemId();

//        outState.putBoolean("ISGONE", isGone);
    }

//    private void getBottomStateFromIntent(int selectedBtnNum) {
//        switch (selectedBtnNum) {
//            case 1: {
//                SearchFragment searchFragment = new SearchFragment();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame_layout, searchFragment);
//                fragmentTransaction.commit();
//                bottomNavigationView.setSelectedItemId(R.id.bottom_nav_search);
//                break;
//            }
//            case 2: {
//                HelpFragment helpFragment = new HelpFragment();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame_layout, helpFragment);
//                fragmentTransaction.commit();
//                bottomNavigationView.setSelectedItemId(R.id.bottom_nav_help);
//                break;
//            }
//            case 4: {
//                ProfileFragment profileFragment = new ProfileFragment();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame_layout, profileFragment);
//                fragmentTransaction.commit();
//                bottomNavigationView.setSelectedItemId(R.id.bottom_nav_profile);
//            }
//            case -1:
//                bottomNavigationView.setSelectedItemId(R.id.bottom_nav_help);
//                break;
//        }
//
//    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener getBottomNavigationListener() {
        return new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_nav_search: {
                        setFragment(searchFragment);
//                        SearchFragment searchFragment = new SearchFragment();
//                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                        fragmentTransaction.replace(R.id.frame_layout, searchFragment);
//                        fragmentTransaction.commit();
                        mSelectedButton = 1;
                        break;
                    }
                    case R.id.bottom_nav_help: {
                        setFragment(helpFragment);
//                        HelpFragment helpFragment = new HelpFragment();
//                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                        fragmentTransaction.replace(R.id.frame_layout, helpFragment);
//                        fragmentTransaction.commit();
                        mSelectedButton = 2;
                        break;
                    }
                    case R.id.bottom_nav_history: {
                        break;
                    }
                    case R.id.bottom_nav_profile:
                        setFragment(profileFragment);
//                        ProfileFragment profileFragment = new ProfileFragment();
//                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                        fragmentTransaction.replace(R.id.frame_layout, profileFragment);
//                        fragmentTransaction.commit();
                        mSelectedButton = 4;
//                        Intent intent = new Intent(HelpActivity.this, ProfileActivity.class);
//                        isGone = true;
//                        startActivity(intent);
                        break;
                }
                return true;
            }
        };


    }

    @Override
    public void savePhotoPath(String photoPath) {
        mPhotoPath = photoPath;
        Toast.makeText(this,mPhotoPath,Toast.LENGTH_SHORT).show();
    }
}
