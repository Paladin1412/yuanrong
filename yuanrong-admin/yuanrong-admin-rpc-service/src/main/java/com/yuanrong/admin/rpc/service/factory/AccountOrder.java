package com.yuanrong.admin.rpc.service.factory;


import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.order.*;
import com.yuanrong.admin.result.PlatformIPAccountResult;
import com.yuanrong.admin.rpc.exception.YuanRongException;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.common.util.SystemParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 账号订单生成
 * Created by zhonghang on 2018/09/14.
 */
public class AccountOrder extends OrderModel {
    public OrderInfoBuyer createOrder() {
        if(!(data instanceof OrderInfoSeller)){
            throw new YuanRongException("作品订单类型错误");
        }
        OrderInfoSeller orderInfoSeller = (OrderInfoSeller) data;
        //保存订单
        OrderInfoBuyer theOrderInfoBuyer = new OrderInfoBuyer();
        theOrderInfoBuyer.setRegisteredUserInfoId(registerUserInfoId);
        theOrderInfoBuyer.setPayStatus(EnumPayStatus.待支付);
        theOrderInfoBuyer.setOrderSn(orderSnFactoryServicesI.createBuyerOrderSn());
        theOrderInfoBuyer.setOrderStatus(EnumOrderBuyerStatus.待卖家执行);
        theOrderInfoBuyer.setCreatedTime(new Date());
        theOrderInfoBuyer.setAmountCollected(BigDecimal.ZERO);
        theOrderInfoBuyer.setSourceId(EnumOrderSource.前台创建.getIndex());
        theOrderInfoBuyer.setCreateUser("买家");
        theOrderInfoBuyer.setEnumOrderSellerType(EnumOrderSellerType.营销分发);
        theOrderInfoBuyer.setRefreId(orderInfoSeller.getOrderInfoSellerId());

        PlatformIPAccount platformIPAccount = platformIPAccountDaoI.getById(orderInfoSeller.getReferId());
        //发票费
        BigDecimal invoiceFeePercent = orderInfoSeller.getInvoiceRate().divide(new BigDecimal("100"));
        BigDecimal invoiceFee = orderInfoSeller.getBuyerOrderPrice().multiply(invoiceFeePercent).setScale(2,BigDecimal.ROUND_UP);

        theOrderInfoBuyer.setTotalMoney(orderInfoSeller.getBuyerOrderPrice().add(invoiceFee));
        theOrderInfoBuyer.setReceivableMoney(theOrderInfoBuyer.getTotalMoney());
        theOrderInfoBuyer.setBuyerServiceRate(orderInfoSeller.getBuyerServiceRate());
        theOrderInfoBuyer.setSaleUserId(orderInfoSeller.getSaleUserId());
        theOrderInfoBuyer.setInvoiceRate(orderInfoSeller.getInvoiceRate());
        orderInfoBuyerDaoI.save(theOrderInfoBuyer);

        //保存附加费用
        //发票费
        OrderCostInfo invocesCost = new OrderCostInfo();
        invocesCost.setCostType(EnumCostType.作品买家订单);
        invocesCost.setMoney(invoiceFee);
        invocesCost.setOrderInfoId(theOrderInfoBuyer.getOrderInfoBuyerId());
        invocesCost.setCostId(dictInfoDaoI.getDictInfoByTypeAndName(EnumDictInfoType.商品费用明细.getIndex(),"发票费").getId());
        orderCostInfoDaoI.save(invocesCost);

        //存储订单明细
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setEnumProductType(EnumProductType.账号);
        orderDetail.setPrice(orderInfoSeller.getBuyerOrderPrice());
        orderDetail.setBasePrice(orderInfoSeller.getReferPrice());
        orderDetail.setNum(1);
        orderDetail.setOrderInfoBuyerId(theOrderInfoBuyer.getOrderInfoBuyerId());
        orderDetail.setReferId(platformIPAccount.getId());
        orderDetail.setProduction(orderInfoSeller.getProduction());
        orderDetailDaoI.save(orderDetail);

        //存储快照
        SnapshotAccount snapshotAccount = new SnapshotAccount();
        snapshotAccount.setOrderDetailId(orderDetail.getOrderDetailId());
        snapshotAccount.setPlatformIPAccountId(platformIPAccount.getId());
        int[] ipAccountIds = {orderInfoSeller.getReferId()};
        List<Map<String,Object>> prices = platformIPAccountPriceDaoI.getGroupPriceByIPAccountIDs(ipAccountIds , 13);
        if(CollectionUtil.size(prices) > 0){
            snapshotAccount.setPriceInfo(PlatformIPAccountResult.getJSONObjectByStringPrice(prices.get(0).get("info").toString()).toJSONString());
        }
        snapshotAccountDaoI.save(snapshotAccount);

        //存储卖家订单
        SellerOrder sellerOrder = new SellerOrder();
        sellerOrder.setEnumSellerOrderStatus(EnumSellerOrderStatus.待卖家执行);
        sellerOrder.setEnumOrderSellerType(EnumOrderSellerType.营销分发);
        sellerOrder.setNum(1);
        sellerOrder.setOrderDetailId(orderDetail.getOrderDetailId());
        sellerOrder.setOrderSn(theOrderInfoBuyer.getOrderSn());
        sellerOrder.setPrice(orderInfoSeller.getSellerPrice());
        sellerOrder.setProduction(orderInfoSeller.getProduction());
        sellerOrder.setSellerServiceRate(orderInfoSeller.getSellerServiceRate());
        //存储卖家服务费
        BigDecimal sellerServicesFeePercent = new BigDecimal(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_SELLER_ACCOUNT)).divide(new BigDecimal("100"));
        BigDecimal sellerServicesFee = sellerServicesFeePercent.multiply(orderInfoSeller.getSellerPrice());
        //卖家应付金额
        sellerOrder.setPayable(orderInfoSeller.getSellerPrice().subtract(sellerServicesFee));
        sellerOrder.setRegisteredUserInfoId(orderInfoSeller.getRegisteredUserInfoId());
        sellerOrder.setMediaUserId(orderInfoSeller.getMediaUserId());
        sellerOrderDaoI.save(sellerOrder);
        return theOrderInfoBuyer;
    }
}
