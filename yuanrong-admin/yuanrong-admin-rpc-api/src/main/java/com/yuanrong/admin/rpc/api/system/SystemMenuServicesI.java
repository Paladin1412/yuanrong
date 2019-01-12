package com.yuanrong.admin.rpc.api.system;


import com.yuanrong.admin.bean.system.SystemMenu;
import com.yuanrong.admin.rpc.BaseServicesI;

import java.util.List;

/**
 * Created by zhonghang on 2018/4/19.
 */
public interface SystemMenuServicesI extends BaseServicesI<SystemMenu> {

    public List<SystemMenu> selectMenu(int adminUserId);
}
