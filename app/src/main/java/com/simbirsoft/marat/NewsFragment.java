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
public class NewsFragment extends Fragment {
    ArrayList<HelpCategory> mCategories;


    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //getListFromJson();
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
                Toast.makeText(getActivity(), "Filter button is clicked!", Toast.LENGTH_LONG).show();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new FilterFragment());
                fragmentTransaction.commit();
                return true;
            }
        });
        mCategories = getCategoriesListFromJson();
        restoreCategoriesState();
        initRecyclerView(view,getListFromJson(mCategories));


        return view;
    }
    private void restoreCategoriesState(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        for (HelpCategory category : mCategories){
            category.setState(sharedPreferences.getBoolean(category.getName(),true));
        }
    }

    private void initRecyclerView(View view, ArrayList<NewsEvent> list) {
        RecyclerView recyclerView = view.findViewById(R.id.news_rv);
        NewsItemAdapter recyclerViewAdapter = new NewsItemAdapter(view.getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    ArrayList<NewsEvent> getListFromJson(ArrayList<HelpCategory> categories) {

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
                boolean checked = checkCategory(getArray(object.getJSONArray("helpCategory")));

                if(checked) {
                    NewsEvent event = new NewsEvent();
                    event.setId(object.getInt("id"));
                    event.setTitel(object.getString("title"));
                    event.setDateText(object.getString("dataText"));
                    event.setFoundationName(object.getString("foundationName"));
                    event.setLocation(object.getString("location"));
                    event.setPhoneNumber(object.getString("phoneNumber"));
                    event.setSupportMessageBegin(object.getString("supportMessageBegin"));
                    event.setSupportMessageEnd(object.getString("supportMessageEnd"));
                    //todo разобраться с фото! что то идет не так.
                    event.setPhotoHead(getActivity(), object.getString("photoHeadPath"));
                    event.setArticleText(object.getString("articleText"));
                    event.setArticleTextEnd(object.getString("articleTextEnd"));
                    event.setPhotoLikersPath(getActivity(), getArray(object.getJSONArray("photoLikersPath")));
                    event.setCountOfLike(object.getString("countOfLike"));
                    event.setHelpCategory(getArray(object.getJSONArray("helpCategory")));
                    eventArrayList.add(event);
                }
            }

            Toast.makeText(getActivity(), "Json was parsed and we have " + eventArrayList.size() + "event", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return eventArrayList;
    }

    private boolean checkCategory(String[] checkList){
        boolean checked = false;
        for(String item : checkList){
            for(HelpCategory category : mCategories){
                if((item.equals(category.getName()))&& category.isState())
                    checked = true;
            }
        }
        return checked;
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

            for(int i = 0; i < jsonArray.length(); i++){
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
