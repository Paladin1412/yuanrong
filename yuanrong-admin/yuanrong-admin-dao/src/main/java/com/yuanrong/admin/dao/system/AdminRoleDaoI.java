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
public interface AdminRoleDaoI extends BaseDaoI<AdminRole> {

    /**
     * 系统角色
     * @return
     */
    List<AdminRole> listAdminRole();
}
