package com.yuanrong.admin.bean.account;

import com.yuanrong.admin.Enum.EnumYesOrNo;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.util.EnumUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * IP列表
 */
public class IP extends BaseBean implements Serializable {
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
     * IP图片
     */
    private String iPImageHeadUrl;
    /**
     * IP粉丝
     */
    private Integer iPFans;
    /**
     * IP简介
     */
    private String iPIntroduction;
    /**
     * IP是否全网报价
     */
    private BigDecimal iPWholeNetworkPrice;
    /**
     * 用户ID
     */
    private Integer registeredUserInfoID;
    /**
     * 是否原创
     */
    private Integer isOriginal;
    /**
     * 行业分类
     */
    private Integer categoryID;
    /**
     * 报价有效期
     */
    //
    private String invalidTime;
    private EnumYesOrNo enumYesOrNo;
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

    public String getiPName() {
        return iPName;
    }

    public void setiPName(String iPName) {
        this.iPName = iPName == null ? null : iPName.trim();
    }

    public String getiPImageHeadUrl() {
        return iPImageHeadUrl;
    }

    public void setiPImageHeadUrl(String iPImageHeadUrl) {
        this.iPImageHeadUrl = iPImageHeadUrl == null ? null : iPImageHeadUrl.trim();
    }

    public Integer getiPFans() {
        return iPFans;
    }

    public void setiPFans(Integer iPFans) {
        this.iPFans = iPFans;
    }

    public String getiPIntroduction() {
        return iPIntroduction;
    }

    public void setiPIntroduction(String iPIntroduction) {
        this.iPIntroduction = iPIntroduction == null ? null : iPIntroduction.trim();
    }

    public BigDecimal getiPWholeNetworkPrice() {
        return iPWholeNetworkPrice;
    }

    public void setiPWholeNetworkPrice(BigDecimal iPWholeNetworkPrice) {
        this.iPWholeNetworkPrice = iPWholeNetworkPrice;
    }

    public Integer getRegisteredUserInfoID() {
        return registeredUserInfoID;
    }

    public void setRegisteredUserInfoID(Integer registeredUserInfoID) {
        this.registeredUserInfoID = registeredUserInfoID;
    }

    public Integer getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(Integer isOriginal) {
        this.isOriginal = isOriginal;
        this.enumYesOrNo = (EnumYesOrNo) EnumUtil.valueOf(EnumYesOrNo.class,isOriginal);
    }

    public Integer getIsPrice() {
        return isPrice;
    }

    public void setIsPrice(Integer isPrice) {
        this.isPrice = isPrice;
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

    public EnumYesOrNo getEnumYesOrNo() {
        return enumYesOrNo;
    }

    public void setEnumYesOrNo(EnumYesOrNo enumYesOrNo) {
        this.enumYesOrNo = enumYesOrNo;
        this.isOriginal = enumYesOrNo.getIndex();
    }

    public String[] getRecIDs() {
        return recIDs;
    }

    public void setRecIDs(String[] recIDs) {
        this.recIDs = recIDs;
    }

    public String[] getiPNames() {
        return iPNames;
    }

    public void setiPNames(String[] iPNames) {
        this.iPNames = iPNames;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recID=").append(recID);
        sb.append(", iPName=").append(iPName);
        sb.append(", iPImageHeadUrl=").append(iPImageHeadUrl);
        sb.append(", iPFans=").append(iPFans);
        sb.append(", iPIntroduction=").append(iPIntroduction);
        sb.append(", iPWholeNetworkPrice=").append(iPWholeNetworkPrice);
        sb.append(", registeredUserInfoID=").append(registeredUserInfoID);
        sb.append("]");
        return sb.toString();
    }
}