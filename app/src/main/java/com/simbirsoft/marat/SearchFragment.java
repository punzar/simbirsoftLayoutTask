package com.simbirsoft.marat;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private SearchView mSearchView = null;
    private SearchView.OnQueryTextListener mQueryTextListener;
    private Toolbar mToolbar;
    Context mContext;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = view.findViewById(R.id.my_toolbar_search);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        mContext = view.getContext();
        if (appCompatActivity != null)

            appCompatActivity.setSupportActionBar(mToolbar);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


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
                    Log.i("onQueryTextSubmit :: ", s);
                    Toast.makeText(mContext, "onQueryTextSubmit: " + s, Toast.LENGTH_SHORT).show();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    Log.i("onQueryTextChange :: ", s);
                    Toast.makeText(mContext, "onQueryTextChange: " + s, Toast.LENGTH_SHORT).show();
                    return true;
                }
            };
            mSearchView.setOnQueryTextListener(mQueryTextListener);
            //todo вынести в string xml;
            mSearchView.setQueryHint("Введите название организации");
            mSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b) {
                        mSearchView.setBackgroundColor(getResources().getColor(R.color.White));

                    } else {
                        mSearchView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    }
                }
            });


        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_search:

                return true;

            default:
                break;
        }

        mSearchView.setOnQueryTextListener(mQueryTextListener);
        return super.onOptionsItemSelected(item);
    }
}
