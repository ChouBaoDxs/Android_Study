package com.example.choubao.androidtutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by choubao on 16/12/1.
 */

public class ActivityLifeCycle extends Activity {

    private EditText etMessageBox;
    private Button btnSendMessage;

    private int count=0;
    private static final int RC_DATA_ACTIVITY=100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        if (savedInstanceState != null) {
            count=savedInstanceState.getInt("count");
            Toast.makeText(this,"count="+count,Toast.LENGTH_SHORT).show();
        }

        etMessageBox= (EditText) findViewById(R.id.etMessageBox);
        btnSendMessage= (Button) findViewById(R.id.btnSendMessage);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strMsg=etMessageBox.getText().toString();
                Intent intent=new Intent(ActivityLifeCycle.this,DataActivity.class);
                intent.putExtra("joke",strMsg);
                startActivityForResult(intent,RC_DATA_ACTIVITY);

/*                Intent intent=new Intent();
                //通过Intent传值
//                intent.putExtra("msg",strMsg);//Intent传送数据的方式也是键值对，第一个参数为名称，第二个参数为值
//                intent.setClass(ActivityLifeCycle.this,DataActivity.class);
//                startActivity(intent);

                //ActivityA->ActivityB->ActivityC
                //Activity->ActivityB ActivityC  通过Bundle传值
                Bundle bundle=new Bundle();
                bundle.putInt("year",2016);
                bundle.putDouble("score",100.0);
                bundle.putString("msg",strMsg);
                intent.putExtras(bundle);
                intent.setClass(ActivityLifeCycle.this,DataActivity.class);
                startActivity(intent);*/
            }
        });
        Log.v("ActivityLifeCycle","onCreate执行完毕");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ActivityLifeCycle","onCreate或onRestart执行完后会执行我——onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        count--;
        Log.e("ActivityLifeCycle","onResume执行了,我在onStart后面");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("ActivityLifeCycle","onPause执行，选择Activity处于暂停");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.wtf("ActivityLifeCycle","onStop执行了，现在Activity看不见了");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("ActivityLifeCycle","onRestart执行了，我在onStop后执行（按下返回键之后）");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("ActivityLifeCycle","onDestroy执行了，现在Activity被系统杀死了");
    }

    //在onDestroy()之前会执行         Bundle保存数据的方式是键值对
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count",count);//这里就保存了一个键值对
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //处理逻辑
        switch(requestCode){
            case RC_DATA_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String strResult=data.getStringExtra("manian");
                    Toast.makeText(ActivityLifeCycle.this,strResult,Toast.LENGTH_SHORT).show();
                } else if (resultCode==RESULT_CANCELED) {
                    Toast.makeText(ActivityLifeCycle.this,"未能正确返回结果",Toast.LENGTH_SHORT).show();
                }
                break;
            default :

                break;
        }
    }
}
