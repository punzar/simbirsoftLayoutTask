package com.simbirsoft.marat;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;


public class HelpCategory implements Parcelable {
    private String name;
    private boolean state;

    public HelpCategory(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        HelpCategory categories = (HelpCategory) obj;
        return categories.getName().equals(name) && (state == categories.isState());
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

    protected HelpCategory(Parcel in) {
        name = in.readString();
        state = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeByte((byte) (state ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<HelpCategory> CREATOR = new Parcelable.Creator<HelpCategory>() {
        @Override
        public HelpCategory createFromParcel(Parcel in) {
            return new HelpCategory(in);
        }

        @Override
        public HelpCategory[] newArray(int size) {
            return new HelpCategory[size];
        }
    };
}
