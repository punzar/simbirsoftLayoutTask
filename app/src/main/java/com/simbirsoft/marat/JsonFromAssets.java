package com.simbirsoft.marat;


import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonFromAssets {

    public static <T> ArrayList<T> getNewsEvent(String json, Type type) {

        ArrayList<T> eventArrayList;

        eventArrayList = new Gson().fromJson(json, type);

        return eventArrayList;
    }
}

