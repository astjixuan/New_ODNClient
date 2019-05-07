package com.cn.nj.putian.newodnclient.widget.popup;

import com.samluys.filtertab.base.BaseFilterBean;

import java.util.List;

/**
 * 一级菜单
 */
public final class FilterAreaEntity extends BaseFilterBean {

    /**
     * 是否Grid样式的，默认不是
     */
    private boolean isGrid = false;

    /**
     * 名称
     */
    private String name;

    /**
     * 对应的ID
     */
    private int area_id;

    /**
     * 选择状态 0 未选择 1 选择
     */
    private int selected;

    /**
     * 二级分类数据
     */
    private List<BaseFilterBean> childAreas;

    /**
     * 组合筛选时，筛选的标题
     */
    private String titleString;

    public void setTitleString(String titleString) {
        this.titleString = titleString;
    }

    @Override
    public String getSortTitle() {
        return titleString;
    }

    public void setGrid(boolean grid) {
        isGrid = grid;
    }

    @Override
    public boolean isCanMulSelect() {
        return isGrid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public void setChildAreas(List<BaseFilterBean> childAreas) {
        this.childAreas = childAreas;
    }

    @Override
    public String getItemName() {
        return name;
    }

    @Override
    public int getId() {
        return area_id;
    }

    @Override
    public int getSelecteStatus() {
        return selected;
    }

    @Override
    public void setSelecteStatus(int status) {
        this.selected = status;
    }

    @Override
    public List<BaseFilterBean> getChildList() {
        return childAreas;
    }
}
