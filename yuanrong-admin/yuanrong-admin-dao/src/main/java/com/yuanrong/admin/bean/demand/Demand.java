package com.yuanrong.admin.bean.demand;
import java.text.ParseException;
import java.util.*;
import java.io.Serializable;
import java.math.*;

import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumDemandStatus;
import com.yuanrong.admin.Enum.EnumDemandType;
import com.yuanrong.admin.Enum.EnumOrderSource;
import com.yuanrong.admin.Enum.EnumYesOrNo;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.bean.order.SnapshotAccount;
import com.yuanrong.admin.bean.order.SnapshotYrAuthor;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.StringUtil;

/**
 * 基本需求信息的实体类
 *
 * @author MDA
 *
 */
public class Demand extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private Integer demandId;
    /*****自定义属性区域begin******/
   
   
    /**
     * 需求号
     * 需求号
     */
    private String demandSn;
   
    /**
     * 需求名称
     * 需求名称
     */
    private String demandName;
   
    /**
     * 需求类型
     * 需求类型
     */
    private EnumDemandType enumDemandType;
    /*
    * 用于接收前台数据
    */
    private Integer demandTypeIndex;
   
    /**
     * 注册用户ID
     * 注册用户ID
     */
    private Integer registeredUserInfoId;

    /**
     * 注册用户
     */
    private RegisteredUserInfo registeredUserInfo;
   
    /**
     * 需求状态
     * 需求状态
     */
    private EnumDemandStatus enumDemandStatus;
    /*
    * 用于接收前台数据
    */
    private Integer demandStatusIndex;
   
    /**
     * 所属行业：内容定制、营销分发
     * 所属行业：内容定制、营销分发
     */
    private Integer tradeId;
    private DictInfo tradeDictinfo;
    /**
     * 联系电话
     * 联系电话
     */
    private String mobile;
   
    /**
     * 需求预算
     * 需求预算
     * 稿件费用
     */
    private BigDecimal budgetMoney;
   
    /**
     * 需求描述
     * 需求描述
     * 征稿要求
     */
    private String remark;
   
    /**
     * 需求附件(营销分发、内容定制)
     * 需求附件(营销分发、内容定制)
     * 征稿素材
     */
    private String attachment;
   
    /**
     * 内容形式(内容定制、IP代理：IP形式)
     * 内容形式(内容定制、IP代理：IP形式)
     * 征稿-表现形式
     */
    private String contentForms;
   
    /**
     * 销售备注
     * 销售备注
     */
    private String saleMark;
   
    /**
     * 拒绝原因(字典表)
     * 拒绝原因(字典表)
     */
    private String refuseReason;
   
    /**
     * 平台(营销分发)
     * 平台(营销分发)
     */
    private String platformName;
   
    /**
     * 预计推广时间(营销分发)
     * 预计推广时间(营销分发)
     */
    private String spreadTime;
   
    /**
     * 圆融分类（账号分类/ip分类/内容领域）
     * 圆融分类（账号分类/ip分类/内容领域）
     */
    private String yrCategory;
   
    /**
     * 粉丝数(93_700)(营销分发)
     * 粉丝数(93_700)(营销分发)
     */
    private String fans;
   
    /**
     * 使用场景(内容定制)
     * 使用场景(内容定制)
     */
    private String scenes;
   
    /**
     * 期望完成时间(内容定制、营销分发:最后反馈时间)
     * 期望完成时间(内容定制、营销分发:最后反馈时间)
     * 原创征稿-截止时间
     */
    private String expectedTime;
    /**
     * 创建来源(1-前台创建/2-后台创建)
     */
    private Integer sourceId;

    private EnumOrderSource enumOrderSource;

    /**
     * 创建人
     */
    private String createUser;
    /**
     * 需求取消原因
     */
    private String cancelReason;

    /**
     *订单ID
     * @return
     */
    private OrderInfoSeller orderInfoSeller;

    /**
     *账号名
     * @return
     */
    private SnapshotAccount snapshotAccount;

    /**
     *账号名
     * @return
     */
    private SnapshotYrAuthor snapshotYrAuthor;

    /**
     * 关键词
     */
    private String[] likeName;
    /**
     * 期望数量-原创征稿(征集数量)
     */
    private Integer expectNum;

    /**
     * 期望报价项（营销分发）
     */
    private String expectOffer;

    /**
     * 统计该需求的报名数
     */
    private Integer cnt_num;

    /**
     * 是否在需求大厅展示
     */
    private Integer isShow;

    private EnumYesOrNo enumYesOrNo;

    /**
     * 字数要求(原创征稿)
     */
    private String requireWordNum;

    /**
     * 时长要求(原创征稿)
     */
    private String requireSeconds;
    /**
     * 参考样稿(原创征稿)
     */
    private String referURL;
    /**
     * 审核时间
     */
    private String auditTime;
    /**
     * 审核人
     */
    private String auditUser;
    private Integer[] orderStatuss;
    private Integer[] applyStatuss;
    private String orderSn;
    private Integer orderType;
    private Integer[] yrProductStatus;

    public Integer[] getYrProductStatus() {
        return yrProductStatus;
    }

    public void setYrProductStatus(Integer[] yrProductStatus) {
        this.yrProductStatus = yrProductStatus;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer[] getOrderStatuss() {
        return orderStatuss;
    }

    public void setOrderStatuss(Integer[] orderStatuss) {
        this.orderStatuss = orderStatuss;
    }

    public Integer[] getApplyStatuss() {
        return applyStatuss;
    }

    public void setApplyStatuss(Integer[] applyStatuss) {
        this.applyStatuss = applyStatuss;
    }

    public Integer getDemandId() {
        return this.demandId;
    }
    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public Integer getExpectNum() {
        return expectNum;
    }

    public void setExpectNum(Integer expectNum) {
        this.expectNum = expectNum;
    }

    public String getExpectOffer() {
        return expectOffer;
    }

    public void setExpectOffer(String expectOffer) {
        this.expectOffer = expectOffer;
    }

    public Integer getCnt_num() {
        return cnt_num;
    }

    public void setCnt_num(Integer cnt_num) {
        this.cnt_num = cnt_num;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
        this.enumYesOrNo = (EnumYesOrNo) EnumUtil.valueOf(EnumYesOrNo.class, isShow);
    }

    public EnumYesOrNo getEnumYesOrNo() {
        return enumYesOrNo;
    }

    public void setEnumYesOrNo(EnumYesOrNo enumYesOrNo) {
        this.enumYesOrNo = enumYesOrNo;
        this.isShow = enumYesOrNo.getIndex();
    }

    public String getRequireWordNum() {
        return requireWordNum;
    }

    public void setRequireWordNum(String requireWordNum) {
        this.requireWordNum = requireWordNum;
    }

    public String getRequireSeconds() {
        return requireSeconds;
    }

    public void setRequireSeconds(String requireSeconds) {
        this.requireSeconds = requireSeconds;
    }

    public String getReferURL() {
        return referURL;
    }

    public void setReferURL(String referURL) {
        this.referURL = referURL;
    }

    /*****自定义属性区域begin.get/set******/
   
    public String getDemandSn() {
        return this.demandSn;
    }
    public void setDemandSn(String demandSn) {
        this.demandSn = demandSn;
    }
    public String getDemandName() {
        return this.demandName;
    }
    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }

    public EnumDemandType getEnumDemandType() {
    return enumDemandType;
}
    public void setEnumDemandType(EnumDemandType enumDemandType) {
        this.enumDemandType = enumDemandType;
        this.demandTypeIndex = enumDemandType.getIndex();
    }

    public Integer getDemandTypeIndex() {
        return demandTypeIndex;
    }
    public void setDemandTypeIndex(Integer demandTypeIndex) {
        this.demandTypeIndex = demandTypeIndex;
        this.enumDemandType = (EnumDemandType) EnumUtil.valueOf(EnumDemandType.class, demandTypeIndex);
    }


    public Integer getRegisteredUserInfoId() {
        return this.registeredUserInfoId;
    }
    public void setRegisteredUserInfoId(Integer registeredUserInfoId) {
        this.registeredUserInfoId = registeredUserInfoId;
    }
   
    public EnumDemandStatus getEnumDemandStatus() {
    return enumDemandStatus;
}
    public void setEnumDemandStatus(EnumDemandStatus enumDemandStatus) {
        this.enumDemandStatus = enumDemandStatus;
        this.demandStatusIndex = enumDemandStatus.getIndex();
    }

    public Integer getDemandStatusIndex() {
        return demandStatusIndex;
    }
    public void setDemandStatusIndex(Integer demandStatusIndex) {
        this.demandStatusIndex = demandStatusIndex;
        this.enumDemandStatus = (EnumDemandStatus) EnumUtil.valueOf(EnumDemandStatus.class, demandStatusIndex);
    }

    public Integer getTradeId() {
        return this.tradeId;
    }
    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;

    }

    public DictInfo getTradeDictinfo() {
        return tradeDictinfo;
    }

    public void setTradeDictinfo(DictInfo tradeDictinfo) {
        this.tradeDictinfo = tradeDictinfo;
    }

    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public BigDecimal getBudgetMoney() {
        return this.budgetMoney;
    }
    public void setBudgetMoney(BigDecimal budgetMoney) {
        this.budgetMoney = budgetMoney;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getAttachment() {
        return this.attachment;
    }
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
    public String getContentForms() {
        return this.contentForms;
    }
    public void setContentForms(String contentForms) {
        this.contentForms = contentForms;
    }
    public String getSaleMark() {
        return this.saleMark;
    }
    public void setSaleMark(String saleMark) {
        this.saleMark = saleMark;
    }
    public String getRefuseReason() {
        return this.refuseReason;
    }
    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }
   
    public String getPlatformName() {
        return this.platformName;
    }
    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
    public String getSpreadTime() {
        return this.spreadTime;
    }
    public void setSpreadTime(String spreadTime) {
        this.spreadTime = spreadTime;
    }
    public String getYrCategory() {
        return this.yrCategory;
    }
    public void setYrCategory(String yrCategory) {
        this.yrCategory = yrCategory;
    }
    public String getFans() {
        return this.fans;
    }
    public void setFans(String fans) {
        this.fans = fans;
    }
    public String getScenes() {
        return this.scenes;
    }
    public void setScenes(String scenes) {
        this.scenes = scenes;
    }
    public String getExpectedTime() {
        return this.expectedTime;
    }
    public void setExpectedTime(String expectedTime) {
        this.expectedTime = expectedTime;
    }

    public RegisteredUserInfo getRegisteredUserInfo() {
        return registeredUserInfo;
    }

    public void setRegisteredUserInfo(RegisteredUserInfo registeredUserInfo) {
        this.registeredUserInfo = registeredUserInfo;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
        this.enumOrderSource = (EnumOrderSource) EnumUtil.valueOf(EnumOrderSource.class, sourceId);
    }

    public EnumOrderSource getEnumOrderSource() {
        return enumOrderSource;
    }

    public void setEnumOrderSource(EnumOrderSource enumOrderSource) {
        this.enumOrderSource = enumOrderSource;
        this.sourceId = enumOrderSource.getIndex();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public OrderInfoSeller getOrderInfoSeller() {
        return orderInfoSeller;
    }

    public void setOrderInfoSeller(OrderInfoSeller orderInfoSeller) {
        this.orderInfoSeller = orderInfoSeller;
    }

    public SnapshotAccount getSnapshotAccount() {
        return snapshotAccount;
    }

    public void setSnapshotAccount(SnapshotAccount snapshotAccount) {
        this.snapshotAccount = snapshotAccount;
    }

    public SnapshotYrAuthor getSnapshotYrAuthor() {
        return snapshotYrAuthor;
    }

    public void setSnapshotYrAuthor(SnapshotYrAuthor snapshotYrAuthor) {
        this.snapshotYrAuthor = snapshotYrAuthor;
    }

    public String[] getLikeName() {
        return likeName;
    }

    public void setLikeName(String[] likeName) {
        this.likeName = likeName;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    /**
     * 后台—需求详情的数据封装(营销分发)
     * @param demandInfo
     * @return
     */
    public static JSONObject packageDemanInfo(Demand demandInfo) throws ParseException {
        JSONObject jsonObject = new JSONObject();
        //需求Id
        jsonObject.put("demandId",demandInfo.getDemandId());
        //需求号
        jsonObject.put("demandSn",demandInfo.getDemandSn());
        //需求名称
        jsonObject.put("demandName",demandInfo.getDemandName());
        //需求类型id
        jsonObject.put("demandTypeIndex",demandInfo.getDemandTypeIndex());
        //需求类型
        jsonObject.put("demandType",demandInfo.getEnumDemandType().getName());
        //需求状态id
        jsonObject.put("demandStatusIndex",demandInfo.getDemandStatusIndex());
        //需求状态
        jsonObject.put("demandStatus",demandInfo.getEnumDemandStatus().getName());
        //需求预算-征稿费用
        jsonObject.put("budgetMoney",demandInfo.getBudgetMoney());
        //需求描述-征稿要求
        jsonObject.put("remark",demandInfo.getRemark());
        //需求手机号
        jsonObject.put("mobile",demandInfo.getMobile());
        //用户Id
        jsonObject.put("userId",demandInfo.getRegisteredUserInfo() == null ? "" : demandInfo.getRegisteredUserInfo().getRecID());
        //用户昵称
        jsonObject.put("userNickName",demandInfo.getRegisteredUserInfo() == null ? "" : demandInfo.getRegisteredUserInfo().getNickName());
        //用户注册手机号
        jsonObject.put("userMobile",demandInfo.getRegisteredUserInfo() == null ? "" : demandInfo.getRegisteredUserInfo().getMobile());
        //销售备注
        jsonObject.put("saleMark",demandInfo.getSaleMark());
        //圆融分类(账号分类/IP行业/创作要求—内容领域)
        jsonObject.put("yrCategory",demandInfo.getYrCategory());
        //是否在需求大厅显示
        jsonObject.put("isShow",demandInfo.getIsShow());
        jsonObject.put("isShowName",demandInfo.getEnumYesOrNo() == null ? "" : demandInfo.getEnumYesOrNo().getName());
        if (demandInfo.getDemandTypeIndex().compareTo(EnumDemandType.营销分发.getIndex()) == 0){
            //需求平台
            jsonObject.put("platformName",demandInfo.getPlatformName());
            //预计推广时间
            jsonObject.put("spreadTime", StringUtil.isBlank(demandInfo.getSpreadTime()) ? "" : DateUtil.format(DateUtil.parseDate(demandInfo.getSpreadTime(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
            //粉丝数
            jsonObject.put("fans",demandInfo.getFans());
            if(demandInfo.getFans()!=null){
                String[] fans = demandInfo.getFans().split("_");
                if(fans.length ==2){
                    jsonObject.put("startFans",fans[0]);
                    jsonObject.put("endFans",fans[1]);
                }
            }
            //需求附件
            jsonObject.put("attachment",demandInfo.getAttachment());
            //最后反馈时间
            jsonObject.put("expectedTime",StringUtil.isBlank(demandInfo.getExpectedTime()) ? "" : DateUtil.format(DateUtil.parseDate(demandInfo.getExpectedTime(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
            //所属行业（定制内容/营销分发）
            jsonObject.put("tradeId",demandInfo.getTradeId());
            //期望报价
            jsonObject.put("expectOffer",demandInfo.getExpectOffer());
            //期望个数
            jsonObject.put("expectNum",demandInfo.getExpectNum());
            jsonObject.put("endTime",StringUtil.isBlank(demandInfo.getSpreadTime())?"": DateUtil.getDatePoor(new Date(),DateUtil.getDateByDateStr(demandInfo.getSpreadTime(),"yyyy-MM-dd HH:mm:ss")));
        }else if (demandInfo.getDemandTypeIndex().compareTo(EnumDemandType.ip代理.getIndex()) == 0){
            //IP类型
            jsonObject.put("contentForms",demandInfo.getContentForms());
        }else if (demandInfo.getDemandTypeIndex().compareTo(EnumDemandType.定制内容.getIndex()) == 0){
            //创作要求—使用场景1
            jsonObject.put("scenes",demandInfo.getScenes());
            //创作要求—内容形式
            jsonObject.put("contentForms",demandInfo.getContentForms());
            //需求附件
            jsonObject.put("attachment",demandInfo.getAttachment());
            //期望完成时间
            jsonObject.put("expectedTime",StringUtil.isBlank(demandInfo.getExpectedTime()) ? "" : DateUtil.format(DateUtil.parseDate(demandInfo.getExpectedTime(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
            //所属行业（定制内容/营销分发）
            jsonObject.put("tradeId",demandInfo.getTradeId());
            //期望报价
            jsonObject.put("expectOffer",demandInfo.getExpectOffer());
            //期望个数
            jsonObject.put("expectNum",demandInfo.getExpectNum());
            jsonObject.put("endTime",StringUtil.isBlank(demandInfo.getExpectedTime())? "" :  DateUtil.getDatePoor(new Date(),DateUtil.getDateByDateStr(demandInfo.getExpectedTime(),"yyyy-MM-dd HH:mm:ss")));
        }else if (demandInfo.getDemandTypeIndex().compareTo(EnumDemandType.原创征稿.getIndex()) == 0){//原创征稿
            //表现形式
            jsonObject.put("contentForms",demandInfo.getContentForms());
            //字数要求
            jsonObject.put("requireWordNum",demandInfo.getRequireWordNum());
            //时长要求
            jsonObject.put("requireSeconds",demandInfo.getRequireSeconds());
            //征集数量
            jsonObject.put("num",demandInfo.getExpectNum());
            //征稿素材
            jsonObject.put("attachment",demandInfo.getAttachment());
            //参考样稿
            jsonObject.put("referUrl",demandInfo.getReferURL());
            //所属行业
            jsonObject.put("tradeId",demandInfo.getTradeId());
            //期望个数
            jsonObject.put("expectNum",demandInfo.getExpectNum());
            //期望完成时间
            jsonObject.put("expectedTime",StringUtil.isBlank(demandInfo.getExpectedTime()) ? "" : DateUtil.format(DateUtil.parseDate(demandInfo.getExpectedTime(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
            jsonObject.put("endTime",StringUtil.isBlank(demandInfo.getExpectedTime())?"": DateUtil.getDatePoor(new Date(),DateUtil.getDateByDateStr(demandInfo.getExpectedTime(),"yyyy-MM-dd HH:mm:ss")));
        }
        return jsonObject;
    }

    /**
     * 需求大厅结果封装
     * @param demand
     * @return
     * @throws ParseException
     */
    public static JSONObject packageDemandHall (Demand demand,boolean flag)  {
        JSONObject jsonObject = new JSONObject();
        //需求类型
        if(demand.getEnumDemandType().getIndex()==EnumDemandType.定制内容.getIndex()){
            jsonObject.put("enumDemandType","内容定制");
        }else{
            jsonObject.put("enumDemandType",demand.getEnumDemandType());
        }
        jsonObject.put("demandTypeIndex",demand.getDemandTypeIndex());
        //需求名称
        jsonObject.put("demandName",demand.getDemandName());
        //预算
        jsonObject.put("budgetMoney",demand.getBudgetMoney());
        //已报名数
        jsonObject.put("cnt_num",demand.getCnt_num());
        //期望人数、征稿数量、期望作者数
        jsonObject.put("expectNum",demand.getExpectNum());

        jsonObject.put("demandStatusIndex",demand.getDemandStatusIndex());
        //报名剩余时间
        //营销分发：推广时间SpreadTime - creatTime
        //原创征稿、内容定制：expectedTime -CreateTime
        try {
            if(demand.getDemandStatusIndex() == EnumDemandStatus.已完成.getIndex()){
                jsonObject.put("endTime","0天0小时0分钟");

            }else {

                if(demand.getDemandTypeIndex() ==EnumDemandType.营销分发.getIndex()){
                    jsonObject.put("endTime",StringUtil.isBlank(demand.getSpreadTime())?"": DateUtil.getDatePoor(new Date(),DateUtil.getDateByDateStr(demand.getSpreadTime(),"yyyy-MM-dd HH:mm:ss")));
                }else{
                    jsonObject.put("endTime",StringUtil.isBlank(demand.getExpectedTime())? "" :  DateUtil.getDatePoor(new Date(),DateUtil.getDateByDateStr(demand.getExpectedTime(),"yyyy-MM-dd HH:mm:ss")));
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //注册用户Id
        jsonObject.put("remark",demand.getRemark());
        //内容形式
        jsonObject.put("contentForms",demand.getContentForms());
        //需求单号
        jsonObject.put("demandSn",demand.getDemandSn());
        //flag为true，返回需求详情
        if(flag){
          if(demand.getDemandTypeIndex() ==EnumDemandType.营销分发.getIndex()){
              //平台
              jsonObject.put("platformName",demand.getPlatformName());
              //账号分类
              jsonObject.put("yrCategory",demand.getYrCategory());
              //粉丝数
              jsonObject.put("fans",demand.getFans());
              //预计推广时间
              jsonObject.put("spreadTime",demand.getSpreadTime());
              //素材附件
              jsonObject.put("attachment",demand.getAttachment());
          }

          if(demand.getDemandTypeIndex()==EnumDemandType.定制内容.getIndex()){
              //使用场景
              jsonObject.put("scenes",demand.getScenes());
              //内容领域
              jsonObject.put("yrCategory",demand.getYrCategory());
              //期望作者数
              jsonObject.put("expectNum",demand.getExpectNum());
              //素材附件
              jsonObject.put("attachment",demand.getAttachment());
          }

          if(demand.getDemandTypeIndex()==EnumDemandType.原创征稿.getIndex()){
              //征集数量
              jsonObject.put("expectNum",demand.getExpectNum());
              //表现形式
              jsonObject.put("contentForms",demand.getContentForms());
              //字数要求
              jsonObject.put("requireWordNum",demand.getRequireWordNum());
              //时常要求：文章没有时间要求
              if(demand.getContentForms().equals("视频")){
                  //时常要求
                  jsonObject.put("requireSeconds",demand.getRequireSeconds());
              }
              //素材附件
              jsonObject.put("attachment",demand.getAttachment());
              //参考样稿
              jsonObject.put("referURL",demand.getReferURL());
          }
        }

        return jsonObject;
    }
    /**
     * 卖家中心封装
     * @param demand
     * @return
     * @throws ParseException
     */
    public static JSONObject packageDemandMy (Demand demand,boolean flag)  {
        JSONObject jsonObject = new JSONObject();
        //需求类型
        jsonObject.put("enumDemandType",demand.getEnumDemandType().getName());
        jsonObject.put("demandTypeIndex",demand.getDemandTypeIndex());
        //需求名称
        jsonObject.put("demandName",demand.getDemandName());
        //预算
        jsonObject.put("budgetMoney",demand.getBudgetMoney());

        jsonObject.put("remark",demand.getRemark());
        jsonObject.put("isShow",demand.getIsShow());
        //需求单号
        jsonObject.put("demandSn",demand.getDemandSn());
        //返回需求状态
        jsonObject.put("demandStatusIndex",demand.getDemandStatusIndex());
        //返回需求
        jsonObject.put("demandStatus",demand.getEnumDemandStatus());
        //flag为true，返回需求详情
        if(flag){
            if(demand.getDemandTypeIndex() ==EnumDemandType.营销分发.getIndex()){
                //平台
                jsonObject.put("platformName",demand.getPlatformName());
                //账号分类
                jsonObject.put("yrCategory",demand.getYrCategory());
                //粉丝数
                jsonObject.put("fans",demand.getFans());
                //预计推广时间
                jsonObject.put("spreadTime",demand.getSpreadTime());
                //期望人数、征稿数量、期望作者数
                jsonObject.put("expectNum",demand.getExpectNum());
                jsonObject.put("attachment",demand.getAttachment());
            }

            if(demand.getDemandTypeIndex()==EnumDemandType.定制内容.getIndex()){
                jsonObject.put("contentForms",demand.getContentForms());
                //使用场景
                jsonObject.put("scenes",demand.getScenes());
                //内容领域
                jsonObject.put("yrCategory",demand.getYrCategory());
                //期望作者数
                jsonObject.put("expectNum",demand.getExpectNum());
                //素材附件
                jsonObject.put("attachment",demand.getAttachment());
                //
                jsonObject.put("expectedTime",demand.getExpectedTime());
            }

            if(demand.getDemandTypeIndex()==EnumDemandType.原创征稿.getIndex()){
                jsonObject.put("contentForms",demand.getContentForms());
                //征集数量
                jsonObject.put("expectNum",demand.getExpectNum());
                //表现形式
                jsonObject.put("contentForms",demand.getContentForms());
                //字数要求
                jsonObject.put("requireWordNum",demand.getRequireWordNum());
                //素材附件
                jsonObject.put("attachment",demand.getAttachment());
                //参考样稿
                jsonObject.put("referURL",demand.getReferURL());
                jsonObject.put("expectNum",demand.getExpectNum());
                //期望人数、征稿数量、期望作者数
                jsonObject.put("expectedTime",demand.getExpectedTime());

            }
            if(demand.getDemandTypeIndex()==EnumDemandType.ip代理.getIndex()){
                jsonObject.put("contentForms",demand.getContentForms());
                //征集数量
                jsonObject.put("yrCategory",demand.getYrCategory());
                //表现形式
            }
        }

        return jsonObject;
    }


}
