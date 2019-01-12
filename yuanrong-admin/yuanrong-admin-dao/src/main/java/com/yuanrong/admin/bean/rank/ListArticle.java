package com.yuanrong.admin.bean.rank;

import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;
import java.util.Date;

/**
 * 内容价值排行榜
 */
public class ListArticle extends BaseBean implements Serializable {
    private static final long serialVersionUID = 921912668025850975L;
    /**
     *主键ID
     */
    private  Integer recID;

    /**
     *文章标题
     */
    private  String title;

    /**
     *链接
     */
    private  String link;

    /**
     *阅读数
     */
    private  Integer readCount;

    /**
     *点赞数
     */
    private  Integer likeCount;

    /**
     *主题性
     */
    private  Double theme;

    /**
     *创造性
     */
    private  Double creativity;

    /**
     *原创性
     */
    private  Double originality;

    /**
     *综合指数
     */
    private  Double totalIndex;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *状态值 0 不可用、1 可用
     */
    private  Integer statusValue;

    /**
     *发布时间
     */
    private Date publishTime;

    /**
     * 时间段
     */
    private  String rangeTime;

    private  Integer xbId;

    public ListArticle() {
    }

    public ListArticle(Integer recID,Integer xbId, String title, String link, Integer readCount, Integer likeCount, Double theme, Double creativity, Double originality, Double totalIndex, Date createTime, Integer statusValue, Date publishTime, String rangeTime) {
        this.recID = recID;
        this.xbId = xbId;
        this.title = title;
        this.link = link;
        this.readCount = readCount;
        this.likeCount = likeCount;
        this.theme = theme;
        this.creativity = creativity;
        this.originality = originality;
        this.totalIndex = totalIndex;
        this.createTime = createTime;
        this.statusValue = statusValue;
        this.publishTime = publishTime;
        this.rangeTime = rangeTime;
    }
    public Integer getXbId() {
        return xbId;
    }

    public void setXbId(Integer xbId) {
        this.xbId = xbId;
    }
    public Integer getRecID() {
        return recID;
    }

    public void setRecID(Integer recID) {
        this.recID = recID;
    }

    public String getRangeTime() {
        return rangeTime;
    }

    public void setRangeTime(String rangeTime) {
        this.rangeTime = rangeTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Double getTheme() {
        return theme;
    }

    public void setTheme(Double theme) {
        this.theme = theme;
    }

    public Double getCreativity() {
        return creativity;
    }

    public void setCreativity(Double creativity) {
        this.creativity = creativity;
    }

    public Double getOriginality() {
        return originality;
    }

    public void setOriginality(Double originality) {
        this.originality = originality;
    }

    public Double getTotalIndex() {
        return totalIndex;
    }

    public void setTotalIndex(Double totalIndex) {
        this.totalIndex = totalIndex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(Integer statusValue) {
        this.statusValue = statusValue;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}
