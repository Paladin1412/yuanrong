package com.yuanrong.admin.bean.demand;

import java.io.Serializable;

public class DemandYrAuthorRelation implements Serializable {
    /**
     * 需求id
     */
    private Integer demandId;
    /**
     * yrAuthorId
     */
    private Integer yrAuthorId;

    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public Integer getYrAuthorId() {
        return yrAuthorId;
    }

    public void setYrAuthorId(Integer yrAuthorId) {
        this.yrAuthorId = yrAuthorId;
    }
}
