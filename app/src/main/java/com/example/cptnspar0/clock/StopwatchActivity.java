package com.example.cptnspar0.clock;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {

    Button btnStart, btnPause, btnLap;
    TextView txtTimer;
    Handler customHandler = new Handler();
    LinearLayout container;
    long startTime = 0L, timeInMilliSeconds = 0L, timeSwapBuff = 0L, updateTime = 0L;
    Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliSeconds = SystemClock.uptimeMillis()-startTime;
            updateTime = timeSwapBuff+timeInMilliSeconds;
            int secs = (int)(updateTime/1000);
            int mins = secs/60;
            int milliseconds = (int)(updateTime%1000);
            txtTimer.setText(""+mins+":"+String.format("%2d",secs)+":"
            +String.format("%3d",milliseconds));
            customHandler.postDelayed(this,0);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnPause = (Button)findViewById(R.id.btnPause);
        btnLap = (Button)findViewById(R.id.btnLap);
        txtTimer = (TextView)findViewById(R.id.timerValue);
        container = (LinearLayout)findViewById(R.id.container);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);

            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSwapBuff+=timeInMilliSeconds;
                customHandler.removeCallbacks(updateTimerThread);
            }
        });

        btnLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View addView = inflater.inflate(R.layout.row,null);
                TextView txtValue = (TextView) addView.findViewById(R.id.txtContent);
                txtValue.setText(txtTimer.getText());
                container.addView(addView);
            }
        });


    }

    protected void goToClock(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /*protected void goToReminder(View view){

        Intent  intent = new Intent(this, ReminderActivity.class);
        startActivity(intent);

    }*/

    protected void goToAlarm(View view){

        Intent  intent = new Intent(this, AlarmActivity.class);
        startActivity(intent);

    }

}
