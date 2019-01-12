package com.yuanrong.admin.rpc.service.impl.pay;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.fiance.UserBalanceDetails;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.bean.order.SellerOrder;
import com.yuanrong.admin.bean.pay.PayWater;
import com.yuanrong.admin.rpc.api.pay.PayWaterServicesI;
import com.yuanrong.admin.rpc.exception.WxPayException;
import com.yuanrong.admin.rpc.exception.YRParamterException;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.SystemParam;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * 支付流水的services实现类
 * Created MDA
 */
@Service
public class PayWaterServicesImpl extends BaseServicesAbstract<PayWater> implements PayWaterServicesI {
    @Override
    public PayWater getById(Integer id) {
        return payWaterDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        payWaterDaoI.deleteById(id);
    }
    @Override
    public void save(PayWater object) {
        payWaterDaoI.save(object);
    }
    @Override
    public List<PayWater> getAll() {
        return payWaterDaoI.getAll();
    }
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "payWaterId desc");
        List<PayWater> result = payWaterDaoI.list(data);
        return new PageInfo(result);
    }

    @Override
    public void savePayWater(WxPayOrderNotifyResult result) {
        //防止微信重复回调  --  查找是否有该订单的支付流水记录，如果有，则说明已经处理了。
        if(payWaterDaoI.getByOutTradeNo(result.getOutTradeNo()) != null){
            throw new WxPayException("200" ,"微信回调，重复回调。订单已支付,订单号："+result.getOutTradeNo());
        }
        //搜索订单是否存在
        List<OrderInfoBuyer> orderInfoBuyers =  orderInfoBuyerDaoI.getByPayOrderSn(result.getOutTradeNo());
        if(CollectionUtil.size(orderInfoBuyers) == 0){
            throw new WxPayException("500" , "微信回调，订单号不存在");
        }

        BigDecimal orderTotleMoney = BigDecimal.ZERO;
        for (OrderInfoBuyer orderInfoBuyer : orderInfoBuyers){
            orderTotleMoney = orderTotleMoney.add(orderInfoBuyer.getReceivableMoney());
        }
        //验证金额是否一致
        BigDecimal orderMoney = new BigDecimal(result.getTotalFee()).divide(new BigDecimal(100));
        if(orderTotleMoney.compareTo(orderMoney) != 0){
            throw new WxPayException("500" ,"微信回调，金额不合法。订单号："+result.getOutTradeNo() + "；微信回调金额：" + orderMoney + "；订单应收金额：" + orderMoney);
        }

        //设置买家订单已支付
        orderInfoBuyerDaoI.setOrderAsPaid(result.getOutTradeNo() , EnumPayType.微信);

        //保存流水信息
        PayWater payWater = new PayWater();
        payWater.setBankType(result.getBankType());
        payWater.setIsSubscribe(result.getIsSubscribe());
        payWater.setMchId(result.getMchId());
        payWater.setOpenid(result.getOpenid());
        payWater.setOutTradeNo(result.getOutTradeNo());
        payWater.setPayType(EnumPayType.微信);
        payWater.setRegisteredUserInfoId(orderInfoBuyers.get(0).getRegisteredUserInfoId());
        payWater.setTimeEnd(result.getTimeEnd());
        payWater.setTotalFee(orderMoney);
        payWater.setTransactionId(result.getTransactionId());
        payWater.setTradeType(result.getTradeType());
        payWaterDaoI.save(payWater);

        //设置卖家订单已完成
        sellerOrderDaoI.finishSellOrder(result.getOutTradeNo());
        //给卖家增加余额
        List<SellerOrder> sellerOrders = sellerOrderDaoI.getByPayOrderSn(result.getOutTradeNo());
        if(CollectionUtil.size(sellerOrders) > 0){
            for (SellerOrder sellerOrder : sellerOrders) {
                if(sellerOrder.getEnumOrderSellerType().getIndex() == EnumOrderSellerType.定制内容.getIndex()
                   || sellerOrder.getEnumOrderSellerType().getIndex() == EnumOrderSellerType.营销分发.getIndex()){
                    continue;
                }
                //增加卖家余额流水
                UserBalanceDetails userBalanceDetails = new UserBalanceDetails();
                userBalanceDetails.setReferType(EnumReferType.作品收入);
                userBalanceDetails.setTradeType(EnumTradeType.收入);
                userBalanceDetails.setBusinessTime(DateUtil.getNowDateTime());
                userBalanceDetails.setReferId(sellerOrder.getSellerOrderId());
                userBalanceDetails.setRegisteredUserInfoId(sellerOrder.getRegisteredUserInfoId());
                if(sellerOrder.getEnumOrderSellerType().getIndex() == EnumOrderSellerType.营销分发.getIndex()){
                    userBalanceDetails.setRemark("营销分发收入-"+sellerOrder.getProduction());
                }else if(sellerOrder.getEnumOrderSellerType().getIndex() == EnumOrderSellerType.定制内容.getIndex()){
                    userBalanceDetails.setRemark("定制内容收入-"+sellerOrder.getProduction());
                }else if(sellerOrder.getEnumOrderSellerType().getIndex() == EnumOrderSellerType.原创征稿.getIndex()){
                    userBalanceDetails.setRemark("原创征稿收入-"+sellerOrder.getProduction());
                }else if(sellerOrder.getEnumOrderSellerType().getIndex() == EnumOrderSellerType.作品订单.getIndex()){
                    userBalanceDetails.setRemark("作品收入-"+sellerOrder.getProduction());
                }
                //如果下单前没有算过，则现在计算
                userBalanceDetails.setMoney(sellerOrder.getPayable());
                userBalanceDetailsDaoI.save(userBalanceDetails);
            }
        }

    }

    @Override
    public PayWater getByOutTradeNo(String outTradeNo) {
        return payWaterDaoI.getByOutTradeNo(outTradeNo);
    }
}
