package com.yuanrong.admin.dao.system;

import com.yuanrong.admin.bean.system.SystemMenu;
import com.yuanrong.admin.bean.system.SystemUser;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhonghang on 2018/4/11.
 */
@Repository
public interface SystemUserDaoI extends BaseDaoI<SystemUser> {
    /**
     * 登录
     */
    SystemUser login(@Param("userName") String userName, @Param("passWord") String passWord);

    /**
     * 获取指定用户的拥有的菜单信息
     * @param systemUser
     * @return
     */
    List<SystemMenu> getMenuInfosBySystemUser(@Param("systemUser") SystemUser systemUser);
}
