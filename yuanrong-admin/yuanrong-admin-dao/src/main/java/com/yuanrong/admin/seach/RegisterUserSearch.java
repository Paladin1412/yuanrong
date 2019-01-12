package com.yuanrong.admin.seach;

import com.yuanrong.admin.bean.system.AdminUser;

import java.io.Serializable;
/**
* @Description:   条件查询的条件
* @Author:         ShiLinghuai
* @CreateDate:     2018/5/7 11:53
* @UpdateUser:     ShiLinghuai
* @UpdateDate:     2018/5/7 11:53
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class RegisterUserSearch implements Serializable {
    /**
     * 用户id
     */
    private String userID;
    private Integer[] userIDs;

    /**
     *手机号
     */
    private String mobile;
    private String[] mobiles;
    /**
     * 个人的真实姓名，企业的联系人姓名
     */
    private String name;
    private String[] names;
    /**
     * 用户简称
     */
    private String nickName;
    private String[] nickNames;
    /**
     *销售id
     */
    private String saleID;
    private Integer[] saleIDs;
    /**
     *媒介id
     */
    private String mediaID;
    private Integer[] mediaIDs;
    /**
     *
     */
    private String registerTimeStart;
    /**
     *
     */
    private String registerTimeEnd;

    /**
     * 当前登录用户
     */
    private AdminUser currLoginUser;
    /**
     * 用户状态1=审核成功，0审核失败，2待审核3注册成功
     */
    private String statusValue;
    private Integer[] statusValues;
    /**
     * 传1全部选择   不传分页查询
     */
    private Integer isAllSelect = 0;

    private Integer currentRole;
    /**
     * 创建用户来源
     */
    private Integer sourceID;
    /**
     * 请求审核开始时间
     */
    private String askAuditTimeStart;
    /**
     * 请求审核结束时间
     */
    private String askAuditTimeEnd;

    private Integer role;

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getAskAuditTimeStart() {
        return askAuditTimeStart;
    }

    public void setAskAuditTimeStart(String askAuditTimeStart) {
        this.askAuditTimeStart = askAuditTimeStart;
    }

    public String getAskAuditTimeEnd() {
        return askAuditTimeEnd;
    }

    public void setAskAuditTimeEnd(String askAuditTimeEnd) {
        this.askAuditTimeEnd = askAuditTimeEnd;
    }

    public Integer getSourceID() {
        return sourceID;
    }

    public void setSourceID(Integer sourceID) {
        this.sourceID = sourceID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer[] getUserIDs() {
        return userIDs;
    }

    public void setUserIDs(Integer[] userIDs) {
        this.userIDs = userIDs;
    }

    public Integer getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(Integer currentRole) {
        this.currentRole = currentRole;
    }

    public Integer getIsAllSelect() {
        return isAllSelect;
    }

    public void setIsAllSelect(Integer isAllSelect) {
        this.isAllSelect = isAllSelect;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public Integer[] getStatusValues() {
        return statusValues;
    }

    public void setStatusValues(Integer[] statusValues) {
        this.statusValues = statusValues;
    }



    public AdminUser getCurrLoginUser() {
        return currLoginUser;
    }

    public void setCurrLoginUser(AdminUser currLoginUser) {
        this.currLoginUser = currLoginUser;
    }

    public Integer[] getSaleIDs() {
        return saleIDs;
    }

    public void setSaleIDs(Integer[] saleIDs) {
        this.saleIDs = saleIDs;
    }

    public Integer[] getMediaIDs() {
        return mediaIDs;
    }

    public void setMediaIDs(Integer[] mediaIDs) {
        this.mediaIDs = mediaIDs;
    }

    public String[] getMobiles() {
        return mobiles;
    }

    public void setMobiles(String[] mobiles) {
        this.mobiles = mobiles;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public String[] getNickNames() {
        return nickNames;
    }

    public void setNickNames(String[] nickNames) {
        this.nickNames = nickNames;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSaleID() {
        return saleID;
    }

    public void setSaleID(String saleID) {
        this.saleID = saleID;
    }

    public String getMediaID() {
        return mediaID;
    }

    public void setMediaID(String mediaID) {
        this.mediaID = mediaID;
    }

    public String getRegisterTimeStart() {
        return registerTimeStart;
    }

    public void setRegisterTimeStart(String registerTimeStart) {
        this.registerTimeStart = registerTimeStart;
    }

    public String getRegisterTimeEnd() {
        return registerTimeEnd;
    }

    public void setRegisterTimeEnd(String registerTimeEnd) {
        this.registerTimeEnd = registerTimeEnd;
    }
}
