# CountdownView
用于界面跳过倒计时、获取验证码倒计时，可单独设置倒计时的时间、开始、结束的回调监听.......

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
	      compile 'com.github.CorruptWood:CountdownView:1.1.1'
	}

注意：

	xml设置时间范围为1~99秒 不设置时间，默认30秒。
	
	不设置显示文字，默认:获取验证码 倒计时过程中文字默认显示为:剩余多少S  倒计时完成默认显示为：重新获取
	
	android:text 属性与 app:start_text 属性可以当作是同一个属性
	
	倒计时文字务必使用占位符设置，可灵活处理时间显示在前或在后
	
	更可以单独设置倒计时时间的颜色
	
	app:count_down_time_color="#ff6633"
	
	


Demo示例（使用AutoLayout做的适配）：
     
    <com.zdm.lib_countdownview.CountDownView
            android:id="@+id/count_down4"
            android:layout_width="280px"
            android:layout_height="60px"
            android:layout_margin="20px"
            android:background="@drawable/text_shape_two"
            android:gravity="center"
            android:text="跳过"
            app:count_down_text="@string/skip1"   //  跳过 %1$s
            app:count_down_time="80"
            app:isClickable="true"
            app:start_text_color="#999"
            app:type="zeroize"/>


        <com.zdm.lib_countdownview.CountDownView
            android:id="@+id/count_down5"
            android:layout_width="140px"
            android:layout_height="60px"
            android:layout_margin="20px"
            android:background="@drawable/text_shape_two"
            android:gravity="center"
            android:text="跳过"
            app:count_down_text="@string/skip2"   //  %1$s 跳过 
            app:count_down_text_color="#999"
            app:count_down_time="15"
            app:count_down_time_color="#ff6633"
            app:end_text="跳过"
            app:start_text_color="#999"
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

倒计时开始的回调监听：

	countDown.setOnStartListener(new OnCountDownStartListener() {
            @Override
            public void OnCountDownStart() {
                setLoger("countDown5倒计时开始了");
            }
        });

倒计时结束的回调监听：

	countDown.setOnStopListener(new OnCountDownStopListener() {
            @Override
            public void OnCountDownStop() {
                
            }
        });
