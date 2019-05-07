package com.cn.nj.putian.newodnclient.widget.popup;

import com.samluys.filtertab.base.BaseFilterBean;

//二级菜单
public final class FilterChildAreasEntity extends BaseFilterBean {

    /**
     * 街道名称
     */
    private String name;

    /**
     * 对应的ID
     */
    private int childId;

    /**
     * 选择状态
     */
    private int selected;

    public void setName(String name) {
        this.name = name;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    @Override
    public String getItemName() {
        return name;
    }

    @Override
    public int getId() {
        return childId;
    }

    @Override
    public int getSelecteStatus() {
        return selected;
    }

    @Override
    public void setSelecteStatus(int status) {
        this.selected = status;
    }
}
