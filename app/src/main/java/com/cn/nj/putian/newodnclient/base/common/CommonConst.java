package com.cn.nj.putian.newodnclient.base.common;

/**
 * 一些固定的参数
 */
public class CommonConst {

    public static final boolean DEBUG = true;//debug模式
    public static final String APK_NAME = "PT_ODNclient.apk";
    public static final String DOWNLOAD = "/PT_ODN/download/";//存apk的地方
    //默认用户名（只用于连接服务器验证）
    public final static String name = "njptpeixian";
    public final static String pass = "njputianpx";
    public final static int timeout = 300000; //300秒超时---webservice
    public final static int timeout_short = 30000; //30秒超时---webservice

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
}
