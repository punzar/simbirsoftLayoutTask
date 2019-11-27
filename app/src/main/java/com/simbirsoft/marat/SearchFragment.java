package com.simbirsoft.marat;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;

import com.google.android.material.tabs.TabLayout;


public class SearchFragment extends Fragment {

    private SearchView mSearchView = null;
    private SearchView.OnQueryTextListener mQueryTextListener;
    private Toolbar mToolbar;
    Context mContext;

    ViewPager viewPager;
    PagerAdapter pagerAdapter;


    public SearchFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mToolbar = view.findViewById(R.id.my_toolbar_search);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        mContext = view.getContext();
        if (appCompatActivity != null)
            appCompatActivity.setSupportActionBar(mToolbar);
        viewPager = view.findViewById(R.id.view_pager);
        pagerAdapter = new MyFragmentPagerAdapter(getFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(pagerAdapter);


        TabLayout tabLayout = view.findViewById(R.id.tabLayoutSearch);
        tabLayout.setupWithViewPager(viewPager);


        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            mSearchView = (SearchView) searchItem.getActionView();
        }

        if (mSearchView != null) {
            mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            mQueryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return true;
                }
            };
            mSearchView.setOnQueryTextListener(mQueryTextListener);
            mSearchView.setIconifiedByDefault(false);
            mSearchView.setQueryHint(getResources().getString(R.string.search_view_hint));
        }
    }

    private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

        public MyFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new EventFragment();
                case 1:
                    return new NKOFragment();

                default:
                    return new NKOFragment();
            }

        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.pager_events_title);
                case 1:
                    return getResources().getString(R.string.pager_nko_title);
                default:
                    return "wrong";
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

    }

}


