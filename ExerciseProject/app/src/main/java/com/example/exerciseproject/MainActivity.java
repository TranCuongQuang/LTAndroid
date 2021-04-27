package com.example.exerciseproject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    ProgressBar myBarHorizontal;
    TextView lblTopCaption;
    EditText txtDataBox;
    Button btnDoItAgain;
    int accum = 0, progressStep = 1;
    boolean isRunning = false;
    final int MAX_PROGRESS = 100;
    int numPercent;
    Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        myBarHorizontal = (ProgressBar) findViewById(R.id.myBarHor);
        txtDataBox = (EditText) findViewById(R.id.txtBox1);
        lblTopCaption = (TextView) findViewById(R.id.lblTopCaption);
        btnDoItAgain = (Button) findViewById(R.id.btnDoItAgain);

        btnDoItAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String val = txtDataBox.getText().toString();
                if (!val.equals("") && isNumeric(val)) {
                    numPercent = Integer.parseInt(val);
                    excuteProcess();
                } else {
                    Toast.makeText(getApplication(), "Dữ liệu bắt buộc phải là số.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    protected void excuteProcess() {
        isRunning = true;
        txtDataBox.setText("");
        btnDoItAgain.setEnabled(false);
        accum = 0;
        myBarHorizontal.setMax(MAX_PROGRESS);
        myBarHorizontal.setProgress(0);
        myBarHorizontal.setVisibility(View.VISIBLE);
        lblTopCaption.setVisibility(View.VISIBLE);

        Thread myBackgroundThread = new Thread(backgroundTask, "backAlias1");
        myBackgroundThread.start();
    }

    private Runnable foregroundRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                myBarHorizontal.incrementProgressBy(progressStep);
                accum += progressStep;
                lblTopCaption.setText(accum + "%");
                if (accum == myBarHorizontal.getMax()) {
                    btnDoItAgain.setEnabled(true);
                    isRunning = false;
                    myBarHorizontal.setProgress(0);
                    lblTopCaption.setText("0%");

//                    myBarHorizontal.setVisibility(View.INVISIBLE);
//                    lblTopCaption.setVisibility(View.INVISIBLE);
                }
            } catch (Exception e) {
                isRunning = false;
                Log.e("<<foregroundTask>>", e.getMessage());
            }
        }
    };

    private Runnable backgroundTask = new Runnable() {
        @Override
        public void run() {
            try {
                int runPercent = numPercent / 100;
                for (int n = 0; n < 100 && isRunning; n++) {
                    Thread.sleep(runPercent);
                    myHandler.post(foregroundRunnable);
                }
            } catch (InterruptedException e) {
                isRunning = false;
                Log.e(" << foregroundTask >>", e.getMessage());
            }
        }
    };
}
