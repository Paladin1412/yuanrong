package com.yuanrong.admin.seach;

import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.StringUtil;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @Description:    条件查询作品的前台参数vo类
* @Author:         ShiLinghuai
* @CreateDate:     2018/5/21 10:15
* @UpdateUser:     ShiLinghuai
* @UpdateDate:     2018/5/21 10:15
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class YRProductionListParam extends BaseModel implements Serializable{
    /**
     * 作品名
     */
    private String productionName;
    /**
     * 作者昵称
     */
    private String authorNickname;
    /**
     * 内容形式id
     */
    private Integer contentFormID;
    /**
     * 发表状态id
     */
    private Integer publishStatusID;
    /**
     * 当前登录对象
     */
    private RegisteredUserInfo registeredUserInfo;
    private Integer registerUserInfoId;

    /**
     * 创作者
     */
    private Integer yrAuthorId;

    /**
     * 报价
     */
    private BigDecimal priceStart;
    private BigDecimal pirceEnd;

    /**
     * 作品状态
     */
    private String yrProductionStatus;

    /**
     * 发布状态
     */
    private String publishStatus;

    /**
     * 文章ID
     * @return
     */
    private String ypRecId;
    private String[] ypRecIds;

    /**
     * 是否代表作
     * @return
     */
    private String isRepresentative;

    /**
     * 用户简称
     * @return
     */
    private String nickName;
    private String[] nickNames;

    /**
     * UserId
     * @return
     */
    private String rgRecId;
    private String[] rgRecIds;

    /**
     * 开始入库时间
     * @return
     */
    private String startCreatedTime;

    /**
     * 结束入库时间
     * @return
     */
    private String endCreatedTime;
    /**
     * 内容形式
     */
    private  Integer yrCategoryId;

    /**
     * 营销场景
     */
    private Integer marketingSceneId;

    /**
     * 排序
     */
    private String sortPrice;
    private String sortComposite;
    private String latest;

    /**
     * 搜索条件
     */
    private String keyWord;
    /**
     * 需求Id
     */
    private Integer demandId;
    /**
     * 作品唯一值
     */
    private String onlyValue;

    public RegisteredUserInfo getRegisteredUserInfo() {
        return registeredUserInfo;
    }

    public void setRegisteredUserInfo(RegisteredUserInfo registeredUserInfo) {
        this.registeredUserInfo = registeredUserInfo;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public String getAuthorNickname() {
        return authorNickname;
    }

    public void setAuthorNickname(String authorNickname) {
        this.authorNickname = authorNickname;
    }

    public Integer getContentFormID() {
        return contentFormID;
    }

    public void setContentFormID(Integer contentFormID) {
        this.contentFormID = contentFormID;
    }

    public Integer getPublishStatusID() {
        return publishStatusID;
    }

    public void setPublishStatusID(Integer publishStatusID) {
        this.publishStatusID = publishStatusID;
    }

    public Integer getYrAuthorId() {
        return yrAuthorId;
    }

    public void setYrAuthorId(Integer yrAuthorId) {
        this.yrAuthorId = yrAuthorId;
    }

    public BigDecimal getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(BigDecimal priceStart) {
        this.priceStart = priceStart;
    }

    public BigDecimal getPirceEnd() {
        return pirceEnd;
    }

    public void setPirceEnd(BigDecimal pirceEnd) {
        this.pirceEnd = pirceEnd;
    }

    public String getYrProductionStatus() {
        return yrProductionStatus;
    }

    public void setYrProductionStatus(String yrProductionStatus) {
        this.yrProductionStatus = yrProductionStatus;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getYpRecId() {
        return ypRecId;
    }

    public void setYpRecId(String ypRecId) {
        this.ypRecId = ypRecId;
    }

    public String getIsRepresentative() {
        return isRepresentative;
    }

    public void setIsRepresentative(String isRepresentative) {
        this.isRepresentative = isRepresentative;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRgRecId() {
        return rgRecId;
    }

    public void setRgRecId(String rgRecId) {
        this.rgRecId = rgRecId;
    }

    public String getStartCreatedTime() {
        return startCreatedTime;
    }

    public void setStartCreatedTime(String startCreatedTime) {
        this.startCreatedTime = startCreatedTime;
    }

    public String getEndCreatedTime() {
        return endCreatedTime;
    }

    public void setEndCreatedTime(String endCreatedTime) {
        this.endCreatedTime = endCreatedTime;
    }

    public String[] getYpRecIds() {
        return ypRecIds;
    }

    public void setYpRecIds(String[] ypRecIds) {
        this.ypRecIds = ypRecIds;
    }

    public String[] getNickNames() {
        return nickNames;
    }

    public void setNickNames(String[] nickNames) {
        this.nickNames = nickNames;
    }

    public String[] getRgRecIds() {
        return rgRecIds;
    }

    public void setRgRecIds(String[] rgRecIds) {
        this.rgRecIds = rgRecIds;
    }

    public Integer getYrCategoryId() {
        return yrCategoryId;
    }

    public void setYrCategoryId(Integer yrCategoryId) {
        this.yrCategoryId = yrCategoryId;
    }

    public Integer getMarketingSceneId() {
        return marketingSceneId;
    }

    public void setMarketingSceneId(Integer marketingSceneId) {
        this.marketingSceneId = marketingSceneId;
    }

    public String getSortPrice() {
        return sortPrice;
    }

    public void setSortPrice(String sortPrice) {
        this.sortPrice = sortPrice;
    }

    public String getSortComposite() {
        return sortComposite;
    }

    public void setSortComposite(String sortComposite) {
        this.sortComposite = sortComposite;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getRegisterUserInfoId() {
        return registerUserInfoId;
    }

    public void setRegisterUserInfoId(Integer registerUserInfoId) {
        this.registerUserInfoId = registerUserInfoId;
    }

    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public String getOnlyValue() {
        return onlyValue;
    }

    public void setOnlyValue(String onlyValue) {
        this.onlyValue = onlyValue;
    }

    public String getSort(){
        String sort = "";
        if(StringUtil.isNotBlank(this.getSortPrice())){
            if("desc".equals(this.getSortPrice())){
                sort += " yp.productQuotedPrice desc ";
            }else {
                sort += " yp.productQuotedPrice asc ";
            }
        }

        if(StringUtil.isNotBlank(this.getSortComposite())){
            if(StringUtil.isNotBlank(sort)){
                sort += ",";
            }
            if("desc".equals(this.getSortComposite())){
                sort += " yp.sortScore desc ";
            }else {
                sort += " yp.sortScore asc ";
            }
        }

        if(StringUtil.isNotBlank(this.getLatest())){
            if(StringUtil.isNotBlank(sort)){
                sort += ",";
            }
            if("desc".equals(this.getLatest())){
                sort += " yp.upTime desc ";
            }else {
                sort += " yp.upTime asc ";
            }
        }

        if(StringUtil.isBlank(sort)){
            sort += " yp.sortScore desc ";
        }
        return sort;
    }
}
