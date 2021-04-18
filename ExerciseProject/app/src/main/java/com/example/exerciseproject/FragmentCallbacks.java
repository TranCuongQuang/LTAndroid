package com.example.exerciseproject;

public interface FragmentCallbacks {
    public void onMsgFromMainToLeftFragment(String sender, String position);
    public void onMsgFromMainToRightFragment(String sender, Person strValue);
}
