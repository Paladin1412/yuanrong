package com.yuanrong.admin.bean.account;

import com.yuanrong.admin.Enum.EnumYesOrNo;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.util.EnumUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhonghang on 2018/4/27.
 * IP账号价格表
 */
public class PlatformIPAccountPrice extends BaseBean implements Serializable {
    private int id;
    private int iPAcctountID;//	IPAcctount表的ID字段
    private BigDecimal price ; //	价格
    private int platformPriceNameID ; //	价格说明-名称
    private PlatformPriceName platformPriceName;
    private int source ; //	来源 0我们 1微博易 2新榜 3weiq 4代理公司1 5 代理公司2
    private Date endTime ; //	过期时间

    /**
     * 是否原创
     */
    private EnumYesOrNo isOriginalEnum;
    private Integer isOriginal;

    /**
     * 非数据库字段
     */
    private String priceName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getiPAcctountID() {
        return iPAcctountID;
    }

    public void setiPAcctountID(int iPAcctountID) {
        this.iPAcctountID = iPAcctountID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getPlatformPriceNameID() {
        return platformPriceNameID;
    }

    public void setPlatformPriceNameID(int platformPriceNameID) {
        this.platformPriceNameID = platformPriceNameID;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public EnumYesOrNo getIsOriginalEnum() {
        return isOriginalEnum;
    }

    public void setIsOriginalEnum(EnumYesOrNo isOriginalEnum) {
        this.isOriginal = isOriginalEnum.getIndex();
        this.isOriginalEnum = isOriginalEnum;
    }

    public Integer getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(Integer isOriginal) {
        this.isOriginalEnum = (EnumYesOrNo) EnumUtil.valueOf(EnumYesOrNo.class , isOriginal);
        this.isOriginal = isOriginal;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public PlatformPriceName getPlatformPriceName() {
        return platformPriceName;
    }

    public void setPlatformPriceName(PlatformPriceName platformPriceName) {
        this.platformPriceName = platformPriceName;
    }
}
