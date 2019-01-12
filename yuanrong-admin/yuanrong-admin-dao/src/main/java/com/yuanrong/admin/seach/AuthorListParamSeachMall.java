package com.yuanrong.admin.seach;

import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 创作者列表查询参数
 */
public class AuthorListParamSeachMall extends BaseBean implements Serializable {
    /**
     * 关键词查询
     */
    private String likeName;
    /**
     * 内容领域
     */
    private Integer category;
    /**
     * 使用场景
     */
    private Integer scenes;
    /**
     * 内容形式
     */
    private Integer contentForm;
    /**
     * 创作者原创报价—用于接收数据
     */
    private String creationPrice;
    /**
     * 创作者原创报价—结束
     */
    private Integer creationPriceEnd;
    /**
     * 创作者原创报价—开始
     */
    private Integer creationPriceStart;
    /**
     * 创作者原创报价—最大
     */
    private Integer creationPriceMax;
    /**
     * 创作者原创用时—用于接收数据
     */
    private String creationTime;
    /**
     * 创作者原创用时—开始
     */
    private Integer creationTimeStart;
    /**
     * 创作者原创用时—结束
     */
    private Integer creationTimeEnd;
    /**
     * 创作者原创用时—最大
     */
    private Integer creationTimeMax;
    /**
     * 创作风格(标签的内容属性/表现风格)
     */
    private Integer lableId;
    /**
     * 当前用户id
     */
    private Integer registeredUserInfoId;

    private static final long serialVersionUID = 1L;

    public String getLikeName() {
        return likeName;
    }

    public void setLikeName(String likeName) {
        this.likeName = likeName;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getScenes() {
        return scenes;
    }

    public void setScenes(Integer scenes) {
        this.scenes = scenes;
    }

    public Integer getContentForm() {
        return contentForm;
    }

    public void setContentForm(Integer contentForm) {
        this.contentForm = contentForm;
    }

    public String getCreationPrice() {
        return creationPrice;
    }

    public void setCreationPrice(String creationPrice) {
        this.creationPrice = creationPrice;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public Integer getLableId() {
        return lableId;
    }

    public void setLableId(Integer lableId) {
        this.lableId = lableId;
    }

    public Integer getCreationPriceEnd() {
        return creationPriceEnd;
    }

    public void setCreationPriceEnd(Integer creationPriceEnd) {
        this.creationPriceEnd = creationPriceEnd;
    }

    public Integer getCreationPriceStart() {
        return creationPriceStart;
    }

    public void setCreationPriceStart(Integer creationPriceStart) {
        this.creationPriceStart = creationPriceStart;
    }

    public Integer getCreationPriceMax() {
        return creationPriceMax;
    }

    public void setCreationPriceMax(Integer creationPriceMax) {
        this.creationPriceMax = creationPriceMax;
    }

    public Integer getCreationTimeStart() {
        return creationTimeStart;
    }

    public void setCreationTimeStart(Integer creationTimeStart) {
        this.creationTimeStart = creationTimeStart;
    }

    public Integer getCreationTimeEnd() {
        return creationTimeEnd;
    }

    public void setCreationTimeEnd(Integer creationTimeEnd) {
        this.creationTimeEnd = creationTimeEnd;
    }

    public Integer getCreationTimeMax() {
        return creationTimeMax;
    }

    public void setCreationTimeMax(Integer creationTimeMax) {
        this.creationTimeMax = creationTimeMax;
    }

    public Integer getRegisteredUserInfoId() {
        return registeredUserInfoId;
    }

    public void setRegisteredUserInfoId(Integer registeredUserInfoId) {
        this.registeredUserInfoId = registeredUserInfoId;
    }
}