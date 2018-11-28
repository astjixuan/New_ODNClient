package com.cn.nj.putian.newodnclient.utils;


import android.util.Log;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 简单的一些方法
 * Created by zhaolei on 2016/11/28.
 */
public final class StringUtils {

    //规定每段显示的长度
    private final static int LOG_MAXLENGTH = 100;

    /**
     * 打印log
     * @param TAG
     * @param msg
     */
    public static void e(String TAG, String msg) {
        int strLength = msg.length();
        int start = 0;
        int end = LOG_MAXLENGTH;
        for (int i = 0; i < 100; i++) {
            //剩下的文本还是大于规定长度则继续重复截取并输出
            if (strLength > end) {
                String val = msg.substring(start, end);
                Log.e(TAG + i, val);
                start = end;
                end = end + LOG_MAXLENGTH;
            } else {
                String val = msg.substring(start, strLength);
                Log.e(TAG, val);
                break;
            }
        }
    }

    /**
     * 合并数组
     * @param a 开始数组
     * @param b 截止数组
     * @return a + b 的合并数组
     */
    public static String[] concat(String[] a, String[] b) {
        String[] c = new String[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    /**
     * 是否为空
     * @param str
     * @return true是空，false不是空
     */
    public static boolean isEmpty(String str) {
        if (null == str) {
            return true;
        } else if (0 == str.trim().length()) {
            return true;
        } else if ("null".equals(str) || "NULL".equals(str)) {
            return true;
        }
        return false;
    }



    /**
     * 得到端口状态中文
     * @param portState
     * @return
     */
    public static String getPortStateVal(int portState) {
        String val = "";
        if (1 == portState) {
            val = "未完成";
        } else {
            val = "已完成";
        }
        return val;
    }


    /**
     * 工单详细信息时间格式调整
     * @param str
     * @return
     */
    public static String timeString(String str) {
        String dateString = null;
        SimpleDateFormat dateFormat_src = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat_des = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat_src.parse(str);
            dateString = dateFormat_des.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }

    /**
     * 60个uuid字符串，分割成2位一组的字符串
     * @param uuid
     * @return
     */
    public static List<String> getUUIDByStringList(String uuid) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < uuid.length(); i++) {
            String two = uuid.substring(i, i + 2);
            list.add(two);
            i = i + 1;
        }
        return list;
    }

    /**
     * 得到标签类型
     * @param str
     * @return
     */
    public static byte getLabelType(String str) {
        byte val = 0;
        if(str.equals("one")) {
            //单端(出),最普通的尾纤
            val = (byte)0x42;
        } else if(str.equals("one_in")) {
            //分光器（入）,肯定也是单端的
            val = (byte)0x44;
        } else if(str.equals("one_out")) {
            //分光器（出）,肯定也是单端的
            val = (byte)0x84;
        }
        return val;
    }


    /**
     * 把字符串2位一组分割，形成一个list
     * @param str
     * @return
     */
    public static List<String> getSplitTwoStringList(String str) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < str.length(); i++) {
            String two = str.substring(i, i + 2);
            list.add(two);
            i = i + 1;
        }
        return list;
    }

    /**
     * 将data字节型数据转换为0~255 (0xFF 即BYTE)。
     * @param data
     * @return
     */
    public static int getUnsignedByte(byte data) {
        return data & 0x0FF;
    }

    /**
     * eid类型执行中，返回的数据错误信息
     * @param type
     * @return
     */
    public static String getEidWrongType(byte type) {
        String val = "";
        switch (type) {
            case (byte)0x00:
                val = "执行成功";
                break;
            case (byte)0x01:
                val = "不是该设备的工单(uuid不同)";
                break;
            case (byte)0x02:
                val = "已有此类型的工单，正在执行";
                break;
            case (byte)0x03:
                //主控派发失败
                val = "下发数据中，有框失联，请查询网管告警信息(主控派发失败)";
                break;
            case (byte)0x04:
                //接口板派发失败
                val = "下发数据中，有托盘失联，请查询网管告警信息(接口板派发失败)";
                break;
            case (byte)0x05:
                //工单中端口异常
                val = "下发数据中，有端口异常，请查询网管告警信息";
                break;
            case (byte)0x06:
                //架间通信异常
                val = "架间通信异常";
                break;
            case (byte)0x07:
                //工单确认命令失败
                val = "工单中有端口确认失败，所有端口已恢复成执行前状态";
                break;

            default:
                break;
        }
        return val;
    }

    /**
     * 机框排列方式
     * @param caseRank
     * @return
     */
    public static String getCaseRankString(int caseRank) {
        String val = "";
        if(1 == caseRank) {
            val = "从上向下";
        } else {
            val = "从下向上";
        }
        return val;
    }

    /**
     * 是否成功
     * @param isSuccess 1,成功。2，失败
     * @return
     */
    public static String getIsSuccessVal(int isSuccess) {
        String val = "";
        if(1 == isSuccess) {
            val = "成功";
        } else {
            val = "失败";
        }
        return val;
    }

    /**
     * (施工人员)得到工单状态中文
     * @param orderState
     * @return
     */
    public static String getOrderStateVal(String orderState) {
        String val = "";
        if (orderState.equals("0")) {
            val = "";
        } else if (orderState.equals("1")) {
            val = "完成没上交";
        } else if (orderState.equals("2")) {
            val = "提交失败";
        } else if (orderState.equals("3")) {
            val = "追单";
        } else if (orderState.equals("4")) {
            val = "故障";
        } else if (orderState.equals("8")) {
            val = "半完工";
        } else if(orderState.equals("12")) {
            val = "工单异常";
        }
        return val;
    }

    /**
     * 得到设备类型----华为协议中定义
     * @param machineTypeNum
     * @return 0:0df,1:occ,2:odb
     */
    public static String getDeviceType(byte machineTypeNum) {
        String val = "";
        if (machineTypeNum == (byte)0x01) {
            val = "0"; //ODF
        } else if (machineTypeNum == (byte)0x02) {
            val = "1"; //OCC
        } else {
            val = "2"; //ODB
        }
        return val;
    }

    /**
     * 根据机框编号方式类型值，得到机框编号方式字符串
     * @param caseReadSeqByte 0x03：从上向下；0x04：从下向上；
     * @return (3或者4)
     */
    public static String getCaseReadSeqByNum(byte caseReadSeqByte) {
        String val = "";
        if (caseReadSeqByte == (byte)0x03) {
            val = "3";
        } else {
            val = "4";
        }
        return val;
    }

    /**
     * 得到告警类型的中文
     * @param type
     * @return 中文说明
     */
    public static String getWarningTypeString(int type) {
        String val = "";
        switch (type) {
            case 1:
                val = "电子标签异常拔出";
                break;

            case 2:
                val = "电子标签非法插入";
                break;

            case 3:
                val = "板、盘异常拔出（通信中断）";
                break;

            case 4:
                val = "板、盘非法插入";
                break;

            case 5:
                val = "软件升级失败";
                break;

            default:
                break;
        }
        return val;
    }

    /**
     * 得到告警状态的中文
     * @param state
     * @return 中文说明
     */
    public static String getWarningStateString(int state) {
        String val = "";
        switch (state) {
            case 1:
                val = "告警产生";
                break;

            case 2:
                val = "告警消除";
                break;

            default:
                break;
        }
        return val;
    }

    /**
     * 得到分光器or普通端口的中文
     * @param isFeng
     * @return 中文说明
     */
    public static String getIsFengString(int isFeng) {
        String val = "";
        switch (isFeng) {
            case 0:
                val = "普通端口";
                break;

            case 1:
                val = "分光器";
                break;
        }
        return val;
    }

    /**
     * 得到跳纤还是光缆
     * @param type
     * @return 中文说明
     */
    public static String getOpString(int type) {
        String val = "";
        switch (type) {
            case 1:
                val = "光缆";
                break;
            case 2:
                val = "跳纤";
                break;
            case 3:
                val = "OLT";
                break;
            case 4:
                val = "ONU";
                break;
        }
        return val;
    }

    /**
     * 按照格式，组织eid码
     * @param eidCode_new 32位eid码
     * @return
     */
    public static String getEidString(String eidCode_new) {
        if(null != eidCode_new && !"".equals(eidCode_new)) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(eidCode_new.substring(0, 2) + "-");// 版本号
            buffer.append(eidCode_new.substring(2, 4) + "-");// 类型
            buffer.append(eidCode_new.substring(4, 10) + "-");// 厂商
            buffer.append(eidCode_new.substring(10, 42) + "-");// eid码
            buffer.append(eidCode_new.substring(42, 44) + "-");// 端口序号
            buffer.append(eidCode_new.substring(44, 46) + "-");// 入端口数
            buffer.append(eidCode_new.substring(46, 48) + "-");// 出端口数
            buffer.append(eidCode_new.substring(48, 50) + "-");// 运营商标识
            buffer.append(eidCode_new.substring(50, 58) + "-");// 运营商保留
            buffer.append(eidCode_new.substring(58, 62) + "-");// 预留
            buffer.append(eidCode_new.substring(62, 64));// crc
            return buffer.toString();
        } else {
            return "";
        }
    }

    /**
     * 得到下个二维码
     * @param code 当前二维码
     * @param seq 第几个码(0,1,3)
     * @return
     */
    public static String nextQrcode(String code,int seq) {
        String str = "";
        Long temp = Long.valueOf(code,36);
        temp = temp+seq;
        str = Long.toString(temp,36);
        String a = "";
        for(int i = 0;i<10-str.length();i++) {
            a+="0";
        }
        str = a+str;
        return str.toUpperCase();
    }

    /**
     * eid端口类型中文
     * @param type
     * @return
     */
    public static String getEidPortTypeString(byte type) {
        String val = "";
        switch (type) {
            case (byte)0x42:
                val = "单端跳纤";
                break;
            case (byte)0x41:
            case (byte)0x81:
                val = "双端标签";
                break;
            case (byte)0x44:
                val = "分光器(入)";
                break;
            case (byte)0x84:
                val = "分光器(出)";
                break;

            default:
                break;
        }
        return val;
    }

    /**
     * 得到时间（格式为yyyy-MM-dd 2012-01-01）
     * @param year 年
     * @param monthOfYear 月份的值在0-11之间
     * @param dayOfMonth 日
     * @return 2012-01-01
     */
    public static String getDateTime(int year, int monthOfYear, int dayOfMonth) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(year,monthOfYear,dayOfMonth);
        String time = format.format(c.getTime());
        return time;
    }

    /**
     * 哪个端口是单端
     * @param salverType
     * @return 真是单端,假是双端
     */
//    public static boolean whichPortIsDanDuan(String salverType) {
//        boolean result = false;
//        if(CommonConst.mianTiaoDuanKou.equals(salverType) ||
//                CommonConst.rongXianPan.equals(salverType) ||
//                CommonConst.heShiFenGuangQi.equals(salverType) ||
//                CommonConst.weiGuangFenGuangQi.equals(salverType) ||
//                CommonConst.tuoPanWeiXianFenGuangQi.equals(salverType)) {
//            result = true;
//        } else {
//            result = false;
//        }
//        return result;
//    }

    /**
     * 得到跳线种类中文
     * @param jumperKind
     * @return
     */
//    public static String getjumperKindVal(String jumperKind) {
//        String val = "";
//        if (jumperKind.equals(CommonConst.QRCODE)) {
//            val = "二维码";
//        } else if (jumperKind.equals(CommonConst.INDICATION)) {
//            val = "指示标签";
//        } else if (jumperKind.equals(CommonConst.ELECTRONICS)) {
//            val = "全电子";
//        } else {
//            val = "RFID";
//        }
//        return val;
//    }

    /**
     * 得到设备类型----华为协议中定义
     * @param machineTypeString
     * @return 0:0df,1:occ,2:odb
     */
//    public static String getDeviceType1(String machineTypeString) {
//        String val = "";
//        if (CommonConst.MachineType_shelf_2.equalsIgnoreCase(machineTypeString)) {
//            val = "0"; //ODF
//        } else if (CommonConst.MachineType_opticalconnect_2.equalsIgnoreCase(machineTypeString)) {
//            val = "1"; //OCC
//        } else {
//            val = "2"; //ODB
//        }
//        return val;
//    }

    /**
     * 根据设备类型值，得到设备类型
     * @param machineTypeNum 0x01：ODF；0x02：OCC；0x03：ODB
     * @return (ODF；OCC；ODB)
     */
//    public static String getMachineTypeByNum(byte machineTypeNum) {
//        String val = "";
//        if (machineTypeNum == (byte)0x01) {
//            val = CommonConst.MachineType_shelf_2; //ODF
//        } else if (machineTypeNum == (byte)0x02) {
//            val = CommonConst.MachineType_opticalconnect_2; //OCC
//        } else {
//            val = CommonConst.MachineType_dividingbox_2; //ODB
//        }
//        return val;
//    }

    /**
     * 得到工单类型中文
     * @param orderType
     * @return
     */
//    public static String getOrderTypeVal(String orderType) {
//        String val = "";
//        if (orderType.equals(CommonConst.orderType_new)
//                || orderType.equals(CommonConst.orderType_new_four)
//                || orderType.equals(CommonConst.orderType_new_three)) {
//            val = "新建";
//        } else if (orderType.equals(CommonConst.orderType_change)
//                || orderType.equals(CommonConst.orderType_change_four)
//                || orderType.equals(CommonConst.orderType_change_three)) {
//            val = "改跳";
//        } else if (orderType.equals(CommonConst.orderType_del)
//                || orderType.equals(CommonConst.orderType_del_four)
//                || orderType.equals(CommonConst.orderType_del_three)) {
//            val = "拆除";
//        } else if (orderType.equals(CommonConst.orderType_polling)) {
//            val = "巡检";
//        } else if(orderType.equals(CommonConst.orderType_gather)){
//            val = "采集";
//        } else if(orderType.equals(CommonConst.orderType_B_NEW)) {
//            val = "批量新建";
//        } else if(orderType.equals(CommonConst.orderType_2B_NEW)) {
//            val = "批量新建(架间)";
//        } else if(orderType.equals(CommonConst.orderType_setting)) {
//            val = "配置工单";
//        } else if(orderType.equals(CommonConst.orderType_3B_NEW)
//                || orderType.equals(CommonConst.orderType_4B_NEW)) {
//            val = "双纤新建";
//        } else if(orderType.equals(CommonConst.orderType_SWAP)) {
//            val = "调纤工单";
//        } else {
//            val = "软件升级";
//        }
//        return val;
//    }

    /**
     * 根据设备类型英文，得到中文的设备类型
     * @param machineTypeString
     * @return
     */
//    public static String getMachineTypeByString(String machineTypeString) {
//        String val = "";
//        if (machineTypeString.equals("1")
//                || CommonConst.MachineType_shelf.equals(machineTypeString)
//                || CommonConst.MachineType_shelf_2.equalsIgnoreCase(machineTypeString)) {
//            val = "机架";
//        } else if (machineTypeString.equals("2")
//                || CommonConst.MachineType_opticalconnect
//                .equals(machineTypeString)
//                || CommonConst.MachineType_opticalconnect_2
//                .equalsIgnoreCase(machineTypeString)) {
//            val = "光交";
//        } else if (machineTypeString.equals("3")
//                || CommonConst.MachineType_dividingbox
//                .equals(machineTypeString)
//                || CommonConst.MachineType_dividingbox_2
//                .equalsIgnoreCase(machineTypeString)) {
//            val = "分纤箱";
//        }
//        return val;
//    }

}
