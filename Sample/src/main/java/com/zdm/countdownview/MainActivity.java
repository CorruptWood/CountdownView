package com.zdm.countdownview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zdm.lib_countdownview.CountDownView;


public class MainActivity extends AppCompatActivity {

    private CountDownView countDown1;
    private CountDownView countDown2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countDown1 = ((CountDownView) findViewById(R.id.count_down1));
        countDown2 = ((CountDownView) findViewById(R.id.count_down2));

        countDown1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDown1.startCounDownTime();
            }
        });

        countDown2.startCounDownTime();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDown1.stopCounDownTime();
        countDown2.stopCounDownTime();
    }
}
