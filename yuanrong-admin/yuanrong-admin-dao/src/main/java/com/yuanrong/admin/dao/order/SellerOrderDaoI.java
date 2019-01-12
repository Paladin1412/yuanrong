package com.yuanrong.admin.dao.order;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.order.SellerOrder;
import com.yuanrong.admin.dao.BaseDaoI;
import com.yuanrong.admin.result.SellerOrderResult;
import com.yuanrong.admin.seach.OrderManagementSearch;
import com.yuanrong.admin.seach.OrderPriceParamSearch;
import com.yuanrong.admin.seach.SellerOrderSearch;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 卖家订单的dao
 * Created MDA
 */
@Repository
public interface SellerOrderDaoI extends BaseDaoI<SellerOrder> {
    /**
    * 获取卖家订单
    * @author      ShiLinghuai
    * @param
    * @return      
    * @exception   
    * @date        2018/9/14 17:15
    */
    List<SellerOrder> getByEntity(Demand demand);

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
    void updateSellerOrder(@Param(value="data")SellerOrder sellerOrder);
    /**
     * 取消卖家订单
     * @param orderInfoBuyerId
     */
    void cancelSellOrder(@Param("orderInfoBuyerId") Integer orderInfoBuyerId);

    /**
     * 完成卖家订单
     * @param payOrderSn
     */
    void finishSellOrder(@Param("payOrderSn") String payOrderSn);

    /**
     * 根据支付订单号获取卖家的订单信息
     * @param payOrderSn
     * @return
     */
    List<SellerOrder> getByPayOrderSn(@Param("payOrderSn") String payOrderSn);

    /**
     * 通过报名号或者卖家订单号获取卖家订单详情
     * @param sellerOrderSearch
     * @return
     */
    SellerOrderResult getDetailBySn(@Param("data") SellerOrderSearch sellerOrderSearch);

    /**
     * 后台—修改卖家订单
     * @param order
     */
    void updateSeller(@Param("data") SellerOrder order);

    /**
     * 查询各订单状态的订单数
     * @param
     */
    List<Map<String,Object>> getOrderStatusCount(@Param("data") SellerOrderSearch data);
}
