package com.yuanrong.admin.bean.trading;

import java.io.Serializable;

import com.yuanrong.admin.Enum.EnumChannel;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.util.EnumUtil;

/**
 * 交易记录的实体类
 *
 * @author MDA
 */
public class TradingRecord extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增类型
     */
    private Integer tradingRecordId;
    /*****自定义属性区域begin******/

    /**
     * 用户简称
     */
    private String nickName;

    /**
     * 交易执行时间
     * 交易执行时间
     */
    private String tradingDate;

    /**
     * 卖方交易账号
     * 卖方交易账号
     */
    private String sellerAccount;

    /**
     * 交易账号ID
     * 交易账号ID
     */
    private String accountId;

    /**
     * 交易所涉平台
     * 交易所涉平台
     */
    private String referPlatform;

    /**
     * 交易服务内容
     * 交易服务内容
     */
    private String servicesContent;

    /**
     * 交易金额（元）
     * 交易金额（元）
     */
    private String money;

    /**
     * 合作品牌
     * 合作品牌
     */
    private String cooPerationBrand;

    /**
     * 买方
     * 买方
     */
    private String buyerName;

    /**
     * 所属注册用户
     * 所属注册用户
     */
    private RegisteredUserInfo registeredUserInfo;

    /**
     * 所属注册用户Id
     * 所属注册用户Id
     */
    private Integer registeredUserInfoId;

    /**
     * 来源、渠道
     * 来源、渠道
     */
    private EnumChannel channel;
    /*
     * 用于接收前台数据
     */
    private Integer channelIndex;

    /**
     * 上传人
     * 上传人
     */
    private String heir;

    public Integer getTradingRecordId() {
        return tradingRecordId;
    }

    public void setTradingRecordId(Integer tradingRecordId) {
        this.tradingRecordId = tradingRecordId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /*****自定义属性区域begin.get/set******/



    public String getTradingDate() {
        return this.tradingDate;
    }

    public void setTradingDate(String tradingDate) {
        this.tradingDate = tradingDate;
    }

    public String getSellerAccount() {
        return this.sellerAccount;
    }

    public void setSellerAccount(String sellerAccount) {
        this.sellerAccount = sellerAccount;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getReferPlatform() {
        return this.referPlatform;
    }

    public void setReferPlatform(String referPlatform) {
        this.referPlatform = referPlatform;
    }

    public String getServicesContent() {
        return this.servicesContent;
    }

    public void setServicesContent(String servicesContent) {
        this.servicesContent = servicesContent;
    }

    public String getMoney() {
        return this.money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCooPerationBrand() {
        return this.cooPerationBrand;
    }

    public void setCooPerationBrand(String cooPerationBrand) {
        this.cooPerationBrand = cooPerationBrand;
    }

    public String getBuyerName() {
        return this.buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public RegisteredUserInfo getRegisteredUserInfo() {
        return this.registeredUserInfo;
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
        this.channel = (EnumChannel) EnumUtil.valueOf(EnumChannel.class, channelIndex);
    }

    public String getHeir() {
        return this.heir;
    }

    public void setHeir(String heir) {
        this.heir = heir;
    }
}
