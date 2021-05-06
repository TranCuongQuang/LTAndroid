package com.example.exerciseproject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityW7 extends Activity {
    public ProgressBar myBarHorizontal;
    TextView lblTopCaption;
    EditText txtDataBox;
    Button btnDoItAgain;
    int accum = 0, progressStep = 1;
    boolean isRunning = false;
    final int MAX_PROGRESS = 100;
    int numPercent;
    Handler myHandler = new Handler();
    Integer count =1;
    // TextView txt;
    int gblRun = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        myBarHorizontal = (ProgressBar) findViewById(R.id.myBarHor);
        txtDataBox = (EditText) findViewById(R.id.txtBox1);
        lblTopCaption = (TextView) findViewById(R.id.lblTopCaption);
        btnDoItAgain = (Button) findViewById(R.id.btnDoItAgain);

        //  txt = (TextView) findViewById(R.id.output);

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
        gblRun = 0;
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
                int runPercent = numPercent / 100;

                new MainActivityW7.MyTask().execute(runPercent);

//                for (int n = 0; n <= runPercent && isRunning; n++) {
//                    if( n == runPercent){
//                        myBarHorizontal.incrementProgressBy(progressStep);
//                        accum += progressStep;
//                        lblTopCaption.setText(accum + "%");
//                        if (accum == myBarHorizontal.getMax()) {
//                            btnDoItAgain.setEnabled(true);
//                            isRunning = false;
//                            myBarHorizontal.setProgress(0);
//                            lblTopCaption.setText("0%");
//
////                    myBarHorizontal.setVisibility(View.INVISIBLE);
////                    lblTopCaption.setVisibility(View.INVISIBLE);
//                        }
//                    }
//                }
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
                for (int n = 0; n < 100 && isRunning; n++) {
                    Thread.sleep(1);
                    myHandler.post(foregroundRunnable);
                }
            } catch (InterruptedException e) {
                isRunning = false;
                Log.e(" << foregroundTask >>", e.getMessage());
            }
        }
    };

    class MyTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            //  int startChange = 0;
            // int
            for (; count <= params[0]; count++) {
                try {
                    Thread.sleep(1);

                    //   startChange
                    if(count == params[0]){
                        gblRun +=1;

                        publishProgress(gblRun);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Task Completed.";
        }
        @Override
        protected void onPostExecute(String result) {
            // progressBar.setVisibility(View.GONE);
            //lblTopCaption.setText(result);
            // btn.setText("Restart");
        }
        @Override
        protected void onPreExecute() {

            //txt.setText("Task Starting...");
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            lblTopCaption.setText(values[0] + "%");
            myBarHorizontal.setProgress(values[0]);
        }
    }
}
