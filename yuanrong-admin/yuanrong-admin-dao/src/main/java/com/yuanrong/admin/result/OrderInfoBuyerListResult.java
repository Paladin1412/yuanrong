package com.yuanrong.admin.result;

import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumOrderBuyerStatus;
import com.yuanrong.admin.Enum.EnumPayStatus;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.StringUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;

/**
 * 购买作品列表返回结果
 * @ClassName OrderInfoBuyerListResult
 * @Author Jss
 * @Date 2018/6/30
 */
public class OrderInfoBuyerListResult extends OrderInfoBuyer implements Serializable {

    /**
     * 用户Id
     */
    private Integer userId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 销售经理
     */
    private String saleUserName;
    /**
     * 媒介经理
     */
    private String mediaUserName;
    /**
     * 待支付状态订单的个数
     */
    private Integer readyPayNum;
    /**
     * 卖家用户
     */
    private RegisteredUserInfo sellerUser;

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

    public Integer getReadyPayNum() {
        return readyPayNum;
    }

    public void setReadyPayNum(Integer readyPayNum) {
        this.readyPayNum = readyPayNum;
    }

    public String getSaleUserName() {
        return saleUserName;
    }

    public void setSaleUserName(String saleUserName) {
        this.saleUserName = saleUserName;
    }

    public String getMediaUserName() {
        return mediaUserName;
    }

    public void setMediaUserName(String mediaUserName) {
        this.mediaUserName = mediaUserName;
    }

    public RegisteredUserInfo getSellerUser() {
        return sellerUser;
    }

    public void setSellerUser(RegisteredUserInfo sellerUser) {
        this.sellerUser = sellerUser;
    }

    /**
     * 数据封装
     * @param orderInfo
     * @return
     */
    public static JSONObject packageOrderBuyInfo(OrderInfoBuyerListResult orderInfo) {
        JSONObject jsonObject = new JSONObject();
        //订单Id
        jsonObject.put("orderInfoBuyerId",orderInfo.getOrderInfoBuyerId());
        //订单号
        jsonObject.put("orderSn",orderInfo.getOrderSn());
        //文章标题
        jsonObject.put("title",orderInfo.getSnapshotYrProduction() == null ?  "": orderInfo.getSnapshotYrProduction().getTitle());
        //文章标题—字数
        jsonObject.put("wordNum",orderInfo.getSnapshotYrProduction() == null ?  "": orderInfo.getSnapshotYrProduction().getWordNum());
        //文章标题—图片数
        jsonObject.put("imgNum",orderInfo.getSnapshotYrProduction() == null ?  "": orderInfo.getSnapshotYrProduction().getImgNum());
        //文章类型(公开-未公开)
        jsonObject.put("publishStatus",orderInfo.getSnapshotYrProduction() == null ?  "": orderInfo.getSnapshotYrProduction().getPublishStatus().getName());
        //订单状态
        jsonObject.put("orderStatusIndex",orderInfo.getOrderStatusValue());
        jsonObject.put("orderStatus",orderInfo.getOrderStatusValue()  == null ? "" : EnumUtil.valueOf(EnumOrderBuyerStatus.class,orderInfo.getOrderStatusValue()).getName());
        //支付状态
        jsonObject.put("payStatusIndex",orderInfo.getPayStatusValue());
        jsonObject.put("payStatus",orderInfo.getPayStatusValue()  == null ? "" : EnumUtil.valueOf(EnumPayStatus.class,orderInfo.getPayStatusValue()).getName());
        //销售经理
        jsonObject.put("saleUserName",orderInfo.getSaleUserName());
        //媒介经理
        jsonObject.put("mediaUserName",orderInfo.getMediaUserName());
        //买卖家
        //买家简称
        jsonObject.put("buyerName",orderInfo.getRegisteredUserInfo() == null ? "" : orderInfo.getRegisteredUserInfo().getNickName());
        //买家ID
        jsonObject.put("buyerId",orderInfo.getRegisteredUserInfoId());
        //卖家简称
        jsonObject.put("sellerName",orderInfo.getSellerUser() == null ? "" : orderInfo.getSellerUser().getNickName());
        //卖家ID
        jsonObject.put("sellerId",orderInfo.getSellerUser() == null ? "" : orderInfo.getSellerUser().getRecID());
        //订单购买时间
        jsonObject.put("createdTime",orderInfo.getCreatedTime() == null ? "" : DateUtil.format(orderInfo.getCreatedTime(),"yyyy-MM-dd HH:mm:ss"));
        //订单支付时间
        try {
            jsonObject.put("payTime",orderInfo.getPayTime() == null ? "" : DateUtil.format(orderInfo.getPayTime(),"yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //待支付数
        jsonObject.put("readyPayNum",orderInfo.getReadyPayNum());
        JSONObject object = new JSONObject();
        //支付金额—订单总金额
        object.put("sumCostMoney",orderInfo.getOrderDetail() == null ? 0 : orderInfo.getOrderDetail().getPrice());
        //支付金额—订单总税额
        String invoice = orderInfo.getMoneyDetail();
        BigDecimal sumInvoice = new BigDecimal("0");
        if(StringUtil.isNotBlank(invoice)){
            String[] split = invoice.split("-_-");
            for (String  str : split){
                if(str.split("_-_").length > 0){
                    String[] cost = str.split("_-_");
                    Integer costId = Integer.valueOf(cost[1]);
                    if("1".equals(cost[0])){//买家
                        if(costId == 242){//税额
                            sumInvoice = sumInvoice.add(new BigDecimal(cost[2]));
                        }
                    }
                }
            }
        }
        object.put("sumInvoice",sumInvoice);
        //支付金额—订单总计
        object.put("sumMoney",sumInvoice.add(object.getBigDecimal("sumCostMoney")));
        jsonObject.put("moneyDetail",object);
        return jsonObject;
    }
}
