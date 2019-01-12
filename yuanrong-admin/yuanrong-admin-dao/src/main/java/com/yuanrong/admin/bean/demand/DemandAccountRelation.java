package com.yuanrong.admin.bean.demand;

import java.io.Serializable;

public class DemandAccountRelation implements Serializable {
    /**
     *  需求id
     */
    private Integer demandId;

    /**
     *   账号id
     */
    private Integer platformIPAccountId;

    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public Integer getPlatformIPAccountId() {
        return platformIPAccountId;
    }

    public void setPlatformIPAccountId(Integer platformIPAccountId) {
        this.platformIPAccountId = platformIPAccountId;
    }
}
