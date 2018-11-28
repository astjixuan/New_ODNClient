package com.cn.nj.putian.newodnclient.base.mvp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 所有Activity的基类
 * @param <T>
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends AppCompatActivity implements BaseMvpView{

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        //设置为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //初始化页面
        initView();
        initPresenter();
        //绑定Presenter层
        bindPresenter();
        //初始化数据
        initData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑Presenter层
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
            System.gc();
        }
    }

    /**
     * 绑定presenter
     */
    private void bindPresenter() {
        if(null != mPresenter) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 组件初始化操作
     */
    public abstract void initView();

    /**
     * 页面初始化页面数据，在initView之后调用
     */
    public abstract void initData();

    /**
     * 返回资源的布局
     * @return
     */
    public abstract int getLayoutResId();

    /**
     * Presenter的初始化
     */
    public abstract void initPresenter();

    /**
     * 跳转页面，无数据传输
     * @param cls
     */
    public void startActivity(Class<?> cls) {
        startActivity(cls,null);
    }

    /**
     * 跳转页面,并带有数据
     * @param cls
     * @param bundle
     */
    public void startActivity(Class<?> cls,Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if(null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}
