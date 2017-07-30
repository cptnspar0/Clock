package com.example.cptnspar0.clock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by cptnspar0 on 30/7/17.
 */

public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("In the receiver", " ");

        String get_your_string = intent.getExtras().getString("extra");

        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        service_intent.putExtra("extra", get_your_string);

        context.startService(service_intent);


    }
}
