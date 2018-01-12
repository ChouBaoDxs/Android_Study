package com.example.choubao.learncontext;                   //context的作用式访问全局的资源 也可以作为全局信息共享的桥梁

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   // private TextView tv;
    private TextView textView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("Main Activity");
        //tv=new TextView(MainActivity.this);
        //tv.setText("Hello Android");
        //tv.setText(R.string.hello_world); //显示string中的hello_word
        //setContentView(tv);

        //System.out.println(getResources().getText(R.string.hello_world));

        /*ImageView iv=new ImageView(this);
        iv.setImageResource(R.mipmap.ic_launcher);//显示mipmap中的ic_launcher
        setContentView(iv);*/
        setContentView(R.layout.main1);


        textView= (TextView) findViewById(R.id.textView);
        editText= (EditText) findViewById(R.id.editText);

        textView.setText("共享的数据是:"+getApp().getTextData());

        findViewById(R.id.btnSaveData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((App)getApplicationContext()).setTextData(editText.getText().toString());

                textView.setText("共享的数据是:"+editText.getText().toString());
            }
        });
    }
    public App getApp(){
        return (App)getApplicationContext();
    }

    public void gotoMain2Activity(View view) {
        startActivity(new Intent(MainActivity.this,Main2.class));
    }
}