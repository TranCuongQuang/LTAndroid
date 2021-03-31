package com.example.exerciseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

//    private Context context;
//    private int duration = Toast.LENGTH_SHORT;
//
//    private Button btnExit;
//    private EditText txtColorSelected;
//    private TextView txtSpyBox;
//    private LinearLayout myScreen;
//    private String PREFNAME = "myPrefFile1";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        txtColorSelected = (EditText) findViewById(R.id.editText1);
//        btnExit = (Button) findViewById(R.id.button1);
//        txtSpyBox = (TextView) findViewById(R.id.textView1);
//        myScreen = (LinearLayout) findViewById(R.id.myScreen1);
//
//        btnExit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        txtColorSelected.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                String chosenColor = s.toString().toLowerCase(Locale.US);
//                txtSpyBox.setText(chosenColor);
//                setBackgroundColor(chosenColor, myScreen);
//            }
//
//        });
//
//        context = getApplicationContext();
//        Toast.makeText(context, "onCreate", duration).show();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        updateMeUsingSavedStateData();
//        Toast.makeText(context, "onStart", duration).show();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        String chosenColor = txtSpyBox.getText().toString();
//        saveStateData(chosenColor);
//        Toast.makeText(context, "onPause", duration).show();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Toast.makeText(context, "onResume", duration).show();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Toast.makeText(context, "onStop", duration).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(context, "onDestroy", duration).show();
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Toast.makeText(context, "onRestart", duration).show();
//    }
//
//    private void setBackgroundColor(String chosenColor, LinearLayout myScreen) {
//        if (chosenColor.contains("red")) myScreen.setBackgroundColor(0xffff0000);
//        if (chosenColor.contains("green")) myScreen.setBackgroundColor(0xff00ff00);
//        if (chosenColor.contains("blue")) myScreen.setBackgroundColor(0xff0000ff);
//        if (chosenColor.contains("white")) myScreen.setBackgroundColor(0xffffffff);
//    }
//
//    private void saveStateData(String chosenColor) {
//        SharedPreferences myPrefContainer = getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);
//        SharedPreferences.Editor myPrefEditor = myPrefContainer.edit();
//        String key = "chosenBackgroundColor";
//        String value = txtSpyBox.getText().toString();
//        myPrefEditor.putString(key, value);
//        myPrefEditor.commit();
//    }
//
//    private void updateMeUsingSavedStateData() {
//        SharedPreferences myPrefContainer = getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);
//        String key = "chosenBackgroundColor";
//        String defaultValue = "white";
//        if ((myPrefContainer != null) && myPrefContainer.contains(key)) {
//            String color = myPrefContainer.getString(key, defaultValue);
//            setBackgroundColor(color, myScreen);
//        }
//    }

    private Context context;
    Button btnReset, btnSignup;
    EditText editUsername, editPassword, editRetype, editBirthday;
    RadioButton rdGenderMale, rdGenderFemale;
    CheckBox checkboxFutsal, checkBoxTennis, checkBoxOther;
    RadioGroup rdgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        btnReset = (Button) findViewById(R.id.button2);
        btnSignup = (Button) findViewById(R.id.button3);

        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editRetype = (EditText) findViewById(R.id.editRetype);
        editBirthday = (EditText) findViewById(R.id.editBirthday);

        rdGenderFemale = (RadioButton) findViewById(R.id.radioGenderFemale);
        rdGenderMale = (RadioButton) findViewById(R.id.radioGenderMale);
        rdGenderMale.setChecked(true);

        checkboxFutsal = (CheckBox) findViewById(R.id.checkboxFutbal);
        checkBoxTennis = (CheckBox) findViewById(R.id.checkboxTennis);
        checkBoxOther = (CheckBox) findViewById(R.id.checkboxOther);

        rdgGender = (RadioGroup) findViewById(R.id.rdgGender);


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUsername.setText("");
                editPassword.setText("");
                editRetype.setText("");
                editBirthday.setText("");
                checkboxFutsal.setChecked(false);
                checkBoxTennis.setChecked(false);
                checkBoxOther.setChecked(false);
                rdGenderMale.setChecked(true);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                String UsernameValue = editUsername.getText().toString();
                String BirthdayValue = editBirthday.getText().toString();

                if (!editPassword.getText().toString().equals(editRetype.getText().toString())) {
                    Toast.makeText(context, "Password doest not match", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                }



            }


        });

        context = getApplicationContext();
    }
}