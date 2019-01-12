package com.yuanrong.admin.bean.order;
import java.util.*;
import java.io.Serializable;
import java.math.*;

import com.yuanrong.admin.Enum.EnumCostType;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.util.EnumUtil;

/**
 * 买家订单附加费用的实体类
 *
 * @author MDA
 *
 */
public class OrderCostInfo extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private Integer orderCostInfoId;
    /*****自定义属性区域begin******/
   
   
    /**
     * 订单ID(买家订单id或者卖家订单id)
     * 订单ID(买家订单id或者卖家订单id)
     */
    private Integer orderInfoId;

    /**
     * 金额
     * 金额
     */
    private BigDecimal money;
   
    /**
     * 字典表，费用名称（服务费，发票费，优惠等）
     * 字典表，费用名称（服务费，发票费，优惠等）
     */
    private Integer costId;
   
    /**
     * 费用类别（1.买家订单；2.卖家订单）
     * 费用类别（1.买家订单；2.卖家订单）
     */
    private EnumCostType costType;
    /*
     * 用于接收前台数据
     */
    private Integer costTypeIndex;

    /**
     * 字典表，费用名称（服务费，发票费，优惠等）
     * 字典表，费用名称（服务费，发票费，优惠等）
     */
    private DictInfo dictInfo;

    public Integer getOrderCostInfoId() {
        return this.orderCostInfoId;
    }
    public void setOrderCostInfoId(Integer orderCostInfoId) {
        this.orderCostInfoId = orderCostInfoId;
    }
    /*****自定义属性区域begin.get/set******/
   
    public Integer getOrderInfoId() {
        return this.orderInfoId;
    }
    public void setOrderInfoId(Integer orderInfoId) {
        this.orderInfoId = orderInfoId;
    }
   
    public BigDecimal getMoney() {
        return this.money;
    }
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    public Integer getCostId() {
        return this.costId;
    }
    public void setCostId(Integer costId) {
        this.costId = costId;
    }
   
    public EnumCostType getCostType() {
    return costType;
}
    public void setCostType(EnumCostType costType) {
        this.costType = costType;
        this.costTypeIndex = costType.getIndex();
    }

    public Integer getCostTypeIndex() {
        return costTypeIndex;
    }
    public void setCostTypeIndex(Integer costTypeIndex) {
        this.costType = (EnumCostType) EnumUtil.valueOf(EnumCostType.class, costTypeIndex);
        this.costTypeIndex = costTypeIndex;
    }

    public DictInfo getDictInfo() {
        return dictInfo;
    }

    public void setDictInfo(DictInfo dictInfo) {
        this.dictInfo = dictInfo;
    }
}
