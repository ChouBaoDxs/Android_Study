package com.example.choubao.myclock;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by choubao on 16/11/20.
 */

public class PlayAlarmAty extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {

        super.onCreate(savedInstanceState, persistentState);
        System.out.println("setContentView");
        setContentView(R.layout.alarm_player_aty);
        System.out.println("play");
        mp=MediaPlayer.create(this,R.raw.music);
        mp.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
        mp.release();
    }

    private MediaPlayer mp;
}
