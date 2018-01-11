package com.example.choubao.imageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button btnNext;
    private ImageView ivImage;

    private int[] images={R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4};
    private int imageIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNext=(Button) findViewById(R.id.btnNext);
        ivImage=(ImageView) findViewById(R.id.ivImage);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageIndex++;
                if(imageIndex>3){
                    imageIndex=0;
                }
                ivImage.setImageResource(images[imageIndex]);
            }
        });
    }
}
