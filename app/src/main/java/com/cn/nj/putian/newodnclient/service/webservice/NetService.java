package com.cn.nj.putian.newodnclient.service.webservice;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.nj.putian.newodnclient.base.common.CommonConst;


import java.util.HashMap;
import java.util.Map;

/**
 * webservice的一些请求
 */
public class NetService {

    /**
     * 是否能连接上webservice
     * 模拟测试用
     * @param mathodName
     * @param ip
     * @param url 服务器url
     * @return
     */
    public Map<String, String> isConnect(String mathodName, String ip, String url) {
        Map<String,String> map = new HashMap<>();
        //模拟的ip地址
        String demoIp = "{\"ip\":\"192.168.30.45\",\"port\":\"8888\"}";
        JSONObject ipJson = JSON.parseObject(demoIp);
        if(ip.equals(ipJson.getString("ip"))) {
            //成功
            map.put("key","ok");
        } else {
            //失败
            map.put("key","fail");
        }
        return map;
    }

    /**
     * 登录
     * @param type 登录类型
     * @param user
     * @param pass
     * @return
     */
    public Map<String,String> loginUser(String type,String user,String pass) {
        Map<String,String> map = new HashMap<>();
        //模拟数据
        String url = "{\"loginType\":\"name\",\"name\":\"zhou\",\"pass\":\"123456\"}";
        JSONObject jsonData = JSON.parseObject(url);
        if(user.equals(jsonData.getString("name")) && pass.equals(jsonData.getString("pass"))) {
            map.put("key","ok");
            map.put("name",user);
        } else {
            map.put("key","fail");
            map.put("val","用户名或密码有误");
        }
        return map;
    }

}
