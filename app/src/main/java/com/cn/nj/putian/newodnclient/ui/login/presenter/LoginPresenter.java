package com.cn.nj.putian.newodnclient.ui.login.presenter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import com.blankj.utilcode.util.StringUtils;
import com.cn.nj.putian.newodnclient.base.common.CommonConst;
import com.cn.nj.putian.newodnclient.base.mvp.BasePresenter;
import com.cn.nj.putian.newodnclient.facade.SharedPerFacade;
import com.cn.nj.putian.newodnclient.service.webservice.NetService;
import com.cn.nj.putian.newodnclient.ui.login.contract.LoginContract;

import java.util.Map;

/**
 * 登录页面的逻辑实现类
 */
public final class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter{

    private Context mContext;
    private SharedPerFacade sharedFacade;

    public LoginPresenter(Context context) {
        sharedFacade = new SharedPerFacade(context);
        this.mContext = context;
    }

    @Override
    public void loginUser(String user, String pass) {
        if(checkInputUserAndPass(user,pass)) {
            //启动进度条
            getMvpView().showProgressDialog();
            //登录
            LoginTask task = new LoginTask();
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,user,pass);
        }
    }

    @Override
    public boolean checkInputUserAndPass(String user, String pass) {
        if(StringUtils.isTrimEmpty(user)) {
            getMvpView().showUserInputError("用户名不能为空");
            return false;
        }
        if(StringUtils.isTrimEmpty(pass)) {
            getMvpView().showPassInputError("密码不能为空");
            return false;
        }
        return true;
    }

    @Override
    public String getAppVersion() {
        String localVersion = "";
        try {
            PackageInfo packageInfo = mContext.getPackageManager()
                    .getPackageInfo(mContext.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    @Override
    public String getLastUser() {
        //TODO 还没写逻辑
        return "";
    }

    //登录
    private class LoginTask extends AsyncTask<String, Void, Map<String,String>> {

        @Override
        protected Map<String,String> doInBackground(String... p) {
            String user = p[0];
            String pass = p[1];
            NetService service = new NetService();
            return service.loginUser("user",user,pass);
        }

        @Override
        protected void onPostExecute(Map<String,String> result) {
            super.onPostExecute(result);
            String key = result.get("key");
            getMvpView().dismissProgress();
            if("ok".equals(key)) {
                //登录成功，保存用户名
                CommonConst.setUserName(result.get("name"));
                sharedFacade.saveUserName(result.get("name"));
                getMvpView().goToNextActivity();
                getMvpView().finishActivity();
            } else {
                String failStr = result.get("val");
                getMvpView().showError(failStr);
            }
        }
    }
}
