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

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.simbirsoft.marat.interfaces.FilterSettingsClickListener;
import com.simbirsoft.marat.interfaces.NewsItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
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
        mCategories = getCategoriesListFromJson();
        mNewsEvents = getListFromJson();
        initRecyclerView(view, mNewsEvents);
        ArrayList<NewsEvent> newList = filterList(mNewsEvents);
        mAdapter.updateData(newList);
        mNewsEvents = newList;

        return view;
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
            boolean isFound = false;
            for (HelpCategory category : item.getHelpCategory()) {
                if (!isFound && mCategories.contains(category)) {
                    newList.add(item);
                    isFound = true;
                }
            }
        }
        return newList;
    }

    ArrayList<NewsEvent> getListFromJson() {

        String json;
        ArrayList<NewsEvent> eventArrayList = new ArrayList<>();

        try (InputStream inputStream = getActivity().getAssets().open("news_events.json")) {
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                NewsEvent event = new NewsEvent();
                event.setId(object.getInt("id"));
                event.setTitel(object.getString("title"));
                event.setDateText(object.getString("dataText"));
                event.setFoundationName(object.getString("foundationName"));
                event.setLocation(object.getString("location"));
                event.setPhoneNumber(object.getString("phoneNumber"));
                event.setSupportMessageBegin(object.getString("supportMessageBegin"));
                event.setSupportMessageEnd(object.getString("supportMessageEnd"));
                event.setPhotoHead(object.getString("photoHeadPath"));
                event.setArticleText(object.getString("articleText"));
                event.setArticleTextEnd(object.getString("articleTextEnd"));
                event.setPhotoLikersPath(getArray(object.getJSONArray("photoLikersPath")));
                event.setCountOfLike(object.getString("countOfLike"));
                event.setHelpCategory(getCategoriesArray(object.getJSONArray("helpCategory")));

                eventArrayList.add(event);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return eventArrayList;
    }

    private ArrayList<HelpCategory> getCategoriesArray(JSONArray helpCategory) throws JSONException {
        ArrayList<HelpCategory> newList = new ArrayList<>();

        for (int i = 0; i < helpCategory.length(); i++) {
            for (HelpCategory category : mCategories) {
                if (category.getName().equals(helpCategory.getString(i)))
                    newList.add(category);
            }
        }
        return newList;
    }


    public ArrayList<HelpCategory> getCategoriesListFromJson() {

        String json;
        ArrayList<HelpCategory> categoryArrayList = new ArrayList<>();

        try (InputStream inputStream = getActivity().getAssets().open("event_category.json")) {
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                HelpCategory category = new HelpCategory(object.getString("name"),
                        object.getBoolean("state"));
                categoryArrayList.add(category);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categoryArrayList;
    }

    private String[] getArray(JSONArray jsonArray) throws JSONException {
        String[] array = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            array[i] = (String) jsonArray.get(i);
        }
        return array;
    }

}
