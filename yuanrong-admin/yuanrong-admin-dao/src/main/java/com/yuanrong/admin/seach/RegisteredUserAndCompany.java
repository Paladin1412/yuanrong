package com.yuanrong.admin.seach;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 添加用户VO类，前台传来的所有参数封装类
 * @Author: ShiLinghuai
 * @CreateDate: 2018/4/28 11:21
 * @UpdateUser: ShiLinghuai
 * @UpdateDate: 2018/4/28 11:21
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class RegisteredUserAndCompany implements Serializable {
    /**
     * 注册用户id
     */
    private Integer recID;
    /**
     * 手机号
     */
    private String userMobile;
    /**
     * 验证码
     */
    private String checkCode;
    /**
     * 简介
     */
    private String nickName;
    /**
     * 角色买家0-既是买家卖家-1
     */
    private Integer role;
    /**
     * 用户类型1-个人 2 -企业
     */
    private Integer userType;

    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 身份证号
     */
    private String IDCardNumber;
    /**
     * 身份证正面
     */
    private String IDCardFaceImageUrl;
    /**
     * 身份证背面
     */
    private String IDCardBackImageUrl;
    /**
     * 拓展公司信息
     */
    private Integer companyExtID;

    /**
     * 添加人id
     */
    private Integer adminUserCreaterID;




    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 负责人名字
     */
    private String leaderName;
    /**
     * 负责人手机号
     */
    private String leaderMobile;
    /**
     * 联系人身份
     */
    private String roleID;
    /**
     * 社会信用代码
     */
    private String companyID;
    /**
     * 公司营业执照照片
     */
    private String companyImageUrl;

    /**
     * 媒介id
     */
    private Integer adminUserMediaID;
    /**
     * 销售id
     */
    private Integer adminUserSalesID;
    /**
     * 用户状态
     */
    private Integer statusValue;

    /**
     * 买家的类别
     */
    private Integer buyerUserType;
    /**
     * 卖家的类别
     */
    private Integer sellerUserType;
    /**
     * 当前角色
     */
    private Integer currentRole;
    /**
     * 前台更新信息时从哪个中心过来的
     */
    private Integer fromWhichCenter;

    private String password;
    /**
     * 卖家状态
     */
    private Integer sellerStatusValue;

    /**
     * 买家状态
     * @return
     */
    private Integer buyerStatusValue;

    private String sellerCheckFailedReasonID;
    private String buyerCheckFailedReasonID;

    /**
     * 买家审核人
     */
    private String buyerAuditUser;
    /**
     * 卖家审核人
     */
    private String sellerAuditUser;
    /**
     * 普通需求id
     */
    private Integer demandID;
    /**
     * 快速需求id
     */
    private Integer demandFastID;
    /**
     * 卖家请求审核时间
     */
    private Date sellerAskAudit;
    /**
     * 卖家请求审核时间
     */
    private Date buyerAskAudit;
    public String getBuyerAuditUser() {
        return buyerAuditUser;
    }

    public void setBuyerAuditUser(String buyerAuditUser) {
        this.buyerAuditUser = buyerAuditUser;
    }

    public String getSellerAuditUser() {
        return sellerAuditUser;
    }

    public void setSellerAuditUser(String sellerAuditUser) {
        this.sellerAuditUser = sellerAuditUser;
    }

    public String getSellerCheckFailedReasonID() {
        return sellerCheckFailedReasonID;
    }

    public void setSellerCheckFailedReasonID(String sellerCheckFailedReasonID) {
        this.sellerCheckFailedReasonID = sellerCheckFailedReasonID;
    }

    public String getBuyerCheckFailedReasonID() {
        return buyerCheckFailedReasonID;
    }

    public void setBuyerCheckFailedReasonID(String buyerCheckFailedReasonID) {
        this.buyerCheckFailedReasonID = buyerCheckFailedReasonID;
    }

    public Integer getSellerStatusValue() {
        return sellerStatusValue;
    }

    public void setSellerStatusValue(Integer sellerStatusValue) {
        this.sellerStatusValue = sellerStatusValue;
    }

    public Integer getBuyerStatusValue() {
        return buyerStatusValue;
    }

    public void setBuyerStatusValue(Integer buyerStatusValue) {
        this.buyerStatusValue = buyerStatusValue;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getFromWhichCenter() {
        return fromWhichCenter;
    }

    public void setFromWhichCenter(Integer fromWhichCenter) {
        this.fromWhichCenter = fromWhichCenter;
    }




    public Integer getBuyerUserType() {
        return buyerUserType;
    }

    public void setBuyerUserType(Integer buyerUserType) {
        this.buyerUserType = buyerUserType;
    }

    public Integer getSellerUserType() {
        return sellerUserType;
    }

    public void setSellerUserType(Integer sellerUserType) {
        this.sellerUserType = sellerUserType;
    }

    public Integer getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(Integer currentRole) {
        this.currentRole = currentRole;
    }



    public Integer getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(Integer statusValue) {
        this.statusValue = statusValue;
    }


    public Integer getAdminUserMediaID() {
        return adminUserMediaID;
    }

    public void setAdminUserMediaID(Integer adminUserMediaID) {
        this.adminUserMediaID = adminUserMediaID;
    }

    public Integer getAdminUserSalesID() {
        return adminUserSalesID;
    }

    public void setAdminUserSalesID(Integer adminUserSalesID) {
        this.adminUserSalesID = adminUserSalesID;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getRecID() {
        return recID;
    }

    public void setRecID(Integer recID) {
        this.recID = recID;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIDCardNumber() {
        return IDCardNumber;
    }

    public void setIDCardNumber(String IDCardNumber) {
        this.IDCardNumber = IDCardNumber;
    }

    public String getIDCardFaceImageUrl() {
        return IDCardFaceImageUrl;
    }

    public void setIDCardFaceImageUrl(String IDCardFaceImageUrl) {
        this.IDCardFaceImageUrl = IDCardFaceImageUrl;
    }

    public String getIDCardBackImageUrl() {
        return IDCardBackImageUrl;
    }

    public void setIDCardBackImageUrl(String IDCardBackImageUrl) {
        this.IDCardBackImageUrl = IDCardBackImageUrl;
    }

    public Integer getCompanyExtID() {
        return companyExtID;
    }

    public void setCompanyExtID(Integer companyExtID) {
        this.companyExtID = companyExtID;
    }

    public Integer getAdminUserCreaterID() {
        return adminUserCreaterID;
    }

    public void setAdminUserCreaterID(Integer adminUserCreaterID) {
        this.adminUserCreaterID = adminUserCreaterID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeaderMobile() {
        return leaderMobile;
    }

    public void setLeaderMobile(String leaderMobile) {
        this.leaderMobile = leaderMobile;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getCompanyImageUrl() {
        return companyImageUrl;
    }

    public void setCompanyImageUrl(String companyImageUrl) {
        this.companyImageUrl = companyImageUrl;
    }

    public Integer getDemandID() {
        return demandID;
    }

    public void setDemandID(Integer demandID) {
        this.demandID = demandID;
    }

    public Integer getDemandFastID() {
        return demandFastID;
    }

    public void setDemandFastID(Integer demandFastID) {
        this.demandFastID = demandFastID;
    }

    public Date getSellerAskAudit() {
        return sellerAskAudit;
    }

    public void setSellerAskAudit(Date sellerAskAudit) {
        this.sellerAskAudit = sellerAskAudit;
    }

    public Date getBuyerAskAudit() {
        return buyerAskAudit;
    }

    public void setBuyerAskAudit(Date buyerAskAudit) {
        this.buyerAskAudit = buyerAskAudit;
    }
}
