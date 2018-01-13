package com.choubao.www.lunbopicture.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.choubao.www.lunbopicture.Constant;
import com.choubao.www.lunbopicture.R;

import java.util.List;

/**
 * Created by choubao on 17/5/22.
 */

public class ImageBarnnerFrameLayout extends FrameLayout implements ImageBarnnerViewGroup.ImageBarnnerViewGroupChangeListener,ImageBarnnerViewGroup.ImageBarnnerListener{

    private ImageBarnnerViewGroup mImageBarnnerViewGroup;
    private LinearLayout mLinearLayout;

    private FrameLayoutListener mFrameLayoutListener;

    public FrameLayoutListener getFrameLayoutListener() {
        return mFrameLayoutListener;
    }

    public void setFrameLayoutListener(FrameLayoutListener frameLayoutListener) {
        mFrameLayoutListener = frameLayoutListener;
    }

    public ImageBarnnerFrameLayout(Context context) {
        this(context,null);
    }

    public ImageBarnnerFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ImageBarnnerFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initImageBarnnerViewGroup();
        initDotLinearLayout();
    }

    //初始化我们的自定义的图片轮播功能的核心类
    private void initImageBarnnerViewGroup() {
        mImageBarnnerViewGroup = new ImageBarnnerViewGroup(getContext());
        FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mImageBarnnerViewGroup.setLayoutParams(lp);
        mImageBarnnerViewGroup.setImageBarnnerViewGroupChangeListener(this);//设置监听 将Listner传递给FrameLayout
        mImageBarnnerViewGroup.setListener(this);
        addView(mImageBarnnerViewGroup);
    }

    //初始化我们的底部圆点布局
    private void initDotLinearLayout() {
        mLinearLayout = new LinearLayout(getContext());
        FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 40);
        mLinearLayout.setLayoutParams(lp);
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        mLinearLayout.setGravity(Gravity.CENTER);
        mLinearLayout.setBackgroundColor(Color.RED);//透明色
        addView(mLinearLayout);
        FrameLayout.LayoutParams layoutParams= (LayoutParams) mLinearLayout.getLayoutParams();
        layoutParams.gravity=Gravity.BOTTOM;
        mLinearLayout.setLayoutParams(layoutParams);

        //这里有一个知识点：就是在3.0以后，我们使用的是setAlpha()，在3.0之前使用的也是setAlpha()但是调用者不同
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mLinearLayout.setAlpha(0.5f);
        } else {
            mLinearLayout.getBackground().setAlpha(128);
        }

    }

    public void addBitmaps(List<Bitmap> list) {
        for (int i = 0; i < list.size(); i++) {
            Bitmap bitmap=list.get(i);
            addBitmapToImageBarnnerViewGroup(bitmap);
            addDotToLinearLayout();
        }
    }

    private void addDotToLinearLayout() {
        ImageView iv=new ImageView(getContext());
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(5,5,5,5);
        iv.setLayoutParams(lp);
        iv.setImageResource(R.drawable.dot_normal);
        mLinearLayout.addView(iv);
    }

    private void addBitmapToImageBarnnerViewGroup(Bitmap bitmap) {
        ImageView iv=new ImageView(getContext());
        //让图片铺满
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setLayoutParams(new ViewGroup.LayoutParams(Constant.WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT));
        iv.setImageBitmap(bitmap);
        mImageBarnnerViewGroup.addView(iv);
    }

    @Override
    public void selectImage(int index) {
        int count=mLinearLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            ImageView iv= (ImageView) mLinearLayout.getChildAt(i);
            if (i == index) {
                iv.setImageResource(R.drawable.dot_select);
            } else {
                iv.setImageResource(R.drawable.dot_normal);
            }
        }
    }

    @Override
    public void clickImageIndex(int pos) {
        mFrameLayoutListener.clickImageIndex(pos);
    }

    public interface FrameLayoutListener{
        void clickImageIndex(int pos);
    }
}
