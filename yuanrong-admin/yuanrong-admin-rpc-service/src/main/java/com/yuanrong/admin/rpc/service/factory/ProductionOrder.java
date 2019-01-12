package com.yuanrong.admin.rpc.service.factory;

import cn.hutool.core.date.DateUtil;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.order.*;
import com.yuanrong.admin.rpc.exception.YRParamterException;
import com.yuanrong.admin.rpc.exception.YuanRongException;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.SystemParam;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 作品订单类生成 -- 用户
 * Created by zhonghang on 2018/09/14.
 */
public class ProductionOrder extends OrderModel{
    public OrderInfoBuyer createOrder() {
        if(data == null || !(data instanceof YRProduction)){
            throw new YuanRongException("作品订单类型错误");
        }
        YRProduction production = (YRProduction) data;

        //未发布作品
        List<Integer> unPublishProduct = new ArrayList<Integer>();
        //验证作品是否有失效的作品
        if(production.getYrProductionStatus().getIndex() != EnumYRProductionStatus.上架.getIndex()){
            throw new YRParamterException("购物车部分商品下架或售罄，请刷新页面重新提交订单");
        }
        if(production.getPublishStatus().getIndex() == EnumPublishStatus.未公开.getIndex()){
            unPublishProduct.add(production.getRecId());
        }

        //保存订单
        OrderInfoBuyer theOrderInfoBuyer = new OrderInfoBuyer();
        theOrderInfoBuyer.setRegisteredUserInfoId(registerUserInfoId);
        theOrderInfoBuyer.setPayStatus(EnumPayStatus.待支付);
        theOrderInfoBuyer.setOrderSn(orderSnFactoryServicesI.createBuyerOrderSn());
        theOrderInfoBuyer.setOrderStatus(EnumOrderBuyerStatus.待付款);
        theOrderInfoBuyer.setCreatedTime(new Date());
        theOrderInfoBuyer.setAmountCollected(BigDecimal.ZERO);
        theOrderInfoBuyer.setSourceId(EnumOrderSource.前台创建.getIndex());
        theOrderInfoBuyer.setCreateUser("买家");
        Date payInvalidTime = DateUtils.addMinutes(theOrderInfoBuyer.getCreatedTime() , Integer.parseInt(configurationDaoI.getbyKey(SystemParam.BUYER_ORDER_PAY_TEIM)));
        theOrderInfoBuyer.setPayInvalidTime(DateUtil.formatDateTime(payInvalidTime));
        theOrderInfoBuyer.setEnumOrderSellerType(EnumOrderSellerType.作品订单);
        //发票费
        BigDecimal invoiceFeePercent = new BigDecimal(configurationDaoI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER)).divide(new BigDecimal("100"));
        BigDecimal invoiceFee = production.getSellPrice().multiply(invoiceFeePercent).setScale(2,BigDecimal.ROUND_UP);
        theOrderInfoBuyer.setTotalMoney(production.getSellPrice().add(invoiceFee));
        theOrderInfoBuyer.setReceivableMoney(theOrderInfoBuyer.getTotalMoney());

        //买家服务费率
        BigDecimal serviceFeePercent = new BigDecimal(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_BUYER_PERCENT));
        theOrderInfoBuyer.setBuyerServiceRate(serviceFeePercent);
        theOrderInfoBuyer.setInvoiceRate(new BigDecimal(configurationDaoI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER)));
        theOrderInfoBuyer.setSaleUserId(Integer.parseInt(configurationDaoI.getbyKey(SystemParam.SALE_ID)));
        orderInfoBuyerDaoI.save(theOrderInfoBuyer);

        //保存附加费用
        //发票费
        if(invoiceFee.compareTo(BigDecimal.ZERO) > 0){
            OrderCostInfo invocesCost = new OrderCostInfo();
            invocesCost.setCostType(EnumCostType.作品买家订单);
            invocesCost.setMoney(invoiceFee);
            invocesCost.setCostId(dictInfoDaoI.getDictInfoByTypeAndName(EnumDictInfoType.商品费用明细.getIndex(),"发票费").getId());
            invocesCost.setOrderInfoId(theOrderInfoBuyer.getOrderInfoBuyerId());
            orderCostInfoDaoI.save(invocesCost);
        }

        //存储订单明细
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setEnumProductType(EnumProductType.作品);
        orderDetail.setPrice(production.getSellPrice());
        orderDetail.setNum(1);
        orderDetail.setOrderInfoBuyerId(theOrderInfoBuyer.getOrderInfoBuyerId());
        orderDetail.setReferId(production.getRecId());
        orderDetail.setProduction(production.getTitle());
        orderDetail.setBasePrice(production.getProductQuotedPrice());
        orderDetailDaoI.save(orderDetail);

        //存储快照
        SnapshotYrProduction snapshotYrProduction = new SnapshotYrProduction();
        snapshotYrProduction.setOrderDetailId(orderDetail.getOrderDetailId());
        snapshotYrProduction.setYrProductionId(production.getRecId());
        snapshotYrProductionDaoI.save(snapshotYrProduction);

        //存储卖家订单
        SellerOrder sellerOrder = new SellerOrder();
        sellerOrder.setEnumSellerOrderStatus(EnumSellerOrderStatus.待买家付款);
        sellerOrder.setEnumOrderSellerType(EnumOrderSellerType.作品订单);
        sellerOrder.setNum(1);
        sellerOrder.setOrderDetailId(orderDetail.getOrderDetailId());
        sellerOrder.setOrderSn(theOrderInfoBuyer.getOrderSn());
        sellerOrder.setPrice(production.getProductQuotedPrice());
        sellerOrder.setProduction(production.getTitle());
        sellerOrder.setChannel(EnumChannel.前台创建);
        //存储卖家服务费
        BigDecimal sellerServicesFeePercent = new BigDecimal(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_SELLER_PERCENT)).divide(new BigDecimal("100"));
        BigDecimal sellerServicesFee = sellerServicesFeePercent.multiply(production.getProductQuotedPrice());
        sellerOrder.setSellerServiceRate(new BigDecimal(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_SELLER_PERCENT)));
        //卖家应付金额
        sellerOrder.setPayable(production.getProductQuotedPrice().subtract(sellerServicesFee));
        sellerOrder.setRegisteredUserInfoId(production.getRegisteredUserInfoId());

        sellerOrder.setMediaUserId(Integer.parseInt(configurationDaoI.getbyKey(SystemParam.MEDIA_ID)));

        sellerOrderDaoI.save(sellerOrder);
        //添加定时任务
        quartzManagerI.addTimeoutOrderInfoJobs(theOrderInfoBuyer);

        //如果为未发布作品，则设置为售罄
        if(CollectionUtil.size(unPublishProduct) > 0){
            yRProductionDaoI.unPublishSellOut(unPublishProduct);
        }

        return theOrderInfoBuyer;
    }
}
