package com.cn.nj.putian.newodnclient.base.mvp;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 所有Presenter的基类
 * 使用WeakReference弱引用，便于释放资源
 * @author zhaol
 */
public class BasePresenter<T extends BaseMvpView> implements BaseMvpPresenter<T> {

    protected Reference<T> mView;

    @Override
    public void attachView(T mvpView) {
        mView = new WeakReference<T>(mvpView);
    }

    @Override
    public void detachView() {
        if(null != mView) {
            mView.clear();
            mView = null;
        }
    }

    /**
     * 得到view层实例
     * @return 得到view实例
     */
    public T getMvpView() {
        return mView.get();
    }

    /**
     * view层和Presenter，是否绑定
     * @return
     */
    public boolean isViewAttached() {
        return mView != null && mView.get() != null;
    }
}
