package com.example.android.project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.lib.util.DoubleCalculation;
import com.example.android.project.R;

import java.math.BigDecimal;

public class DemoActivity extends AppCompatActivity {

    private static final String TAG = "DemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        Log.e(TAG, "onCreate: ");
        findViewById(R.id.btn_test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DemoActivity.this, DemoActivity.class));
            }
        });

        findViewById(R.id.btn_test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                double d = 23.6d;
//                long lon = 123L;
//                DoubleCalculation.div(d, lon, 2);
//                long l = Math.round(d);
//                Log.e(TAG, "onClick: " + l);
//                long ll = Double.valueOf(d).longValue();
//                Log.e(TAG, "onClick: " + ll);
//                BigDecimal.valueOf()
                Log.e(TAG, "onClick: " + DoubleCalculation.div(1995, 100, 1));
                Log.e(TAG, "onClick: " + div(1995, 100, 1));
                Log.e(TAG, "onClick: " + DoubleCalculation.changeF2Y(1995));
                Log.e(TAG, "onClick: " + changeF2Y(16.66665));

            }
        });

    }

    public static String changeF2Y(double price) {
        return BigDecimal.valueOf(price).divide(new BigDecimal(5)).toString();
    }

    public static double div(double m1, double m2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("Parameter error");
        }
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.divide(p2, scale, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }

}