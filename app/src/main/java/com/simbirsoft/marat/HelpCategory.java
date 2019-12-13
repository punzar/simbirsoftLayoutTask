package com.simbirsoft.marat;

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
