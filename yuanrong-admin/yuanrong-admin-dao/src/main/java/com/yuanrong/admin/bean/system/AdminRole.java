package com.yuanrong.admin.bean.system;

import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;
import java.util.Date;
/**
* @Description:    角色表（业务和权限）
* @Author:         ShiLinghuai
* @CreateDate:     2018/5/4 15:31
* @UpdateUser:     ShiLinghuai
* @UpdateDate:     2018/5/4 15:31
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class AdminRole implements Serializable {
    private int RoleID;
    private String RoleName;
    private int Status;
    private Date CreatedTime;
    private Date modifiedTime;
    private int modifiedCount;
    private String memo;
    private static final long serialVersionUID = 1L;

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public Date getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(Date createdTime) {
        CreatedTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public int getModifiedCount() {
        return modifiedCount;
    }

    public void setModifiedCount(int modifiedCount) {
        this.modifiedCount = modifiedCount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getRoleID() {

        return RoleID;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
    }
}
