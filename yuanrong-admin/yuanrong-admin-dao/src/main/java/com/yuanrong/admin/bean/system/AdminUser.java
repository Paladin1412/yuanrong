package com.yuanrong.admin.bean.system;

import com.yuanrong.admin.bean.BaseBean;
import java.util.Date;
import java.io.Serializable;

/**
 * Created by Tangzheng on 2018/4/27.
 */
public class AdminUser extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    private  Integer RecID;

    /**
     * 用户名
     */
    private String UserName;

    /**
     * 用户真实姓名
     */
    private String RealName;

    /**
     * 真实姓名数组
     */
    private String[] RealNameS;

    /**
     * 手机号
     */
    private String IPhoneNum;

    /**
     *  手机号数组
     */
    private String[] IPhoneNumS;

    /**
     * 密码
     */
    private  String PassWord;
    /**
     * 创建时间
     */
    private Date CreateTime;

    /**
      * 更新时间
     */
    private Date UpdateTime;

    /**
     * 状态值
     */
    private Integer StatusValue;

    /**
     * 销售还是媒介等
     */
    private Integer AdminRoleID;

    /**
     * 下属用户，非数据库字段
     */
    private String underUser;

    /**
     * 微信二维码
     */
    private String QrCodeWx;

    /**
     * 邮箱
     */
    private String Email;

    /**
     * 企业微信ID
     */
    private String cpWeiXinId;

    public String getQrCodeWx() {
        return QrCodeWx;
    }

    public void setQrCodeWx(String qrCodeWx) {
        QrCodeWx = qrCodeWx;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public AdminUser() {
    }

//    public AdminUser(Integer recID, String userName, String realName, String IPhoneNum,String passWord, Date createTime, Date updateTime, Integer statusValue, Integer adminRoleID) {
//        RecID = recID;
//        UserName = userName;
//        RealName = realName;
//        this.IPhoneNum = IPhoneNum;
//        PassWord =passWord;
//        CreateTime = createTime;
//        UpdateTime = updateTime;
//        StatusValue = statusValue;
//        AdminRoleID = adminRoleID;
//    }


    public AdminUser(Integer recID, String userName, String realName, String[] realNameS, String IPhoneNum, String[] IPhoneNumS, String passWord, Date createTime, Date updateTime, Integer statusValue, Integer adminRoleID, String underUser, String qrCodeWx, String email) {
        RecID = recID;
        UserName = userName;
        RealName = realName;
        RealNameS = realNameS;
        this.IPhoneNum = IPhoneNum;
        this.IPhoneNumS = IPhoneNumS;
        PassWord = passWord;
        CreateTime = createTime;
        UpdateTime = updateTime;
        StatusValue = statusValue;
        AdminRoleID = adminRoleID;
        this.underUser = underUser;
        QrCodeWx = qrCodeWx;
        Email = email;
    }

    public Integer getRecID() {
        return RecID;
    }

    public void setRecID(Integer recID) {
        RecID = recID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String[] getRealNameS() {
        return RealNameS;
    }

    public void setRealNameS(String[] realNameS) {
        RealNameS = realNameS;
    }

    public String[] getIPhoneNumS() {
        return IPhoneNumS;
    }

    public void setIPhoneNumS(String[] IPhoneNumS) {
        this.IPhoneNumS = IPhoneNumS;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getIPhoneNum() {
        return IPhoneNum;
    }

    public void setIPhoneNum(String IPhoneNum) {
        this.IPhoneNum = IPhoneNum;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Date getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Date updateTime) {
        UpdateTime = updateTime;
    }

    public Integer getStatusValue() {
        return StatusValue;
    }

    public void setStatusValue(Integer statusValue) {
        StatusValue = statusValue;
    }

    public Integer getAdminRoleID() {
        return AdminRoleID;
    }

    public void setAdminRoleID(Integer adminRoleID) {
        AdminRoleID = adminRoleID;
    }

    public String getUnderUser() {
        return underUser;
    }

    public void setUnderUser(String underUser) {
        this.underUser = underUser;
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "RecID=" + RecID +
                ", UserName='" + UserName + '\'' +
                ", RealName='" + RealName + '\'' +
                ", IPhoneNum='" + IPhoneNum + '\'' +
                ", CreateTime=" + CreateTime +
                ", UpdateTime=" + UpdateTime +
                ", StatusValue=" + StatusValue +
                ", AdminRoleID=" + AdminRoleID +
                '}';
    }

    public String getCpWeiXinId() {
        return cpWeiXinId;
    }

    public void setCpWeiXinId(String cpWeiXinId) {
        this.cpWeiXinId = cpWeiXinId;
    }
}
