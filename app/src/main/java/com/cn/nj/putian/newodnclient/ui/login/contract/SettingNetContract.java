package com.cn.nj.putian.newodnclient.ui.login.contract;

import com.cn.nj.putian.newodnclient.base.mvp.BaseMvpView;

/**
 * 设置pin码的接口
 */
public interface SettingNetContract {

    //业务逻辑
    interface Presenter {

        /**
         * 连接测试
         * @param ip 地址
         * @param port 端口号
         */
        void connectTest(String ip,String port);

        //验证ip地址格式是否正确
        boolean checkIp(String ip);

        boolean checkPort(String port);

        //得到保存的ip地址和端口号
        void getLastIpAndPort();
    }

    //页面逻辑
    interface View extends BaseMvpView {

        //验证格式失败，提示用户
        void showError(String str);

        void showProgressDialog();

        void dismissProgress();

        void showToast(String msg);

        void showIpAddr(String ip);

        void showIpPort(String port);

    }
}
