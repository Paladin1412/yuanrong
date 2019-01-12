package com.yuanrong.admin.rpc.api.order;

/**
 * Created by zhonghang on 2018/7/3.
 */
public interface OrderSnFactoryServicesI {

    /**
     * 生成买家订单号
     * @return
     */
    public String createBuyerOrderSn();

    /**
     * 生成卖家订单号
     * @return
     */
    public String createSellerOrderSn();

    /**
     * 生成需求订单号
     * @return
     */
    public String createDemandOrderSn();

    /**
     * 支付单号
     * @return
     */
    public String createdPayOrderSn();
}
