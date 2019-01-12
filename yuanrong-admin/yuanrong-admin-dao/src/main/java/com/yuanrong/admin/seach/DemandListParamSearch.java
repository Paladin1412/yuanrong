package com.yuanrong.admin.seach;

import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 需求列表查询参数
 */
public class DemandListParamSearch extends BaseBean implements Serializable {
    /**
     * 需求编号
     */
    private String[] demandSn;
    /**
     * 需求名称
     */
    private String demandName;
    /**
     * 需求类型
     */
    private Integer demandTypeIndex;
    /**
     * 需求状态
     */
    private Integer demandStatusIndex;
    /**
     * 销售经理
     */
    private Integer saleId;
    /**
     * 用户简称
     */
    private String[] nickName;
    /**
     * 注册手机号
     */
    private String[] mobile;
    /**
     * 创建时间—开始
     */
    private String createdTimeBegin;
    /**
     * 创建时间—结束
     */
    private String createdTimeEnd;

    private static final long serialVersionUID = 1L;

    public String[] getDemandSn() {
        return demandSn;
    }

    public void setDemandSn(String[] demandSn) {
        this.demandSn = demandSn;
    }

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }

    public Integer getDemandTypeIndex() {
        return demandTypeIndex;
    }

    public void setDemandTypeIndex(Integer demandTypeIndex) {
        this.demandTypeIndex = demandTypeIndex;
    }

    public Integer getDemandStatusIndex() {
        return demandStatusIndex;
    }

    public void setDemandStatusIndex(Integer demandStatusIndex) {
        this.demandStatusIndex = demandStatusIndex;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public String[] getNickName() {
        return nickName;
    }

    public void setNickName(String[] nickName) {
        this.nickName = nickName;
    }

    public String[] getMobile() {
        return mobile;
    }

    public void setMobile(String[] mobile) {
        this.mobile = mobile;
    }

    public String getCreatedTimeBegin() {
        return createdTimeBegin;
    }

    public void setCreatedTimeBegin(String createdTimeBegin) {
        this.createdTimeBegin = createdTimeBegin;
    }

    public String getCreatedTimeEnd() {
        return createdTimeEnd;
    }

    public void setCreatedTimeEnd(String createdTimeEnd) {
        this.createdTimeEnd = createdTimeEnd;
    }
}