package com.yuanrong.admin.result;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumOrderSellerStatus;
import com.yuanrong.admin.Enum.EnumOrderSellerType;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.OrderTotalAmountRoundUtil;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.common.util.SystemParam;

import java.math.BigDecimal;

/**
 * Created by zhonghang on 2018/7/20.
 */
public class DemandSignListResult extends OrderInfoSeller {

    /**
     * 订单金额
     */
    private String moneyDetail;
    public String getMoneyDetail() {
        return moneyDetail;
    }

    public void setMoneyDetail(String moneyDetail) {
        this.moneyDetail = moneyDetail;
    }

    /**
     * 封装数据—后台—通过需求Id获取报名列表
     * @param signInfo
     * @return
     */
    public static JSONObject packageSignData(DemandSignListResult signInfo,String invoiceFee) {
        JSONObject result = new JSONObject();
        //报名Id
        result.put("orderInfoSellerId", signInfo.getOrderInfoSellerId());
        //报名号
        result.put("orderSn", signInfo.getOrderSn());
        //报名状态id
        result.put("orderStatus", signInfo.getOrderStatusValue());
        //报名状态
        result.put("orderStatusValue", signInfo.getOrderStatus().getName());
        if (signInfo.getOrderStatusValue() != null && signInfo.getOrderStatusValue().compareTo(EnumOrderSellerStatus.卖家拒约.getIndex()) == 0) {
            result.put("rejectReason", signInfo.getRejectReason());//拒约原因
            result.put("rejectRemark", signInfo.getRejectRemark());//拒约备注
        }
        if (signInfo.getOrderStatusValue() != null && signInfo.getOrderStatusValue().compareTo(EnumOrderSellerStatus.买家拒绝.getIndex()) == 0) {
            result.put("refuseReason", StringUtil.isBlank(signInfo.getRefuseReason()) ? "买家拒绝" : signInfo.getRefuseReason());//拒绝原因
        }
        //用户简称
        result.put("userNickName", signInfo.getSaler() == null ? "" : signInfo.getSaler().getNickName());
        //用户Id
        result.put("userId", signInfo.getSaler() == null ? "" : signInfo.getSaler().getRecID());
        //创作耗时、可用排期
        result.put("usableDate",signInfo.getUsableDate());
        //应约备注
        result.put("acceptRemark", signInfo.getAcceptRemark());
        //媒介经理
        result.put("mediaName", signInfo.getMedia() == null ? "" : signInfo.getMedia().getRealName());
        //报名时间
        result.put("createdTime", signInfo.getCreatedTime() == null ? "" : DateUtil.format(signInfo.getCreatedTime(),"yyyy-MM-dd HH:mm:ss"));
        if (signInfo.getOrderTypeValue().compareTo(EnumOrderSellerType.营销分发.getIndex()) == 0) {
            //账号Id
            result.put("accountId", signInfo.getPlatformIPAccount() == null ? "" : signInfo.getPlatformIPAccount().getId());
            //账号名称
            result.put("accountName", signInfo.getPlatformIPAccount() == null ? "" : signInfo.getPlatformIPAccount().getName());
            //账号头图
            result.put("accountImage", signInfo.getPlatformIPAccount() == null ? "" : signInfo.getPlatformIPAccount().getHeadImageUrlLocal());
            //账号平台图标
            result.put("accountPlatIco", signInfo.getPlatformIPAccount() == null || signInfo.getPlatformIPAccount().getShortVideoPlatformInfo() == null? "" : signInfo.getPlatformIPAccount().getShortVideoPlatformInfo().getIcoUrl());
            //账号平台名称
            result.put("accountPlatName", signInfo.getPlatformIPAccount() == null || signInfo.getPlatformIPAccount().getShortVideoPlatformInfo() == null? "" : signInfo.getPlatformIPAccount().getShortVideoPlatformInfo().getPlatformname());
        }else if (signInfo.getOrderTypeValue().compareTo(EnumOrderSellerType.定制内容.getIndex()) == 0){
            //创作者Id
            result.put("authorId", signInfo.getyRAuthor() == null ? "" : signInfo.getyRAuthor().getRecId());
            //创作者名称
            result.put("authorNickname", signInfo.getyRAuthor() == null ? "" : signInfo.getyRAuthor().getAuthorNickname());
            //创作者头像
            result.put("authorImg", signInfo.getyRAuthor() == null ? "" : signInfo.getyRAuthor().getAuthorImg());
        }else if(signInfo.getOrderTypeValue().compareTo(EnumOrderSellerType.原创征稿.getIndex()) == 0){
            //作品Id
            result.put("productId", signInfo.getyRProduction() == null ? "" : signInfo.getyRProduction().getRecId());
            //作品状态
            result.put("yrProductionStatusIndex",signInfo.getyRProduction() == null ? "" : signInfo.getyRProduction().getYrProductionStatusIndex());
            //作品状态
            result.put("yrProductionStatus",signInfo.getyRProduction() == null ? "" : signInfo.getyRProduction().getYrProductionStatus().getName());
            //作品标题
            result.put("productTitle", signInfo.getyRProduction() == null ? "" : signInfo.getyRProduction().getTitle());
            //作品字数
            result.put("productWordNum", signInfo.getyRProduction() == null ? "" : signInfo.getyRProduction().getWordNum());
            //作品图片数
            result.put("productImgNum", signInfo.getyRProduction() == null ? "" : signInfo.getyRProduction().getImgNum());
            //作品原创度
            result.put("originalScore", signInfo.getyRProduction() == null ? "" : signInfo.getyRProduction().getOriginalScore());
            //作品圆融指数
            result.put("contentEvaluationScore", signInfo.getyRProduction() == null ? "" : signInfo.getyRProduction().getContentEvaluationScore());
            /*//作品金额
            BigDecimal costPrice = signInfo.getReferPrice() == null ? new BigDecimal("0") : signInfo.getReferPrice();//成本价
            //买家服务费率
            BigDecimal buyerFreeRate = signInfo.getBuyerServiceRate() == null ? new BigDecimal("0") : signInfo.getBuyerServiceRate().divide(new BigDecimal("100"));
            BigDecimal orderPrice = OrderTotalAmountRoundUtil.getRoundAmount(costPrice.add(costPrice.multiply(buyerFreeRate)));*/
            //订单金额
            BigDecimal orderPrice = signInfo.getBuyerOrderPrice() == null ? BigDecimal.ZERO : signInfo.getBuyerOrderPrice();
            //发票
            BigDecimal invoiceFeePercent = new BigDecimal(invoiceFee).divide(new BigDecimal("100"));
            //税额
            BigDecimal invoice = orderPrice.multiply(invoiceFeePercent).setScale(2,BigDecimal.ROUND_UP);
            //总计
            BigDecimal sumMoney = orderPrice.add(invoice);
            JSONObject price = new JSONObject();
            price.put("orderPrice",orderPrice);
            price.put("invoice",invoice);
            price.put("sumMoney",sumMoney);
            result.put("moneyDetail",price);
        }
        if(signInfo.getOrderTypeValue().compareTo(EnumOrderSellerType.营销分发.getIndex()) == 0
                || signInfo.getOrderTypeValue().compareTo(EnumOrderSellerType.定制内容.getIndex()) == 0){
            //订单金额
            String moneyDetail = signInfo.getMoneyDetail();
            if(StringUtil.isNotBlank(moneyDetail) && moneyDetail.split("_-_").length > 0){
                JSONArray price = new JSONArray();
                String[] split = moneyDetail.split("_-_");
                for (String str : split){
                    String[] spl = str.split("-_-");
                    if(spl.length > 0){
                        JSONObject object = new JSONObject();
                        object.put("priceName",spl[0]);
                        object.put("executePrice",spl[2]);
                        BigDecimal invoice = new BigDecimal(spl[2]).multiply(new BigDecimal(invoiceFee).divide(new BigDecimal("100"))).setScale(2,BigDecimal.ROUND_UP);
                        object.put("sumPrice",new BigDecimal(spl[2]).add(invoice));
                        price.add(object);
                    }
                }
                result.put("moneyDetail",price);
            }
        }
        return result;
    }
}
