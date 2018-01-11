package com.choubao.barcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.choubao.androidtutorial.R;

public class ScanBarcodeActivity extends Activity {
    private Button btnScanBarcode;
    private TextView tvScanBarcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);

        btnScanBarcode= (Button) findViewById(R.id.btnScanBarcode);
        tvScanBarcode= (TextView) findViewById(R.id.tvScanBarcode);

        btnScanBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用Barcode Scanner扫描条形码
                IntentIntegrator intentIntegrator=new IntentIntegrator(ScanBarcodeActivity.this);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanResult=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (scanResult != null) {
            //处理扫描结果
            tvScanBarcode.setText(scanResult.toString());
        } else {
            //扫描识别未成功
            Toast.makeText(this, "扫描识别未成功", Toast.LENGTH_SHORT).show();
        }
    }
}
