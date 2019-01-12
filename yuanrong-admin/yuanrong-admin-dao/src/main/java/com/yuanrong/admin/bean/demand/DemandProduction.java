package com.yuanrong.admin.bean.demand;

import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;
import java.util.Date;

/**
 * 报名表
 * @author Tangz
 * @date 2018/9/7 17:49
 */
public class DemandProduction extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    private Integer demandProductionId ;
    /**
     * 需求id
     */
    private Integer demandId;
    /**
     * 所属作品ID
     */
    private Integer yrProductionId;
    /**
     * 报名用户ID
     */
    private Integer   registeredUserInfoId;
    /**
     * 确认时间
     */
    private String confirmTime;

    public Integer getDemandProductionId() {
        return demandProductionId;
    }

    public void setDemandProductionId(Integer demandProductionId) {
        this.demandProductionId = demandProductionId;
    }

    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public Integer getYrProductionId() {
        return yrProductionId;
    }

    public void setYrProductionId(Integer yrProductionId) {
        this.yrProductionId = yrProductionId;
    }

    public Integer getRegisteredUserInfoId() {
        return registeredUserInfoId;
    }

    public void setRegisteredUserInfoId(Integer registeredUserInfoId) {
        this.registeredUserInfoId = registeredUserInfoId;
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }
}
