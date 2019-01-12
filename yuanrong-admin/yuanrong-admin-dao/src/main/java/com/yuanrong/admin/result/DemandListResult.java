package com.yuanrong.admin.result;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumDemandStatus;
import com.yuanrong.admin.Enum.EnumDemandType;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.StringUtil;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 后台—普通需求列表返回结果
 * @ClassName DemandListResult
 * @Author Jss
 * @Date 2018/6/30
 */
public class DemandListResult extends Demand implements Serializable {

    /**
     * 订单数
     */
    private Integer orderNum;
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
    private String realName;
    /**
     * 费用明细（成本价，发票费，服务费）
     */
    private String moneyDetail;
    /**
     * 订单总金额
     */
    private BigDecimal sumCostMoney;
    /**
     * 总税额
     */
    private BigDecimal sumInvoice;
    /**
     * 需求应收总金额
     */
    private BigDecimal sumMoney;
    /**
     * 总应约价
     */
    private BigDecimal sumOfferMoney;

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMoneyDetail() {
        return moneyDetail;
    }

    public void setMoneyDetail(String moneyDetail) {
        this.moneyDetail = moneyDetail;
    }

    public BigDecimal getSumCostMoney() {
        return sumCostMoney;
    }

    public void setSumCostMoney(BigDecimal sumCostMoney) {
        this.sumCostMoney = sumCostMoney;
    }

    public BigDecimal getSumInvoice() {
        return sumInvoice;
    }

    public void setSumInvoice(BigDecimal sumInvoice) {
        this.sumInvoice = sumInvoice;
    }

    public BigDecimal getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(BigDecimal sumMoney) {
        this.sumMoney = sumMoney;
    }

    public BigDecimal getSumOfferMoney() {
        return sumOfferMoney;
    }

    public void setSumOfferMoney(BigDecimal sumOfferMoney) {
        this.sumOfferMoney = sumOfferMoney;
    }

    /**
     * 需求列表结果封装
     * @param demandListResult
     * @return
     */
    public static JSONObject packageDemand(DemandListResult demandListResult){
        JSONObject jsonObject = new JSONObject();
        //需求id
        jsonObject.put("demandId",demandListResult.getDemandId());
        //需求编号
        jsonObject.put("demandSn",demandListResult.getDemandSn());
        //需求名称
        jsonObject.put("demandName",demandListResult.getDemandName());
        //需求状态
        jsonObject.put("demandStatusIndex",demandListResult.getDemandStatusIndex());
        jsonObject.put("demandStatus",demandListResult.getDemandStatusIndex() == null ? "" : EnumUtil.valueOf(EnumDemandStatus.class,demandListResult.getDemandStatusIndex()).getName());
        //需求类型
        jsonObject.put("demandTypeIndex",demandListResult.getDemandTypeIndex());
        jsonObject.put("demandType",demandListResult.getDemandTypeIndex() == null ? "" : EnumUtil.valueOf(EnumDemandType.class,demandListResult.getDemandTypeIndex()).getName());
        //需求订单数
        jsonObject.put("orderNum",demandListResult.getOrderNum());
        //需求预算
        jsonObject.put("budgetMoney",demandListResult.getBudgetMoney());
        //用户id
        jsonObject.put("userId",demandListResult.getUserId());
        //需求用户昵称
        jsonObject.put("nickName",demandListResult.getNickName());
        //销售经理
        jsonObject.put("realName",demandListResult.getRealName());
        //需求创建时间
        jsonObject.put("createdTime",demandListResult.getCreatedTime() == null ? "" : DateUtil.format(demandListResult.getCreatedTime(),"yyyy-MM-dd HH:mm:ss"));
        //需求拒绝原因
        jsonObject.put("refuseReason",demandListResult.getRefuseReason());
        //需求取消原因
        jsonObject.put("cancelReason",demandListResult.getCancelReason());
        //需求金额
        JSONObject object = new JSONObject();
        //需求金额—订单总金额
        object.put("sumCostMoney",demandListResult.getSumCostMoney() == null ? 0 : demandListResult.getSumCostMoney());
        //需求金额—订单总税额
        object.put("sumInvoice",demandListResult.getSumInvoice() == null ? 0 : demandListResult.getSumInvoice());
        //需求金额—订单应收总金额
        object.put("sumMoney",demandListResult.getSumMoney() == null ? 0 : demandListResult.getSumMoney());
        //需求金额—平均服务费=1-订单总金额/总应约价
        //总应约价
        BigDecimal avgServiceFree = new BigDecimal("0");
        if(demandListResult.getSumCostMoney() != null){
            BigDecimal offerTail = demandListResult.getSumOfferMoney();
            avgServiceFree = new BigDecimal("1").subtract(demandListResult.getSumCostMoney().divide(offerTail,2,BigDecimal.ROUND_UP));
        }
        object.put("avgServiceFree",avgServiceFree);
        jsonObject.put("moneyDetail",object);
        return jsonObject;
    }
    /**
    * 买家中心封装结果
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/7/6 14:22
    */
    public static JSONObject packageDemandBuyCenter(DemandListResult demand) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("demandSn", demand.getDemandSn());
        jsonObject.put("demandName", demand.getDemandName());
        jsonObject.put("userId", demand.getUserId());
        jsonObject.put("demandStatusIndex", demand.getDemandStatusIndex());
        jsonObject.put("demandStatus", demand.getDemandStatusIndex() == null ? "" : EnumUtil.valueOf(EnumDemandStatus.class, demand.getDemandStatusIndex()).getName());
        jsonObject.put("demandTypeIndex", demand.getDemandTypeIndex());
        jsonObject.put("demandType", demand.getDemandTypeIndex() == null ? "" : EnumUtil.valueOf(EnumDemandType.class, demand.getDemandTypeIndex()).getName());
        jsonObject.put("budgetMoney", demand.getBudgetMoney());
        jsonObject.put("createdTime", demand.getCreatedTime() == null ? "" : DateUtil.format(demand.getCreatedTime(), "yyyy-MM-dd HH:mm:ss"));
        jsonObject.put("cancelReason",demand.getCancelReason());
        jsonObject.put("refuseReason",demand.getRefuseReason());
        return jsonObject;
    }
}
