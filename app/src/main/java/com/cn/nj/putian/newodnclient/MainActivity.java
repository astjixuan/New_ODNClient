package com.cn.nj.putian.newodnclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cn.nj.putian.newodnclient.ui.login.view.LoginActivity;
import com.cn.nj.putian.newodnclient.ui.login.view.SettingNetActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 第一启动的画面，初始化数据等
 * 3秒后，到下个页面
 */
public class MainActivity extends AppCompatActivity {

    private Timer timer;
    private int count = 0;
    private TextView showWords;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if("show_main_countTV".equals(intent.getAction())) {
                //更新页面
                showWords.setText(String.valueOf(count));
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);
        IntentFilter filter = new IntentFilter("show_main_countTV");
        registerReceiver(receiver,filter);

        showWords = findViewById(R.id.main_act_words);

        //过3秒，直接进入login页面
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //发送广播更新页面
                count++;
                Intent intent = new Intent();
                if(count == 4)
                {
                    //去登陆页面
                    intent.setClass(MainActivity.this,SettingNetActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    intent.setAction("show_main_countTV");
                    sendBroadcast(intent);
                }
            }
        },1000,1000);//延迟1秒后，每隔1秒执行

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != timer) {
            timer.cancel();
        }
        unregisterReceiver(receiver);
    }
}
