package com.simbirsoft.marat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.simbirsoft.marat.interfaces.FilterSettingsClickListener;
import com.simbirsoft.marat.interfaces.NewsItemClickListener;

public class HelpActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        FilterSettingsClickListener, NewsItemClickListener {
    private BottomNavigationView bottomNavigationView;
    private boolean isBottomVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        if (savedInstanceState != null)
            isBottomVisible = savedInstanceState.getBoolean("ISBOTTOMVISIBLE", true);

        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        if (!isBottomVisible)
            bottomNavigationView.setVisibility(View.GONE);

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

    private void setFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (addToBackStack)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.bottom_nav_news: {
                setFragment(new NewsFragment());
                break;
            }
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


    @Override
    public void setOnFilterListener() {
        setFragment(new FilterFragment(), true);
    }

    @Override
    public void setOnOkBtnListener() {
        setFragment(new NewsFragment());
    }


    @Override
    public void onNewsItemCLick(NewsEvent event) {
        bottomNavigationView.setVisibility(View.GONE);
        //todo
        isBottomVisible = false;
        Bundle bundle = new Bundle();
        bundle.putParcelable("NewsEvent", event);

        Fragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundle);

//        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        Gson gson = new Gson();
//        editor.putString("EVENT", gson.toJson(event));
//        editor.apply();

        setFragment(detailsFragment, true);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("ISBOTTOMVISIBLE", isBottomVisible);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        bottomNavigationView.setVisibility(View.VISIBLE);
        isBottomVisible = true;

    }
}