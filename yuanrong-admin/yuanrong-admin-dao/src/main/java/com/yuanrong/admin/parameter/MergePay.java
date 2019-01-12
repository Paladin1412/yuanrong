package com.yuanrong.admin.parameter;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhonghang on 2018/10/11.
 */
public class MergePay implements Serializable {
    private WxPayUnifiedOrderRequest orderRequest;
    private List<OrderInfoBuyer> orderInfoBuyers;
    private BigDecimal totalMoney;

    public MergePay(WxPayUnifiedOrderRequest orderRequest, List<OrderInfoBuyer> orderInfoBuyers, BigDecimal totalMoney) {
        this.orderRequest = orderRequest;
        this.orderInfoBuyers = orderInfoBuyers;
        this.totalMoney = totalMoney;
    }

    public WxPayUnifiedOrderRequest getOrderRequest() {
        return orderRequest;
    }

    public void setOrderRequest(WxPayUnifiedOrderRequest orderRequest) {
        this.orderRequest = orderRequest;
    }

    public List<OrderInfoBuyer> getOrderInfoBuyers() {
        return orderInfoBuyers;
    }

    public void setOrderInfoBuyers(List<OrderInfoBuyer> orderInfoBuyers) {
        this.orderInfoBuyers = orderInfoBuyers;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
}
