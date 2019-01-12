package com.yuanrong.admin.rpc.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.system.SystemMenu;
import com.yuanrong.admin.rpc.api.system.SystemMenuServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by zhonghang on 2018/4/19.
 */
@Service
public class SystemMenuServicesImpl extends BaseServicesAbstract<SystemMenu> implements SystemMenuServicesI {
    @Override
    public SystemMenu getById(Integer id) {
        return systemMenuDaoI.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        systemMenuDaoI.deleteById(id);
    }

    @Override
    public void save(SystemMenu object) {
        systemMenuDaoI.save(object);
    }

    @Override
    public List<SystemMenu> getAll() {
        return systemMenuDaoI.getAll();
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.offsetPage(baseModel.getCp() , baseModel.getRows());
        return new PageInfo<SystemMenu>(systemMenuDaoI.list(data));
    }

    @Override
    public List<SystemMenu> selectMenu(int adminUserId) {
        return systemMenuDaoI.selectMenu(adminUserId);
    }
}
