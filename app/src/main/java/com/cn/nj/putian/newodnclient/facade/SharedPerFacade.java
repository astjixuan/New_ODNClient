package com.cn.nj.putian.newodnclient.facade;

import android.content.Context;

import com.cn.nj.putian.newodnclient.base.common.CommonConst;
import com.cn.nj.putian.newodnclient.utils.SharedPreferencesUtils;
import com.cn.nj.putian.newodnclient.utils.StringUtils;

/**
 * SP的一些逻辑
 */
public final class SharedPerFacade {

    private Context mContext;
    private SharedPreferencesUtils spUtils;
    private static final String regularEx = ",";//分隔符

    public SharedPerFacade(Context context) {
        this.mContext = context;
        spUtils = SharedPreferencesUtils.getInstance(mContext);
    }

    /**
     * 保存服务器ip地址
     * @param ip ip地址
     */
    public void saveLastIp(String ip) {
        spUtils.saveStringVal(CommonConst.save_ip,ip);
    }

    /**
     * 读取服务器ip地址
     * @return
     */
    public String getLastIp() {
        return spUtils.getStringVal(CommonConst.save_ip);
    }

    /**
     * 保存webservice的端口号
     * @param port 端口号
     */
    public void saveIpPort(String port) {
        spUtils.saveStringVal(CommonConst.save_port,port);
    }

    /**
     * 读取服务器端口号
     * @return
     */
    public String getIpPort() {
        return spUtils.getStringVal(CommonConst.save_port);
    }

    /**
     * 保存登陆成功的名称
     * @param userName
     */
    public void saveUserName(String userName) {
        //得到之前保存的名称，如zhaol,zhou
        String names = getAllUserName();
        if(!StringUtils.isEmpty(names)) {

            String[] userAll = names.split(regularEx);
            for(String u : userAll) {
                if(u.equals(userName)) {
                    //跟原本保存的一样，就无需保存
                    return;
                }
            }
        }

        //保存新的名称
        spUtils.saveStringVal(CommonConst.save_username,names+userName+regularEx);
    }

    /**
     * 得到所有已经在用的名称(,已这个分割)
     * @return
     */
    public String getAllUserName() {
        String names = spUtils.getStringVal(CommonConst.save_username);//zhaol,zhou,xxx,xxx
        return names;
    }
}
