package com.yuanrong.admin.bean.demand;
import java.util.*;
import java.io.Serializable;
import java.math.*;

import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumDemandFastStatus;
import com.yuanrong.admin.Enum.EnumDemandType;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.util.EnumUtil;

/**
 * 快速需求的实体类
 *
 * @author MDA
 *
 */
public class DemandFast extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增类型
     */
    private Integer demandFastId;
    /*****自定义属性区域begin******/


    /**
     * 需求号
     * 需求号
     */
    private String demandSn;

    /**

     * 开始时间
     */
    private String beginTime;
    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 销售经理
     * 销售经理
     */
    private Integer saleManId;

    /**
     * 称呼
     * 称呼
     */
    private String nickname;

    /**
     * 手机号
     * 手机号
     */
    private String mobile;

    /**
     * 需求类型
     * 需求类型
     */
    private EnumDemandType demandType;
    /*
     * 用于接收前台数据
     */
    private Integer demandTypeIndex;

    /**
     * 需求状态
     * 需求状态
     */
    private EnumDemandFastStatus demandFastStatus;
    /**
     * 普通需求id
     */
    private Integer demandId;
    /*
     * 用于接收前台数据
     */
    private Integer demandFastStatusIndex;

    /*
     * 注册用户Id
     */
    private Integer registeredUserInfoId;

    /*
     * 注册用户
     */
    private RegisteredUserInfo registeredUserInfo;
    /**
     * 拒绝原因
     */
    private String refuseReason;
    public Integer getRegisteredUserInfoId() {
        return registeredUserInfoId;
    }

    public void setRegisteredUserInfoId(Integer registeredUserInfoId) {
        this.registeredUserInfoId = registeredUserInfoId;
    }

    public Integer getDemandFastId() {
        return this.demandFastId;
    }
    public void setDemandFastId(Integer demandFastId) {
        this.demandFastId = demandFastId;
    }
    /*****自定义属性区域begin.get/set******/
    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDemandSn() {
        return this.demandSn;
    }
    public void setDemandSn(String demandSn) {
        this.demandSn = demandSn;
    }

    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public EnumDemandType getDemandType() {
        return demandType;
    }
    public void setDemandType(EnumDemandType demandType) {
        this.demandTypeIndex = demandType.getIndex();
        this.demandType = demandType;
    }
    public Integer getDemandTypeIndex() {
        return demandTypeIndex;
    }
    public void setDemandTypeIndex(Integer demandTypeIndex) {
        this.demandTypeIndex = demandTypeIndex;
        this.demandType = (EnumDemandType) EnumUtil.valueOf(EnumDemandType.class, demandTypeIndex);
    }
    public EnumDemandFastStatus getDemandFastStatus() {
        return demandFastStatus;
    }
    public void setDemandFastStatus(EnumDemandFastStatus demandFastStatus) {
        this.demandFastStatusIndex = demandFastStatus.getIndex();
        this.demandFastStatus = demandFastStatus;

    }

    public Integer getDemandFastStatusIndex() {
        return demandFastStatusIndex;
    }
    public void setDemandFastStatusIndex(Integer demandFastStatusIndex) {
        this.demandFastStatusIndex = demandFastStatusIndex;
        this.demandFastStatus = (EnumDemandFastStatus) EnumUtil.valueOf(EnumDemandFastStatus.class, demandFastStatusIndex);
    }

    public Integer getSaleManId() {
        return saleManId;
    }

    public void setSaleManId(Integer saleManId) {
        this.saleManId = saleManId;
    }

    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public RegisteredUserInfo getRegisteredUserInfo() {
        return registeredUserInfo;
    }

    public void setRegisteredUserInfo(RegisteredUserInfo registeredUserInfo) {
        this.registeredUserInfo = registeredUserInfo;
    }

    /**
     * 快速需求数据处理
     * @param demandFast
     * @return
     */
    public static JSONObject dealDemandFast(DemandFast demandFast) {
        JSONObject result = new JSONObject();
        //快速需求id
        result.put("demandFastId",demandFast.getDemandFastId());
        //快速需求类型
        result.put("demandFastTypeIndex",demandFast.getDemandTypeIndex());
        result.put("demandFastType",demandFast.getDemandTypeIndex() == null ? "" : EnumUtil.valueOf(EnumDemandType.class,demandFast.getDemandTypeIndex()).getName());
        //快速需求状态
        result.put("demandFastStatusIndex",demandFast.getDemandFastStatusIndex());
        result.put("demandFastStatus",demandFast.getDemandFastStatusIndex() == null ? "" : EnumUtil.valueOf(EnumDemandFastStatus.class,demandFast.getDemandFastStatusIndex()).getName());
        //快速需求拒约原因
        result.put("refuseReason",demandFast.getRefuseReason());
        result.put("userId",demandFast.getRegisteredUserInfo() == null ? "" : demandFast.getRegisteredUserInfo().getRecID());
        result.put("userNickName",demandFast.getRegisteredUserInfo() == null ? "" : demandFast.getRegisteredUserInfo().getNickName());
        result.put("userMobile",demandFast.getRegisteredUserInfo() == null ? "" : demandFast.getRegisteredUserInfo().getMobile());
        return result;
    }
}
