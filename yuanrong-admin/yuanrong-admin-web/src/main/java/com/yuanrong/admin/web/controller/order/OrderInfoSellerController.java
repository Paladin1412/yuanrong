package com.yuanrong.admin.web.controller.order;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumOrderSellerStatus;
import com.yuanrong.admin.Enum.EnumOrderSellerType;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Map;

/**
 * 卖家订单的controller
 * Created MDA
 */
@Controller
@RequestMapping("order")
public class OrderInfoSellerController extends BaseController {
    private static final Logger logger = Logger.getLogger(OrderInfoSellerController.class);

    @RequestMapping(value = "orderInfoSeller_list", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(OrderInfoSeller data, BaseModel baseModel, HttpSession session) {
        data.setRegisteredUserInfoId(getWebUser(session).getRecID());
        PageInfo<OrderInfoSeller> orderInfoSellerPageInfo = orderInfoSellerServicesI.list(data, baseModel);
        JSONArray jsonArray = new JSONArray();
        if(CollectionUtil.size(orderInfoSellerPageInfo.getList()) > 0){
            for(OrderInfoSeller orderInfoSeller : orderInfoSellerPageInfo.getList()){
                JSONObject ele = new JSONObject();
                ele.put("enrollSn" , orderInfoSeller.getOrderSn());
                ele.put("orderType" , orderInfoSeller.getOrderType().getName());
                ele.put("orderStatus" , orderInfoSeller.getOrderStatus().getName());
                ele.put("createdTime" , DateUtil.formatDateTime(orderInfoSeller.getCreatedTime()));
                ele.put("orderStatusIndex" , orderInfoSeller.getOrderStatus().getIndex());
                ele.put("orderTypeIndex" , orderInfoSeller.getOrderType().getIndex());
                //拒绝使用 -- 买家
                ele.put("refuseReason" , orderInfoSeller.getRefuseReason());
                //拒约原因 -- 卖家
                ele.put("rejectReason" , orderInfoSeller.getRejectReason());
                ele.put("demandName",orderInfoSeller.getDemand().getDemandName());
                if(orderInfoSeller.getOrderType().getIndex() == EnumOrderSellerType.定制内容.getIndex()){
                    ele.put("authorImg" ,orderInfoSeller.getyRAuthor() == null ? "" : orderInfoSeller.getyRAuthor().getAuthorImg());
                    ele.put("authorNickname" ,orderInfoSeller.getyRAuthor() == null ? "" : orderInfoSeller.getyRAuthor().getAuthorNickname());
                }else if(orderInfoSeller.getOrderType().getIndex() == EnumOrderSellerType.营销分发.getIndex()){
                    ele.put("accountID" ,orderInfoSeller.getPlatformIPAccount()== null ? "" : orderInfoSeller.getPlatformIPAccount().getAccountID());
                    ele.put("accountName" , orderInfoSeller.getPlatformIPAccount() == null ? "" :orderInfoSeller.getPlatformIPAccount().getName());
                    ele.put("accountImg" ,orderInfoSeller.getPlatformIPAccount() == null ? "" : orderInfoSeller.getPlatformIPAccount().getHeadImageUrlLocal());
                    ele.put("platformLogo" ,orderInfoSeller.getPlatformIPAccount() == null ? "" : orderInfoSeller.getPlatformIPAccount().getShortVideoPlatformInfo().getIcoUrl());
                    ele.put("platformName" ,orderInfoSeller.getPlatformIPAccount() == null ? "" : orderInfoSeller.getPlatformIPAccount().getShortVideoPlatformInfo().getPlatformname());
                }else if(orderInfoSeller.getOrderType().getIndex() == EnumOrderSellerType.原创征稿.getIndex()){
                    ele.put("productName" , orderInfoSeller.getyRProduction().getTitle());
                    ele.put("productionId",orderInfoSeller.getReferId());
                }
                jsonArray.add(ele);
            }
        }
        return new ResultTemplate(orderInfoSellerPageInfo , jsonArray);
    }

    @RequestMapping("orderInfoSeller/orderTypeInfo")
    @ResponseBody
    public ResultTemplate getOrderType(){
        return new ResultTemplate(EnumOrderSellerType.getMapInfo());
    }

    @RequestMapping("orderInfoSeller/orderStatusInfo")
    @ResponseBody
    public ResultTemplate orderStatusInfo(){
        return new ResultTemplate(EnumOrderSellerStatus.getMapInfo());
    }

    /**
     * @param orderInfoSellerId
     * @author songwq
     * @data 2018/6/29
     * @description 买家中心已购买作品详情
     */
    @RequestMapping(value = "getSellerProductInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getSellerProductInfo(int orderInfoSellerId) {
        if (orderInfoSellerId != 0) {
            Map<String,Object> map = orderInfoSellerServicesI.getSellerProductInfo(orderInfoSellerId);
            logger.info(map);
            return new ResultTemplate("", map);
        } else {
            return new ResultTemplate("", "请求参数为空！");
        }
    }

    @RequestMapping("orderInfoSeller/getByOrderSn")
    @ResponseBody
    public ResultTemplate getByOrderSn(String orderSn,HttpSession session){
        if(StringUtil.isBlank(orderSn) ){
            return new ResultTemplate("订单号为空！");
        }
        OrderInfoSeller orderInfoSeller = orderInfoSellerServicesI.getByOrderSnAndRegisterUserInfoId(orderSn , getWebUser(session).getRecID());
        if(StringUtils.isEmpty(orderInfoSeller)){
            return new ResultTemplate("无法获取到该订单！");
        }
        JSONObject result = new JSONObject();
        JSONObject orderInfo = new JSONObject();
        orderInfo.put("orderSn" , orderInfoSeller.getOrderSn());
        orderInfo.put("orderType" , orderInfoSeller.getOrderType().getName());
        orderInfo.put("orderStatus" , orderInfoSeller.getOrderStatus().getName());
        orderInfo.put("orderInfoPrice" , orderInfoSeller.getPrice());
        orderInfo.put("createdTime" , DateUtil.formatDateTime(orderInfoSeller.getCreatedTime()));
        orderInfo.put("returnImgOrFile" , orderInfoSeller.getReturnImg());
        orderInfo.put("returnUrl" , orderInfoSeller.getReturnUrl());
        orderInfo.put("payable" , orderInfoSeller.getPayable());
        if(orderInfoSeller.getOrderType().getIndex() == EnumOrderSellerType.定制内容.getIndex()){
            orderInfo.put("authorImg" ,orderInfoSeller.getSnapshotYrAuthor() == null ? "" : orderInfoSeller.getSnapshotYrAuthor().getAuthorImg());
            orderInfo.put("authorNickname" ,orderInfoSeller.getSnapshotYrAuthor() == null ? "" : orderInfoSeller.getSnapshotYrAuthor().getAuthorNickname());
            try {
                orderInfo.put("finishTime" ,  orderInfoSeller.getReturnTime() == null ? "" :
                        com.yuanrong.common.util.DateUtil.format(orderInfoSeller.getReturnTime(),"yyyy-MM-dd HH:mm:ss"));
                orderInfo.put("executeTime" , orderInfoSeller.getExecuteTime() == null ? "" : com.yuanrong.common.util.DateUtil.format(orderInfoSeller.getExecuteTime(),"yyyy-MM-dd"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            orderInfo.put("referencePrice" ,orderInfoSeller.getSnapshotYrAuthor() == null ? "" : orderInfoSeller.getSnapshotYrAuthor().getPriceInfo());
            orderInfo.put("priceName" , orderInfoSeller.getProduction());
        }else if(orderInfoSeller.getOrderType().getIndex() == EnumOrderSellerType.营销分发.getIndex()){
            orderInfo.put("accountID" ,orderInfoSeller.getSnapshotAccount() == null ? "" : orderInfoSeller.getSnapshotAccount().getAccountID());
            orderInfo.put("accountName" , orderInfoSeller.getSnapshotAccount() == null ? "" :orderInfoSeller.getSnapshotAccount().getName());
            orderInfo.put("accountImg" ,orderInfoSeller.getSnapshotAccount() == null ? "" : orderInfoSeller.getSnapshotAccount().getHeadImageUrlLocal());
            orderInfo.put("platformLogo" ,orderInfoSeller.getSnapshotAccount() == null ? "" : orderInfoSeller.getSnapshotAccount().getPlatformLogo());
            orderInfo.put("platformName" ,orderInfoSeller.getSnapshotAccount() == null ? "" : orderInfoSeller.getSnapshotAccount().getPlatformName());
            orderInfo.put("referencePrice" ,orderInfoSeller.getSnapshotAccount() == null ? "" : JSONObject.parseObject(orderInfoSeller.getSnapshotAccount().getPriceInfo()));
            orderInfo.put("priceName" ,orderInfoSeller.getProduction());
            orderInfo.put("fans" , orderInfoSeller.getSnapshotAccount() == null ? "" :orderInfoSeller.getSnapshotAccount().getFans());
            try {
                orderInfo.put("finishTime" ,  orderInfoSeller.getReturnTime() == null ? "" :
                        com.yuanrong.common.util.DateUtil.format(orderInfoSeller.getReturnTime(),"yyyy-MM-dd HH:mm:ss"));
                orderInfo.put("executeTime" , orderInfoSeller.getExecuteTime() == null ? "" : com.yuanrong.common.util.DateUtil.format(orderInfoSeller.getExecuteTime(),"yyyy-MM-dd"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(orderInfoSeller.getOrderType().getIndex() == EnumOrderSellerType.作品订单.getIndex()){
            //参考报价
            orderInfo.put("referencePrice" , orderInfoSeller.getSnapshotYrProduction().getProductQuotedPrice());
            orderInfo.put("productName" , orderInfoSeller.getProduction());
            orderInfo.put("productUrl" , "http://www.yuanrongbank.com/contentBank/article_detail_"+orderInfoSeller.getSnapshotYrProduction().getYrProductionId()+".html");
        }
        result.put("orderInfo" , orderInfo);

        if(orderInfoSeller.getOrderType().getIndex() != EnumOrderSellerType.作品订单.getIndex()){
            JSONObject demandInfo = new JSONObject();
            demandInfo.put("demandName" , orderInfoSeller.getDemand().getDemandName());
            demandInfo.put("contentForms" , orderInfoSeller.getDemand().getContentForms());
            demandInfo.put("scenes" , orderInfoSeller.getDemand().getScenes());
            demandInfo.put("yrCategory" , orderInfoSeller.getDemand().getYrCategory());
            try {
                demandInfo.put("expectedTime" ,StringUtil.isBlank(orderInfoSeller.getDemand().getExpectedTime()) ? "":
                        com.yuanrong.common.util.DateUtil.format(orderInfoSeller.getDemand().getExpectedTime(), "yyyy-MM-dd"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            demandInfo.put("remark" , orderInfoSeller.getDemand().getRemark());
            demandInfo.put("attachment" , orderInfoSeller.getDemand().getAttachment());
            try {
                demandInfo.put("spreadTime" ,StringUtil.isBlank(orderInfoSeller.getDemand().getSpreadTime()) ? "" :
                        com.yuanrong.common.util.DateUtil.format(orderInfoSeller.getDemand().getSpreadTime(),"yyyy-MM-dd"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            demandInfo.put("budgetMoney" , orderInfoSeller.getDemand().getBudgetMoney());
            result.put("demandInfo" , demandInfo);
        }

        return new ResultTemplate(result);
    }
}
