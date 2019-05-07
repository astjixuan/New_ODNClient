package com.cn.nj.putian.newodnclient.ui.main.support;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.support.SimpleClickSupport;

/**
 * 点击事件
 */
public final class ItemClickSupport extends SimpleClickSupport {

    public ItemClickSupport() {
        setOptimizedMode(true);
    }

    @Override
    public void defaultClick(View targetView, BaseCell cell, int eventType) {
        super.defaultClick(targetView, cell, eventType);
        String stringType = cell.stringType;//使用这个代替type
        int type = Integer.parseInt(stringType);
        String str = cell.optStringParam("msg");
        switch (type) {
            case 1:

                break;

            case 2:

                break;

            case 3:

                break;
        }


        Toast.makeText(targetView.getContext(), " 您点击了组件  type:"+stringType+ ",msg = " + str,
                Toast.LENGTH_SHORT).show();



    }

    private void goToOrderActivity(Intent intent, Context context, String orderNo, String userName) {
//        intent.setClass(context,);
//        intent.putExtra();
//        intent.putExtra();
//        intent.putExtra();
//        context.startActivity(intent);
    }

    private void goToActivity(Intent intent,Context context) {
//        intent.setClass(context,);
//        context.startActivity(intent);
    }
}
