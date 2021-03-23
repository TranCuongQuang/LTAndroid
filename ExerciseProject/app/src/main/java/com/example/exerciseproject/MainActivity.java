package com.example.exerciseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private int duration = Toast.LENGTH_SHORT;

    //PLUMBING: Pairing GUI controls with Java objects
    private Button btnExit;
    private EditText txtColorSelected;
    private TextView txtSpyBox;
    private LinearLayout myScreen;
    private String PREFNAME = "myPrefFile1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtColorSelected = (EditText) findViewById(R.id.editText1);
        btnExit = (Button) findViewById(R.id.button1);
        txtSpyBox = (TextView) findViewById(R.id.textView1);
        myScreen = (LinearLayout) findViewById(R.id.myScreen1);


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        txtColorSelected.addTextChangedListener(new TextWatcher() {
//            @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) { // nothing TODO, needed by interface
//        }
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//// nothing TODO, needed by interface }
//                @Override
//                public void afterTextChanged(Editable s) {
////set background to selected color
//                    String chosenColor = s.toString().toLowerCase(Locale.US); txtSpyBox.setText(chosenColor); setBackgroundColor(chosenColor, myScreen);
//                } });
        context = getApplicationContext();
        Toast.makeText(context, "onCreate", duration).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(context, "onStart", duration).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(context, "onPause", duration).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(context, "onResume", duration).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(context, "onStop", duration).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(context, "onDestroy", duration).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(context, "onRestart", duration).show();
    }
}