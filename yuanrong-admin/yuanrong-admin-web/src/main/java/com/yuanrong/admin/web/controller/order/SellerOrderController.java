package com.yuanrong.admin.web.controller.order;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumOrderSellerStatus;
import com.yuanrong.admin.Enum.EnumOrderSellerType;
import com.yuanrong.admin.Enum.EnumSellerOrderStatus;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.bean.order.SellerOrder;
import com.yuanrong.admin.result.OrderInfoSellerResult;
import com.yuanrong.admin.result.SellerOrderResult;
import com.yuanrong.admin.seach.SellerOrderSearch;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * 卖家订单的controller
 * Created MDA
 */
@Controller
@RequestMapping("order")
public class SellerOrderController extends BaseController {
    private static final Logger logger = Logger.getLogger(SellerOrderController.class);

    @RequestMapping( value = "sellerOrder_list" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(SellerOrderSearch data , BaseModel baseModel, HttpSession session){
        data.setRegisteredUserInfoId(getWebUser(session).getRecID());
        PageInfo<SellerOrder> sellerOrderPageInfo = sellerOrderServicesI.list(data , baseModel);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(sellerOrderPageInfo.getList()) > 0){
            for(SellerOrder sellerOrder : sellerOrderPageInfo.getList()){
                JSONObject ele = new JSONObject();
                ele.put("sellerOrderSn" , sellerOrder.getOrderSn());
                ele.put("orderType" , sellerOrder.getEnumOrderSellerType().getName());
                ele.put("orderTypeIndex" , sellerOrder.getEnumOrderSellerType().getIndex());
                ele.put("orderPrice",sellerOrder.getPrice());
                ele.put("payable" , sellerOrder.getPayable());
                ele.put("orderStatus" , sellerOrder.getEnumSellerOrderStatus());
                ele.put("orderStatusIndex",sellerOrder.getEnumSellerOrderStatus().getIndex());
                ele.put("createdTime", DateUtil.formatDateTime(sellerOrder.getCreatedTime()));
                if(sellerOrder.getEnumOrderSellerType().getIndex() == EnumOrderSellerType.定制内容.getIndex()){
                    ele.put("authorImg" , sellerOrder.getSnapshotYrAuthor().getAuthorImg());
                    ele.put("authorNickname",sellerOrder.getSnapshotYrAuthor().getAuthorNickname());
                    ele.put("priceName" , sellerOrder.getProduction());
                    ele.put("enrolOrderSn" , sellerOrder.getOrderInfoSeller().getOrderSn());
                    ele.put("returnImg",sellerOrder.getOrderInfoSeller().getReturnImg());
                    ele.put("returnUrl",sellerOrder.getOrderInfoSeller().getReturnUrl());
                    ele.put("demandName" , sellerOrder.getDemand().getDemandName());
                }else if(sellerOrder.getEnumOrderSellerType().getIndex() == EnumOrderSellerType.营销分发.getIndex()){
                    ele.put("enrolOrderSn" , sellerOrder.getOrderInfoSeller().getOrderSn());
                    ele.put("accountImg" , sellerOrder.getSnapshotAccount().getHeadImageUrlLocal());
                    ele.put("accountName",sellerOrder.getSnapshotAccount().getName());
                    ele.put("priceName" , sellerOrder.getProduction());
                    ele.put("platformLogo" , sellerOrder.getSnapshotAccount().getPlatformLogo());
                    ele.put("platformName" , sellerOrder.getSnapshotAccount().getPlatformName());
                    ele.put("returnImg",sellerOrder.getOrderInfoSeller().getReturnImg());
                    ele.put("returnUrl",sellerOrder.getOrderInfoSeller().getReturnUrl());
                    ele.put("demandName" , sellerOrder.getDemand().getDemandName());
                }else if(sellerOrder.getEnumOrderSellerType().getIndex() == EnumOrderSellerType.原创征稿.getIndex()){
                    ele.put("enrolOrderSn" , sellerOrder.getOrderInfoSeller().getOrderSn());
                    ele.put("imgNum",sellerOrder.getSnapshotYrProduction().getImgNum());
                    ele.put("wordNum",sellerOrder.getSnapshotYrProduction().getWordNum());
                    ele.put("production" , sellerOrder.getSnapshotYrProduction().getTitle());
                    ele.put("yrProductionId",sellerOrder.getSnapshotYrProduction().getYrProductionId());
                    ele.put("returnImg",sellerOrder.getOrderInfoSeller().getReturnImg());
                    ele.put("returnUrl",sellerOrder.getOrderInfoSeller().getReturnUrl());
                    ele.put("demandName" , sellerOrder.getDemand().getDemandName());
                }else if(sellerOrder.getEnumOrderSellerType().getIndex() == EnumOrderSellerType.作品订单.getIndex()){
                    ele.put("productionTitle" , sellerOrder.getSnapshotYrProduction().getTitle());
                    ele.put("imgNum" , sellerOrder.getSnapshotYrProduction().getImgNum());
                    ele.put("wordNum" , sellerOrder.getSnapshotYrProduction().getWordNum());
                    ele.put("yrProductionId",sellerOrder.getSnapshotYrProduction().getYrProductionId());
                }
                ele.put("cancelReason",sellerOrder.getCancelReason());
                result.add(ele);
            }
        }
        return new ResultTemplate(sellerOrderPageInfo , result);
    }

    @RequestMapping( value = "sellerOrder/getDetailBySn" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getDetailByOrderSn(SellerOrderSearch sellerOrderSearch ){
        if(StringUtil.isBlank(sellerOrderSearch.getOrderSn())){
            return new ResultTemplate("参数错误");
        }
        sellerOrderSearch.setRegisteredUserInfoId(getWebUser().getRecID());
        SellerOrderResult sellerOrderResult = sellerOrderServicesI.getDetailBySn(sellerOrderSearch);
        return this.detail(sellerOrderResult);
    }

    @RequestMapping( value = "enroll/getDetailBySn" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getEnrollDetailByOrderSn(SellerOrderSearch sellerOrderSearch){
        if(StringUtil.isBlank(sellerOrderSearch.getSerialOrderSn())){
            return new ResultTemplate("参数错误");
        }
        sellerOrderSearch.setRegisteredUserInfoId(getWebUser().getRecID());
        OrderInfoSellerResult orderInfoSeller = orderInfoSellerServicesI.getDetailUnConfirmBySn(sellerOrderSearch.getSerialOrderSn(),getWebUser().getRecID());
        if(orderInfoSeller == null){
            return new ResultTemplate("报名号不存在");
        }
        if(orderInfoSeller.getOrderStatus().getIndex() != EnumOrderSellerStatus.已生成订单.getIndex()){
            JSONObject result = new JSONObject();

            //返回订单信息
            JSONObject orderInfo = new JSONObject();
            if(orderInfoSeller.getOrderType().getIndex() == EnumOrderSellerType.原创征稿.getIndex() ){
                orderInfo.put("productTitle" , orderInfoSeller.getyRProduction().getTitle());
                JSONObject proJson = new JSONObject();
                proJson.put("wordNum" , orderInfoSeller.getyRProduction().getWordNum());
                proJson.put("imgNum" , orderInfoSeller.getyRProduction().getImgNum());
                orderInfo.put("productionInfo",proJson);
                orderInfo.put("enrollTime" , DateUtil.formatDateTime(orderInfoSeller.getCreatedTime()));
            }else if(orderInfoSeller.getOrderType().getIndex() == EnumOrderSellerType.营销分发.getIndex()){
                JSONObject accountInfo = new JSONObject();
                accountInfo.put("accountName" , orderInfoSeller.getPlatformIPAccount().getName());
                accountInfo.put("accountID" , orderInfoSeller.getPlatformIPAccount().getAccountID());
                accountInfo.put("platformName",orderInfoSeller.getPlatformIPAccount().getPlatformName());
                accountInfo.put("platformLogo" , orderInfoSeller.getPlatformIPAccount().getIcoUrl());
                accountInfo.put("accountImg" , orderInfoSeller.getPlatformIPAccount().getHeadImageUrlLocal());

                orderInfo.put("accountInfo" ,accountInfo);
            }else if(orderInfoSeller.getOrderType().getIndex() == EnumOrderSellerType.定制内容.getIndex()){
                JSONObject authorInfo = new JSONObject();
                authorInfo.put("authorNickname" , orderInfoSeller.getyRAuthor().getAuthorNickname());
                authorInfo.put("authorImg" , orderInfoSeller.getyRAuthor().getAuthorImg());
                result.put("authorInfo" , authorInfo);
            }
            orderInfo.put("sellerServiceRate",orderInfoSeller.getSellerServiceRate());
            //返回报价项信息
            orderInfo.put("offerPrice" ,  SellerOrderResult.getJSONOfferPriceByString(orderInfoSeller.getEnrollOfferPrice()));

            orderInfo.put("usableDate",orderInfoSeller.getUsableDate());
            //选了的-可用排期
            orderInfo.put("executeTime",orderInfoSeller.getExecuteTime());

            //拒约原因
            orderInfo.put("rejectReason",orderInfoSeller.getRejectReason());
            //拒约备注
            orderInfo.put("acceptRemark",orderInfoSeller.getAcceptRemark());
            orderInfo.put("refuseReason",orderInfoSeller.getRefuseReason());

            orderInfo.put("returnUrl" , orderInfoSeller.getReturnUrl());
            orderInfo.put("returnImg" , orderInfoSeller.getReturnImg());

            if(orderInfoSeller.getOrderType().getIndex() == EnumOrderSellerType.原创征稿.getIndex()){
                orderInfo.put("sellerPrice" , orderInfoSeller.getSellerPrice());
                orderInfo.put("payable" , orderInfoSeller.getSellerPrice().subtract(orderInfoSeller.getSellerPrice().multiply(new BigDecimal("0.01").multiply(orderInfoSeller.getSellerServiceRate()))));
            }

            orderInfo.put("offerPriceCollection" ,orderInfoSeller.getSellerPrice() );

            result.put("orderInfo" , orderInfo);

                //返回需求信息
            JSONObject demand = new JSONObject();
            demand.put("demandName",orderInfoSeller.getDemand().getDemandName());
            demand.put("budgetMoney" ,orderInfoSeller.getDemand().getBudgetMoney());
            demand.put("expectedTime",orderInfoSeller.getDemand().getExpectedTime());
            demand.put("contentForms",orderInfoSeller.getDemand().getContentForms());
            demand.put("requireWordNum",orderInfoSeller.getDemand().getRequireWordNum());
            demand.put("requireSeconds",orderInfoSeller.getDemand().getRequireSeconds());
            demand.put("remark",orderInfoSeller.getDemand().getRemark());
            demand.put("attachment",orderInfoSeller.getDemand().getAttachment());
            demand.put("referURL",orderInfoSeller.getDemand().getReferURL());
            demand.put("fans" ,orderInfoSeller.getDemand().getFans());
            demand.put("demandType",orderInfoSeller.getDemand().getEnumDemandType().getName());
            demand.put("demandTypeIndex",orderInfoSeller.getDemand().getDemandTypeIndex());
            demand.put("expectNum",orderInfoSeller.getDemand().getExpectNum());
            demand.put("tradeName",orderInfoSeller.getDemand().getTradeDictinfo() == null ? "" : orderInfoSeller.getDemand().getTradeDictinfo().getName());
            demand.put("demandStatus",orderInfoSeller.getDemand().getEnumDemandStatus());
            demand.put("demandStatusIndex",orderInfoSeller.getDemand().getEnumDemandStatus().getIndex());
            demand.put("isShow" , orderInfoSeller.getDemand().getIsShow());
            demand.put("spreadTime" , orderInfoSeller.getDemand().getSpreadTime());
            demand.put("platformName" , orderInfoSeller.getDemand().getPlatformName());

            demand.put("scenes" , orderInfoSeller.getDemand().getScenes());
            demand.put("yrCategory" , orderInfoSeller.getDemand().getYrCategory());
            demand.put("serialOrderSn" , orderInfoSeller.getOrderSn());
            demand.put("serialStatus",orderInfoSeller.getOrderStatus());
            demand.put("serialStatusIndex",orderInfoSeller.getOrderStatusValue());

            demand.put("serialTime" , DateUtil.formatDateTime(orderInfoSeller.getCreatedTime()));
            demand.put("sellerServiceRate" , orderInfoSeller.getSellerServiceRate());
            result.put("demandInfo" , demand);

            return new ResultTemplate(result);


        }else{
            SellerOrderResult sellerOrderResult = orderInfoSellerServicesI.getDetailBySn(sellerOrderSearch);
            return this.detail(sellerOrderResult);
        }

    }

    private ResultTemplate detail(SellerOrderResult sellerOrderResult){
        if(sellerOrderResult == null){
            return new ResultTemplate("该订单不存在");
        }
        JSONObject result = new JSONObject();

        //返回订单信息
        JSONObject orderInfo = new JSONObject();
        if(sellerOrderResult.getOrderInfoSeller().getOrderStatus().getIndex() == EnumOrderSellerStatus.已生成订单.getIndex()){
            orderInfo.put("orderSn" , sellerOrderResult.getOrderSn());
            orderInfo.put("orderAmount" , sellerOrderResult.getPrice());
            orderInfo.put("payable" , sellerOrderResult.getPayable());
            orderInfo.put("orderStatus" , sellerOrderResult.getEnumSellerOrderStatus());
            orderInfo.put("orderStatusIndex" , sellerOrderResult.getEnumSellerOrderStatus().getIndex());
            orderInfo.put("sellerServiceRate",sellerOrderResult.getSellerServiceRate());
        }
        if(sellerOrderResult.getOrderInfoSeller().getOrderType().getIndex() == EnumOrderSellerType.原创征稿.getIndex() ){
            orderInfo.put("productTitle" , sellerOrderResult.getProduction());
            JSONObject proJson = new JSONObject();
            proJson.put("wordNum" , sellerOrderResult.getOrderDetail().getSnapshotYrProduction().getWordNum());
            proJson.put("imgNum" , sellerOrderResult.getOrderDetail().getSnapshotYrProduction().getImgNum());

            orderInfo.put("offerPriceCollection",sellerOrderResult.getPrice());
            orderInfo.put("productionInfo",proJson);
            orderInfo.put("enrollTime" , DateUtil.formatDateTime(sellerOrderResult.getOrderInfoSeller().getCreatedTime()));
        }else if(sellerOrderResult.getOrderInfoSeller().getOrderType().getIndex() == EnumOrderSellerType.作品订单.getIndex()){
            orderInfo.put("productTitle" , sellerOrderResult.getProduction());
        }else if(sellerOrderResult.getOrderInfoSeller().getOrderType().getIndex() == EnumOrderSellerType.营销分发.getIndex()){
            JSONObject accountInfo = new JSONObject();
            accountInfo.put("accountName" , sellerOrderResult.getOrderDetail().getSnapshotAccount().getName());
            accountInfo.put("accountID" , sellerOrderResult.getOrderDetail().getSnapshotAccount().getAccountID());
            accountInfo.put("platformName",sellerOrderResult.getOrderDetail().getSnapshotAccount().getPlatformName());
            accountInfo.put("platformLogo" , sellerOrderResult.getOrderDetail().getSnapshotAccount().getPlatformLogo());
            accountInfo.put("accountImg" , sellerOrderResult.getOrderDetail().getSnapshotAccount().getHeadImageUrlLocal());

            orderInfo.put("priceInfo",sellerOrderResult.getOrderDetail().getProduction());
            orderInfo.put("accountInfo" ,accountInfo);
        }else if(sellerOrderResult.getOrderInfoSeller().getOrderType().getIndex() == EnumOrderSellerType.定制内容.getIndex()){
            JSONObject authorInfo = new JSONObject();
            authorInfo.put("authorNickname" , sellerOrderResult.getOrderDetail().getSnapshotYrAuthor().getAuthorNickname());
            authorInfo.put("authorImg" , sellerOrderResult.getOrderDetail().getSnapshotYrAuthor().getAuthorImg());
            orderInfo.put("priceInfo",sellerOrderResult.getOrderDetail().getProduction());
            result.put("authorInfo" , authorInfo);
        }
        orderInfo.put("cancelReason",sellerOrderResult.getCancelReason());
        result.put("orderInfo" , orderInfo);

        if( sellerOrderResult.getOrderInfoSeller().getOrderType().getIndex() != EnumOrderSellerType.作品订单.getIndex()){
            //返回需求信息
            JSONObject demand = new JSONObject();
            demand.put("demandName",sellerOrderResult.getOrderInfoSeller().getDemand().getDemandName());
            demand.put("budgetMoney" ,sellerOrderResult.getOrderInfoSeller().getDemand().getBudgetMoney());
            demand.put("expectedTime",sellerOrderResult.getOrderInfoSeller().getDemand().getExpectedTime());
            demand.put("contentForms",sellerOrderResult.getOrderInfoSeller().getDemand().getContentForms());
            demand.put("requireWordNum",sellerOrderResult.getOrderInfoSeller().getDemand().getRequireWordNum());
            demand.put("requireSeconds",sellerOrderResult.getOrderInfoSeller().getDemand().getRequireSeconds());
            demand.put("remark",sellerOrderResult.getOrderInfoSeller().getDemand().getRemark());
            demand.put("attachment",sellerOrderResult.getOrderInfoSeller().getDemand().getAttachment());
            demand.put("referURL",sellerOrderResult.getOrderInfoSeller().getDemand().getReferURL());
            demand.put("fans" ,sellerOrderResult.getOrderInfoSeller().getDemand().getFans());
            demand.put("demandType",sellerOrderResult.getOrderInfoSeller().getDemand().getEnumDemandType().getName());
            demand.put("demandTypeIndex",sellerOrderResult.getOrderInfoSeller().getDemand().getDemandTypeIndex());
            demand.put("expectNum",sellerOrderResult.getOrderInfoSeller().getDemand().getExpectNum());
            demand.put("tradeName",sellerOrderResult.getOrderInfoSeller().getDemand().getTradeDictinfo() == null ? "" : sellerOrderResult.getOrderInfoSeller().getDemand().getTradeDictinfo().getName());
            demand.put("demandStatus",sellerOrderResult.getOrderInfoSeller().getDemand().getEnumDemandStatus());
            demand.put("demandStatusIndex",sellerOrderResult.getOrderInfoSeller().getDemand().getEnumDemandStatus().getIndex());
            demand.put("isShow" , sellerOrderResult.getOrderInfoSeller().getDemand().getIsShow());

            demand.put("spreadTime" , sellerOrderResult.getOrderInfoSeller().getDemand().getSpreadTime());

            demand.put("scenes" , sellerOrderResult.getOrderInfoSeller().getDemand().getScenes());
            demand.put("yrCategory" , sellerOrderResult.getOrderInfoSeller().getDemand().getYrCategory());
            demand.put("serialOrderSn" , sellerOrderResult.getOrderInfoSeller().getOrderSn());
            demand.put("serialStatus",sellerOrderResult.getOrderInfoSeller().getOrderStatus());
            demand.put("serialStatusIndex",sellerOrderResult.getOrderInfoSeller().getOrderStatusValue());

            demand.put("platformName",sellerOrderResult.getOrderInfoSeller().getDemand().getPlatformName());

            demand.put("serialTime" , DateUtil.formatDateTime(sellerOrderResult.getOrderInfoSeller().getCreatedTime()));
            demand.put("sellerServiceRate" , sellerOrderResult.getOrderInfoSeller().getSellerServiceRate());
            result.put("demandInfo" , demand);

            //返回报价项信息
            orderInfo.put("offerPrice" , sellerOrderResult.getJSONOfferPrice());

            orderInfo.put("usableDate",sellerOrderResult.getOrderInfoSeller().getUsableDate());
            //选了的-可用排期
            orderInfo.put("executeTime",sellerOrderResult.getOrderInfoSeller().getExecuteTime());
            //应约备注
            orderInfo.put("acceptRemark" , sellerOrderResult.getOrderInfoSeller().getAcceptRemark());

            orderInfo.put("returnUrl" , sellerOrderResult.getOrderInfoSeller().getReturnUrl());
            orderInfo.put("returnImg" , sellerOrderResult.getOrderInfoSeller().getReturnImg());

        }
        return new ResultTemplate(result);
    }

    @RequestMapping("seller/orderStatus")
    @ResponseBody
    public ResultTemplate getSellerOrderStatus(){
        return new ResultTemplate(EnumSellerOrderStatus.getMapInfo());
    }

    @RequestMapping("seller/orderType")
    @ResponseBody
    public ResultTemplate getSellerOrderType(){
        return new ResultTemplate(EnumOrderSellerType.getMapInfo());
    }

}
