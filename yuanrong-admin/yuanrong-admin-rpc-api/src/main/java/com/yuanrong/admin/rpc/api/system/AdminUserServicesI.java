package com.yuanrong.admin.rpc.api.system;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.system.AdminRole;
import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.util.BaseModel;

import java.util.List;
import java.util.Map;

/**
 * Created by TZ on 2018/4/27.
 */
public interface AdminUserServicesI extends BaseServicesI<AdminUser>{


    List<Map<String,Object>> getRoleByID(Map<String,Object> map);
    /**
     * 登录
     * @param userName
     * @param passWord
     * @return
     */
      AdminUser login(String userName,String passWord);

     Map<String,Object> indexCnt(String underUserIds);

    /**
     * 通过ID获取其管理用户
     * @param adminUserID
     * @return
     */
    String getMyUnderling(int adminUserID);

    /**
     * 通过ID获取AdminUser
     * @param map
     * @return
     */
    Map<String,Object> getAdminUserById(Map<String,Object> map);

   AdminUser  getByRealNameOrIPhoneNum(Map<String,Object> map);

    void updateAdminUserByID(AdminUser adminUser);

    int saveKey(AdminUser adminUser);

    PageInfo listAdminUserByCondtion(AdminUser adminUser , BaseModel baseModel);
}
