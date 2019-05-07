package com.cn.nj.putian.newodnclient.ui.main.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SnackbarUtils;
import com.cn.nj.putian.newodnclient.R;
import com.cn.nj.putian.newodnclient.base.mvp.BaseMvpFragment;
import com.cn.nj.putian.newodnclient.ui.main.contract.OrderFragmentContract;
import com.cn.nj.putian.newodnclient.ui.main.presenter.OrderPresenter;
import com.cn.nj.putian.newodnclient.ui.main.support.ItemClickSupport;
import com.tmall.wireless.tangram.TangramEngine;

import org.json.JSONArray;

/**
 * 工单页面
 */
public final class OrderFragment extends BaseMvpFragment<OrderPresenter> implements OrderFragmentContract.View{

    private RecyclerView mRecyclerView;
    private ProgressDialog mProgressDialog;
    private TangramEngine engine;
    private View mainView;

    public static OrderFragment newFragment() {
        return new OrderFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        View v = inflater.inflate(R.layout.order_fragment,container,false);
        mRecyclerView = v.findViewById(R.id.order_recyclerView);
        mainView = v.findViewById(R.id.fragment_main_layout);
        mPresenter.initTangramBuilder();//第一步
        engine = mPresenter.buildTangramEngine(getContext());//第二，三,四步
        //Step 6: 绑定业务 support 类到 engine
        engine.addSimpleClickSupport(new ItemClickSupport());//点击事件
//        engine.addCardLoadSupport(new XXCardLoadSupport()); //添加加载更多支持
//        engine.addExposureSupport(new XXExposureSuport());  //添加曝光事件支持

        //Step 7:绑定 recyclerView
        engine.bindView(mRecyclerView);
        setRecyclerViewListener();
        initData();
        return v;
    }

    @Override
    public void initData() {
        mPresenter.initTangramData("zhou");
    }

    private void setRecyclerViewListener() {
        //Step 8: 监听 recyclerView 的滚动事件
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                engine.onScrolled();
            }
        });
    }

    @Override
    public void initPresenter() {
        mPresenter = new OrderPresenter(getContext());
    }

    @Override
    public void showProgressDialog() {
        if(null == mProgressDialog) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage("正在加载数据，稍后");
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
        if (isDetached()) {
            return;
        }
        if (null != mProgressDialog && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void setTangramData(JSONArray array) {
        engine.setData(array);
    }

    @Override
    public void showError(String str) {
        //第三方的封装
        SnackbarUtils.with(mainView).setMessage(str)
                .setDuration(SnackbarUtils.LENGTH_SHORT)
                .showError();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroyTangram(engine);
    }

    @Override
    public void finishActivity() {
    }

    @Override
    public void finishFragment() {
        getActivity().onBackPressed();//销毁自己
    }
}
