package com.yuanrong.admin.rpc.api.order;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.order.OrderInfoOffer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.bean.order.SnapshotYrProduction;
import com.yuanrong.admin.result.OrderInfoBuyerDetailResult;
import com.yuanrong.admin.result.OrderInfoSellerResult;
import com.yuanrong.admin.result.SellerOrderResult;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.seach.OrderManagementSearch;
import com.yuanrong.admin.seach.OrderOfferParam;
import com.yuanrong.admin.seach.OrderPriceParamSearch;
import com.yuanrong.admin.seach.SellerOrderSearch;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.ResultTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 卖家订单的services接口
 * Created MDA
 */
public interface OrderInfoSellerServicesI extends BaseServicesI<OrderInfoSeller> {
    /**
     * 卖家中心已购买作品详情
     * @param orderInfoSellerId
     * @return
     */
    Map<String,Object> getSellerProductInfo(Integer orderInfoSellerId);

    /**
     * 后台—买家订单详情（作品购买）
     * @param orderInfoBuyerId
     * @param baseModel
     * @return
     */
    PageInfo<OrderInfoBuyerDetailResult> orderInfoBuyerDetailList(Integer orderInfoBuyerId, BaseModel baseModel);

    /**
     * 卖家中心已购买作品详情
     * @param orderManagementSearch
     * @return
     */
    PageInfo getPendingResponseList(OrderManagementSearch orderManagementSearch , BaseModel baseModel);

    /**
     *@author songwq
     *@param
     *@date 2018/7/9
     *@descriptionr 根据需求id查询需求详情
    */
    Demand getDemandInfoByDemandSn(String orderInfoSellerId , BaseModel baseModel);

    /**
     *@author songwq
     *@param
     *@date 2018/7/10
     *@description 订单管理---待响应--应约
     */
    void insertAcceptConvention(OrderOfferParam orderOfferParam , BaseModel baseModel);

    /**
     *@author songwq
     *@param
     *@date 2018/7/10
     *@description 订单管理---待响应--拒约
     */
    void updateRefuseConvention(OrderOfferParam orderOfferParam , BaseModel baseModel);

    /**
     *@author songwq
     *@param
     *@date 2018/7/10
     *@description 订单管理--待确认使用--确认使用查询
     */
    OrderInfoSeller getAccountInfo(Integer OrderInfoSellerId , BaseModel baseModel);
    /**
     *@author songwq
     *@param
     *@date 2018/7/10
     *@description 订单管理--待确认使用--确认使用提交
     */
    void updateConfirmUse(OrderOfferParam orderOfferParam);


    /**
     *@author songwq
     *@param
     *@date 2018/7/10
     *@description 订单管理--待确认使用--拒绝使用
     */
    void updateRefuseUse(OrderOfferParam orderOfferParam, BaseModel baseModel);

    /**
     *@author songwq
     *@param
     *@date 2018/7/12
     *@description 订单管理--待执行-修改执行数据查询
     */
    OrderInfoSeller getExecuteInfo(OrderInfoSeller orderInfoSeller, BaseModel baseModel);


    /**
     *@author songwq
     *@param
     *@date 2018/7/12
     *@description 订单管理--待执行-提交执行
     */
    void updateCommitExecute(OrderOfferParam orderOfferParam, BaseModel baseModel);

    /**
     *@author songwq
     *@param
     *@date 2018/7/13
     *@description 订单管理--待确认执行-确认结果
     */
    void updateConfirmResult(OrderOfferParam orderOfferParam, BaseModel baseModel);
    /**
     *@author songwq
     *@param
     *@date 2018/7/13
     *@description 拒绝原因查询
     */
    List<DictInfo> getRefuseReason(BaseModel baseModel);

    /**
     * 通过订单号和注册用户查询卖家订单
     * @param orderSn
     * @param registerUserInfoId
     * @return
     */
    OrderInfoSeller getByOrderSnAndRegisterUserInfoId(String orderSn , Integer registerUserInfoId);

    /**
     * 后台—修改订单状态—待响应应约或者拒约、拒绝使用、执行终止状态修改
     * @param orderInfo
     */
    void updateOrderStatus(OrderOfferParam orderInfo);

    /**
     * 查询各订单状态的订单数
     * @param
     */
    List<Map<String,Object>> getOrderStatusCount(OrderManagementSearch data);

    /**
     * 删除卖家订单—假删
     * @param orderInfoSellerId
     */
    void deleteSellerOrder(Integer orderInfoSellerId);

    /**
     * 多个作品报名
     * @param list
     */
    void saveMultProduct(List<OrderInfoSeller> list);

    /**
     * @author tangz
     *@description  内容定制报名
     * @param data
     */
    int saveContent(OrderInfoSeller data);

    /**
     * @author tangz
     *@description  内容定制报名,将报价项保存到orderInfoOffer
     * @param data
     */
    void saveContentOffer( OrderInfoSeller data,List<OrderInfoOffer> list);

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
    OrderInfoSeller getByOrderSn(String orderSn);
    /**
     * 根据订单查询数据
     * @param orderSn
     * @return
     */
    OrderInfoSeller getBySellerOrderSn(String orderSn);

    /**
     * 后台—报名记录修改价格（原创征稿）
     * @param orderSignId
     * @param orderPrice
     * @param userName
     */
    void updateProPriceInfo(Integer orderSignId, BigDecimal orderPrice,String userName);

    /**
     * <获取报名列表通过需求demandSn
     * @param demandSn
     * @return
     */
    List<OrderInfoSeller> getReferIdByDemandSn(String demandSn,Integer registeredUserInfoID);
    /**
    * 更新实体
    * @author      ShiLinghuai
    * @param
    * @return      
    * @exception   
    * @date        2018/10/16 17:11
    */
    void updateEntity(OrderInfoSeller orderInfoSeller);

    /**
     * 通过报名用户或者需求用户，报名需求号来查询报名单
     * @param data
     * @return
     */
    OrderInfoSeller getByParam(  OrderInfoSeller data);

    /**
     * 通过报名号或者卖家订单号获取卖家订单详情 --- 已确认使用
     * @param sellerOrderSearch
     * @return
     */
    SellerOrderResult getDetailBySn( SellerOrderSearch sellerOrderSearch);

    /**
     * 未确认使用 -- 获取明细信息
     * @return
     */
    OrderInfoSellerResult getDetailUnConfirmBySn(String orderSn , Integer registerUserInfoId);
}
