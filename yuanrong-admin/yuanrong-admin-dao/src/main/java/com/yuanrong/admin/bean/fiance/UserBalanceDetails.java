package com.yuanrong.admin.bean.fiance;

import java.io.Serializable;
import java.math.*;

import com.yuanrong.admin.Enum.EnumReferType;
import com.yuanrong.admin.Enum.EnumTradeType;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.util.EnumUtil;

/**
 * 用户余额流水的实体类
 *
 * @author MDA
 */
public class UserBalanceDetails extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增类型
     */
    private int userBalanceDetailsId;
    /*****自定义属性区域begin******/


    /**
     * 交易类型
     * 交易类型
     */
    private EnumTradeType tradeType;
    /*
     * 用于接收前台数据
     */
    private Integer tradeTypeIndex;

    /**
     * 金额
     * 金额
     */
    private BigDecimal money;

    /**
     * 摘要
     * 摘要
     */
    private String remark;

    /**
     * 业务时间
     * 业务时间
     */
    private String businessTime;

    /**
     * 业务类型
     * 业务类型
     */
    private EnumReferType referType;
    /*
     * 用于接收前台数据
     */
    private Integer referTypeIndex;

    /**
     * 关联业务id
     * 关联业务id
     */
    private int referId;

    /**
     * 注册用户ID
     * 注册用户ID
     */
    private int registeredUserInfoId;

    /**
     * 注册用户
     * 注册用户
     */
    private RegisteredUserInfo registeredUserInfo;

    public int getUserBalanceDetailsId() {
        return this.userBalanceDetailsId;
    }

    public void setUserBalanceDetailsId(int userBalanceDetailsId) {
        this.userBalanceDetailsId = userBalanceDetailsId;
    }

    /*****自定义属性区域begin.get/set******/

    public EnumTradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(EnumTradeType tradeType) {
        this.tradeType = tradeType;
        this.tradeTypeIndex = tradeType.getIndex();
    }


    public Integer getTradeTypeIndex() {
        return tradeTypeIndex;
    }

    public void setTradeTypeIndex(Integer tradeTypeIndex) {
        this.tradeType = (EnumTradeType) EnumUtil.valueOf(EnumTradeType.class, tradeTypeIndex);
        this.tradeTypeIndex = tradeTypeIndex;
    }

    public BigDecimal getMoney() {
        return this.money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBusinessTime() {
        return this.businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public EnumReferType getReferType() {
        return referType;
    }

    public void setReferType(EnumReferType referType) {
        this.referType = referType;
        this.referTypeIndex = referType.getIndex();
    }

    public Integer getReferTypeIndex() {
        return referTypeIndex;
    }

    public void setReferTypeIndex(Integer referTypeIndex) {
        this.referType = (EnumReferType) EnumUtil.valueOf(EnumReferType.class, referTypeIndex);
        this.referTypeIndex = referTypeIndex;
    }

    public int getReferId() {
        return this.referId;
    }

    public void setReferId(int referId) {
        this.referId = referId;
    }

    public int getRegisteredUserInfoId() {
        return this.registeredUserInfoId;
    }

    public void setRegisteredUserInfoId(int registeredUserInfoId) {
        this.registeredUserInfoId = registeredUserInfoId;
    }

    public RegisteredUserInfo getRegisteredUserInfo() {
        return this.registeredUserInfo;
    }

    public void setRegisteredUserInfo(RegisteredUserInfo registeredUserInfo) {
        this.registeredUserInfo = registeredUserInfo;
    }


}
