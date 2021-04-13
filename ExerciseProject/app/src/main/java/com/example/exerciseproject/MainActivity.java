package com.example.exerciseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        if (fragment.getClass() == FragmentLeftW5.class ){
            frmLeft = (FragmentLeftW5) fragment;
        }
        if (fragment.getClass() == FragmentRightW5.class ){
            frmRight = (FragmentRightW5) fragment;
        }
    }

    @Override
    public void onMsgFromFragToMain(String sender, Person strValue) {
//        Toast.makeText(getApplication(), " MAIN GOT>>" + sender + "\n" + strValue.getId(), Toast.LENGTH_LONG).show();
        if (sender.equals("RIGHT-FRAG")) {
            Toast.makeText(getApplication(), " MAIN GOT>>" + sender + "\n" + strValue.getId(), Toast.LENGTH_LONG).show();
        }
        if (sender.equals("LEFT-FRAG")) {
            try {
                frmRight.onMsgFromMainToFragment(sender ,strValue);
            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain" + e.getMessage());
            }
        }
    }
}