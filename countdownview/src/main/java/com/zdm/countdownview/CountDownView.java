package com.zdm.countdownview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
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
    private int countDownTime;
    private int tempTime;
    private String startText;
    private String countDownText;
    private String endText;
    private int startTextColor;
    private int countDownTextColor;
    private int endTextColor;
    private int type;
    private OnCountDownStopListener stopListener;
    private OnCountDownStartListener startListener;
    //倒计时的状态
    private boolean countDownStatus = false;
    private int countDownTimeColor;


    public void setOnStartListener(OnCountDownStartListener startListener) {
        this.startListener = startListener;
    }

    public void setOnStopListener(OnCountDownStopListener listener) {
        this.stopListener = listener;
    }

    public boolean isCountDownStatus() {
        return countDownStatus;
    }

    public void setCountDownTime(int time) {
        this.countDownTime = time;
    }

    public void setType(int type) {
        this.type = type;
    }

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
        countDownTime = typedArray.getInteger(R.styleable.CountDownView_count_down_time, 30);
        //文字
        startText = typedArray.getString(R.styleable.CountDownView_start_text);
        countDownText = typedArray.getString(R.styleable.CountDownView_count_down_text);
        endText = typedArray.getString(R.styleable.CountDownView_end_text);
        //颜色
        startTextColor = typedArray.getColor(R.styleable.CountDownView_start_text_color, Color.parseColor("#333333"));
        countDownTextColor = typedArray.getColor(R.styleable.CountDownView_count_down_text_color, startTextColor);
        endTextColor = typedArray.getColor(R.styleable.CountDownView_end_text_color, startTextColor);
        countDownTimeColor = typedArray.getColor(R.styleable.CountDownView_count_down_time_color, countDownTextColor);
        //秒数不足2位时 是否补0 0表示不补齐
        type = typedArray.getInt(R.styleable.CountDownView_type, 0);

        //释放资源
        typedArray.recycle();

        initView();
    }

    private Handler handler = new Handler();
    private final Runnable countDownRunnable = new Runnable() {
        @Override
        public void run() {
            if (countDownStatus) {
                tempTime--;
                if (tempTime <= 0) {
                    stopCounDownTime();
                    return;
                }
                setCountDownViewText();
                Log.e("-----------------","tempTime："+tempTime);
                handler.postDelayed(this, 1000);
            } else {
                handler.removeCallbacks(this);
                if (stopListener != null) {
                    stopListener.OnCountDownStop();
                }
            }
        }
    };

    public void setCountDownViewText() {
        String temp;
        if (type == 0) {
            temp = String.valueOf(tempTime);
        } else {
            temp = String.valueOf(tempTime < 10 ? "0" + tempTime : tempTime);
        }
        String string = String.format(countDownText, temp);
        if (countDownTextColor != countDownTimeColor) {
            SpannableString format = new SpannableString(string);
            //倒计时的索引位置
            int indexOf = string.indexOf(temp);
            format.setSpan(new ForegroundColorSpan(countDownTextColor), 0, format.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            format.setSpan(new ForegroundColorSpan(countDownTimeColor), indexOf, indexOf + temp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(format);
        } else {
            setTextColor(countDownTextColor);
            setText(string);
        }
    }


    /**
     * 开始倒计时
     */
    public void startCounDownTime() {
        countDownStatus = true;
        tempTime = countDownTime;
        initView();
        handler.removeCallbacks(countDownRunnable);
        handler.postDelayed(countDownRunnable, 0);
        if (startListener != null)
            startListener.OnCountDownStart();
    }

    private void initView() {
        countDownTime = countDownTime > 99 ? 99 : (countDownTime < 1 ? 30 : countDownTime);
        if (TextUtils.isEmpty(startText)) {
            String text = this.getText().toString();
            startText = text;
        } else {
            setText(startText);
        }

        if (TextUtils.isEmpty(countDownText)) {
            countDownText = getContext().getString(R.string.count_down_text);
        }

        if (TextUtils.isEmpty(endText)) {
            endText = startText;
        }

        Log.e("-----------------","startText:"+startText+";countDownText:"+countDownText+";endText:"+endText);
        setTextColor(startTextColor);
    }

    /**
     * 结束倒计时
     */
    public void stopCounDownTime() {
        countDownStatus = false;
        setText(endText);
        setTextColor(endTextColor);
    }


    /**
     * 获取当前倒计时的时间
     */
    public int getCountDownTime() {
        return tempTime;
    }
}
