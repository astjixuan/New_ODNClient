package com.cn.nj.putian.newodnclient.widget.tangramview;

import android.content.Context;

import com.cn.nj.putian.newodnclient.R;
import com.cn.nj.putian.newodnclient.widget.popup.FilterAreaEntity;
import com.cn.nj.putian.newodnclient.widget.popup.FilterChildAreasEntity;
import com.samluys.filtertab.FilterInfoBean;
import com.samluys.filtertab.FilterResultBean;
import com.samluys.filtertab.FilterTabConfig;
import com.samluys.filtertab.FilterTabView;
import com.samluys.filtertab.base.BaseFilterBean;
import com.samluys.filtertab.listener.OnSelectResultListener;
import com.tmall.wireless.tangram.structure.BaseCell;

import java.util.ArrayList;
import java.util.List;

/**
 * 工单筛选页面(type=3)
 */
public final class OrderScreenView extends BaseItemView{

    private FilterTabView filterTabView;

    public OrderScreenView(Context context) {
        super(context);
    }

    @Override
    public void initView() {
        inflate(getContext(), R.layout.order_title_view, this);
        filterTabView = findViewById(R.id.ftb_filter);
    }

    @Override
    public void postBindView(BaseCell cell) {
        super.postBindView(cell);
        initFilter();
    }

    private void initFilter() {
        //控件调用之前最好初始化一下，避免加载失败重新加载数据是出现重复的问题。
        filterTabView.removeViews();
        //导入数据
        List<BaseFilterBean> list1 = initFilterEntity();

        List<BaseFilterBean> list2 =  getPRICEList();

        List<BaseFilterBean> list3 = getSingleList();

        List<BaseFilterBean> list4 =  getZuHeList();

        //支持区域二级联动选择
        FilterInfoBean bean1 = new FilterInfoBean("区域", FilterTabConfig.FILTER_TYPE_AREA, list1);
        //支持带EditText的单行选择
        FilterInfoBean bean2 = new FilterInfoBean("总价", FilterTabConfig.FILTER_TYPE_PRICE, list2);
        //支持单行List样式选择
        FilterInfoBean bean3 = new FilterInfoBean("户型", FilterTabConfig.FILTER_TYPE_SINGLE_SELECT, list3);
        //支持多类型选择
        FilterInfoBean bean4 = new FilterInfoBean("筛选", FilterTabConfig.FILTER_TYPE_MUL_SELECT, list4);
        //支持Grid样式多选
        FilterInfoBean bean5 = new FilterInfoBean("Grid", FilterTabConfig.FILTER_TYPE_SINGLE_GIRD, list3);

        filterTabView.addFilterItem(bean1.getTabName(), bean1.getFilterData(), bean1.getPopupType(), 0);
        filterTabView.addFilterItem(bean2.getTabName(), bean2.getFilterData(), bean2.getPopupType(), 1);
        filterTabView.addFilterItem(bean3.getTabName(), bean3.getFilterData(), bean3.getPopupType(), 2);
        filterTabView.addFilterItem(bean4.getTabName(), bean4.getFilterData(), bean4.getPopupType(), 3);
        filterTabView.addFilterItem(bean5.getTabName(), bean5.getFilterData(), bean5.getPopupType(), 4);

        filterTabView.setOnSelectResultListener(new OnSelectResultListener() {
            @Override
            public void onSelectResult(FilterResultBean resultBean) {

            }

            @Override
            public void onSelectResultList(List<FilterResultBean> resultBeans) {

            }
        });
    }

    private List<BaseFilterBean> initFilterEntity() {
        List<BaseFilterBean> listAll = new ArrayList<>();

        FilterAreaEntity entity1 = new FilterAreaEntity();
        entity1.setArea_id(-1);
        entity1.setName("不限");
        entity1.setSelecteStatus(0);
        listAll.add(entity1);

        FilterAreaEntity entity2 = new FilterAreaEntity();
        entity2.setArea_id(1);
        entity2.setName("江宁区");
        entity2.setSelecteStatus(0);

        List<BaseFilterBean> childList1 = new ArrayList<>();
        FilterChildAreasEntity childInfo = new FilterChildAreasEntity();
        childInfo.setName("不限");
        childInfo.setChildId(-1);
        childInfo.setSelecteStatus(0);
        childList1.add(childInfo);

        FilterChildAreasEntity childInfo2 = new FilterChildAreasEntity();
        childInfo2.setName("江宁金鹰");
        childInfo2.setChildId(30);
        childInfo2.setSelecteStatus(0);
        childList1.add(childInfo2);

        FilterChildAreasEntity childInfo3 = new FilterChildAreasEntity();
        childInfo3.setName("砂之船");
        childInfo3.setChildId(31);
        childInfo3.setSelecteStatus(0);
        childList1.add(childInfo3);
        entity2.setChildAreas(childList1);

        listAll.add(entity2);

        FilterAreaEntity entity3 = new FilterAreaEntity();
        entity3.setArea_id(2);
        entity3.setName("雨花区");
        entity3.setSelecteStatus(0);

        List<BaseFilterBean> childList2 = new ArrayList<>();

        FilterChildAreasEntity childInfo4 = new FilterChildAreasEntity();
        childInfo4.setName("雨花客厅");
        childInfo4.setChildId(33);
        childInfo4.setSelecteStatus(0);
        childList2.add(childInfo4);

        FilterChildAreasEntity childInfo5 = new FilterChildAreasEntity();
        childInfo5.setName("虹悦城");
        childInfo5.setChildId(34);
        childInfo5.setSelecteStatus(0);
        childList2.add(childInfo5);

        FilterChildAreasEntity childInfo6 = new FilterChildAreasEntity();
        childInfo6.setName("夫子庙");
        childInfo6.setChildId(35);
        childInfo6.setSelecteStatus(0);
        childList2.add(childInfo6);

        entity3.setChildAreas(childList2);

        listAll.add(entity3);
        return listAll;
    }

    private List<BaseFilterBean> getSingleList() {
        List<BaseFilterBean> listAll = new ArrayList<>();
        for(int i = 1; i < 7; i++) {
            FilterAreaEntity info = new FilterAreaEntity();
            if(i == 1) {
                info.setName("不限");
                info.setSelecteStatus(1);
            } else {
                info.setName(i+"室");
                info.setSelecteStatus(0);
            }
            info.setArea_id(i);
            listAll.add(info);
        }
        return listAll;
    }

    private List<BaseFilterBean> getPRICEList() {
        List<BaseFilterBean> listAll = new ArrayList<>();
        for(int i = 1; i < 7; i++) {
            FilterAreaEntity info = new FilterAreaEntity();
            if(i == 1) {
                info.setName("不限");
                info.setSelecteStatus(1);
            } else {
                info.setName(i+" 80-100万");
                info.setSelecteStatus(0);
            }
            info.setArea_id(i);
            listAll.add(info);
        }
        return listAll;
    }

    //组合的list
    private List<BaseFilterBean> getZuHeList() {
        List<BaseFilterBean> listAll = new ArrayList<>();

        FilterAreaEntity paiXu = new FilterAreaEntity();
        paiXu.setTitleString("排序");
        paiXu.setArea_id(-1);
        paiXu.setSelecteStatus(0);

        List<BaseFilterBean> paiChild = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            FilterAreaEntity info = new FilterAreaEntity();
            if(i == 0) {
                info.setName("不限");
                info.setSelecteStatus(1);
                info.setArea_id(-1);
            } else {
                info.setName("总价从低到高 " + i);
                info.setSelecteStatus(0);
                info.setArea_id(i);
            }
            paiChild.add(info);
        }
        paiXu.setChildAreas(paiChild);
        listAll.add(paiXu);

        FilterAreaEntity mianJi = new FilterAreaEntity();
        mianJi.setTitleString("面积");
        mianJi.setArea_id(-1);
        mianJi.setSelecteStatus(0);

        List<BaseFilterBean> mianChild = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            FilterAreaEntity info = new FilterAreaEntity();
            if(i == 0) {
                info.setName("不限");
                info.setSelecteStatus(1);
                info.setArea_id(-1);
            } else {
                info.setName("50平米以下 " + i);
                info.setSelecteStatus(0);
                info.setArea_id(i);
            }
            mianChild.add(info);
        }
        mianJi.setChildAreas(mianChild);
        listAll.add(mianJi);

        return listAll;
    }
}
