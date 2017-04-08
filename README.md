# CountdownView
用于单个View显示倒计时的情景，比如获取验证码...

用法：
     <com.zdm.lib_countdownview.CountDownView
                android:id="@+id/register_get_virification"
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
                <!--不足2位时 不补0-->
                <enum name="normal" value="0" />
                <enum name="zeroize" value="1" />
            </attr>