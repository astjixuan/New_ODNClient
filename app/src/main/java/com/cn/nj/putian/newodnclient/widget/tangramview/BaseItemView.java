package com.cn.nj.putian.newodnclient.widget.tangramview;

import android.content.Context;
import android.widget.LinearLayout;

import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.view.ITangramViewLifeCycle;

public abstract class BaseItemView extends LinearLayout implements ITangramViewLifeCycle {

    public BaseItemView(Context context) {
        super(context);
        initView();
    }

    public abstract void initView();

    @Override
    public void cellInited(BaseCell cell) {
        setOnClickListener(cell);//点击事件绑定
    }

    @Override
    public void postBindView(BaseCell cell) {

    }

    @Override
    public void postUnBindView(BaseCell cell) {

    }
}
