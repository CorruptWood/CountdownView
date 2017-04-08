package com.zdm.lib_countdownview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * ......................我佛慈悲....................
 * ......................_oo0oo_.....................
 * .....................o8888888o....................
 * .....................88" . "88....................
 * .....................(| -_- |)....................
 * .....................0\  =  /0....................
 * ...................___/`---'\___..................
 * ..................' \\|     |// '.................
 * ................./ \\|||  :  |||// \..............
 * .............../ _||||| -卍-|||||- \..............
 * ..............|   | \\\  -  /// |   |.............
 * ..............| \_|  ''\---/''  |_/ |.............
 * ..............\  .-\__  '-'  ___/-. /.............
 * ............___'. .'  /--.--\  `. .'___...........
 * .........."" '<  `.___\_<|>_/___.' >' ""..........
 * ........| | :  `- \`.;`\ _ /`;.`/ - ` : | |.......
 * ........\  \ `_.   \_ __\ /__ _/   .-` /  /.......
 * ....=====`-.____`.___ \_____/___.-`___.-'=====....
 * ......................`=---='.....................
 * <p>
 * ..................佛祖开光 ,永无BUG................
 * <p>
 * <p>
 * <p>
 * Created by zdm on 2017/4/7/0007.
 * <p>
 * 描述: 验证码倒计时
 */

public class CountDownView extends TextView {

    //倒计时  默认30s
    private int count_down_time;
    private int temp_time;
    private String startText;
    private String countDownText;
    private String endText;
    private int startTextColor;
    private int countDownTextColor;
    private int endTextColor;
    private int type;

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CountDownView, defStyleAttr, 0);
        //倒计时
        count_down_time = typedArray.getInteger(R.styleable.CountDownView_count_down_time, 30);
        //文字
        startText = typedArray.getString(R.styleable.CountDownView_start_text);
        countDownText = typedArray.getString(R.styleable.CountDownView_count_down_text);
        endText = typedArray.getString(R.styleable.CountDownView_end_text);
        //颜色
        startTextColor = typedArray.getColor(R.styleable.CountDownView_start_text_color,Color.parseColor("#333333"));
        countDownTextColor = typedArray.getColor(R.styleable.CountDownView_count_down_text_color, startTextColor);
        endTextColor = typedArray.getColor(R.styleable.CountDownView_end_text_color, startTextColor);
        //秒数不足2位时 是否补0 0表示不补齐
        type = typedArray.getInt(R.styleable.CountDownView_type, 0);


        if(TextUtils.isEmpty(startText)){
            String text = this.getText().toString();
            startText=text;
        }else {
            setText(startText);
        }

        if(TextUtils.isEmpty(countDownText)){
            countDownText=context.getString(R.string.count_down_text);
        }

        if(TextUtils.isEmpty(endText)){
            endText=context.getString(R.string.end_text);;
        }

        setTextColor(startTextColor);

        //释放资源
        typedArray.recycle();
    }

    public void startCounDownTime() {
        handler.obtainMessage().sendToTarget();
        temp_time=count_down_time;
    }

    public void stopCounDownTime() {
        temp_time=0;
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handler.postDelayed(countDownRunnable,0);
            setClickable(false);
        }
    };

    Runnable countDownRunnable=new Runnable() {
        @Override
        public void run() {
            if(temp_time<=1){
                handler.removeCallbacks(this);
                setClickable(true);
                setText(endText);
                setTextColor(endTextColor);
                return;
            }
            temp_time--;
            if(type==0){
                setText(String.format(countDownText,String.valueOf(temp_time)));
            }else {
                setText(String.format(countDownText,String.valueOf((
                        temp_time<10?"0"+temp_time:temp_time))));
            }
            setTextColor(countDownTextColor);
            handler.postDelayed(this,1000);
        }
    };
}
