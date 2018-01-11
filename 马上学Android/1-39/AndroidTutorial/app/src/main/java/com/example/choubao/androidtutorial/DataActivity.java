package com.example.choubao.androidtutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DataActivity extends Activity {

    private Button btnReturnActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        btnReturnActivity= (Button) findViewById(R.id.btnReturnActivity);
        btnReturnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strGetMsg= getIntent().getStringExtra("joke");
                if (strGetMsg.equals("joke")) {
                    Intent intentData = new Intent();
                    intentData.putExtra("manian", "马云马航马伊琍，失意失联失文章");
                    setResult(RESULT_OK,intentData);
                    finish();
                } else {
                    Intent intentData = new Intent();
                    setResult(RESULT_CANCELED,intentData);
                    finish();
                }
            }
        });
/*        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        //String strGetMsg=intent.getStringExtra("msg");
        String strGetMsg=bundle.getString("msg");
        int year=bundle.getInt("year");
        double score=bundle.getDouble("score");
        //Toast.makeText(this,"消息的内容是："+strGetMsg,Toast.LENGTH_LONG).show();
        Toast.makeText(this,strGetMsg+"   "+year+"   "+score,Toast.LENGTH_LONG).show();*/
    }
}
