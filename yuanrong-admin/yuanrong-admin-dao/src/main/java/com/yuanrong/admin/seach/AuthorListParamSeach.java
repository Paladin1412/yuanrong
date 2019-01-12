package com.yuanrong.admin.seach;

import com.yuanrong.admin.bean.BaseBean;

import javax.xml.soap.SAAJResult;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 创作者列表查询参数
 */
public class AuthorListParamSeach extends BaseBean implements Serializable {
    /**
     * 创作者名称
     */
    private String authorName;
    /**
     * 内容领域
     */
    private String categoryIds;
    /**
     * 使用场景
     */
    private String scenesIds;
    /**
     * 内容形式
     */
    private String contentIds;
    /**
     * 创作者原创报价开始
     */
    private BigDecimal priceBegin;
    /**
     * 创作者原创报价结束
     */
    private BigDecimal priceEnd;
    /**
     * 创作者状态
     */
    private Integer statusValue;
    /**
     * 用户简称
     */
    private String[] userName;
    /**
     * 用户Id
     */
    private String[] userId;
    /**
     * 入库时间
     */
    private String createTimeBegin;
    /**
     * 入库时间
     */
    private String createTimeEnd;
    /**
     * 创作来源
     */
    private Integer createSource;
    /**
     * 字典状态
     */
    private Integer dicStatus;

    private static final long serialVersionUID = 1L;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getScenesIds() {
        return scenesIds;
    }

    public void setScenesIds(String scenesIds) {
        this.scenesIds = scenesIds;
    }

    public String getContentIds() {
        return contentIds;
    }

    public void setContentIds(String contentIds) {
        this.contentIds = contentIds;
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

    public Integer getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(Integer statusValue) {
        this.statusValue = statusValue;
    }

    public Integer getDicStatus() {
        return dicStatus;
    }

    public void setDicStatus(Integer dicStatus) {
        this.dicStatus = dicStatus;
    }

    public String[] getUserName() {
        return userName;
    }

    public void setUserName(String[] userName) {
        this.userName = userName;
    }

    public String[] getUserId() {
        return userId;
    }

    public void setUserId(String[] userId) {
        this.userId = userId;
    }

    public String getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(String createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Integer getCreateSource() {
        return createSource;
    }

    public void setCreateSource(Integer createSource) {
        this.createSource = createSource;
    }
}