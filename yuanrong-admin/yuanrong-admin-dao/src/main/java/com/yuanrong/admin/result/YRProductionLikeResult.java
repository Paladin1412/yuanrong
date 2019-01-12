package com.yuanrong.admin.result;

import java.io.Serializable;
import java.math.BigDecimal;

/*
 *@author songwq
 *@date 2018/8/3
 *@time 18:18
 *@description
 */
public class YRProductionLikeResult  implements Serializable {

    private Integer recId;
    private String productionTitle;
    private String publishPlatform;
    private BigDecimal productQuotedPrice;
    private Integer yrAuthorId;
    private String authorNickname;
    private String publishTime;
    private String localContent;
    private String crawTitle;
    private String name;
    private String tags;
    private Integer contentScore;
    private String coverLocalUrl;
    private String contentFormId;
    private String contentFormName;
    private String authorImg;
    private String yrAuthorCategory;
    private String accessNum;
    private String isAnonymous;
    private String prnum;
    private String yrAuthorStatus;
    private String contentform;
    private String imgNum;
    private String wordNum;
    private String publishStatusIndex;


    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public String getProductionTitle() {
        return productionTitle;
    }

    public void setProductionTitle(String productionTitle) {
        this.productionTitle = productionTitle;
    }

    public String getPublishPlatform() {
        return publishPlatform;
    }

    public void setPublishPlatform(String publishPlatform) {
        this.publishPlatform = publishPlatform;
    }

    public BigDecimal getProductQuotedPrice() {
        return productQuotedPrice;
    }

    public void setProductQuotedPrice(BigDecimal productQuotedPrice) {
        this.productQuotedPrice = productQuotedPrice;
    }

    public Integer getYrAuthorId() {
        return yrAuthorId;
    }

    public void setYrAuthorId(Integer yrAuthorId) {
        this.yrAuthorId = yrAuthorId;
    }

    public String getAuthorNickname() {
        return authorNickname;
    }

    public void setAuthorNickname(String authorNickname) {
        this.authorNickname = authorNickname;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getLocalContent() {
        return localContent;
    }

    public void setLocalContent(String localContent) {
        this.localContent = localContent;
    }

    public String getCrawTitle() {
        return crawTitle;
    }

    public void setCrawTitle(String crawTitle) {
        this.crawTitle = crawTitle;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getContentScore() {
        return contentScore;
    }

    public void setContentScore(Integer contentScore) {
        this.contentScore = contentScore;
    }

    public String getCoverLocalUrl() {
        return coverLocalUrl;
    }

    public void setCoverLocalUrl(String coverLocalUrl) {
        this.coverLocalUrl = coverLocalUrl;
    }

    public String getContentFormId() {
        return contentFormId;
    }

    public void setContentFormId(String contentFormId) {
        this.contentFormId = contentFormId;
    }

    public String getContentFormName() {
        return contentFormName;
    }

    public void setContentFormName(String contentFormName) {
        this.contentFormName = contentFormName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorImg() {
        return authorImg;
    }

    public void setAuthorImg(String authorImg) {
        this.authorImg = authorImg;
    }

    public String getYrAuthorCategory() {
        return yrAuthorCategory;
    }

    public void setYrAuthorCategory(String yrAuthorCategory) {
        this.yrAuthorCategory = yrAuthorCategory;
    }

    public String getAccessNum() {
        return accessNum;
    }

    public void setAccessNum(String accessNum) {
        this.accessNum = accessNum;
    }

    public String getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(String isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public String getPrnum() {
        return prnum;
    }

    public void setPrnum(String prnum) {
        this.prnum = prnum;
    }

    public String getYrAuthorStatus() {
        return yrAuthorStatus;
    }

    public void setYrAuthorStatus(String yrAuthorStatus) {
        this.yrAuthorStatus = yrAuthorStatus;
    }

    public String getContentform() {
        return contentform;
    }

    public void setContentform(String contentform) {
        this.contentform = contentform;
    }

    public String getImgNum() {
        return imgNum;
    }

    public void setImgNum(String imgNum) {
        this.imgNum = imgNum;
    }

    public String getWordNum() {
        return wordNum;
    }

    public void setWordNum(String wordNum) {
        this.wordNum = wordNum;
    }

    public String getPublishStatusIndex() {
        return publishStatusIndex;
    }

    public void setPublishStatusIndex(String publishStatusIndex) {
        this.publishStatusIndex = publishStatusIndex;
    }
}
