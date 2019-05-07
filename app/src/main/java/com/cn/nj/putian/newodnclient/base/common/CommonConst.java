package com.cn.nj.putian.newodnclient.base.common;


import android.util.ArrayMap;

import com.cn.nj.putian.newodnclient.R;
import com.tmall.wireless.tangram.util.Preconditions;

import java.util.Map;

/**
 * 一些固定的参数
 */
public final class CommonConst {

    public static final boolean DEBUG = true;//debug模式
    public static final String APK_NAME = "PT_ODNclient.apk";
    public static final String DOWNLOAD = "/PT_ODN/download/";//存apk的地方
    //默认用户名（只用于连接服务器验证）
    public final static String name = "njptpeixian";
    public final static String pass = "njputianpx";
    public final static int timeout = 300000; //300秒超时---webservice
    public final static int timeout_short = 30000; //30秒超时---webservice
    //图标数据
    private static Map<String,Integer> iconMap = new ArrayMap<>();
    public static void initIconMap() {
        iconMap.put("ic_category_2", R.drawable.ic_category_2);
        iconMap.put("ic_category_13",R.drawable.ic_category_13);
        iconMap.put("ic_category_14",R.drawable.ic_category_14);
        iconMap.put("ic_category_16",R.drawable.ic_category_16);
        iconMap.put("ic_category_25",R.drawable.ic_category_25);
        iconMap.put("ic_home_first",R.drawable.ic_home_first);
        iconMap.put("ic_home_four",R.drawable.ic_home_four);
        iconMap.put("ic_home_second",R.drawable.ic_home_second);
        iconMap.put("ic_home_third",R.drawable.ic_home_third);
    }

    public static Map<String,Integer> getIconMap() {
        Preconditions.checkNotNull(iconMap, "iconMap should not be null");
        return iconMap;
    }

    //储存登录的用户名
    private static String userName = "";
    public static String getUserName() {
        return userName;
    }
    public static void setUserName(String userName) {
        CommonConst.userName = userName;
    }

    /**
     * 所要调用的Web Service的命名空间
     */
    public final static String SERVICE_NAMESPACE = "http://iterface.mobil.com/";

    /**
     * 服务器部分url(Web Service)
     */
    public final static String SERVICE_URL = "/iodn_ptpx/face.do?wsdl";

    /**
     * 服务器端口号(Web Service)---默认值，可以修改的
     */
    public final static int Port = 8888;

    /**
     * 网管系统网页访问的端口号(访问网页用)---默认值，可以修改的
     */
    public final static int WEB_PORT = 8080;


    //---------------SharePreference的key值-------------
    public static final String save_username = "user_name";//保存上次登录的用户(格式如XXX,BBB,XXX)
    public static final String save_ip = "ip_addr";
    public static final String save_port = "ip_port";


    //Tangram type说明
    public static final String TangramType1 = "1";//首页中，网格图片显示
    public static final String TangramType2 = "2";//首页中，固定布局
    public static final String TangramType3 = "3";//首页中，吸顶布局
    public static final String TangramType4 = "4";//工单列表中。工单布局
    //public static final String TangramType5 = "5";//

    public static final String orderType_NEW = "new";
    public static final String orderType_DEL = "del";
    public static final String orderType_CHANGE = "change";
    public static final String orderType_SETTING = "setting";
}
