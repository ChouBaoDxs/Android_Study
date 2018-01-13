package com.choubao.www.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button insert,delete,update,findById,findAll;
    private DatabaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insert= (Button) findViewById(R.id.insert);
        delete= (Button) findViewById(R.id.delete);
        update= (Button) findViewById(R.id.update);
        findById= (Button) findViewById(R.id.findById);
        findAll= (Button) findViewById(R.id.findAll);

        dbAdapter=new DatabaseAdapter(this);

        insert.setOnClickListener(this);
        delete.setOnClickListener(this);
        update.setOnClickListener(this);
        findById.setOnClickListener(this);
        findAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.insert:
                Dog dog1=new Dog("doge",5);
                dbAdapter.add(dog1);
                break;
            case R.id.delete:
                dbAdapter.delete(1);
                break;
            case R.id.update:
                Dog dog3=new Dog("dwangwang",5);
                dbAdapter.update(dog3);
                break;
            case R.id.findById:
                Dog dog4=dbAdapter.findById(1);
                System.out.println(dog4);
                break;
            case R.id.findAll:
                ArrayList<Dog> dogs=dbAdapter.findAll();
                for (Dog d : dogs) {
                    System.out.println(d.toString());
                }
                break;
        }
    }
}
