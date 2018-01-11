package com.example.choubao.androidtutorial;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CaptureActivity extends Activity {

    private Button btnCapture;
    private ImageView ivCaptureImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

        btnCapture= (Button) findViewById(R.id.btnCapture);
        ivCaptureImg= (ImageView) findViewById(R.id.ivCaptureImg);

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent capIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(capIntent,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            if (resultCode == RESULT_OK) {
                //显示图片到ImageView上
                Bitmap bitmap=(Bitmap) data.getExtras().get("data");
                ivCaptureImg.setImageBitmap(bitmap);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(CaptureActivity.this,"拍照未成功",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
