package com.cn.nj.putian.newodnclient.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 *  工单实体
 */
public final class OrderEntity implements Serializable {

    private String orderNo ; //-- 工单号

    private String orderState ; //-- 工单状态

    private String orderType ; //-- 工单类型

    private String workGroup ; //工作组(管理员账户)

    private String workAccount ; //-- 工单执行人员

    private String machineType ; //-- 工单所属设备类型

    private String paName ; //-- 局所name(区域，如建邺区)范围大

    private String pfName ; //-- 局楼name(详细在那座楼里，如某某小区)范围中等

    private String jiFangName; //-- 机房名称（目前大部分工单都在一个机房内完成）

    private String jumperType ; //-- 跳纤类型(单端or双端)

    private String jumperKind ; //-- 跳线种类(电子or二维码)

    private String workRemarks ; //-- 工单备注

    private String finishTime ; //-- 实际完成日期 (施工人员类特有字段)

    private String orderTime ;// -- 下单日期

    private String requirFinishTime ; //-- 建议完成日期

    private String unFinishRemarks ; //-- 未完成原因

    private int paId; //--局所id(用于本地排序)

    private List<OrderPortEntity> portList = null;

    public List<OrderPortEntity> getPortList() {
        return portList;
    }

    public void setPortList(List<OrderPortEntity> portList) {
        this.portList = portList;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getWorkGroup() {
        return workGroup;
    }

    public void setWorkGroup(String workGroup) {
        this.workGroup = workGroup;
    }

    public String getWorkAccount() {
        return workAccount;
    }

    public void setWorkAccount(String workAccount) {
        this.workAccount = workAccount;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getPaName() {
        return paName;
    }

    public void setPaName(String paName) {
        this.paName = paName;
    }

    public String getPfName() {
        return pfName;
    }

    public void setPfName(String pfName) {
        this.pfName = pfName;
    }

    public String getJumperType() {
        return jumperType;
    }

    public void setJumperType(String jumperType) {
        this.jumperType = jumperType;
    }

    public String getJumperKind() {
        return jumperKind;
    }

    public void setJumperKind(String jumperKind) {
        this.jumperKind = jumperKind;
    }

    public String getWorkRemarks() {
        return workRemarks;
    }

    public void setWorkRemarks(String workRemarks) {
        this.workRemarks = workRemarks;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getRequirFinishTime() {
        return requirFinishTime;
    }

    public void setRequirFinishTime(String requirFinishTime) {
        this.requirFinishTime = requirFinishTime;
    }

    public String getUnFinishRemarks() {
        return unFinishRemarks;
    }

    public void setUnFinishRemarks(String unFinishRemarks) {
        this.unFinishRemarks = unFinishRemarks;
    }

    public int getPaId() {
        return paId;
    }

    public void setPaId(int paId) {
        this.paId = paId;
    }

    public String getJiFangName() {
        return jiFangName;
    }

    public void setJiFangName(String jiFangName) {
        this.jiFangName = jiFangName;
    }
}
