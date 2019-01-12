package com.yuanrong.admin.rpc.service.impl.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.*;
import com.mchange.lang.DoubleUtils;
import com.yuanrong.admin.Enum.EnumChannel;
import com.yuanrong.admin.Enum.EnumCostType;
import com.yuanrong.admin.Enum.EnumOrderSellerStatus;
import com.yuanrong.admin.Enum.EnumOrderSellerType;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.fiance.UserBalanceDetails;
import com.yuanrong.admin.bean.order.*;
import com.yuanrong.admin.bean.order.OrderCostInfo;
import com.yuanrong.admin.bean.order.OrderInfoOffer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.bean.order.SnapshotAccount;
import com.yuanrong.admin.bean.trading.TradingRecord;
import com.yuanrong.admin.dao.order.OrderInfoOfferDaoI;
import com.yuanrong.admin.exception.ErrorMessageData;
import com.yuanrong.admin.exception.ParmException;
import com.yuanrong.admin.result.OrderInfoBuyerDetailResult;
import com.yuanrong.admin.result.OrderInfoSellerResult;
import com.yuanrong.admin.result.SellerOrderResult;
import com.yuanrong.admin.rpc.api.order.OrderInfoBuyerServicesI;
import com.yuanrong.admin.rpc.api.order.OrderInfoSellerServicesI;
import com.yuanrong.admin.rpc.exception.YRParamterException;
import com.yuanrong.admin.rpc.exception.YuanRongException;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.seach.OrderManagementSearch;
import com.yuanrong.admin.seach.OrderOfferParam;
import com.yuanrong.admin.seach.OrderPriceParamSearch;
import com.yuanrong.admin.seach.SellerOrderSearch;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.OrderTotalAmountRoundUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.SystemParam;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 卖家订单的services实现类
 * Created MDA
 */
@Service
public class OrderInfoSellerServicesImpl extends BaseServicesAbstract<OrderInfoSeller> implements OrderInfoSellerServicesI {
    @Override
    public OrderInfoSeller getById(Integer id) {
        return orderInfoSellerDaoI.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        orderInfoSellerDaoI.deleteById(id);
    }

    @Override
    public void save(OrderInfoSeller object) {
        orderInfoSellerDaoI.save(object);
    }

    @Override
    public List<OrderInfoSeller> getAll() {
        return orderInfoSellerDaoI.getAll();
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp(), baseModel.getRows(), " ois.orderInfoSellerId desc");
        List<OrderInfoSeller> result = orderInfoSellerDaoI.list(data);
        return new PageInfo(result);
    }

    /**
     * 买家中心已购买作品详情
     *
     * @param orderInfoSellerId
     * @return
     */
    @Override
    public Map<String, Object> getSellerProductInfo(Integer orderInfoSellerId) {
        Map<String, Object> map = orderInfoSellerDaoI.getSellerProductInfo(orderInfoSellerId);
        return map;
    }

    /**
     * 后台—买家订单详情（作品购买）
     *
     * @param orderInfoBuyerId
     * @param baseModel
     * @return
     */
    @Override
    public PageInfo orderInfoBuyerDetailList(Integer orderInfoBuyerId, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp(), baseModel.getRows(), "orderInfoSellerId desc");
        List<OrderInfoBuyerDetailResult> result = orderInfoSellerDaoI.orderInfoBuyerDetailList(orderInfoBuyerId);
        return new PageInfo(result);
    }

    /**
     * 订单管理待响应列表查询
     *
     * @param orderManagementSearch
     * @param baseModel
     * @return
     */
    @Override
    public PageInfo getPendingResponseList(OrderManagementSearch orderManagementSearch, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp(), baseModel.getRows(), "ois.orderInfoSellerId desc");
        List<OrderInfoSeller> result = orderInfoSellerDaoI.getPendingResponseList(orderManagementSearch);

        for (OrderInfoSeller orderInfoSeller : result) {
            //查询应约回复
            System.out.println(orderInfoSeller.getOrderInfoSellerId());
            orderInfoSeller.setOrderInfoOfferList(orderInfoSellerDaoI.getOrderInfoOffer(orderInfoSeller.getOrderInfoSellerId()));
            //查询确认信息
            //orderInfoSeller.setOrderCostInfoList(orderInfoSellerDaoI.getCostInfoList(orderInfoSeller.getOrderInfoSellerId()));
        }
        return new PageInfo(result);
    }

    /**
     * @param
     * @author songwq
     * @date 2018/7/9
     * @description 根据需求id查询需求详情
     */
    @Override
    public Demand getDemandInfoByDemandSn(String orderInfoSellerId, BaseModel baseModel) {
        Demand demand = orderInfoSellerDaoI.getDemandInfoByDemandSn(orderInfoSellerId);
        return demand;
    }

    /**
     * @param
     * @author songwq
     * @date 2018/7/10
     * @description 订单管理---待响应--应约
     */
    @Override
    public void insertAcceptConvention(OrderOfferParam orderOfferParam, BaseModel baseModel) {
        //新增价格项数据
        List offerList = new ArrayList<OrderInfoOffer>();
        String[] names = orderOfferParam.getPriceNames();
        String[] prices = orderOfferParam.getPrices();
        OrderInfoSeller orderInfoSeller = orderInfoSellerDaoI.getById(orderOfferParam.getOrderInfoSellerId());
        if(!StringUtils.isEmpty(orderInfoSeller)) {
            for (int i = 0; i < names.length; i++) {//每次应约，默认往offer表插入两条数据，默认执行价格为应约价
                OrderInfoOffer orderInfoOffer = new OrderInfoOffer();
                orderInfoOffer.setOrderInfoSeller(orderOfferParam.getOrderInfoSellerId());
                orderInfoOffer.setPriceName(names[i]);
                orderInfoOffer.setExecutePrice(new BigDecimal(prices[i]).add(new BigDecimal(prices[i]).multiply(orderInfoSeller.getBuyerServiceRate().multiply(new BigDecimal("0.01")))));
                orderInfoOffer.setPrice(new BigDecimal(prices[i]));
                orderInfoOffer.setSellerPrice(new BigDecimal(prices[i]));
                offerList.add(orderInfoOffer);
            }

            orderInfoSellerDaoI.insertOrderInfoOffer(offerList);
            //修改需求状态(已应约)enum订单状态(1. 已拒约；2. 待响应；3. 待确认使用；4. 待执行；5.待确认结果；6. 已完成；7. 已取消；8. 买家拒绝)
            orderOfferParam.setOrderStatusValue(EnumOrderSellerStatus.待买家确认.getIndex());
            //设置响应时间
            orderOfferParam.setResponseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            orderInfoSellerDaoI.updateOrderStatus(orderOfferParam);
        }
    }


    /**
     * @param
     * @author songwq
     * @date 2018/7/10
     * @description 订单管理---待响应--拒约
     */
    @Override
    public void updateRefuseConvention(OrderOfferParam orderOfferParam, BaseModel baseModel) {
        //修改需求状态(已应约)enum订单状态(1. 已拒约；2. 待响应；3. 待确认使用；4. 待执行；5.待确认结果；6. 已完成；7. 已取消；8. 买家拒绝)
        orderOfferParam.setOrderStatusValue(1);
        //设置响应时间
        orderOfferParam.setResponseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        orderInfoSellerDaoI.updateOrderStatus(orderOfferParam);
    }

    /**
     * @param
     * @author songwq
     * @date 2018/7/10
     * @description 订单管理--待确认使用--确认使用查询
     */
    @Override
    public OrderInfoSeller getAccountInfo(Integer orderInfoSellerId, BaseModel baseModel) {
        OrderInfoSeller orderInfoSeller = orderInfoSellerDaoI.getOrderInfoSeller(orderInfoSellerId);
        if (!StringUtils.isEmpty(orderInfoSeller)) {
            orderInfoSeller.setOrderInfoOfferList(orderInfoSellerDaoI.getOrderInfoOffer(orderInfoSellerId));
        }
        return orderInfoSeller;
    }

    /**
     * @param
     * @author songwq
     * @date 2018/7/10
     * @description 订单管理--待确认使用--确认使用提交
     */
    @Override
    public void updateConfirmUse(OrderOfferParam orderOfferParam) {
        BigDecimal serviceFees = new BigDecimal(0);
        Integer orderInfoSellerId = orderOfferParam.getOrderInfoSellerId();
        OrderInfoSeller orderInfoSeller = orderInfoSellerDaoI.getById(orderInfoSellerId);
        List<OrderInfoSeller> orderInfoSellerList = new ArrayList<>();
        orderInfoSellerList.add(orderInfoSeller);

        //生成卖家订单
        OrderInfoBuyerServicesI orderInfoBuyerServicesI = new OrderInfoBuyerServicesImpl();
        orderInfoBuyerServicesI.savePlaceTheDemandOrder(orderInfoSellerList);

        //修改报名表OrderInfoSeller的相关数据
        //查询cost表的服务费
        Integer orderInfoOfferId = orderOfferParam.getOrderInfoOfferId();
        List<OrderCostInfo> costList = orderCostInfoDaoI.getOrderCostInfoList(orderInfoSellerId);
        if(!CollectionUtil.isEmpty(costList)){
            for(OrderCostInfo orderCostInfo : costList){
                if(orderCostInfo.getCostId()==241){
                    serviceFees = orderCostInfo.getMoney();
                }
            }

            OrderInfoOffer orderInfoOffer = orderInfoOfferDaoI.getById(orderInfoOfferId);
            orderInfoSeller.setPrice(orderInfoOffer.getPrice().subtract(serviceFees));//给卖家总金额(应约价-服务费)
            orderInfoSeller.setReferPrice(orderInfoOffer.getPrice());//参考报价
            if(StringUtils.isEmpty(orderOfferParam.getServiceFee())){
                orderInfoSeller.setSellerServiceRate(new BigDecimal(orderOfferParam.getServiceFee()));
            }
            orderInfoSellerDaoI.updateOrderStatus(orderOfferParam);
        }else{
            throw new YuanRongException("无法获取订单的服务费");
        }
        //新增orderCostInfo
        /*List<OrderCostInfo> orderCostInfoList = new ArrayList();
        BigDecimal price = orderOfferParam.getPrice();//成本价(应约价)
        BigDecimal offerPrice = orderOfferParam.getExecutePrice();//执行价
        if (StringUtils.isEmpty(offerPrice)) {
            offerPrice = price;
        }
        int cost = 240;//字典表成本价编号
        int service = 241;//字典表服务费编号
        int invoice = 242;//字典表发票费编号
        int[] costId = {cost, service, invoice};
        //查询服务费比率
        //String serviceRate = configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_SELLER_PERCENT);
        //前台传的10，实际上是10%，所以后台处理数据的时候要乘以0.01
        double serviceRate = Double.valueOf(orderOfferParam.getServiceFee()) * 0.01;
        //查询发票费比率 后台处理数据的时候要乘以0.01
        double invoiceRate = new Double("0");
        if (orderOfferParam.getOrderTypeValue().compareTo(EnumOrderSellerType.营销分发.getIndex()) == 0) {
            invoiceRate = (100 - Integer.parseInt(configurationDaoI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_ACCOUNT))) * 0.01;
        } else if (orderOfferParam.getOrderTypeValue().compareTo(EnumOrderSellerType.定制内容.getIndex()) == 0) {
            invoiceRate = (100 - Integer.parseInt(configurationDaoI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_AUTHOR))) * 0.01;
        } else if (orderOfferParam.getOrderTypeValue().compareTo(EnumOrderSellerType.原创征稿.getIndex()) == 0) {
            invoiceRate = (100 - Integer.parseInt(configurationDaoI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_COLLECTION))) * 0.01;
        }
        //各种费用计算后的值
        double costFee = price.doubleValue();//成本费(应约价)
        double serviceFee = Double.parseDouble(String.format("%.2f", costFee * serviceRate));//服务费=成本费*服务费比率
        double invoiceFee = Double.parseDouble(String.format("%.2f", (costFee + serviceFee) / invoiceRate - serviceFee - costFee));//发票费=（服务费+成本费)/发票费比率-服务费-成本费---向上取整
        BigDecimal[] fee = {new BigDecimal(costFee), new BigDecimal(serviceFee), new BigDecimal(invoiceFee)};
        //循环将各种费用写入实体类
        for (int i = 0; i < costId.length; i++) {
            OrderCostInfo orderCostInfo = new OrderCostInfo();
            orderCostInfo.setOrderInfoId(orderOfferParam.getOrderInfoSellerId());
            orderCostInfo.setCostId(costId[i]);
            orderCostInfo.setMoney(fee[i]);
            orderCostInfo.setCostTypeIndex(2);//买家订单
            orderCostInfoList.add(orderCostInfo);
        }
        //卖家服务费率
        double sellerServiceRate = 0.0;
        if (orderOfferParam.getSellerServiceRate() != null) {
            sellerServiceRate = Double.parseDouble(orderOfferParam.getSellerServiceRate().toString()) * 0.01;
        } else {
            if (orderOfferParam.getOrderTypeValue().compareTo(EnumOrderSellerType.营销分发.getIndex()) == 0) {
                sellerServiceRate = Double.parseDouble(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_SELLER_ACCOUNT)) * 0.01;
            } else if (orderOfferParam.getOrderTypeValue().compareTo(EnumOrderSellerType.定制内容.getIndex()) == 0) {
                sellerServiceRate = Double.parseDouble(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_SELLER_AUTHOR)) * 0.01;
            }
        }
        //卖家发票费
        double sellerInvoiceRate = Double.parseDouble(configurationDaoI.getbyKey(SystemParam.INVOICE_PERCENT_SELLER)) * 0.01;
        //各种费用计算后的值
        double sellerCostFee = offerPrice.doubleValue();//执行价
        double sellerServiceFee = Double.parseDouble(String.format("%.2f", (sellerCostFee * sellerServiceRate)));//服务费=成本费*服务费比率
        double sellerInvoiceFee = sellerInvoiceRate == 0 ? 0 : Double.parseDouble(String.format("%.2f", (sellerCostFee + sellerServiceFee) / sellerInvoiceRate - sellerInvoiceRate - sellerCostFee));//发票费=（服务费+执行价)/发票费比率-服务费-成本费---向上取整
        BigDecimal[] sellerFee = {new BigDecimal(sellerCostFee), new BigDecimal(sellerServiceFee), new BigDecimal(sellerInvoiceFee)};

        for (int i = 0; i < costId.length; i++) {
            OrderCostInfo orderCostInfo = new OrderCostInfo();
            orderCostInfo.setOrderInfoId(orderOfferParam.getOrderInfoSellerId());
            orderCostInfo.setCostId(costId[i]);
            orderCostInfo.setMoney(sellerFee[i]);
            orderCostInfo.setCostTypeIndex(3);//卖家需求
            orderCostInfoList.add(orderCostInfo);
        }
        orderCostInfoDaoI.insertOrderCostInfo(orderCostInfoList);
        //修改OrderInfoSeller
        orderOfferParam.setOrderStatusValue(4);
        orderOfferParam.setBuyerPrice(new BigDecimal(costFee + serviceFee + invoiceFee));//买家应付成本费+服务费+发票费
        orderOfferParam.setOfferPrice(new BigDecimal(costFee));//卖家应约价
        *//*double sellerServiceRate = 0.0;
        if(orderOfferParam.getOrderTypeValue()==2){//账号
            sellerServiceRate = Integer.valueOf(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_SELLER_ACCOUNT))*0.01;
        }else if(orderOfferParam.getOrderTypeValue()==3){//创作者
            sellerServiceRate = Integer.valueOf(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_SELLER_AUTHOR))*0.01;
        }else if(orderOfferParam.getOrderTypeValue()==1){//作品
            sellerServiceRate = Integer.valueOf(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_SELLER_PERCENT))*0.01;
        }*//*
        //卖家服务费
        //double sellerServiceFee = costFee*sellerServiceRate;
        orderOfferParam.setPayable(new BigDecimal(sellerCostFee - sellerServiceFee));//卖家成本费-服务费
        orderOfferParam.setProduction(orderOfferParam.getPriceName());
        orderOfferParam.setBuyerServiceRate(new BigDecimal(orderOfferParam.getServiceFee()));
        orderInfoSellerDaoI.updateOrderStatus(orderOfferParam);*/
    }

    /**
     * @param
     * @author songwq
     * @date 2018/7/10
     * @description 订单管理--待确认使用--拒绝使用
     */
    @Override
    public void updateRefuseUse(OrderOfferParam orderOfferParam, BaseModel baseModel) {
        orderOfferParam.setOrderStatusValue(EnumOrderSellerStatus.买家拒绝.getIndex());
        orderInfoSellerDaoI.updateOrderStatus(orderOfferParam);
    }

    /**
     * @param
     * @author songwq
     * @date 2018/7/12
     * @description 订单管理--待执行-修改执行数据查询
     */
    @Override
    public OrderInfoSeller getExecuteInfo(OrderInfoSeller orderInfoSeller, BaseModel baseModel) {
        OrderInfoSeller executeInfo = orderInfoSellerDaoI.getExecuteInfo(orderInfoSeller);
        return executeInfo;
    }

    /**
     * @param
     * @author songwq
     * @date 2018/7/12
     * @description 订单管理--待执行-提交执行
     */
    @Override
    public void updateCommitExecute(OrderOfferParam orderOfferParam,BaseModel baseModel) {
        //设置反馈时间
        orderOfferParam.setReturnTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        orderInfoSellerDaoI.updateOrderStatus(orderOfferParam);
        //修改卖家订单表的状态
        SellerOrder sellerOrder = sellerOrderDaoI.getById(orderOfferParam.getSellerOrderId());
        sellerOrder.setOrderStatus(EnumSellerOrderStatus.待买家验收.getIndex());
        sellerOrderDaoI.updateSeller(sellerOrder);
        //修改买家订单表的状态
        Integer orderInfoBuyerId =orderInfoBuyerDaoI.getBuyerIdBySellerId(orderOfferParam.getSellerOrderId());
        OrderInfoBuyer buyer = orderInfoBuyerDaoI.getById(orderInfoBuyerId);
        buyer.setOrderStatusValue(EnumOrderBuyerStatus.待买家验收.getIndex());
        orderInfoBuyerDaoI.updateBuyerOrder(buyer);
    }


    /**
     * @param
     * @author songwq
     * @date 2018/7/13
     * @description 订单管理--待确认执行-确认结果
     */
    @Override
    public void updateConfirmResult(OrderOfferParam orderOfferParam, BaseModel baseModel) {
//        orderOfferParam.setOrderStatusValue(EnumOrderSellerStatus.已完成.getIndex());//订单已完成
        OrderInfoSeller orderInfoSeller = orderInfoSellerDaoI.getById(orderOfferParam.getOrderInfoSellerId());
        //订单是营销分发的话——添加交易记录
        /*if (orderInfoSeller != null && orderInfoSeller.getOrderTypeValue().compareTo(EnumOrderSellerType.营销分发.getIndex()) == 0) {

            TradingRecord tradingRecord = new TradingRecord();
            //卖家用户简称
            tradingRecord.setNickName(registeredUserInfoDAO.getById(orderInfoSeller.getRegisteredUserInfoId()).getNickName());
            //交易执行时间
            tradingRecord.setTradingDate(orderInfoSeller.getExecuteTime());
            //卖家交易账号
            SnapshotAccount snapshotAccount = snapshotAccountDaoI.getByOrderInfoSellerId(orderInfoSeller.getOrderInfoSellerId());
            tradingRecord.setSellerAccount(snapshotAccount.getName());
            //交易涉及平台
            tradingRecord.setReferPlatform(snapshotAccount.getPlatformName());
            //交易服务内容
            tradingRecord.setServicesContent(orderInfoSeller.getProduction());
            //交易金额
            tradingRecord.setMoney(String.valueOf(orderInfoSeller.getBuyerPrice()));
            //买家用户
            tradingRecord.setBuyerName(registeredUserInfoDAO.getById(demandDaoI.getById(orderInfoSeller.getDemandId()).getRegisteredUserInfoId()).getNickName());
            //提交时间
            //数据来源
            tradingRecord.setChannelIndex(EnumChannel.系统创建.getIndex());
            //上传人
            tradingRecord.setHeir("系统");
            tradingRecordDaoI.saveGetKey(tradingRecord);
        }*/
        //订单完成—添加用户余额
        UserBalanceDetails userBalanceDetails = new UserBalanceDetails();
        userBalanceDetails.setMoney(orderInfoSeller.getPayable());
        userBalanceDetails.setTradeTypeIndex(EnumTradeType.收入.getIndex());
        userBalanceDetails.setReferTypeIndex(EnumReferType.需求收入.getIndex());
        userBalanceDetails.setRegisteredUserInfoId(orderInfoSeller.getRegisteredUserInfoId());
        userBalanceDetails.setReferId(orderInfoSeller.getOrderInfoSellerId());
        userBalanceDetails.setBusinessTime(DateUtil.getNowDateTime());
        userBalanceDetails.setRemark("需求收入-" + orderInfoSeller.getProduction());
        userBalanceDetailsDaoI.save(userBalanceDetails);
        orderInfoSellerDaoI.updateOrderStatus(orderOfferParam);
    }

    /**
     * @param
     * @author songwq
     * @date 2018/7/13
     * @description 拒绝原因查询
     */
    @Override
    public List<DictInfo> getRefuseReason(BaseModel baseModel) {
        List<DictInfo> dictInfoList = dictInfoDaoI.getDictInfoByType(19);
        return dictInfoList;
    }

    @Override
    public OrderInfoSeller getByOrderSnAndRegisterUserInfoId(String orderSn, Integer registerUserInfoId) {
        return orderInfoSellerDaoI.getByOrderSnAndRegisterUserInfoId(orderSn, registerUserInfoId);
    }

    /**
     * 后台—修改订单状态—待响应应约或者拒约、拒绝使用、执行终止状态修改
     *
     * @param orderInfo
     */
    @Override
    public void updateOrderStatus(OrderOfferParam orderInfo) {
        orderInfoSellerDaoI.updateOrderStatus(orderInfo);
    }


    /**
     * 查询各订单状态的订单数
     *
     * @param
     */
    @Override
    public List<Map<String, Object>> getOrderStatusCount(OrderManagementSearch data) {
        return orderInfoSellerDaoI.getOrderStatusCount(data);
    }

    /**
     * 删除卖家订单—假删
     *
     * @param orderInfoSellerId
     */
    @Override
    public void deleteSellerOrder(Integer orderInfoSellerId) {
        orderInfoSellerDaoI.deleteSellerOrder(orderInfoSellerId);
    }



    @Override
    public void saveMultProduct(List<OrderInfoSeller> list) {
        for(OrderInfoSeller data : list){
            orderInfoSellerDaoI.save(data);
        }
    }

    @Override
    public int saveContent(OrderInfoSeller data) {
        orderInfoSellerDaoI.save(data);
        return data.getOrderInfoSellerId();
    }

    /**
     *  step1 : 将data数据存入到 OrderInfoSeller；
     *  step2 : 得到key(主键Id)将数据插入 List<OrderInfoOffer> list
     */
    @Override
    public void saveContentOffer(OrderInfoSeller data, List<OrderInfoOffer> list) {
       int key_orderInfoSellerId = saveContent(data);
       for(OrderInfoOffer orderInfoOffer : list){
           orderInfoOffer.setOrderInfoSeller(key_orderInfoSellerId);
           orderInfoOffer.setStatusIndex(1);
           orderInfoOffer.setCreatedTime(new Date());
           orderInfoOffer.setExecutePrice(OrderTotalAmountRoundUtil.getRoundAmount(orderInfoOffer.getPrice().add(orderInfoOffer.getPrice().multiply(data.getBuyerServiceRate().divide(new BigDecimal("100"))))));
           orderInfoOffer.setSellerPrice(orderInfoOffer.getPrice());
           orderInfoOfferDaoI.save(orderInfoOffer);
       }
    }

    @Override
    public int cnt_num(Map<String, Object> map) {
        return orderInfoSellerDaoI.cnt_num(map);
    }

    public void updateCost(OrderCostInfo orderCostInfo, BigDecimal price, BigDecimal serviceFee) {
        if (orderCostInfo.getCostId() == 240) {//执行价
            orderCostInfo.setMoney(price);
            orderCostInfoDaoI.updateMoneyByOrderInfoId(orderCostInfo);
        } else if (orderCostInfo.getCostId() == 241) {//执行服务费
            orderCostInfo.setMoney(serviceFee);
            orderCostInfoDaoI.updateMoneyByOrderInfoId(orderCostInfo);
        }
    }

    @Override
    public OrderInfoSeller getByOrderSn(String orderSn) {
        return orderInfoSellerDaoI.getByOrderSn(orderSn);
    }
    @Override
    public OrderInfoSeller getBySellerOrderSn(String orderSn) {
        return orderInfoSellerDaoI.getBySellerOrderSn(orderSn);
    }

    /**
     * 后台—报名记录修改价格（原创征稿）
     * @param orderSignId
     * @param orderPrice
     */
    @Override
    public void updateProPriceInfo(Integer orderSignId, BigDecimal orderPrice,String userName) {
        OrderInfoSeller seller = orderInfoSellerDaoI.getById(orderSignId);
        BigDecimal oldOrderPrice = seller.getBuyerOrderPrice();
        //订单金额
        seller.setBuyerOrderPrice(orderPrice);
        //服务费率
        BigDecimal service = orderPrice.subtract(seller.getReferPrice()).divide(seller.getReferPrice(),3,BigDecimal.ROUND_UP).multiply(new BigDecimal("100"));
        seller.setBuyerServiceRate(service);

        orderInfoSellerDaoI.updateOrderSeller(seller);
        systemLogDaoI.log(OrderInfoSeller.class.getName(),orderSignId ,"修改价格由"+ oldOrderPrice + "修改为"+ orderPrice,userName);
    }

    @Override
    public List<OrderInfoSeller> getReferIdByDemandSn(String demandSn,Integer registeredUserInfoID) {
        return orderInfoSellerDaoI.getReferIdByDemandSn(demandSn,registeredUserInfoID);
    }

    @Override
    public void updateEntity(OrderInfoSeller orderInfoSeller) {
        OrderInfoSeller orderInfoSeller1 = orderInfoSellerDaoI.getByOrderSn(orderInfoSeller.getOrderSn());
        if(orderInfoSeller1 == null){
            throw new ParmException(new ErrorMessageData("","数据错误"));
        }else {
            //业务判断，更新报名状态
            if(orderInfoSeller.getOrderStatusValue()== EnumOrderSellerStatus.买家拒绝.getIndex()&&!orderInfoSeller1.getOrderStatus().equals(EnumOrderSellerStatus.待买家确认)){
                throw new YRParamterException("报名状态不是待买家确认");
            }
        }
        orderInfoSellerDaoI.updateOrderSeller(orderInfoSeller);
    }

    @Override
    public OrderInfoSeller getByParam(OrderInfoSeller data) {
        return orderInfoSellerDaoI.getByParam(data);
    }

    @Override
    public SellerOrderResult getDetailBySn(SellerOrderSearch sellerOrderSearch) {
        return orderInfoSellerDaoI.getDetailBySn(sellerOrderSearch);
    }

    @Override
    public OrderInfoSellerResult getDetailUnConfirmBySn(String orderSn , Integer registerUserInfoId) {
        return orderInfoSellerDaoI.getDetailUnConfirmBySn(orderSn, registerUserInfoId);
    }
}
