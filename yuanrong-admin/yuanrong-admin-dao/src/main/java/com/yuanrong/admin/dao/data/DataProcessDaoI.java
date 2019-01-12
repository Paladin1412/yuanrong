package com.yuanrong.admin.dao.data;

import com.sun.imageio.plugins.common.I18N;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.fiance.UserBalanceDetails;
import com.yuanrong.admin.bean.order.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/10/22.
 */
@Repository
public interface DataProcessDaoI {
    /**
     * 获取待处理的卖家订单
     * @return
     */
    List<Map> getOrderInfoseller ();

    /**
     * 保存订单明细
     * @param map
     */
    void saveOrderDetail(@Param("data") Map map);

    /**
     * 通过卖家订单号，搜索作品快照
     * @param map
     * @return
     */
    Map getSnapshotYrProductionByOrderInfoSellerId(@Param("data") Map map);

    /**
     * 订单号
     * @param snpYrproductionId
     * @param orderDetailId
     */
    void updateSnpYrProductionOrderDetailId(@Param("snpYrproductionId") int snpYrproductionId ,@Param("orderDetailId") int orderDetailId);

    OrderInfoSeller getByOrderSellerId(@Param("orderInfoSellerId") Integer orderInfoSellerId);

    /**
     * 修改快照和订单明细关系——营销账号
     * @param orderDetailId
     * @param snapshotAccountId
     */
    void updateSnapshotAccount(@Param("orderDetailId") Integer orderDetailId, @Param("snapshotAccountId") Integer snapshotAccountId);

    /**
     * 通过报名Id获取快照
     * @param orderInfoSellerId
     * @return
     */
    SnapshotYrAuthor getByOrderInfoSellerId(@Param("orderInfoSellerId")Integer orderInfoSellerId);

    /**
     * 修改快照和订单明细关系——定制内容
     * @param orderDetailId
     * @param snapshotYrAuthorId
     */
    void updateSnapshotYrAuthor(@Param("orderDetailId") Integer orderDetailId,@Param("snapshotYrAuthorId") Integer snapshotYrAuthorId);

    /**
     * 通过用户ID、需求ID、商品ID获取报名信息
     * @param demandId
     * @param referId
     * @param registeredUserInfoId
     * @return
     */
    OrderInfoSeller getOrderInfoSeller(@Param("demandId") Integer demandId, @Param("referId") Integer referId,
                                       @Param("registeredUserInfoId") Integer registeredUserInfoId,@Param("orderTypeValue") Integer orderTypeValue);

    /**
     * 新增买家订单
     * @param data
     */
    void saveOrderBuyer(@Param("data") OrderInfoBuyer data);

    /**
     * 新增订单明细
     * @param data
     */
    void saveDetail(@Param("data") OrderDetail data);

    /**
     * 新增卖家订单
     * @param data
     */
    void saveSellerOrder(@Param("data") SellerOrder data);

    /**
     * 订单完成修改对应的卖家用户余额
     * @param userBalanceDetails
     */
    void updateUserBalanceDetails(@Param("data") UserBalanceDetails userBalanceDetails);

    UserBalanceDetails getUserBalanceDetails(@Param("orderInfoSellerId") Integer orderInfoSellerId);

    /**
     * 修改金额明细—OrderCostInfo
     * @param orderCostInfoId
     * @param orderInfoBuyerId
     * @param costTypeIndex
     */
    void updateOrderCostInfo(@Param("orderCostInfoId") Integer orderCostInfoId, @Param("orderInfoBuyerId") Integer orderInfoBuyerId,@Param("costTypeIndex") Integer costTypeIndex);
}
