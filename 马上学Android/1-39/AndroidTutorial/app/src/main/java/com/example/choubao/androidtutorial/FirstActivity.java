package com.example.choubao.androidtutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends Activity {

    private Button btnStartSecondActivity;

    private Button btnGetMoney;
    private TextView tvGetMoney;
    private Button btnLoseMoney;
    private EditText etGoalMoney;
    private int money=0;

    private RadioGroup rgSuvery;
    private CheckBox cbLOL,cbSleep,cbMakeMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btnStartSecondActivity=(Button) findViewById(R.id.btnStartSecondActivity);


        btnGetMoney= (Button) findViewById(R.id.btnGetMoney);
        tvGetMoney= (TextView) findViewById(R.id.tvGetMoney);
        btnLoseMoney= (Button) findViewById(R.id.btnLoseMoney);
        etGoalMoney= (EditText) findViewById(R.id.etGoalMoney);

        rgSuvery=(RadioGroup) findViewById(R.id.rgSuvery);

        cbLOL=(CheckBox) findViewById(R.id.cbLOL);
        cbSleep=(CheckBox) findViewById(R.id.cbSleep);
        cbMakeMoney=(CheckBox) findViewById(R.id.cbMakeMoney);

        btnGetMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strInputMoney=etGoalMoney.getText().toString().trim(); //trim()的作用是删除字符串首尾的空格
                int iMoney=0;
                iMoney=Integer.parseInt(strInputMoney);
                if(iMoney==money){
                    Toast.makeText(FirstActivity.this,"你经过努力已经完成目标！",Toast.LENGTH_SHORT).show();
                }else {
                    money++;
                }
                tvGetMoney.setText("哈哈，我通过点击鼠标轻易赚了" + money + "元钱");
            }
        });

        btnLoseMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(money==0) {
                    Toast.makeText(FirstActivity.this,"现在已经是穷光蛋，不要再按了！",Toast.LENGTH_LONG).show();//消息框
                }else{
                    money--;
                    tvGetMoney.setText("哈哈，我通过点击鼠标轻易赚了" + money + "元钱");
                }
            }
        });

        rgSuvery.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbSoHappy:
                        Toast.makeText(FirstActivity.this,"你态度很好，继续保持",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbNoHappy:
                        Toast.makeText(FirstActivity.this,"Really?推荐你每天去看CCTV",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbDong:
                        Toast.makeText(FirstActivity.this,"你时来捣乱的吧！",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        cbLOL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(FirstActivity.this,"少年，没事多看看书，别整天LOL！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        cbSleep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(FirstActivity.this,"活着何须多睡，死后自然长眠！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        cbMakeMoney.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(FirstActivity.this,"年轻人，我看好你！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnStartSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动第二个Activity
                Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
