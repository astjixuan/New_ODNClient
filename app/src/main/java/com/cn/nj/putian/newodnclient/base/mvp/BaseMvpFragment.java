package com.cn.nj.putian.newodnclient.base.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

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
