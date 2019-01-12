package com.yuanrong.admin.bean.account;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumChannel;
import com.yuanrong.admin.Enum.EnumSex;
import com.yuanrong.admin.Enum.EnumYesOrNo;
import com.yuanrong.admin.Enum.EnumPlatformIPAccountStatus;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.StringUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/4/27.
 * 平台IP账号
 */
public class PlatformIPAccount extends BaseBean implements Serializable {
    private int id ;
    /**
     * 账号名称，中文
     */
    private String name ;
    /**
     * 账号名id-微信号，微博号，美拍号
     */
    private String accountID;
    /**
     * 文章抓取url
     */
    private String articleUrl;
    private int platformID; //	平台id
    private ShortVideoPlatformInfo shortVideoPlatformInfo;
    private String headImageUrl; //	网络原始头图
    private String headImageUrlLocal; //	本地化原始头图
    private String qRcodeImageUrl; //	二维码网络图片
    private String qRcodeImageUrlLocal; //	二维码本地图片
    private int fans; //	粉丝数
    private String introduction; //	账号简介
    private int categoryID; //	字典表中 type=2 （微信）3 （微博）4（短视频）
    private DictInfo dictInfoCategory;
    private String lowerCauseID; //	字典表（type=5 未上架原因）
    private DictInfo dictInfoLowerCause;
    private String notes; //	合作须知
    private String successUrl; //	成功案例
    private String successTitle; //	成功案例品牌说明
    private String advantage; //	突出优势
    private int isAuthentication; //	是否认证
    private EnumYesOrNo isAuthenticationEnum;
    private String authenticationInfo; //	认证信息
    private int cityID; //	城市id
    private String areaName;
    private BigDecimal womanPercent; //	女性比例
    private int createrAdminID; //	创建者id
    private int  adminMediaID; //	后台媒介id
    private Integer  iPID; //	IP账号对应

    /**
     * 用户主页
     */
    private String indexUrl;

    private int registeredUserInfoID; //对应注册用户ID

    /**
     * 平台名字
     */
    private String platformName;
    private String accountStatusName;
    private String categoryName;
    /**
     * 类型
     */
    private String categorys;



    /**
     * 性别
     */
    private int sex;
    private EnumSex sexEnum;

    private int accountStatus; //	-1不接单 0 下架 1 上架
    private EnumPlatformIPAccountStatus platformIPAccountStatus;
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
    /**
     * 报价失效时间
     */
    private String invalidTime ;

    private String platPrices;

    /**
     * 平台唯一标识
     */
    private String pid;

    /**
     * 被约次数
     */
    private int reservationNum;

    /**
     * 圆融指数
     */
    private double yrIndex;

    /**
     * 平均播放数
     */
    private Integer avgPlayCount;

    /**
     * 用于通过购物车选择平台IP账号
     */
    private Integer shoppingCartId;

    /**
     * 最后更新时间
     */
    private Date lastUpdate;

    /**
     * 是否创建创作者
     */
    private Integer isCreatedYrAuthor;

    /**
     * 所属唯一账号
     */
    private int accountOnlyId;

    /**
     * 批量导入错误信息
     */
    private String importErrorMsg;

    private String icoUrl;

    /**
     * 是否代理
     */
    private Integer isAgent;
    /**
     * 代理权合作品牌
     */
    private String agentCoopBrand;
    /**
     * 代理权合作条件
     */
    private String agentCoopCondition;

    public String getCategorys() {
        return categorys;
    }

    public void setCategorys(String categorys) {
        this.categorys = categorys;
    }

    /**
     * 渠道
     */
    private EnumChannel channel;
    private Integer channelIndex;

    public String getIcoUrl() {
        return icoUrl;
    }

    public void setIcoUrl(String icoUrl) {
        this.icoUrl = icoUrl;
    }

    private List<PlatformIPAccountPrice> ipAccountPrices = new ArrayList<PlatformIPAccountPrice>();

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }

    public Integer getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Integer shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public int getPlatformID() {
        return platformID;
    }

    public void setPlatformID(int platformID) {
        this.platformID = platformID;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public String getHeadImageUrlLocal() {
        return headImageUrlLocal;
    }

    public void setHeadImageUrlLocal(String headImageUrlLocal) {
        this.headImageUrlLocal = headImageUrlLocal;
    }

    public String getqRcodeImageUrl() {
        return qRcodeImageUrl;
    }

    public void setqRcodeImageUrl(String qRcodeImageUrl) {
        this.qRcodeImageUrl = qRcodeImageUrl;
    }

    public String getqRcodeImageUrlLocal() {
        return qRcodeImageUrlLocal;
    }

    public void setqRcodeImageUrlLocal(String qRcodeImageUrlLocal) {
        this.qRcodeImageUrlLocal = qRcodeImageUrlLocal;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getLowerCauseID() {
        return lowerCauseID;
    }

    public void setLowerCauseID(String lowerCauseID) {
        this.lowerCauseID = lowerCauseID;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getSuccessTitle() {
        return successTitle;
    }

    public void setSuccessTitle(String successTitle) {
        this.successTitle = successTitle;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public int getIsAuthentication() {
        return isAuthentication;
    }

    public void setIsAuthentication(int isAuthentication) {
        this.isAuthenticationEnum = (EnumYesOrNo) EnumUtil.valueOf(EnumYesOrNo.class , isAuthentication);
        this.isAuthentication = isAuthentication;
    }

    public String getAuthenticationInfo() {
        return authenticationInfo;
    }

    public void setAuthenticationInfo(String authenticationInfo) {
        this.authenticationInfo = authenticationInfo;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public BigDecimal getWomanPercent() {
        return womanPercent;
    }

    public void setWomanPercent(BigDecimal womanPercent) {
        this.womanPercent = womanPercent;
    }

    public int getCreaterAdminID() {
        return createrAdminID;
    }

    public void setCreaterAdminID(int createrAdminID) {
        this.createrAdminID = createrAdminID;
    }

    public int getAdminMediaID() {
        return adminMediaID;
    }

    public void setAdminMediaID(int adminMediaID) {
        this.adminMediaID = adminMediaID;
    }

    public Integer getiPID() {
        return iPID;
    }

    public void setiPID(Integer iPID) {
        this.iPID = iPID;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
        this.platformIPAccountStatus = (EnumPlatformIPAccountStatus) EnumUtil.valueOf(EnumPlatformIPAccountStatus.class , accountStatus);
    }

    public int getRegisteredUserInfoID() {
        return registeredUserInfoID;
    }

    public void setRegisteredUserInfoID(int registeredUserInfoID) {
        this.registeredUserInfoID = registeredUserInfoID;
    }

    public EnumPlatformIPAccountStatus getPlatformIPAccountStatus() {
        return platformIPAccountStatus;
    }

    public void setPlatformIPAccountStatus(EnumPlatformIPAccountStatus platformIPAccountStatus) {
        this.platformIPAccountStatus = platformIPAccountStatus;
        this.accountStatus = platformIPAccountStatus.getIndex();
    }

    public EnumYesOrNo getIsAuthenticationEnum() {
        return isAuthenticationEnum;
    }

    public void setIsAuthenticationEnum(EnumYesOrNo isAuthenticationEnum) {
        this.isAuthentication = isAuthenticationEnum.getIndex();
        this.isAuthenticationEnum = isAuthenticationEnum;
    }

    public String getPlatPrices() {
        return platPrices;
    }

    public void setPlatPrices(String platPrices) {
        this.platPrices = platPrices;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sexEnum = (EnumSex) EnumUtil.valueOf(EnumSex.class , sex);
        this.sex = sex;
    }

    public EnumSex getSexEnum() {
        return sexEnum;
    }

    public void setSexEnum(EnumSex sexEnum) {
        this.sex = sexEnum.getIndex();
        this.sexEnum = sexEnum;
    }

    public List<PlatformIPAccountPrice> getIpAccountPrices() {
        return ipAccountPrices;
    }

    public void setIpAccountPrices(List<PlatformIPAccountPrice> ipAccountPrices) {
        this.ipAccountPrices = ipAccountPrices;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }

    public int getReservationNum() {
        return reservationNum;
    }

    public void setReservationNum(int reservationNum) {
        this.reservationNum = reservationNum;
    }

    public double getYrIndex() {
        return yrIndex;
    }

    public void setYrIndex(double yrIndex) {
        this.yrIndex = yrIndex;
    }

    public Integer getAvgPlayCount() {
        return avgPlayCount;
    }

    public void setAvgPlayCount(Integer avgPlayCount) {
        this.avgPlayCount = avgPlayCount;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
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

    /**
     * 将Map 转换成JSON
     * @param ele
     * @return
     */
    public static JSONObject mapPareJSONObject(Map<String, Object> ele){
        JSONObject jsonObject = new JSONObject();
        //账号-账号名
        jsonObject.put("name" , ele.get("name"));
        //账号-平台账号ID
        jsonObject.put("accountID" , ele.get("accountID"));
        //账号-系统ID
        jsonObject.put("id" , ele.get("id"));
        //平台图标
        jsonObject.put("icoUrl" , ele.get("icoUrl"));
        //账号报价
        String infos = (String) ele.get("info");
        if(StringUtil.isNoneBlank(infos)){
            jsonObject.put("pricesInfo" , getJSONArraybyStringPrice(infos));
        }
        //报价失效时间
        try {
            jsonObject.put("invalidTime" ,ele.get("invalidTime") ==null ? "" : DateUtil.format(DateUtil.parseDate(ele.get("invalidTime").toString(), "yyyy-MM-dd" ), "yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //数据展示
        jsonObject.put("avgReadCount" , ele.get("avgReadCount"));
        jsonObject.put("avgLikeCount" , ele.get("avgLikeCount"));
        jsonObject.put("avgForwardCount" , ele.get("avgForwardCount"));
        jsonObject.put("avgCommontCount" , ele.get("avgCommontCount"));
        jsonObject.put("avgPlayCount" , ele.get("avgPlayCount"));
        // 消息最近更新时间
        try {
            jsonObject.put("newsLateTime" , ele.get("lastUpdate") == null ? "" :  DateUtil.format(ele.get("lastUpdate").toString() , "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //账号状态
        EnumPlatformIPAccountStatus status = ele.get("accountStatus") == null ? EnumPlatformIPAccountStatus.下架 : (EnumPlatformIPAccountStatus) EnumUtil.valueOf(EnumPlatformIPAccountStatus.class , ele.get("accountStatus").toString());
        jsonObject.put("platformIPAccountStatus" , status.getName());
        //注册用户简称-用户ID
        jsonObject.put("RecID" , ele.get("RecID"));
        //注册用户简称-用户名
        jsonObject.put("UserName" , ele.get("NickName"));
        //IP名称
        jsonObject.put("iPName" , ele.get("iPName"));
        //媒介经理
        jsonObject.put("adminUserMediaName" , ele.get("RealName"));
        //IP账号ID
        jsonObject.put("iPID" , ele.get("iPID"));
        //文章抓取url
        jsonObject.put("articleUrl" , ele.get("articleUrl"));
        //IP账号ID
        jsonObject.put("iPID" , ele.get("iPID"));
        //文章抓取url
        jsonObject.put("articleUrl" , ele.get("articleUrl"));
        //平台id
        jsonObject.put("platformID" , ele.get("platformID"));
        //网络原始头图
        jsonObject.put("headImageUrl" , ele.get("headImageUrl"));
        //	本地化原始头图
        jsonObject.put("headImageUrlLocal" , ele.get("headImageUrlLocal"));
        //	二维码网络图片
        jsonObject.put("qRcodeImageUrl" , ele.get("qRcodeImageUrl"));
        //	二维码本地图片
        jsonObject.put("qRcodeImageUrlLocal" , ele.get("qRcodeImageUrlLocal"));
        //	粉丝数
        jsonObject.put("fans" , ele.get("fans"));
        //	账号简介
        jsonObject.put("introduction" , ele.get("introduction"));
        //类别
        jsonObject.put("categoryID" , ele.get("categoryID"));
        //用户主页
        jsonObject.put("indexUrl" , ele.get("indexUrl"));
        //下架原因
        jsonObject.put("lowerCause",ele.get("lowerCause"));
        jsonObject.put("lowerCauseID" , ele.get("lowerCauseID"));
        //唯一抓取项
        jsonObject.put("articleUrl" , ele.get("articleUrl"));
        //圆融指数
        jsonObject.put("yrIndex" , ele.get("yrIndex"));
        //入库时间
        try {
            jsonObject.put("createdTime" ,ele.get("createdTime") ==null ? "" : DateUtil.format(DateUtil.parseDate(ele.get("createdTime").toString(), "yyyy-MM-dd HH:mm:ss" ), "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //二维码
        jsonObject.put("qRcodeImageUrlLocal" , ele.get("qRcodeImageUrlLocal"));
        //是否代理
        jsonObject.put("isAgent" , ele.get("isAgent")==null?"":ele.get("isAgent"));
        //代理权合作品牌
        jsonObject.put("agentCoopBrand" , ele.get("agentCoopBrand")==null?"":ele.get("agentCoopBrand"));
        //代理权合作条件
        jsonObject.put("agentCoopCondition" , ele.get("agentCoopCondition")==null?"":ele.get("agentCoopCondition"));
        //YRID
        jsonObject.put("YRID" , ele.get("accountOnlyId")==null?"":ele.get("accountOnlyId"));
        //代理权合作条件
        jsonObject.put("PID" , ele.get("pid")==null?"":ele.get("pid"));
        return jsonObject;
    }

    public static JSONArray getJSONArraybyStringPrice(String priceInfo){
        JSONArray prices = new JSONArray();
        if(StringUtil.isBlank(priceInfo)){
            return prices;
        }
        String[] price = priceInfo.split("-_-");
        for(String pr : price){
            String[] priceTemp = pr.split("_-_");
            JSONObject prInfo = new JSONObject();
            //价格名称
            prInfo.put("priceName" ,priceTemp[0] );
            //价格
            prInfo.put("price" ,priceTemp[1] );
            //是否原创
            prInfo.put("isOriginal" ,priceTemp[2]);
            //价格ID
            prInfo.put("id" ,priceTemp[3]);
            //价格名称ID
            prInfo.put("priceNameID" ,priceTemp[4]);
            prices.add(prInfo);
        }
        return prices;
    }

    public DictInfo getDictInfoCategory() {
        return dictInfoCategory;
    }

    public void setDictInfoCategory(DictInfo dictInfoCategory) {
        this.dictInfoCategory = dictInfoCategory;
    }

    public DictInfo getDictInfoLowerCause() {
        return dictInfoLowerCause;
    }

    public void setDictInfoLowerCause(DictInfo dictInfoLowerCause) {
        this.dictInfoLowerCause = dictInfoLowerCause;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public ShortVideoPlatformInfo getShortVideoPlatformInfo() {
        return shortVideoPlatformInfo;
    }

    public void setShortVideoPlatformInfo(ShortVideoPlatformInfo shortVideoPlatformInfo) {
        this.shortVideoPlatformInfo = shortVideoPlatformInfo;
    }

    public Integer getIsCreatedYrAuthor() {
        return isCreatedYrAuthor;
    }

    public void setIsCreatedYrAuthor(Integer isCreatedYrAuthor) {
        this.isCreatedYrAuthor = isCreatedYrAuthor;
    }

    public int getAccountOnlyId() {
        return accountOnlyId;
    }

    public void setAccountOnlyId(int accountOnlyId) {
        this.accountOnlyId = accountOnlyId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getAccountStatusName() {
        return accountStatusName;
    }

    public void setAccountStatusName(String accountStatusName) {
        this.accountStatusName = accountStatusName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImportErrorMsg() {
        return importErrorMsg;
    }

    public void setImportErrorMsg(String importErrorMsg) {
        this.importErrorMsg = importErrorMsg;
    }

    public static JSONObject getJSONObjectByStringPrice(String priceInfo){
        JSONObject result = new JSONObject();
        JSONArray prices = new JSONArray();
        if(StringUtil.isBlank(priceInfo)){
            return result;
        }
        String[] price = priceInfo.split("-_-");
        for(String pr : price){
            String[] priceTemp = pr.split("_-_");
            JSONObject prInfo = new JSONObject();
            //价格名称
            prInfo.put("priceName" ,priceTemp[0] );
            //价格
            prInfo.put("price" ,priceTemp[1] );
            prices.add(prInfo);
        }
        result.put("positionPrice",prices);
        result.put("platformName","圆融");
        return result;
    }


    /**
     * 将Map 转换成JSON
     * @param ele
     * @return
     */
    public static JSONObject mapParerPlatformAgent(Map<String, Object> ele){
        JSONObject jsonObject = new JSONObject();
        //账号-系统ID
        jsonObject.put("id" , ele.get("id"));
        //账号-账号名
        jsonObject.put("name" , ele.get("name"));
        //账号-账号名id
        jsonObject.put("accountID" , ele.get("accountID"));
        //平台id
        jsonObject.put("platformID" , ele.get("platformID"));
        //平台图标
        jsonObject.put("icoUrl" , ele.get("icoUrl"));

        //数据展示
        jsonObject.put("avgReadCount" , ele.get("avgReadCount"));
        jsonObject.put("avgLikeCount" , ele.get("avgLikeCount"));
        jsonObject.put("avgForwardCount" , ele.get("avgForwardCount"));
        jsonObject.put("avgCommontCount" , ele.get("avgCommontCount"));
        jsonObject.put("avgPlayCount" , ele.get("avgPlayCount"));

        //注册用户简称-用户ID
        jsonObject.put("recID" , ele.get("RecID"));
        //注册用户简称-用户名
        jsonObject.put("nickName" , ele.get("NickName"));
        //媒介经理
        jsonObject.put("adminUserMediaName" , ele.get("RealName"));
        //平台id
        //jsonObject.put("platformID" , ele.get("platformID"));
        //网络原始头图
        //jsonObject.put("headImageUrl" , ele.get("headImageUrl"));
        //	本地化原始头图
        jsonObject.put("headImageUrlLocal" , ele.get("headImageUrlLocal"));
        //	二维码网络图片
        //jsonObject.put("qRcodeImageUrl" , ele.get("qRcodeImageUrl"));
        //	二维码本地图片
        jsonObject.put("qRcodeImageUrlLocal" , ele.get("qRcodeImageUrlLocal"));
        //	粉丝数
        jsonObject.put("fans" , ele.get("fans"));
        //类别
        jsonObject.put("categoryID" , ele.get("categoryID"));
        jsonObject.put("categoryName" , ele.get("categoryName"));
        //用户主页
        jsonObject.put("indexUrl" , ele.get("indexUrl"));
        //圆融ID
        jsonObject.put("accountOnlyId" , ele.get("accountOnlyId"));
        //合作品牌
        jsonObject.put("agentCoopBrand" , ele.get("agentCoopBrand"));
        //圆融指数
        jsonObject.put("yrIndex" , ele.get("yrIndex"));
        //入库时间
        try {
            jsonObject.put("createdTime" ,ele.get("createdTime") ==null ? "" : DateUtil.format(DateUtil.parseDate(ele.get("createdTime").toString(), "yyyy-MM-dd HH:mm:ss.0" ), "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
