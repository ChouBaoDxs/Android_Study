package com.choubao.www.checkbox_radiobutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    private CheckBox cb_mf,cb_xf,cb_xzf;
    private RadioButton rb_male,rb_female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb_mf= (CheckBox) findViewById(R.id.checkBox_minfa);
        cb_xf= (CheckBox) findViewById(R.id.checkBox_xingfa);
        cb_xzf= (CheckBox) findViewById(R.id.checkBox_xingzhengfa);

        cb_mf.setOnCheckedChangeListener(this);
        cb_xf.setOnCheckedChangeListener(this);
        cb_xzf.setOnCheckedChangeListener(this);

        rb_male= (RadioButton) findViewById(R.id.radioButton_male);
        rb_female= (RadioButton) findViewById(R.id.radioButton_female);

        rb_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(MainActivity.this,"男 isChecked="+b,Toast.LENGTH_SHORT).show();
            }
        });

        rb_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Toast.makeText(MainActivity.this,"女 isChecked="+b,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {



        switch(compoundButton.getId()){
            case R.id.checkBox_minfa:
                if(b) Toast.makeText(this,"你选择了民法",Toast.LENGTH_SHORT).show();
                else Toast.makeText(this,"你取消了民法",Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkBox_xingfa:
                if(b) Toast.makeText(this,"你选择了刑法",Toast.LENGTH_SHORT).show();
                else Toast.makeText(this,"你取消了刑法",Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkBox_xingzhengfa:
                if(b) Toast.makeText(this,"你选择了行政法",Toast.LENGTH_SHORT).show();
                else Toast.makeText(this,"你取消了行政法",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
