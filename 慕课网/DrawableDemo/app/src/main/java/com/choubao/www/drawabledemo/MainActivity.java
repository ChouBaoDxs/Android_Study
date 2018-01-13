package com.choubao.www.drawabledemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button startBitmapDrawable,startLayerDrawable,startStateListDrawable,
            startLevelListDrawable,startTransitionDrawable,startInsertDrawable,
            startClipDrawable,startCustomDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBitmapDrawable= (Button) findViewById(R.id.startBitmapDrawable);
        startLayerDrawable= (Button) findViewById(R.id.startLayerDrawable);
        startStateListDrawable= (Button) findViewById(R.id.startStateListDrawable);
        startLevelListDrawable= (Button) findViewById(R.id.startLevelListDrawable);
        startTransitionDrawable= (Button) findViewById(R.id.startTransitionDrawable);
        startInsertDrawable= (Button) findViewById(R.id.startInsertDrawable);
        startClipDrawable= (Button) findViewById(R.id.startClipDrawable);
        startCustomDrawable= (Button) findViewById(R.id.startCustomDrawable);
        startBitmapDrawable.setOnClickListener(this);
        startLayerDrawable.setOnClickListener(this);
        startStateListDrawable.setOnClickListener(this);
        startLevelListDrawable.setOnClickListener(this);
        startTransitionDrawable.setOnClickListener(this);
        startInsertDrawable.setOnClickListener(this);
        startClipDrawable.setOnClickListener(this);
        startCustomDrawable.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.startBitmapDrawable:
                startActivity(new Intent(MainActivity.this,BitmapDrawableActivity.class));
                break;
            case R.id.startLayerDrawable:
                startActivity(new Intent(MainActivity.this,LayerDrawableActivity.class));
                break;
            case R.id.startStateListDrawable:
                startActivity(new Intent(MainActivity.this,StateListActivity.class));
                break;
            case R.id.startLevelListDrawable:
                startActivity(new Intent(MainActivity.this,LevelListDrawableActivity.class));
                break;
            case R.id.startTransitionDrawable:
                startActivity(new Intent(MainActivity.this,TransitionDrawableActivity.class));
                break;
            case R.id.startInsertDrawable:
                startActivity(new Intent(MainActivity.this,InsertDrawableActivity.class));
                break;
            case R.id.startClipDrawable:
                startActivity(new Intent(MainActivity.this,ClipDrawableActivity.class));
                break;
            case R.id.startCustomDrawable:
                startActivity(new Intent(MainActivity.this,CustomDrawableActivity.class));
                break;
        }
    }
}
