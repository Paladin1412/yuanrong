package com.yuanrong.admin.server.controller.order;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumOrderSellerStatus;
import com.yuanrong.admin.Enum.EnumOrderSellerType;
import com.yuanrong.admin.Enum.EnumSellerOrderStatus;
import com.yuanrong.admin.bean.order.OrderInfoOffer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.bean.order.SellerOrder;
import com.yuanrong.admin.seach.OrderManagementSearch;
import com.yuanrong.admin.seach.OrderOfferParam;
import com.yuanrong.admin.seach.SellerOrderSearch;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 卖家订单的controller
 * Created MDA
 */
@Controller
@RequestMapping("order")
public class SellerOrderController extends BaseController {
    private static final Logger logger = Logger.getLogger(SellerOrderController.class);

    /**
     *@author songwq
     *@param
     *@date 2018/10/09
     *@description 卖家订单状态
     */
    @RequestMapping(value="getEnumSellerOrderStatus")
    @ResponseBody
    public ResultTemplate getEnumSellerOrderStatus(){
        return new ResultTemplate("", EnumSellerOrderStatus.getMapInfo());
    }

    @RequestMapping( value = "sellerOrder_list" )
    @ResponseBody
    public ResultTemplate list(SellerOrderSearch data , BaseModel baseModel){
        PageInfo<SellerOrder> sellerOrderPageInfo = sellerOrderServicesI.list(data , baseModel);
        List<Map<String, Object>> mapListOne = sellerOrderServicesI.getOrderStatusCount(data);
        List<Map<String, Object>> mapList = sellerOrderServicesI.getOrderStatusCount(new SellerOrderSearch());
        JSONArray array = new JSONArray();
        JSONObject eleCount = new JSONObject();
        eleCount.put("performCount", 0);
        if(CollectionUtil.size(sellerOrderPageInfo.getList()) > 0){
            for (SellerOrder sellerOrder : sellerOrderPageInfo.getList()) {
                JSONObject ele = new JSONObject();
                //主键
                ele.put("sellerOrderId", sellerOrder.getSellerOrderId());
                //报名表主键
                ele.put("orderInfoSellerId", sellerOrder.getOrderInfoSeller() == null?"":sellerOrder.getOrderInfoSeller().getOrderInfoSellerId());
                //订单号
                ele.put("orderSn", sellerOrder.getOrderSn());
                //流水号
                ele.put("serialOrderSn", sellerOrder.getOrderInfoSeller() == null ? "" : sellerOrder.getOrderInfoSeller().getOrderSn());
                //需求编号
                ele.put("demandSn", sellerOrder.getDemand() == null ? "" : sellerOrder.getDemand().getDemandId());
                //需求名称
                ele.put("demandName", sellerOrder.getDemand() == null ? "" : sellerOrder.getDemand().getDemandName());
                //订单类型
                ele.put("orderType", sellerOrder.getSellerOrderType() == null ? "" : sellerOrder.getSellerOrderType());
                ele.put("orderTypeValue", sellerOrder.getEnumOrderSellerType() == null ? "" : sellerOrder.getEnumOrderSellerType());
                //订单内容--文章id
                ele.put("yrProductionId", sellerOrder.getSnapshotYrProduction() == null ? "" : sellerOrder.getSnapshotYrProduction().getYrProductionId());
                //订单内容--标题
                ele.put("productTitle", sellerOrder.getSnapshotYrProduction() == null ? "" : sellerOrder.getSnapshotYrProduction().getTitle());
                //订单内容--字数
                ele.put("productWordNum", sellerOrder.getSnapshotYrProduction() == null ? "" : sellerOrder.getSnapshotYrProduction().getWordNum());
                //订单内容--图片数
                ele.put("productImgNum", sellerOrder.getSnapshotYrProduction() == null ? "" : sellerOrder.getSnapshotYrProduction().getImgNum());
                //账号icon
                ele.put("icon", sellerOrder.getSnapshotAccount() == null ? "" : sellerOrder.getSnapshotAccount().getPlatformLogo());
                //账号头像
                ele.put("accountImg", sellerOrder.getSnapshotAccount() == null ? "" : sellerOrder.getSnapshotAccount().getHeadImageUrlLocal());
                //账号名称，中文
                ele.put("accountName", sellerOrder.getSnapshotAccount() == null ? "" : sellerOrder.getSnapshotAccount().getName());
                //账号ID
                ele.put("accountId", sellerOrder.getSnapshotAccount() == null ? "" : sellerOrder.getSnapshotAccount().getAccountID());
                //账号URL
                ele.put("indexUrl", sellerOrder.getPlatformIPAccount() == null ? "" : sellerOrder.getPlatformIPAccount().getIndexUrl());
                //创作者头像
                ele.put("authorImg", sellerOrder.getSnapshotYrAuthor() == null ? "" : sellerOrder.getSnapshotYrAuthor().getAuthorImg());
                //创作者昵称
                ele.put("authorNickName", sellerOrder.getSnapshotYrAuthor() == null ? "" : sellerOrder.getSnapshotYrAuthor().getAuthorNickname());
                //价格项
                ele.put("production", sellerOrder.getOrderInfoSeller() == null ? "" : sellerOrder.getOrderInfoSeller().getProduction());
                //执行时间
                ele.put("executeTime", sellerOrder.getOrderInfoSeller() == null ? "" : sellerOrder.getOrderInfoSeller().getExecuteTime());
                //卖家报价
                ele.put("price", sellerOrder.getPrice());
                //卖家应约价
                ele.put("basePrice", sellerOrder.getOrderDetail()==null?"":sellerOrder.getOrderDetail().getBasePrice());
                //卖家收入
                ele.put("payAble", sellerOrder.getPayable());
                //卖家佣金
                ele.put("sellerServiceRate", sellerOrder.getSellerServiceRate());
                //订单状态
                ele.put("orderStatus", sellerOrder.getOrderStatus() == null ? "" : sellerOrder.getOrderStatus());
                ele.put("orderStatusValue", sellerOrder.getEnumSellerOrderStatus() == null ? "" : sellerOrder.getEnumSellerOrderStatus());
                //支付状态
                ele.put("payStatus", sellerOrder.getOrderInfoBuyer() == null ? "" : sellerOrder.getOrderInfoBuyer().getPayStatusValue());
                ele.put("payStatusValue", sellerOrder.getOrderInfoBuyer() == null ? "" : sellerOrder.getOrderInfoBuyer().getPayStatus());
                //创建时间
                ele.put("createdTime", sellerOrder.getCreatedTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sellerOrder.getCreatedTime()));
                //原创度
                ele.put("originalScore", sellerOrder.getYrProduction() == null ? "" : sellerOrder.getYrProduction().getOriginalScore());
                //买家订单号
                ele.put("buyerId", sellerOrder.getOrderInfoBuyer() == null ? "" : sellerOrder.getOrderInfoBuyer().getOrderInfoBuyerId());

                if(sellerOrder.getSellerOrderType().equals(EnumOrderSellerType.作品订单.getIndex())){//作品订单
                    //销售
                    ele.put("saleUser", sellerOrder.getProductSale() == null ? "" : sellerOrder.getProductSale().getRealName());
                    //媒介
                    ele.put("mediaUser", sellerOrder.getProductMedia() == null ? "" : sellerOrder.getProductMedia().getRealName());
                }else{
                    //销售
                    ele.put("saleUser", sellerOrder.getSale() == null ? "" : sellerOrder.getSale().getRealName());
                    //媒介
                    ele.put("mediaUser", sellerOrder.getMedia() == null ? "" : sellerOrder.getMedia().getRealName());
                }
                //用户简称
                ele.put("nickName", sellerOrder.getRegisteredUserInfo() == null ? "" : sellerOrder.getRegisteredUserInfo().getNickName());
                //创作者ID
                ele.put("yrAuthorId", sellerOrder.getRegisteredUserInfoId() == null ? "" : sellerOrder.getRegisteredUserInfoId());
                //应约回复
                /*List<JSONObject> offerList = new ArrayList<>();
                if (sellerOrder.getOrderInfoOfferList().size() > 0) {
                    for (OrderInfoOffer orderInfoOffer : sellerOrder.getOrderInfoOfferList()) {
                        JSONObject costJson = new JSONObject();
                        costJson.put("price", orderInfoOffer.getPrice());
                        costJson.put("executePrice", orderInfoOffer.getExecutePrice());
                        costJson.put("priceName", orderInfoOffer.getPriceName());
                        costJson.put("orderInfoOfferId", orderInfoOffer.getOrderInfoOfferId());
                        offerList.add(costJson);
                    }
                }
                ele.put("offerList", offerList);*/
                //查看结果
                ele.put("returnUrl", sellerOrder.getOrderInfoSeller() == null ? "" : sellerOrder.getOrderInfoSeller().getReturnUrl());
                //查看结果
                ele.put("returnImg", sellerOrder.getOrderInfoSeller() == null ? "" : sellerOrder.getOrderInfoSeller().getReturnImg());
                //失效原因
                ele.put("cancelReason", sellerOrder.getCancelReason() == null ? "" : sellerOrder.getCancelReason());
                //表现形式
                ele.put("contentForms", sellerOrder.getDemand() == null ? "" : sellerOrder.getDemand().getContentForms());
                //字数要求
                ele.put("requireWordNum", sellerOrder.getDemand() == null ? "" : sellerOrder.getDemand().getRequireWordNum());
                //篇数要求
                ele.put("expectNum", sellerOrder.getDemand() == null ? "" : sellerOrder.getDemand().getExpectNum());
                //截止日期
                if(sellerOrder.getSellerOrderType().equals(EnumOrderSellerType.营销分发.getIndex()) ){
                    ele.put("expectedTime", sellerOrder.getDemand() == null ? "" : sellerOrder.getDemand().getSpreadTime());
                }else{
                    ele.put("expectedTime", sellerOrder.getDemand() == null ? "" : sellerOrder.getDemand().getExpectedTime());
                }
                //创作要求
                ele.put("remark", sellerOrder.getDemand() == null ? "" : sellerOrder.getDemand().getRemark());
                //征稿素材
                ele.put("attachment", sellerOrder.getDemand() == null ? "" : sellerOrder.getDemand().getAttachment());
                //参考样稿
                ele.put("referURL", sellerOrder.getDemand() == null ? "" : sellerOrder.getDemand().getReferURL());
                //所属行业
                ele.put("tradeName", sellerOrder.getDictInfo() == null ? "" : sellerOrder.getDictInfo().getName());
                //用户ID
                ele.put("registeredUserInfoId", sellerOrder.getDemand() == null ? "" : sellerOrder.getDemand().getRegisteredUserInfoId());
                //用户简称
                ele.put("nickName", sellerOrder.getRegisteredUserInfo() == null ? "" : sellerOrder.getRegisteredUserInfo().getNickName());
                //手机号
                ele.put("mobile", sellerOrder.getDemand() == null ? "" : sellerOrder.getDemand().getMobile());
                //销售备注
                ele.put("saleMark", sellerOrder.getDemand() == null ? "" : sellerOrder.getDemand().getSaleMark());
                //创作耗时
                ele.put("useableDate", sellerOrder.getOrderInfoSeller() == null ? "" : sellerOrder.getOrderInfoSeller().getUsableDate());
                //应约备注
                ele.put("acceptRemark", sellerOrder.getOrderInfoSeller() == null ? "" : sellerOrder.getOrderInfoSeller().getAcceptRemark());
                array.add(ele);
            }
            boolean statusBool = !StringUtils.isEmpty(data.getOrderStatus());
            if (mapList.size() > 0) {
                for (int i = 0; i < mapList.size(); i++) {
                    if (mapList.get(i).get("orderStatus") != null && mapList.get(i).get("orderStatus").equals(EnumSellerOrderStatus.待卖家执行.getIndex())) {//待执行
                        eleCount.put("performCount", mapList.get(i).get("countNum"));
                        if (mapListOne.size() == 1 && statusBool && data.getOrderStatus().equals(EnumSellerOrderStatus.待卖家执行.getIndex())) {
                            eleCount.put("performCount", mapListOne.get(0).get("countNum"));
                        } else if (mapListOne.size() <= 0 && statusBool && data.getOrderStatus().equals(EnumSellerOrderStatus.待卖家执行.getIndex())) {
                            eleCount.put("performCount", 0);
                        }
                    }
                }
            }
        }
        return new ResultTemplate(sellerOrderPageInfo , array,eleCount);
    }

    /**
     *@author songwq
     *@param
     *@date 2018/7/13
     *@description 获取订单状态
     */
    @RequestMapping(value="getEnumOrderStatus")
    @ResponseBody
    public ResultTemplate getEnumOrderStatus(){
        return new ResultTemplate("", EnumOrderSellerStatus.getPartMapInfo());
    }

    /**
     *@author songwq
     *@param
     *@date 2018/7/13
     *@description 获取订单类型
     */
    @RequestMapping(value="getOrderSellerType")
    @ResponseBody
    public ResultTemplate getOrderSellerType(){
        return new ResultTemplate("", EnumOrderSellerType.getMapInfo());
    }

    /**
     *@author songwq
     *@param
     *@date 2018/7/12
     *@description 订单管理--待执行-提交执行
     */
    @RequestMapping( value = "commitExecute" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate commitExecute(OrderOfferParam orderOfferParam , BaseModel baseModel){
        if(orderOfferParam==null){
            return new ResultTemplate("您传的参数不对");
        }else{
            if(orderOfferParam.getOrderInfoSellerId()==null || orderOfferParam.getOrderInfoSellerId()==0){
                return new ResultTemplate("您传的参数不对");
            }
            if(orderOfferParam.getSellerOrderId()==null || orderOfferParam.getSellerOrderId()==0){
                return new ResultTemplate("您传的参数不对");
            }
            //根据传入的id查询该条数据是否存在以及该条数据的类型
            OrderInfoSeller orderInfoSeller = orderInfoSellerServicesI.getById(orderOfferParam.getOrderInfoSellerId());
            if(StringUtils.isEmpty(orderInfoSeller)){
                return new ResultTemplate("无法获取该条报名信息");
            }

            SellerOrder sellerOrder = sellerOrderServicesI.getById(orderOfferParam.getSellerOrderId());
            if(StringUtils.isEmpty(sellerOrder)){
                return new ResultTemplate("无法获取该条订单信息");
            }
            if(!((orderInfoSeller.getOrderTypeValue().compareTo(EnumOrderSellerType.营销分发.getIndex())==0)
                    || (orderInfoSeller.getOrderTypeValue().compareTo(EnumOrderSellerType.定制内容.getIndex()))==0)){
                return new ResultTemplate("该订单类型无法提交执行");
            }
            //营销分发不能为空、定制创作为空
            if(orderInfoSeller.getOrderTypeValue().compareTo(EnumOrderSellerType.营销分发.getIndex())==0
                    && (orderOfferParam.getReturnUrl()==null || orderOfferParam.getReturnUrl()=="")){
                return new ResultTemplate("您传的参数不对");
            }
            if(orderOfferParam.getReturnImg()==null ||orderOfferParam.getReturnImg()==""){
                return new ResultTemplate("您传的参数不对");
            }

            //orderOfferParam.setOrderStatusValue(EnumOrderSellerStatus.已完成.getIndex());
            orderInfoSellerServicesI.updateCommitExecute(orderOfferParam,baseModel);
            return new ResultTemplate("", "提交执行操作成功");
        }
    }


    /**
     *@author songwq
     *@param
     *@date 2018/7/13
     *@description 订单管理--待执行-修改执行数据查询
     */
    @RequestMapping( value = "updateExecuteSearch" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate updateExecuteSearch(OrderInfoSeller orderInfoSeller , BaseModel baseModel){
        if(orderInfoSeller==null){
            return new ResultTemplate("您传的参数不对");
        }else{
            if(orderInfoSeller.getOrderInfoSellerId()==null || orderInfoSeller.getOrderInfoSellerId()==0){
                return new ResultTemplate("您传的参数不对");
            }

            OrderInfoSeller executeInfo = orderInfoSellerServicesI.getExecuteInfo(orderInfoSeller,baseModel);
            if(StringUtils.isEmpty(executeInfo)){
                return new ResultTemplate("无法获取到执行链接等信息");
            }
            JSONObject json = new JSONObject();
            json.put("orderInfoSellerId",executeInfo.getOrderInfoSellerId());
            json.put("orderSn",executeInfo.getOrderSn());
            if(executeInfo.getOrderTypeValue().compareTo(EnumOrderSellerType.营销分发.getIndex())==0){
                json.put("name",executeInfo.getSnapshotAccount()==null?"":executeInfo.getSnapshotAccount().getName());
            } else if(executeInfo.getOrderTypeValue().compareTo(EnumOrderSellerType.定制内容.getIndex())==0){
                json.put("name",executeInfo.getSnapshotYrAuthor()==null?"":executeInfo.getSnapshotYrAuthor().getAuthorNickname());
            }else{
                json.put("name","");
            }
            json.put("returnUrl",executeInfo.getReturnUrl()==null?"":executeInfo.getReturnUrl());
            json.put("returnImg",executeInfo.getReturnImg()==null?"":executeInfo.getReturnImg());
            return new ResultTemplate("", json);
        }
    }

    /**
     *@author songwq
     *@param
     *@date 2018/7/13
     *@description 订单管理--待执行-修改执行
     */
    @RequestMapping( value = "updateExecute" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate updateExecute(OrderOfferParam orderOfferParam , BaseModel baseModel){
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
            if(!((orderInfoSeller.getOrderTypeValue().compareTo(EnumOrderSellerType.营销分发.getIndex())==0)
                    || (orderInfoSeller.getOrderTypeValue().compareTo(EnumOrderSellerType.定制内容.getIndex()))==0)){
                return new ResultTemplate("该订单类型无法提交执行");
            }
            if(orderOfferParam.getReturnUrl()==null || orderOfferParam.getReturnUrl()==""){
                return new ResultTemplate("您传的参数不对");
            }
            if(orderOfferParam.getReturnImg()==null ||orderOfferParam.getReturnImg()==""){
                return new ResultTemplate("您传的参数不对");
            }

            orderInfoSellerServicesI.updateCommitExecute(orderOfferParam,baseModel);
            return new ResultTemplate("", "修改执行操作成功");
        }
    }
}
