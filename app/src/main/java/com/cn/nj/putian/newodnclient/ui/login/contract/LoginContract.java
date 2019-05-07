package com.cn.nj.putian.newodnclient.ui.login.contract;

import com.cn.nj.putian.newodnclient.base.mvp.BaseMvpView;

/**
 * 登录页面的一些接口
 */
public interface LoginContract {

    //业务逻辑
    interface Presenter {

        void loginUser(String user,String pass);

        //String getIpAddress();

        boolean checkInputUserAndPass(String user,String pass);

        String getAppVersion();

        String getLastUser();
    }

    //页面逻辑
    interface View extends BaseMvpView {

        //提示用户
        void showError(String str);

        void showProgressDialog();

        void dismissProgress();

        //输入框提示
        void showUserInputError(String str);

        void showPassInputError(String str);

        void goToNextActivity();

        void showVersion(String version);
    }

}
