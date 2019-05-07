package com.cn.nj.putian.newodnclient.model.entity;

import java.io.Serializable;

/**
 * 工单端口实体
 */
public final class OrderPortEntity implements Serializable {

    private String orderNo ; //-- 工单号

    private String portNo ; //-- 工单端口编号（1、2、3、4）

    private int machineId ; // -- 设备id

    private String machineCode ; // -- 设备码

    private String machineName ; // -- 设备名称

    private String portNum ; //-- 端口号

    private String portCode ; //-- 端口码

    private String jumperCode ;  //-- 跳纤码

    private String jifangName ; // -- 机房名

    private int portId ; // -- 端口id

    private String faceName ; //-- 面的名称

    private String regionName ; //-- 区/列的名称

    private String caseName ; //-- 框的名称

    private String caseTypeName ; //-- 框的类型名称

    private String salverTypeName ;  //-- 托盘类型

    private String salverName ; //-- 托盘名称

    private String portState; //端口状态 1表示未完成, 2表示完成 (施工人员类特有字段)

    private int SalverAlign; //托盘横竖

    private String machineUUID; //电子设备的UUID

    private String machineIP; //电子设备ip地址,设备端口号(192.168.8.45-60001)

    private int eidCaseSeq; //读取框的顺序（框的映射）

    private String portEid; //端口eid码

    private String splitRatio;//分光比(只有光分路器用,如"1-8")

    private String portShuoMing; //端口说明 ----页面使用，不存在数据库中

    private double longitude;//经度

    private double latitude;//纬度

    private String portAddr;//端口位置(发给底层用)

    private String caseAddr;//框位置(发给底层用)

    private String salverAddr;//托盘位置(发给底层用)

    private String portRemarks;//端口备注信息

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPortNo() {
        return portNo;
    }

    public void setPortNo(String portNo) {
        this.portNo = portNo;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getPortNum() {
        return portNum;
    }

    public void setPortNum(String portNum) {
        this.portNum = portNum;
    }

    public String getPortCode() {
        return portCode;
    }

    public void setPortCode(String portCode) {
        this.portCode = portCode;
    }

    public String getJumperCode() {
        return jumperCode;
    }

    public void setJumperCode(String jumperCode) {
        this.jumperCode = jumperCode;
    }

    public String getJifangName() {
        return jifangName;
    }

    public void setJifangName(String jifangName) {
        this.jifangName = jifangName;
    }

    public int getPortId() {
        return portId;
    }

    public void setPortId(int portId) {
        this.portId = portId;
    }

    public String getFaceName() {
        return faceName;
    }

    public void setFaceName(String faceName) {
        this.faceName = faceName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseTypeName() {
        return caseTypeName;
    }

    public void setCaseTypeName(String caseTypeName) {
        this.caseTypeName = caseTypeName;
    }

    public String getSalverTypeName() {
        return salverTypeName;
    }

    public void setSalverTypeName(String salverTypeName) {
        this.salverTypeName = salverTypeName;
    }

    public String getSalverName() {
        return salverName;
    }

    public void setSalverName(String salverName) {
        this.salverName = salverName;
    }

    public String getPortState() {
        return portState;
    }

    public void setPortState(String portState) {
        this.portState = portState;
    }

    public int getSalverAlign() {
        return SalverAlign;
    }

    public void setSalverAlign(int salverAlign) {
        SalverAlign = salverAlign;
    }

    public String getMachineUUID() {
        return machineUUID;
    }

    public void setMachineUUID(String machineUUID) {
        this.machineUUID = machineUUID;
    }

    public String getMachineIP() {
        return machineIP;
    }

    public void setMachineIP(String machineIP) {
        this.machineIP = machineIP;
    }

    public int getEidCaseSeq() {
        return eidCaseSeq;
    }

    public void setEidCaseSeq(int eidCaseSeq) {
        this.eidCaseSeq = eidCaseSeq;
    }

    public String getPortEid() {
        return portEid;
    }

    public void setPortEid(String portEid) {
        this.portEid = portEid;
    }

    public String getSplitRatio() {
        return splitRatio;
    }

    public void setSplitRatio(String splitRatio) {
        this.splitRatio = splitRatio;
    }

    public String getPortShuoMing() {
        return portShuoMing;
    }

    public void setPortShuoMing(String portShuoMing) {
        this.portShuoMing = portShuoMing;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getPortAddr() {
        return portAddr;
    }

    public void setPortAddr(String portAddr) {
        this.portAddr = portAddr;
    }

    public String getCaseAddr() {
        return caseAddr;
    }

    public void setCaseAddr(String caseAddr) {
        this.caseAddr = caseAddr;
    }

    public String getSalverAddr() {
        return salverAddr;
    }

    public void setSalverAddr(String salverAddr) {
        this.salverAddr = salverAddr;
    }

    public String getPortRemarks() {
        return portRemarks;
    }

    public void setPortRemarks(String portRemarks) {
        this.portRemarks = portRemarks;
    }
}
