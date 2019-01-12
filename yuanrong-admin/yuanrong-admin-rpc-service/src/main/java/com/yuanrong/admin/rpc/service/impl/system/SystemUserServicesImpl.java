package com.yuanrong.admin.rpc.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.system.SystemMenu;
import com.yuanrong.admin.bean.system.SystemUser;
import com.yuanrong.admin.rpc.api.system.SystemUserServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.AESUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhonghang on 2018/4/11.
 */
@Service
public class SystemUserServicesImpl extends BaseServicesAbstract<SystemUser> implements SystemUserServicesI {

    @Override
    public SystemUser getById(Integer id) {
        return systemUserDaoI.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        systemUserDaoI.deleteById(id);
    }

    @Override
    public void save(SystemUser systemUser) {
        systemUserDaoI.save(systemUser);
    }

    @Override
    public List<SystemUser> getAll() {
        return systemUserDaoI.getAll();
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() ,baseModel.getRows() , "systemUserId desc");
        return new PageInfo<SystemUser>(systemUserDaoI.list(data));
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    @Override
    public SystemUser login(String userName, String password) {
        return systemUserDaoI.login(userName , AESUtil.aesEncode(password));
    }

    @Override
    public List<SystemMenu> getMenuInfosBySystemUser(SystemUser systemUser) {
        return systemUserDaoI.getMenuInfosBySystemUser(systemUser);
    }
}
