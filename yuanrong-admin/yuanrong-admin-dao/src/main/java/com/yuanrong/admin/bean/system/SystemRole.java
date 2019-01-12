package com.yuanrong.admin.bean.system;

import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;

/**
 * Created by zhonghang on 2018/4/17.
 * 系统角色
 */
public class SystemRole  extends BaseBean implements Serializable {
    private int systemRoleId;
    private String roleName;

    public int getSystemRoleId() {
        return systemRoleId;
    }

    public void setSystemRoleId(int systemRoleId) {
        this.systemRoleId = systemRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
