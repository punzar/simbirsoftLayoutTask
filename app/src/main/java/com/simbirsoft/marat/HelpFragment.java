package com.simbirsoft.marat;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment {
    private static final int NUM_OF_COLUMN = 2;
    private ArrayList<String> mTexts = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>();
    private View view;


    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_help, container, false);

        initItems();
        return view;

    }
    private void initItems(){
        mTexts.add(getString(R.string.kids));
        mImages.add(R.drawable.little_girl);

        mTexts.add(getString(R.string.adults));
        mImages.add(R.drawable.mustachioed_man);

        mTexts.add(getString(R.string.elderly));
        mImages.add(R.drawable.granny);

        mTexts.add(getString(R.string.pets));
        mImages.add(R.drawable.cat);

        mTexts.add(getString(R.string.events));
        mImages.add(R.drawable.vans);

        initRecyclerView();
    }
    private void initRecyclerView(){
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_help);
        HelpReciclerViewAdapter helpReciclerViewAdapter = new HelpReciclerViewAdapter(view.getContext(), mTexts, mImages);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_OF_COLUMN, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(helpReciclerViewAdapter);
    }

}
