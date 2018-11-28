package com.cn.nj.putian.newodnclient.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

/**
 * 配置整个项目
 */
public class APPController extends Application {

    /**
     * 程序启动的时候执行
     */
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化数据库
//        SQLiteDatabase db = DatabaseWorkOrderHelper.getInstance(this).getWritableDatabase();
//        if(null != db) {
//            db.close();
//        }

        //内存检测
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }

    /**
     * 低内存的时候执行
     */
    @Override
    public void onLowMemory() {
        Log.d("Application", "onLowMemory");
        super.onLowMemory();
    }


    /**
     * HOME键退出应用程序
     * 程序在内存清理的时候执行
     */
    @Override
    public void onTrimMemory(int level) {
        Log.d("Application", "onTrimMemory");
        super.onTrimMemory(level);
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        Log.d("Application", "onTerminate");
        super.onTerminate();

    }
}
