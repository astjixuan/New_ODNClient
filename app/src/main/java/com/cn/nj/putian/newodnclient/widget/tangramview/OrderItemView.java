package com.cn.nj.putian.newodnclient.widget.tangramview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.cn.nj.putian.newodnclient.R;
import com.cn.nj.putian.newodnclient.base.common.CommonConst;
import com.tmall.wireless.tangram.structure.BaseCell;

/**
 * 普通工单布局(type=4)
 */
public final class OrderItemView extends BaseItemView {

    private TextView typeTV,orderNoTV,timeTV,addressTV,stateTV,titleTV;

    public OrderItemView(Context context) {
        super(context);
    }

    @Override
    public void initView() {
        inflate(getContext(), R.layout.order_view_item, this);
        typeTV = findViewById(R.id.order_item_typeVal);
        orderNoTV = findViewById(R.id.order_item_no);
        timeTV = findViewById(R.id.order_item_time);
        addressTV = findViewById(R.id.order_item_address);
        stateTV = findViewById(R.id.order_item_state);
        titleTV = findViewById(R.id.order_item_title);
    }

    @Override
    public void postBindView(BaseCell cell) {
        super.postBindView(cell);
        String showType = cell.optStringParam("isShow");
        if("show".equals(showType)) {
            titleTV.setVisibility(View.VISIBLE);
            titleTV.setText(cell.optStringParam("paName"));
        } else {
            titleTV.setVisibility(View.GONE);
        }
        showOrderTypeText(cell.optStringParam("orderType"));
        orderNoTV.setText(cell.optStringParam("orderNo"));
        StringBuilder time = new StringBuilder();
        time.append("建议时间:").append(cell.optStringParam("time"));
        timeTV.setText(time.toString());
        addressTV.setText(cell.optStringParam("address"));
        //TODO 状态改变还没写
        stateTV.setVisibility(View.INVISIBLE);
        //stateTV.setText(cell.optStringParam(""));
    }

    private void showOrderTypeText(String type) {
        if(CommonConst.orderType_NEW.equals(type)) {
            typeTV.setText("新");
            typeTV.setBackgroundResource(R.drawable.ic_order01);
        } else if(CommonConst.orderType_DEL.equals(type)) {
            typeTV.setText("拆");
            typeTV.setBackgroundResource(R.drawable.ic_order02);
        } else if(CommonConst.orderType_CHANGE.equals(type)) {
            typeTV.setText("改");
            typeTV.setBackgroundResource(R.drawable.ic_order03);
        } else {
            typeTV.setText("配置");
            typeTV.setBackgroundResource(R.drawable.ic_order04);
        }
    }
}
