package com.simbirsoft.marat;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class NKOFragment extends Fragment {
    private ArrayList<String> mTexts = null;
    private View view;


    public NKOFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_nko, container, false);
        initItems();
        return view;
    }

    private void initItems() {
        if (mTexts != null) mTexts = null;
        mTexts = new ArrayList<>();
        String[] texts = getResources().getStringArray(R.array.nko_array_texts);
        Random rnd = new Random();
        for (int i = 0; i < texts.length; i++) {
            mTexts.add(texts[rnd.nextInt(texts.length)]);
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_nko);
        NKORecyclerViewAdapter recyclerViewAdapter = new NKORecyclerViewAdapter(view.getContext(), mTexts);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        initItems();
    }
}
