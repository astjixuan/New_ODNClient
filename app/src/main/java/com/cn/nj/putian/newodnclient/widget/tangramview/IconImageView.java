package com.cn.nj.putian.newodnclient.widget.tangramview;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.nj.putian.newodnclient.R;
import com.cn.nj.putian.newodnclient.base.common.CommonConst;
import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.view.ITangramViewLifeCycle;

import java.util.Map;

/**
 * type是1的，控件显示(type=1)
 */
public final class IconImageView extends LinearLayout implements ITangramViewLifeCycle{

    private ImageView icon;
    private TextView titleView;

    public IconImageView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.first_view_item, this);
        icon = findViewById(R.id.first_item_img);
        titleView = findViewById(R.id.first_item_text);
    }

    @Override
    public void cellInited(BaseCell cell) {
        //绑定数据前调用
        setOnClickListener(cell);//点击事件绑定
    }

    @Override
    public void postBindView(BaseCell cell) {
        //绑定数据时机(完成组件业务逻辑)
        String title = cell.optStringParam("msg");
        String imgName = cell.optStringParam("imgName");
        titleView.setText(title);
        Map<String,Integer> imgMap = CommonConst.getIconMap();
        int imgId = imgMap.get(imgName);
        if(imgId != 0) {
            icon.setImageResource(imgId);
        }
    }

    @Override
    public void postUnBindView(BaseCell cell) {
        //滑出屏幕，解除绑定
    }
}
