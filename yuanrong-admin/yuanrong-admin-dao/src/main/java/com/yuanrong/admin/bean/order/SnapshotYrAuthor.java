package com.yuanrong.admin.bean.order;
import java.util.*;
import java.io.Serializable;
import java.math.*;
import com.yuanrong.admin.bean.BaseBean;

/**
 * 作者快照的实体类
 *
 * @author MDA
 *
 */
public class SnapshotYrAuthor extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private Integer snapshotYrAuthorId;
    /*****自定义属性区域begin******/
   


    private Integer orderDetailId;
    /**
     * 作者昵称
     * 作者昵称
     */
    private String authorNickname;
   
    /**
     * 作者头像
     * 作者头像
     */
    private String authorImg;
   
    /**
     * 创作者简介
     * 创作者简介
     */
    private String introduction;
   
    /**
     * 作者Id
     * 作者Id
     */
    private Integer yrAuthorId;
    /**
     * 参考报价
     */
    private String priceInfo;
    public Integer getSnapshotYrAuthorId() {
        return this.snapshotYrAuthorId;
    }
    public void setSnapshotYrAuthorId(Integer snapshotYrAuthorId) {
        this.snapshotYrAuthorId = snapshotYrAuthorId;
    }
    /*****自定义属性区域begin.get/set******/
   
    public String getAuthorNickname() {
        return this.authorNickname;
    }
    public void setAuthorNickname(String authorNickname) {
        this.authorNickname = authorNickname;
    }
    public String getAuthorImg() {
        return this.authorImg;
    }
    public void setAuthorImg(String authorImg) {
        this.authorImg = authorImg;
    }
    public String getIntroduction() {
        return this.introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public Integer getYrAuthorId() {
        return this.yrAuthorId;
    }
    public void setYrAuthorId(Integer yrAuthorId) {
        this.yrAuthorId = yrAuthorId;
    }

    public String getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(String priceInfo) {
        this.priceInfo = priceInfo;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
}
