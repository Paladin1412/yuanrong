package com.yuanrong.admin.bean.order;
import java.util.*;
import java.io.Serializable;
import java.math.*;

import com.yuanrong.admin.Enum.EnumProductType;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.util.EnumUtil;

/**
 * 订单明细的实体类
 *
 * @author MDA
 *
 */
public class OrderDetail extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private Integer orderDetailId;
    /*****自定义属性区域begin******/
   
   
    /**
     * 所属买家订单Id
     * 所属买家订单
     */
    private Integer orderInfoBuyerId;
    private OrderInfoBuyer orderInfoBuyer;
   
    /**
     * 买家单价(商品的价格)
     * 买家单价(商品的价格) 加了服务费的
     */
    private BigDecimal price;

    /**
     * 底价 没加服务费的
     */
    private BigDecimal basePrice;
   
    /**
     * 商品名称
     * 商品名称
     */
    private String production;
   
    /**
     * 数量
     * 数量
     */
    private Integer num;
   
    /**
     * 商品类别(1.作品；2.账号；3.创作者)
     * 商品类别(1.作品；2.账号；3.创作者)
     */
    private EnumProductType enumProductType;
    /*
    * 用于接收前台数据
    */
    private Integer productType;
   
    /**
     * 关联商品对象(作品id，账号id，创作者id)
     * 关联商品对象(作品id，账号id，创作者id)
     */
    private Integer referId;

    private YRProduction yrProduction;

    //账号快照
    private SnapshotAccount snapshotAccount;
    //作者快照
    private SnapshotYrAuthor snapshotYrAuthor;
    /**
     * 作品快照
     */
    private SnapshotYrProduction snapshotYrProduction;

    public SnapshotYrProduction getSnapshotYrProduction() {
        return snapshotYrProduction;
    }

    public void setSnapshotYrProduction(SnapshotYrProduction snapshotYrProduction) {
        this.snapshotYrProduction = snapshotYrProduction;
    }

    public SnapshotAccount getSnapshotAccount() {
        return snapshotAccount;
    }

    public void setSnapshotAccount(SnapshotAccount snapshotAccount) {
        this.snapshotAccount = snapshotAccount;
    }

    public SnapshotYrAuthor getSnapshotYrAuthor() {
        return snapshotYrAuthor;
    }

    public void setSnapshotYrAuthor(SnapshotYrAuthor snapshotYrAuthor) {
        this.snapshotYrAuthor = snapshotYrAuthor;
    }

    public Integer getOrderDetailId() {
        return this.orderDetailId;
    }
    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
    /*****自定义属性区域begin.get/set******/
   
    public Integer getOrderInfoBuyerId() {
        return this.orderInfoBuyerId;
    }
    public void setOrderInfoBuyerId(Integer orderInfoBuyerId) {
        this.orderInfoBuyerId = orderInfoBuyerId;
    }
   
    public BigDecimal getPrice() {
        return this.price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getProduction() {
        return this.production;
    }
    public void setProduction(String production) {
        this.production = production;
    }
    public Integer getNum() {
        return this.num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
   
    public EnumProductType getEnumProductType() {
    return enumProductType;
}
    public void setEnumProductType(EnumProductType enumProductType) {
        this.enumProductType = enumProductType;
        this.productType =enumProductType.getIndex();
    }
    public Integer getProductType() {
        return productType;
    }
    public void setProductType(Integer productType) {
        this.productType = productType;
        this.enumProductType = (EnumProductType) EnumUtil.valueOf(EnumProductType.class, productType);
    }
    public Integer getReferId() {
        return this.referId;
    }
    public void setReferId(Integer referId) {
        this.referId = referId;
    }

    public YRProduction getYrProduction() {
        return yrProduction;
    }

    public void setYrProduction(YRProduction yrProduction) {
        this.yrProduction = yrProduction;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public OrderInfoBuyer getOrderInfoBuyer() {
        return orderInfoBuyer;
    }

    public void setOrderInfoBuyer(OrderInfoBuyer orderInfoBuyer) {
        this.orderInfoBuyer = orderInfoBuyer;
    }
}
