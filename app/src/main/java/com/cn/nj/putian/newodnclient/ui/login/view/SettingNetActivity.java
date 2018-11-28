package com.cn.nj.putian.newodnclient.ui.login.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cn.nj.putian.newodnclient.R;
import com.cn.nj.putian.newodnclient.base.mvp.BaseMvpActivity;
import com.cn.nj.putian.newodnclient.ui.login.contract.SettingNetContract;
import com.cn.nj.putian.newodnclient.ui.login.presenter.SettingNetPresenter;

/**
 * 设置网络链接
 * pin ip地址
 */
public class SettingNetActivity extends BaseMvpActivity<SettingNetPresenter> implements SettingNetContract.View,View.OnClickListener {

    private EditText ipTV,portTV;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void initView() {
        findViewById(R.id.setting_net_connectBtn).setOnClickListener(this);
        ipTV = findViewById(R.id.setting_net_ip_edit);
        portTV = findViewById(R.id.setting_net_port_edit);
    }

    @Override
    public void initData() {
        //初始化数据
        //得到上次保存的ip和port号
        mPresenter.getLastIpAndPort();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.setting_act;
    }

    @Override
    public void initPresenter() {
        mPresenter = new SettingNetPresenter(getApplicationContext());
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void finishFragment() {
    }

    @Override
    public void showError(String str) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showIpAddr(String ip) {
        ipTV.setText(ip);
    }

    @Override
    public void showIpPort(String port) {
        portTV.setText(port);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.setting_net_connectBtn) {
            //测试连接网络

        }
    }
}