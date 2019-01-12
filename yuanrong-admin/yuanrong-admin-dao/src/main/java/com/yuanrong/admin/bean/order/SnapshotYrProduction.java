package com.yuanrong.admin.bean.order;
import java.util.*;
import java.io.Serializable;
import java.math.*;

import com.yuanrong.admin.Enum.EnumOrderSellerStatus;
import com.yuanrong.admin.Enum.EnumPublishStatus;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.util.EnumUtil;

/**
 * 作品快照的实体类
 *
 * @author MDA
 *
 */
public class SnapshotYrProduction extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private Integer snapshotYrProductionId;
    /*****自定义属性区域begin******/

    /**
     * 订单明细ID
     */
    private Integer orderDetailId;
   
    /**
     * title
     * 作品名称
     */
    private String title;
   
    /**
     * localcontent
     * 本地内容
     */
    private String localcontent;
   
    /**
     * yrAuthorName
     * 作者名字
     */
    private String yrAuthorName;
   
    /**
     * coverLocalUrl
     * 作品图片
     */
    private String coverLocalUrl;
   
    /**
     * contentForm
     * 内容形式
     */
    private String contentForm;

    /**
     * publishStatus
     * 发布状态
     */
    private EnumPublishStatus publishStatus;

    /**
     * publishStatus
     * 发布状态
     */
    private Integer publishStatusValue;
   
    /**
     * isRepresentative
     * 是否代表作
     */
    private Integer isRepresentative;
   
    /**
     * 作品
     * 作品
     */
    private YRProduction yrProduction;
    /**
     * 作品Id
     */
    private Integer yrProductionId;
    /**
     * 报价
     */
    private BigDecimal productQuotedPrice;
    /**
     * 字数
     */
    private Integer wordNum;
    /**
     * 图片数
     */
    private Integer imgNum;
    public Integer getSnapshotYrProductionId() {
        return this.snapshotYrProductionId;
    }
    public void setSnapshotYrProductionId(Integer snapshotYrProductionId) {
        this.snapshotYrProductionId = snapshotYrProductionId;
    }
    /*****自定义属性区域begin.get/set******/
   
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLocalcontent() {
        return this.localcontent;
    }
    public void setLocalcontent(String localcontent) {
        this.localcontent = localcontent;
    }
    public String getYrAuthorName() {
        return this.yrAuthorName;
    }
    public void setYrAuthorName(String yrAuthorName) {
        this.yrAuthorName = yrAuthorName;
    }
    public String getCoverLocalUrl() {
        return this.coverLocalUrl;
    }
    public void setCoverLocalUrl(String coverLocalUrl) {
        this.coverLocalUrl = coverLocalUrl;
    }
    public String getContentForm() {
        return this.contentForm;
    }
    public void setContentForm(String contentForm) {
        this.contentForm = contentForm;
    }

    public EnumPublishStatus getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(EnumPublishStatus publishStatus) {
        this.publishStatus = publishStatus;
        this.publishStatusValue = publishStatus.getIndex();
    }

    public Integer getPublishStatusValue() {
        return publishStatusValue;
    }

    public void setPublishStatusValue(Integer publishStatusValue) {
        this.publishStatusValue = publishStatusValue;
        this.publishStatus = (EnumPublishStatus) EnumUtil.valueOf(EnumPublishStatus.class, publishStatusValue);
    }

    public Integer getIsRepresentative() {
        return this.isRepresentative;
    }
    public void setIsRepresentative(Integer isRepresentative) {
        this.isRepresentative = isRepresentative;
    }
   
    public YRProduction getYrProduction() {
        return this.yrProduction;
    }
    public void setYrProduction(YRProduction yrProduction) {
        this.yrProduction = yrProduction;
    }

    public Integer getYrProductionId() {
        return yrProductionId;
    }

    public void setYrProductionId(Integer yrProductionId) {
        this.yrProductionId = yrProductionId;
    }

    public BigDecimal getProductQuotedPrice() {
        return productQuotedPrice;
    }

    public void setProductQuotedPrice(BigDecimal productQuotedPrice) {
        this.productQuotedPrice = productQuotedPrice;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getWordNum() {
        return wordNum;
    }

    public void setWordNum(Integer wordNum) {
        this.wordNum = wordNum;
    }

    public Integer getImgNum() {
        return imgNum;
    }

    public void setImgNum(Integer imgNum) {
        this.imgNum = imgNum;
    }
}
