package com.yuanrong.admin.rpc.service.impl.data;

import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.fiance.UserBalanceDetails;
import com.yuanrong.admin.bean.order.*;
import com.yuanrong.admin.rpc.api.data.DataProcessServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.SystemParam;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/10/22.
 */

@Service
public class DataProcessServicesImpl extends BaseServicesAbstract implements DataProcessServicesI {
    @Override
    public void updateDataProcessingV1_6() throws ParseException {
        List<Map> orderInfoseller = dataProcessDaoI.getOrderInfoseller();
        if (CollectionUtil.size(orderInfoseller) >0){
            for (Map ele: orderInfoseller ) {
                int orderTypeValue = Integer.parseInt(ele.get("orderTypeValue").toString());
                if(orderTypeValue == 1){
                    if(Integer.parseInt(ele.get("orderInfoSellerId").toString()) ==6){
                        System.out.println("11111111111");
                    }
                    //*****作品类型订单******
                    //生成订单明细
                    System.out.println("=============="+ele.get("orderInfoSellerId"));
                    Map snapYrproduction = dataProcessDaoI.getSnapshotYrProductionByOrderInfoSellerId(ele);
                    if(snapYrproduction == null){
                        continue;
                    }
                    ele.put("referId" , snapYrproduction.get("yrProductionId"));
                    dataProcessDaoI.saveOrderDetail(ele);
                    //处理订单明细和快照关系
                    dataProcessDaoI.updateSnpYrProductionOrderDetailId( Integer.parseInt(snapYrproduction.get("snapshotYrProductionId").toString()),Integer.parseInt(ele.get("orderDetailId").toString()));
                    //生成卖家订单
                    SellerOrder sellerOrder = new SellerOrder();
                    sellerOrder.setOrderDetailId(Integer.parseInt(ele.get("orderDetailId").toString()));
                    sellerOrder.setOrderSn(ele.get("orderSn").toString());
                    sellerOrder.setPrice(new BigDecimal(ele.get("price").toString()));
                    sellerOrder.setPayable(new BigDecimal(ele.get("payable") == null ? "0" : ele.get("payable").toString()));
                    sellerOrder.setEnumOrderSellerType(EnumOrderSellerType.作品订单);
                    /**
                     * 卖家拒约("卖家拒约",1,"卖家拒约"),待卖家响应("待卖家响应",2,"待卖家响应"),待买家确认("待买家确认",3, "待买家确认"),
                     *     待执行("待执行",4,"待执行"),待确认执行("待确认执行",5,"待确认执行"),已完成("已完成",6,"已完成"), 已取消("已取消",7,"已取消"),
                     *
                     *     买家拒绝("买家拒绝",8,"买家拒绝"),已生成订单("已生成订单",9,"已生成订单");
                     */
                    int orderStatus = Integer.parseInt(ele.get("orderStatusValue").toString());
                    if(orderStatus ==4 ){
                        sellerOrder.setEnumSellerOrderStatus(EnumSellerOrderStatus.待买家付款);
                    }else if(orderStatus ==6){
                        sellerOrder.setEnumSellerOrderStatus(EnumSellerOrderStatus.已完成);
                    }else if(orderStatus ==7){
                        sellerOrder.setEnumSellerOrderStatus(EnumSellerOrderStatus.已取消);
                        sellerOrder.setCancelReason("订单超时未支付，取消订单");
                    }else {
                        throw new RuntimeException("作品订单，订单状态不存在！状态值："+orderStatus+" 卖家订单ID："+ele.get("orderInfoSellerId"));
                    }
                    sellerOrder.setNum(1);
                    sellerOrder.setProduction(ele.get("production").toString());
                    sellerOrder.setSellerServiceRate(new BigDecimal(10));
                    sellerOrder.setRegisteredUserInfoId(Integer.parseInt(ele.get("registeredUserInfoId").toString()));
                    sellerOrder.setCreatedTime(DateUtil.parseDate(ele.get("createdTime").toString(),"yyyy-MM-dd HH:mm:ss"));
                    sellerOrder.setStatusIndex(1);
                    sellerOrder.setModifiedTime(DateUtil.parseDate(ele.get("modifiedTime").toString(),"yyyy-MM-dd HH:mm:ss"));
                    sellerOrder.setChannel(EnumChannel.前台创建);
                    sellerOrder.setModifiedCount(1);
                    sellerOrder.setMediaUserId(117);
                    sellerOrderDaoI.save(sellerOrder);
                    orderInfoSellerDaoI.deleteById(Integer.parseInt(ele.get("orderInfoSellerId").toString()));
                }else if(orderTypeValue == 2 || orderTypeValue == 3){
                    //*****2-----营销分发*****3----内容定制*****
                    Integer orderStatus = Integer.parseInt(ele.get("orderStatusValue").toString());
                    //根据状态处理
                    switch (orderStatus){
                        case 1://已拒约1——卖家拒约1
                            //修改报名记录
                            OrderInfoSeller infoSeller = updatOrderInfoSeller(ele);
//                            OrderInfoSeller orderInfoSeller = dataProcessDaoI.getOrderInfoSeller(infoSeller.getDemandId(),infoSeller.getReferId()
//                                    ,infoSeller.getRegisteredUserInfoId(),infoSeller.getOrderTypeValue());
//                            if(orderInfoSeller ==null) {
                                orderInfoSellerDaoI.updateOrderSeller(infoSeller);
//                            }
                            break;
                        case 2://待响应2——待卖家响应2
                            //修改报名记录
                            OrderInfoSeller infoSeller2 = updatOrderInfoSeller(ele);
//                            OrderInfoSeller orderInfoSeller2 = dataProcessDaoI.getOrderInfoSeller(infoSeller2.getDemandId(),infoSeller2.getReferId()
//                                    ,infoSeller2.getRegisteredUserInfoId(),infoSeller2.getOrderTypeValue());
//                            if(orderInfoSeller2 ==null) {
                                orderInfoSellerDaoI.updateOrderSeller(infoSeller2);
//                            }
                            break;
                        case 3://待确认使用3——待买家确认3
                            //修改报名记录
                            OrderInfoSeller infoSeller3 = updatOrderInfoSeller(ele);
//                            OrderInfoSeller orderInfoSeller3 = dataProcessDaoI.getOrderInfoSeller(infoSeller3.getDemandId(),infoSeller3.getReferId(),
//                                    infoSeller3.getRegisteredUserInfoId(),infoSeller3.getOrderTypeValue());
//                            if(orderInfoSeller3 ==null){
                                orderInfoSellerDaoI.updateOrderSeller(infoSeller3);
//                            }
                            //将报价项保存在需求中
                            updateDemand(infoSeller3);
                            break;
                        case 4://待执行4——已生成订单9
                            updateOrder(ele);
                            break;
                        case 5://待确认执行5——已生成订单9
                            updateOrder(ele);
                            break;
                        case 6://已完成6——已生成订单9
                            updateOrder(ele);
                            break;
                        case 7://已取消7——已生成订单9
                            updateOrder(ele);
                            break;
                        case 8://买家拒绝8——买家拒绝8//修改报名记录
                            OrderInfoSeller infoSeller8 = updatOrderInfoSeller(ele);
                            orderInfoSellerDaoI.updateOrderSeller(infoSeller8);
                            //将报价项保存在需求中
                            updateDemand(infoSeller8);
                            break;
                    }
                }
            }
        }
    }

    private void updateOrder(Map ele) throws ParseException {
        //修改报名记录
        OrderInfoSeller infoSeller = updatOrderSeller(ele);
        infoSeller.setOrderStatus(EnumOrderSellerStatus.已生成订单);
//        OrderInfoSeller orderInfoSeller = dataProcessDaoI.getOrderInfoSeller(infoSeller.getDemandId(),infoSeller.getReferId(),infoSeller.getRegisteredUserInfoId(),infoSeller.getOrderTypeValue());
//        if(orderInfoSeller ==null) {
            orderInfoSellerDaoI.updateOrderSeller(infoSeller);
//        }
        //将报价项保存在需求中
        updateDemand(infoSeller);
        //添加买家订单
        ele.put("invoiceRate",infoSeller.getInvoiceRate());
        ele.put("buyerServiceRate",infoSeller.getBuyerServiceRate());
        ele.put("sellerServiceRate",infoSeller.getSellerServiceRate());
        ele.put("saleUserId",infoSeller.getSaleUserId());
        OrderInfoBuyer infoBuyer = saveOrderInfoBuyer(ele);
        dataProcessDaoI.saveOrderBuyer(infoBuyer);
        //添加订单明细
        OrderDetail detail = saveOrderDetail(ele);
        System.out.println("买家订单-----------"+infoBuyer.getOrderInfoBuyerId());
        detail.setOrderInfoBuyerId(infoBuyer.getOrderInfoBuyerId());
        dataProcessDaoI.saveDetail(detail);
        //修改快照和明细关系
        Integer orderTypeValue = Integer.parseInt(ele.get("orderTypeValue").toString());
        if(orderTypeValue == 2 && ele.get("snapshotAccountId") !=null){//营销分发
            dataProcessDaoI.updateSnapshotAccount(detail.getOrderDetailId() , Integer.parseInt(ele.get("snapshotAccountId").toString()));
        }else if(orderTypeValue == 3 && ele.get("snapshotYrAuthorId") != null){//定制内容
            dataProcessDaoI.updateSnapshotYrAuthor(detail.getOrderDetailId() , Integer.parseInt(ele.get("snapshotYrAuthorId").toString()));
        }
        //添加卖家订单
        SellerOrder sellerOrder = saveSellerOrder(ele);
        System.out.println("买家订单明细++++++++====="+detail.getOrderDetailId());
        sellerOrder.setOrderDetailId(detail.getOrderDetailId());
        dataProcessDaoI.saveSellerOrder(sellerOrder);
        if(Integer.parseInt(ele.get("orderStatusValue").toString()) == 6){//已完成——修改对应的卖家用户余额UserBalanceDetails
            UserBalanceDetails userBalanceDetails = new UserBalanceDetails();
            UserBalanceDetails userBalanceDetailsOld = dataProcessDaoI.getUserBalanceDetails(Integer.parseInt(ele.get("orderInfoSellerId").toString()));
            if(userBalanceDetailsOld != null){
                userBalanceDetails.setUserBalanceDetailsId(userBalanceDetailsOld.getUserBalanceDetailsId());
                userBalanceDetails.setReferId(sellerOrder.getSellerOrderId());
                dataProcessDaoI.updateUserBalanceDetails(userBalanceDetails);
            }
        }
        //修改金额明细表
        List<OrderCostInfo> list = orderCostInfoDaoI.getOrderCostInfoList(Integer.parseInt(ele.get("orderInfoSellerId").toString()));
        if(CollectionUtil.size(list) > 0){
            for (OrderCostInfo costInfo : list){
                dataProcessDaoI.updateOrderCostInfo(costInfo.getOrderCostInfoId(),infoBuyer.getOrderInfoBuyerId(),costInfo.getCostTypeIndex());
            }
        }

    }

    /**
     * 修改需求的价格项
     * @param infoSeller3
     */
    private void updateDemand(OrderInfoSeller infoSeller3) {
        List<OrderInfoOffer> list =orderInfoOfferDaoI.getByOrderInfoSellerId(infoSeller3.getOrderInfoSellerId());
        StringBuilder offerName = new StringBuilder();
        if(CollectionUtil.size(list) > 0){
            for (OrderInfoOffer infoOffer : list){
                if(offerName == null || offerName.length() <=0){
                    offerName.append(infoOffer.getPriceName());
                }else{
                    offerName.append("_-,-_" + infoOffer.getPriceName());
                }
            }
        }
        System.out.println("*********************************************" + offerName);
        if(offerName != null && offerName.length() >0){
            Demand demand = new Demand();
            demand.setDemandId(infoSeller3.getDemandId());
            demand.setExpectOffer(offerName.toString());
            demandDaoI.updateDemand(demand);
        }
    }

    /**
     * 通用—报名记录
     * @param ele
     * @return
     */
    private OrderInfoSeller updatOrderInfoSeller(Map ele){
        OrderInfoSeller infoSeller = new OrderInfoSeller();
        infoSeller.setOrderInfoSellerId(Integer.parseInt(ele.get("orderInfoSellerId").toString()));
        infoSeller.setBuyerServiceRate(new BigDecimal(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_BUYER_ACCOUNT)));//买家服务费率
        infoSeller.setSellerServiceRate(new BigDecimal(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_SELLER_ACCOUNT)));//卖家服务费率
        infoSeller.setInvoiceRate(new BigDecimal(configurationDaoI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_ACCOUNT)));//买家税率
        infoSeller.setMediaUserId(117);//买家用户对应的销售
        infoSeller.setSaleUserId(105);//卖家用户对应的媒介
        ele.put("mediaUserId",117);
        ele.put("saleUserId",105);
        infoSeller.setDemandId(Integer.parseInt(ele.get("demandId").toString()));
        infoSeller.setOrderTypeValue(Integer.parseInt(ele.get("orderTypeValue").toString()));
        Integer orderTypeValue = Integer.parseInt(ele.get("orderTypeValue").toString());
        if(orderTypeValue == 2){//营销分发
            System.out.println("-------------" + ele.get("orderInfoSellerId"));
            SnapshotAccount snapshotAccount = snapshotAccountDaoI.getByOrderInfoSellerId(Integer.parseInt(ele.get("orderInfoSellerId").toString()));
            if(snapshotAccount != null){
                infoSeller.setReferId(snapshotAccount.getPlatformIPAccountId());//报名账号Id
                ele.put("snapshotAccountId",snapshotAccount.getSnapshotAccountId());
                if(ele.get("production") == null){//
                    infoSeller.setProduction(snapshotAccount.getName());
                }
            }
        }else if(orderTypeValue == 3){//定制内容
            SnapshotYrAuthor snapshotYrAuthor = dataProcessDaoI.getByOrderInfoSellerId(Integer.parseInt(ele.get("orderInfoSellerId").toString()));
            if(snapshotYrAuthor !=null){
                infoSeller.setReferId(snapshotYrAuthor.getYrAuthorId());//报名账号Id
                ele.put("snapshotYrAuthorId",snapshotYrAuthor.getSnapshotYrAuthorId());
                if(ele.get("production") == null){
                    infoSeller.setProduction(snapshotYrAuthor.getAuthorNickname());
                }
            }
        }
        return infoSeller;
    }

    /**
     * 保存价格——报名记录
     * @param ele
     * @return
     */
    private OrderInfoSeller updatOrderSeller(Map ele){
        OrderInfoSeller infoSeller = updatOrderInfoSeller(ele);
        List<OrderCostInfo> list = orderCostInfoDaoI.getOrderCostInfoList(infoSeller.getOrderInfoSellerId());
        Map map = getCost(list);
        BigDecimal costMoney = new BigDecimal(map.get("costMoney") ==null ? "0":map.get("costMoney").toString());
        BigDecimal serviceMoney = new BigDecimal(map.get("serviceMoney") == null? "0" : map.get("serviceMoney").toString());
        infoSeller.setSellerPrice(costMoney);
        ele.put("sellerPrice",costMoney);
        infoSeller.setBuyerOrderPrice(costMoney.add(serviceMoney));
        ele.put("buyerOrderPrice",costMoney.add(serviceMoney));
        infoSeller.setReferPrice(costMoney);
        ele.put("referPrice",costMoney);
        return infoSeller;
    }

    /**
     * 买家订单
     * @param ele
     * @return
     */
    private OrderInfoBuyer saveOrderInfoBuyer(Map ele) throws ParseException {
        OrderInfoBuyer infoBuyer = new OrderInfoBuyer();
        infoBuyer.setOrderSn(ele.get("orderSn").toString());//订单号
        OrderInfoSeller orderInfoSeller = dataProcessDaoI.getByOrderSellerId(Integer.parseInt(ele.get("orderInfoSellerId").toString()));
        infoBuyer.setRegisteredUserInfoId(orderInfoSeller.getDemand() == null ? null : orderInfoSeller.getDemand().getRegisteredUserInfoId());//买家用户
        infoBuyer.setPayStatus(EnumPayStatus.待支付);
        if(ele.get("orderStatusValue").equals(4)){//待执行
            infoBuyer.setOrderStatus(EnumOrderBuyerStatus.待卖家执行);
        }else if(ele.get("orderStatusValue").equals(5)){//待确认执行
            infoBuyer.setOrderStatus(EnumOrderBuyerStatus.待买家验收);
        }else if(ele.get("orderStatusValue").equals(6)){//已完成
            infoBuyer.setOrderStatus(EnumOrderBuyerStatus.已完成);
            infoBuyer.setPayStatus(EnumPayStatus.待支付);
            infoBuyer.setPayTime(ele.get("createdTime").toString());
        }else if(ele.get("orderStatusValue").equals(7)) {//已取消
            infoBuyer.setOrderStatus(EnumOrderBuyerStatus.已取消);
        }
        infoBuyer.setOrderSn(orderInfoSeller.getOrderSn());
        infoBuyer.setCreatedTime(new Date());
        infoBuyer.setAmountCollected(BigDecimal.ZERO);
        infoBuyer.setSourceId(EnumOrderSource.后台创建.getIndex());
        infoBuyer.setCreateUser("系统");
        if(ele.get("orderTypeValue").equals(2)){
            infoBuyer.setEnumOrderSellerType(EnumOrderSellerType.营销分发);
        }else if(ele.get("orderTypeValue").equals(3)){
            infoBuyer.setEnumOrderSellerType(EnumOrderSellerType.定制内容);
        }
        infoBuyer.setRefreId(orderInfoSeller.getOrderInfoSellerId());
        if(ele.get("orderStatusValue").equals(7)) {//已取消
            if(ele.get("executeOver") != null){
                infoBuyer.setCancelReason(ele.get("executeOver").toString());
            }
        }
        List<OrderCostInfo> list = orderCostInfoDaoI.getOrderCostInfoList(orderInfoSeller.getOrderInfoSellerId());
        Map map = getCost(list);
        BigDecimal costMoney = new BigDecimal(map.get("costMoney") ==null? "0" : map.get("costMoney").toString());
        BigDecimal serviceMoney = new BigDecimal(map.get("serviceMoney")==null? "0":map.get("serviceMoney").toString());
        BigDecimal invoiceMoney = new BigDecimal(map.get("invoiceMoney") == null? "0": map.get("invoiceMoney").toString());

        infoBuyer.setTotalMoney(costMoney.add(serviceMoney).add(invoiceMoney));
        infoBuyer.setReceivableMoney(infoBuyer.getTotalMoney());
        infoBuyer.setBuyerServiceRate(new BigDecimal(ele.get("buyerServiceRate").toString()));
        infoBuyer.setSaleUserId(Integer.parseInt(ele.get("saleUserId").toString()));
        infoBuyer.setInvoiceRate(new BigDecimal(ele.get("sellerServiceRate").toString()));
        infoBuyer.setModifiedTime(DateUtil.parseDate(ele.get("modifiedTime").toString(),"yyyy-MM-dd HH:mm:ss"));
        infoBuyer.setCreatedTime(DateUtil.parseDate(ele.get("createdTime").toString(),"yyyy-MM-dd HH:mm:ss"));
        return infoBuyer;
    }

    /**
     * 买家订单——明细
     * @param ele
     * @return
     */
    private OrderDetail saveOrderDetail(Map ele) throws ParseException {
        OrderDetail orderDetail = new OrderDetail();
        OrderInfoSeller infoSeller =  updatOrderSeller(ele);
        orderDetail.setProduction(ele.get("production") == null? null : ele.get("production").toString());
        if(ele.get("orderTypeValue").equals(2)){
            orderDetail.setEnumProductType(EnumProductType.账号);
        }else if(ele.get("orderTypeValue").equals(3)){
            orderDetail.setEnumProductType(EnumProductType.创作者);
        }
        orderDetail.setPrice(infoSeller.getBuyerOrderPrice());
        orderDetail.setBasePrice(infoSeller.getReferPrice());
        orderDetail.setNum(1);
//        orderDetail.setOrderInfoBuyerId(theOrderInfoBuyer.getOrderInfoBuyerId());
        orderDetail.setReferId(infoSeller.getReferId());
        orderDetail.setModifiedTime(DateUtil.parseDate(ele.get("modifiedTime").toString(),"yyyy-MM-dd HH:mm:ss"));
        orderDetail.setCreatedTime(DateUtil.parseDate(ele.get("createdTime").toString(),"yyyy-MM-dd HH:mm:ss"));
        return orderDetail;
    }

    /**
     * 获取买、卖家，成本价/服务费/发票费
     * @param list
     * @return
     */
    private Map getCost(List<OrderCostInfo> list){
        Map map = new HashMap();
        if(CollectionUtil.size(list) > 0){
            for (OrderCostInfo costInfo : list){
                if(costInfo.getCostTypeIndex().compareTo(EnumCostType.非作品买家订单.getIndex()) == 0){
                    if(costInfo.getCostId() == 240){//成本
                        map.put("costMoney",costInfo.getMoney());
                    }
                    if(costInfo.getCostId() == 241){//服务费
                        map.put("serviceMoney",costInfo.getMoney());
                    }
                    if(costInfo.getCostId() == 242){//税额
                        map.put("invoiceMoney",costInfo.getMoney());
                    }
                }
                if(costInfo.getCostTypeIndex().compareTo(EnumCostType.卖家需求.getIndex()) == 0){
                    if(costInfo.getCostId() == 240){//成本
                        map.put("costSellerMoney",costInfo.getMoney());
                    }
                    if(costInfo.getCostId() == 241){//服务费
                        map.put("serviceSellerMoney",costInfo.getMoney());
                    }
                    if(costInfo.getCostId() == 242){//税额
                        map.put("invoiceSellerMoney",costInfo.getMoney());
                    }
                }
            }
        }
        return map;
    }

    /**
     * 卖家订单
     * @param ele
     * @return
     */
    private SellerOrder saveSellerOrder(Map ele) throws ParseException {
        SellerOrder sellerOrder = new SellerOrder();
        if(ele.get("orderStatusValue").equals(4)){//待执行
            sellerOrder.setEnumSellerOrderStatus(EnumSellerOrderStatus.待卖家执行);
        }else if(ele.get("orderStatusValue").equals(5)){//待确认执行
            sellerOrder.setEnumSellerOrderStatus(EnumSellerOrderStatus.待买家验收);
        }else if(ele.get("orderStatusValue").equals(6)){//已完成
            sellerOrder.setEnumSellerOrderStatus(EnumSellerOrderStatus.已完成);
        }else if(ele.get("orderStatusValue").equals(7)) {//已取消
            sellerOrder.setEnumSellerOrderStatus(EnumSellerOrderStatus.已取消);
        }
        if(ele.get("orderTypeValue").equals(2)){
            sellerOrder.setEnumOrderSellerType(EnumOrderSellerType.营销分发);
        }else if(ele.get("orderTypeValue").equals(3)){
            sellerOrder.setEnumOrderSellerType(EnumOrderSellerType.定制内容);
        }
        sellerOrder.setNum(1);
        sellerOrder.setOrderSn(ele.get("orderSn").toString());
        sellerOrder.setPrice(new BigDecimal(ele.get("sellerPrice") == null ? "0" : ele.get("sellerPrice").toString()));
        sellerOrder.setProduction(ele.get("production").toString());
        sellerOrder.setSellerServiceRate(new BigDecimal(ele.get("sellerServiceRate").toString()));

        List<OrderCostInfo> list = orderCostInfoDaoI.getOrderCostInfoList(Integer.parseInt(ele.get("orderInfoSellerId").toString()));
        Map map = getCost(list);
        BigDecimal costSellerMoney = new BigDecimal(map.get("costSellerMoney") == null ? "0" : map.get("costSellerMoney").toString());
        BigDecimal serviceSellerMoney = new BigDecimal(map.get("serviceSellerMoney") == null ? "0" : map.get("serviceSellerMoney").toString());
//        BigDecimal invoiceSellerMoney = new BigDecimal(map.get("invoiceSellerMoney").toString());
        //卖家应付金额
        sellerOrder.setPayable(costSellerMoney.subtract(serviceSellerMoney));
        sellerOrder.setRegisteredUserInfoId(Integer.parseInt(ele.get("registeredUserInfoId").toString()));
        sellerOrder.setMediaUserId(Integer.parseInt(ele.get("mediaUserId").toString()));
        sellerOrder.setStatusIndex(1);
        sellerOrder.setModifiedTime(DateUtil.parseDate(ele.get("modifiedTime").toString(),"yyyy-MM-dd HH:mm:ss"));
        sellerOrder.setCreatedTime(DateUtil.parseDate(ele.get("createdTime").toString(),"yyyy-MM-dd HH:mm:ss"));
        sellerOrder.setChannel(EnumChannel.前台创建);
        sellerOrder.setModifiedCount(1);
        return sellerOrder;
    }
}
