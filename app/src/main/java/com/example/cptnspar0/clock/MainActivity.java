package com.example.cptnspar0.clock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    protected void goToAlarm(View view){

        Intent  intent = new Intent(this, AlarmActivity.class);
        startActivity(intent);

    }

   /* protected void goToReminder(View view){

        Intent  intent = new Intent(this, ReminderActivity.class);
        startActivity(intent);

    } */

    protected void goToStopwatch(View view){

        Intent  intent = new Intent(this, StopwatchActivity.class);
        startActivity(intent);

    }


}
