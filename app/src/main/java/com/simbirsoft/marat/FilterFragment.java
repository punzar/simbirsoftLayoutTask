package com.simbirsoft.marat;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {

    ArrayList<HelpCategory> mCategories;
    FilterItemAdapter recyclerViewAdapter;


    public FilterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_filter, container, false);
        Toolbar toolbar = view.findViewById(R.id.filter_toolbar);
        toolbar.inflateMenu(R.menu.ok_filter_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                StringBuilder flags = new StringBuilder();
                for (HelpCategory category: mCategories){
                    flags.append(category.isState() + " ");
                }
               // Toast.makeText(view.getContext(),flags,Toast.LENGTH_LONG).show();
                //todo применить фильтр к новостям
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new NewsFragment());
                fragmentTransaction.commit();
                return true;
            }
        });
        mCategories = getListFromJson();
        restoreCategoriesState();
        initRecyclerView(view,mCategories);

        return view;
    }

    private void restoreCategoriesState(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        for (HelpCategory category : mCategories){
            category.setState(sharedPreferences.getBoolean(category.getName(),true));
        }
    }

    private void initRecyclerView(View view, ArrayList<HelpCategory> list) {
        RecyclerView recyclerView = view.findViewById(R.id.filter_rv);
        recyclerViewAdapter = new FilterItemAdapter(view.getContext(), list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setOnCheckedChangeListener(mOnCheckedChangeListener);

    }

    public ArrayList<HelpCategory> getListFromJson() {

        String json;
        ArrayList<HelpCategory> categoryArrayList = new ArrayList<>();

        try (InputStream inputStream = getActivity().getAssets().open("event_category.json")) {
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                HelpCategory category = new HelpCategory(object.getString("name"),
                        object.getBoolean("state"));
                categoryArrayList.add(category);
            }

            Toast.makeText(getActivity(), "Json was parsed and we have " + categoryArrayList.size() + "event", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categoryArrayList;
    }
    private FilterItemAdapter.OnCheckedChangeListener mOnCheckedChangeListener = new FilterItemAdapter.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(int position, boolean isChecked) {
            mCategories.get(position).setState(isChecked);

            SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(mCategories.get(position).getName(),isChecked);
            editor.apply();
        }
    };
}
