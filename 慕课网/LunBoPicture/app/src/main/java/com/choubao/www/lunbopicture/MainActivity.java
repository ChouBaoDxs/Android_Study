package com.choubao.www.lunbopicture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.choubao.www.lunbopicture.view.ImageBarnnerFrameLayout;
import com.choubao.www.lunbopicture.view.ImageBarnnerViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ImageBarnnerFrameLayout.FrameLayoutListener {

    private ImageBarnnerViewGroup mGroup;

    private ImageBarnnerFrameLayout mImageBarnnerFrameLayout;

    private int[] ids = new int[]{
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //我们需要得到当前手机的宽度
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        Constant.WIDTH=width;
        int height=dm.heightPixels;

//        mGroup= (ImageBarnnerViewGroup) findViewById(R.id.image_group);
//
//        for (int i = 0; i < ids.length; i++) {
//            ImageView iv=new ImageView(this);
//            //让图片铺满
//            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            iv.setLayoutParams(new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//            iv.setImageResource(ids[i]);
//            mGroup.addView(iv);
//        }
//        mGroup.setListener(this);


        mImageBarnnerFrameLayout= (ImageBarnnerFrameLayout) findViewById(R.id.mImageBarnnerFrameLayout);
        mImageBarnnerFrameLayout.setFrameLayoutListener(this);
        List<Bitmap> list=new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),ids[i]);
            list.add(bitmap);
        }
        mImageBarnnerFrameLayout.addBitmaps(list);

    }

    @Override
    public void clickImageIndex(int pos) {
        Toast.makeText(MainActivity.this,"pos="+pos,Toast.LENGTH_SHORT).show();
    }
}
