package com.yuanrong.admin.dao.order;
import com.yuanrong.admin.Enum.EnumPayType;
import com.yuanrong.admin.bean.cart.ShoppingCart;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.bean.order.SnapshotYrProduction;
import com.yuanrong.admin.dao.BaseDaoI;
import com.yuanrong.admin.result.OrderInfoBuyerListResult;
import com.yuanrong.admin.seach.OrderInfoBuyerListSearch;
import com.yuanrong.admin.seach.OrderInfoBuyerSearch;
import com.yuanrong.admin.seach.ShoppingCartSearch;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 买家订单的dao
 * Created MDA
 */
@Repository
public interface OrderInfoBuyerDaoI extends BaseDaoI<OrderInfoBuyer> {
    /**
     *@author songwq
     *@param
     *@data 2018/7/3
     *@description 查询所有订单
     */
    List<OrderInfoBuyer> getBuyerList(@Param("data") Object data) ;

    /**
     *@author songwq
     *@param
     *@data 2018/7/3
     *@description 根据订单号获取作品
     */
    List<T> getSellerProductById(Integer orderInfoBuyerId ) ;

    /**
     * 后台—作品购买列表
     * @param data
     * @return
     */
    List<OrderInfoBuyerListResult> buyOrderList(@Param("data") OrderInfoBuyerListSearch data);

    /**
     * 后台—获取作品订单信息
     * @param orderInfoBuyerId
     * @return
     */
    OrderInfoBuyer findById(@Param("orderInfoBuyerId") Integer orderInfoBuyerId);

    /**
     * 通过注册用户，购物车类别和购物车ID搜索购物车
     * @param data
     * @return
     */
    List<ShoppingCart> getByIdsAndUserIdAndCartType(@Param("data")ShoppingCartSearch data);

    /**
     * 通过订单号获取卖家订单
     * @param orderSn
     * @return
     */
    OrderInfoBuyer getByOrderSn(@Param("orderSn")String orderSn);

    /**
     * 修改订单
     * @param data
     */
    void update(@Param("data") OrderInfoBuyer data);

    /**
     * 删除买家订单—假删
     * @param orderInfoBuyerId
     */
    void deleteBuyOrder(@Param("orderInfoBuyerId") Integer orderInfoBuyerId);
    /**
    * 获取买家订单信息
    * @author      ShiLinghuai
    * @param
    * @return      
    * @exception   
    * @date        2018/9/14 17:16
    */
    List<OrderInfoBuyer> getByEntity(Demand demand);

    /**
     * 根据订单号查询订单
     * @param orderSns
     * @return
     */
    List<OrderInfoBuyer> getByOrderSns(@Param("userId") Integer userId ,@Param("orderSns") String[] orderSns);

    /**
     * 记录合并支付的订单信息
     * @param orderInfoBuyerIds
     * @param payOrderSn
     */
    void saveMergePayOrder(@Param("orderInfoBuyerIds") Integer[] orderInfoBuyerIds ,@Param("payOrderSn") String payOrderSn);

    /**
     * 通过支付号，查询订单信息
     * @param payOrderSn
     * @return
     */
    List<OrderInfoBuyer> getByPayOrderSn(@Param("payOrderSn") String payOrderSn);

    /**
     * 通过合并支付订单号，设置买家订单为已支付
     * @param payOrderSn
     * @param payType
     */
    void setOrderAsPaid(@Param("payOrderSn")String payOrderSn ,@Param("payType") EnumPayType payType);

    /**
     * 后台—买家订单执行终止
     * @param orderInfo
     */
    void updateBuyerOrder(@Param("data") OrderInfoBuyer orderInfo);

    /**
     *
     * @param orderInfoSellerId
     * @return
     */
    List<OrderInfoBuyer> getByOrderSellerId(@Param("orderInfoSellerId") Integer orderInfoSellerId);

    /**
     * 修改买家、卖家订单状态
     * @param infoBuyer
     */
    void updateBuyerAndSellerStatus(@Param("data") OrderInfoBuyer infoBuyer);

    /**
     * 后台—修改订单价格查询
     * @param orderBuyerId
     * @return
     */
    OrderInfoBuyer findOrderPrice(@Param("orderBuyerId") Integer orderBuyerId);
    /**
     * 后台—根据卖家订单id获取卖家订单id
     * @param sellerOrderId
     * @return
     */
    Integer getBuyerIdBySellerId(@Param("sellerOrderId") Integer sellerOrderId);
}
