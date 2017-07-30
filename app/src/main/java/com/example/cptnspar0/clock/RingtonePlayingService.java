package com.example.cptnspar0.clock;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by cptnspar0 on 30/7/17.
 */

public class RingtonePlayingService extends Service {
    MediaPlayer media_song;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i("LocalServices", "Recieved start id " + startId + ":" + intent);
        String state = intent.getExtras().getString("extra");

        Log.e("Ringtone extra is ", state);
        assert state!=null;
        switch(state){
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                Log.e("Start id is", state);
                break;
            default:
                startId = 0;
                break;
        }
        if(!this.isRunning && startId==1){
            media_song = MediaPlayer.create(this, R.raw.jarviswakeup);
            media_song.start();

            this.isRunning = true;
            this.startId = 0;


        }
        else if(this.isRunning && startId==0){
            media_song.stop();
            media_song.reset();
            this.isRunning = false;
            this.startId= 0;
        }
        else if(!this.isRunning && startId==0){
            this.isRunning = true;
            this.startId = 1;
        }
        else if(this.isRunning && startId==1){

        }
        else{}

        return START_NOT_STICKY;
    }
    public void onDestroy(){
        super.onDestroy();
        this.isRunning =false;
    }


}
