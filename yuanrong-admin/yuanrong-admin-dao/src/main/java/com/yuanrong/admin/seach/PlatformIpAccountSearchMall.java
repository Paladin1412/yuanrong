package com.yuanrong.admin.seach;

import com.github.pagehelper.util.StringUtil;
import com.yuanrong.admin.bean.BaseBean;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Created by zhonghang on 2018/6/1.
 */
public class PlatformIpAccountSearchMall extends BaseBean implements Serializable {
    private String sort_fans;
    private String sort_AvgReadNum;
    private String sort_AvgForwardCount;
    private String sort_AvgCommontCount;
    private String sort_AvgLikeCount;
    private String sort_YrIndex;
    private String sort_avgPlayCount;
    private Integer categoryID;
    private String search;

    private String fansNum;
    private Integer fansNumStart;
    private Integer fansNumEnd;
    private Integer fansNumMax;

    private String referQuo;
    private Integer referQuoEnd;
    private Integer referQuoStart;
    private Integer referQuoMax;

    private String order;
    /**
     * 平台ID
     */
    private Integer platFormId;
    /**
     * 是否有代理权
     */
    private Integer isAgent;

    public Integer getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(Integer isAgent) {
        this.isAgent = isAgent;
    }

    public String getSort_fans() {
        return sort_fans;
    }

    public void setSort_fans(String sort_fans) {
        this.sort_fans = sort_fans;
    }

    public String getSort_AvgReadNum() {
        return sort_AvgReadNum;
    }

    public void setSort_AvgReadNum(String sort_AvgReadNum) {
        this.sort_AvgReadNum = sort_AvgReadNum;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getFansNum() {
        return fansNum;
    }

    public void setFansNum(String fansNum) {
        this.fansNum = fansNum;
        String[] str = fansNum.split("-");
        if (str.length > 1) { //区间搜索粉丝数
            this.fansNumEnd = Integer.parseInt(str[1]);
            this.fansNumStart = Integer.parseInt(str[0]);
        } else {//搜索指定值以上的
            this.fansNumMax = Integer.parseInt(str[0]);
        }
    }

    public Integer getFansNumStart() {
        return fansNumStart;
    }

    public void setFansNumStart(Integer fansNumStart) {
        this.fansNumStart = fansNumStart;
    }

    public Integer getFansNumEnd() {
        return fansNumEnd;
    }

    public void setFansNumEnd(Integer fansNumEnd) {
        this.fansNumEnd = fansNumEnd;
    }

    public Integer getFansNumMax() {
        return fansNumMax;
    }

    public void setFansNumMax(Integer fansNumMax) {
        this.fansNumMax = fansNumMax;
    }

    public String getReferQuo() {
        return referQuo;
    }

    public void setReferQuo(String referQuo) {
        if (referQuo != null) {
            String[] str = referQuo.split("-");
            if (str.length > 1) { //区间搜索粉丝数
                this.referQuoEnd = Integer.parseInt(str[1]);
                this.referQuoStart = Integer.parseInt(str[0]);
            } else {//搜索指定值以上的
                this.referQuoMax = Integer.parseInt(str[0]);
            }
        }
        this.referQuo = referQuo;
    }

    public Integer getReferQuoEnd() {
        return referQuoEnd;
    }

    public void setReferQuoEnd(Integer referQuoEnd) {
        this.referQuoEnd = referQuoEnd;
    }

    public Integer getReferQuoStart() {
        return referQuoStart;
    }

    public void setReferQuoStart(Integer referQuoStart) {
        this.referQuoStart = referQuoStart;
    }

    public Integer getReferQuoMax() {
        return referQuoMax;
    }

    public void setReferQuoMax(Integer referQuoMax) {
        this.referQuoMax = referQuoMax;
    }

    public Integer getPlatFormId() {
        return platFormId;
    }

    public void setPlatFormId(Integer platFormId) {
        this.platFormId = platFormId;
    }

    public String getOrder(){
        String order = "";
        //粉丝数 平均阅读数
        //粉丝排序 asc 升序 、desc降序
        if (StringUtils.isNotBlank(this.sort_fans)) {
            order += StringUtils.isNotBlank(order) ? " , " : "";
            order += "asc".equals(this.sort_fans) ? "pi.fans asc " : "pi.fans desc ";
        }
        //平均阅读
        if (StringUtils.isNotBlank(this.sort_AvgReadNum)) {
            order += StringUtils.isNotBlank(order) ? " , " : "";
            order += "asc".equals(this.sort_AvgReadNum) ? "pi.avgReadCount asc " : "pi.avgReadCount desc ";
        }
        //转发
        if (StringUtils.isNotBlank(this.sort_AvgForwardCount)) {
            order += StringUtils.isNotBlank(order) ? " , " : "";
            order += "asc".equals(this.sort_AvgForwardCount) ? "pi.avgForwardCount asc " : "pi.avgForwardCount desc ";
        }
        //评论
        if (StringUtils.isNotBlank(this.sort_AvgCommontCount)) {
            order += StringUtils.isNotBlank(order) ? " , " : "";
            order += "asc".equals(this.sort_AvgCommontCount) ? "pi.avgCommontCount asc " : "pi.avgCommontCount desc ";
        }
        //点赞
        if (StringUtils.isNotBlank(this.sort_AvgLikeCount)) {
            order += StringUtils.isNotBlank(order) ? " , " : "";
            order += "asc".equals(this.sort_AvgLikeCount) ? "pi.avgLikeCount asc " : "pi.avgLikeCount desc ";
        }
        //圆融指数
        if (StringUtils.isNotBlank(this.sort_YrIndex)) {
            order += StringUtils.isNotBlank(order) ? " , " : "";
            order += "asc".equals(this.sort_YrIndex) ? "pi.yrIndex asc " : "pi.yrIndex desc ";
        }
        //平均播放次数
        if (StringUtils.isNotBlank(this.sort_avgPlayCount)) {
            order += StringUtils.isNotBlank(order) ? " , " : "";
            order += "asc".equals(this.sort_avgPlayCount) ? "pi.avgPlayCount asc " : "pi.avgPlayCount desc ";
        }


        order = StringUtil.isNotEmpty(order) ? order : "pi.reservationNum desc,pi.fans desc";
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort_AvgForwardCount() {
        return sort_AvgForwardCount;
    }

    public void setSort_AvgForwardCount(String sort_AvgForwardCount) {
        this.sort_AvgForwardCount = sort_AvgForwardCount;
    }

    public String getSort_AvgCommontCount() {
        return sort_AvgCommontCount;
    }

    public void setSort_AvgCommontCount(String sort_AvgCommontCount) {
        this.sort_AvgCommontCount = sort_AvgCommontCount;
    }

    public String getSort_AvgLikeCount() {
        return sort_AvgLikeCount;
    }

    public void setSort_AvgLikeCount(String sort_AvgLikeCount) {
        this.sort_AvgLikeCount = sort_AvgLikeCount;
    }

    public String getSort_YrIndex() {
        return sort_YrIndex;
    }

    public void setSort_YrIndex(String sort_YrIndex) {
        this.sort_YrIndex = sort_YrIndex;
    }

    public String getSort_avgPlayCount() {
        return sort_avgPlayCount;
    }

    public void setSort_avgPlayCount(String sort_avgPlayCount) {
        this.sort_avgPlayCount = sort_avgPlayCount;
    }
}
