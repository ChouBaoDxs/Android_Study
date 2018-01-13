package com.choubao.www.textview_editview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView_email;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_email= (TextView) findViewById(R.id.textView_email);
        textView_email.setText("826842697@163.com");

        editText= (EditText) findViewById(R.id.editText);
//        editText.setText("");

        //选取文本
        /*Editable editable=editText.getText();
        Selection.setSelection(editable,1,editable.length());*/

        //监听输入
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println("beforeTextChanged:"+charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println("onTextChanged:"+charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                System.out.println("afterTextChanged:"+editable);
            }
        });

        //监听回车和确认键
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Toast.makeText(MainActivity.this,textView.getText().toString(),Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}
