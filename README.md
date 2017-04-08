# CountdownView
用于单个View显示倒计时的情景，比如获取验证码...

![image](https://github.com/CorruptWood/CountdownView/blob/master/coundown.gif?raw=true)

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        compile 'com.github.CorruptWood:CountdownView:1.0.0'
	}


示例（使用AutoLayout做的适配）：
     
     <com.zdm.lib_countdownview.CountDownView
                android:id="@+id/CountDownView"
                android:layout_width="@dimen/px150"
                android:layout_height="@dimen/px60"
                android:layout_gravity="center"
                android:layout_marginRight="@dimen/px20"
                android:background="@mipmap/virification_bg"
                android:gravity="center"
                android:text="@string/get_virification"
                android:textSize="@dimen/px24"
                app:count_down_time="60"
                app:start_text_color="@color/text_fff"
                app:type="zeroize"/>

属性：
      
      <!--倒计时-->
            <attr name="count_down_time" format="integer"/>
            <!--开始显示的文字-->
            <attr name="start_text" format="string"/>
            <!--倒计时显示的文字-->
            <attr name="count_down_text" format="string"/>
            <!--倒计时结束显示的文字-->
            <attr name="end_text" format="string"/>
            <!--颜色-->
            <attr name="start_text_color" format="color"/>
            <attr name="count_down_text_color" format="color"/>
            <attr name="end_text_color" format="color"/>
            <!--倒计时样式-->
            <attr name="type" format="enum">
                <!--时间不足2位时 不补0-- 10 9 ... >
                <enum name="normal" value="0" />
                 <!--时间不足2位时  补0-- 10 09 ...>
                <enum name="zeroize" value="1" />
            </attr>

倒计时开始后默认显示 剩余多少s 

倒计时结束后默认显示 重新获取



开始倒计时：
        
        countDownView.startCounDownTime();

页面销毁时调用(避免页面销毁时，倒计时仍在进行)：
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
         countDownView.stoptCounDownTime();
    }

