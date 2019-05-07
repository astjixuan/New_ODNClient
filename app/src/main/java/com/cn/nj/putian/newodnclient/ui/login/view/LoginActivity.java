package com.cn.nj.putian.newodnclient.ui.login.view;

import android.app.ProgressDialog;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SnackbarUtils;
import com.cn.nj.putian.newodnclient.R;
import com.cn.nj.putian.newodnclient.base.mvp.BaseMvpActivity;
import com.cn.nj.putian.newodnclient.ui.login.contract.LoginContract;
import com.cn.nj.putian.newodnclient.ui.login.presenter.LoginPresenter;
import com.cn.nj.putian.newodnclient.ui.main.view.FirstActivity;

public final class LoginActivity extends BaseMvpActivity<LoginPresenter>
        implements LoginContract.View,View.OnClickListener {

    private TextView versionTV;
    private View mainView;
    private TextInputLayout userInputLayout,passInputLayout;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        mainView = findViewById(android.R.id.content);
        versionTV = findViewById(R.id.login_version);
        userInputLayout = findViewById(R.id.login_userInput);
        passInputLayout = findViewById(R.id.login_passInput);
        findViewById(R.id.login_Btn).setOnClickListener(this);
        findViewById(R.id.login_settingBtn).setOnClickListener(this);
    }

    @Override
    public void initData() {
        //得到上次登录过的，用户名，显示出来
        String lastUser = mPresenter.getLastUser();
        Log.w("LoginActivity","lastUser = " + lastUser);
        userInputLayout.getEditText().setText(lastUser);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.login_act;
    }

    @Override
    public void initPresenter() {
        mPresenter = new LoginPresenter(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_Btn:
                //登录
                String user = userInputLayout.getEditText().getText().toString();
                String pass = passInputLayout.getEditText().getText().toString();
                mPresenter.loginUser(user,pass);
                break;

            case R.id.login_settingBtn:
                //设置页面
                startActivity(SettingNetActivity.class);
                userInputLayout.setErrorEnabled(false);
                passInputLayout.setErrorEnabled(false);
                break;
        }
    }

    @Override
    public void showError(String str) {
        //第三方的封装
        SnackbarUtils.with(mainView).setMessage(str)
                .setDuration(SnackbarUtils.LENGTH_SHORT)
                .showError();
    }

    @Override
    public void showProgressDialog() {
        if(null == mProgressDialog) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("尝试连接服务器");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // indicator
            //mProgressDialog.setCancelable(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();
        } else {
            mProgressDialog.show();
        }
    }

    @Override
    public void dismissProgress() {
        if (isFinishing()) {
            return;
        }
        if (null != mProgressDialog && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showUserInputError(String str) {
        userInputLayout.setErrorEnabled(true);
        userInputLayout.setError(str);
    }

    @Override
    public void showPassInputError(String str) {
        passInputLayout.setErrorEnabled(true);
        passInputLayout.setError(str);
    }

    @Override
    public void goToNextActivity() {
        startActivity(FirstActivity.class);
    }

    @Override
    public void showVersion(String version) {
        versionTV.setText("版本号："+version);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void finishFragment() {
    }
}
