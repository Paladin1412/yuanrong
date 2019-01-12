package com.yuanrong.admin.bean.author;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.base.ContentForm;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.bean.base.Scenes;
import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.FilterHtmlUtil;
import com.yuanrong.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 圆融作品的实体类
 *
 * @author MDA
 */
public class YRProduction extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     * 主键
     */
    private Integer recId;

    /**
     * 圆融作者
     * 圆融作者
     */
    private YRAuthor yrAuthor;
    /**
     * 圆融作者ID
     * 圆融作者ID
     */
    private Integer yrAuthorId;

    /**
     * 作品名称
     * 作品名称
     */
    private String title;

    /**
     * 作品简介
     * 作品简介
     */
    private String introduction;

    /**
     * 作品链接
     * 作品链接
     */
    private String productUrl;

    /**
     * 作品封面本地化
     * 作品封面本地化
     */
    private String coverLocalUrl;

    /**
     * 作品封面
     * 作品封面
     */
    private String coverUrl;

    /**
     * 内容形式
     * 内容形式
     */
    private ContentForm contentForm;

    /**
     * 使用场景
     * 使用场景
     */
    private Scenes scenes;

    /**
     * 发布时间
     * 发布时间
     */
    private String publishTime;

    /**
     * 作品内容
     * 作品内容
     */
    private String content;

    /**
     * 作品内容本地
     * 作品内容本地
     */
    private String localcontent;

    /**
     * 作品状态
     * 作品状态
     */
    private EnumYRProductionStatus yrProductionStatus;
    /*
     * 用于接收前台数据
     */
    private String yrProductionStatusIndex;

    /**
     * 报价 - 卖家录入的原始报价
     */
    private BigDecimal productQuotedPrice;

    /**
     * 报价 - c端展示给买家看的报价
     */
    private BigDecimal sellPrice;

    /**
     * 发布状态
     * 发布状态
     */
    private EnumPublishStatus publishStatus;
    /*
     * 用于接收前台数据
     */
    private String publishStatusIndex;
    /**
     *作品审核失败原因
     */
    private String productionAuditFailReasonId;

    /**
     * 是否代表作
     */
    private Integer isRepresentative;
    private EnumYesOrNo isRepresentativeStatus;
    /**
     * 内容指数
     */
    private Double contentScore;

    /**
     * 作品下架原因
     */
    private String proUnderReasonId;

    /**
     * 发表平台
     */
    private String publishPlatform;

    /**
     * 审核人
     */
    private String auditUser;

    /**
     * 所属注册用户
     */
    private RegisteredUserInfo registeredUserInfo;
    private Integer registeredUserInfoId;

    /**
     * 渠道
     */
    private EnumChannel channel;
    private Integer channelIndex;

    /**
     * 媒介经理
     */
    private AdminUser adminUser;


    /**
     * 作品字数
     */
    private int wordNum;

    /**
     * 敏感词
     */
    private String sensitiveWords;

    /**
     * 被访问次数
     */
    private Integer accessTimes;
    /**
     * 上架时间
     */
    private Date upTime;
    /**
     * 作品图片数
     */
    private Integer imgNum;
    /**
     * 排序规则(分数)Score = (P-1)/(T+2)^G
     */
    private BigDecimal sortScore;
    /**
     * 作品分类
     */
    private int yrCategoryId;
    private DictInfo yrCategory;
    /**
     * 高频词
     */
    private String tags;

    /**
     * 访问次数 假的
     */
    private Integer accessNum;

    /**
     * 图文热词
     */
    private String hotWords;
    /**
     * 是否阅读
     */
    private Integer isRead;

    /**
     * 作品唯一值
     */
    private String onlyValue;

    /**
     * 作品是否计算了唯一值
     */
    private EnumYesOrNo enumOnlyIndex;
    private Integer onlyIndex;

    /**
     * 是否导入
     */
    private Integer isImport;
    private EnumYesOrNo isImportEnum;
    /**
     * 敏感词是否抓取
     */
    private Integer crawlerStatus;
    private EnumYesOrNo enumCrawlerStatus;

    /**
     * 是否作者本人
     */
    private Integer isAuthor;

    /**
     * 获取全部内容
     */
    private Integer getMoreInfo;
    /**
     * 内容评估总分值
     */
    private BigDecimal contentEvaluationScore;

    /**
     * 原创度分值
     */
    private BigDecimal originalScore;

    /**
     * 作品类别 - 临时字段
     */
    private EnumOrderSellerType orderType;
    /**
     * 购物车
     */
    private Integer isAddCart;

    /**
     * 是否重复
     */
     private EnumRepeat enumReStatus;
     private Integer reStatus;

   /*****自定义属性区域begin.get/set******/

    public Integer getRecId() {
        return this.recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public YRAuthor getYrAuthor() {
        return this.yrAuthor;
    }

    public void setYrAuthor(YRAuthor yrAuthor) {
        this.yrAuthor = yrAuthor;
    }

    public Integer getYrAuthorId() {
        return yrAuthorId;
    }

    public void setYrAuthorId(Integer yrAuthorId) {
        this.yrAuthorId = yrAuthorId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getProductUrl() {
        return this.productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getCoverLocalUrl() {
        return this.coverLocalUrl;
    }

    public void setCoverLocalUrl(String coverLocalUrl) {
        this.coverLocalUrl = coverLocalUrl;
    }

    public String getCoverUrl() {
        return this.coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public ContentForm getContentForm() {
        return this.contentForm;
    }

    public void setContentForm(ContentForm contentForm) {
        this.contentForm = contentForm;
    }

    public Scenes getScenes() {
        return this.scenes;
    }

    public void setScenesId(Scenes scenesId) {
        this.scenes = scenesId;
    }

    public String getPublishTime() {
        return this.publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocalcontent() {
        return this.localcontent;
    }

    public void setLocalcontent(String localcontent) {
        this.localcontent = localcontent;
    }

    public EnumYRProductionStatus getYrProductionStatus() {
        return yrProductionStatus;
    }

    public void setYrProductionStatus(EnumYRProductionStatus yrProductionStatus) {
        this.yrProductionStatus = yrProductionStatus;
        yrProductionStatusIndex = yrProductionStatus.getIndex()+"";
    }

    public void setYrProductionStatus(String yrProductionStatus) {
        this.yrProductionStatusIndex = yrProductionStatus;
        this.yrProductionStatus = (EnumYRProductionStatus) EnumUtil.valueOf(EnumYRProductionStatus.class, yrProductionStatus);
    }

    public String getYrProductionStatusIndex() {
        return yrProductionStatusIndex;
    }

    public void setYrProductionStatusIndex(String yrProductionStatusIndex) {
        this.yrProductionStatusIndex = yrProductionStatusIndex;
        this.yrProductionStatus = (EnumYRProductionStatus) EnumUtil.valueOf(EnumYRProductionStatus.class, yrProductionStatusIndex);
    }

    public EnumPublishStatus getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(EnumPublishStatus publishStatus) {
        this.publishStatus = publishStatus;
        publishStatusIndex = publishStatus.getIndex()+"";
    }

    public String getPublishStatusIndex() {
        return publishStatusIndex;
    }

    public void setPublishStatusIndex(String publishStatusIndex) {
        this.publishStatusIndex = publishStatusIndex;
        this.publishStatus = (EnumPublishStatus) EnumUtil.valueOf(EnumPublishStatus.class, publishStatusIndex);
    }

    public BigDecimal getProductQuotedPrice() {
        return productQuotedPrice;
    }

    public void setProductQuotedPrice(BigDecimal productQuotedPrice) {
        this.productQuotedPrice = productQuotedPrice;
    }

    public String getProductionAuditFailReasonId() {
        return productionAuditFailReasonId;
    }

    public void setProductionAuditFailReasonId(String productionAuditFailReasonId) {
        this.productionAuditFailReasonId = productionAuditFailReasonId;
    }

    public void setScenes(Scenes scenes) {
        this.scenes = scenes;
    }

    public Integer getIsRepresentative() {
        return isRepresentative;
    }

    public void setIsRepresentative(Integer isRepresentative) {
        this.isRepresentative = isRepresentative;
        this.isRepresentativeStatus = (EnumYesOrNo) EnumUtil.valueOf(EnumYesOrNo.class , isRepresentative);
    }

    public EnumYesOrNo getIsRepresentativeStatus() {
        return isRepresentativeStatus;
    }

    public void setIsRepresentativeStatus(EnumYesOrNo isRepresentativeStatus) {
        this.isRepresentativeStatus = isRepresentativeStatus;
        this.isRepresentative = isRepresentativeStatus.getIndex();
    }

    public Double getContentScore() {
        return contentScore;
    }

    public void setContentScore(Double contentScore) {
        this.contentScore = contentScore;
    }

    public String getProUnderReasonId() {
        return proUnderReasonId;
    }

    public void setProUnderReasonId(String proUnderReasonId) {
        this.proUnderReasonId = proUnderReasonId;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
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

    public Integer getImgNum() {
        return imgNum;
    }

    public void setImgNum(Integer imgNum) {
        this.imgNum = imgNum;
    }

    public BigDecimal getSortScore() {
        return sortScore;
    }

    public void setSortScore(BigDecimal sortScore) {
        this.sortScore = sortScore;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getYrCategoryId() {
        return yrCategoryId;
    }

    public void setYrCategoryId(int yrCategoryId) {
        this.yrCategoryId = yrCategoryId;
    }

    public DictInfo getYrCategory() {
        return yrCategory;
    }

    public void setYrCategory(DictInfo yrCategory) {
        this.yrCategory = yrCategory;
    }

    public String getHotWords() {
        return hotWords;
    }

    public void setHotWords(String hotWords) {
        this.hotWords = hotWords;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getIsAuthor() {
        return isAuthor;
    }

    public void setIsAuthor(Integer isAuthor) {
        this.isAuthor = isAuthor;
    }

    public Integer getGetMoreInfo() {
        return getMoreInfo;
    }

    public void setGetMoreInfo(Integer getMoreInfo) {
        this.getMoreInfo = getMoreInfo;
    }

    public BigDecimal getContentEvaluationScore() {
        return contentEvaluationScore;
    }

    public void setContentEvaluationScore(BigDecimal contentEvaluationScore) {
        this.contentEvaluationScore = contentEvaluationScore;
    }

    public Integer getIsAddCart() {
        return isAddCart;
    }

    public void setIsAddCart(Integer isAddCart) {
        this.isAddCart = isAddCart;
    }

    /**
     * 封装创作的代表作品
     * @param authorProduction
     * @return
     */
    public static JSONArray packageAuthorProduction(List<Map<String, Object>> authorProduction) {
        JSONArray authorProList = new JSONArray();
        for (Map<String , Object> map : authorProduction){
            JSONObject json = new JSONObject();
            //作品Id
            json.put("recId",map.get("recId"));
            //作品头图本地链接
            json.put("coverLocalUrl",map.get("coverLocalUrl"));
            //作品标题
            json.put("title",map.get("title"));
            //作品本地内容
            json.put("localcontent",map.get("localcontent") == null ? "" : map.get("localcontent"));
            //作品原文链接
            json.put("productUrl",map.get("productUrl"));
            try {
                //作品发布时间
                json.put("publishTime",(map.get("publishTime") ==null || "".equals(map.get("publishTime")) )? "" : DateUtil.format(DateUtil.parseDate(map.get("publishTime").toString(), "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //作品标签
            json.put("lableName",map.get("lableName") == null ? "" : map.get("lableName"));
            //作品内容指数
            json.put("contentScore",map.get("contentEvaluationScore"));
            //作品内容形式
            json.put("contentFormId",map.get("contentFormId"));
            authorProList.add(json);
        }
        return authorProList;
    }

    public String getPublishPlatform() {
        return publishPlatform;
    }

    public void setPublishPlatform(String publishPlatform) {
        this.publishPlatform = publishPlatform;
    }

    public static JSONObject getJSONObjectByYRProduction(YRProduction production){
        JSONObject result = new JSONObject();
        //id
        result.put("recId"  , production.getRecId());
        //创作者昵称
        result.put("authorNickName" ,production.getYrAuthor() != null ? production.getYrAuthor().getAuthorNickname() : "");
        result.put("authorId" , production.getYrAuthor()!= null ? production.getYrAuthor().getRecId() : "");
        //用户简称
        result.put("registUserNickName" , (production.getYrAuthor()!= null && production.getYrAuthor().getRegisteredUserInfo() != null) ? production.getYrAuthor().getRegisteredUserInfo().getNickName() : "");
        result.put("registUserId" , (production.getYrAuthor()!= null && production.getYrAuthor().getRegisteredUserInfo() != null) ? production.getYrAuthor().getRegisteredUserInfo().getRecID() : "");
        // 注册手机
        result.put("registUserMobile" , (production.getYrAuthor()!= null && production.getYrAuthor().getRegisteredUserInfo() != null) ? production.getYrAuthor().getRegisteredUserInfo().getMobile() : "");
        //标题
        result.put("title" , production.getTitle());
        //文章内容
        //result.put("content" , production.getLocalcontent());
        if(StringUtils.isNotBlank(production.getSensitiveWords())){
            result.put("content" , dealIntroduction(production.getSensitiveWords(),production.getLocalcontent()));
        }else{
            result.put("content" , production.getLocalcontent());
        }
        //封面
        result.put("coverUrl" , production.getCoverLocalUrl());
        //文章类型名称
        result.put("contentFormName" , production.getContentForm() == null ? "" : production.getContentForm().getContentFormName());
        //文章类型ID
        result.put("contentFormId" ,  production.getContentForm() == null ? "" :production.getContentForm().getId());
        //发布情况
        result.put("publishStatus" ,production.getPublishStatus() == null ? "" : production.getPublishStatus().getName());
        result.put("publishStatusIndex" ,production.getPublishStatus() == null ? "" : production.getPublishStatus().getIndex());
        //发布时间
        result.put("publishTime" , production.getPublishTime());
        //作品报价
        result.put("productQuotedPrice" , production.getProductQuotedPrice());
        //是否代表作
        result.put("isRepresentative" , production.getIsRepresentative());
        result.put("isRepresentativeStatus" , production.getIsRepresentativeStatus().getName());
        //发表平台
        result.put("publishPlatform" , production.getPublishPlatform());
        //审核人
        result.put("auditUser" , production.getAuditUser());
        //作品状态
        result.put("yrProductionStatus" , production.getYrProductionStatus());
        result.put("yrProductionStatusIndex" , production.getYrProductionStatus()==null?"":production.getYrProductionStatus().getIndex());
        //媒介经理
        result.put("mediaUser" , production.getAdminUser()==null?"":production.getAdminUser().getRealName());
        result.put("isImport" , production.getIsImport());
        result.put("yrCategoryId" , production.getYrCategoryId());
        result.put("tags" , production.getTags());
        if(production.getReStatus()==-1){
            result.put("onlyValue" , production.getOnlyValue());
        }
        return result;
    }

    public RegisteredUserInfo getRegisteredUserInfo() {
        return registeredUserInfo;
    }

    public void setRegisteredUserInfo(RegisteredUserInfo registeredUserInfo) {
        this.registeredUserInfo = registeredUserInfo;
    }

    public Integer getRegisteredUserInfoId() {
        return registeredUserInfoId;
    }

    public void setRegisteredUserInfoId(Integer registeredUserInfoId) {
        this.registeredUserInfoId = registeredUserInfoId;
    }

    public EnumChannel getChannel() {
        return channel;
    }

    public void setChannel(EnumChannel channel) {
        this.channel = channel;
        this.channelIndex = channel.getIndex();
    }

    public Integer getChannelIndex() {
        return channelIndex;
    }

    public void setChannelIndex(Integer channelIndex) {
        this.channelIndex = channelIndex;
        this.channel = (EnumChannel) EnumUtil.valueOf(EnumChannel.class , channelIndex);
    }

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public int getWordNum() {
        return wordNum;
    }

    public void setWordNum(int wordNum) {
        this.wordNum = wordNum;
    }

    public String getSensitiveWords() {
        return sensitiveWords;
    }

    public void setSensitiveWords(String sensitiveWords) {
        this.sensitiveWords = sensitiveWords;
    }

    public Integer getCrawlerStatus() {
        return crawlerStatus;
    }

    public void setCrawlerStatus(Integer crawlerStatus) {
        this.crawlerStatus = crawlerStatus;
        this.enumCrawlerStatus = (EnumYesOrNo) EnumUtil.valueOf(EnumYesOrNo.class , crawlerStatus);
    }

    public Integer getAccessNum() {
        return accessNum;
    }

    public void setAccessNum(Integer accessNum) {
        this.accessNum = accessNum;
    }

    public EnumYesOrNo getEnumCrawlerStatus() {
        return enumCrawlerStatus;
    }

    public void setEnumCrawlerStatus(EnumYesOrNo enumCrawlerStatus) {
        this.enumCrawlerStatus = enumCrawlerStatus;
        this.crawlerStatus = enumCrawlerStatus.getIndex();
    }

    public String getOnlyValue() {
        return onlyValue;
    }

    public void setOnlyValue(String onlyValue) {
        this.onlyValue = onlyValue;
    }

    public EnumYesOrNo getEnumOnlyIndex() {
        return enumOnlyIndex;
    }

    public void setEnumOnlyIndex(EnumYesOrNo enumOnlyIndex) {
        this.enumOnlyIndex = enumOnlyIndex;
        this.onlyIndex = enumOnlyIndex.getIndex();
    }

    public Integer getOnlyIndex() {
        return onlyIndex;
    }

    public void setOnlyIndex(Integer onlyIndex) {
        this.onlyIndex = onlyIndex;
        this.enumOnlyIndex = (EnumYesOrNo) EnumUtil.valueOf(EnumYesOrNo.class , onlyIndex);
    }

    public Integer getIsImport() {
        return isImport;
    }

    public void setIsImport(Integer isImport) {
        this.isImport = isImport;
        this.isImportEnum = (EnumYesOrNo) EnumUtil.valueOf(EnumYesOrNo.class , isImport);
    }

    public EnumYesOrNo getIsImportEnum() {
        return isImportEnum;
    }

    public void setIsImportEnum(EnumYesOrNo isImportEnum) {
        this.isImportEnum = isImportEnum;
        this.isImport = isImportEnum.getIndex();
    }

    public BigDecimal getOriginalScore() {
        return originalScore;
    }

    public void setOriginalScore(BigDecimal originalScore) {
        this.originalScore = originalScore;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public EnumOrderSellerType getOrderType() {
        return orderType;
    }

    public void setOrderType(EnumOrderSellerType orderType) {
        this.orderType = orderType;
    }

    public EnumRepeat getEnumReStatus() {
        return enumReStatus;
    }

    public void setEnumReStatus(EnumRepeat enumReStatus) {
        this.enumReStatus = enumReStatus;
        this.reStatus = enumReStatus.getIndex();
    }

    public Integer getReStatus() {
        return reStatus;
    }

    public void setReStatus(Integer reStatus) {
        this.reStatus = reStatus;
        this.enumReStatus = (EnumRepeat) EnumUtil.valueOf(EnumRepeat.class , reStatus);
    }

    public static String dealIntroduction(String sensitiveWords, String introduction){
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

    public static JSONObject getProductionJSONObject(YRProduction production,Integer num,List<YRProduction> productionList,YRAuthor yRAuthor,Integer returnPercent,Integer getMoreInfo,Integer isAuthor,Integer isBuy){
        JSONObject result = new JSONObject();
        result.put("isBuy",isBuy);//是否购买
        result.put("isAuthor",isAuthor);//是否作者本人1是0否
        result.put("getMoreInfo",getMoreInfo);//是否可查看全部0可以，1不可以
        //作品ID
        result.put("recId",production.getRecId()==null?"":production.getRecId());
        //标题
        result.put("title",production.getTitle()==null?"":production.getTitle());
        //作者
        result.put("authorNickname",production.getYrAuthor()==null?"":production.getYrAuthor().getAuthorNickname());
        result.put("authorId",production.getYrAuthorId()==null?"":production.getYrAuthorId());
        //作品报价
        result.put("productQuotedPrice",production.getSellPrice()==null?"":production.getSellPrice());
        //图文热词
        result.put("hotWords",production.getHotWords()==null?"":production.getHotWords());
        //字数
        result.put("wordNum",production.getWordNum());
        //图片数
        result.put("imgNum",production.getImgNum());
        //是否已经阅读
        result.put("isRead",production.getIsRead()==null?"":production.getIsRead());
        //发布状态
        result.put("publishStatus",production.getPublishStatus()==null?"":production.getPublishStatus());
        result.put("publishStatusIndex",production.getPublishStatusIndex()==null?"":production.getPublishStatusIndex());
        //作品状态
        result.put("yrProductionStatus",production.getYrProductionStatus()==null?"":production.getYrProductionStatus());
        result.put("yrProductionStatusIndex",production.getYrProductionStatusIndex()==null?"":production.getYrProductionStatusIndex());
        //发布时间
        if(StringUtil.isNotEmpty(production.getPublishTime())){
            try {
                result.put("publishTime",DateUtil.format(DateUtil.parseDate(production.getPublishTime(), "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            result.put("publishTime",production.getPublishTime());
        }

        //发布平台
        result.put("publishPlatform",production.getPublishPlatform()==null?"":production.getPublishPlatform());
        //文章内容
        /*String content = production.getLocalcontent()==null?"":production.getLocalcontent();
        if(returnPercent==30){
            result.put("localContent",getThirtyPercent(content));
        }else if(returnPercent==100){
            result.put("localContent",content);
        }else{
            result.put("localContent","");
        }*/
        //创作者头像
        result.put("authorImg",production.getYrAuthor()==null?"":production.getYrAuthor().getAuthorImg());
        //创作者状态
        result.put("authorStatus",production.getYrAuthor()==null?"":production.getYrAuthor().getAuthorStatus());
        result.put("authorStatusName",production.getYrAuthor()==null?"":production.getYrAuthor().getEnumAuthorStatus().getName());
        //浏览量
        result.put("accessTimes",production.getAccessNum()==null?0:production.getAccessNum());
        //人气
        //result.put("authorAccessTimes",yRAuthor.getAccessTimes()==null?0:yRAuthor.getAccessTimes());
        result.put("authorAccessNum",yRAuthor.getAccessNum()==null?0:yRAuthor.getAccessNum());
        //图文热词
        //result.put("horWords",production.getHotWords()==null?0:production.getHotWords());
        //作品(显示实际的作品数量)
        result.put("productionNum",num);
        //最近5片已发布文章（只返回文章标题和ID）
        result.put("productionList",productionList.size()==0?"":productionList);
        //内容指数
        result.put("contentEvaluationScore",production.getContentEvaluationScore());
        //分类
        result.put("yrCategoryId",production.getYrCategoryId());
        result.put("yrCategoryName",production.getYrCategory()==null?"":production.getYrCategory().getName());
        //高频词
        result.put("tags",production.getTags());
        result.put("returnPercent",returnPercent);
        //是否加入购物车
        result.put("isAddCart",production.getIsAddCart());
        //原创度
        result.put("originalScore",production.getOriginalScore());
        return result;
    }

    /**
     *@author songwq
     *@param
     *@date 2018/8/3
     *@description 获取作品50%的内容
    */
    public static String getThirtyPercent(String content){
        String returnContent = "";
        if(StringUtils.isNotBlank(content)){
            returnContent =  FilterHtmlUtil.Html2Text(content);
            //截取50%的内容返回前台
            returnContent = returnContent.substring(0,Math.round(returnContent.length()/2));
        }
        return returnContent;
    }

    public static YRProduction getProduction(YRProduction production,Integer returnPercent,Integer getMoreInfo,Integer isAuthor){

        //是否作者本人
        production.setIsAuthor(isAuthor);//1是0否
        //是否可查看全部0可以，1不可以
        production.setGetMoreInfo(getMoreInfo);
        //发布时间
        if(StringUtil.isNotEmpty(production.getPublishTime())){
            try {
                production.setPublishTime(DateUtil.format(DateUtil.parseDate(production.getPublishTime(), "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            production.setPublishTime(production.getPublishTime());
        }

        //文章内容
        String content = production.getLocalcontent()==null?"":production.getLocalcontent();
        if(returnPercent==30){
            production.setLocalcontent(getThirtyPercent(content));
        }else if(returnPercent==100){
            production.setLocalcontent(content);
        }else{
            production.setLocalcontent("");
        }
        return production;
    }
}
