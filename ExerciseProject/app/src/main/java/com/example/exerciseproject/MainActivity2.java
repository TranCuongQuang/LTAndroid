package com.example.exerciseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView txtUsername, txtBirthday, txtGender, txtHobbies;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        txtUsername = (TextView) findViewById(R.id.txtUsername);
        txtBirthday = (TextView) findViewById(R.id.txtBirthday);
        txtGender = (TextView) findViewById(R.id.txtGender);
        txtHobbies = (TextView) findViewById(R.id.txtHobbies);
        btnExit = (Button) findViewById(R.id.btnExit);

        Intent myCallerIntent = getIntent();

        String username = myCallerIntent.getExtras().getString("username");
        String birthday = myCallerIntent.getExtras().getString("birthday");
        String gender = myCallerIntent.getExtras().getString("gender");
        String hobbies = myCallerIntent.getExtras().getString("hobbies");

        txtUsername.setText(username);
        txtBirthday.setText(birthday);
        txtGender.setText(gender);
        txtHobbies.setText(hobbies);

        btnExit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }
}
