package com.cn.nj.putian.newodnclient.ui.main.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.blankj.utilcode.util.ResourceUtils;
import com.cn.nj.putian.newodnclient.base.common.CommonConst;
import com.cn.nj.putian.newodnclient.base.mvp.BasePresenter;
import com.cn.nj.putian.newodnclient.model.entity.OrderEntity;
import com.cn.nj.putian.newodnclient.ui.main.contract.OrderFragmentContract;
import com.cn.nj.putian.newodnclient.widget.tangramview.IconImageView;
import com.cn.nj.putian.newodnclient.widget.tangramview.OrderItemView;
import com.cn.nj.putian.newodnclient.widget.tangramview.OrderScreenView;
import com.squareup.picasso.Picasso;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.TangramEngine;
import com.tmall.wireless.tangram.util.IInnerImageSetter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class OrderPresenter extends BasePresenter<OrderFragmentContract.View>
        implements OrderFragmentContract.Presenter{

    private Context mContext;

    public OrderPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public List<OrderEntity> getAllOrder(String userName) {
        //TODO 应该从db里面取数据，这里模拟数据
        return testData();
    }

    @Override
    public List<OrderEntity> getOrderByPfName(String userName, String pfName) {
        return null;
    }

    @Override
    public List<OrderEntity> getOrderByJiFang(String userName, String jiFang) {
        return null;
    }

    @Override
    public void initTangramData(String userName) {
        //默认查询出该用户，所以工单
        //进度条开启
        getMvpView().showProgressDialog();
        DataInit task = new DataInit();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,userName);
//        JSONArray dataArray = getTangramData(userName);
//        getMvpView().setTangramData(dataArray);
//        getMvpView().dismissProgress();
    }

    private JSONArray getTangramData(String userName) {
        List<OrderEntity> list = getAllOrder(userName);

        //组织页面数据
        String str = ResourceUtils.readAssets2String("order_data.json");
        JSONArray data = null;

        try {
            data = new JSONArray(str);//里面放置头文件(固定文件)

            //添加工单布局
            JSONObject object = new JSONObject();
            object.put("type","container-oneColumn");
            object.put("viewName","普通网格布局，单列(一排一)");
            object.put("load","queryMoreOrder");
            object.put("loadType",1);

            //样式
            //JSONObject styleJson = new JSONObject();
            //styleJson.put("bgColor","#999999");
            //styleJson.put("vGap",5);
            //object.put("style",styleJson);

            JSONArray items = new JSONArray();

            String lastPaName = "";//上个局所名称(工单分类用，名称分类)
            for(OrderEntity order : list) {
                JSONObject jsonOrder = new JSONObject();

                String paName = order.getPaName();
                if(!lastPaName.equals(paName)) {
                    //不同居所，显示居所信息
                    jsonOrder.put("isShow","show");//局所的title显示
                    jsonOrder.put("paName",order.getPaName());//局所
                    lastPaName = paName;
                } else {
                    jsonOrder.put("isShow","not");//不显示
                }

                StringBuffer buffer = new StringBuffer();//地址
                buffer.append(order.getPfName()).append(",").append(order.getJiFangName());
                jsonOrder.put("type", CommonConst.TangramType4);//工单列表显示
                jsonOrder.put("orderNo",order.getOrderNo());
                jsonOrder.put("address",buffer.toString());
                jsonOrder.put("orderType",order.getOrderType());
                jsonOrder.put("time",order.getRequirFinishTime());
                items.put(jsonOrder);
            }
            object.put("items",items);
            data.put(object);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }

    @Override
    public void initTangramBuilder() {
        //Step 1: 初始化 Tangram 环境
        TangramBuilder.init(mContext, new IInnerImageSetter() {
            @Override
            public <IMAGE extends ImageView> void doLoadImageUrl(@NonNull IMAGE view, @Nullable String url) {
                Log.e("Picasso doLoadImageUrl","url = "+ url);
                Picasso.with(mContext).load(url).into(view);
            }
        },ImageView.class);
    }

    @Override
    public TangramEngine buildTangramEngine(Context activity) {
        //Step 2: 初始化 TangramBuilder
        TangramBuilder.InnerBuilder builder = TangramBuilder.newInnerBuilder(activity);

        //Step 3: 注册自定义的卡片和组件
        //TangramBuilder中已经预先注册了许多组件及卡片，可直接使用；此处只需要注册自定义的组件及卡片即可
        builder.registerCell(CommonConst.TangramType1,IconImageView.class);
        //builder.registerCell(CommonConst.TangramType2,SecondItemView.class);
        builder.registerCell(CommonConst.TangramType3,OrderScreenView.class);
        builder.registerCell(CommonConst.TangramType4,OrderItemView.class);

        //Step 4: 生成TangramEngine实例
        TangramEngine engine = builder.build();
        return engine;
    }

    @Override
    public void destroyTangram(TangramEngine engine) {
        if(null != engine) {
            engine.destroy();
        }
    }

    //数据初始化查询
    private class DataInit extends AsyncTask<String, String, String> {

        private JSONArray data;

        @Override
        protected String doInBackground(String... strings) {
            String name = strings[0];
            data = getTangramData(name);
            if(data == null) {
                //加载失败
                return "fail";
            } else {
                return "ok";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            getMvpView().dismissProgress();
            if("fail".equals(s)) {
                getMvpView().showError("数据加载错误！");
            } else {
                getMvpView().setTangramData(data);
            }
        }
    }

    //测试数据
    private List<OrderEntity> testData() {
        List<OrderEntity> list = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            OrderEntity entity = new OrderEntity();
            entity.setJiFangName("普天3楼机房");
            entity.setRequirFinishTime("20190425");
            entity.setOrderType("new");
            entity.setOrderNo("20190425xxxx");
            entity.setPaId(1);
            entity.setPaName("秦淮区");
            entity.setPfName("普天局楼");
            entity.setWorkRemarks("备注:" + i);
            list.add(entity);
        }
        for(int i = 0; i < 20; i++) {
            OrderEntity entity = new OrderEntity();
            entity.setJiFangName("卡子门机房");
            entity.setRequirFinishTime("20190425");
            entity.setOrderType("new");
            entity.setOrderNo("20190425xxxx");
            entity.setPaId(1);
            entity.setPaName("雨花区");
            entity.setPfName("雨花局楼");
            entity.setWorkRemarks("备注:" + i);
            list.add(entity);
        }
        for(int i = 0; i < 20; i++) {
            OrderEntity entity = new OrderEntity();
            entity.setJiFangName("随便机房");
            entity.setRequirFinishTime("20190425");
            entity.setOrderType("new");
            entity.setOrderNo("20190425xxxx");
            entity.setPaId(1);
            entity.setPaName("宣武区");
            entity.setPfName("玄武局楼");
            entity.setWorkRemarks("备注:" + i);
            list.add(entity);
        }
        return list;
    }
}
