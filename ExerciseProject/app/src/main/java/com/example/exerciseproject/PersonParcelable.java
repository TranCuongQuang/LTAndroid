package com.example.exerciseproject;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonParcelable implements Parcelable {
    private String userName;
    private String password;

    protected PersonParcelable(String userName, String password) {
        userName = userName;
        password = password;
    }

    // Parcelling part
    public PersonParcelable(Parcel in){
        this.userName = in.readString();
        this.password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PersonParcelable> CREATOR = new Creator<PersonParcelable>() {
        @Override
        public PersonParcelable createFromParcel(Parcel in) {
            return new PersonParcelable(in);
        }

        @Override
        public PersonParcelable[] newArray(int size) {
            return new PersonParcelable[size];
        }
    };
}
