package com.yuanrong.admin.result;

import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumCostType;
import com.yuanrong.admin.Enum.EnumDemandStatus;
import com.yuanrong.admin.Enum.EnumDemandType;
import com.yuanrong.admin.Enum.EnumOrderSellerType;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.order.OrderCostInfo;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

/**
 * 后台—普通需求订单列表返回结果
 * @ClassName DemandOrderListResult
 * @Author Jss
 * @Date 2018/9/30
 */
public class DemandOrderListResult extends OrderInfoBuyer implements Serializable {

    /**
     * 费用明细列表
     */
    private List<OrderCostInfo> costInfoList;
    /**
     * 媒介经理
     */
    private AdminUser mediaUser;

    public List<OrderCostInfo> getCostInfoList() {
        return costInfoList;
    }

    public void setCostInfoList(List<OrderCostInfo> costInfoList) {
        this.costInfoList = costInfoList;
    }

    public AdminUser getMediaUser() {
        return mediaUser;
    }

    public void setMediaUser(AdminUser mediaUser) {
        this.mediaUser = mediaUser;
    }

    /**
     * 需求——订单列表
     * @param infoSeller
     * @return
     */
    public static JSONObject packageOrderData(DemandOrderListResult infoSeller) {
        JSONObject result = new JSONObject();
        //订单ID
        result.put("orderInfoBuyerId",infoSeller.getOrderInfoBuyerId());
        //订单号
        result.put("orderSn",infoSeller.getOrderSn());
        //订单状态值
        result.put("orderStatusIndex",infoSeller.getOrderStatusValue());
        result.put("orderStatus",infoSeller.getOrderStatus().getName());
        //订单支付状态值
        result.put("payStatusIndex",infoSeller.getPayStatusValue());
        result.put("payStatus",infoSeller.getPayStatus().getName());
        //媒介经理
        result.put("mediaUserName",infoSeller.getMediaUser() == null ? "" : infoSeller.getMediaUser().getRealName());
        //订单生成时间
        result.put("orderCreateTime",infoSeller.getCreatedTime() == null ? "" : DateUtil.format(infoSeller.getCreatedTime(),"yyyy-MM-dd HH:mm:ss"));
        //执行链接/图片
        result.put("returnImg",infoSeller.getOrderInfoSeller() == null ? "" : infoSeller.getOrderInfoSeller().getReturnImg());
        //执行链接/图片
        result.put("returnUrl",infoSeller.getOrderInfoSeller() == null ? "" : infoSeller.getOrderInfoSeller().getReturnUrl());
        if(infoSeller.getOrderInfoType().compareTo(EnumOrderSellerType.营销分发.getIndex()) == 0){
            result.put("accountName",infoSeller.getSnapshotAccount() == null ? "" : infoSeller.getSnapshotAccount().getName());
            result.put("accountImg",infoSeller.getSnapshotAccount() == null ? "" : infoSeller.getSnapshotAccount().getHeadImageUrlLocal());
            result.put("platformLogo",infoSeller.getSnapshotAccount() == null ? "" : infoSeller.getSnapshotAccount().getPlatformLogo());
            result.put("accountId",infoSeller.getSnapshotAccount() == null ? "" : infoSeller.getSnapshotAccount().getPlatformIPAccountId());
            try {
                result.put("returnTime",infoSeller.getOrderInfoSeller() == null || infoSeller.getOrderInfoSeller().getReturnTime() == null ? "" : DateUtil.format(infoSeller.getOrderInfoSeller().getReturnTime(),"yyyy-MM-dd"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //价格项
            result.put("priceName",infoSeller.getOrderDetail() == null ? "" : infoSeller.getOrderDetail().getProduction());
        }else if(infoSeller.getOrderInfoType().compareTo(EnumOrderSellerType.定制内容.getIndex()) == 0){
            result.put("authorName",infoSeller.getSnapshotYrAuthor() == null ? "" : infoSeller.getSnapshotYrAuthor().getAuthorNickname());
            result.put("authorImg",infoSeller.getSnapshotYrAuthor() == null ? "" : infoSeller.getSnapshotYrAuthor().getAuthorImg());
            result.put("authorId",infoSeller.getSnapshotYrAuthor() == null ? "" : infoSeller.getSnapshotYrAuthor().getYrAuthorId());
            try {
                result.put("returnTime",infoSeller.getOrderInfoSeller() == null || infoSeller.getOrderInfoSeller().getReturnTime() == null ? "" : DateUtil.format(infoSeller.getOrderInfoSeller().getReturnTime(),"yyyy-MM-dd"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //价格项
            result.put("priceName",infoSeller.getOrderDetail() == null ? "" : infoSeller.getOrderDetail().getProduction());
        }else if(infoSeller.getOrderInfoType().compareTo(EnumOrderSellerType.原创征稿.getIndex()) == 0){
            result.put("productTitle",infoSeller.getSnapshotYrProduction() == null ? "" : infoSeller.getSnapshotYrProduction().getTitle());
            result.put("productId",infoSeller.getSnapshotYrProduction() == null ? "" : infoSeller.getSnapshotYrProduction().getYrProductionId());
            result.put("productWordNum",infoSeller.getSnapshotYrProduction() == null ? "" : infoSeller.getSnapshotYrProduction().getWordNum());
            result.put("productImgNum",infoSeller.getSnapshotYrProduction() == null ? "" : infoSeller.getSnapshotYrProduction().getImgNum());
        }
        JSONObject priceDetail = new JSONObject();
        //订单金额
        priceDetail.put("orderMoney",infoSeller.getOrderDetail() == null ? "" : infoSeller.getOrderDetail().getPrice());
        //服务费率
        priceDetail.put("serviceRate",infoSeller.getBuyerServiceRate());
        //税额
        List<OrderCostInfo> costInfoList = infoSeller.getCostInfoList();
        BigDecimal invoice = new BigDecimal("0");
        if(CollectionUtil.size(costInfoList) >0){
            for (OrderCostInfo cost : costInfoList){
                if(cost != null && cost.getCostId() == 242 && cost.getCostTypeIndex().compareTo(EnumCostType.作品买家订单.getIndex()) == 0){
                    invoice = cost.getMoney();
                }
            }
        }
        priceDetail.put("invoiceMoney",invoice);
        //订单总计
        priceDetail.put("sumMoney",infoSeller.getReceivableMoney());
        result.put("moneyDetail",priceDetail);
        return result;
    }


}
