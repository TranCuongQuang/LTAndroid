package com.example.exerciseproject;

public interface MainCallbacks {
    public void onMsgFromLeftFragToMain (String sender, Person strValue);
    public void onMsgFromRightFragToMain (String sender, String position);
}
