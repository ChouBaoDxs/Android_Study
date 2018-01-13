package com.choubao.www.canvas_drawable;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private SurfaceView surfaceView;
    private SurfaceHolder holder;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        holder=surfaceView.getHolder();
        holder.addCallback(this);
        holder.setFixedSize(320,220);//设置分辨率，不设置为视频的默认
    }

    public void playClick(View v) {
        mp.start();
    }

    public void pauseClick(View v) {
        mp.pause();
    }

    public void stopClick(View v) {
        mp.stop();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mp=new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_RING);
        mp.setDisplay(holder);
        String path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)+"/名字.mp4";//这里是视频文件的路径
        try {
            mp.setDataSource(path);//设置播放视频源
            mp.prepare();//预备
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
            }
        }
    }
}
