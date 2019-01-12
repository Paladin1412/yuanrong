package com.yuanrong.admin.bean.order;
import java.io.Serializable;
import java.math.*;
import java.util.Date;
import java.util.List;

import com.yuanrong.admin.Enum.EnumChannel;
import com.yuanrong.admin.Enum.EnumOrderSellerStatus;
import com.yuanrong.admin.Enum.EnumOrderSellerType;
import com.yuanrong.admin.Enum.EnumSellerOrderStatus;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.util.EnumUtil;

/**
 * 卖家订单的实体类
 *
 * @author MDA
 *
 */
public class SellerOrder extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private Integer sellerOrderId;
    /*****自定义属性区域begin******/
   
   
    /**
     * 订单明细Id
     * 订单明细Id
     */
    private Integer orderDetailId;
   
    /**
     * 卖家订单号
     * 卖家订单号
     */
    private String orderSn;
   
    /**
     * 执行价
     * 执行价
     */
    private BigDecimal price;
   
    /**
     * 应付卖家金额
     * 应付卖家金额
     */
    private BigDecimal payable;
   
    /**
     * 卖家订单类型（1.作品订单；2. 营销分发(账号)；3. 定制内容(创作者))
     * 卖家订单类型（1.作品订单；2. 营销分发(账号)；3. 定制内容(创作者))
     */
    private EnumOrderSellerType enumOrderSellerType;
    /*
    * 用于接收前台数据
    */
    private Integer sellerOrderType;
   
    /**
     * 卖家订单状态(1.待执行；2.已完成；3.已取消)
     * 卖家订单状态(1.待执行；2.已完成；3.已取消)
     */
    private EnumSellerOrderStatus enumSellerOrderStatus;
    /*
    * 用于接收前台数据
    */
    private Integer orderStatus;
   
    /**
     * 数量
     * 数量
     */
    private Integer num;
   
    /**
     * 商品名字
     * 商品名字
     */
    private String production;
   
    /**
     * 卖家服务费率
     * 卖家服务费率
     */
    private BigDecimal sellerServiceRate;

    /**
     * 创建来源
     */
    private EnumChannel channel;
    private Integer channelIndex;
    /**
     * 卖家用户
     * 卖家用户
     */
    private Integer registeredUserInfoId;
    /**
     * 封装类订单详情
     */
    private OrderDetail orderDetail;
    /**
     * 流水记录
     */
    private OrderInfoSeller orderInfoSeller;
    /**
     * 用户简称
     */
    private String nickName;
    /**
     * 创建时间
     */
    private Date startCreatedTime;
    /**
     * 创建时间
     */
    private Date endCreatedTime;
    /**
     * 订单完成时间
     */
    private Date finishTime;

    private SnapshotYrAuthor snapshotYrAuthor;

    private SnapshotAccount snapshotAccount;

    private SnapshotYrProduction snapshotYrProduction;


    private OrderInfoBuyer orderInfoBuyer;

    private PlatformIPAccount platformIPAccount;

    private YRProduction yrProduction;

    private Demand demand;

    private RegisteredUserInfo registeredUserInfo;

    //报名号
    private String serialOrderSn;
    //卖家媒介
    private Integer mediaUserId;
    //订单失效原因
    private String  cancelReason;

    private List<OrderInfoOffer> orderInfoOfferList;
    //字典表
    private DictInfo dictInfo;

    private AdminUser sale;

    private AdminUser media;

    private AdminUser productSale;

    private AdminUser productMedia;

    public Integer getSellerOrderId() {
        return this.sellerOrderId;
    }
    public void setSellerOrderId(Integer sellerOrderId) {
        this.sellerOrderId = sellerOrderId;
    }
    /*****自定义属性区域begin.get/set******/
   
    public Integer getOrderDetailId() {
        return this.orderDetailId;
    }
    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
   
    public String getOrderSn() {
        return this.orderSn;
    }
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }
    public BigDecimal getPrice() {
        return this.price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public BigDecimal getPayable() {
        return this.payable;
    }
    public void setPayable(BigDecimal payable) {
        this.payable = payable;
    }

    public EnumOrderSellerType getEnumOrderSellerType() {
    return enumOrderSellerType;
}
    public void setEnumOrderSellerType(EnumOrderSellerType enumOrderSellerType) {
        this.enumOrderSellerType = enumOrderSellerType;
        this.sellerOrderType = enumOrderSellerType.getIndex();
    }
    public Integer getSellerOrderType() {
        return sellerOrderType;
    }
    public void setSellerOrderType(Integer sellerOrderType) {
        this.sellerOrderType = sellerOrderType;
        this.enumOrderSellerType = (EnumOrderSellerType) EnumUtil.valueOf(EnumOrderSellerType.class, sellerOrderType);
    }

    public EnumSellerOrderStatus getEnumSellerOrderStatus() {
    return enumSellerOrderStatus;
}
    public void setEnumSellerOrderStatus(EnumSellerOrderStatus enumSellerOrderStatus) {
        this.enumSellerOrderStatus = enumSellerOrderStatus;
        this.orderStatus = enumSellerOrderStatus.getIndex();
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
        this.enumSellerOrderStatus = (EnumSellerOrderStatus) EnumUtil.valueOf(EnumSellerOrderStatus.class, orderStatus);
    }
    public Integer getNum() {
        return this.num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
   
    public String getProduction() {
        return this.production;
    }
    public void setProduction(String production) {
        this.production = production;
    }
    public BigDecimal getSellerServiceRate() {
        return this.sellerServiceRate;
    }
    public void setSellerServiceRate(BigDecimal sellerServiceRate) {
        this.sellerServiceRate = sellerServiceRate;
    }
    public Integer getRegisteredUserInfoId() {
        return this.registeredUserInfoId;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public OrderInfoSeller getOrderInfoSeller() {
        return orderInfoSeller;
    }

    public void setOrderInfoSeller(OrderInfoSeller orderInfoSeller) {
        this.orderInfoSeller = orderInfoSeller;
    }

    public SnapshotYrAuthor getSnapshotYrAuthor() {
        return snapshotYrAuthor;
    }

    public void setSnapshotYrAuthor(SnapshotYrAuthor snapshotYrAuthor) {
        this.snapshotYrAuthor = snapshotYrAuthor;
    }

    public SnapshotAccount getSnapshotAccount() {
        return snapshotAccount;
    }

    public void setSnapshotAccount(SnapshotAccount snapshotAccount) {
        this.snapshotAccount = snapshotAccount;
    }

    public SnapshotYrProduction getSnapshotYrProduction() {
        return snapshotYrProduction;
    }

    public void setSnapshotYrProduction(SnapshotYrProduction snapshotYrProduction) {
        this.snapshotYrProduction = snapshotYrProduction;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public OrderInfoBuyer getOrderInfoBuyer() {
        return orderInfoBuyer;
    }

    public void setOrderInfoBuyer(OrderInfoBuyer orderInfoBuyer) {
        this.orderInfoBuyer = orderInfoBuyer;
    }

    public PlatformIPAccount getPlatformIPAccount() {
        return platformIPAccount;
    }

    public void setPlatformIPAccount(PlatformIPAccount platformIPAccount) {
        this.platformIPAccount = platformIPAccount;
    }

    public YRProduction getYrProduction() {
        return yrProduction;
    }

    public void setYrProduction(YRProduction yrProduction) {
        this.yrProduction = yrProduction;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public RegisteredUserInfo getRegisteredUserInfo() {
        return registeredUserInfo;
    }

    public void setRegisteredUserInfo(RegisteredUserInfo registeredUserInfo) {
        this.registeredUserInfo = registeredUserInfo;
    }

    public String getSerialOrderSn() {
        return serialOrderSn;
    }

    public void setSerialOrderSn(String serialOrderSn) {
        this.serialOrderSn = serialOrderSn;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getStartCreatedTime() {
        return startCreatedTime;
    }

    public void setStartCreatedTime(Date startCreatedTime) {
        this.startCreatedTime = startCreatedTime;
    }

    public Date getEndCreatedTime() {
        return endCreatedTime;
    }

    public void setEndCreatedTime(Date endCreatedTime) {
        this.endCreatedTime = endCreatedTime;
    }

    public Integer getMediaUserId() {
        return mediaUserId;
    }

    public void setMediaUserId(Integer mediaUserId) {
        this.mediaUserId = mediaUserId;
    }

    public List<OrderInfoOffer> getOrderInfoOfferList() {
        return orderInfoOfferList;
    }

    public void setOrderInfoOfferList(List<OrderInfoOffer> orderInfoOfferList) {
        this.orderInfoOfferList = orderInfoOfferList;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public DictInfo getDictInfo() {
        return dictInfo;
    }

    public void setDictInfo(DictInfo dictInfo) {
        this.dictInfo = dictInfo;
    }

    public AdminUser getSale() {
        return sale;
    }

    public void setSale(AdminUser sale) {
        this.sale = sale;
    }

    public AdminUser getMedia() {
        return media;
    }

    public void setMedia(AdminUser media) {
        this.media = media;
    }

    public AdminUser getProductSale() {
        return productSale;
    }

    public void setProductSale(AdminUser productSale) {
        this.productSale = productSale;
    }

    public AdminUser getProductMedia() {
        return productMedia;
    }

    public void setProductMedia(AdminUser productMedia) {
        this.productMedia = productMedia;
    }
}
