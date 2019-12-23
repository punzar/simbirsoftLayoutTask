package com.simbirsoft.marat;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.simbirsoft.marat.HelpCategory;
import com.simbirsoft.marat.NewsEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonFromAssets {
    public static ArrayList<HelpCategory> getCategories(Context context) {

        String json;
        ArrayList<HelpCategory> categoryArrayList = new ArrayList<>();

        try (InputStream inputStream = context.getAssets().open("event_category.json")) {
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);

            json = new String(buffer, "UTF-8");

            Gson gson = new Gson();
            categoryArrayList = gson.fromJson(json, new TypeToken<ArrayList<HelpCategory>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return categoryArrayList;
    }

    public static <T> ArrayList<T> getNewsEvent(String json, Type type) {

        ArrayList<T> eventArrayList = new ArrayList<>();

//            Type type = new TypeToken<ArrayList<NewsEvent>>(){}.getType();
            eventArrayList = new Gson().fromJson(json,type);

        return eventArrayList;
    }
}

