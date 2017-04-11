# CountdownView
用于单个View显示倒计时的情景，比如获取验证码...

![image](https://github.com/CorruptWood/CountdownView/blob/master/coundown.gif?raw=true)

项目下的build.gradle添加：

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Module下的build.gradle添加：

	dependencies {
	      compile 'com.github.CorruptWood:CountdownView:1.0.9'
	}

注意：

	xml设置时间范围为1~99秒 不设置时间，默认30秒。
	
	不设置显示文字，默认:获取验证码 倒计时过程中文字默认显示为:剩余多少S  倒计时完成默认显示为：重新获取
	
	android:text 属性与 app:start_text 属性可以当作是同一个属性
	
	


示例（使用AutoLayout做的适配）：
     
     <com.zdm.lib_countdownview.CountDownView
        android:id="@+id/count_down1"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:gravity="center"
        android:text="获取验证码"
        app:count_down_text_color="@color/colorAccent"
        app:count_down_time="15"
        app:start_text_color="@color/colorPrimary"
        app:type="zeroize"/>

    <View
        android:layout_width="match_parent"
        android:layout_height="2dp"
        android:background="@color/colorPrimary"/>

    <com.zdm.lib_countdownview.CountDownView
        android:id="@+id/count_down2"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:layout_alignParentRight="true"
        android:gravity="center"
        android:padding="10dp"
        android:text="跳过"
        app:count_down_text="跳过"
        app:count_down_time="15"
        app:end_text="跳过"
        app:start_text_color="@color/colorAccent"
        app:type="normal"/>


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


在你想开始倒计时的地方使用：
        
    countDownView.startCounDownTime();

页面销毁时调用(避免页面销毁时，倒计时仍在进行)：
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
         countDownView.stoptCounDownTime();
    }

结束监听：

	countDown.setOnStopListener(new OnCountDownStopListener() {
            @Override
            public void OnCountDownStop() {
                
            }
        });
