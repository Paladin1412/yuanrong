package com.yuanrong.admin.bean.author;
import java.util.*;
import java.math.*;

import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumAuthorStatus;
import com.yuanrong.admin.Enum.EnumChannel;
import com.yuanrong.admin.Enum.EnumUserRoleLicenseStatus;
import com.yuanrong.admin.Enum.EnumYesOrNo;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 圆融创作者的实体类
 *
 * @author MDA
 *
 */
public class YRAuthor extends BaseBean implements java.io.Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private Integer recId;
    /*****自定义属性区域begin******/
   private String recIds;
    /**
     * 作者昵称
     * 作者昵称
     */
    private String authorNickname;
   
    /**
     * 作者头像
     * 作者头像
     */
    private String authorImg;

    /**
     * 用于通过购物车选择创作者
     */
    private Integer shoppingCartId;

    /**
     * 创作者简介
     * 创作者简介
     */
    private String introduction;
   
    /**
     * 创作用时
     * 创作用时
     */
    private Integer authorCreationTime;
   
    /**
     * 创作报价
     * 创作报价
     */
    private BigDecimal createdPrice;
   
    /**
     * 用户ID
     * 用户ID
     */
    private Integer registeredUserInfoID;

    private RegisteredUserInfo registeredUserInfo;

    /**
     * 创作用时备注
     * 创作用时备注
     */
    private String creationTimeRemark;
   
    /**
     * 创作报价备注
     * 创作报价备注
     */
    private String createdPriceRemark;
   
    /**
     * 创作者上架状态
     * 创作者上架状态
     */
    private EnumAuthorStatus enumAuthorStatus;
    /*
    * 用于接收前台数据
    */
    private Integer authorStatus;
   
    /**
     * 创作者审核失败原因
     * 创作者审核失败原因
     */
    private String authorAuditFailReason;
   
    /**
     * 修改时间
     * 修改时间
     */
    private String modifyTime;
    /**
     * 当前圆融排序规则(圆融指数)
     */
    private Integer yRIndex;
    /**
     * 创作形式
     */
    private String contentForm;
    /**
     * 使用场景
     */
    private String scenes;
    /**
     * 擅长领域
     */
    private String category;
    /**
     * 作品标签—内容属性
     */
    private String contentLable;
    /**
     * 作品标签—表现风格
     */
    private String styleLable;
    /**
     * 母IP
     */
    private String parentIP;
    /**
     * 子IP
     */
    private String sonIP;
    /**
     * 创作者下架原因
     */
    private String authorUnderReason;

    /**
     * 该作者的作品
     */
    private List<YRProduction> yrProductions;
    /**
     * 访问的次数
     */
    private Integer accessTimes;
    /**
     * 上架时间
     */
    private Date upTime;
    /**
     * 排序规则(分数)Score = (P-1)/(T+2)^G
     */
    private BigDecimal sortScore;

    /**
     * 审核人
     */
    private String auditUser;
    /**
     * 创建来源
     */
    private EnumChannel enumChannel;
    private Integer createSource;
    /**
     * 是否匿名创作者
     */
    private Integer isAnonymous;
    private EnumYesOrNo enumYesOrNo;
    /**
     * 上架代表作个数
     */
    private Integer representativeNum;
    /**
     * 敏感词
     */
    private String sensitiveWords;
    /**
     * 敏感词是否爬取(0-未爬取 1-已爬取)
     */
    private Integer crawlerStatus;
    private EnumYesOrNo enumCrawlerStatus;

    /**
     * 虚拟访问次数
     */
    private Integer accessNum;

    public String getRecIds() {
        return recIds;
    }

    public void setRecIds(String recIds) {
        this.recIds = recIds;
    }

    public List<YRProduction> getYrProductions() {
        return yrProductions;
    }

    public void setYrProductions(List<YRProduction> yrProductions) {
        this.yrProductions = yrProductions;
    }

    public Integer getRecId() {
        return this.recId;
    }
    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public Integer getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Integer shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    /*****自定义属性区域begin.get/set******/
   
    public String getAuthorNickname() {
        return this.authorNickname;
    }
    public void setAuthorNickname(String authorNickname) {
        this.authorNickname = authorNickname;
    }
    public String getAuthorImg() {
        return this.authorImg;
    }
    public void setAuthorImg(String authorImg) {
        this.authorImg = authorImg;
    }
    public String getIntroduction() {
        return this.introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public Integer getAuthorCreationTime() {
        return this.authorCreationTime;
    }
    public void setAuthorCreationTime(Integer authorCreationTime) {
        this.authorCreationTime = authorCreationTime;
    }
   
    public BigDecimal getCreatedPrice() {
        return this.createdPrice;
    }
    public void setCreatedPrice(BigDecimal createdPrice) {
        this.createdPrice = createdPrice;
    }

    public Integer getRegisteredUserInfoID() {
        return this.registeredUserInfoID;
    }
    public void setRegisteredUserInfoID(Integer registeredUserInfoID) {
        this.registeredUserInfoID = registeredUserInfoID;
    }
   
    public String getCreationTimeRemark() {
        return this.creationTimeRemark;
    }
    public void setCreationTimeRemark(String creationTimeRemark) {
        this.creationTimeRemark = creationTimeRemark;
    }
    public String getCreatedPriceRemark() {
        return this.createdPriceRemark;
    }
    public void setCreatedPriceRemark(String createdPriceRemark) {
        this.createdPriceRemark = createdPriceRemark;
    }

    public EnumAuthorStatus getEnumAuthorStatus() {
    return enumAuthorStatus;
}
    public void setEnumAuthorStatus(EnumAuthorStatus enumAuthorStatus) {
        this.enumAuthorStatus =enumAuthorStatus;
        this.authorStatus = enumAuthorStatus.getIndex();
    }

    public Integer getAuthorStatus() {
        return authorStatus;
    }
    public void setAuthorStatus(Integer authorStatus) {
        this.authorStatus =authorStatus;
        this.enumAuthorStatus = (EnumAuthorStatus) EnumUtil.valueOf(EnumAuthorStatus.class, authorStatus);
    }
    public String getAuthorAuditFailReason() {
        return this.authorAuditFailReason;
    }
    public void setAuthorAuditFailReason(String authorAuditFailReason) {
        this.authorAuditFailReason = authorAuditFailReason;
    }
    public String getModifyTime() {
        return this.modifyTime;
    }
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public RegisteredUserInfo getRegisteredUserInfo() {
        return registeredUserInfo;
    }

    public void setRegisteredUserInfo(RegisteredUserInfo registeredUserInfo) {
        this.registeredUserInfo = registeredUserInfo;
    }

    public Integer getyRIndex() {
        return yRIndex;
    }

    public void setyRIndex(Integer yRIndex) {
        this.yRIndex = yRIndex;
    }

    public String getContentForm() {
        return contentForm;
    }

    public void setContentForm(String contentForm) {
        this.contentForm = contentForm;
    }

    public String getScenes() {
        return scenes;
    }

    public void setScenes(String scenes) {
        this.scenes = scenes;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContentLable() {
        return contentLable;
    }

    public void setContentLable(String contentLable) {
        this.contentLable = contentLable;
    }

    public String getStyleLable() {
        return styleLable;
    }

    public void setStyleLable(String styleLable) {
        this.styleLable = styleLable;
    }

    public String getParentIP() {
        return parentIP;
    }

    public void setParentIP(String parentIP) {
        this.parentIP = parentIP;
    }

    public String getSonIP() {
        return sonIP;
    }

    public void setSonIP(String sonIP) {
        this.sonIP = sonIP;
    }

    public String getAuthorUnderReason() {
        return authorUnderReason;
    }

    public void setAuthorUnderReason(String authorUnderReason) {
        this.authorUnderReason = authorUnderReason;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public EnumChannel getEnumChannel() {
        return enumChannel;
    }

    public void setEnumChannel(EnumChannel enumChannel) {
        this.enumChannel = enumChannel;
        this.createSource = enumChannel.getIndex();
    }

    public Integer getCreateSource() {
        return createSource;
    }

    public void setCreateSource(Integer createSource) {
        this.createSource = createSource;
        this.enumChannel = (EnumChannel)EnumUtil.valueOf(EnumChannel.class,createSource);
    }

    public Integer getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Integer isAnonymous) {
        this.isAnonymous = isAnonymous;
        this.enumYesOrNo = (EnumYesOrNo)EnumUtil.valueOf(EnumYesOrNo.class,isAnonymous);
    }

    public EnumYesOrNo getEnumYesOrNo() {
        return enumYesOrNo;
    }

    public void setEnumYesOrNo(EnumYesOrNo enumYesOrNo) {
        this.enumYesOrNo = enumYesOrNo;
        this.isAnonymous = enumYesOrNo.getIndex();
    }

    public Integer getRepresentativeNum() {
        return representativeNum;
    }

    public void setRepresentativeNum(Integer representativeNum) {
        this.representativeNum = representativeNum;
    }

    public String getSensitiveWords() {
        return sensitiveWords;
    }

    public void setSensitiveWords(String sensitiveWords) {
        this.sensitiveWords = sensitiveWords;
    }

    public Integer getAccessTimes() {
        return accessTimes;
    }

    public void setAccessTimes(Integer accessTimes) {
        this.accessTimes = accessTimes;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public BigDecimal getSortScore() {
        return sortScore;
    }

    public void setSortScore(BigDecimal sortScore) {
        this.sortScore = sortScore;
    }

    public Integer getCrawlerStatus() {
        return crawlerStatus;
    }

    public void setCrawlerStatus(Integer crawlerStatus) {
        this.crawlerStatus = crawlerStatus;
        this.enumCrawlerStatus = (EnumYesOrNo)EnumUtil.valueOf(EnumYesOrNo.class,crawlerStatus);
    }

    public EnumYesOrNo getEnumCrawlerStatus() {
        return enumCrawlerStatus;
    }

    public void setEnumCrawlerStatus(EnumYesOrNo enumCrawlerStatus) {
        this.enumCrawlerStatus = enumCrawlerStatus;
        this.crawlerStatus = enumCrawlerStatus.getIndex();
    }

    public Integer getAccessNum() {
        return accessNum;
    }

    public void setAccessNum(Integer accessNum) {
        this.accessNum = accessNum;
    }

    /**
     * 封装创作者参数—详情
     * @param authorlist
     * @return
     */
    public static JSONObject packageAuthorParam(List<Map<String, Object>> authorlist){
        JSONObject authorInfo = new JSONObject();
        //创作者Id
        authorInfo.put("recId",authorlist.get(0).get("recId"));
        //创作者头像
        authorInfo.put("authorImg",authorlist.get(0).get("authorImg"));
        //创作者名称
        authorInfo.put("authorNickname",authorlist.get(0).get("authorNickname"));
        //创作者原创报价
        authorInfo.put("createdPrice",authorlist.get(0).get("createdPrice"));
        //创作者原创报价说明
        authorInfo.put("createdPriceRemark",authorlist.get(0).get("createdPriceRemark"));
        //创作者创作用时
        authorInfo.put("authorCreationTime",authorlist.get(0).get("authorCreationTime"));
        //创作者创作用时说明
        authorInfo.put("creationTimeRemark",authorlist.get(0).get("creationTimeRemark"));
        //创作者简介
        authorInfo.put("Introduction",authorlist.get(0).get("Introduction"));
        //创作者状态值
        authorInfo.put("authorStatusIndex",authorlist.get(0).get("authorStatus"));
        //创作者状态
        authorInfo.put("authorStatus",authorlist.get(0).get("authorStatus") == null ? "" : EnumUtil.valueOf(EnumAuthorStatus.class,authorlist.get(0).get("authorStatus").toString()).getName());
        //创作者使用场景
        authorInfo.put("scenesName",authorlist.get(0).get("scenesName"));
        //创作者使用场景Id
        authorInfo.put("scenesIds",authorlist.get(0).get("scenesIds"));
        //创作者擅长领域
        authorInfo.put("CategoryName",authorlist.get(0).get("CategoryName"));
        //创作者内容形式
        authorInfo.put("contentFormName",authorlist.get(0).get("contentFormName"));
        //创作者内容形式
        authorInfo.put("contentFormIds",authorlist.get(0).get("contentFormIds"));
        //创作者标签
        authorInfo.put("lableName",authorlist.get(0).get("lableName"));
        //用户审核状态值
        authorInfo.put("userStatusIndex",authorlist.get(0).get("sellerStatusValue"));
        //用户审核状态
        authorInfo.put("userStatus",authorlist.get(0).get("sellerStatusValue") == null ? "" : EnumUtil.valueOf(EnumUserRoleLicenseStatus.class, authorlist.get(0).get("sellerStatusValue").toString()).getName());
        //创作者审核人
        authorInfo.put("auditUser",authorlist.get(0).get("auditUser"));
        //创作者敏感词
        authorInfo.put("sensitiveWords",authorlist.get(0).get("sensitiveWords"));
        return authorInfo;
    }

    /**
     * 封装创作者参数—编辑页面
     * @param authorlist
     * @return
     */
    public static JSONObject packageAuthorParamUpdate(List<Map<String, Object>> authorlist){
        JSONObject authorInfo = new JSONObject();
        //创作者Id
        authorInfo.put("recId",authorlist.get(0).get("recId"));
        //创作者头像
        authorInfo.put("authorImg",authorlist.get(0).get("authorImg"));
        //创作者名称
        authorInfo.put("authorNickname",authorlist.get(0).get("authorNickname"));
        //创作者原创报价
        authorInfo.put("createdPrice",authorlist.get(0).get("createdPrice"));
        //创作者原创报价备注
        authorInfo.put("createdPriceRemark",authorlist.get(0).get("createdPriceRemark"));
        //创作者创作用时
        authorInfo.put("authorCreationTime",authorlist.get(0).get("authorCreationTime"));
        //创作者创作用时备注
        authorInfo.put("creationTimeRemark",authorlist.get(0).get("creationTimeRemark"));
        //创作者简介
        authorInfo.put("Introduction",authorlist.get(0).get("Introduction"));
        //创作者状态值
        authorInfo.put("authorStatusIndex",authorlist.get(0).get("authorStatus"));
        //创作者状态
        authorInfo.put("authorStatus",authorlist.get(0).get("authorStatus") == null ? "" : EnumUtil.valueOf(EnumAuthorStatus.class,authorlist.get(0).get("authorStatus").toString()).getName());
        //创作者使用场景Id
        authorInfo.put("scenesIds",authorlist.get(0).get("scenesIds"));
        //创作者使用场景Name
        authorInfo.put("scenesName",authorlist.get(0).get("scenesName"));
        //创作者擅长领域
        authorInfo.put("CategoryIds",authorlist.get(0).get("CategoryIds"));
        //创作者内容形式Id
        authorInfo.put("contentFormIds",authorlist.get(0).get("contentFormIds"));
        //创作者内容形式Name
        authorInfo.put("contentFormName",authorlist.get(0).get("contentFormName"));
        //用户ID
        authorInfo.put("userId",authorlist.get(0).get("registeredUserInfoID"));
        //创作者敏感词
        authorInfo.put("sensitiveWords",authorlist.get(0).get("sensitiveWords"));
        if(StringUtil.isNotBlank(String.valueOf(authorlist.get(0).get("sensitiveWords")))){
           String newIntro = dealIntroduction(String.valueOf(authorlist.get(0).get("sensitiveWords")),String.valueOf(authorlist.get(0).get("Introduction")));
            authorInfo.put("newIntro",newIntro);
        }
        return authorInfo;
    }

    public static String dealIntroduction(String sensitiveWords,String introduction){
        String[] split = sensitiveWords.split("、");
        if (split.length >0 ){
            for (String str : split){
                if(introduction.contains(str)){
                    introduction = introduction.replace(str,"<span style='color:red'>" + str + "</span>");
                }
            }
        }
        return introduction;

    }
}
