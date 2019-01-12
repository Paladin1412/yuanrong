package com.yuanrong.admin.rpc.service.impl.system;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.system.SystemRole;
import com.yuanrong.admin.rpc.api.system.SystemRoleServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhonghang on 2018/4/19.
 */
@Service
public class SystemRoleServicesImpl extends BaseServicesAbstract<SystemRole> implements SystemRoleServicesI {
    @Override
    public SystemRole getById(Integer id) {
        return systemRoleDaoI.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        systemRoleDaoI.deleteById(id);
    }

    @Override
    public void save(SystemRole object) {
        systemRoleDaoI.save(object);
    }

    @Override
    public List<SystemRole> getAll() {
        return systemRoleDaoI.getAll();
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        return null;
    }
}
