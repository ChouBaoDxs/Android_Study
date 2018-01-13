package com.choubao.www.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivityResult extends AppCompatActivity {

    private static final int REQUESTCODE_1 = 0x1;
    private EditText editText_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_result);
        editText_number= (EditText) findViewById(R.id.editText_number);
    }

    public void selectClick(View v) {
        Intent intent=new Intent(this,PhoneNumberListActivity.class);
        //intent，请求编码（用于区分打开的是哪个Activity）
        startActivityForResult(intent,REQUESTCODE_1);
    }

    //重写该方法来处理返回的结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUESTCODE_1 && resultCode == RESULT_OK) {
            String number=data.getStringExtra("number");
            editText_number.setText(number);
        }
    }

    //拨打电话
    public void callClick(View v) {
        String number=editText_number.getText().toString();
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number));
        startActivity(intent);
    }
}
