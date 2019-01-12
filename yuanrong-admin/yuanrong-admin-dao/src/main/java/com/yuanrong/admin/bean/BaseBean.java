package com.yuanrong.admin.bean;


import com.yuanrong.admin.Enum.EnumBaseStatus;
import com.yuanrong.admin.bean.base.UserInfo;
import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.util.EnumUtil;

import java.util.Date;


/**
 * Created by zhonghang on 2018/4/11.
 */
public class BaseBean {
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 最后修改时间
     */
    private Date modifiedTime;
    /**
     * 修改次数
     */
    private int modifiedCount;

    /**
     * 备注
     */
    private String memo;

    /**
     * 状态
     */
    private EnumBaseStatus status;
    private Integer statusIndex;

    /**
     * 当前登录用户
     */
    private AdminUser currLoginUser;

    /**
     * 当前前台登录用户(废弃)
     */
    @Deprecated
    private RegisteredUserInfo currRegisteredUserInfo;
    private Integer registerUserInfoId;
    private Integer currRegisterUserInfoId;
    /**
     * 创建用户
     */
    private AdminUser createdAdminUser;

    /**
     * 当前前台登录用户
     */
    private UserInfo webUser;

    //选择控件1 2
    private String forward; //转向信息
    private String selectName1;
    private String selectName2;

    /**
     * 操作人
     */
    private String operator;
    /**
     * 是否查询全部
     */
    private int isSelectAll;

    public int getIsSelectAll() {
        return isSelectAll;
    }

    public void setIsSelectAll(int isSelectAll) {
        this.isSelectAll = isSelectAll;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public int getModifiedCount() {
        return modifiedCount;
    }

    public void setModifiedCount(int modifiedCount) {
        this.modifiedCount = modifiedCount;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public EnumBaseStatus getStatus() {
        return status;
    }

    public void setStatus(EnumBaseStatus status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = (EnumBaseStatus) EnumUtil.valueOf(EnumBaseStatus.class, status);
    }

    public Integer getStatusIndex() {
        return statusIndex;
    }

    public void setStatusIndex(Integer statusIndex) {
        this.status = (EnumBaseStatus) EnumUtil.valueOf(EnumBaseStatus.class, statusIndex);
        this.statusIndex=statusIndex;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getSelectName1() {
        return selectName1;
    }

    public void setSelectName1(String selectName1) {
        this.selectName1 = selectName1;
    }

    public String getSelectName2() {
        return selectName2;
    }

    public void setSelectName2(String selectName2) {
        this.selectName2 = selectName2;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public AdminUser getCurrLoginUser() {
        return currLoginUser;
    }

    public void setCurrLoginUser(AdminUser currLoginUser) {
        this.currLoginUser = currLoginUser;
    }

    public AdminUser getCreatedAdminUser() {
        return createdAdminUser;
    }

    public void setCreatedAdminUser(AdminUser createdAdminUser) {
        this.createdAdminUser = createdAdminUser;
    }

    public RegisteredUserInfo getCurrRegisteredUserInfo() {
        return currRegisteredUserInfo;
    }

    public void setCurrRegisteredUserInfo(RegisteredUserInfo currRegisteredUserInfo) {
        this.currRegisteredUserInfo = currRegisteredUserInfo;
    }

    public UserInfo getWebUser() {
        return webUser;
    }

    public void setWebUser(UserInfo webUser) {
        this.webUser = webUser;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getRegisterUserInfoId() {
        return registerUserInfoId;
    }

    public void setRegisterUserInfoId(Integer registerUserInfoId) {
        this.registerUserInfoId = registerUserInfoId;
    }

    public Integer getCurrRegisterUserInfoId() {
        return currRegisterUserInfoId;
    }

    public void setCurrRegisterUserInfoId(Integer currRegisterUserInfoId) {
        this.currRegisterUserInfoId = currRegisterUserInfoId;
    }
}
