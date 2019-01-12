package com.yuanrong.admin.rpc.service.impl.system;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.system.AdminRole;
import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.rpc.api.system.AdminUserServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.MD5Util;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/27.
 */
@Service
public class AdminUserServicesImpl extends BaseServicesAbstract<AdminUser> implements AdminUserServicesI {

    @Override
    public List<Map<String, Object>> getRoleByID(Map<String, Object> map) {
        return adminUserDaoI.getRoleByID(map);
    }

    /**
     * 登录p
     * @param UserName
     * @param PassWord
     * @return
     */
    @Override
    public AdminUser login(String UserName, String PassWord) {
        //后台加密
        PassWord = MD5Util.md5(MD5Util.md5(PassWord+"#yr"));
        return adminUserDaoI.login(UserName, PassWord);
    }

    @Override
    public Map<String, Object> indexCnt(String underUserIds) {

        return adminUserDaoI.indexCnt(underUserIds);
    }

    @Override
    public String getMyUnderling(int adminUserID) {
        return adminUserDaoI.getMyUnderling(adminUserID);
    }

    /**
     * 通过ID获取AdminUser
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> getAdminUserById(Map<String, Object> map) {
        return adminUserDaoI.getAdminUserById(map);
    }

    @Override
    public AdminUser getByRealNameOrIPhoneNum(Map<String, Object> map) {
        return adminUserDaoI.getByRealNameOrIPhoneNum(map);
    }

    @Override
    public void updateAdminUserByID(AdminUser adminUser) {
        adminUserDaoI.updateAdminUserByID(adminUser);
    }

    @Override
    public int saveKey(AdminUser adminUser) {
        adminUserDaoI.save(adminUser);
        return adminUser.getRecID();
    }

    @Override
    public PageInfo listAdminUserByCondtion(AdminUser adminUser, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp(),baseModel.getRows()," StatusValue  desc ,RecID desc");
        return new PageInfo<AdminUser>(adminUserDaoI.listAdminUserByCondtion(adminUser));
    }


    @Override
    public AdminUser getById(Integer id) {
        return adminUserDaoI.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        adminUserDaoI.deleteById(id);
    }

    @Override
    public void save(AdminUser adminUser) {
        adminUserDaoI.save(adminUser);
    }

    @Override
    public List<AdminUser> getAll() {
        return adminUserDaoI.getAll();
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp(),baseModel.getRows(),"RecID desc");
        return new PageInfo<AdminUser>(adminUserDaoI.list(data));
    }
}
