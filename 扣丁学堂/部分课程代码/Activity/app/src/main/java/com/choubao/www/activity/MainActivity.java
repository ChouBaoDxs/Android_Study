package com.choubao.www.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 *
 * Activity的三个状态和七大生命周期方法
 */

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    //Activity创建时第一个调用的方法，通常我们在该方法中加载布局文件，初始化UI组件，事件注册等
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("MainActivity-onCreate");

        editText= (EditText) findViewById(R.id.editText_info);
    }

    //启动轻量级存储的Activity
    public void startSharedPreferencesActivity(View v) {
        startActivity(new Intent(this,SharedPreferencesActivity.class));
    }
    //启动屏幕翻转改变的Activity
    public void startScreenChangeActivity(View v) {
        startActivity(new Intent(this,ScreenChangeActivity.class));
    }
    //启动屏幕翻转的Activity
    public void startScreenOritationActivity(View v) {
        startActivity(new Intent(this,ScreenOrientationActivity.class));
    }
    //启动结果传递的Activity
    public void startMainActivityResult(View v) {
        startActivity(new Intent(this,MainActivityResult.class));
    }
    //启动第二个Activity，并且使用Parcelable传递对象，这种方式是推荐的方式，效率高
    public void sendObject2Click(View v) {
        Dog dog=new Dog();
        dog.name="汪汪";
        dog.age=1;
        dog.type="萨摩耶";

        Intent intent=new Intent(this,Main2Activity.class);
        intent.putExtra("dog",dog);


        Cat cat=new Cat();
        cat.name="小叮当";
        cat.age=100;
        cat.type="机器猫";
        intent.putExtra("cat",cat);
        String info=editText.getText().toString();
        //封装要传递的数据
        Bundle data=new Bundle();
        data.putString("info",info);
        intent.putExtra("data",data);
        startActivity(intent);
    }
    //启动第二个Activity，并且使用Serializable(序列化方式)传递对象，但是序列化消耗的性能较多
    public void sendObjectClick(View v) {
        //要传递的对象
        Cat cat=new Cat();
        cat.name="小叮当";
        cat.age=100;
        cat.type="机器猫";

        Intent intent=new Intent(this,Main2Activity.class);
        intent.putExtra("cat",cat);


        String info=editText.getText().toString();
        //封装要传递的数据
        Bundle data=new Bundle();
        data.putString("info",info);
        intent.putExtra("data",data);
        startActivity(intent);
    }
    //启动第二个Activity，并且传递数据
    public void sendInfo(View v) {
        //创建一个意图(想干什么) 参数：上下文,要跳转的组件的字节码
        Intent intent=new Intent(this,Main2Activity.class);

        String info=editText.getText().toString();

        //封装要传递的数据
        Bundle data=new Bundle();
        data.putString("info",info);
        intent.putExtra("data",data);

        //或者直接intent.put
//        intent.putExtra("info",info);
//        intent.putExtra("age",18);
        startActivity(intent);
    }

    //在onCreat方法之后调用，用于显示界面，但用户还不能进行交互
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("MainActivity-onStart");
    }

    //在onStart方法后调用，该方法执行完成后，用户可以进行交互，当前Activity进入resumed（运行）状态
    //当一个paused状态的activty被重新返回时，会再次调用该方法，让Activity进入resumed（运行）状态
    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("MainActivity-onResume");
    }

    //当其它Activity（透明或窗口模式）进入时，该方法会被调用，让当前Activity进入paused（暂停）状态
    //当前Activity还可见但不可交互，如果其它更高优先级的APP需要内存时，当前Activity可能会被kill（销毁）
    //当前Activity被返回时会调用onResume方法
    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("MainActivity-onPause");
    }


    //当其它Activity完全覆盖该Activity时，会调用该方法，当前Activity进入stopped（停止）状态
    //Activity不可见也不可交互，如果其它更高优先级的APP需要内存时，当前Activity可能会被kill（销毁）
    //当前Activity被返回时会调用onRestart方法
    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("MainActivity-onStop");
    }

    //当一个stopped状态的Activity被返回时调用，之后再调用onResume方法进入运行状态
    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("MainActivity-onRestart");
    }

    //当前Activity被销毁时调用，通常在该方法中释放资源，当前Activity被kill
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("MainActivity-onDestroy");
    }


}
