package com.simbirsoft.marat;

import androidx.annotation.Nullable;

import java.util.ArrayList;

class HelpCategory {
    private int id;
    private String name;
    private boolean state;
    private static int countId = 0;

    public HelpCategory(String name) {
        this.name = name;
        id = countId;
        countId++;
    }

    public HelpCategory(String name, boolean state) {
        this.name = name;
        this.state = state;
        id = countId;
        countId++;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
//        if (this == obj)
//            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        HelpCategory categories = (HelpCategory) obj;
        return categories.getName().equals(name) && state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }
}
