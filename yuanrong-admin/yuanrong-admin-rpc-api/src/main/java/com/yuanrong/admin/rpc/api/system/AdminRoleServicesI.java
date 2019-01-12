package com.yuanrong.admin.rpc.api.system;

import com.yuanrong.admin.bean.system.AdminRole;
import com.yuanrong.admin.rpc.BaseServicesI;

import java.util.List;

public interface AdminRoleServicesI extends BaseServicesI<AdminRole> {

    /**
     * 系统角色
     * @return
     */
    List<AdminRole> listAdminRole();
}
