package com.choubao.www.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = (TextView) view;
                Toast.makeText(MainActivity.this, tv.getText(), Toast.LENGTH_SHORT).show();

                if (i == 0) {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                } else if (i == 1) {
                    Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(intent);
                } else if (i == 2) {
                    Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                    startActivity(intent);
                } else if (i == 3) {
                    Intent intent = new Intent(MainActivity.this, MainActivity5.class);
                    startActivity(intent);
                } else if (i == 4) {
                    Intent intent = new Intent(MainActivity.this, MainActivity6.class);
                    startActivity(intent);
                }
            }
        });
    }
}
