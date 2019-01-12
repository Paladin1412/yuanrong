package com.yuanrong.admin.bean.usermanagement;

import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.base.DictInfo;

import java.io.Serializable;
import java.util.Date;

public class RegisteredUserExtCompany extends BaseBean implements Serializable {

    /**
     *公司名称
     */
    private String companyName;
    /**
     *联系负责人名称
     */
    private String leaderName;
    /**
     *手机号
     */
    private String mobile;
    /**
     *角色（客户公司）
     */
    private String roleID;
    /**
     *营业执照编号唯一
     */
    private String companyID;
    /**
     *营业执照照片
     */
    private String companyImageUrl;
    /**
     * 企业身份审核状态
     */
    private Integer companyCheckStatus;
    /**
     * 企业类型审核失败原因
     */
    private Integer companyCheckFailedReasonID;
    private DictInfo companyCheckFailedReason;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     * 主键
     */
    private  Integer RecID;

    public DictInfo getCompanyCheckFailedReason() {
        return companyCheckFailedReason;
    }

    public void setCompanyCheckFailedReason(DictInfo companyCheckFailedReason) {
        this.companyCheckFailedReason = companyCheckFailedReason;
    }

    public Integer getCompanyCheckStatus() {
        return companyCheckStatus;
    }

    public void setCompanyCheckStatus(Integer companyCheckStatus) {
        this.companyCheckStatus = companyCheckStatus;
    }

    public Integer getCompanyCheckFailedReasonID() {
        return companyCheckFailedReasonID;
    }

    public void setCompanyCheckFailedReasonID(Integer companyCheckFailedReasonID) {
        this.companyCheckFailedReasonID = companyCheckFailedReasonID;
    }

    public Integer getRecID() {
        return RecID;
    }

    public void setRecID(Integer recID) {
        RecID = recID;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }




}
