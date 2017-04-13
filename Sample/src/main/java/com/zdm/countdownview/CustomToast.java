package com.zdm.countdownview;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by zdm on 2016/9/14/0014.
 */
public class CustomToast {
    private static Toast mToast;
    private static int duration=5000;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
        }
    };

    public static void showToast(Context context,String text) {

        mHandler.removeCallbacks(r);
        if (mToast != null)
            mToast.setText(text);
        else
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        mHandler.postDelayed(r, duration);

        mToast.show();
    }
}
