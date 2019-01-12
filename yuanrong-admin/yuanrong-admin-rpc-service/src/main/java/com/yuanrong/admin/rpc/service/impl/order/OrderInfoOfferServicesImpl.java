package com.yuanrong.admin.rpc.service.impl.order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumOrderSellerStatus;
import com.yuanrong.admin.bean.order.OrderCostInfo;
import com.yuanrong.admin.bean.order.OrderInfoOffer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.bean.order.SellerOrder;
import com.yuanrong.admin.rpc.api.order.OrderInfoOfferServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.seach.OrderOfferParam;
import com.yuanrong.admin.seach.OrderPriceParamSearch;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
/**
 * 卖家订单报价的services实现类
 * Created MDA
 */
@Service
public class OrderInfoOfferServicesImpl extends BaseServicesAbstract<OrderInfoOffer> implements OrderInfoOfferServicesI {
    @Override
    public OrderInfoOffer getById(Integer id) {
        return orderInfoOfferDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        orderInfoOfferDaoI.deleteById(id);
    }
    @Override
    public void save(OrderInfoOffer object) {
        orderInfoOfferDaoI.save(object);
    }
    @Override
    public List<OrderInfoOffer> getAll() {
        return orderInfoOfferDaoI.getAll();
    }
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "orderInfoOfferId desc");
        List<OrderInfoOffer> result = orderInfoOfferDaoI.list(data);
        return new PageInfo(result);
    }


    /**
     *@author songwq
     *@param
     *@date 2018/8/23
     *@description 修改定制创作和营销分发应约价、报价、卖家服务费率
     */
    @Override
    public void updateSignUp(OrderPriceParamSearch orderPriceParamSearch,OrderInfoSeller orderInfoSeller) {
        BigDecimal buyerServiceRate = orderInfoSeller.getBuyerServiceRate();
        Integer[] offerIds = orderPriceParamSearch.getOfferIds();
        BigDecimal[] prices = orderPriceParamSearch.getPrices();
        BigDecimal[] sellerPrices = orderPriceParamSearch.getSellerPrices();
        BigDecimal[] executePrices =new BigDecimal[prices.length];
        //修改报名表OrderInfoSeller的相关数据
        if(orderPriceParamSearch.getOrderStatus()==EnumOrderSellerStatus.待买家确认.getIndex()){
            //修改orderInfoOffer应约价、报价，判断应约价是否被修改，若已被修改，则修改买家金额
            for(int i=0;i<offerIds.length;i++){
                OrderInfoOffer offer = orderInfoOfferDaoI.getById(offerIds[i]);
                if(offer.getPrice().compareTo(prices[i])!=0){
                    if(buyerServiceRate==null){
                        executePrices[i] = prices[i];//买家服务费率为空，默认将费率当做0
                    }else{
                        executePrices[i] = prices[i].multiply(new BigDecimal(1).add(buyerServiceRate));//重新计算买家金额
                    }
                }else{
                    executePrices[i] = offer.getExecutePrice();
                }
                offer.setPrice(prices[i]);
                offer.setExecutePrice(executePrices[i]);
                offer.setSellerPrice(sellerPrices[i]);

                orderInfoOfferDaoI.updateOrderInfoOffer(offer);
            }
            //修改orderInfoSeller的卖家服务费
            if(orderInfoSeller.getSellerServiceRate()==null||orderPriceParamSearch.getSellerServiceRate().compareTo(orderInfoSeller.getSellerServiceRate())!=0){
                orderInfoSeller.setSellerServiceRate(orderPriceParamSearch.getSellerServiceRate());
                orderInfoSellerDaoI.updateOrderSeller(orderInfoSeller);
            }
        }
    }
    /**
     *@author songwq
     *@param
     *@date 2018/8/23
     *@description 修改定制创作和营销分发应约价、报价、卖家服务费率
     */
    @Override
    public void updateOrderInfo(OrderPriceParamSearch orderPriceParamSearch,OrderInfoSeller orderInfoSeller,SellerOrder sellerOrder) {
        BigDecimal sellerPrice = orderPriceParamSearch.getSellerPrice();
        BigDecimal payAble = orderPriceParamSearch.getPayAble();

        //修改orderInfoSeller的卖家服务费
        if(orderInfoSeller.getSellerServiceRate()==null || orderPriceParamSearch.getSellerServiceRate().compareTo(orderInfoSeller.getSellerServiceRate())!=0){
            orderInfoSeller.setSellerServiceRate(orderPriceParamSearch.getSellerServiceRate());
            orderInfoSeller.setSellerPrice(sellerPrice);
            orderInfoSellerDaoI.updateOrderSeller(orderInfoSeller);
        }
        //修改卖家订单表
        sellerOrder.setPrice(sellerPrice);//报价
        sellerOrder.setPayable(payAble);//卖家收入
        sellerOrder.setSellerServiceRate(orderPriceParamSearch.getSellerServiceRate());//卖家佣金
        sellerOrderDaoI.updateSellerOrder(sellerOrder);
    }

    /**
     * 确认使用前修改价格
     * @param orderOffer
     * @param userName
     */
    @Override
    public void updateOrderOffer(OrderInfoOffer orderOffer,String userName) {
        //修改价格项—Offer
        OrderInfoOffer offer = orderInfoOfferDaoI.getById(orderOffer.getOrderInfoOfferId());
        BigDecimal serviceRate = orderOffer.getExecutePrice().subtract(offer.getPrice()).divide(offer.getPrice(),3,BigDecimal.ROUND_UP);
        BigDecimal service = serviceRate.multiply(new BigDecimal("100"));
        offer.setExecutePrice(orderOffer.getExecutePrice());
        orderInfoOfferDaoI.updatePricesById(offer);
        //修改买家服务费率—orderSeller
        OrderInfoSeller orderInfoSeller = new OrderInfoSeller();
        orderInfoSeller.setBuyerServiceRate(service);
        orderInfoSeller.setOrderInfoSellerId(offer.getOrderInfoSeller());
        orderInfoSellerDaoI.updateOrderSeller(orderInfoSeller);
        systemLogDaoI.log(OrderInfoSeller.class.getName(),offer.getOrderInfoSeller() ,"确认使用前修改买家订单金额" ,userName);
    }
    /**
     * 根据报名Id获取报价项
     * @param orderInfoSellerId
     * @return
     */
    @Override
    public List<OrderInfoOffer> getByOrderInfoSellerId(Integer orderInfoSellerId) {
        return orderInfoOfferDaoI.getByOrderInfoSellerId(orderInfoSellerId);
    }
}
