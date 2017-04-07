package com.zdm.countdownview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zdm.lib_countdownview.CountDownView;


public class MainActivity extends AppCompatActivity {

    private CountDownView countDownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countDownView = ((CountDownView) findViewById(R.id.countdownview));
    }


    public void click(View v){
        countDownView.startCounDownTime();

        //处理具体的业务逻辑
    }
}
