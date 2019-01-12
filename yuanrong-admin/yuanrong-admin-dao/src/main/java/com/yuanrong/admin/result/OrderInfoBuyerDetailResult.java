package com.yuanrong.admin.result;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.yuanrong.admin.Enum.EnumPayStatus;
import com.yuanrong.admin.Enum.EnumPublishStatus;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.SystemParam;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 购买作品列表返回结果
 * @ClassName OrderInfoBuyerListResult
 * @Author Jss
 * @Date 2018/6/30
 */
public class OrderInfoBuyerDetailResult extends OrderInfoSeller implements Serializable {

    /**
     * 用户Id
     */
    private Integer userId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 创作者
     */
    private String yrAuthorName;
    /**
     * 作品发布状态
     */
    private Integer publishStatusValue;
    /**
     * 作品名称
     */
    private String title;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getYrAuthorName() {
        return yrAuthorName;
    }

    public void setYrAuthorName(String yrAuthorName) {
        this.yrAuthorName = yrAuthorName;
    }

    public Integer getPublishStatusValue() {
        return publishStatusValue;
    }

    public void setPublishStatusValue(Integer publishStatusValue) {
        this.publishStatusValue = publishStatusValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 数据封装
     * @param orderList
     * @param invoicePercent
     * @return
     */
    public static JSONObject packageOrderSellerInfo(List<OrderInfoBuyerDetailResult> orderList,String invoicePercent) {
        JSONObject result = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonNum = new JSONObject();
        BigDecimal costMoney = new BigDecimal("0");
        BigDecimal authorMoney = new BigDecimal("0");
        for (OrderInfoBuyerDetailResult orderInfo : orderList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderSn",orderInfo.getOrderSn());
            jsonObject.put("title",orderInfo.getTitle());
            jsonObject.put("publishStatusIndex",orderInfo.getPublishStatusValue());
            jsonObject.put("publishStatus",orderInfo.getPublishStatusValue() == null ? "" : EnumUtil.valueOf(EnumPublishStatus.class,orderInfo.getPublishStatusValue()).getName());
            jsonObject.put("authorName",orderInfo.getYrAuthorName());
            JSONArray json = new JSONArray();
            JSONObject costPrice = new JSONObject();
            costPrice.put("PriceName","成本价");
            costPrice.put("Price",orderInfo.getPrice());
            json.add(costPrice);
            JSONObject authorIncome = new JSONObject();
            authorIncome.put("PriceName","作者收入");
            authorIncome.put("Price",orderInfo.getPayable());
            json.add(authorIncome);
            jsonObject.put("orderPrice",json);
            jsonObject.put("nickName",orderInfo.getNickName());
            jsonObject.put("userId",orderInfo.getUserId());
            jsonArray.add(jsonObject);
            costMoney = costMoney.add(orderInfo.getPrice());
            authorMoney = authorMoney.add(orderInfo.getPayable());
        }
        jsonNum.put("productNum",orderList.size());
        jsonNum.put("costTotalPrice",costMoney.doubleValue());
        jsonNum.put("authorMoney",authorMoney.doubleValue());
//        System.out.println("++++++++" + Double.parseDouble(invoicePercent)/Double.parseDouble("100"));
//        System.out.println("________" + BigDecimal.ONE.subtract(new BigDecimal(invoicePercent).divide(new BigDecimal("100"))));
//        System.out.println("---------" + costMoney.divide(BigDecimal.ONE.subtract(new BigDecimal(invoicePercent).divide(new BigDecimal("100"))),0,BigDecimal.ROUND_UP));
        jsonNum.put("invoiceMoney",costMoney.divide(new BigDecimal(1 - Double.parseDouble(invoicePercent)/Double.parseDouble("100")),0, BigDecimal.ROUND_UP));
        result.put("orderList",jsonArray);
        result.put("numAndMoney",jsonNum);
        return result;
    }
}
