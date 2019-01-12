package com.yuanrong.admin.rpc.service.impl.system;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.system.AdminRole;
import com.yuanrong.admin.rpc.api.system.AdminRoleServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminRoleServicesImpl  extends BaseServicesAbstract<AdminRole> implements AdminRoleServicesI {

    @Override
    public AdminRole getById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void save(AdminRole object) {

    }

    @Override
    public List<AdminRole> getAll() {
        return null;
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        return null;
    }

    @Override
    public List<AdminRole> listAdminRole() {
        return adminRoleDaoI.listAdminRole();
    }
}
