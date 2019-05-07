package com.cn.nj.putian.newodnclient.ui.main.contract;

import android.content.Context;

import com.cn.nj.putian.newodnclient.base.mvp.BaseMvpView;
import com.cn.nj.putian.newodnclient.model.entity.OrderEntity;
import com.tmall.wireless.tangram.TangramEngine;

import org.json.JSONArray;

import java.util.List;

/**
 * 工单页面接口
 */
public interface OrderFragmentContract {

    //业务逻辑
    interface Presenter {

        //取得所有工单
        List<OrderEntity> getAllOrder(String userName);

        /**
         * 根据pf（局楼），查询所有工单
         * @param userName
         * @param pfName
         * @return
         */
        List<OrderEntity> getOrderByPfName(String userName,String pfName);

        /**
         * 根据机房，查询工单
         * @param userName
         * @param jiFang
         * @return
         */
        List<OrderEntity> getOrderByJiFang(String userName,String jiFang);

        /**
         *  初始化数据
         * @param userName
         */
        void initTangramData(String userName);

        /**
         * 1,初始化 Tangram 环境
         */
        void initTangramBuilder();

        /**
         * 2,初始化 TangramBuilder
         * 3,注册自定义的卡片和组件
         * 4,生成TangramEngine实例
         * @param activity
         * @return
         */
        TangramEngine buildTangramEngine(Context activity);

        /**
         * 退出的时候销毁 engine
         * @param engine
         */
        void destroyTangram(TangramEngine engine);

    }

    //页面接口
    interface View extends BaseMvpView {

        void showProgressDialog();

        void dismissProgress();

        /**
         * 加载数据并传递给 engine
         * @param array
         */
        void setTangramData(JSONArray array);

        //void goOrderActivity(String orderNo,String userName);

        //提示用户
        void showError(String str);
    }
}
