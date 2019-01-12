package com.yuanrong.admin.server.controller.order;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumOrderInfoSellerType;
import com.yuanrong.admin.Enum.EnumOrderSellerStatus;
import com.yuanrong.admin.Enum.EnumOrderSellerType;
import com.yuanrong.admin.Enum.EnumSellerOrderStatus;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.order.OrderInfoOffer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.bean.order.SellerOrder;
import com.yuanrong.admin.seach.OrderManagementSearch;
import com.yuanrong.admin.seach.OrderOfferParam;
import com.yuanrong.admin.seach.OrderPriceParamSearch;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.common.util.SystemParam;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 卖家订单的controller
 * Created MDA
 */
@Controller
@RequestMapping("order")
public class OrderInfoSellerController extends BaseController {
    private static final Logger logger = Logger.getLogger(OrderInfoSellerController.class);

    @RequestMapping( value = "orderInfoSeller_list" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(OrderInfoSeller data , BaseModel baseModel){
        PageInfo<OrderInfoSeller> snapshotAccountPageInfo = orderInfoSellerServicesI.list(data , baseModel);
        return new ResultTemplate(snapshotAccountPageInfo , snapshotAccountPageInfo);
    }

    /**
     *@author songwq
     *@param
     *@date 2018/7/13
     *@description 获取订单状态
    */
    @RequestMapping(value="getEnumOrderSellerStatus")
    @ResponseBody
    public ResultTemplate getEnumOrderSellerStatus(){
        return new ResultTemplate("", EnumOrderSellerStatus.getMapInfo());
    }

    /**
     *@author songwq
     *@param
     *@date 2018/7/13
     *@description 获取报名类型
    */
    @RequestMapping(value="getEnumOrderInfoSellerType")
    @ResponseBody
    public ResultTemplate getEnumOrderInfoSellerType(){
        return new ResultTemplate("", EnumOrderInfoSellerType.getMapInfo());
    }

    /**
     *@author songwq
     *@param
     *@date 2018/7/13
     *@description 获取订单类型
     */
    @RequestMapping(value="getAllEnumOrderSellerType")
    @ResponseBody
    public ResultTemplate getAllEnumOrderSellerType(){
        return new ResultTemplate("", EnumOrderSellerType.getMapInfo());
    }
    /**
     *@author songwq
     *@param
     *@date 2018/7/5
     *@description 订单管理待响应列表查询
     */
    @RequestMapping( value = "getPendingResponseList" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getPendingResponseList(OrderManagementSearch data , BaseModel baseModel) {
        PageInfo<OrderInfoSeller> pageInfoList = orderInfoSellerServicesI.getPendingResponseList(data, baseModel);
        List<Map<String, Object>> mapListOne = orderInfoSellerServicesI.getOrderStatusCount(data);
        List<Map<String, Object>> mapList = orderInfoSellerServicesI.getOrderStatusCount(new OrderManagementSearch());
        JSONArray result = new JSONArray();
        for (OrderInfoSeller orderInfoSeller : pageInfoList.getList()) {
            JSONObject ele = new JSONObject();
            //主键
            ele.put("orderInfoSellerId", orderInfoSeller.getOrderInfoSellerId());
            //订单号
            ele.put("orderSn", orderInfoSeller.getOrderSn());
            //需求编号
            ele.put("demandSn", orderInfoSeller.getDemand() == null ? "" : orderInfoSeller.getDemand().getDemandSn());
            //需求名称
            ele.put("demandName", orderInfoSeller.getDemand() == null ? "" : orderInfoSeller.getDemand().getDemandName());
            //订单类型
            ele.put("orderTypeValue", orderInfoSeller.getOrderType() == null ? "" : orderInfoSeller.getOrderType());
            ele.put("orderType", orderInfoSeller.getOrderTypeValue() == null ? "" : orderInfoSeller.getOrderTypeValue());
            //参考价
            ele.put("priceInfo", orderInfoSeller.getReferPrice()==null?"":orderInfoSeller.getReferPrice());
            //征稿报价
            ele.put("fillingPrice", orderInfoSeller.getPrice());
            //征稿卖家收入
            ele.put("fillingPayable", orderInfoSeller.getPayable());
            //有效时间
            //ele.put("invalidTime", orderInfoSeller.getPlatformIPAccount() == null ? "" : orderInfoSeller.getPlatformIPAccount().getInvalidTime() == null ? "" : DateUtil.format(DateUtil.parseDate(orderInfoSeller.getPlatformIPAccount().getInvalidTime()), "yyyy-MM-dd"));
            if (orderInfoSeller.getOrderTypeValue().equals(EnumOrderSellerType.营销分发.getIndex())) {//账号
                //创作者/账号icon
                ele.put("icon", orderInfoSeller.getPlatformIPAccount() == null ? "" : orderInfoSeller.getPlatformIPAccount().getShortVideoPlatformInfo()==null?"":orderInfoSeller.getPlatformIPAccount().getShortVideoPlatformInfo().getIcoUrl());
                //创作者/账号头像
                ele.put("authorImg", orderInfoSeller.getPlatformIPAccount() == null ? "" : orderInfoSeller.getPlatformIPAccount().getHeadImageUrlLocal());
                //创作者/账号URL
                ele.put("indexUrl", orderInfoSeller.getPlatformIPAccount() == null ? "" : orderInfoSeller.getPlatformIPAccount().getIndexUrl());
                //执行时间、提交时间
                ele.put("executeTime", orderInfoSeller.getDemand()==null || orderInfoSeller.getDemand().getSpreadTime()==null? "" : DateUtil.format(DateUtil.parseDate(orderInfoSeller.getDemand().getSpreadTime()), "yyyy-MM-dd HH:mm:ss"));

            } else if (orderInfoSeller.getOrderTypeValue().equals(EnumOrderSellerType.定制内容.getIndex())) {//创作者
                //创作者/账号icon
                ele.put("icon", "");
                //创作者/账号头像
                ele.put("authorImg", orderInfoSeller.getyRAuthor() == null ? "" : orderInfoSeller.getyRAuthor().getAuthorImg());
                //创作者/账号URL
                ele.put("indexUrl", "");
                //执行时间、提交时间
                ele.put("executeTime", orderInfoSeller.getDemand()==null || orderInfoSeller.getDemand().getExpectedTime()==null? "" : DateUtil.format(DateUtil.parseDate(orderInfoSeller.getDemand().getExpectedTime()), "yyyy-MM-dd HH:mm:ss"));

            } else {
                //创作者/账号icon
                ele.put("icon", "");
                //创作者/账号头像
                ele.put("authorImg", "");
                //创作者/账号URL
                ele.put("indexUrl", "");
                //执行时间、提交时间
                ele.put("executeTime", orderInfoSeller.getDemand()==null || orderInfoSeller.getDemand().getExpectedTime()==null? "" : DateUtil.format(DateUtil.parseDate(orderInfoSeller.getDemand().getExpectedTime()), "yyyy-MM-dd HH:mm:ss"));

            }
            //卖家服務費
            String sellerServiceRate = "";
            if(orderInfoSeller.getSellerServiceRate()!=null){
                sellerServiceRate = orderInfoSeller.getSellerServiceRate().toString();
            }else{
                if(orderInfoSeller.getOrderTypeValue()==2){//账号
                    sellerServiceRate =configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_SELLER_ACCOUNT);
                }else if(orderInfoSeller.getOrderTypeValue()==3){//创作者
                    sellerServiceRate =configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_SELLER_AUTHOR);
                }else if(orderInfoSeller.getOrderTypeValue()==4){//原创征稿
                    sellerServiceRate =configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_SELLER_COLLECTION);
                }
            }
            //买家服務費
            /*String buyerServiceRate = "";
            if(orderInfoSeller.getBuyerServiceRate()!=null){
                buyerServiceRate = orderInfoSeller.getBuyerServiceRate().toString();
            }else{
                if(orderInfoSeller.getOrderTypeValue()==2){//账号
                    buyerServiceRate =configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_BUYER_ACCOUNT);
                }else if(orderInfoSeller.getOrderTypeValue()==3){//创作者
                    buyerServiceRate =configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_BUYER_AUTHOR);
                }else if(orderInfoSeller.getOrderTypeValue()==4){//原创征稿
                    buyerServiceRate =configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_BUYER_COLLECTION);
                }
            }*/
            //卖家发票费
            String invoiceRate = configurationServicesI.getbyKey(SystemParam.INVOICE_PERCENT_SELLER);
            ele.put("invoiceRate",invoiceRate);

            ele.put("sellerServiceRate",sellerServiceRate);
            //创作者ID
            ele.put("yrAuthorId", orderInfoSeller.getyRAuthor() == null ? "" : orderInfoSeller.getyRAuthor().getRecId());
            //账户名
            ele.put("accountId", orderInfoSeller.getPlatformIPAccount() == null ? "" : orderInfoSeller.getPlatformIPAccount().getAccountID());
            ele.put("accountName", orderInfoSeller.getPlatformIPAccount() == null ? "" : orderInfoSeller.getPlatformIPAccount().getName());
            ele.put("authorNickName", orderInfoSeller.getyRAuthor() == null ? "" : orderInfoSeller.getyRAuthor().getAuthorNickname());
            ele.put("title", orderInfoSeller.getyRProduction() == null ? "" : orderInfoSeller.getyRProduction().getTitle()==null?"":orderInfoSeller.getyRProduction().getTitle());
            //作品ID
            ele.put("yrProductionId",orderInfoSeller.getyRProduction() == null ? "" : orderInfoSeller.getyRProduction().getRecId());
            //作品字数
            ele.put("wordNum",orderInfoSeller.getyRProduction() == null ? "" : orderInfoSeller.getyRProduction().getWordNum());
            //作品图片数
            ele.put("imgNum",orderInfoSeller.getyRProduction() == null ? "" : orderInfoSeller.getyRProduction().getImgNum());
            //订单状态
            ele.put("orderStatus", orderInfoSeller.getOrderStatus() == null ? "" : orderInfoSeller.getOrderStatus());
            ele.put("orderStatusValue", orderInfoSeller.getOrderStatusValue() == null ? "" : orderInfoSeller.getOrderStatusValue());
            //需求类型
//            ele.put("orderTypeStatus", orderInfoSeller.getOrderType() == null ? "" : orderInfoSeller.getOrderType());
//            ele.put("orderTypeStatusValue", orderInfoSeller.getOrderTypeValue() == null ? "" : orderInfoSeller.getOrderTypeValue());
            //销售
            ele.put("saleUserRealName", orderInfoSeller.getSale()==null?"":orderInfoSeller.getSale().getRealName());
            //媒介
            ele.put("mediaUserRealName", orderInfoSeller.getMedia()==null?"":orderInfoSeller.getMedia().getRealName());
            //卖家用户简称
            ele.put("NickName", orderInfoSeller.getSaler() == null ? "" : orderInfoSeller.getSaler().getNickName());
            //卖家用户简称
            ele.put("userId", orderInfoSeller.getSaler() == null ? "" : orderInfoSeller.getSaler().getRecID());
            //创建时间
            ele.put("createdTime" ,StringUtils.isEmpty(orderInfoSeller.getCreatedTime()) ? "" : DateUtil.format(orderInfoSeller.getCreatedTime(), "yyyy-MM-dd HH:mm:ss"));
            //执行结果
            ele.put("returnUrl", orderInfoSeller.getReturnUrl() == null ? "" : orderInfoSeller.getReturnUrl());
            ele.put("returnImg", orderInfoSeller.getReturnImg() == null ? "" : orderInfoSeller.getReturnImg());
            //可用排期
            ele.put("useableDate", orderInfoSeller.getUsableDate() == null ? "" : orderInfoSeller.getUsableDate());
            //应约备注
            ele.put("acceptRemark", orderInfoSeller.getAcceptRemark() == null ? "" : orderInfoSeller.getAcceptRemark());
            //应约备注
            ele.put("expectOffer", orderInfoSeller.getDemand() == null ? "" : orderInfoSeller.getDemand().getExpectOffer());
            //原创度
            ele.put("originalScore", orderInfoSeller.getyRProduction() == null ? "" : orderInfoSeller.getyRProduction().getOriginalScore());
            //应约回复
            List<JSONObject> offerList = new ArrayList<>();
            if (orderInfoSeller.getOrderInfoOfferList().size() > 0) {
                for (OrderInfoOffer orderInfoOffer : orderInfoSeller.getOrderInfoOfferList()) {
                    JSONObject costJson = new JSONObject();
                    costJson.put("prices", orderInfoOffer.getPrice());
//                    costJson.put("executePrice", orderInfoOffer.getExecutePrice());
                    costJson.put("sellerPrices",orderInfoOffer.getSellerPrice());
                    costJson.put("priceName", orderInfoOffer.getPriceName());
                    costJson.put("orderInfoOfferId", orderInfoOffer.getOrderInfoOfferId());
                    //计算卖家收入
                    if(sellerServiceRate==""){
                        costJson.put("payAbles", orderInfoOffer.getSellerPrice()==null?"0.0":orderInfoOffer.getSellerPrice().subtract(orderInfoOffer.getSellerPrice().multiply(new BigDecimal(sellerServiceRate).multiply(new BigDecimal(0.01)))));
                    }else{
                        costJson.put("payAbles", orderInfoOffer.getSellerPrice()==null?"0.0":String.format("%.2f", (orderInfoOffer.getSellerPrice().subtract(orderInfoOffer.getSellerPrice().multiply(new BigDecimal(Double.parseDouble(sellerServiceRate)*0.01))))));
                    }
                    offerList.add(costJson);
                }
            }
            ele.put("offerList", offerList);
            //拒绝原因
            ele.put("rejectReason", orderInfoSeller.getRejectReason() == null ? "" : orderInfoSeller.getRejectReason());
            //拒绝备注
            ele.put("rejectRemark", orderInfoSeller.getRejectRemark() == null ? "" : orderInfoSeller.getRejectRemark());

            //产品名称（价格项）
            ele.put("production", orderInfoSeller.getProduction() == null ? "" : orderInfoSeller.getProduction());
            //报价
            ele.put("sellerPrice", orderInfoSeller.getSellerPrice());
            //报价
            if(orderInfoSeller.getSellerPrice()==null){
                ele.put("payAble","");
            }else{
                BigDecimal rate = (new BigDecimal(1).subtract(new BigDecimal(sellerServiceRate).multiply(new BigDecimal(0.01)))).setScale(2,BigDecimal.ROUND_HALF_UP);
                ele.put("payAble", orderInfoSeller.getSellerPrice().multiply(rate));
            }
            //确认信息
            //JSONObject costJson = new JSONObject();
            //BigDecimal offerPrice = orderInfoSeller.getOfferPrice()==null?new BigDecimal(0):orderInfoSeller.getOfferPrice();

            //costJson.put("costPrice", offerPrice);
            //向上取整
            //costJson.put("servicePrice", buyerServiceRate==""?offerPrice:offerPrice.multiply(new BigDecimal(buyerServiceRate)).multiply(new BigDecimal(0.01)).setScale(0, BigDecimal.ROUND_UP).doubleValue());
            //costJson.put("invoicePrice", invoiceRate==""?offerPrice:offerPrice.multiply(new BigDecimal(invoiceRate)).multiply(new BigDecimal(0.01)).setScale(0, BigDecimal.ROUND_UP).doubleValue());
            //ele.put("confirmInfo", costJson);

            result.add(ele);
        }
        //将各状态的数据总数返回前台
        JSONObject eleCount = new JSONObject();
        eleCount.put("responseCount", 0);
        eleCount.put("performCount", 0);
        eleCount.put("confirmToUseCount", 0);
        eleCount.put("confirmToExecCount", 0);
        boolean statusBool = !StringUtils.isEmpty(data.getOrderStatusValue());
        if (mapList.size() > 0) {
            for (int i = 0; i < mapList.size(); i++) {
                if (mapList.get(i).get("orderStatusValue")!=null && mapList.get(i).get("orderStatusValue").toString().equals("2")) {//待卖家响应
                    eleCount.put("responseCount", mapList.get(i).get("countNum"));
                    if(mapListOne.size()==1 && statusBool && data.getOrderStatusValue().toString().equals("2")){
                        eleCount.put("responseCount", mapListOne.get(0).get("countNum"));
                    }else if(mapListOne.size()<=0 && statusBool && data.getOrderStatusValue().toString().equals("2")){
                        eleCount.put("responseCount", 0);
                    }
                }
                if (mapList.get(i).get("orderStatusValue")!=null && mapList.get(i).get("orderStatusValue").toString().equals("4")) {//待执行
                    eleCount.put("performCount", mapList.get(i).get("countNum"));
                    if(mapListOne.size()==1 && statusBool && data.getOrderStatusValue().toString().equals("4")){
                        eleCount.put("performCount", mapListOne.get(0).get("countNum"));
                    }else if(mapListOne.size()<=0 && statusBool && data.getOrderStatusValue().toString().equals("4")){
                        eleCount.put("performCount", 0);
                    }
                }
                if (mapList.get(i).get("orderStatusValue")!=null && mapList.get(i).get("orderStatusValue").toString().equals("3")) {//待买家确认
                    eleCount.put("confirmToUseCount", mapList.get(i).get("countNum"));
                    if(mapListOne.size()==1&& statusBool && data.getOrderStatusValue().toString().equals("3")){
                        eleCount.put("confirmToUseCount", mapListOne.get(0).get("countNum"));
                    }else if(mapListOne.size()<=0 && statusBool && data.getOrderStatusValue().toString().equals("3")){
                        eleCount.put("confirmToUseCount", 0);
                    }
                }
                if (mapList.get(i).get("orderStatusValue")!=null && mapList.get(i).get("orderStatusValue").toString().equals("5")) {//待确认执行
                    eleCount.put("confirmToExecCount", mapList.get(i).get("countNum"));
                    if(mapListOne.size()==1 && statusBool && data.getOrderStatusValue().toString().equals("5")){
                        eleCount.put("confirmToExecCount", mapListOne.get(0).get("countNum"));
                    }else if(mapListOne.size()<=0 && statusBool && data.getOrderStatusValue().toString().equals("5")){
                        eleCount.put("confirmToExecCount", 0);
                    }
                }
            }
        }
        return new ResultTemplate(pageInfoList , result,eleCount);
    }

    /**
     *@author songwq
     *@param
     *@date 2018/7/9
     *@description 根据需求id查询需求详情
    */
    @RequestMapping( value = "getDemandInfoByDemandSn" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getDemandInfoById(String orderInfoSellerId , BaseModel baseModel){
        if(orderInfoSellerId==null){
            return new ResultTemplate("您传的参数不对");
        }else {
            Demand demand = orderInfoSellerServicesI.getDemandInfoByDemandSn(orderInfoSellerId, baseModel);
            if(StringUtils.isEmpty(demand)){
                return new ResultTemplate("查询不到相应的需求");
            }
            JSONObject result = new JSONObject();
            //result.put("orderInfoId" , demand.getOrderInfoSeller()==null?"":demand.getOrderInfoSeller().getOrderInfoId());
            //根据订单类型，返回账号名
            if(demand.getOrderInfoSeller()==null){
                return new ResultTemplate("查询不到相应的订单");
            }
            if(demand.getOrderInfoSeller().getOrderTypeValue()==2){//账号
                result.put("accountName",demand.getSnapshotAccount()==null?"":demand.getSnapshotAccount().getName());
                result.put("accountNameValue","账号名");
            }else if(demand.getOrderInfoSeller().getOrderTypeValue()==3){//创作者
                result.put("accountName",demand.getSnapshotYrAuthor()==null?"":demand.getSnapshotYrAuthor().getAuthorNickname());
                result.put("accountNameValue","作者名");
            }else{
                result.put("accountName","");
                result.put("accountNameValue","");
            }
            result.put("demandSn",demand.getDemandSn());
            result.put("platformName",demand.getPlatformName()==null?"":demand.getPlatformName());
            try {
                result.put("spreadTime",StringUtil.isBlank(demand.getSpreadTime()) ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(demand.getSpreadTime())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(demand.getFans()!=null){
                String[] fans = demand.getFans().split("_");
                if(fans.length!=2){
                    return new ResultTemplate("粉丝数不对");
                }else{
                    result.put("startFans",fans[0]);
                    result.put("endFans",fans[1]);
                }
            }
            result.put("demandName",demand.getDemandName());
            result.put("demandTypeIndex",demand.getDemandTypeIndex());
            result.put("demandType",demand.getEnumDemandType());
            result.put("contentForms",demand.getContentForms());
            result.put("scenes",demand.getScenes());
            result.put("yrCategory",demand.getYrCategory());
            try {
                result.put("expectedTime",StringUtil.isBlank(demand.getExpectedTime()) ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(demand.getExpectedTime())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            result.put("remark",demand.getRemark()==null?"":demand.getRemark());
            result.put("attachment",demand.getAttachment()==null?"":demand.getAttachment());
            result.put("saleMark",demand.getSaleMark()==null?"":demand.getSaleMark());


            //字数要求
            result.put("requireWordNum", demand.getRequireWordNum());
            //篇数要求
            result.put("expectNum", demand.getExpectNum());
            //征稿素材
            result.put("attachment", demand.getAttachment());
            //参考样稿
            result.put("referUrl", demand.getReferURL());
            //所属行业
            result.put("tradeName", demand.getTradeDictinfo() == null ? "" : demand.getTradeDictinfo().getName());
            //用户ID
            result.put("userId",demand.getRegisteredUserInfoId());
            //用户简称
            result.put("userNickName", demand.getRegisteredUserInfo() == null ? "" : demand.getRegisteredUserInfo().getNickName());
            //手机号
            result.put("mobile", demand.getMobile());

            return new ResultTemplate("", result);
        }
    }
    /**
     *@author songwq
     *@param
     *@date 2018/7/10
     *@description 订单管理---待响应--应约
    */
    @RequestMapping( value = "acceptConvention" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate acceptConvention(OrderOfferParam orderOfferParam , BaseModel baseModel){
        if(orderOfferParam==null){
            return new ResultTemplate("您传的参数不对");
        }else{
            Integer orderInfoSellerId= orderOfferParam.getOrderInfoSellerId();
            String[] priceNames = orderOfferParam.getPriceNames();
            String[] prices = orderOfferParam.getPrices();
            String usableDate = orderOfferParam.getUsableDate();
            if( orderInfoSellerId==null ||orderInfoSellerId==0  ){
                return new ResultTemplate("您传的参数不对");
            }
            if(priceNames.length==0){
                return new ResultTemplate("您传的参数不对");
            }
            if(prices.length==0){
                return new ResultTemplate("您传的参数不对");
            }
            if(priceNames.length!=prices.length){
                return new ResultTemplate("您传的参数不对");
            }
            if(null==usableDate || "".equals(usableDate)){
                return new ResultTemplate("您传的参数不对");
            }
            orderInfoSellerServicesI.insertAcceptConvention(orderOfferParam,baseModel);
            return new ResultTemplate("", "应约成功");
        }
    }
    /**
     *@author songwq
     *@param
     *@date 2018/7/23
     *@description 拒绝原因查询
    */
    @RequestMapping( value = "getRefuseReason" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getRefuseReason(BaseModel baseModel){
        List<DictInfo> dictInfoList =  orderInfoSellerServicesI.getRefuseReason(baseModel);
        List<JSONObject> jsonList = new ArrayList<>();
        if(dictInfoList.size()>0){
            for(DictInfo dictInfo:dictInfoList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name",dictInfo.getName());
                jsonObject.put("value",dictInfo.getStatusIndex());
                jsonList.add(jsonObject);
            }
        }
        return new ResultTemplate("", jsonList);
    }


    /**
     *@author songwq
     *@param
     *@date 2018/7/10
     *@description 订单管理---待响应--拒约
     */
    @RequestMapping( value = "refuseConvention" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate refuseConvention(OrderOfferParam orderOfferParam , BaseModel baseModel){
        if(orderOfferParam==null){
            return new ResultTemplate("您传的参数不对");
        }else{
            Integer orderInfoSellerId = orderOfferParam.getOrderInfoSellerId();
            String rejectReason = orderOfferParam.getRejectReason();
            if(rejectReason==null || "".equals(rejectReason)){
                return new ResultTemplate("您传的参数不对");
            }
            if(orderInfoSellerId==null || "".equals(orderInfoSellerId)){
                return new ResultTemplate("您传的参数不对");
            }

            OrderInfoSeller orderInfoSeller = orderInfoSellerServicesI.getById(orderOfferParam.getOrderInfoSellerId());
            if(StringUtils.isEmpty(orderInfoSeller)){
                return new ResultTemplate("无法获取该条订单信息");
            }
            //String rejectRemark = orderInfoSeller.getRejectRemark();
            orderInfoSellerServicesI.updateRefuseConvention(orderOfferParam,baseModel);
            return new ResultTemplate("", "拒约成功");
        }
    }
    /**
     *@author songwq
     *@param
     *@date 2018/7/10
     *@description 订单管理--待确认使用--确认使用查询
     */
    @RequestMapping( value = "getAccountInfo" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getAccountInfo(String orderInfoSellerId ,BaseModel baseModel){

        if(orderInfoSellerId==null || orderInfoSellerId==""){
            return new ResultTemplate("您传的参数不对");
        }else{
            OrderInfoSeller orderInfoSeller = orderInfoSellerServicesI.getAccountInfo(Integer.parseInt(orderInfoSellerId),baseModel);
            if(StringUtils.isEmpty(orderInfoSeller)){
                return new ResultTemplate("无法获取到订单信息");
            }
            //查询配置表的服务费
            String serviceFee = "";
            if (orderInfoSeller.getOrderTypeValue() == EnumOrderSellerType.定制内容.getIndex() && orderInfoSeller.getSellerServiceRate()==null){
                serviceFee = configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_BUYER_AUTHOR);
            }else if(orderInfoSeller.getOrderTypeValue() == EnumOrderSellerType.营销分发.getIndex() && orderInfoSeller.getSellerServiceRate()==null ){
                serviceFee = configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_BUYER_ACCOUNT);
            }else if(orderInfoSeller.getOrderTypeValue() == EnumOrderSellerType.原创征稿.getIndex()&& orderInfoSeller.getSellerServiceRate()==null){
                serviceFee = configurationServicesI.getbyKey(SystemParam.SERVICES_FEE_BUYER_COLLECTION);
            }
            //查询配置表的发票费
            /*String invoiceRate = "";
            if (orderInfoSeller.getOrderTypeValue() == EnumOrderSellerType.定制内容.getIndex()){
                invoiceRate = configurationServicesI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_AUTHOR);
            }else if(orderInfoSeller.getOrderTypeValue() == EnumOrderSellerType.营销分发.getIndex()){
                invoiceRate = configurationServicesI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_ACCOUNT);
            }else if(orderInfoSeller.getOrderTypeValue() == EnumOrderSellerType.原创征稿.getIndex()){
                invoiceRate = configurationServicesI.getbyKey(SystemParam.INVOICE_PERCENT_BUYER_COLLECTION);
            }*/
            if(StringUtils.isEmpty(orderInfoSeller) || serviceFee=="" ){
                return new ResultTemplate("未查询到相关数据，请联系管理员");
            }
            //将参数封装为一个JSONObject
            List<Object> list = new ArrayList<>();
            list.add(orderInfoSeller);
            list.add(serviceFee);
            //list.add(100 - Integer.parseInt(invoiceRate));
            JSONObject object = OrderInfoSeller.packageOrderInfoSeller(list);
            return new ResultTemplate("", object);
        }
    }

    /**
     *@author songwq
     *@param
     *@date 2018/7/10
     *@description 订单管理--待确认使用--确认使用提交
    */
    @RequestMapping( value = "confirmUse")
    @ResponseBody
    public ResultTemplate confirmUse(OrderOfferParam orderOfferParam){
        /*if(orderOfferParam==null){
            return new ResultTemplate("您传的参数不对");
        }else{
            Integer orderInfoSellerId=orderOfferParam.getOrderInfoSellerId();
            //String[] priceNames=orderOfferParam.getPriceNames();
            //String prices[] = orderOfferParam.getPrices();//执行价
            String executeTime=orderOfferParam.getExecuteTime();
            String serviceFee=orderOfferParam.getServiceFee();
            //String[] offerPrices=orderOfferParam.getOfferPrices();//应约价
            Integer orderInfoOfferId = orderOfferParam.getOrderInfoOfferId();
            if(orderInfoSellerId==null || orderInfoSellerId==0){
                return new ResultTemplate("您传的参数不对");
            }
            OrderInfoSeller orderInfoSeller = orderInfoSellerServicesI.getById(orderOfferParam.getOrderInfoSellerId());
            if(orderInfoOfferId==null || orderInfoOfferId==0){
                return new ResultTemplate("您传的参数不对");
            }
            OrderInfoOffer orderInfoOffer = orderInfoOfferServicesI.getById(orderInfoOfferId);
            if(StringUtils.isEmpty(orderInfoSeller)){
                return new ResultTemplate("无法获取该条订单信息");
            }
            if(StringUtils.isEmpty(orderInfoOffer)){
                return new ResultTemplate("无法获取该条订单信息");
            }
            if(executeTime==null || executeTime==""){
                return new ResultTemplate("您传的参数不对");
            }
            if(serviceFee==null || serviceFee==""){
                return new ResultTemplate("您传的参数不对");
            }
            orderOfferParam.setExecuteTime(executeTime);
            orderOfferParam.setPrice(orderInfoOffer.getPrice());
            orderOfferParam.setOrderTypeValue(orderInfoSeller.getOrderTypeValue());
            orderOfferParam.setSellerServiceRate(orderInfoSeller.getSellerServiceRate());
            orderOfferParam.setPriceName(orderInfoOffer.getPriceName());
            orderInfoSellerServicesI.updateConfirmUse(orderOfferParam,baseModel);
            return new ResultTemplate("", "确认使用操作成功");
        }*/
        if(orderOfferParam==null){
            return new ResultTemplate("您传的参数不对");
        }else{
            Integer orderInfoSellerId=orderOfferParam.getOrderInfoSellerId();
            if(orderInfoSellerId==null || orderInfoSellerId==0){
                return new ResultTemplate("您传的参数不对");
            }
            OrderInfoSeller orderInfoSeller = orderInfoSellerServicesI.getById(orderInfoSellerId);
            if(StringUtils.isEmpty(orderInfoSeller)){
                return new ResultTemplate("无法获取相关报名信息");
            }
            Integer orderInfoOfferId = orderOfferParam.getOrderInfoOfferId();
            if(orderInfoOfferId==null || orderInfoOfferId==0){
                return new ResultTemplate("您传的参数不对");
            }
            OrderInfoOffer orderInfoOffer = orderInfoOfferServicesI.getById(orderInfoOfferId);
            if(StringUtils.isEmpty(orderInfoOffer)){
                return new ResultTemplate("无法获取该条订单信息");
            }
            String executeTime=orderOfferParam.getExecuteTime();
            String serviceFee=orderOfferParam.getServiceFee();
            if(executeTime==null || executeTime==""){
                return new ResultTemplate("您传的参数不对");
            }
            if(serviceFee==null || serviceFee==""){
                return new ResultTemplate("您传的参数不对");
            }

            orderInfoSellerServicesI.updateConfirmUse(orderOfferParam);
        }
        return new ResultTemplate("", "确认使用操作成功");
    }
    /**
     *@author songwq
     *@param
     *@date 2018/7/10
     *@description 订单管理--待确认使用-拒绝使用
     */
    @RequestMapping( value = "refuseUse" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate refuseUse(OrderOfferParam orderOfferParam , BaseModel baseModel){
        if(orderOfferParam==null){
            return new ResultTemplate("您传的参数不对");
        }else{
            if(orderOfferParam.getOrderInfoSellerId()==null || orderOfferParam.getOrderInfoSellerId()==0){
                return new ResultTemplate("报名ID不能为空");
            }
            OrderInfoSeller orderInfoSeller = orderInfoSellerServicesI.getById(orderOfferParam.getOrderInfoSellerId());
            if(StringUtils.isEmpty(orderInfoSeller)){
                return new ResultTemplate("无法获取报名信息");
            }
            if(StringUtil.isBlank(orderOfferParam.getRefuseReason())){
                orderOfferParam.setRefuseReason("买家拒绝");
            }
            orderInfoSellerServicesI.updateRefuseUse(orderOfferParam,baseModel);
            systemLogServicesI.log(OrderInfoSeller.class.getName(),orderOfferParam.getOrderInfoSellerId() ,"将报名信息状态修改为"+ EnumOrderSellerStatus.买家拒绝.getName(),getUser().getRealName());
            return new ResultTemplate();
        }
    }

    /**
     *@author songwq
     *@param
     *@date 2018/7/13
     *@description 订单管理--待确认执行-确认结果
     */
    @RequestMapping( value = "confirmResult" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate updateConfirmResult(OrderOfferParam orderOfferParam , BaseModel baseModel){
        if(orderOfferParam==null){
            return new ResultTemplate("您传的参数不对");
        }else{
            if(orderOfferParam.getOrderInfoSellerId()==null || orderOfferParam.getOrderInfoSellerId()==0){
                return new ResultTemplate("您传的参数不对");
            }

            OrderInfoSeller orderInfoSeller = orderInfoSellerServicesI.getById(orderOfferParam.getOrderInfoSellerId());
            if(StringUtils.isEmpty(orderInfoSeller)){
                return new ResultTemplate("无法获取该条订单信息");
            }

            orderInfoSellerServicesI.updateConfirmResult(orderOfferParam,baseModel);
            return new ResultTemplate("", "确认结果操作成功");
        }
    }

    /**
     * 删除卖家订单—假删
     * @param orderInfoSellerId
     * @return
     */
    @RequestMapping(value = "deleteSellerOrder",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate deleteSellerOrder(Integer orderInfoSellerId){
        if(orderInfoSellerId == null){
            return new ResultTemplate("订单Id不能为空");
        }
        if(orderInfoSellerServicesI.getById(orderInfoSellerId) == null){
            return new ResultTemplate("订单信息不存在");
        }
        orderInfoSellerServicesI.deleteSellerOrder(orderInfoSellerId);
        return new ResultTemplate();
    }

    /**
     *@author songwq
     *@param
     *@date 2018/8/23
     *@description 修改定制创作和营销分发应约价、报价、卖家服务费率
    */
    @RequestMapping(value = "updateSignUp")
    @ResponseBody
    public ResultTemplate updateSignUp(OrderPriceParamSearch orderPriceParamSearch){
        if(orderPriceParamSearch == null) {
            return new ResultTemplate("参数不能为空");
        }
        if(orderPriceParamSearch.getOrderSn()==null) {
            return new ResultTemplate("id不能为空");
        }
        OrderInfoSeller orderInfoSeller = orderInfoSellerServicesI.getByOrderSn(orderPriceParamSearch.getOrderSn());
        if(orderInfoSeller == null){
            return new ResultTemplate("信息不存在");
        }
        if(orderPriceParamSearch.getSellerPrices()==null || orderPriceParamSearch.getSellerPrices().length<=0) {
            return new ResultTemplate("订单报价不能为空");
        }
        if(orderPriceParamSearch.getSellerServiceRate()==null) {
            return new ResultTemplate("卖家佣金不能为空");
        }

        if(orderInfoSeller.getOrderStatusValue()==EnumOrderSellerStatus.待买家确认.getIndex()){//待确认使用
            if(orderPriceParamSearch.getPrices()==null ||orderPriceParamSearch.getPrices().length<=0 ) {
                return new ResultTemplate("订单应约价不能为空不能为空");
            }
            orderPriceParamSearch.setOrderStatus(orderInfoSeller.getOrderStatusValue());
            orderPriceParamSearch.setBuyerServiceRate(orderInfoSeller.getBuyerOrderPrice());
            orderInfoOfferServicesI.updateSignUp(orderPriceParamSearch,orderInfoSeller);
        }else{
            return new ResultTemplate("订单在该状态下不能修改价格");
        }

        return new ResultTemplate("","修改成功");
    }

    /**
     *@author songwq
     *@param
     *@date 2018/8/23
     *@description 修改定制创作和营销分发应约价、报价、卖家服务费率
     */
    @RequestMapping(value = "updateOrderInfo")
    @ResponseBody
    public ResultTemplate updateOrderInfo(OrderPriceParamSearch orderPriceParamSearch){
        if(orderPriceParamSearch == null) {
            return new ResultTemplate("参数不能为空");
        }
        if(orderPriceParamSearch.getOrderSn()==null) {
            return new ResultTemplate("id不能为空");
        }
        OrderInfoSeller orderInfoSeller = orderInfoSellerServicesI.getBySellerOrderSn(orderPriceParamSearch.getOrderSn());
        SellerOrder sellerOrder = sellerOrderServicesI.getByOrderSn(orderPriceParamSearch.getOrderSn());
        if(orderInfoSeller == null){
            return new ResultTemplate("信息不存在");
        }
        if(sellerOrder == null){
            return new ResultTemplate("订单信息不存在");
        }
        if(orderPriceParamSearch.getSellerPrice()==null) {
            return new ResultTemplate("订单报价不能为空");
        }
        if(orderPriceParamSearch.getSellerServiceRate()==null) {
            return new ResultTemplate("卖家佣金不能为空");
        }
        if(orderPriceParamSearch.getPayAble()==null) {
            return new ResultTemplate("卖家收入不能为空");
        }

        if(sellerOrder.getOrderStatus()==EnumSellerOrderStatus.待卖家执行.getIndex()
                || sellerOrder.getOrderStatus()==EnumSellerOrderStatus.待买家验收.getIndex()){
            orderPriceParamSearch.setOrderStatus(orderInfoSeller.getOrderStatusValue());
            orderPriceParamSearch.setBuyerServiceRate(orderInfoSeller.getBuyerOrderPrice());
            orderInfoOfferServicesI.updateOrderInfo(orderPriceParamSearch,orderInfoSeller,sellerOrder);
        }else{
            return new ResultTemplate("订单在该状态下不能修改价格");
        }

        return new ResultTemplate("","修改成功");
    }

    /**
     *@author songwq
     *@param
     *@date 2018/8/23
     *@description 修改作品购买和原创征稿报价、收入、卖家服务费率
     */
    @RequestMapping(value = "updateProductPrice")
    @ResponseBody
    public ResultTemplate updateProductPrice(OrderPriceParamSearch orderPriceParamSearch){
        if(orderPriceParamSearch == null) {
            return new ResultTemplate("参数不能为空");
        }
        if(orderPriceParamSearch.getOrderSn()==null) {
            return new ResultTemplate("id不能为空");
        }
        if(orderPriceParamSearch.getSellerPrice()==null) {
            return new ResultTemplate("订单报价不能为空");
        }
        if(orderPriceParamSearch.getSellerServiceRate()==null) {
            return new ResultTemplate("卖家佣金不能为空");
        }
        if(orderPriceParamSearch.getPayAble()==null) {
            return new ResultTemplate("订单收入不能为空");
        }
        SellerOrder sellerOrder = sellerOrderServicesI.getByOrderSn(orderPriceParamSearch.getOrderSn());
        if(sellerOrder == null){
            return new ResultTemplate("订单信息不存在");
        }
        if(!sellerOrder.getOrderStatus().equals(EnumSellerOrderStatus.已完成.getIndex())){
            sellerOrderServicesI.updateProductPrice(orderPriceParamSearch,sellerOrder);
        }else{
            return new ResultTemplate("订单在该状态下不能修改价格");
        }

        return new ResultTemplate("","修改成功");
    }

    /**
     *@author songwq
     *@param
     *@date 2018/10/16
     *@description 价格的变化
    */
    @RequestMapping(value = "updateProductionPrice")
    @ResponseBody
    public ResultTemplate updateProductionPrice(BigDecimal price,BigDecimal payAble,BigDecimal sellerServiceRate,int type){
        if(price==null || payAble==null || sellerServiceRate ==null){
            return new ResultTemplate("","请求参数不能为空");
        }
        if(type==0){
            return new ResultTemplate("","修改类型不能为空");
        }
        Map<String,Object> map = new HashMap<>();
        sellerServiceRate = sellerServiceRate.multiply(new BigDecimal(0.01)).setScale(2,BigDecimal.ROUND_HALF_UP);
        if(type==1){
            payAble = price.multiply(new BigDecimal(1).subtract(sellerServiceRate)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }else if(type==2){
            sellerServiceRate = (price.subtract(payAble)).divide(price,3,BigDecimal.ROUND_HALF_UP);
        }else if(type==3){
            payAble = price.multiply((new BigDecimal(1).subtract(sellerServiceRate))).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        map.put("price",price);
        map.put("payAble",payAble);
        map.put("sellerServiceRate",sellerServiceRate.multiply(new BigDecimal(100)));
        return new ResultTemplate(map);
    }

    /**
     *@author songwq
     *@param
     *@date 2018/10/16
     *@description 价格的变化
     */
    @RequestMapping(value = "updateResponsePrice")
    @ResponseBody
    public ResultTemplate updateResponsePrice(Integer[] ids, BigDecimal[] sellerPrices, BigDecimal[] payAbles,
                                              BigDecimal sellerServiceRate, Integer updateId, String updateItem){
        JSONObject ele = new JSONObject();
        if("sellerPrice".equals(updateItem)){//修改的是卖家报价
            if(judgeParam(ids,updateId,sellerPrices)){
                if(sellerServiceRate==null){
                    return new ResultTemplate("","sellerServiceRate参数不能为空");
                }
                sellerServiceRate = sellerServiceRate.multiply(new BigDecimal(0.01)).setScale(2,BigDecimal.ROUND_HALF_UP);
                for(int i=0;i<ids.length;i++){
                    if(ids[i].compareTo(updateId)==0){
                        payAbles[i]=sellerPrices[i].multiply((new BigDecimal(1).subtract(sellerServiceRate))).setScale(2,BigDecimal.ROUND_HALF_UP);
                    }
                }
            }
        }else if("payAble".equals(updateItem)){//修改的是卖家收入
            if(judgeParam(ids,updateId,sellerPrices)){
                if(payAbles==null || payAbles.length!=ids.length){
                    return new ResultTemplate("","payAbles参数有误");
                }
                for(int i=0;i<ids.length;i++){//根据修改的卖家收入计算卖家佣金
                    if(ids[i].compareTo(updateId)==0){
                        sellerServiceRate =  (sellerPrices[i].subtract(payAbles[i])).divide(sellerPrices[i],3,BigDecimal.ROUND_HALF_UP);
                    }
                }
                for(int i=0;i<payAbles.length;i++){//本条记录的卖家收入不修改
                    if(ids[i].compareTo(updateId)!=0){
                        payAbles[i]=sellerPrices[i].multiply((new BigDecimal(1).subtract(sellerServiceRate))).setScale(2,BigDecimal.ROUND_HALF_UP);
                    }
                }
            }
        }else if("sellerServiceRate".equals(updateItem)){//修改的是卖家佣金
            if(sellerServiceRate==null ||sellerPrices==null || ids==null || ids.length!=sellerPrices.length){
                return new ResultTemplate("","sellerServiceRate、sellerPrices或ids参数有误");
            }
            sellerServiceRate = sellerServiceRate.multiply(new BigDecimal(0.01));
            for(int i=0;i<ids.length;i++){
                payAbles[i] = sellerPrices[i].multiply((new BigDecimal(1).subtract(sellerServiceRate))).setScale(2,BigDecimal.ROUND_HALF_UP);
            }
        }
        ele.put("id",ids);
        ele.put("sellerPrice",sellerPrices);
        ele.put("payAble",payAbles);
        ele.put("sellerServiceRate",sellerServiceRate.multiply(new BigDecimal(100)));
        return new ResultTemplate(ele);
    }

    //修改价格时判断修改的列是否在价格项内
    private boolean judgeParam(Integer[] ids, Integer updateId,BigDecimal[] sellerPrices) {
        //确认修改的数据属于价格项
        boolean isTrue = false;
        boolean returns = false;
        for(int id :ids){
            if(id==updateId){
                isTrue=true;
                break;
            }
        }
        //卖家报价不能为空并且价格项个数和卖家报价个数一致
        if(isTrue && sellerPrices!=null && ids.length==sellerPrices.length){
            returns=true;
        }
        return returns;
    }


}
