package com.yuanrong.admin.rpc.service.impl.order;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.cart.ShoppingCart;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.fiance.UserBalanceDetails;
import com.yuanrong.admin.bean.order.*;
import com.yuanrong.admin.bean.trading.TradingRecord;
import com.yuanrong.admin.parameter.MergePay;
import com.yuanrong.admin.result.OrderInfoBuyerListResult;
import com.yuanrong.admin.result.ShoppingCartResult;
import com.yuanrong.admin.rpc.api.order.OrderInfoBuyerServicesI;
import com.yuanrong.admin.rpc.exception.YRParamterException;
import com.yuanrong.admin.rpc.exception.YuanRongException;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.rpc.service.factory.OrderFactory;
import com.yuanrong.admin.rpc.service.factory.OrderModel;
import com.yuanrong.admin.seach.OrderInfoBuyerListSearch;
import com.yuanrong.admin.seach.OrderSureSearch;
import com.yuanrong.admin.seach.ShoppingCartSearch;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.OrderTotalAmountRoundUtil;
import com.yuanrong.common.util.SystemParam;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 买家订单的services实现类
 * Created MDA
 */
@Service
public class OrderInfoBuyerServicesImpl extends BaseServicesAbstract<OrderInfoBuyer> implements OrderInfoBuyerServicesI {
    @Override
    public OrderInfoBuyer getById(Integer id) {
        return orderInfoBuyerDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        orderInfoBuyerDaoI.deleteById(id);
    }
    @Override
    public void save(OrderInfoBuyer object) {
        orderInfoBuyerDaoI.save(object);
    }
    @Override
    public List<OrderInfoBuyer> getAll() {
        return orderInfoBuyerDaoI.getAll();
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "orderInfoBuyerId desc");
        List<OrderInfoBuyer> orderInfoBuyer = orderInfoBuyerDaoI.getBuyerList(data);
        //遍历买家订单号，查询对应的作品
       /* for (int i=0;i<orderInfoBuyer.size();i++) {
            String buyId = orderInfoBuyer.get(i).getOrderInfoBuyerId().toString();
            List<OrderInfoSeller> sellerList = orderInfoSellerDaoI.getByBuyerId(buyId);
            orderInfoBuyer.get(i).setOrderInfoSellerList(sellerList);
        }*/
        return new PageInfo(orderInfoBuyer);
    }

    /**
     * 后台—作品购买列表
     * @param data
     * @param baseModel
     * @return
     */
    @Override
    public PageInfo buyOrderList(OrderInfoBuyerListSearch data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "ob.createdTime desc");
        List<OrderInfoBuyerListResult> orderInfoBuyerList = orderInfoBuyerDaoI.buyOrderList(data);
        return new PageInfo(orderInfoBuyerList);
    }

    /**
     * 后台—获取作品订单信息
     * @param orderInfoBuyerId
     * @return
     */
    @Override
    public OrderInfoBuyer findById(Integer orderInfoBuyerId) {
        return orderInfoBuyerDaoI.findById(orderInfoBuyerId);
    }

    @Override
    public List<OrderInfoBuyer> savePlaceTheProductOrder(ShoppingCartSearch data) {
        //验证购物车内容是否为当前注册用户的
        List<ShoppingCartResult> shoppingCartList = shoppingCartDaoI.getProductByShppingCartIdssAndUserId(data);
        if(shoppingCartList == null || shoppingCartList.size() != data.getShoppingCartIds().length){
            throw new YRParamterException("购物车部分商品无效，请刷新页面重新提交订单");
        }
        List<OrderInfoBuyer> orderInfoBuyers = new ArrayList<>();
        for (ShoppingCartResult shoppingCartResult : shoppingCartList){
            shoppingCartResult.getYrProduction().setOrderType(EnumOrderSellerType.作品订单);
            OrderModel orderModel = OrderFactory.getOrder(EnumOrderSellerType.作品订单);
            orderModel.init(orderSnFactoryServicesI , orderInfoBuyerDaoI ,shoppingCartResult.getYrProduction(),data.getRegisteredUserInfoId(),
                    configurationDaoI, dictInfoDaoI, orderCostInfoDaoI, orderDetailDaoI
                    , snapshotYrProductionDaoI, sellerOrderDaoI, snapshotYrAuthorDaoI, snapshotAccountDaoI,quartzManagerI,yRProductionDaoI
                    ,yRAuthorDaoI , platformIPAccountDaoI,platformIPAccountPriceDaoI);
            OrderInfoBuyer orderInfoBuyer = orderModel.createOrder();
            orderInfoBuyers.add(orderInfoBuyer);
        }
        //清空购物车
        shoppingCartDaoI.batchDeleteByIds(data.getShoppingCartIds() , data.getRegisteredUserInfoId());
        return orderInfoBuyers;
    }

    @Override
    public List<OrderInfoBuyer> savePlaceTheDemandOrder(List<OrderInfoSeller> orderInfoSellers) {
        if(CollectionUtil.size(orderInfoSellers) == 0){
            throw new YuanRongException("报名信息为空，下单失败");
        }
        List<OrderInfoBuyer> result = new ArrayList<>();
        for (OrderInfoSeller orderInfoSeller : orderInfoSellers) {
            result.add(this.savePlaceTheDemandOrder(orderInfoSeller));
        }
        
        return result;
    }

    @Override
    public OrderInfoBuyer savePlaceTheDemandOrder(OrderInfoSeller orderInfoSeller) {
        OrderModel orderModel ;
        if(orderInfoSeller.getOrderType().getIndex() == EnumOrderSellerType.原创征稿.getIndex()){
            orderModel = OrderFactory.getOrder(EnumOrderSellerType.原创征稿);
        } else if(orderInfoSeller.getOrderType().getIndex() == EnumOrderSellerType.定制内容.getIndex()){
            orderModel = OrderFactory.getOrder(EnumOrderSellerType.定制内容);
        }else if(orderInfoSeller.getOrderType().getIndex() == EnumOrderSellerType.营销分发.getIndex()){
            orderModel = OrderFactory.getOrder(EnumOrderSellerType.营销分发);
        }else{
            throw new YuanRongException("报名单状态错误，下单失败");
        }
        //查询需求单
        Demand demand = demandDaoI.getById(orderInfoSeller.getDemandId());
        orderModel.init(orderSnFactoryServicesI , orderInfoBuyerDaoI ,orderInfoSeller,demand.getRegisteredUserInfoId(),
                configurationDaoI, dictInfoDaoI, orderCostInfoDaoI, orderDetailDaoI
                , snapshotYrProductionDaoI, sellerOrderDaoI, snapshotYrAuthorDaoI, snapshotAccountDaoI,quartzManagerI,yRProductionDaoI
                ,yRAuthorDaoI , platformIPAccountDaoI,platformIPAccountPriceDaoI);
        return orderModel.createOrder();
    }

    @Override
    public List<OrderInfoBuyer> getByOrderSns(Integer userId , String[] orderSns) {
        return orderInfoBuyerDaoI.getByOrderSns(userId , orderSns);
    }

    @Override
    public MergePay saveMergePayOrder(String[] orderSns, String ip, Integer userId) {
        if(userId == null){
            userId = getByOrderSn(orderSns[0]).getRegisteredUserInfoId();
        }
        List<OrderInfoBuyer> orderInfoBuyers = this.getByOrderSns(userId , orderSns);
        if(CollectionUtil.size(orderInfoBuyers) != orderSns.length){
            throw new YRParamterException("部分订单不存在！");
        }

        BigDecimal orderMoney = BigDecimal.ZERO;
        Integer[] orderInfoBuyerIds = new Integer[orderInfoBuyers.size()];
        int i = 0;
        for(OrderInfoBuyer orderInfoBuyer : orderInfoBuyers){
            if(orderInfoBuyer.getRegisteredUserInfoId().intValue() != userId.intValue()){
               throw new YRParamterException("部分订单不存在，请刷新重试");
            }

            if(orderInfoBuyer.getPayStatus().getIndex() == EnumPayStatus.已失效.getIndex()){
                throw new YRParamterException("很抱歉，部分订单已被取消，请重新下单后再支付");
            }

            if(orderInfoBuyer.getPayStatus().getIndex() == EnumPayStatus.已支付.getIndex()){
                throw new YRParamterException("部分订单已支付，无需多次支付");
            }
            orderMoney = orderMoney.add(orderInfoBuyer.getReceivableMoney());
            orderInfoBuyerIds[i++] = orderInfoBuyer.getOrderInfoBuyerId();
        }


        String payOrderSn = orderSnFactoryServicesI.createdPayOrderSn();
        //保存合并支付订单
        orderInfoBuyerDaoI.saveMergePayOrder(orderInfoBuyerIds , payOrderSn);

        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
        orderRequest.setSpbillCreateIp(ip);
        orderRequest.setOutTradeNo(payOrderSn);
        orderRequest.setTotalFee(orderMoney.multiply(new BigDecimal("100")).intValue());
        orderRequest.setTradeType("NATIVE");
        orderRequest.setBody("圆融-购买作品");
        orderRequest.setProductId(payOrderSn);

        return new MergePay(orderRequest , orderInfoBuyers,orderMoney);
    }

    @Override
    public List<OrderInfoBuyer> getByPayOrderSn(String payOrderSn) {
        return orderInfoBuyerDaoI.getByPayOrderSn(payOrderSn);
    }

    @Override
    public OrderInfoBuyer saveBuyNow(YRProduction yrProduction) {
        yrProduction.setOrderType(EnumOrderSellerType.作品订单);
        OrderModel orderModel = OrderFactory.getOrder(EnumOrderSellerType.作品订单);
        orderModel.init(orderSnFactoryServicesI , orderInfoBuyerDaoI ,yrProduction,yrProduction.getCurrRegisterUserInfoId(),
                configurationDaoI, dictInfoDaoI, orderCostInfoDaoI, orderDetailDaoI
                , snapshotYrProductionDaoI, sellerOrderDaoI, snapshotYrAuthorDaoI, snapshotAccountDaoI,quartzManagerI,yRProductionDaoI
                ,yRAuthorDaoI , platformIPAccountDaoI,platformIPAccountPriceDaoI);
        OrderInfoBuyer orderInfoBuyer = orderModel.createOrder();
        return orderInfoBuyer;
    }

    @Override
    public OrderInfoBuyer getByOrderSn(String orderSn) {
        return orderInfoBuyerDaoI.getByOrderSn(orderSn);
    }

    @Override
    public void updateTimeoutOrderInfo(Integer orderInfoBuyerId) {
        OrderInfoBuyer orderInfoBuyer = orderInfoBuyerDaoI.getById(orderInfoBuyerId);
        if(orderInfoBuyer != null && orderInfoBuyer.getPayStatus().getIndex() == EnumPayStatus.待支付.getIndex()){
            orderInfoBuyer.setOrderStatus(EnumOrderBuyerStatus.已取消);
            orderInfoBuyer.setPayStatus(EnumPayStatus.已失效);
            orderInfoBuyer.setCancelReason("订单超时未支付，取消订单");
            orderInfoBuyerDaoI.update(orderInfoBuyer);
            //将未发布的作品调整为上架
            yRProductionDaoI.unPublishProductShelf(orderInfoBuyerId);
            //卖家订单状态调整为已取消
            sellerOrderDaoI.cancelSellOrder(orderInfoBuyerId);
            systemLogDaoI.log(OrderInfoBuyer.class.getName() , orderInfoBuyerId, "订单超时未支付，取消订单" , "系统");
        }
    }

    /**
     * 根据用户Id和购物车ID获取用户购物车信息
     * @param shoppingCartSearch
     * @return
     */
    @Override
    public List<ShoppingCartResult> getProductByShppingCartIdssAndUserId(ShoppingCartSearch shoppingCartSearch) {
        return shoppingCartDaoI.getProductByShppingCartIdssAndUserId(shoppingCartSearch);
    }

    /**
     * 删除买家订单—假删
     * @param orderInfoBuyerId
     */
    @Override
    public void deleteBuyOrder(Integer orderInfoBuyerId) {
        orderInfoBuyerDaoI.deleteBuyOrder(orderInfoBuyerId);
    }

    /**
     * 后台—买家订单执行终止
     * @param orderInfo
     */
    @Override
    public void updateBuyerOrder(OrderInfoBuyer orderInfo,String userName) {
        orderInfoBuyerDaoI.updateBuyerOrder(orderInfo);
        SellerOrder sellerOrder = new SellerOrder();
        sellerOrder.setSellerOrderId(orderInfo.getSellerOrder().getSellerOrderId());
        sellerOrder.setOrderStatus(EnumSellerOrderStatus.已取消.getIndex());
        sellerOrder.setCancelReason(orderInfo.getCancelReason());
        sellerOrderDaoI.updateSeller(sellerOrder);
        systemLogDaoI.log(OrderInfoBuyer.class.getName(),orderInfo.getOrderInfoBuyerId() ,"用户执行终止，订单取消",userName);
    }

    /**
     * 后台—作品购买列表
     * @param data
     * @return
     */
    @Override
    public List<OrderInfoBuyerListResult> buyOrderList(OrderInfoBuyerListSearch data) {
        return orderInfoBuyerDaoI.buyOrderList(data);
    }

    /**
     * 需求—修改订单金额(已生成订单)
     * @param orderBuyerId
     * @param orderPrice
     */
    @Override
    public void updateOrderPrice(Integer orderBuyerId, BigDecimal orderPrice,String userName) {
        OrderInfoBuyer infoBuyer = orderInfoBuyerDaoI.getById(orderBuyerId);//买家订单
        OrderInfoSeller infoSeller = orderInfoSellerDaoI.getById(infoBuyer.getRefreId());//报名信息
        List<OrderCostInfo> costInfoList =  orderCostInfoDaoI.getOrderCostInfoList(infoBuyer.getOrderInfoBuyerId());//费用明细
        OrderDetail orderDetail = orderDetailDaoI.getByOrderBuyerId(orderBuyerId);//订单明细
        BigDecimal oldOrderPrice = orderDetail.getPrice();
        //计算服务费率
        BigDecimal service = orderPrice.subtract(orderDetail.getBasePrice()).divide(orderDetail.getBasePrice(),3,BigDecimal.ROUND_UP);
        BigDecimal serviceRate = service.multiply(new BigDecimal("100"));
        //计算发票费
        BigDecimal invoice = orderPrice.multiply(infoSeller.getInvoiceRate().divide(new BigDecimal("100"))).setScale(2,BigDecimal.ROUND_UP);
        //修改买家订单
        infoBuyer.setBuyerServiceRate(serviceRate);
        infoBuyer.setTotalMoney(orderPrice.add(invoice));
        infoBuyer.setReceivableMoney(orderPrice.add(invoice));
        orderInfoBuyerDaoI.updateBuyerOrder(infoBuyer);
        //修改订单明细
        orderDetail.setPrice(orderPrice);
        orderDetailDaoI.updateDetail(orderDetail);
        //修改报名表价格
        infoSeller.setBuyerServiceRate(serviceRate);
        infoSeller.setBuyerOrderPrice(orderPrice);
        orderInfoSellerDaoI.updateOrderSeller(infoSeller);
        //修改费用明细
        for (OrderCostInfo orderCostInfo: costInfoList){
            if(orderCostInfo.getCostTypeIndex() == EnumCostType.作品买家订单.getIndex()
                    && orderCostInfo.getCostId().compareTo(dictInfoDaoI.getDictInfoByTypeAndName(EnumDictInfoType.商品费用明细.getIndex(),"发票费").getId()) == 0){
                orderCostInfo.setMoney(invoice);
                orderCostInfoDaoI.updateMoneyByOrderInfoId(orderCostInfo);
            }
        }
        systemLogDaoI.log(OrderInfoBuyer.class.getName(),orderBuyerId ,"修改价格由" +oldOrderPrice + "修改为" +orderPrice,userName);
    }

    /**
     * 后台—修改订单价格查询
     * @param orderBuyerId
     */
    @Override
    public OrderInfoBuyer findOrderPrice(Integer orderBuyerId) {
        OrderInfoBuyer infoBuyer = orderInfoBuyerDaoI.findOrderPrice(orderBuyerId);
        if(infoBuyer !=null){
            infoBuyer.setOrderCostInfoList(orderCostInfoDaoI.getOrderCostInfoList(infoBuyer.getOrderInfoBuyerId()));
        }
        return infoBuyer;

    }

    /**
     * 确认订单
     * @param data
     * @return
     */
    @Override
    public JSONObject sureOrder(OrderSureSearch data) {
        JSONObject result = null;
        if(data.getType().compareTo( EnumOrderSure.从报名表.getIndex())==0){
            result = sureOrderByOrderInfoSeller(data);
        }else if(data.getType().compareTo( EnumOrderSure.从立即下单.getIndex())==0){
            result = sureOrderByYRProdction(data);
        }else if(data.getType().compareTo( EnumOrderSure.从购物车.getIndex())==0) {
            result = sureOrderByShoppingCarts(data);
        }

        return result;
    }

    /**
     * 确认订单：作品立即下单
     * @param data
     * @return
     */
    public JSONObject sureOrderByYRProdction(OrderSureSearch data) {
        //返回结果
        JSONObject result = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject jb = new JSONObject();
        YRProduction yRProduction =  yRProductionDaoI.getYRProductionById(data.getYrProductionId());
        if(yRProduction == null){
            throw  new YRParamterException("作品不存在！");
        }
        //订单金额 = 作品金额 + 卖家作品购买服务费
        //         = 作品金额 + 作品金额* 卖家作品购买服务费率* 0.001
//        BigDecimal orderPrice =  yRProduction.getSellPrice().add(yRProduction.getSellPrice().multiply(new BigDecimal(0.01)).multiply(new BigDecimal(Double.parseDouble(configurationDaoI.getbyKey("SERVICES_FEE_BUYER_PERCENT"))) ));
        BigDecimal orderPrice =  yRProduction.getSellPrice();
        //INVOICE_PERCENT_BUYER
        //税额
        BigDecimal taxPrice = orderPrice.multiply(new BigDecimal("0.01")).multiply(new BigDecimal(configurationDaoI.getbyKey("INVOICE_PERCENT_BUYER"))).setScale(2,BigDecimal.ROUND_UP);
        //订单总计
        BigDecimal totalPrice = orderPrice.add(taxPrice);
        //实际付款
        BigDecimal receiptMoney = totalPrice;
        jb.put("wordNum",yRProduction.getWordNum());
        jb.put("imgNum",yRProduction.getImgNum());
        jb.put("title",yRProduction.getTitle());
        jb.put("id",yRProduction.getRecId());
        jb.put("orderType",EnumOrderSellerType.作品订单.getIndex());
        jb.put("orderTypeName",EnumOrderSellerType.作品订单.getName());
        jb.put("orderPrice",orderPrice);  //订单金额
        jb.put("taxPrice",taxPrice);  //税额
        jb.put("totalPrice",totalPrice);//订单总计
        jsonArray.add(jb);
        result.put("orderSureDetail",jsonArray);
        result.put("totalOrderPrice",totalPrice);//订单总金额
        result.put("totalTaxPrice",taxPrice);//总税额
        result.put("receiptMoney",receiptMoney);//实付款
        return  result;
    }

    /**
     * 确认订单：报名记录表
     * @param data
     * @return
     */
    public JSONObject sureOrderByOrderInfoSeller(OrderSureSearch data) {
        String[] orderSns = data.getOrderSns();
        //返回结果
        JSONObject result = new JSONObject();
        //
        JSONArray jsonArray = new JSONArray();
        BigDecimal totalTaxPrice = new BigDecimal(0);//税额
        BigDecimal bigPrice = new BigDecimal(0);//订单总金额
        for(int i = 0; i<orderSns.length; i++){
            JSONObject jb = new JSONObject();
            String orderSn = orderSns[i];
            OrderInfoSeller orderInfoSeller = orderInfoSellerDaoI.getByOrderSn(orderSn);
            if(orderInfoSeller == null){
               throw  new YRParamterException("报名记录不存在！");
            }
            // 买家—发票费率
            BigDecimal invoiceRate = orderInfoSeller.getInvoiceRate();
            //订单金额： 买家订单金额(应约价+服务费)
            BigDecimal orderPrice = orderInfoSeller.getBuyerOrderPrice();
            //向上取整
            //costJson.put("servicePrice", buyerServiceRate==""?offerPrice:offerPrice.multiply(new BigDecimal(buyerServiceRate)).multiply(new BigDecimal(0.01)).setScale(0, BigDecimal.ROUND_UP).doubleValue());
            // 税额：
            BigDecimal taxPrice = invoiceRate.multiply(orderPrice).multiply( BigDecimal.valueOf(0.01)).setScale(2,BigDecimal.ROUND_UP) ;
            BigDecimal totalPrice = orderPrice.add(taxPrice);
            jb.put("orderType", orderInfoSeller.getOrderTypeValue());
            jb.put("orderPrice",orderInfoSeller.getBuyerOrderPrice()==null ? "" : orderInfoSeller.getBuyerOrderPrice());
            jb.put("taxPrice",taxPrice);
            jb.put("totalPrice",totalPrice);
            totalTaxPrice =totalTaxPrice.add(taxPrice);
            bigPrice=bigPrice.add(totalPrice);
            if(orderInfoSeller.getOrderTypeValue()==EnumOrderSellerType.原创征稿.getIndex() ||
                    orderInfoSeller.getOrderTypeValue()==EnumOrderSellerType.作品订单.getIndex()){//4
                YRProduction yRProduction =  yRProductionDaoI.getYRProductionById(orderInfoSeller.getReferId());
                jb.put("wordNum",yRProduction.getWordNum());
                jb.put("imgNum",yRProduction.getImgNum());
                jb.put("title",yRProduction.getTitle());
                jb.put("id",yRProduction.getRecId());
                if(orderInfoSeller.getOrderTypeValue()==EnumOrderSellerType.原创征稿.getIndex() ){
                    jb.put("orderTypeName",EnumOrderSellerType.原创征稿.getName());
                }else {
                    jb.put("orderTypeName",EnumOrderSellerType.作品订单.getName());
                }
            }else if(orderInfoSeller.getOrderTypeValue()==EnumOrderSellerType.营销分发.getIndex()){//2
                PlatformIPAccount platformIPAccount = platformIPAccountDaoI.getById(orderInfoSeller.getReferId());
                jb.put("headImg",platformIPAccount.getHeadImageUrlLocal());
                jb.put("name",platformIPAccount.getName());
                jb.put("accountId",platformIPAccount.getAccountID());
                jb.put("orderTypeName",EnumOrderSellerType.营销分发.getName());
            }else if(orderInfoSeller.getOrderTypeValue()==EnumOrderSellerType.定制内容.getIndex()){//3
                YRAuthor yrAuthor = yRAuthorDaoI.getByAuthorId(orderInfoSeller.getReferId());
                jb.put("headImg",yrAuthor.getAuthorImg());
                jb.put("nikeName",yrAuthor.getAuthorNickname());
                jb.put("orderTypeName",EnumOrderSellerType.定制内容.getName());
            }
            jsonArray.add(jb);
        }
        result.put("orderSureDetail",jsonArray);//订单详情
        result.put("totalTaxPrice",totalTaxPrice);//总税额
        result.put("totalOrderPrice",bigPrice); //订单总金额
        result.put("receiptMoney",bigPrice); //实付款 totalTaxPrice.add(bigPrice)
        return result;
    }

    /**
     * 确认订单：购物车作品购买
     * @param data
     * @return
     */
    public JSONObject sureOrderByShoppingCarts(OrderSureSearch data) {
        //返回结果
        JSONObject result = new JSONObject();
        //
        JSONArray jsonArray = new JSONArray();
        //总税额
        BigDecimal totalTaxPrice = new BigDecimal(0);
        //订单总金额
        BigDecimal totalOrderPrice = new BigDecimal(0);
        //实际付款
        BigDecimal receiptMoney = new BigDecimal(0);
        for(int i=0; i<data.getShoppingCarts().length; i++ ){
            JSONObject jb = new JSONObject();
            ShoppingCart shoppingCart = shoppingCartDaoI.getById(data.getShoppingCarts()[i]);
            YRProduction yRProduction =  yRProductionDaoI.getYRProductionById(shoppingCart.getProductId());
            if(yRProduction == null){
                throw  new YRParamterException("作品不存在！");
            }
            //订单金额 = 作品金额 + 买家作品购买服务费
            //         = 作品金额 + 作品金额* 卖家作品购买服务费率* 0.001
//            BigDecimal orderPrice =  yRProduction.getSellPrice().add(yRProduction.getSellPrice().multiply(new BigDecimal(0.01)).multiply(new BigDecimal(configurationDaoI.getbyKey("SERVICES_FEE_BUYER_PERCENT"))));
            BigDecimal orderPrice =  yRProduction.getSellPrice();
            //INVOICE_PERCENT_BUYER
            //税额
            BigDecimal taxPrice = orderPrice.multiply(new BigDecimal("0.01")).multiply(new BigDecimal(configurationDaoI.getbyKey("INVOICE_PERCENT_BUYER"))).setScale(2,BigDecimal.ROUND_UP);
            //订单总计
            BigDecimal totalPrice = orderPrice.add(taxPrice);
//            OrderTotalAmountRoundUtil.getRoundAmount()
            //累计税额
            totalTaxPrice = totalTaxPrice.add(taxPrice);
            //累计金额
            totalOrderPrice = totalOrderPrice.add(totalPrice);
            jb.put("wordNum",yRProduction.getWordNum());
            jb.put("imgNum",yRProduction.getImgNum());
            jb.put("title",yRProduction.getTitle());
            jb.put("id",yRProduction.getRecId());
            jb.put("orderType",EnumOrderSellerType.作品订单.getIndex());
            jb.put("orderTypeName",EnumOrderSellerType.作品订单.getName());
            jb.put("orderPrice",orderPrice);  //订单金额
            jb.put("taxPrice",taxPrice);  //税额
            jb.put("totalPrice",totalPrice);//订单总计
            jsonArray.add(jb);
        }
        result.put("orderSureDetail",jsonArray);
        result.put("totalOrderPrice",totalOrderPrice);//订单总金额
        result.put("totalTaxPrice",totalTaxPrice);//总税额 totalTaxPrice
        result.put("receiptMoney",totalOrderPrice);//实付款  totalOrderPrice.add(totalTaxPrice)
        return  result;
    }


    /**
     * 后台—确认执行 修改订单状态已完成
     * @param orderBuyerId
     */
    @Override
    public void updateOrderBuyerStatus(Integer orderBuyerId,String userName) {
        //修改买家订单—已完成
        OrderInfoBuyer infoBuyer = orderInfoBuyerDaoI.getById(orderBuyerId);
        infoBuyer.setOrderStatus(EnumOrderBuyerStatus.已完成);
        orderInfoBuyerDaoI.updateBuyerOrder(infoBuyer);
        //修改卖家订单——已完成
        SellerOrder sellerOrder = new SellerOrder();
        sellerOrder.setOrderStatus(EnumSellerOrderStatus.已完成.getIndex());
        sellerOrder.setSellerOrderId(infoBuyer.getSellerOrder().getSellerOrderId());
        sellerOrder.setFinishTime(new Date());
        sellerOrderDaoI.updateSeller(sellerOrder);
        //订单是营销分发的话——添加交易记录
        if (infoBuyer.getOrderInfoType().compareTo(EnumOrderSellerType.营销分发.getIndex()) == 0) {
            saveTradingRecord(infoBuyer);
        }
        //订单完成—添加卖家用户余额
        saveUserBalanceDetails(infoBuyer);

        systemLogDaoI.log(OrderInfoBuyer.class.getName(),orderBuyerId ,"将订单状态修改为"+ EnumOrderBuyerStatus.已完成.getName(),userName);
    }

    /**
     * 订单完成—添加卖家用户余额
     * @param infoBuyer
     */
    public void saveUserBalanceDetails(OrderInfoBuyer infoBuyer) {
        SellerOrder sellerOrderOld =sellerOrderDaoI.getById(infoBuyer.getSellerOrder().getSellerOrderId());
        UserBalanceDetails userBalanceDetails = new UserBalanceDetails();
        userBalanceDetails.setMoney(sellerOrderOld.getPayable());
        userBalanceDetails.setTradeTypeIndex(EnumTradeType.收入.getIndex());
        userBalanceDetails.setReferTypeIndex(EnumReferType.需求收入.getIndex());
        userBalanceDetails.setRegisteredUserInfoId(sellerOrderOld.getRegisteredUserInfoId());
        userBalanceDetails.setReferId(sellerOrderOld.getSellerOrderId());
        userBalanceDetails.setBusinessTime(DateUtil.getNowDateTime());
        if (infoBuyer.getOrderInfoType().compareTo(EnumOrderSellerType.营销分发.getIndex()) == 0) {
            userBalanceDetails.setRemark("营销分发—需求收入-" + sellerOrderOld.getProduction());
        }else if (infoBuyer.getOrderInfoType().compareTo(EnumOrderSellerType.定制内容.getIndex()) == 0) {
            userBalanceDetails.setRemark("定制内容—需求收入-" + sellerOrderOld.getProduction());
        }else {
            userBalanceDetails.setRemark("需求收入-" + sellerOrderOld.getProduction());
        }
        userBalanceDetailsDaoI.save(userBalanceDetails);
    }

    /**
     * 订单是营销分发的话——添加交易记录
     * @param infoBuyer
     */
    @Override
    public void saveTradingRecord(OrderInfoBuyer infoBuyer) {
        TradingRecord tradingRecord = new TradingRecord();
        //卖家用户简称
        tradingRecord.setNickName(infoBuyer.getSellerOrder() == null ?
                "" : registeredUserInfoDAO.getById(infoBuyer.getSellerOrder().getRegisteredUserInfoId()) == null ?
                "" : registeredUserInfoDAO.getById(infoBuyer.getSellerOrder().getRegisteredUserInfoId()).getNickName());

        OrderInfoSeller infoSeller = orderInfoSellerDaoI.getById(infoBuyer.getRefreId());//报名记录
        //交易执行时间
        tradingRecord.setTradingDate(infoSeller.getExecuteTime());
        OrderDetail orderDetail = orderDetailDaoI.getByOrderBuyerId(infoBuyer.getOrderInfoBuyerId());//订单明细
        //卖家交易账号
        SnapshotAccount snapshotAccount = snapshotAccountDaoI.getByOrderDetailId(orderDetail.getOrderDetailId());
        tradingRecord.setSellerAccount(snapshotAccount.getName());
        //交易涉及平台
        tradingRecord.setReferPlatform(snapshotAccount.getPlatformName());
        //交易服务内容
        tradingRecord.setServicesContent(orderDetail.getProduction());
        //交易金额
        tradingRecord.setMoney(infoBuyer.getReceivableMoney() == null? "" : infoBuyer.getReceivableMoney().toString());
        //买家用户
        tradingRecord.setBuyerName(registeredUserInfoDAO.getById(infoBuyer.getRegisteredUserInfoId()) == null ?
                "" : registeredUserInfoDAO.getById(infoBuyer.getRegisteredUserInfoId()).getNickName());
        //提交时间
        //数据来源
        tradingRecord.setChannelIndex(EnumChannel.系统创建.getIndex());
        //上传人
        tradingRecord.setHeir("系统");
        tradingRecordDaoI.saveGetKey(tradingRecord);
    }

}
