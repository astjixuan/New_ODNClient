package com.cn.nj.putian.newodnclient.base.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 所有Fragment的基类
 * @author zhaol
 */
public abstract class BaseMvpFragment<T extends BasePresenter> extends Fragment implements BaseMvpView{

    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        bindPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater,container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 初始化view对象，这里在Fragment中的onCreateView方法中进行实现，返回一个View对象
     * @return
     */
    public abstract View initView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container);

    /**
     * 初始化数据,用于数据绑定到页面
     */
    public abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解绑Presenter层
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
            System.gc();
        }
    }

    /**
     * Presenter层的初始化
     */
    public abstract void initPresenter();

    /**
     * 绑定presenter
     */
    private void bindPresenter() {
        if(null != mPresenter) {
            mPresenter.attachView(this);
        }
    }

    public void startActivity(Class<?> cls) {
        startActivity(cls,null);
    }

    public void startActivity(Class<?> cls,Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if(null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}
