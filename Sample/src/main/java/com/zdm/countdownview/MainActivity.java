package com.zdm.countdownview;

import android.os.Bundle;
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

        //没设置倒计时过程中可点击  该方法不生效
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
                if(!countDown1.isCountDownStatus()){
                    countDown1.startCounDownTime();
                    Toast.makeText(MainActivity.this,"countDown2开启了countDown1的倒计时",Toast.LENGTH_SHORT).show();
                }
            }
        });

        countDown3.startCounDownTime();

        countDown3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"countDown3被点击了",Toast.LENGTH_SHORT).show();

                if(countDown1.isCountDownStatus()){
                    Toast.makeText(MainActivity.this,"countDown1正在倒计时,并且被countDown3停止了",Toast.LENGTH_SHORT).show();
                    countDown1.stopCounDownTime();
                    countDown2.startCounDownTime();
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDown1.stopCounDownTime();
        countDown2.stopCounDownTime();
    }
}
