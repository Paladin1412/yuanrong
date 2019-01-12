package com.yuanrong.admin.bean.order;
import java.util.*;
import java.io.Serializable;
import java.math.*;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.account.PlatformIPAccount;

/**
 * 账号快照的实体类
 *
 * @author MDA
 *
 */
public class SnapshotAccount extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private int snapshotAccountId;
    /*****自定义属性区域begin******/
   
   
    /**
     * 卖家订单
     * 卖家订单
     */
    private OrderInfoSeller orderInfoSeller;
    /**
     * 卖家订单Id
     */
    private Integer orderInfoSellerId;
   
    /**
     * 账号名称，中文
     * 账号名称，中文
     */
    private String name;
   
    /**
     * 账号名id-微信号，微博号
     * 账号名id-微信号，微博号
     */
    private String accountID;
   
    /**
     * 平台名字
     * 平台名字
     */
    private String platformName;
   
    /**
     * 平台logo
     * 平台logo
     */
    private String platformLogo;
   
    /**
     * 头像
     * 头像
     */
    private String headImageUrlLocal;
   
    /**
     * 二维码
     * 二维码
     */
    private String qRcodeImageUrlLocal;
   
    /**
     * 账号简介
     * 账号简介
     */
    private String introduction;
   
    /**
     * 账号分类
     * 账号分类
     */
    private String categoryName;
   
    /**
     * 账号报价
     * 账号报价
     */
    private String priceInfo;
   
    /**
     * 有效期
     * 有效期
     */
    private String invalidTime;
   
    /**
     * 平台唯一标识
     * 平台唯一标识
     */
    private String pid;
   
    /**
     * 账号
     * 账号
     */
    private PlatformIPAccount platformIPAccount;
    /**
     * 账号
     * 账号
     */
    private Integer platformIPAccountId;

    private Integer orderDetailId;
    /**
     * 粉丝数
     */
    private Integer fans;
    public int getSnapshotAccountId() {
        return this.snapshotAccountId;
    }
    public void setSnapshotAccountId(int snapshotAccountId) {
        this.snapshotAccountId = snapshotAccountId;
    }
    /*****自定义属性区域begin.get/set******/

    public Integer getOrderInfoSellerId() {
        return orderInfoSellerId;
    }

    public void setOrderInfoSellerId(Integer orderInfoSellerId) {
        this.orderInfoSellerId = orderInfoSellerId;
    }

    public OrderInfoSeller getOrderInfoSeller() {
        return this.orderInfoSeller;
    }
    public void setOrderInfoSeller(OrderInfoSeller orderInfoSeller) {
        this.orderInfoSeller = orderInfoSeller;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAccountID() {
        return this.accountID;
    }
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
    public String getPlatformName() {
        return this.platformName;
    }
    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
    public String getPlatformLogo() {
        return this.platformLogo;
    }
    public void setPlatformLogo(String platformLogo) {
        this.platformLogo = platformLogo;
    }
    public String getHeadImageUrlLocal() {
        return this.headImageUrlLocal;
    }
    public void setHeadImageUrlLocal(String headImageUrlLocal) {
        this.headImageUrlLocal = headImageUrlLocal;
    }
    public String getQRcodeImageUrlLocal() {
        return this.qRcodeImageUrlLocal;
    }
    public void setQRcodeImageUrlLocal(String qRcodeImageUrlLocal) {
        this.qRcodeImageUrlLocal = qRcodeImageUrlLocal;
    }
    public String getIntroduction() {
        return this.introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public String getCategoryName() {
        return this.categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getPriceInfo() {
        return this.priceInfo;
    }
    public void setPriceInfo(String priceInfo) {
        this.priceInfo = priceInfo;
    }
    public String getInvalidTime() {
        return this.invalidTime;
    }
    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }
    public String getPid() {
        return this.pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public PlatformIPAccount getPlatformIPAccount() {
        return this.platformIPAccount;
    }
    public void setPlatformIPAccount(PlatformIPAccount platformIPAccount) {
        this.platformIPAccount = platformIPAccount;
    }

    public String getqRcodeImageUrlLocal() {
        return qRcodeImageUrlLocal;
    }

    public void setqRcodeImageUrlLocal(String qRcodeImageUrlLocal) {
        this.qRcodeImageUrlLocal = qRcodeImageUrlLocal;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Integer getPlatformIPAccountId() {
        return platformIPAccountId;
    }

    public void setPlatformIPAccountId(Integer platformIPAccountId) {
        this.platformIPAccountId = platformIPAccountId;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
}
