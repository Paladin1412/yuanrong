package com.yuanrong.admin.dao.system;

import com.yuanrong.admin.bean.system.SystemMenu;
import com.yuanrong.admin.dao.BaseDaoI;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhonghang on 2018/4/19.
 */
@Repository
public interface SystemMenuDaoI extends BaseDaoI<SystemMenu> {

    List<SystemMenu> selectMenu(int adminUserId);
}
