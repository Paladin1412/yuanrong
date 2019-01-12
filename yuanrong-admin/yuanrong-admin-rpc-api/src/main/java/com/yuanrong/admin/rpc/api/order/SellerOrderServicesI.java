package com.yuanrong.admin.rpc.api.order;

import com.yuanrong.admin.bean.order.SellerOrder;
import com.yuanrong.admin.result.SellerOrderResult;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.seach.OrderManagementSearch;
import com.yuanrong.admin.seach.OrderPriceParamSearch;
import com.yuanrong.admin.seach.SellerOrderSearch;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 卖家订单的services接口
 * Created MDA
 */
public interface SellerOrderServicesI extends BaseServicesI<SellerOrder> {
    List<SellerOrder> getByEntity(SellerOrder sellerOrder);

    /**
     * 根据订单查询数据
     * @param orderSn
     * @return
     */
    SellerOrder getByOrderSn(String orderSn);

    /**
     *@author songwq
     *@param
     *@date 2018/9/19
     *@description 修改价格
    */
    void updateProductPrice(OrderPriceParamSearch orderPriceParamSearch,SellerOrder sellerOrder);

    /**
     * 通过报名号或者卖家订单号获取卖家订单详情
     * @param sellerOrderSearch
     * @return
     */
    SellerOrderResult getDetailBySn(SellerOrderSearch sellerOrderSearch);

    /**
     * 查询各订单状态的订单数
     * @param
     */
    List<Map<String,Object>> getOrderStatusCount(SellerOrderSearch data);
}
