package com.cn.nj.putian.newodnclient.ui.login.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.cn.nj.putian.newodnclient.base.common.CommonConst;
import com.cn.nj.putian.newodnclient.base.mvp.BasePresenter;
import com.cn.nj.putian.newodnclient.facade.SharedPerFacade;
import com.cn.nj.putian.newodnclient.service.webservice.NetService;
import com.cn.nj.putian.newodnclient.ui.login.contract.SettingNetContract;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


/**
 * 设置ip地址的业务实现
 * @author zhaol
 */
public class SettingNetPresenter extends BasePresenter<SettingNetContract.View> implements SettingNetContract.Presenter {

    private SharedPerFacade sharedFacade;

    public SettingNetPresenter(Context context) {
        sharedFacade = new SharedPerFacade(context);
    }

    @Override
    public void connectTest(String ip, String port) {
        //启动进度条
        getMvpView().showProgressDialog();
        //测试线程
        testNet task = new testNet();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,ip,port);
    }

    @Override
    public boolean checkIp(String ip) {
        //可能时域名，可能时ip地址
        //所以目前不验证
        return true;
    }

    @Override
    public boolean checkPort(String port) {
        //验证端口号,必须时数字
        // 要验证的字符串（port）
        String regEx = "^[0-9]{4,10}$";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(port);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();

        return rs;
    }



    @Override
    public void getLastIpAndPort() {
        getMvpView().showIpAddr(sharedFacade.getLastIp());
        getMvpView().showIpPort(sharedFacade.getIpPort());
    }

    /**
     * 测试连接网络
     */
    private class testNet extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String ip = params[0];
            String portString = params[1];
            StringBuffer url = new StringBuffer();
            url.append("http://");
            url.append(ip);
            url.append(":" + portString);
            url.append(CommonConst.SERVICE_URL);
            String service_url = url.toString();
            String xml = isConnectWebServiceParam(ip);
            NetService service = new NetService();
            Map<String, String> map = service.isConnect("isConnectWebService", xml, service_url);
            String key = map.get("key");
            if("OK".equals(key)) {
                //连接成功
                //保存当前IP
                //sharedFacade.saveIpAddress(ip);
                sharedFacade.saveLastIp(ip);
                sharedFacade.saveIpPort(portString);//保存port端口

                return "connectOk";
            } else {
                return "netWrong";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.equals("netWrong")) {
                getMvpView().dismissProgress();
                getMvpView().showError("IP地址不正确, 或网络异常! ");
            } else if (result.equals("connectOk")) {
                //成功
                getMvpView().dismissProgress();
                getMvpView().showToast("连接成功! 已跳转至登录界面");
                getMvpView().finishActivity();
            }
        }
    }

    /**
     * 连接服务器的xml
     * @return
     */
    private String isConnectWebServiceParam(String ip) {
        Element root = new Element("request");//根目录
        Document doc = new Document(root);
        root.addContent(new Element("ip").setText(ip));//新的元素
        XMLOutputter xo = new XMLOutputter(Format.getCompactFormat()); // getPrettyFormat() 层次格式化
        return xo.outputString(doc);
    }
}
