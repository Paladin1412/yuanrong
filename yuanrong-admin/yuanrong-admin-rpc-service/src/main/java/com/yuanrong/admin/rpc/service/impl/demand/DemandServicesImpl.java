package com.yuanrong.admin.rpc.service.impl.demand;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.base.UserInfo;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.demand.DemandAccountRelation;
import com.yuanrong.admin.bean.demand.DemandYrAuthorRelation;
import com.yuanrong.admin.bean.order.*;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.result.DemandListResult;
import com.yuanrong.admin.result.DemandOrderListResult;
import com.yuanrong.admin.result.DemandSignListResult;
import com.yuanrong.admin.result.PlatformIPAccountResult;
import com.yuanrong.admin.rpc.api.demand.DemandServicesI;
import com.yuanrong.admin.rpc.api.order.OrderInfoBuyerServicesI;
import com.yuanrong.admin.rpc.exception.YRParamterException;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.rpc.service.impl.cart.ShoppingCartServicesImpl;
import com.yuanrong.admin.rpc.service.impl.order.OrderInfoBuyerServicesImpl;
import com.yuanrong.admin.seach.CommonSearchClass;
import com.yuanrong.admin.seach.DemandListParamSearch;
import com.yuanrong.admin.seach.OrderOfferParam;
import com.yuanrong.admin.seach.PlatformIpAccountSeach;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.OrderTotalAmountRoundUtil;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.common.util.SystemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 基本需求信息的services实现类
 * Created MDA
 */
@Service
public class DemandServicesImpl extends BaseServicesAbstract<Demand> implements DemandServicesI {

    @Autowired
    private OrderInfoBuyerServicesI orderInfoBuyerServicesI;
    @Override
    public Demand getById(Integer id) {
        return demandDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        demandDaoI.deleteById(id);
    }
    @Override
    public void save(Demand object) {
        demandDaoI.save(object);
    }
    @Override
    public List<Demand> getAll() {
        return demandDaoI.getAll();
    }
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        List<Demand> resulr =  demandDaoI.list(data);
        return  new PageInfo(resulr)  ;
    }

    /**
     * 后台—需求列表查询
     * @param data
     * @param baseModel
     * @return
     */
    @Override
    public PageInfo demandList(DemandListParamSearch data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows());
        List<DemandListResult> result = demandDaoI.demandList(data);
        return new PageInfo(result);
    }

    /**
     * 查询状态个数（待审核、待推荐）
     * @return
     */
    @Override
    public List<Map<String, Object>> statusNum() {
        return demandDaoI.statusNum();
    }

    /**
     * 后台—通过需求Id获取信息
     * @param demandId
     * @return
     */
    @Override
    public Demand findById(Integer demandId) {
        return demandDaoI.findById(demandId);
    }

    /**
     * 后台—编辑修改需求
     * @param demand
     */
    @Override
    public void updateDemand(Demand demand,String userName) {
        demandDaoI.updateDemand(demand);
        systemLogDaoI.log(Demand.class.getName(),demand.getDemandId() ,"需求修改",userName);
    }

    /**
     * 后台—修改需求状态
     * @param demand
     * @param userName
     */
    @Override
    public void updateDemandStatus(Demand demand,String userName) {
        Demand demandOld = getById(demand.getDemandId());
        if(demand.getDemandStatusIndex() == EnumDemandStatus.处理中.getIndex()
                || demand.getDemandStatusIndex() == EnumDemandStatus.已取消.getIndex()){//审核、取消
            if (demandOld.getDemandStatusIndex() == EnumDemandStatus.待审核.getIndex()){//在已创建的状态下进行审核、取消、拒绝
                //审核通过—判断需求是否有账号/创作者【生成报名记录】
                if(demand.getDemandStatusIndex() == EnumDemandStatus.处理中.getIndex()){
                    if(demandOld.getDemandTypeIndex().compareTo(EnumDemandType.ip代理.getIndex()) != 0
                            && demandOld.getDemandTypeIndex().compareTo(EnumDemandType.原创征稿.getIndex()) != 0 ){
                        List<Map<String, Object>> mapList = demandDaoI.getDemandGoods(demand.getDemandId(), demandOld.getDemandTypeIndex());
                        if(CollectionUtil.size(mapList) >0){
                            if (demandOld.getDemandTypeIndex() == EnumDemandType.营销分发.getIndex()){
                                for (Map<String , Object> map : mapList){
                                    //添加报名信息
                                    saveAccountSignRecord(demand.getDemandId(),Integer.valueOf(map.get("id").toString()));
                                }
                            }else if (demandOld.getDemandTypeIndex() == EnumDemandType.定制内容.getIndex()){
                                for (Map<String , Object> map : mapList){
                                    //添加报名信息
                                    saveAuthorSignRecord(demand.getDemandId(),Integer.valueOf(map.get("recId").toString()));
                                }
                            }
                        }
                    }else if(demandOld.getDemandTypeIndex() == EnumDemandType.ip代理.getIndex()){//ip代理已完成
                        demand.setDemandStatusIndex(EnumDemandStatus.已完成.getIndex());
                    }
                }
                demandDaoI.updateDemandStatus(demand);
                systemLogDaoI.log(Demand.class.getName(),demand.getDemandId() ,"将需求状态修改为"+ demand.getEnumDemandStatus().getName(),userName);
            }
        }else if(demand.getDemandStatusIndex().compareTo(EnumDemandStatus.待推荐.getIndex()) == 0){//待推荐
            if(demandOld.getDemandStatusIndex().compareTo(EnumDemandStatus.处理中.getIndex()) == 0){
                demandDaoI.updateDemandStatus(demand);
                systemLogDaoI.log(Demand.class.getName(),demand.getDemandId() ,"将需求状态修改为"+ demand.getEnumDemandStatus().getName(),userName);
            }
        }else if(demand.getDemandStatusIndex().compareTo(EnumDemandStatus.完成推荐.getIndex()) == 0){//完成推荐
            if(demandOld.getDemandStatusIndex().compareTo(EnumDemandStatus.待推荐.getIndex()) == 0){
                demandDaoI.updateDemandStatus(demand);
                systemLogDaoI.log(Demand.class.getName(),demand.getDemandId() ,"将需求状态修改为"+ demand.getEnumDemandStatus().getName(),userName);
            }
        }else if(demand.getDemandStatusIndex().compareTo(EnumDemandStatus.已完成.getIndex()) == 0){//已完成
            if(demandOld.getDemandStatusIndex().compareTo(EnumDemandStatus.处理中.getIndex()) == 0
                    || demandOld.getDemandStatusIndex().compareTo(EnumDemandStatus.待推荐.getIndex()) == 0
                    || demandOld.getDemandStatusIndex().compareTo(EnumDemandStatus.完成推荐.getIndex()) == 0){
                demandDaoI.updateDemandStatus(demand);
                //获取报名列表通过需求Id
                List<OrderInfoSeller> infoSellerList = orderInfoSellerDaoI.getOrderSellerByDemandId(demand.getDemandId());
                if(CollectionUtil.size(infoSellerList) > 0){
                    for (OrderInfoSeller infoSeller : infoSellerList){
                        //修改报名列表状态(待确认使用———改为买家拒绝)
                        if(infoSeller.getOrderStatusValue().compareTo(EnumOrderSellerStatus.待买家确认.getIndex()) == 0){
                            infoSeller.setOrderStatusValue(EnumOrderSellerStatus.买家拒绝.getIndex());
                            infoSeller.setRefuseReason("买家拒绝");
                            orderInfoSellerDaoI.updateOrderSeller(infoSeller);
                        }
                        //通过报名ID获取买家订单和卖家订单
                        List<OrderInfoBuyer> infoBuyerList = orderInfoBuyerDaoI.getByOrderSellerId(infoSeller.getOrderInfoSellerId());
                        //修改订单状态(待买家验收———改为已完成)
                        if(CollectionUtil.size(infoBuyerList) >0){
                            for (OrderInfoBuyer infoBuyer : infoBuyerList){
                                if(infoBuyer != null && infoBuyer.getOrderStatusValue().compareTo(EnumOrderBuyerStatus.待买家验收.getIndex()) == 0){
                                    //修改买家订单—已完成
                                    infoBuyer.setOrderStatusValue(EnumOrderBuyerStatus.已完成.getIndex());
                                    orderInfoBuyerDaoI.updateBuyerOrder(infoBuyer);
                                    //修改卖家订单——已完成
                                    SellerOrder sellerOrder = new SellerOrder();
                                    sellerOrder.setOrderStatus(EnumSellerOrderStatus.已完成.getIndex());
                                    sellerOrder.setSellerOrderId(infoBuyer.getSellerOrder().getSellerOrderId());
                                    sellerOrder.setFinishTime(new Date());
                                    sellerOrderDaoI.updateSeller(sellerOrder);
                                    //订单是营销分发的话——添加交易记录
                                    if(infoBuyer.getOrderInfoType().compareTo(EnumOrderSellerType.营销分发.getIndex()) == 0){
                                        orderInfoBuyerServicesI.saveTradingRecord(infoBuyer);
                                    }
                                    //订单完成—添加卖家用户余额
                                    orderInfoBuyerServicesI.saveUserBalanceDetails(infoBuyer);
                                }
                            }
                        }

                    }
                }
                systemLogDaoI.log(Demand.class.getName(),demand.getDemandId() ,"将需求状态修改为"+ demand.getEnumDemandStatus().getName(),userName);
            }
        }
    }

    @Override
    public int saveGetKey(Demand demand,String userName) {
        demandDaoI.save(demand);
        systemLogDaoI.log(Demand.class.getName(),demand.getDemandId() ,"新增需求",userName);
        return demand.getDemandId();
    }

    @Override
    public void insertBatchDemandAccountRelation(int demandId,String ids) {
        List<DemandAccountRelation>  demandAccountList = new ArrayList<DemandAccountRelation>();
        String [] accountIPStr = ids.split(",");
        for(int i=0; i<accountIPStr.length;i++){
            DemandAccountRelation demandAccountRelation = new DemandAccountRelation();
            demandAccountRelation.setDemandId(demandId);
            demandAccountRelation.setPlatformIPAccountId(Integer.parseInt(accountIPStr[i]));
            demandAccountList.add(demandAccountRelation);
        }
            demandDaoI.insertBatchDemandAccountRelation(demandAccountList);

    }

    @Override
    public void insertBatchDemandYrAuthorRelation(int demandId, String yrAuthotIds) {
        List<DemandYrAuthorRelation> demandYrAuthorRelationList = new ArrayList<DemandYrAuthorRelation>();
        String [] yrAuthotIdsArray = yrAuthotIds.split(",");
        for(int i=0; i<yrAuthotIdsArray.length;i++){
            DemandYrAuthorRelation demandYrAuthorRelation = new DemandYrAuthorRelation();
            demandYrAuthorRelation.setDemandId(demandId);
            demandYrAuthorRelation.setYrAuthorId(Integer.parseInt(yrAuthotIdsArray[i]));
            demandYrAuthorRelationList.add(demandYrAuthorRelation);
        }
        demandDaoI.insertBatchDemandYrAuthorRelation(demandYrAuthorRelationList);
    }

    /**
    *  根据需求号查询需求详情带订单.买家中心
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/7/6 11:52
    */
    @Override
    public Demand getDetailByDemand(Demand demand) {
        Demand demandSelected = demandDaoI.getDetailByDemand(demand);
        if (demandSelected == null) {
            throw new YRParamterException("数据库无此需求id的数据");
        }
        return demandSelected;
    }

    /**
     * 后台—需求推荐(营销分发/定制内容)
     * @param orderOfferParam
     */
    @Override
    public void saveDemandOrderAndSnap(OrderOfferParam orderOfferParam) {
        Demand demand = demandDaoI.getById(orderOfferParam.getDemandId());
        //添加订单信息
        OrderInfoSeller data =saveOrderInfoSeller(orderOfferParam.getDemandId(),orderOfferParam.getProductId());
        data.setAcceptRemark(orderOfferParam.getAcceptRemark());//应约备注
        data.setUsableDate(orderOfferParam.getUsableDate());//可用排期、创作耗时
        data.setResponseTime(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));//响应时间
        data.setOrderStatusValue(EnumOrderSellerStatus.待买家确认.getIndex());//订单状态
        orderInfoSellerDaoI.save(data);
        //添加需求关联关系（账号/创作者）
        if(demand.getDemandTypeIndex() == EnumDemandType.定制内容.getIndex()){
            insertBatchDemandYrAuthorRelation(orderOfferParam.getDemandId(),orderOfferParam.getProductId().toString());
        }else if(demand.getDemandTypeIndex() == EnumDemandType.营销分发.getIndex()) {
            insertBatchDemandAccountRelation(orderOfferParam.getDemandId(),orderOfferParam.getProductId().toString());
        }
        //添加报价信息
        orderOfferParam.setOrderInfoSellerId(data.getOrderInfoSellerId());
        orderInfoSellerDaoI.insertOrderInfoOffer(orderInfoOfferList(orderOfferParam));
        //添加快照信息
        if(demand.getDemandTypeIndex() == EnumDemandType.定制内容.getIndex()){
            saveSnapshotYrAuthor(data.getOrderInfoSellerId(),orderOfferParam.getProductId());
        }else if(demand.getDemandTypeIndex() == EnumDemandType.营销分发.getIndex()) {
            saveSnapshotAccount(data.getOrderInfoSellerId(), orderOfferParam.getProductId());
        }
    }

    /**
     * 订单信息封装
     * @param demandId
     * @param goodsId
     */
    public OrderInfoSeller saveOrderInfoSeller(Integer demandId, Integer goodsId){
        OrderInfoSeller data = new OrderInfoSeller();
        Demand demand = demandDaoI.getById(demandId);
        data.setDemandId(demandId);//需求id
        data.setOrderSn(orderSnFactoryServicesI.createSellerOrderSn());//报名单号
        data.setSaleUser(adminUserDaoI.getById(registeredUserInfoDAO.getById(demand.getRegisteredUserInfoId()).getAdminUserSalesID()).getRealName());//买家对应销售经理
        data.setReferId(goodsId);//商品id(作品、创作者、账号)
        if(demand.getDemandTypeIndex() == EnumDemandType.营销分发.getIndex()){//营销分发
            PlatformIPAccount account =  platformIPAccountDaoI.getById(goodsId);//获取账号id
            data.setRegisteredUserInfoId(account.getRegisteredUserInfoID());//卖家用户id
            RegisteredUserInfo userInfo = registeredUserInfoDAO.getById(account.getRegisteredUserInfoID());
            if ( userInfo != null && userInfo.getAdminUserMediaID() != null) {
                data.setMediaUser(adminUserDaoI.getById(userInfo.getAdminUserMediaID()).getRealName());//卖家对应媒介经理
            }
            data.setProduction(account.getName());//商品名称
            data.setOrderTypeValue(EnumOrderSellerType.营销分发.getIndex());//报名单类型
            String servicFeeRate = configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_BUYER_ACCOUNT);
            data.setBuyerServiceRate(new BigDecimal(servicFeeRate));//买家服务费率
            data.setBuyerServiceRate(new BigDecimal(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_SELLER_ACCOUNT)));//买家服务费率
        }else if(demand.getDemandTypeIndex() == EnumDemandType.定制内容.getIndex()){//定制内容
            YRAuthor yrAuthor = yRAuthorDaoI.getById(goodsId);
            data.setRegisteredUserInfoId(yrAuthor.getRegisteredUserInfoID());
            RegisteredUserInfo userInfo = registeredUserInfoDAO.getById(yrAuthor.getRegisteredUserInfoID());
            if ( userInfo != null && userInfo.getAdminUserMediaID() != null) {
                data.setMediaUser(adminUserDaoI.getById(userInfo.getAdminUserMediaID()).getRealName());//卖家对应媒介经理
            }
            data.setProduction(yrAuthor.getAuthorNickname());//商品名称
            data.setPrice(yrAuthor.getCreatedPrice());//卖家订单成本价
            data.setOrderTypeValue(EnumOrderSellerType.定制内容.getIndex());//报名单类型
            String servicFeeRate = configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_BUYER_AUTHOR);
            data.setBuyerServiceRate(new BigDecimal(servicFeeRate));//买家服务费率
            data.setSellerServiceRate(new BigDecimal(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_SELLER_AUTHOR)));
        }else if(demand.getDemandTypeIndex().compareTo(EnumDemandType.原创征稿.getIndex()) == 0){//原创征稿
            YRProduction yrProduction = yRProductionDaoI.getById(goodsId);
            data.setRegisteredUserInfoId(yrProduction.getRegisteredUserInfoId());
            data.setProduction(yrProduction.getTitle());//商品名称
            data.setPrice(yrProduction.getProductQuotedPrice());
            RegisteredUserInfo userInfo = registeredUserInfoDAO.getById(yrProduction.getRegisteredUserInfoId());
            if ( userInfo != null && userInfo.getAdminUserMediaID() != null) {
                data.setMediaUser(adminUserDaoI.getById(userInfo.getAdminUserMediaID()).getRealName());//卖家对应媒介经理
            }
            data.setOrderTypeValue(EnumOrderSellerType.原创征稿.getIndex());//报名单类型
            String servicFeeRate = configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_BUYER_COLLECTION);
            data.setBuyerServiceRate(new BigDecimal(servicFeeRate));//买家服务费率
            BigDecimal servicrFee = yrProduction.getProductQuotedPrice().multiply(new BigDecimal(servicFeeRate));
            data.setReferPrice(yrProduction.getProductQuotedPrice().add(servicrFee));//报名价
        }
        return data;
    }

    /**
     * 关于卖家报价数据的封装
     * @param offerParam
     * @return
     */
    public List orderInfoOfferList(OrderOfferParam offerParam){
        //新增价格项数据
        List<OrderInfoOffer> offerList =  new ArrayList<OrderInfoOffer>();
        String[] priceNames = offerParam.getPriceNames();
        String[] prices = offerParam.getPrices();
        for (int i=0; i < priceNames.length; i++){
            OrderInfoOffer infoOffer = new OrderInfoOffer();
            infoOffer.setOrderInfoSeller(offerParam.getOrderInfoSellerId());
            infoOffer.setPrice(new BigDecimal(prices[i]));
            infoOffer.setPriceName(priceNames[i]);
            infoOffer.setExecutePrice(OrderTotalAmountRoundUtil.getRoundAmount(new BigDecimal(prices[i]).add(new BigDecimal(prices[i]).multiply(offerParam.getBuyerServiceRate().divide(new BigDecimal("100"))))));
            infoOffer.setSellerPrice(new BigDecimal(prices[i]));
            offerList.add(infoOffer);
        }
        return offerList;
    }

    /**
     * 新增创作者快照信息
     * @param orderInfoSellerId
     * @param yrAuthorId
     */
    public void saveSnapshotYrAuthor(Integer orderInfoSellerId,Integer yrAuthorId){
        SnapshotYrAuthor snapshotYrAuthor = new SnapshotYrAuthor();
//        snapshotYrAuthor.setOrderInfoSellerId(orderInfoSellerId);
        snapshotYrAuthor.setYrAuthorId(yrAuthorId);
        snapshotYrAuthorDaoI.save(snapshotYrAuthor);
    }

    /**
     * 新增账号快照信息
     * @param orderInfoSellerId
     * @param accountId
     */
    public void saveSnapshotAccount(Integer orderInfoSellerId,Integer accountId){
        SnapshotAccount snapshotAccount = new SnapshotAccount();
        snapshotAccount.setPlatformIPAccountId(accountId);
        snapshotAccount.setOrderInfoSellerId(orderInfoSellerId);
        int[] ipAccountIds = {accountId};
        List<Map<String,Object>> prices = platformIPAccountPriceDaoI.getGroupPriceByIPAccountIDs(ipAccountIds , 13);
        if(prices !=null && prices.size() > 0){
            for(Map<String,Object> ele : prices){
                if(accountId.toString().equals(ele.get("iPAcctountID").toString())){
                    //账号报价
                    String infos = (String) ele.get("info");
                    if(StringUtil.isNoneBlank(infos)){
                        snapshotAccount.setPriceInfo(PlatformIPAccountResult.getJSONObjectByStringPrice(infos).toJSONString());
                    }
                }
            }
        }
        snapshotAccountDaoI.save(snapshotAccount);
    }

    /**
     * 后台—获取需求的选购商品(前台选购的创作者/账号)
     * @param demandId
     * @param demandTypeIndex
     * @return
     */
    @Override
    public List<Map<String, Object>> getDemandGoods(Integer demandId, Integer demandTypeIndex) {
        List<Map<String, Object>> mapList = demandDaoI.getDemandGoods(demandId, demandTypeIndex);
        if (CollectionUtil.size(mapList) > 0){
            if(demandTypeIndex == EnumDemandType.营销分发.getIndex()){
                int[] ipAccountIds = new int[mapList.size()];
                int index = 0 ;
                for(Map<String,Object> map : mapList){
                    ipAccountIds[index++] = Integer.parseInt(map.get("id").toString());
                }
                List<Map<String,Object>> prices = platformIPAccountPriceDaoI.getGroupPriceByIPAccountIDs(ipAccountIds , 13);
                if(prices !=null && prices.size() > 0){
                    for(Map<String,Object> ele : prices){
                        for(Map<String,Object> map : mapList){
                            if(map.get("id").toString().equals(ele.get("iPAcctountID").toString())){
                                //账号报价
                                String infos = (String) ele.get("info");
                                if(StringUtil.isNoneBlank(infos)){
                                    map.put("pricesInfo" , PlatformIPAccount.getJSONArraybyStringPrice(infos));
                                }
                            }
                        }
                    }
                }
            }
        }
        return mapList;
    }

    /**
     * 后台—通过需求Id获取账号订单列表—账号/创作者
     * @param demandId
     * @param baseModel
     * @return
     */
    @Override
    public List<DemandOrderListResult> getOrderListByDemandId(Integer demandId,BaseModel baseModel) {
//        PageHelper.startPage(baseModel.getCp() , baseModel.getRows());
        Demand demand = demandDaoI.getById(demandId);
        Integer orderType =0;
        if(demand.getDemandTypeIndex().compareTo(EnumDemandType.原创征稿.getIndex()) == 0){
            orderType = EnumOrderSellerType.原创征稿.getIndex();
        }else if(demand.getDemandTypeIndex().compareTo(EnumDemandType.营销分发.getIndex()) == 0){
            orderType = EnumOrderSellerType.营销分发.getIndex();
        }else if (demand.getDemandTypeIndex().compareTo(EnumDemandType.定制内容.getIndex()) == 0){
            orderType = EnumOrderSellerType.定制内容.getIndex();
        }
        List<DemandOrderListResult> orderInfo = demandDaoI.getOrderListByDemandId(demandId , orderType);
        if(CollectionUtil.size(orderInfo) > 0){
            for (DemandOrderListResult infoSeller : orderInfo){
                //查询确认信息
                infoSeller.setCostInfoList(orderInfoSellerDaoI.getCostInfoList(infoSeller.getOrderInfoBuyerId()));
            }
        }
        return orderInfo;
    }

    @Override
    public void batchSave(Demand demand, String authorIds, String accountIPIds, String shopCartIds,UserInfo userInfo) {
        //通过shopCartIds获取authorIds、accountIPIds 定制内容
        if( demand.getDemandTypeIndex()==1 && StringUtil.isNotBlank(shopCartIds) && userInfo !=null){
            List<YRAuthor> list = yRAuthorDaoI.getyrAuthorByshoppingCartId(userInfo.getRecID(),shopCartIds);
            String yrAuthorIds = "";
            if(list.size()>0){
                for( YRAuthor yrAuthor : list){
                    yrAuthorIds += yrAuthor.getRecId()+",";
                }
                yrAuthorIds = yrAuthorIds.substring(0,yrAuthorIds.length()-1);
                authorIds = yrAuthorIds;
            }
        }
        //通过shopCartIds获取accountIPIds 营销分发
        if( demand.getDemandTypeIndex()==3 && StringUtil.isNotBlank(shopCartIds) && userInfo !=null){
            List<PlatformIPAccount> list =  platformIPAccountDaoI.list_PlatformIPAccountByShopCartIds(userInfo.getRecID(),shopCartIds);
            String ipAccountIds = "";
            if(list.size()>0) {
                for (PlatformIPAccount platformIPAccount : list) {
                    ipAccountIds += platformIPAccount.getId() + ",";
                }
                ipAccountIds = ipAccountIds.substring(0, ipAccountIds.length() - 1);
                accountIPIds = ipAccountIds;
            }
        }
        //发布需求，添加到数据中,并且返回主键Id
        int demandId = saveGetKey(demand,userInfo == null ? demand.getMobile() : userInfo.getUserName());
        //购物车选中IPAccount批量插入 需求 - IPAccount的中间表；
        if(accountIPIds!=null && userInfo!=null){
            insertBatchDemandAccountRelation(demandId,accountIPIds);
        }
        // 购物车选中创者者批量插入 需求 - 创作者的中间表；
        if(authorIds!=null && userInfo!=null){
            insertBatchDemandYrAuthorRelation(demandId,authorIds);
        }
        //通过购物车产生的需求订单，生成需求订单时，把购物车清除。
        if(shopCartIds != null && userInfo!=null){
            Integer[] shoppingCartidsArray = null;
            String [] shoppingCartidsArray_Str = shopCartIds.split(",");
            shoppingCartidsArray = new Integer[shoppingCartidsArray_Str.length];
            for (int i=0; i<shoppingCartidsArray_Str.length; i++){
                shoppingCartidsArray[i] = Integer.parseInt( shoppingCartidsArray_Str[i]);
            }
            shoppingCartDaoI.batchDeleteByIds(shoppingCartidsArray,userInfo.getRecID() );
        }
        systemLogDaoI.log(Demand.class.getName(),demandId,"创建需求",demand.getMobile());
    }
    /**
     * 前台需求与创作者，账号关联表
    * @Description:
    * @Author:         ShiLinghuai
    * @CreateDate:     2018/8/13 15:07
    * @UpdateUser:     ShiLinghuai
    * @UpdateDate:     2018/8/13 15:07
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    @Override
    public List<Map<String, Object>> getDemandGoodsFront(Integer demandId, Integer demandTypeIndex) {
        List<Map<String, Object>> mapList = demandDaoI.getDemandGoodsFront(demandId, demandTypeIndex);
        if (CollectionUtil.size(mapList) > 0){
            if(demandTypeIndex == EnumDemandType.营销分发.getIndex()){
                int[] ipAccountIds = new int[mapList.size()];
                int index = 0 ;
                for(Map<String,Object> map : mapList){
                    ipAccountIds[index++] = Integer.parseInt(map.get("id").toString());
                }
                List<Map<String,Object>> prices = platformIPAccountPriceDaoI.getGroupPriceByIPAccountIDs(ipAccountIds , 13);
                if(prices !=null && prices.size() > 0){
                    for(Map<String,Object> ele : prices){
                        for(Map<String,Object> map : mapList){
                            if(map.get("id").toString().equals(ele.get("iPAcctountID").toString())){
                                //账号报价
                                String infos = (String) ele.get("info");
                                if(StringUtil.isNoneBlank(infos)){
                                    map.put("priceInfo" , PlatformIPAccountResult.getJSONObjectByStringPrice(infos));
                                }
                            }
                        }
                    }
                }
            }
        }
        return mapList;
    }

    /**
     * 需求选购账号
     * @param accountSeach
     * @param baseModel
     * @return
     */
    @Override
    public PageInfo<Map> getDemandAccount(PlatformIpAccountSeach accountSeach, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "pfi.accountID,pfi.name,pfi.id desc");
        List<Map> result = platformIPAccountDaoI.list(accountSeach);
        if(result.size() > 0){
            int[] ipAccountIds = new int[result.size()];
            int index = 0 ;
            for(Map<String,Object> map : result){
                ipAccountIds[index++] = Integer.parseInt(String.valueOf(map.get("id")));
            }
            List<Map<String,Object>> prices = platformIPAccountPriceDaoI.getGroupPriceByIPAccountIDs(ipAccountIds , accountSeach.getPlatformPrice());
            if(prices.size() > 0){
                for(Map<String,Object> ele : prices){
                    for(Map<String,Object> map : result){
                        if(map.get("id").toString().equals(ele.get("iPAcctountID").toString())){
                            map.put("info" , ele.get("info"));
                        }
                    }
                }
            }
        }
        return new PageInfo(result);
    }

    /**
     * 删除需求—假删
     * @param demandId
     */
    @Override
    public void deleteDemand(Integer demandId) {
        demandDaoI.deleteDemand(demandId);
    }

    @Override
    public PageInfo getDemandHall(Demand data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows());
        List<Demand> result =  demandDaoI.getDemandHall(data);
        return new PageInfo(result);
    }

    @Override
    public Demand getByDemandSn(String demandSn) {
        return demandDaoI.getByDemandSn(demandSn);
    }

    /**
     * 后台—获取需求的报名记录
     * @param demandId
     * @param orderType
     * @param baseModel
     * @return
     */
    @Override
    public List<DemandSignListResult> getDemandSignList(Integer demandId, Integer orderType, BaseModel baseModel) {
//        PageHelper.startPage(baseModel.getCp() , baseModel.getRows());
        List<DemandSignListResult> signList = demandDaoI.getDemandSignList(demandId , orderType);
        return signList;
    }

    /**
     * 后台—保存报名记录
     * @param orderOfferParam
     * @param userName
     */
    @Override
    public void saveSignRecord(OrderOfferParam orderOfferParam,String userName) {
        Demand demand = demandDaoI.getById(orderOfferParam.getDemandId());
        //添加报名信息
        if(demand.getDemandTypeIndex().compareTo(EnumDemandType.原创征稿.getIndex()) == 0){//原创征稿
            saveProSignRecord(orderOfferParam.getDemandId(),orderOfferParam.getProductId());
        }else if(demand.getDemandTypeIndex() == EnumDemandType.营销分发.getIndex()){//营销分发
            OrderInfoSeller data = saveAccountSignRecord(orderOfferParam.getDemandId(),orderOfferParam.getProductId(),orderOfferParam.getAcceptRemark(),orderOfferParam.getUsableDate(),false);
            //添加需求关联关系（账号/创作者）
            insertBatchDemandAccountRelation(orderOfferParam.getDemandId(),orderOfferParam.getProductId().toString());
            //添加报价信息
            orderOfferParam.setOrderInfoSellerId(data.getOrderInfoSellerId());
            orderOfferParam.setBuyerServiceRate(new BigDecimal(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_BUYER_ACCOUNT)));
            orderInfoSellerDaoI.insertOrderInfoOffer(orderInfoOfferList(orderOfferParam));
        }else if(demand.getDemandTypeIndex() == EnumDemandType.定制内容.getIndex()){//定制内容
            OrderInfoSeller data = saveAuthorSignRecord(orderOfferParam.getDemandId(),orderOfferParam.getProductId(),orderOfferParam.getAcceptRemark(),orderOfferParam.getUsableDate(),false);
            //添加需求关联关系（账号/创作者）
            insertBatchDemandYrAuthorRelation(orderOfferParam.getDemandId(),orderOfferParam.getProductId().toString());
            //添加报价信息
            orderOfferParam.setOrderInfoSellerId(data.getOrderInfoSellerId());
            orderOfferParam.setBuyerServiceRate(new BigDecimal(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_BUYER_AUTHOR)));
            orderInfoSellerDaoI.insertOrderInfoOffer(orderInfoOfferList(orderOfferParam));
        }
        systemLogDaoI.log(Demand.class.getName(),orderOfferParam.getDemandId(),"用户报名/投稿",userName);
    }

    /**
     * 后台—保存原创征稿报名信息
     * @param demandId
     * @param goodsId
     * @return
     */
    public void saveProSignRecord(Integer demandId,Integer goodsId){
        OrderInfoSeller data = new OrderInfoSeller();
        Demand demand = demandDaoI.getById(demandId);
        data.setDemandId(demandId);//需求id
        data.setOrderSn(orderSnFactoryServicesI.createSellerOrderSn());//报名单号
        data.setSaleUserId(registeredUserInfoDAO.getById(demand.getRegisteredUserInfoId()).getAdminUserSalesID());//买家对应销售经理
        data.setReferId(goodsId);//商品id(作品、创作者、账号)
        YRProduction yrProduction = yRProductionDaoI.getById(goodsId);
        data.setRegisteredUserInfoId(yrProduction.getRegisteredUserInfoId());
        data.setProduction(yrProduction.getTitle());//商品名称
        data.setSellerPrice(demand.getBudgetMoney());//卖家应约价
        RegisteredUserInfo userInfo = registeredUserInfoDAO.getById(yrProduction.getRegisteredUserInfoId());
        if ( userInfo != null && userInfo.getAdminUserMediaID() != null) {
            data.setMediaUserId(userInfo.getAdminUserMediaID());//卖家对应媒介经理
        }
        data.setOrderTypeValue(EnumOrderSellerType.原创征稿.getIndex());//报名单类型
        String servicFeeRate = configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_BUYER_COLLECTION);
        data.setBuyerServiceRate(new BigDecimal(servicFeeRate));//买家服务费率
        data.setSellerServiceRate(new BigDecimal(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_SELLER_COLLECTION)));//卖家服务费率
        data.setInvoiceRate(new BigDecimal(configurationDaoI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_COLLECTION)));//买家发票费
        data.setReferPrice(demand.getBudgetMoney());//报名价
        data.setBuyerOrderPrice(OrderTotalAmountRoundUtil.getRoundAmount(data.getReferPrice().add(data.getReferPrice().multiply(new BigDecimal(servicFeeRate).divide(new BigDecimal("100"))))));//买家订单金额
        data.setResponseTime(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));//响应时间
        data.setOrderStatusValue(EnumOrderSellerStatus.待买家确认.getIndex());//报名状态
        orderInfoSellerDaoI.save(data);
    }

    /**
     * 后台—保存定制内容报名信息
     * @param demandId
     * @param goodsId
     * @param acceptRemark
     * @param usableDate
     * @param flag
     */
    public OrderInfoSeller saveAuthorSignRecord(Integer demandId,Integer goodsId,String acceptRemark, String usableDate, boolean flag){
        OrderInfoSeller data = new OrderInfoSeller();
        Demand demand = demandDaoI.getById(demandId);
        data.setDemandId(demandId);//需求id
        data.setOrderSn(orderSnFactoryServicesI.createSellerOrderSn());//报名单号
        data.setSaleUserId(registeredUserInfoDAO.getById(demand.getRegisteredUserInfoId()).getAdminUserSalesID());//买家对应销售经理
        data.setReferId(goodsId);//商品id(作品、创作者、账号)
        YRAuthor yrAuthor = yRAuthorDaoI.getById(goodsId);
        data.setRegisteredUserInfoId(yrAuthor.getRegisteredUserInfoID());
        RegisteredUserInfo userInfo = registeredUserInfoDAO.getById(yrAuthor.getRegisteredUserInfoID());
        if ( userInfo != null && userInfo.getAdminUserMediaID() != null) {
            data.setMediaUserId(userInfo.getAdminUserMediaID());//卖家对应媒介经理
        }
        data.setProduction(yrAuthor.getAuthorNickname());//商品名称
        data.setSellerPrice(yrAuthor.getCreatedPrice());//卖家_成本价
        data.setOrderTypeValue(EnumOrderSellerType.定制内容.getIndex());//报名单类型
        String servicFeeRate = configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_BUYER_AUTHOR);
        data.setBuyerServiceRate(new BigDecimal(servicFeeRate));//买家服务费率
        data.setSellerServiceRate(new BigDecimal(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_SELLER_AUTHOR)));//卖家服务费率
        data.setInvoiceRate(new BigDecimal(configurationDaoI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_AUTHOR)));//买家税率
        if (StringUtil.isNotBlank(acceptRemark)){
            data.setAcceptRemark(acceptRemark);//应约备注
        }
        if (StringUtil.isNotBlank(usableDate)){
            data.setUsableDate(usableDate);//创作耗时
        }
        data.setResponseTime(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));//响应时间
        if(flag){
            data.setOrderStatusValue(EnumOrderSellerStatus.待卖家响应.getIndex());//报名状态
        }else {
            data.setOrderStatusValue(EnumOrderSellerStatus.待买家确认.getIndex());//报名状态
        }
        orderInfoSellerDaoI.save(data);
        return data;
    }


    /**
     * 后台—保存定制内容报名信息—待卖家响应
     * @param demandId
     * @param goodsId
     */
    public OrderInfoSeller saveAuthorSignRecord(Integer demandId,Integer goodsId){
        return saveAuthorSignRecord(demandId,goodsId,null,null,true);
    }

    /**
     * 后台—保存营销分发报名信息
     * @param demandId
     * @param goodsId
     * @param acceptRemark
     * @param usableDate
     * @param flag
     */
    public OrderInfoSeller saveAccountSignRecord(Integer demandId,Integer goodsId,String acceptRemark, String usableDate,boolean flag){
        OrderInfoSeller data = new OrderInfoSeller();
        Demand demand = demandDaoI.getById(demandId);
        data.setDemandId(demandId);//需求id
        data.setOrderSn(orderSnFactoryServicesI.createSellerOrderSn());//报名单号
//        data.setSaleUser(adminUserDaoI.getById(registeredUserInfoDAO.getById(demand.getRegisteredUserInfoId()).getAdminUserSalesID()).getRealName());//买家对应销售经理
        data.setSaleUserId(registeredUserInfoDAO.getById(demand.getRegisteredUserInfoId()).getAdminUserSalesID());//买家对应销售经理Id
        data.setReferId(goodsId);//商品id(作品、创作者、账号)
        PlatformIPAccount account =  platformIPAccountDaoI.getById(goodsId);//获取账号id
        data.setRegisteredUserInfoId(account.getRegisteredUserInfoID());//卖家用户id
        RegisteredUserInfo userInfo = registeredUserInfoDAO.getById(account.getRegisteredUserInfoID());
        if ( userInfo != null && userInfo.getAdminUserMediaID() != null) {
            data.setMediaUserId(userInfo.getAdminUserMediaID());//卖家对应媒介经理
        }
        data.setProduction(account.getName());//商品名称
        data.setOrderTypeValue(EnumOrderSellerType.营销分发.getIndex());//报名单类型
        String servicFeeRate = configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_BUYER_ACCOUNT);
        data.setBuyerServiceRate(new BigDecimal(servicFeeRate));//买家服务费率
        data.setSellerServiceRate(new BigDecimal(configurationDaoI.getbyKey(SystemParam.SERVICES_FEE_SELLER_ACCOUNT)));//卖家服务费率
        data.setInvoiceRate(new BigDecimal(configurationDaoI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_ACCOUNT)));//买家税率
        data.setAcceptRemark(acceptRemark);//应约备注
        data.setUsableDate(usableDate);//创作耗时
        data.setResponseTime(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));//响应时间
        if(flag){
            data.setOrderStatusValue(EnumOrderSellerStatus.待卖家响应.getIndex());//报名状态
        }else {
            data.setOrderStatusValue(EnumOrderSellerStatus.待买家确认.getIndex());//报名状态
        }
        orderInfoSellerDaoI.save(data);
        return data;
    }


    /**
     * 后台—保存营销分发报名信息—待卖家响应
     * @param demandId
     * @param goodsId
     */
    public OrderInfoSeller saveAccountSignRecord(Integer demandId,Integer goodsId){
        return saveAccountSignRecord(demandId,goodsId,null,null,true);
    }
    /**
     * 确认使用生成订单——定制内容、营销分发
     * @param orderOfferParam
     * @param userName
     */
    @Override
    public void saveConfirmUseOk(OrderOfferParam orderOfferParam,String userName) {
        OrderInfoOffer infoOffer = orderInfoOfferDaoI.getById(orderOfferParam.getOrderInfoOfferId());
        OrderInfoSeller orderInfoSeller = orderInfoSellerDaoI.getById(infoOffer.getOrderInfoSeller());
        orderInfoSeller.setExecuteTime(orderOfferParam.getExecuteTime());
        orderInfoSeller.setProduction(infoOffer.getPriceName());
        orderInfoSeller.setBuyerOrderPrice(infoOffer.getExecutePrice());
        orderInfoSeller.setSellerPrice(infoOffer.getSellerPrice());
        orderInfoSeller.setReferPrice(infoOffer.getPrice());
        orderInfoSeller.setOrderStatusValue(EnumOrderSellerStatus.已生成订单.getIndex());
        orderInfoSeller.setConfirmTime(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        orderInfoBuyerServicesI.savePlaceTheDemandOrder(orderInfoSeller);
        orderInfoSellerDaoI.updateOrderSeller(orderInfoSeller);
        systemLogDaoI.log(OrderInfoSeller.class.getName(),infoOffer.getOrderInfoSeller() ,"用户确认使用，生成订单",userName);
    }

    @Override
    public PageInfo<OrderInfoBuyer> getOrderList(Demand demand, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp(), baseModel.getRows(),"ob.orderInfoBuyerId desc");
        List<OrderInfoBuyer> orderInfoBuyers = orderInfoBuyerDaoI.getByEntity(demand);
        for (OrderInfoBuyer orderInfoBuyer: orderInfoBuyers
             ) {
            orderInfoBuyer.setOrderCostInfoList(orderInfoSellerDaoI.getCostInfoList(orderInfoBuyer.getOrderInfoBuyerId()));
        }
        return new PageInfo<>(orderInfoBuyers);
    }

    @Override
    public PageInfo<OrderInfoSeller> getApplyList(Demand demand, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp(), baseModel.getRows(),"os.orderInfoSellerId desc");
        List<OrderInfoSeller> orderInfoSellers = orderInfoSellerDaoI.getAllOrderSellerByDemand(demand);
        for (OrderInfoSeller orderInfoSeller: orderInfoSellers
                ) {
            orderInfoSeller.setOrderCostInfoList(orderInfoSellerDaoI.getCostInfoList(
                    orderInfoSeller.getOrderInfoBuyer()==null?null:orderInfoSeller.getOrderInfoBuyer().getOrderInfoBuyerId()));
        }
        return new PageInfo<>(orderInfoSellers);
    }

    /**
     * 后台—原创征稿生成订单
     * @param orderInfoSellerId
     * @param userName
     */
    @Override
    public void saveProOrder(Integer orderInfoSellerId,String userName) {
        OrderInfoSeller orderInfoSeller = orderInfoSellerDaoI.getById(orderInfoSellerId);
        orderInfoSeller.setOrderStatusValue(EnumOrderSellerStatus.已生成订单.getIndex());
        orderInfoSeller.setConfirmTime(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        orderInfoBuyerServicesI.savePlaceTheDemandOrder(orderInfoSeller);
        orderInfoSellerDaoI.updateOrderSeller(orderInfoSeller);
        systemLogDaoI.log(OrderInfoSeller.class.getName(),orderInfoSellerId,"原创征稿生成订单",userName);
    }

    @Override
    public void updateDemandStatusIndex() {
        demandDaoI.updateDemandStatusIndex();
    }

    /**
     * 前台——生成订单—原创征稿
     *  批量
     * @param orderSn
     * @param userName
     */
    @Override
    public  List<OrderInfoBuyer> saveProOrderSn(String[] orderSn, String userName) {
        List<OrderInfoSeller> list = new ArrayList<OrderInfoSeller>();
        for (String str : orderSn){
            OrderInfoSeller orderInfoSeller = orderInfoSellerDaoI.getByOrderSn(str);
            orderInfoSeller.setOrderStatusValue(EnumOrderSellerStatus.已生成订单.getIndex());
            orderInfoSeller.setConfirmTime(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
            orderInfoSellerDaoI.updateOrderSeller(orderInfoSeller);
            list.add(orderInfoSeller);
            systemLogDaoI.log(OrderInfoSeller.class.getName(),orderInfoSeller.getOrderInfoSellerId(),"原创征稿批量生成订单",userName);
        }
        return orderInfoBuyerServicesI.savePlaceTheDemandOrder(list);
    }

    @Override
    public List<Map<String, Integer>> demandTypeCnt() {
        return demandDaoI.demandTypeCnt();
    }
}
