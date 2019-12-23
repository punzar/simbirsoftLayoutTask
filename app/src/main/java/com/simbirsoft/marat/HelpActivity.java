package com.simbirsoft.marat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.simbirsoft.marat.interfaces.FilterSettingsClickListener;
import com.simbirsoft.marat.interfaces.NewsItemClickListener;

public class HelpActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        FilterSettingsClickListener, NewsItemClickListener {
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

    public void hideBottomNavigation(boolean isVisible) {
        if (isVisible) {
            bottomNavigationView.setVisibility(View.VISIBLE);
        } else {
            bottomNavigationView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNewsItemCLick(NewsEvent event) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("NewsEvent", event);
        Fragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundle);

        setFragment(detailsFragment, true);

    }


}