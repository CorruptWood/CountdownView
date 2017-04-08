package com.zdm.countdownview;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zdm.lib_countdownview.CountDownView;
import com.zdm.lib_countdownview.OnCountDownStopListener;


public class MainActivity extends AppCompatActivity {

    private CountDownView countDown1;
    private CountDownView countDown2;
    private CountDownView countDown3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countDown1 = ((CountDownView) findViewById(R.id.count_down1));
        countDown2 = ((CountDownView) findViewById(R.id.count_down2));
        countDown3 = ((CountDownView) findViewById(R.id.count_down3));

        countDown1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDown1.startCounDownTime();
            }
        });

        countDown2.startCounDownTime();

        countDown2.setOnStopListener(new OnCountDownStopListener() {
            @Override
            public void OnCountDownStop() {
                if(countDown1.isCountDownStatus()){
                    countDown1.stopCounDownTime();
                    Toast.makeText(MainActivity.this,"我的倒计时停止了",Toast.LENGTH_SHORT).show();
                    SystemClock.sleep(2000);
                    countDown1.startCounDownTime();
                    Toast.makeText(MainActivity.this,"我又开始倒计时了",Toast.LENGTH_SHORT).show();
                }else {
                    countDown1.startCounDownTime();
                    Toast.makeText(MainActivity.this,"我开始倒计时了",Toast.LENGTH_SHORT).show();
                }
            }
        });

        countDown3.startCounDownTime();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDown1.stopCounDownTime();
        countDown2.stopCounDownTime();
    }
}
