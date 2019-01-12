package com.yuanrong.admin.seach;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @Description:    作品修改价格参数封装类
* @Author:         ShiLinghuai
* @CreateDate:     2018/5/25 16:09
* @UpdateUser:     ShiLinghuai
* @UpdateDate:     2018/5/25 16:09
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class YRProductionChangePrice implements Serializable {
    /**
     * 作品id
     */
    private String recID;
    private List<Integer> ids;

    /**
     * 价格
     */
    private BigDecimal price;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRecID() {
        return recID;
    }

    public void setRecID(String recID) {
        this.recID = recID;
    }
}
