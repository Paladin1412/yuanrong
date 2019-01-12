package com.yuanrong.admin.dao.order;

import com.yuanrong.admin.Enum.EnumOrderSellerType;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.order.OrderCostInfo;
import com.yuanrong.admin.bean.order.OrderInfoOffer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.dao.BaseDaoI;
import com.yuanrong.admin.result.OrderInfoBuyerDetailResult;
import com.yuanrong.admin.result.OrderInfoSellerResult;
import com.yuanrong.admin.result.SellerOrderResult;
import com.yuanrong.admin.seach.OrderManagementSearch;
import com.yuanrong.admin.seach.OrderOfferParam;
import com.yuanrong.admin.seach.SellerOrderSearch;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 卖家订单的dao
 * Created MDA
 */
@Repository
public interface OrderInfoSellerDaoI extends BaseDaoI<OrderInfoSeller> {

    /**
     *@author songwq
     *@param orderInfoSellerId
     *@data 2018/6/29
     *@description 买家中心已购买作品详情
     */
    Map<String,Object> getSellerProductInfo(Integer orderInfoSellerId);

    /**
     *@author songwq
     *@param
     *@data 2018/7/3
     *@description 根据买家的订单号获取作品
     */
    List<OrderInfoSeller> getByBuyerId(String orderInfoSellerId);

    /**
     * 后台—买家订单详情（作品购买）
     * @param orderInfoBuyerId
     * @return
     */
    List<OrderInfoBuyerDetailResult> orderInfoBuyerDetailList(@Param("orderInfoBuyerId") Integer orderInfoBuyerId);

    /**
     * 订单管理待响应列表查询
     * @param data
     * @return
     */
    List<OrderInfoSeller> getPendingResponseList(@Param("data") Object data) ;

    /**
    * 根据需求号获取所有的定制内容类型卖家订单
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/7/4 14:56
    */
    List<OrderInfoSeller> getAllOrderSellerByDemand(Demand demand);
    /**
     * 根据需求号获取所有的营销分发类型卖家订单
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/7/4 14:56
     */
    List<OrderInfoSeller> getAllMarketSendOrderByDemand(Demand demand);

    /**
     *@author songwq
     *@param
     *@date 2018/7/9
     *@description 根据需求id查询需求详情
     */
    Demand getDemandInfoByDemandSn(@Param(value="orderInfoSellerId") String orderInfoSellerId);

    /**
     *@author songwq
     *@param
     *@date 2018/7/10
     *@description 订单管理---待响应--应约--增加价格项
     */
    void insertOrderInfoOffer(@Param(value="offerList") List<OrderInfoOffer> offerList);

    /**
     *@author songwq
     *@param
     *@date 2018/7/10
     *@description 订单管理---待响应--应约--修改需求转台  订单管理--待确认使用--确认使用
     */
    void updateOrderStatus(@Param(value="orderOfferParam") OrderOfferParam orderOfferParam );

    /**
     *@author songwq
     *@param
     *@date 2018/7/10
     *@description 订单管理--待确认使用--确认使用查询
     */
    OrderInfoSeller getOrderInfoSeller(Integer orderInfoSellerId );
    List<OrderInfoOffer> getOrderInfoOffer(Integer orderInfoSellerId );


    /**
     *@author songwq
     *@param
     *@date 2018/7/12
     *@description 订单管理--待执行-修改执行数据查询
     */
    OrderInfoSeller getExecuteInfo(@Param(value="orderInfoSeller") OrderInfoSeller orderInfoSeller);
    /**
     *@author songwq
     *@param
     *@date 2018/7/14
     *@description 
    */
    List<OrderCostInfo> getCostInfoList(@Param(value="orderInfoBuyerId") Integer orderInfoBuyerId);

    /**
     * 通过订单号和注册用户查询卖家订单
     * @param orderSn
     * @param registerUserInfoId
     * @return
     */
    OrderInfoSeller getByOrderSnAndRegisterUserInfoId(@Param("orderSn") String orderSn ,@Param("registerUserInfoId") Integer registerUserInfoId);

    /**
     * 查询各订单状态的订单数
     * @param
     */
    List<Map<String,Object>> getOrderStatusCount(@Param("data") OrderManagementSearch data);

    /**
     * 修改卖家订单
     * @param orderInfoSeller
     */
    void updateSellerOrderStatus(@Param("data") OrderInfoSeller orderInfoSeller);

    /**
     * 删除卖家订单—假删
     * @param orderInfoSellerId
     */
    void deleteSellerOrder(@Param("orderInfoSellerId") Integer orderInfoSellerId);

    /**
     * 根据卖家订单和订单类型获取卖家订单
     * @param orderInfoId
     * @param orderSellerType
     * @return
     */
    List<OrderInfoSeller> getByOrderInfoIdAndOrderType(@Param("orderInfoId") int orderInfoId , @Param("orderSellerType") EnumOrderSellerType orderSellerType);

    /**
     * 统计报名数
     * @param map
     * @return
     */
    int cnt_num(Map<String,Object> map);

    /**
     * 根据订单查询数据
     * @param orderSn
     * @return
     */
    OrderInfoSeller getByOrderSn(@Param(value="orderSn")String orderSn);
    /**
     * 根据订单查询数据
     * @param orderSn
     * @return
     */
    OrderInfoSeller getBySellerOrderSn(@Param(value="orderSn")String orderSn);

    /**
     * 修改报名记录
     * @param orderInfoSeller
     */
    void updateOrderSeller(@Param("data") OrderInfoSeller orderInfoSeller);

    /**
     * 获取报名列表通过需求Id
     * @param demandId
     * @return
     */
    List<OrderInfoSeller> getOrderSellerByDemandId(@Param("demandId") Integer demandId);

    //List<OrderInfoSeller> selectPer11(@Param("orderInfoSellerId") Integer orderInfoSellerId);
    /**
     * <获取报名列表通过需求demandSn
     * @param demandSn
     * @return
     */
    List<OrderInfoSeller> getReferIdByDemandSn(@Param("demandSn")String demandSn,@Param("registeredUserInfoID")Integer registeredUserInfoID);

    /**
     * 通过报名用户或者需求用户，报名需求号来查询报名单
     * @param data
     * @return
     */
    OrderInfoSeller getByParam( @Param("data") OrderInfoSeller data);

    /**
     * 通过报名号或者卖家订单号获取卖家订单详情
     * @param sellerOrderSearch
     * @return
     */
    SellerOrderResult getDetailBySn(@Param("data") SellerOrderSearch sellerOrderSearch);

    /**
     * 未确认使用 -- 获取明细信息
     * @param
     * @return
     */
    OrderInfoSellerResult getDetailUnConfirmBySn(@Param("orderSn") String orderSn , @Param("registerUserInfoId") Integer registerUserInfoId);
}
