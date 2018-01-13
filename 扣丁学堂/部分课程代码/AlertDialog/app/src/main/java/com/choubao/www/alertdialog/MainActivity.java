package com.choubao.www.alertdialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //自定义对话框
    public void dialogClick5(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登陆对话框");
        builder.setIcon(R.mipmap.ic_launcher);

//        builder.setView(R.layout.login_layout);//API21 Android5.0支持

        //实例化布局文件
        final View view=getLayoutInflater().inflate(R.layout.login_layout,null);
        builder.setView(view);

        //正面的按钮
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText editText_username= (EditText) view.findViewById(R.id.editText_username);
                EditText editText_password= (EditText) view.findViewById(R.id.editText_password);

                String username=editText_username.getText().toString();
                String password=editText_password.getText().toString();

                Toast.makeText(MainActivity.this,username+"--"+password, Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });

        //负面的按钮
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }

    String result="民法";
    //单项选择列表对话框
    public void dialogClick4(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择一个部门法：");
        final String[] items = {"民法", "刑法", "行政法", "三国法", "商经法", "理论法", "民诉", "刑诉"};
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                result=items[i];
                Toast.makeText(MainActivity.this,"你选择了"+items[i],Toast.LENGTH_SHORT).show();
            }
        });

        //正面的按钮
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,result, Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });

        //负面的按钮
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    //多项选择对话框
    public void dialogClick3(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择一个部门法：");
        final ArrayList<String> list = new ArrayList<String>();
        final String[] items = {"民法", "刑法", "行政法", "三国法", "商经法", "理论法", "民诉", "刑诉"};
        //boolean[] boolean1={true,true,true,true,true,true,true,true};
        //builder.setMultiChoiceItems(items,boolean1, new DialogInterface.OnMultiChoiceClickListener()

        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                if (b) {
                    list.add(items[i]);
                } else {
                    list.remove(items[i]);
                }
            }
        });

        //正面的按钮
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, list.toString(), Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });

        //负面的按钮
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    //列表选择对话框
    public void dialogClick2(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择一个部门法：");
        final String[] items = {"民法", "刑法", "行政法", "三国法", "商经法", "理论法", "民诉", "刑诉"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "你选择的是：" + items[i], Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    //提示对话框一:普通的
    public void dialogClick1(View v) {
        //创建一个提示对话框的构造器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("请问你有女朋友吗？");
        builder.setIcon(R.mipmap.ic_launcher);

        //正面的按钮
        builder.setPositiveButton("有", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "你可以滚了", Toast.LENGTH_SHORT).show();
            }
        });

        //负面的按钮
        builder.setNegativeButton("没有", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "哈哈哈，你条单身狗", Toast.LENGTH_SHORT).show();
            }
        });

        //中立的按钮
        builder.setNeutralButton("你猜啊(退出对话框)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "我不说话", Toast.LENGTH_SHORT).show();
            }
        });

//        AlertDialog dialog=builder.create();
//        dialog.show();
        builder.show();
    }

}
