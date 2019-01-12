package com.yuanrong.admin.seach;

import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.common.util.StringUtil;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by zhonghang on 2018/5/4.
 */
public class PlatformIpAccountSeach extends BaseBean implements Serializable {
    /**
     * 平台
     */
    private String platformID;
    private int[] platformIDs;

    /**
     * 媒介经理
     */
    private String adminUserMediaID;
    private int[] adminUserMediaIDs;

    /**
     * 用户简称
     */
    private String registerName;
    private String[] registerNames;

    /**
     * 账号名
     */
    private String accountName;
    private String[] accountNames;

    /**
     * 账号ID
     */
    private String accountID;
    private String[] accountIDs;

    /**
     * IP名称
     */
    private String iPName;
    private String[] iPNames;

    /**
     * 账号状态
     */
    private String platformAccountStatus;

    /**
     * 入库日期(开始时间)
     */
    private String createdTimeStart;

    /**
     * 入库日期(结束时间)
     */
    private String createdTimeEnd;

    /**
     * 账号报价类别
     */
    private int priceID;

    private BigDecimal priceStart;

    private BigDecimal priceEnd;

    /**
     * 头条平均阅读量
     */
    private int  avgReadCount ;

    /**
     * 平均点赞
     */
    private int avgLikeCount  ;

    /**
     * 平均转发
     */
    private int avgForwardCount  ;
    /**
     * 平均评论
     */
    private int avgCommontCount  ;

    private int numStart;

    private int numEnd;

    /**
     * ipID
     */
    private String ipid;
    private int[] ipids;

    /**
     * id
     */
    private String id;
    private int[] ids;

    /**
     * 下架原因
     */
    private Integer lowerCauseID;

    /**
     * 有效期
     */
    private String invalidTime;

    /**
     * 有效期开始时间
     */
    private String invalidTimeStart ;
    /**
     * 有效期结束时间
     */
    private String invalidTimeEnd ;

    /**
     * 平台ID，只搜索该平台的价格
     */
    private int platformPrice;

    /**
     * 幅度百分比
     */
    private Integer rangePer;

    /**
     * 需求列表使用—账号ID或账号名批量查询
     */
    private String[] likeName;
    /**
     * 需求Id
     */
    private Integer demandId;

    /**
     * UserId
     * @return
     */
    private String rgiRecId;
    private String[] rgiRecIds;

    /**
     * 排序字段
     * @return
     */
    private String orderByField;

    /**
     * 行业分类
     * @return
     */
    private Integer categoryID;
    /**
     * 是否代理
     * @return
     */
    private Integer isAgent;
    /**
     * 代理权合作品牌
     * @return
     */
    private String agentCoopBrand;
    /**
     * 代理权合作条件
     * @return
     */
    private String agentCoopCondition;

    /**
     * 创建来源
     */
    private Integer channelIndex;

    public String getPlatformID() {
        return platformID;
    }

    public void setPlatformID(String platformID) {
        this.platformID = platformID;
        platformIDs = getArrayByIDs(platformID);
    }

    public String getAdminUserMediaID() {
        return adminUserMediaID;
    }

    public void setAdminUserMediaID(String adminUserMediaID) {
        this.adminUserMediaID = adminUserMediaID;
        adminUserMediaIDs = getArrayByIDs(adminUserMediaID);
    }

    public String getIpid() {
        return ipid;
    }

    public void setIpid(String ipid) {
        this.ipids = getArrayByIDs(ipid);
        this.ipid = ipid;
    }

    public int[] getIpids() {
        return ipids;
    }

    public void setIpids(int[] ipids) {
        this.ipids = ipids;
    }

    private int[] getArrayByIDs(String string){
        if(StringUtil.isNoneBlank(string)){
            String[] strs = string.split(" ");
            int[] result = new int[strs.length];
            try{
                for(int i= 0 ; i < strs.length ; i++){
                    result[i] = Integer.parseInt(strs[i]);
                }
                return result;
            }catch (Exception e){ }
        }
        return null;
    }
    private String[] getArrayBystrs(String string){
        if(StringUtil.isNoneBlank(string)){
            String[] strs = string.split(" ");
            String[] result = new String[strs.length];
            for(int i= 0 ; i < strs.length ; i++){
                result[i] =  StringUtil.format(strs[i]) ;
            }
            return result;
        }
        return null;
    }


    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
        this.registerNames = getArrayBystrs(registerName);
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
        this.accountNames = getArrayBystrs(accountName);
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
        this.accountIDs = getArrayBystrs(accountID);
    }

    public String getIPName() {
        return iPName;
    }

    public void setIPName(String iPName) {
        this.iPName = iPName;
        this.iPNames = getArrayBystrs(iPName);
    }

    public String getPlatformAccountStatus() {
        return platformAccountStatus;
    }

    public void setPlatformAccountStatus(String platformAccountStatus) {
        this.platformAccountStatus = platformAccountStatus;
    }

    public String getiPName() {
        return iPName;
    }

    public void setiPName(String iPName) {
        this.iPName = iPName;
    }

    public String getCreatedTimeStart() {
        return createdTimeStart;
    }

    public void setCreatedTimeStart(String createdTimeStart) {
        this.createdTimeStart = createdTimeStart;
    }

    public String getCreatedTimeEnd() {
        return createdTimeEnd;
    }

    public void setCreatedTimeEnd(String createdTimeEnd) {
        this.createdTimeEnd = createdTimeEnd;
    }

    public int getPriceID() {
        return priceID;
    }

    public void setPriceID(int priceID) {
        this.priceID = priceID;
    }

    public BigDecimal getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(BigDecimal priceStart) {
        this.priceStart = priceStart;
    }

    public BigDecimal getPriceEnd() {
        return priceEnd;
    }

    public void setPriceEnd(BigDecimal priceEnd) {
        this.priceEnd = priceEnd;
    }

    public int getAvgReadCount() {
        return avgReadCount;
    }

    public void setAvgReadCount(int avgReadCount) {
        this.avgReadCount = avgReadCount;
    }

    public int getAvgLikeCount() {
        return avgLikeCount;
    }

    public void setAvgLikeCount(int avgLikeCount) {
        this.avgLikeCount = avgLikeCount;
    }

    public int getAvgForwardCount() {
        return avgForwardCount;
    }

    public void setAvgForwardCount(int avgForwardCount) {
        this.avgForwardCount = avgForwardCount;
    }

    public int getAvgCommontCount() {
        return avgCommontCount;
    }

    public void setAvgCommontCount(int avgCommontCount) {
        this.avgCommontCount = avgCommontCount;
    }

    public int getNumStart() {
        return numStart;
    }

    public void setNumStart(int numStart) {
        this.numStart = numStart;
    }

    public int getNumEnd() {
        return numEnd;
    }

    public void setNumEnd(int numEnd) {
        this.numEnd = numEnd;
    }

    public int[] getPlatformIDs() {
        return platformIDs;
    }

    public void setPlatformIDs(int[] platformIDs) {

        this.platformIDs = platformIDs;
    }

    public int[] getAdminUserMediaIDs() {
        return adminUserMediaIDs;
    }

    public void setAdminUserMediaIDs(int[] adminUserMediaIDs) {
        this.adminUserMediaIDs = adminUserMediaIDs;
    }

    public String[] getRegisterNames() {
        return registerNames;
    }

    public void setRegisterNames(String[] registerNames) {
        this.registerNames = registerNames;
    }

    public String[] getAccountNames() {
        return accountNames;
    }

    public void setAccountNames(String[] accountNames) {
        this.accountNames = accountNames;
    }

    public String[] getAccountIDs() {
        return accountIDs;
    }

    public void setAccountIDs(String[] accountIDs) {
        this.accountIDs = accountIDs;
    }

    public String[] getiPNames() {
        return iPNames;
    }

    public void setiPNames(String[] iPNames) {
        this.iPNames = iPNames;
    }

    public String getInvalidTimeStart() {
        return invalidTimeStart;
    }

    public void setInvalidTimeStart(String invalidTimeStart) {
        this.invalidTimeStart = invalidTimeStart;
    }

    public String getInvalidTimeEnd() {
        return invalidTimeEnd;
    }

    public void setInvalidTimeEnd(String invalidTimeEnd) {
        this.invalidTimeEnd = invalidTimeEnd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        this.ids = this.getArrayByIDs(id);
    }

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public Integer getLowerCauseID() {
        return lowerCauseID;
    }

    public void setLowerCauseID(Integer lowerCauseID) {
        this.lowerCauseID = lowerCauseID;
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }

    public int getPlatformPrice() {
        return platformPrice;
    }

    public void setPlatformPrice(int platformPrice) {
        this.platformPrice = platformPrice;
    }

    public Integer getRangePer() {
        return rangePer;
    }

    public void setRangePer(Integer rangePer) {
        this.rangePer = rangePer;
    }

    public String[] getLikeName() {
        return likeName;
    }

    public void setLikeName(String[] likeName) {
        this.likeName = likeName;
    }

    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public String getRgiRecId() {
        return rgiRecId;
    }

    public void setRgiRecId(String rgiRecId) {
        this.rgiRecId = rgiRecId;
    }

    public String[] getRgiRecIds() {
        return rgiRecIds;
    }

    public void setRgiRecIds(String[] rgiRecIds) {
        this.rgiRecIds = rgiRecIds;
    }

    public String getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(String orderByField) {
        this.orderByField = orderByField;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(Integer isAgent) {
        this.isAgent = isAgent;
    }

    public String getAgentCoopBrand() {
        return agentCoopBrand;
    }

    public void setAgentCoopBrand(String agentCoopBrand) {
        this.agentCoopBrand = agentCoopBrand;
    }

    public String getAgentCoopCondition() {
        return agentCoopCondition;
    }

    public void setAgentCoopCondition(String agentCoopCondition) {
        this.agentCoopCondition = agentCoopCondition;
    }

    public Integer getChannelIndex() {
        return channelIndex;
    }

    public void setChannelIndex(Integer channelIndex) {
        this.channelIndex = channelIndex;
    }

    public String getOrderBy(){
        String orderby ;
        if(StringUtil.isBlank(this.orderByField)){
            orderby = "pfi.id desc";
        }else if(this.orderByField.equals("createdTime")){
            orderby = "pfi.createdTime desc";
        }else if(this.orderByField.equals("fans")){
            orderby = "pfi.fans desc";
        }else if(this.orderByField.equals("yrIndex")){
            orderby = "pfi.yrIndex desc";
        }else if(this.orderByField.equals("avgReadCount")){
            orderby = "pfi.avgReadCount desc";
        }else if(this.orderByField.equals("avgLikeCount")){
            orderby = "pfi.avgLikeCount desc";
        }else if(this.orderByField.equals("avgForwardCount")){
            orderby = "pfi.avgForwardCount desc";
        }else if(this.orderByField.equals("avgCommontCount")){
            orderby = "pfi.avgCommontCount desc";
        }else if(this.orderByField.equals("avgPlayCount")){
            orderby = "pfi.avgPlayCount desc";
        }else {
            orderby = "pfi.id desc";
        }
        return orderby;
    }
}
