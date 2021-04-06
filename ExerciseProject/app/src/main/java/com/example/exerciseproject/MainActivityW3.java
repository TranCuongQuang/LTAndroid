package com.example.exerciseproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivityW3 extends Activity {

    private Context context;
    Button btnReset, btnSignup, btnSelect;
    EditText editUsername, editPassword, editRetype, editBirthday;
    RadioButton rdGenderMale, rdGenderFemale;
    CheckBox checkboxFutsal, checkBoxTennis, checkBoxOther;
    RadioGroup rdgGender;
    private DatePicker datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        this.datePicker = (DatePicker) this.findViewById(R.id.datePicker);

        btnReset = (Button) findViewById(R.id.button2);
        btnSignup = (Button) findViewById(R.id.button3);
        btnSelect = (Button) findViewById(R.id.buttonSelect);

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
                Intent intent = new Intent(MainActivityW3.this, MainActivity2.class);

                String UsernameValue = editUsername.getText().toString();
                String BirthdayValue = editBirthday.getText().toString();

                if (!editPassword.getText().toString().equals(editRetype.getText().toString())) {
                    Toast.makeText(context, "Password doest not match", Toast.LENGTH_SHORT).show();
                    MainActivityW3.this.finish();
                }

                String GenderValue = "Male";
                if (rdGenderFemale.isChecked()) {
                    GenderValue = "Female";
                }

                String HobbiesValue = "";
                if (checkboxFutsal.isChecked()) {
                    HobbiesValue = HobbiesValue.concat(",Futsal");
                }
                if (checkBoxTennis.isChecked()) {
                    HobbiesValue = HobbiesValue.concat(",Tennis");
                }
                if (checkBoxOther.isChecked()) {
                    HobbiesValue = HobbiesValue.concat(",Others");
                }

                Bundle myBundle = new Bundle();

                myBundle.putString("username", UsernameValue);
                myBundle.putString("birthday", BirthdayValue);
                myBundle.putString("gender", GenderValue);
                myBundle.putString("hobbies", HobbiesValue);

                intent.putExtras(myBundle);
                startActivity(intent);



            }


        });

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month  = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        this.datePicker.init( year, month , day , new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                datePickerChange(  datePicker,   year,   month,   dayOfMonth);
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        context = getApplicationContext();
    }

    private void datePickerChange(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);
//        this.editTextDate.setText(dayOfMonth +"-" + (month + 1) + "-" + year);
    }

    private void showDate()  {
        int year = this.datePicker.getYear();
        int month = this.datePicker.getMonth(); // 0 - 11
        int day = this.datePicker.getDayOfMonth();

        Toast.makeText(this, "Date: " + day+"-"+ (month + 1) +"-"+ year, Toast.LENGTH_LONG).show();
    }
}