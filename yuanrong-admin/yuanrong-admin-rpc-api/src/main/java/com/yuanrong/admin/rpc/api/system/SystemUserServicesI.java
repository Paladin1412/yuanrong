package com.yuanrong.admin.rpc.api.system;


import com.yuanrong.admin.bean.system.SystemMenu;
import com.yuanrong.admin.bean.system.SystemUser;
import com.yuanrong.admin.rpc.BaseServicesI;

import java.util.List;

/**
 * Created by zhonghang on 2018/4/11.
 */
public interface SystemUserServicesI extends BaseServicesI<SystemUser> {
    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    public SystemUser login(String userName, String password);

    /**
     * 获取指定用户的拥有的菜单信息
     * @param systemUser
     * @return
     */
    List<SystemMenu> getMenuInfosBySystemUser(SystemUser systemUser);

}
