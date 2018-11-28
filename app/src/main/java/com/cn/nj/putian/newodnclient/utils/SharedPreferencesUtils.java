package com.cn.nj.putian.newodnclient.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;


/**
 * SP的工具类
 * 单例模式（懒汉模式，线程不安全）
 */
public final class SharedPreferencesUtils {

    private static SharedPreferencesUtils utils;

    private static final String default_spName = "share_data";//存放在手机里的文件名(默认名称)

    private SharedPreferences sp;

    private SharedPreferencesUtils(Context context) {
        this(context,default_spName);
    }

    private SharedPreferencesUtils(Context context,String share_name) {
        //私有的文件
        sp = context.getSharedPreferences(share_name,Context.MODE_PRIVATE);
    }

    public static SharedPreferencesUtils getInstance(Context context) {
        if(utils == null) {
            utils = new SharedPreferencesUtils(context);
        }
        return utils;
    }

    public static SharedPreferencesUtils getInstance(Context context,String spName) {
        if(utils == null) {
            utils = new SharedPreferencesUtils(context,spName);
        }
        return utils;
    }

    /**
     * 存放数据
     * @param key 键值对
     * @param val 值
     * @param isCommit 是否使用commit方式or apply方式
     */
    public void saveStringVal(@NonNull String key,@NonNull String val,boolean isCommit) {
        if(isCommit)
        {
            //有返回值
            sp.edit().putString(key,val).commit();
        }
        else
        {
            //无返回值
            sp.edit().putString(key,val).apply();
        }
    }

    /**
     * 默认用apply保存
     * @param key
     * @param val
     */
    public void saveStringVal(@NonNull String key,@NonNull String val) {
        saveStringVal(key,val,false);
    }

    public String getStringVal(@NonNull String key,String defaultVal) {
        return sp.getString(key,defaultVal);
    }

    public String getStringVal(@NonNull String key) {
        return getStringVal(key,"");//默认值""
    }

    public void saveIntVal(@NonNull String key,@NonNull int val,boolean isCommit) {
        if(isCommit)
        {
            //有返回值
            sp.edit().putInt(key,val).commit();
        }
        else
        {
            //无返回值
            sp.edit().putInt(key,val).apply();
        }
    }

    public void saveIntVal(@NonNull String key,@NonNull int val) {
        saveIntVal(key,val,false);
    }

    public int getIntVal(@NonNull String key,int defaultVal) {
        return sp.getInt(key,defaultVal);
    }

    public int getIntVal(@NonNull String key) {
        return getIntVal(key,0);//默认值 0
    }

    public boolean contains(@NonNull String key) {
        return sp.contains(key);
    }

    public void remove(@NonNull String key,boolean isCommit) {
        if(isCommit)
        {
            sp.edit().remove(key).commit();
        }
        else
        {
            sp.edit().remove(key).apply();
        }
    }

    public void remove(@NonNull String key) {
        remove(key,false);
    }

    public void clearAll(@NonNull boolean isCommit) {
        if(isCommit)
        {
            sp.edit().clear().commit();
        }
        else
        {
            sp.edit().clear().apply();
        }
    }

    public void clearAll() {
        clearAll(false);
    }
}
