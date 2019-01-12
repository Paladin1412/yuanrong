package com.yuanrong.admin.bean.usermanagement;

import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.result.DemandListResult;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * @param
 * @author ShiLinghuai
 * @return
 * @exception
 * @date 2018/4/27 12:08
 */
public class RegisteredUserInfo extends BaseBean implements Serializable {
    private static final long serialVersionUID = 921912668025850975L;
    /**
     * 主键
     */
    private Integer recID;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     *验证码
     */
    private String checkCode;
    /**
     *密码
     */
    private String password;
    /**
     * 0-个人 1-企业初始用户类型
     */
    private Integer userType;
    private EnumUserType enumUserType;

    /**
     * 账号 停用，可用
     */
    private Integer statusValue;
    private EnumStatusOfUser enumStatusOfUser;
    /**
     * 动态码预留
     */
    private String dynamicPwd;

    /**
     *头像地址
     */
    private String headImage;
    /**
     *简介
     */
    private String introduction;

    /**
     * 网名-用户简称
     */
    private String nickName;

    /**
     * 初始角色买家0-卖家-1
     */
    private Integer role;
    private EnumUserRole enumUserRole;
    /**
     * 当前角色买家0，卖家1，既是买家又是买家2.
     */
    private Integer currentRole;
    private EnumUserCurrentRole enumUserCurrentRole;
    /**
     * 买家类型
     */
    private Integer buyerType;
    private EnumUserSellerAndBuyerUserType enumBuyerUserType;
    /**
     * 卖家类型
     */
    private Integer sellerType;
    private EnumUserSellerAndBuyerUserType enumSellerUserType;
    /**
     * 卖家审核状态 EnumUserRoleLicenseStatus状态
     */
    private Integer sellerStatusValue;
    private EnumUserRoleLicenseStatus sellerStatus;

    /**
     *买家审核状态 EnumUserRoleLicenseStatus状态
     */
    private Integer buyerStatusValue;
    private EnumUserRoleLicenseStatus buyerStatus;
    /**
     * 对于后台的 媒介 id
     */
    private Integer adminUserMediaID;
    private AdminUser adminUserMedia;
    /**
     * 对于后台的 销售 id
     */
    private Integer adminUserSalesID;
    private AdminUser adminUserSales;
    /**
     * 用户是否激活1=激活，0=未激活
     */
    private Integer actionStatus;
    /**
     * 真实姓名--个人类型审核信息开始--BEGIN
     *
     * */
    private String realName;
    /**
     * 身份证号码
     */
    private String IDCardNumber;
    /**
     * 身份证正面
     */
    private String IDCardFaceImageUrl;

    /**
     * 买家审核失败原因id
     */
    private String buyerCheckFailedReasonID;
    /**
     * 卖家审核失败原因id
     */
    private String sellerCheckFailedReasonID;
    /**
     *身份证背面 --个人类型审核信息--END
     */
    private String IDCardBackImageUrl;
    /**
     * 客户公司营业执照等扩展审核信息
     */
    private Integer companyExtID;
    private RegisteredUserExtCompany registeredUserExtCompany;
    /**
     * 创建用户的人
     */
    private Integer adminUserCreaterID;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *更新时间
     */
    private Date updateTime;
    /**
     * 买家审核人
     */
    private String buyerAuditUser;
    /**
     * 卖家审核人
     */
    private String sellerAuditUser;
    /**
     * 创建来源
     */
    private Integer sourceID;
    private EnumUserSourseID enmuSourceID;
    /**
     * 卖家请求审核时间
     */
    private Date sellerAskAudit;
    /**
     * 买家请求审核时间
     */
    private Date buyerAskAudit;


    private String qq;
    private String weixin;

    public Date getSellerAskAudit() {
        return sellerAskAudit;
    }

    public void setSellerAskAudit(Date sellerAskAudit) {
        this.sellerAskAudit = sellerAskAudit;
    }

    public Integer getSellerStatusValue() {
        return sellerStatusValue;
    }

    public void setSellerStatusValue(Integer sellerStatusValue) {

        this.sellerStatusValue = sellerStatusValue;
        this.sellerStatus = (EnumUserRoleLicenseStatus) EnumUtil.valueOf(EnumUserRoleLicenseStatus.class, sellerStatusValue);

    }

    public Integer getBuyerStatusValue() {
        return buyerStatusValue;
    }

    public void setBuyerStatusValue(Integer buyerStatusValue) {
        this.buyerStatusValue = buyerStatusValue;
        this.buyerStatus = (EnumUserRoleLicenseStatus) EnumUtil.valueOf(EnumUserRoleLicenseStatus.class, buyerStatusValue);

    }


    public AdminUser getAdminUserMedia() {
        return adminUserMedia;
    }

    public void setAdminUserMedia(AdminUser adminUserMedia) {
        this.adminUserMedia = adminUserMedia;
    }

    public AdminUser getAdminUserSales() {
        return adminUserSales;
    }

    public void setAdminUserSales(AdminUser adminUserSales) {
        this.adminUserSales = adminUserSales;
    }




    public Date getCreateTime() {
        return createTime;
    }

    public EnumUserType getEnumUserType() {
        return enumUserType;
    }

    public void setEnumUserType(EnumUserType enumUserType) {
        this.enumUserType = enumUserType;
        this.userType = enumUserType.getIndex();
    }



    public EnumUserRole getEnumUserRole() {
        return enumUserRole;
    }

    public void setEnumUserRole(EnumUserRole enumUserRole) {
        this.enumUserRole = enumUserRole;
        this.role = enumUserRole.getIndex();
    }

    public EnumUserCurrentRole getEnumUserCurrentRole() {
        return enumUserCurrentRole;
    }

    public void setEnumUserCurrentRole(EnumUserCurrentRole enumUserCurrentRole) {
        this.enumUserCurrentRole = enumUserCurrentRole;
        this.currentRole = enumUserCurrentRole.getIndex();

    }



    public EnumUserSellerAndBuyerUserType getEnumBuyerUserType() {
        return enumBuyerUserType;
    }

    public void setEnumBuyerUserType(EnumUserSellerAndBuyerUserType enumBuyerUserType) {
        this.enumBuyerUserType = enumBuyerUserType;
        this.buyerType = enumBuyerUserType.getIndex();
    }

    public EnumUserSellerAndBuyerUserType getEnumSellerUserType() {
        return enumSellerUserType;
    }

    public void setEnumSellerUserType(EnumUserSellerAndBuyerUserType enumSellerUserType) {
        this.enumSellerUserType = enumSellerUserType;
        this.sellerType = enumSellerUserType.getIndex();
    }





    public RegisteredUserExtCompany getRegisteredUserExtCompany() {
        return registeredUserExtCompany;
    }

    public void setRegisteredUserExtCompany(RegisteredUserExtCompany registeredUserExtCompany) {
        this.registeredUserExtCompany = registeredUserExtCompany;
    }

    public Integer getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(Integer currentRole) {
        this.currentRole = currentRole;
        this.enumUserCurrentRole = (EnumUserCurrentRole) EnumUtil.valueOf(EnumUserCurrentRole.class, currentRole);
    }

    public Integer getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(Integer buyerType) {
        this.buyerType = buyerType;
        this.enumBuyerUserType = (EnumUserSellerAndBuyerUserType) EnumUtil.valueOf(EnumUserSellerAndBuyerUserType.class, buyerType);
    }

    public Integer getSellerType() {
        return sellerType;
    }

    public void setSellerType(Integer sellerType) {
        this.sellerType = sellerType;
        this.enumSellerUserType = (EnumUserSellerAndBuyerUserType)EnumUtil.valueOf(EnumUserSellerAndBuyerUserType.class, sellerType);
    }

    public EnumStatusOfUser getEnumStatusOfUser() {
        return enumStatusOfUser;
    }

    public void setEnumStatusOfUser(EnumStatusOfUser enumStatusOfUser) {
        this.enumStatusOfUser = enumStatusOfUser;
        this.statusValue = enumStatusOfUser.getIndex();
    }





    public Integer getRecID() {
        return recID;
    }

    public void setRecID(Integer recID) {
        this.recID = recID;
    }


    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {

        this.userType = userType;
        this.enumUserType = (EnumUserType) EnumUtil.valueOf(EnumUserType.class, userType);

    }



    public Integer getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(Integer statusValue) {
        this.statusValue = statusValue;
        this.enumStatusOfUser = (EnumStatusOfUser) EnumUtil.valueOf(EnumStatusOfUser.class, statusValue);

    }

    public String getDynamicPwd() {
        return dynamicPwd;
    }

    public void setDynamicPwd(String dynamicPwd) {
        this.dynamicPwd = dynamicPwd;
    }



    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
        this.enumUserRole = (EnumUserRole) EnumUtil.valueOf(EnumUserRole.class, role);

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

    public Integer getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(Integer actionStatus) {
        this.actionStatus = actionStatus;
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

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public EnumUserRoleLicenseStatus getSellerStatus() {
        return sellerStatus;
    }

    public void setSellerStatus(EnumUserRoleLicenseStatus sellerStatus) {
        this.sellerStatus = sellerStatus;
        this.sellerStatusValue = sellerStatus.getIndex();
    }

    public EnumUserRoleLicenseStatus getBuyerStatus() {
        return buyerStatus;
    }

    public void setBuyerStatus(EnumUserRoleLicenseStatus buyerStatus) {
        this.buyerStatus = buyerStatus;
        this.buyerStatusValue = buyerStatus.getIndex();
    }







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

    public Integer getSourceID() {
        return sourceID;
    }

    public void setSourceID(Integer sourceID) {
        this.sourceID = sourceID;
        this.enmuSourceID = (EnumUserSourseID) EnumUtil.valueOf(EnumUserSourseID.class, sourceID);

    }

    public EnumUserSourseID getEnmuSourceID() {
        return enmuSourceID;
    }

    public void setEnmuSourceID(EnumUserSourseID enmuSourceID) {
        this.enmuSourceID = enmuSourceID;
        this.sourceID = enmuSourceID.getIndex();

    }

    public String getBuyerCheckFailedReasonID() {
        return buyerCheckFailedReasonID;
    }

    public void setBuyerCheckFailedReasonID(String buyerCheckFailedReasonID) {
        this.buyerCheckFailedReasonID = buyerCheckFailedReasonID;
    }

    public String getSellerCheckFailedReasonID() {
        return sellerCheckFailedReasonID;
    }

    public void setSellerCheckFailedReasonID(String sellerCheckFailedReasonID) {
        this.sellerCheckFailedReasonID = sellerCheckFailedReasonID;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public Date getBuyerAskAudit() {
        return buyerAskAudit;
    }

    public void setBuyerAskAudit(Date buyerAskAudit) {
        this.buyerAskAudit = buyerAskAudit;
    }

    public static JSONObject packageRegisteredUser(RegisteredUserInfo registeredUserInfo) {
        JSONObject ele = new JSONObject();
        //id
        ele.put("id", registeredUserInfo.getRecID());
        ele.put("nickName", registeredUserInfo.getNickName());
        ele.put("role", registeredUserInfo.getRole());
        //设置用户状态'和失败信息
        ele.put("sellerStatusValue", registeredUserInfo.getSellerStatusValue()==null?EnumUserRoleLicenseStatus.注册成功.getIndex():registeredUserInfo.getSellerStatusValue());
        ele.put("sellerFailedReason", registeredUserInfo.getSellerCheckFailedReasonID());
        ele.put("salerName", registeredUserInfo.getAdminUserSales() != null ? registeredUserInfo.getAdminUserSales().getRealName() : null);
        ele.put("mediaName", registeredUserInfo.getAdminUserMedia() != null ? registeredUserInfo.getAdminUserMedia().getRealName() : null);
        ele.put("mobile", registeredUserInfo.getMobile());
        ele.put("registerTime", registeredUserInfo.getCreateTime() == null ? "" : DateUtil.format(registeredUserInfo.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        ele.put("personalLinkman",registeredUserInfo.getRealName());
        ele.put("corporateLinkman",registeredUserInfo.getRegisteredUserExtCompany()==null?null:registeredUserInfo.getRegisteredUserExtCompany().getLeaderName());
        ele.put("sourceID", registeredUserInfo.getSourceID());
        ele.put("qq" , registeredUserInfo.getQq());
        ele.put("weixin",registeredUserInfo.getWeixin());
        return ele;
    }
    public static JSONObject packageRegisteredUserAudit(RegisteredUserInfo registeredUserInfo) {
        JSONObject ele = new JSONObject();
        ele.put("userId", registeredUserInfo.getRecID());
        ele.put("sellerAuditUser", registeredUserInfo.getSellerAuditUser());
        ele.put("salerName", registeredUserInfo.getAdminUserSales() != null ? registeredUserInfo.getAdminUserSales().getRealName() : null);
        ele.put("mediaName", registeredUserInfo.getAdminUserMedia() != null ? registeredUserInfo.getAdminUserMedia().getRealName() : null);
        ele.put("sellerStatusValue", registeredUserInfo.getSellerStatusValue());
        ele.put("sellerFailedReason", registeredUserInfo.getSellerCheckFailedReasonID());
        ele.put("sellerAskAudit",registeredUserInfo.getSellerAskAudit()==null?"":DateUtil.format(registeredUserInfo.getSellerAskAudit(),"yyyy-MM-dd HH:mm:ss"));
        ele.put("buyerAskAudit" ,registeredUserInfo.getBuyerAskAudit()==null?"":DateUtil.format(registeredUserInfo.getBuyerAskAudit(),"yyyy-MM-dd HH:mm:ss") );
        ele.put("sourceID", registeredUserInfo.getSourceID());
        ele.put("role",registeredUserInfo.getRole());
        ele.put("qq" , registeredUserInfo.getQq());
        ele.put("weixin" , registeredUserInfo.getWeixin());
        if (registeredUserInfo.getSellerType().equals(EnumUserSellerAndBuyerUserType.个人.getIndex())) {
            ele.put("nickName", registeredUserInfo.getNickName());
            ele.put("sellerUserType", registeredUserInfo.getSellerType());
            ele.put("sellerStatusValue", registeredUserInfo.getSellerStatusValue());
            ele.put("name", registeredUserInfo.getRealName());
            ele.put("ID", registeredUserInfo.getIDCardNumber());
            ele.put("IDCardFaceImageUrl", registeredUserInfo.getIDCardFaceImageUrl());
            ele.put("IDCardBackImageUrl", registeredUserInfo.getIDCardBackImageUrl());
        } else {
            ele.put("nickName", registeredUserInfo.getNickName());
            ele.put("sellerUserType", registeredUserInfo.getSellerType());
            ele.put("sellerStatusValue", registeredUserInfo.getSellerStatusValue());
            RegisteredUserExtCompany registeredUserExtCompany = registeredUserInfo.getRegisteredUserExtCompany();
            ele.put("name", registeredUserExtCompany.getCompanyName());
            ele.put("ID", registeredUserExtCompany.getCompanyID());
            ele.put("companyImageUrl", registeredUserExtCompany.getCompanyImageUrl());
        }
        return ele;
    }


}
