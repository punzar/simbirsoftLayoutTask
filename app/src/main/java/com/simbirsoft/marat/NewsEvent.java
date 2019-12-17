package com.simbirsoft.marat;

import java.util.ArrayList;

public class NewsEvent {
    int id;
    String titel;
    String dateText;
    String foundationName;
    String Location;
    String phoneNumber;
    String supportMessageBegin;
    String supportMessageEnd;
    String photoHead;
    String articleText;
    String articleTextEnd;
    ArrayList<String> photoLikersPath;
    String countOfLike;
    ArrayList<HelpCategory> helpCategory;

    public void setPhotoHead(String photoHead) {
        this.photoHead = photoHead;
    }

    public String getArticleTextEnd() {
        return articleTextEnd;
    }

    public void setArticleTextEnd(String articleTextEnd) {
        this.articleTextEnd = articleTextEnd;
    }

    public String getPhotoHead() {
        return photoHead;
    }

    public ArrayList<String> getPhotoLikersPath() {
        return photoLikersPath;
    }

    public void setPhotoLikersPath(String[] photoLikers) {

        ArrayList<String> drawableArrayList = new ArrayList<>();

        for (int i = 0; i < photoLikers.length; i++) {
            drawableArrayList.add(photoLikers[i]);
        }

        this.photoLikersPath = drawableArrayList;
    }

    public String getSupportMessageEnd() {
        return supportMessageEnd;
    }

    public void setSupportMessageEnd(String supportMessageEnd) {
        this.supportMessageEnd = supportMessageEnd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setDateText(String dateText) {
        this.dateText = dateText;
    }

    public void setFoundationName(String foundationName) {
        this.foundationName = foundationName;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSupportMessageBegin(String supportMessageBegin) {
        this.supportMessageBegin = supportMessageBegin;
    }


    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public void setCountOfLike(String countOfLike) {
        this.countOfLike = countOfLike;
    }

    public void setHelpCategory(ArrayList<HelpCategory> helpCategoryArray) {
        helpCategory = helpCategoryArray;
    }

    public int getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public String getDateText() {
        return dateText;
    }

    public String getFoundationName() {
        return foundationName;
    }

    public String getLocation() {
        return Location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSupportMessageBegin() {
        return supportMessageBegin;
    }


    public String getArticleText() {
        return articleText;
    }

    public String getCountOfLike() {
        return countOfLike;
    }

    public ArrayList<HelpCategory> getHelpCategory() {
        return helpCategory;
    }
}
