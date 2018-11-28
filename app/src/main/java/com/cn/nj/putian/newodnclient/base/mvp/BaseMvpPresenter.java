package com.cn.nj.putian.newodnclient.base.mvp;

/**
 * Presenter层的基础接口
 * 主要定义，绑定view接口和解除绑定
 */
public interface BaseMvpPresenter<T extends BaseMvpView> {

    /**
     * 绑定view层
     * @param mvpView view层（一般都是activity,fragment）
     */
    void attachView(T mvpView);

    /**
     * 跟view层解除绑定
     */
    void detachView();

}
