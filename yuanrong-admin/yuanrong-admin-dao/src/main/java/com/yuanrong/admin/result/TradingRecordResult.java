package com.yuanrong.admin.result;

import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumDemandStatus;
import com.yuanrong.admin.Enum.EnumDemandType;
import com.yuanrong.admin.bean.trading.TradingRecord;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.DateUtil;

public class TradingRecordResult extends TradingRecord{
    /**
     * 买家中心封装结果-后台
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/7/6 14:22
     */
    public static JSONObject packageTradingRecord(TradingRecordResult tradingRecordResult) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("registeredUserInfoId", tradingRecordResult.getRegisteredUserInfoId());
        jsonObject.put("tradingRecordId", tradingRecordResult.getTradingRecordId());
        jsonObject.put("nickName", tradingRecordResult.getNickName());
        jsonObject.put("tradingDate", tradingRecordResult.getTradingDate());
        jsonObject.put("sellerAccount", tradingRecordResult.getSellerAccount());
        jsonObject.put("referPlatform", tradingRecordResult.getReferPlatform());
        jsonObject.put("servicesContent", tradingRecordResult.getServicesContent());
        jsonObject.put("money", tradingRecordResult.getMoney());
        jsonObject.put("buyerName", tradingRecordResult.getBuyerName());
        jsonObject.put("cooPerationBrand", tradingRecordResult.getCooPerationBrand());
        jsonObject.put("createdTime", tradingRecordResult.getCreatedTime()==null?null:DateUtil.format(tradingRecordResult.getCreatedTime(), "yyyy-MM-dd HH:mm:ss"));
        jsonObject.put("channelIndex", tradingRecordResult.getChannelIndex());
        jsonObject.put("channel", tradingRecordResult.getChannel()!=null?tradingRecordResult.getChannel().getName():null);
        jsonObject.put("heir", tradingRecordResult.getHeir());
        return jsonObject;
    }
    /**
     * 买家中心封装结果-前台
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/7/6 14:22
     */
    public static JSONObject packageTradingRecordFront(TradingRecordResult tradingRecordResult) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("registeredUserInfoId", tradingRecordResult.getRegisteredUserInfoId());
        jsonObject.put("tradingRecordId", tradingRecordResult.getTradingRecordId());
        jsonObject.put("nickName", tradingRecordResult.getNickName());
        jsonObject.put("tradingDate", tradingRecordResult.getTradingDate());
        jsonObject.put("sellerAccount", tradingRecordResult.getSellerAccount());
        jsonObject.put("referPlatform", tradingRecordResult.getReferPlatform());
        jsonObject.put("servicesContent", tradingRecordResult.getServicesContent());
        jsonObject.put("money", tradingRecordResult.getMoney());
        jsonObject.put("buyerName", tradingRecordResult.getBuyerName());
        jsonObject.put("cooPerationBrand", tradingRecordResult.getCooPerationBrand());
        jsonObject.put("createdTime", tradingRecordResult.getCreatedTime()==null?null:DateUtil.format(tradingRecordResult.getCreatedTime(), "yyyy-MM-dd HH:mm:ss"));
        return jsonObject;
    }
}
