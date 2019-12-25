package com.simbirsoft.marat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;
import com.simbirsoft.marat.interfaces.FilterSettingsClickListener;
import com.simbirsoft.marat.interfaces.NewsItemClickListener;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    ArrayList<HelpCategory> mCategories;
    ArrayList<NewsEvent> mNewsEvents;
    NewsItemAdapter mAdapter;

    public NewsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        Toolbar toolbar = view.findViewById(R.id.news_toolbar);
        toolbar.inflateMenu(R.menu.filter_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (getActivity() instanceof FilterSettingsClickListener) {
                    FilterSettingsClickListener listener = (FilterSettingsClickListener) getActivity();
                    listener.setOnFilterListener();
                }
                return true;
            }
        });

        Type typeNewsEvent = new TypeToken<ArrayList<NewsEvent>>() {
        }.getType();
        Type typeHelpCategory = new TypeToken<ArrayList<HelpCategory>>() {
        }.getType();
        mCategories = getEventsList(view.getContext(),
                typeHelpCategory, "event_category.json");
        mNewsEvents = getEventsList(view.getContext(),
                typeNewsEvent, "news_events.json");


        initRecyclerView(view, mNewsEvents);
        ArrayList<NewsEvent> newList = filterList(mNewsEvents);
        mAdapter.updateData(newList);
        mNewsEvents = newList;

        return view;
    }

    private <T> ArrayList<T> getEventsList(Context context, Type typeOfT, String jsonName) {
        String json;
        ArrayList<T> events = new ArrayList<>();
        try (InputStream inputStream = context.getAssets().open(jsonName)) {

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);

            json = new String(buffer, "UTF-8");
            events = new JsonFromAssets().getNewsEvent(json, typeOfT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }

    private void restoreCategoriesState() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            for (HelpCategory category : mCategories) {
                category.setState(sharedPreferences.getBoolean(category.getName(), true));
            }
        }
    }

    private void initRecyclerView(View view, ArrayList<NewsEvent> list) {
        RecyclerView recyclerView = view.findViewById(R.id.news_rv);
        NewsEventListenerable listener = new NewsEventListenerable() {
            @Override
            public void onClick(View view, int position) {
                NewsEvent event = mNewsEvents.get(position);
                if (getActivity() instanceof NewsItemClickListener) {
                    NewsItemClickListener clickListener = (NewsItemClickListener) getActivity();

                    clickListener.onNewsItemCLick(event);
                }
            }
        };
        mAdapter = new NewsItemAdapter(view.getContext(), list, listener);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    private ArrayList<NewsEvent> filterList(ArrayList<NewsEvent> list) {
        restoreCategoriesState();
        ArrayList<NewsEvent> newList = new ArrayList<>();
        for (NewsEvent item : list) {
            for (HelpCategory category : item.getHelpCategory()) {
                if (mCategories.contains(category)) {
                    newList.add(item);
                    break;
                }
            }
        }
        return newList;
    }

}
