package com.cn.nj.putian.newodnclient.service.webservice;

import android.util.Log;

import com.cn.nj.putian.newodnclient.base.common.CommonConst;
import com.cn.nj.putian.newodnclient.utils.MD5Util;

import org.jdom2.JDOMException;
import org.jdom2.output.XMLOutputter;
import org.xmlpull.v1.XmlPullParserException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;
import org.xml.sax.InputSource;
import org.jdom2.output.Format;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * webservice的一些请求
 */
public class NetService {

    /**
     * 是否能连接上webservice
     * @param mathodName
     * @param xml
     * @param url 服务器url
     * @return
     */
    public Map<String, String> isConnect(String mathodName, String xml, String url) {
        if(CommonConst.DEBUG) {
            Log.v("NetWorkService", "发送数据 = " + xml);
        }
        MD5Util md5Util = new MD5Util();
        Map<String, String> map = new HashMap<String, String>();

        HttpTransportSE ht = new HttpTransportSE(url, CommonConst.timeout_short);
        ht.debug = true;
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        //参数
        SoapObject param = new SoapObject(CommonConst.SERVICE_NAMESPACE, mathodName);
        param.addProperty("xml", xml); //必须设置为arg0、arg1、arg2(如果服务器，设置了参数名，就必须用服务器的定义的)
        envelope.bodyOut = param;
        // 设置与.NET提供的Web service保持有良好的兼容性
        //envelope.dotNet = true;

        //头文件
        Element[] header = new Element[1];
        header[0] = new Element().createElement("", "ptpx");
        Element pass = new Element().createElement("", "password");
        //加密后
        String passStringForMD5 = md5Util.string2MD5(CommonConst.pass);
        pass.addChild(Node.TEXT, passStringForMD5);
        header[0].addChild(Node.ELEMENT, pass);

        Element name = new Element().createElement("", "username");
        name.addChild(Node.TEXT, CommonConst.name);
        header[0].addChild(Node.ELEMENT, name);

        envelope.headerOut = header;

        try {
            //(6) 调用webService
            ht.call(null, envelope);

            //(7) 调用web service的返回值
            if (envelope.getResponse() != null) {
                SoapObject result = (SoapObject) envelope.bodyIn;
                String detail = result.getProperty(0).toString();
                if(CommonConst.DEBUG) {
                    System.out.println("收到数据 = " + detail);
                }
                map.put("key", "OK");
                map.put("val", detail.toString());
            } else {
                map.put("key", "Fail");
                map.put("val", "无数据");
            }
        } catch (IOException e) {
            e.printStackTrace();
            map.put("key", "Fail");
            map.put("val", "超时,异常！");
            return map;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            map.put("key", "Fail");
            map.put("val", "超时,异常！");
            return map;
        }
        return map;
    }

}
