package com.simbirsoft.marat;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class NewsEvent implements Parcelable {
    private int id;
    private String titel;
    private String dateText;
    private String foundationName;
    private String Location;
    private String phoneNumber;
    private String supportMessageBegin;
    private String supportMessageEnd;
    private String photoHead;
    private String articleText;
    private String articleTextEnd;
    private ArrayList<String> photoLikersPath;
    private String countOfLike;
    private ArrayList<HelpCategory> helpCategory;

    public NewsEvent(){

    }

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

    protected NewsEvent(Parcel in) {
        id = in.readInt();
        titel = in.readString();
        dateText = in.readString();
        foundationName = in.readString();
        Location = in.readString();
        phoneNumber = in.readString();
        supportMessageBegin = in.readString();
        supportMessageEnd = in.readString();
        photoHead = in.readString();
        articleText = in.readString();
        articleTextEnd = in.readString();
        if (in.readByte() == 0x01) {
            photoLikersPath = new ArrayList<>();
            in.readList(photoLikersPath, String.class.getClassLoader());
        } else {
            photoLikersPath = null;
        }
        countOfLike = in.readString();
        if (in.readByte() == 0x01) {
            helpCategory = new ArrayList<>();
            in.readList(helpCategory, HelpCategory.class.getClassLoader());
        } else {
            helpCategory = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(titel);
        parcel.writeString(dateText);
        parcel.writeString(foundationName);
        parcel.writeString(Location);
        parcel.writeString(phoneNumber);
        parcel.writeString(supportMessageBegin);
        parcel.writeString(supportMessageEnd);
        parcel.writeString(photoHead);
        parcel.writeString(articleText);
        parcel.writeString(articleTextEnd);
        if (photoLikersPath == null) {
            parcel.writeByte((byte) (0x00));
        } else {
            parcel.writeByte((byte) (0x01));
            parcel.writeList(photoLikersPath);
        }
        parcel.writeString(countOfLike);
        if (helpCategory == null) {
            parcel.writeByte((byte) (0x00));
        } else {
            parcel.writeByte((byte) (0x01));
            parcel.writeList(helpCategory);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<NewsEvent> CREATOR = new Parcelable.Creator<NewsEvent>() {
        @Override
        public NewsEvent createFromParcel(Parcel in) {
            return new NewsEvent(in);
        }

        @Override
        public NewsEvent[] newArray(int size) {
            return new NewsEvent[size];
        }
    };
}
