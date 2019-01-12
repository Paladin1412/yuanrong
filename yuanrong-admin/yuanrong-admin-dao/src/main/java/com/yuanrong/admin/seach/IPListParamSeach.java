package com.yuanrong.admin.seach;

import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * IP列表查询参数
 */
public class IPListParamSeach extends BaseBean implements Serializable {
    /**
     * 主键
     */
    private Integer recID;
    private String[] recIDs;
    private String ids;
    /**
     * IP名称
     */
    private String iPName;
    private String[] iPNames;
    /**
     * 行业分类
     */
    private Integer categoryID;
    /**
     * 是否全网报价
     */
    private Integer isPrice;
    /**
     * IP全网发布报价开始
     */
    private BigDecimal priceBegin;
    /**
     * IP全网发布报价结束
     */
    private BigDecimal priceEnd;
    /**
     * 价格有效期开始
     */
    private String invalidTimeBegin;
    /**
     * 价格有效期结束
     */
    private String invalidTimeEnd;
    /**
     * 账号数开始
     */
    private Integer accountNumBegin;
    /**
     * 账号数结束
     */
    private Integer accountNumEnd;

    private static final long serialVersionUID = 1L;

    public Integer getRecID() {
        return recID;
    }

    public void setRecID(Integer recID) {
        this.recID = recID;
    }

    public String[] getRecIDs() {
        return recIDs;
    }

    public void setRecIDs(String[] recIDs) {
        this.recIDs = recIDs;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getiPName() {
        return iPName;
    }

    public void setiPName(String iPName) {
        this.iPName = iPName;
    }

    public String[] getiPNames() {
        return iPNames;
    }

    public void setiPNames(String[] iPNames) {
        this.iPNames = iPNames;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getIsPrice() {
        return isPrice;
    }

    public void setIsPrice(Integer isPrice) {
        this.isPrice = isPrice;
    }

    public BigDecimal getPriceBegin() {
        return priceBegin;
    }

    public void setPriceBegin(BigDecimal priceBegin) {
        this.priceBegin = priceBegin;
    }

    public BigDecimal getPriceEnd() {
        return priceEnd;
    }

    public void setPriceEnd(BigDecimal priceEnd) {
        this.priceEnd = priceEnd;
    }

    public String getInvalidTimeBegin() {
        return invalidTimeBegin;
    }

    public void setInvalidTimeBegin(String invalidTimeBegin) {
        this.invalidTimeBegin = invalidTimeBegin;
    }

    public String getInvalidTimeEnd() {
        return invalidTimeEnd;
    }

    public void setInvalidTimeEnd(String invalidTimeEnd) {
        this.invalidTimeEnd = invalidTimeEnd;
    }

    public Integer getAccountNumBegin() {
        return accountNumBegin;
    }

    public void setAccountNumBegin(Integer accountNumBegin) {
        this.accountNumBegin = accountNumBegin;
    }

    public Integer getAccountNumEnd() {
        return accountNumEnd;
    }

    public void setAccountNumEnd(Integer accountNumEnd) {
        this.accountNumEnd = accountNumEnd;
    }
}