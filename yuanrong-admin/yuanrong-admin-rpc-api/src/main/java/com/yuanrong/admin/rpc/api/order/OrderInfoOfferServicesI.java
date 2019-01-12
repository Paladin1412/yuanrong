package com.yuanrong.admin.rpc.api.order;
import com.yuanrong.admin.bean.order.OrderInfoOffer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.bean.order.SellerOrder;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.seach.OrderOfferParam;
import com.yuanrong.admin.seach.OrderPriceParamSearch;

import java.util.List;

/**
 * 卖家订单报价的services接口
 * Created MDA
 */
public interface OrderInfoOfferServicesI extends BaseServicesI<OrderInfoOffer> {
    /**
     *@author songwq
     *@param
     *@date 2018/9/20
     *@description 修改应约价
    */
    void updateSignUp(OrderPriceParamSearch orderPriceParamSearch,OrderInfoSeller orderInfoSeller);
    /**
     *@author songwq
     *@param
     *@date 2018/9/20
     *@description 修改应约价
    */
    void updateOrderInfo(OrderPriceParamSearch orderPriceParamSearch,OrderInfoSeller orderInfoSeller,SellerOrder sellerOrder);

    /**
     * 确认使用前修改价格
     * @param orderOfferParam
     * @param userName
     */
    void updateOrderOffer(OrderInfoOffer orderOfferParam,String userName);

    /**
     * 根据报名Id获取报价项
     * @param orderInfoSellerId
     * @return
     */
    List<OrderInfoOffer> getByOrderInfoSellerId(Integer orderInfoSellerId);
}
