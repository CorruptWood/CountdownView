package com.zdm.demo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.zdm.countdownview.CountDownView;
import com.zdm.countdownview.OnCountDownStartListener;
import com.zdm.countdownview.OnCountDownStopListener;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends AutoLayoutActivity {

    @InjectView(R.id.count_down1)
    CountDownView countDown1;
    @InjectView(R.id.count_down2)
    CountDownView countDown2;
    @InjectView(R.id.count_down3)
    CountDownView countDown3;
    @InjectView(R.id.count_down4)
    CountDownView countDown4;
    @InjectView(R.id.count_down5)
    CountDownView countDown5;
    @InjectView(R.id.loger)
    TextView loger;
    @InjectView(R.id.clean)
    TextView clean;

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        //让textView 滚动起来
        loger.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        //开始倒计时
        countDown1.setCountDownTime(30);
        countDown2.setCountDownTime(20);
        countDown3.setCountDownTime(40);
        countDown4.startCounDownTime();

        countDown1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDown1.startCounDownTime();
            }
        });

        countDown2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDown2.startCounDownTime();
            }
        });

        countDown3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDown3.startCounDownTime();
            }
        });

        countDown5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDown5.startCounDownTime();
            }
        });


        countDown4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomToast.showToast(MainActivity.this, "countDown4当前的倒计时状态为："
                        + countDown4.isCountDownStatus() + "倒计时时间剩余：" + countDown4.getCountDownTime());
                setLoger("countDown4当前的倒计时状态为："
                        + countDown4.isCountDownStatus() + "倒计时时间剩余：" + countDown4.getCountDownTime());
            }
        });


        //倒计时开始回调
        countDown1.setOnStartListener(new OnCountDownStartListener() {
            @Override
            public void OnCountDownStart() {
                setLoger("countDown1倒计时开始了");
            }
        });

        countDown2.setOnStartListener(new OnCountDownStartListener() {
            @Override
            public void OnCountDownStart() {
                setLoger("countDown2倒计时开始了");
            }
        });

        countDown3.setOnStartListener(new OnCountDownStartListener() {
            @Override
            public void OnCountDownStart() {
                setLoger("countDown3倒计时开始了");
            }
        });

        countDown4.setOnStartListener(new OnCountDownStartListener() {
            @Override
            public void OnCountDownStart() {
                setLoger("countDown4倒计时开始了");
            }
        });

        countDown5.setOnStartListener(new OnCountDownStartListener() {
            @Override
            public void OnCountDownStart() {
                setLoger("countDown5倒计时开始了");
            }
        });



        //倒计时结束回调
        countDown1.setOnStopListener(new OnCountDownStopListener() {
            @Override
            public void OnCountDownStop() {
                setLoger("countDown1倒计时结束了");
            }
        });

        countDown2.setOnStopListener(new OnCountDownStopListener() {
            @Override
            public void OnCountDownStop() {
                setLoger("countDown2倒计时结束了");
            }
        });

        countDown3.setOnStopListener(new OnCountDownStopListener() {
            @Override
            public void OnCountDownStop() {
                setLoger("countDown3倒计时结束了");
            }
        });

        countDown4.setOnStopListener(new OnCountDownStopListener() {
            @Override
            public void OnCountDownStop() {
                setLoger("countDown4倒计时结束了");
            }
        });

        countDown5.setOnStopListener(new OnCountDownStopListener() {
            @Override
            public void OnCountDownStop() {
                setLoger("countDown5倒计时结束了");
            }
        });
    }

    //设置日志文件
    StringBuffer buf ;

    private void setLoger(String text) {
        String oldText = loger.getText().toString();
        buf = new StringBuffer(oldText.length());
        buf.append(oldText);
        buf.append("\n");
        buf.append("=======================================");
        buf.append("\n");
        buf.append(text);
        loger.setText(buf);

    }

    /**
     * 页面销毁时停止还在进行中的倒计时
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDown1 != null && countDown1.isCountDownStatus())
            countDown1.stopCounDownTime();
        if (countDown2 != null && countDown2.isCountDownStatus())
            countDown2.stopCounDownTime();
        if (countDown3 != null && countDown3.isCountDownStatus())
            countDown3.stopCounDownTime();
        if (countDown4 != null && countDown4.isCountDownStatus())
            countDown4.stopCounDownTime();
        if (countDown5 != null && countDown5.isCountDownStatus())
            countDown5.stopCounDownTime();
    }

    //清除日志
    @OnClick(R.id.clean)
    public void onViewClicked() {
        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("清除已打印的日志信息吗？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loger.setText("");
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }
}
