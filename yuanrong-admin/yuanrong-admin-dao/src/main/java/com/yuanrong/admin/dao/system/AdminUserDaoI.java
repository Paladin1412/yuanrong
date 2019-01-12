package com.yuanrong.admin.dao.system;

import com.yuanrong.admin.bean.system.AdminRole;
import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by TZ on 2018/4/27.
 */
@Repository
public interface AdminUserDaoI extends BaseDaoI<AdminUser> {

    /**
     * 通过ID查找权限
     * @param map 用户ID
     * @return 该ID下所有的权限
     */
    List<Map<String,Object>> getRoleByID(Map<String,Object> map);
    /**
     * 登录
     */
    AdminUser login(@Param("userName") String IPhoneNum, @Param("passWord") String PassWord);

    /**
     * 首页指标统计
     * @return
     */
    Map<String, Object> indexCnt(@Param("underUserIds") String underUserIds);
    List<AdminUser> findListByRoleID(Integer roleID);

    /**
     * 通过ID获取其管理用户
     * @param adminUserID
     * @return
     */
    String getMyUnderling(@Param("adminUserID") int adminUserID);
    /**
     * 通过ID获取AdminUser
     * @param map
     * @return
     */
    Map<String,Object> getAdminUserById(Map<String,Object> map);

    AdminUser  getByRealNameOrIPhoneNum(Map<String,Object> map);

    void updateAdminUserByID(AdminUser adminUser);

    /**
     * 系统角色
     * @return
     */
    List<AdminRole> listAdminRole();

   List<AdminUser> listAdminUserByCondtion(@Param("data")AdminUser adminUser);
}
