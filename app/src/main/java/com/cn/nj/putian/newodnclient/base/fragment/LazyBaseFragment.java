package com.cn.nj.putian.newodnclient.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 用于懒加载的fragment，跟ViewPage配合
 * 只完成了页面布局的初始化，没有请求网络,数据绑定。
 */
public abstract class LazyBaseFragment extends Fragment {

    /**
     * Fragment当前状态是否可见
     */
    protected  boolean isVisible;

    /**
     * Fragment的View加载完毕的标记
     */
    protected boolean isViewCreated;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;//页面加载完毕
        lazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 初始化view对象，这里在Fragment中的onCreateView方法中进行实现，返回一个View对象
     * @return
     */
    public abstract View initView(LayoutInflater inflater,ViewGroup container);

    /**
     * 初始化数据,用于数据绑定到页面
     */
    public abstract void initData();

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
     */
    public abstract void stopLoad();

    /**
     * 先于onCreateView()执行的方法,返回用户是否可见画面，在该方法里写懒加载逻辑
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            //显示
            isVisible = true;
            lazyLoad();
        } else {
            //不显示
            isVisible = false;
            //可以回收部分资源
            stopLoad();
        }
    }

    /**
     * 延迟加载(请求网络数据，或者其他数据)
     */
    public void lazyLoad(){
        if (!isViewCreated || !isVisible) {
            //其中只要不可见or页面没加载完成，就不加载数据
            return;
        }
        Log.w("LazyBaseFragment","可见,加载数据");
        //重新方法，把逻辑写进去
        initData();
        //数据加载完毕,恢复标记,防止重复加载
        isViewCreated = false;
        isVisible = false;
    }
}
