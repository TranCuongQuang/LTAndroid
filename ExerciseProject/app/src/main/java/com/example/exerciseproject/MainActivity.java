package com.example.exerciseproject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

public class MainActivity extends FragmentActivity implements MainCallbacks {

    //    FragmentTransaction ft;
    FragmentLeftW5 frmLeft;
    FragmentRightW5 frmRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
//        ft = getSupportFragmentManager().beginTransaction();
//        frmLeft = frmLeft.newInstance("first-blue");
//        ft.replace(R.id.frmLeft, frmLeft);
//        ft.commit();
//
//        ft = getSupportFragmentManager().beginTransaction();
//        frmRight = FragmentRightW5.newInstance("first-red");
//        ft.replace(R.id.frmRight, frmRight);
//        ft.commit();

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment.getClass() == FragmentLeftW5.class) {
            frmLeft = (FragmentLeftW5) fragment;
        }
        if (fragment.getClass() == FragmentRightW5.class) {
            frmRight = (FragmentRightW5) fragment;
        }
    }

    @Override
    public void onMsgFromFragToMain(String sender, Person person, String action) {
//        Toast.makeText(getApplication(), sender +  " : action " + action, Toast.LENGTH_SHORT).show();
        if (sender.equals("RIGHT-FRAG")) {
            frmLeft.onMsgFromMainToFragment(sender, person, action);
        }
        if (sender.equals("LEFT-FRAG")) {
            frmRight.onMsgFromMainToFragment(sender, person, action);
        }
    }
}