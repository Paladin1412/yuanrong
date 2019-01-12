package com.yuanrong.admin.rpc.api.order;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.parameter.MergePay;
import com.yuanrong.admin.result.OrderInfoBuyerListResult;
import com.yuanrong.admin.result.ShoppingCartResult;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.seach.OrderInfoBuyerListSearch;
import com.yuanrong.admin.seach.OrderSureSearch;
import com.yuanrong.admin.seach.ShoppingCartSearch;
import com.yuanrong.admin.util.BaseModel;

import java.math.BigDecimal;
import java.util.List;

/**
 * 买家订单的services接口
 * Created MDA
 */
public interface OrderInfoBuyerServicesI extends BaseServicesI<OrderInfoBuyer> {

    /**
     * 后台—作品购买列表
     * @param data
     * @param baseModel
     * @return
     */
    PageInfo<OrderInfoBuyerListResult> buyOrderList(OrderInfoBuyerListSearch data, BaseModel baseModel);

    /**
     * 后台—获取作品订单信息
     * @param orderInfoBuyerId
     * @return
     */
    OrderInfoBuyer findById(Integer orderInfoBuyerId);

    /**
     * 立即购买
     * @param yrProduction
     * @return
     */
    OrderInfoBuyer saveBuyNow(YRProduction yrProduction);

    /**
     * 通过订单号搜索订单
     * @param orderSn
     * @return
     */
    OrderInfoBuyer getByOrderSn(String orderSn);

    /**
     * 处理超时订单
     * @param orderInfoBuyerId 订单ID
     */
    void updateTimeoutOrderInfo(Integer orderInfoBuyerId);

    /**
     * 根据用户Id和购物车ID获取用户购物车信息
     * @param shoppingCartSearch
     * @return
     */
    List<ShoppingCartResult> getProductByShppingCartIdssAndUserId(ShoppingCartSearch shoppingCartSearch);

    /**
     * 删除买家订单—假删
     * @param orderInfoBuyerId
     */
    void deleteBuyOrder(Integer orderInfoBuyerId);
    /**
     * 提交客户作品订单 - 购物车下单
     * @param data
     */
    List<OrderInfoBuyer> savePlaceTheProductOrder(ShoppingCartSearch data);

    /**
     * 需求生成订单
     * @param orderInfoSellers 征稿单
     * @return
     */
    List<OrderInfoBuyer> savePlaceTheDemandOrder(List<OrderInfoSeller> orderInfoSellers);

    OrderInfoBuyer savePlaceTheDemandOrder(OrderInfoSeller orderInfoSeller);

    /**
     * 根据订单号查询订单
     * @param orderSns
     * @return
     */
    List<OrderInfoBuyer> getByOrderSns(Integer userId , String[] orderSns);

    /**
     * 记录合并支付的订单信息
     * @param orderSns
     * @param ip
     */
    MergePay saveMergePayOrder(String[] orderSns , String ip , Integer userId);

    /**
     * 通过支付号，查询订单信息
     * @param payOrderSn
     * @return
     */
    List<OrderInfoBuyer> getByPayOrderSn(String payOrderSn);

    /**
     * 后台—买家订单执行终止
     * @param orderInfo
     */
    void updateBuyerOrder(OrderInfoBuyer orderInfo,String userName);

    /**
     * 后台—作品购买列表
     * 统计列表金额
     * @param data
     * @return
     */
    List<OrderInfoBuyerListResult> buyOrderList(OrderInfoBuyerListSearch data);

    /**
     * 需求—修改订单金额(已生成订单)
     * @param orderBuyerId
     * @param orderPrice
     * @param userName
     */
    void updateOrderPrice(Integer orderBuyerId, BigDecimal orderPrice,String userName);

    /**
     * 后台—修改订单价格查询
     * @param orderBuyerId
     */
    OrderInfoBuyer findOrderPrice(Integer orderBuyerId);

    /**
     * 确认订单
     * @param data
     * @return
     */
    JSONObject sureOrder(OrderSureSearch data);

    /**
     * 后台—确认执行 修改订单状态已完成
     * @param orderBuyerId
     * @param userName
     */
    void updateOrderBuyerStatus(Integer orderBuyerId,String userName);

    /**
     * 订单是营销分发的话——添加交易记录
     * @param infoBuyer
     */
    void saveTradingRecord(OrderInfoBuyer infoBuyer);

    /**
     * 订单完成—添加卖家用户余额
     * @param infoBuyer
     */
    void saveUserBalanceDetails(OrderInfoBuyer infoBuyer);
}
