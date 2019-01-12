package com.yuanrong.admin.bean.order;

import java.io.Serializable;
import java.math.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumAuthorStatus;
import com.yuanrong.admin.Enum.EnumCostType;
import com.yuanrong.admin.Enum.EnumOrderSellerStatus;
import com.yuanrong.admin.Enum.EnumOrderSellerType;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.author.YRAuthor;
import com.yuanrong.admin.bean.author.YRProduction;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.seach.CommonSearchClass;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.common.util.SystemParam;
import org.springframework.stereotype.Component;

/**
 * 报名表
 *
 * @author MDA
 */
public class OrderInfoSeller extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增类型
     */
    private Integer orderInfoSellerId;
    /*****自定义属性区域begin******/


    /**
     * 订单ID
     * 订单ID
     */
    private Integer orderInfoId;

    /**
     * 需求单
     * 需求单
     */
    private Demand demand;

    private Integer demandId;

    private String demandSn;

    /**
     * 卖家订单号
     * 卖家订单号
     */
    private String orderSn;
    private String[] orderInfoSn;

    /**
     * 商品快照id(可能是作品，账号，作者)
     * 商品快照id(可能是作品，账号，作者)
     */
    private int snapshotId;
    //账号快照
    private SnapshotAccount snapshotAccount;
    //作者快照
    private SnapshotYrAuthor snapshotYrAuthor;
    /**
     * 卖家注册用户
     * 卖家注册用户
     */
    private RegisteredUserInfo saler;

    /**
     * 应付卖家金额
     */
    private BigDecimal payable;

    /**
     * 卖家注册用户Id
     */
    private Integer registeredUserInfoId;

    /**
     * 数量
     * 数量
     */
    private BigDecimal num;

    /**
     * 给卖家最终金额
     * 给卖家最终金额
     */
    private BigDecimal price;

    /**
     * 买家应付金额
     */
    private BigDecimal buyerPrice;

    /**
     * 商品名称
     * 商品名称
     */
    private String production;

    /**
     * 销售
     * 销售
     */
    private AdminUser sale;
    private String saleUser;
    /**
     * 媒介
     * 媒介
     */
    private AdminUser media;
    private String mediaUser;

    /**
     * 订单类型
     * 订单类型
     */
    private EnumOrderSellerType orderType;
    /*
     * 用于接收前台数据
     */
    private Integer orderTypeValue;

    /**
     * 订单状态
     * 订单状态
     */
    private EnumOrderSellerStatus orderStatus;
    /*
     * 用于接收前台数据
     */
    private Integer orderStatusValue;

    /**
     * 反馈执行链接
     * 反馈执行链接
     */
    private String returnUrl;

    /**
     * 反馈执行图片或者文件
     * 反馈执行图片或者文件
     */
    private String returnImg;
    private String[] returnImgs;

    /**
     * 反馈时间
     * 反馈时间
     */
    private String returnTime;

    /**
     * 响应时间
     * 响应时间
     */
    private String responseTime;

    /**
     * 执行时间
     * 执行时间
     */
    private String executeTime;

    /**
     * 拒绝使用-发布需求订单
     * 拒绝使用-发布需求订单
     */
    private String refuseReason;

    /**
     * 字典表 执行终止-发布需求订单
     * 字典表 执行终止-发布需求订单
     */
    private String executeOver;

    /**
     * 应约备注(确认推荐)
     */
    private String acceptRemark;
    /**
     * 可用排期(时间字符串)/创作耗时
     */
    private String usableDate;
    /**
     * 拒约原因
     */
    private String rejectReason;

    /**
     * 拒约备注
     */
    private String rejectRemark;
    /**
     * 发布状态(作品快照)
     */
    private SnapshotYrProduction snapshotYrProduction;

    /**
     * 账号报价
     */
    private OrderInfoOffer orderInfoOffer;
    private List<OrderInfoOffer> orderInfoOfferList;

    /**
     * 账号ID/icon/url
     */
    private PlatformIPAccount platformIPAccount;

    /**
     * orderCostInfo
     */
    private List<OrderCostInfo> orderCostInfoList;

    private YRProduction yRProduction;

    /**
     * 应约价
     *
     * @return
     */
    private BigDecimal offerPrice;
    private String[] offerPrices;

    /**
     * 卖家成本价
     */
    private BigDecimal sellerCostMoney;

    /**
     * 卖家服务费率
     * @return
     */
    private BigDecimal sellerServiceRate;
    /**
     * 买家服务费率
     * @return
     */
    private BigDecimal buyerServiceRate;
    /**
     * 确认时间
     */
    private String confirmTime;
    /**
     * 商品Id(作品、创作者、账号)
     */
    private Integer referId;

    /**
     * 买家订单
     */
    private OrderInfoBuyer orderInfoBuyer;

    public OrderInfoBuyer getOrderInfoBuyer() {
        return orderInfoBuyer;
    }

    public void setOrderInfoBuyer(OrderInfoBuyer orderInfoBuyer) {
        this.orderInfoBuyer = orderInfoBuyer;
    }

    public Integer[] getReferIds() {
        return referIds;
    }

    public void setReferIds(Integer[] referIds) {
        this.referIds = referIds;
    }

    /**
     * 商品Ids(作品、创作者、账号)
     */
    private Integer[] referIds;
    /**
     * 参考报价
     * @return
     */
    private BigDecimal referPrice;
    /**
     * 作者
     * @return
     */
    private YRAuthor yRAuthor;
    /**
     * 买家—发票费率
     */
    private BigDecimal invoiceRate;
    /**
     * 卖家应约价—原始价
     */
    private BigDecimal sellerPrice;
    /**
     * 买家订单金额(应约价+服务费)
     */
    private BigDecimal buyerOrderPrice;

    /**
     * 销售ID
     */
    private Integer saleUserId;
    /**
     * 媒介ID
     */
    private Integer mediaUserId;
    /**
     * 字典表
     */
    private DictInfo dictInfo;


    public Integer getOrderInfoSellerId() {
        return this.orderInfoSellerId;
    }

    public void setOrderInfoSellerId(Integer orderInfoSellerId) {
        this.orderInfoSellerId = orderInfoSellerId;
    }

    public String getDemandSn() {
        return demandSn;
    }

    public void setDemandSn(String demandSn) {
        this.demandSn = demandSn;
    }

    /*****自定义属性区域begin.get/set******/

    public Integer getOrderInfoId() {
        return this.orderInfoId;
    }

    public void setOrderInfoId(Integer orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public Demand getDemand() {
        return this.demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public String getOrderSn() {
        return this.orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public RegisteredUserInfo getSaler() {
        return this.saler;
    }

    public void setSaler(RegisteredUserInfo saler) {
        this.saler = saler;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public String getSaleUser() {
        return saleUser;
    }

    public void setSaleUser(String saleUser) {
        this.saleUser = saleUser;
    }

    public String getMediaUser() {
        return mediaUser;
    }

    public void setMediaUser(String mediaUser) {
        this.mediaUser = mediaUser;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProduction() {
        return this.production;
    }

    public void setProduction(String production) {
        this.production = production;
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

    public EnumOrderSellerType getOrderType() {
        return orderType;
    }

    public void setOrderType(EnumOrderSellerType orderType) {
        this.orderType = orderType;
        this.orderTypeValue = orderType.getIndex();
    }

    public Integer getOrderTypeValue() {
        return orderTypeValue;
    }

    public void setOrderTypeValue(Integer orderTypeValue) {
        this.orderTypeValue = orderTypeValue;
        this.orderType = (EnumOrderSellerType) EnumUtil.valueOf(EnumOrderSellerType.class, orderTypeValue);
    }

    public EnumOrderSellerStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(EnumOrderSellerStatus orderStatus) {
        this.orderStatus = orderStatus;
        this.orderStatusValue = orderStatus.getIndex();
    }

    public Integer getOrderStatusValue() {
        return orderStatusValue;
    }

    public void setOrderStatusValue(Integer orderStatusValue) {
        this.orderStatusValue = orderStatusValue;
        this.orderStatus = (EnumOrderSellerStatus) EnumUtil.valueOf(EnumOrderSellerStatus.class, orderStatusValue);
    }

    public String getReturnImg() {
        return this.returnImg;
    }

    public void setReturnImg(String returnImg) {
        this.returnImg = returnImg;
    }

    public String getReturnTime() {
        return this.returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getResponseTime() {
        return this.responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getExecuteTime() {
        return this.executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public String getRefuseReason() {
        return this.refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }


    public Integer getRegisteredUserInfoId() {
        return registeredUserInfoId;
    }

    public void setRegisteredUserInfoId(Integer registeredUserInfoId) {
        this.registeredUserInfoId = registeredUserInfoId;
    }


    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getRejectRemark() {
        return rejectRemark;
    }

    public void setRejectRemark(String rejectRemark) {
        this.rejectRemark = rejectRemark;
    }


    public String getUsableDate() {
        return usableDate;
    }

    public void setUsableDate(String usableDate) {
        this.usableDate = usableDate;
    }

    public String getAcceptRemark() {
        return acceptRemark;
    }

    public void setAcceptRemark(String acceptRemark) {
        this.acceptRemark = acceptRemark;
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

    public int getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(int snapshotId) {
        this.snapshotId = snapshotId;
    }


    public SnapshotYrProduction getSnapshotYrProduction() {
        return snapshotYrProduction;
    }

    public void setSnapshotYrProduction(SnapshotYrProduction snapshotYrProduction) {
        this.snapshotYrProduction = snapshotYrProduction;
    }

    public OrderInfoOffer getOrderInfoOffer() {
        return orderInfoOffer;
    }

    public void setOrderInfoOffer(OrderInfoOffer orderInfoOffer) {
        this.orderInfoOffer = orderInfoOffer;
    }

    public String[] getReturnImgs() {
        return returnImgs;
    }

    public void setReturnImgs(String[] returnImgs) {
        this.returnImgs = returnImgs;
    }

    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public List<OrderInfoOffer> getOrderInfoOfferList() {
        return orderInfoOfferList;
    }

    public void setOrderInfoOfferList(List<OrderInfoOffer> orderInfoOfferList) {
        this.orderInfoOfferList = orderInfoOfferList;
    }

    public BigDecimal getBuyerPrice() {
        return buyerPrice;
    }

    public void setBuyerPrice(BigDecimal buyerPrice) {
        this.buyerPrice = buyerPrice;
    }

    public PlatformIPAccount getPlatformIPAccount() {
        return platformIPAccount;
    }

    public void setPlatformIPAccount(PlatformIPAccount platformIPAccount) {
        this.platformIPAccount = platformIPAccount;
    }

    public List<OrderCostInfo> getOrderCostInfoList() {
        return orderCostInfoList;
    }

    public void setOrderCostInfoList(List<OrderCostInfo> orderCostInfoList) {
        this.orderCostInfoList = orderCostInfoList;
    }

    public String getExecuteOver() {
        return executeOver;
    }

    public void setExecuteOver(String executeOver) {
        this.executeOver = executeOver;
    }

    public YRProduction getyRProduction() {
        return yRProduction;
    }

    public void setyRProduction(YRProduction yRProduction) {
        this.yRProduction = yRProduction;
    }

    public BigDecimal getPayable() {
        return payable;
    }

    public void setPayable(BigDecimal payable) {
        this.payable = payable;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String[] getOfferPrices() {
        return offerPrices;
    }

    public void setOfferPrices(String[] offerPrices) {
        this.offerPrices = offerPrices;
    }

    public BigDecimal getSellerServiceRate() {
        return sellerServiceRate;
    }

    public void setSellerServiceRate(BigDecimal sellerServiceRate) {
        this.sellerServiceRate = sellerServiceRate;
    }

    public BigDecimal getBuyerServiceRate() {
        return buyerServiceRate;
    }

    public void setBuyerServiceRate(BigDecimal buyerServiceRate) {
        this.buyerServiceRate = buyerServiceRate;
    }


    public BigDecimal getSellerCostMoney() {
        return sellerCostMoney;
    }

    public void setSellerCostMoney(BigDecimal sellerCostMoney) {
        this.sellerCostMoney = sellerCostMoney;
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Integer getReferId() {
        return referId;
    }

    public void setReferId(Integer referId) {
        this.referId = referId;
    }

    public BigDecimal getReferPrice() {
        return referPrice;
    }

    public void setReferPrice(BigDecimal referPrice) {
        this.referPrice = referPrice;
    }

    public YRAuthor getyRAuthor() {
        return yRAuthor;
    }

    public void setyRAuthor(YRAuthor yRAuthor) {
        this.yRAuthor = yRAuthor;
    }

    public BigDecimal getInvoiceRate() {
        return invoiceRate;
    }

    public void setInvoiceRate(BigDecimal invoiceRate) {
        this.invoiceRate = invoiceRate;
    }

    public BigDecimal getSellerPrice() {
        return sellerPrice;
    }

    public void setSellerPrice(BigDecimal sellerPrice) {
        this.sellerPrice = sellerPrice;
    }

    public BigDecimal getBuyerOrderPrice() {
        return buyerOrderPrice;
    }

    public void setBuyerOrderPrice(BigDecimal buyerOrderPrice) {
        this.buyerOrderPrice = buyerOrderPrice;
    }

    public Integer getSaleUserId() {
        return saleUserId;
    }

    public void setSaleUserId(Integer saleUserId) {
        this.saleUserId = saleUserId;
    }

    public Integer getMediaUserId() {
        return mediaUserId;
    }

    public void setMediaUserId(Integer mediaUserId) {
        this.mediaUserId = mediaUserId;
    }

    public DictInfo getDictInfo() {
        return dictInfo;
    }

    public void setDictInfo(DictInfo dictInfo) {
        this.dictInfo = dictInfo;
    }

    public String[] getOrderInfoSn() {
        return orderInfoSn;
    }

    public void setOrderInfoSn(String[] orderInfoSn) {
        this.orderInfoSn = orderInfoSn;
    }

    /**
     * 封装订单管理列表账号报价查询
     *
     * @param
     * @return
     */
    public static JSONObject packageOrderInfoSeller(List<Object> list) {
        JSONObject orderInfoSellerInfo = new JSONObject();
        if (list.get(0) == null) {
            return orderInfoSellerInfo;
        }
        OrderInfoSeller orderInfoSeller = (OrderInfoSeller) list.get(0);

        orderInfoSellerInfo.put("orderInfoSellerId", orderInfoSeller.getOrderInfoSellerId());
        orderInfoSellerInfo.put("usableDate", orderInfoSeller.getUsableDate());
        orderInfoSellerInfo.put("orderSn", orderInfoSeller.getOrderSn());
        if (orderInfoSeller.getOrderTypeValue() == EnumOrderSellerType.定制内容.getIndex()) {
            orderInfoSellerInfo.put("name", orderInfoSeller.getSnapshotYrAuthor() == null ? "" : orderInfoSeller.getSnapshotYrAuthor().getAuthorNickname());
        } else if (orderInfoSeller.getOrderTypeValue() == EnumOrderSellerType.营销分发.getIndex()) {
            orderInfoSellerInfo.put("name", orderInfoSeller.getSnapshotAccount() == null ? "" : orderInfoSeller.getSnapshotAccount().getName());
        }
        List<Map<Object, Object>> feeList = new ArrayList<Map<Object, Object>>();
        for (OrderInfoOffer offer : orderInfoSeller.getOrderInfoOfferList()) {
            Map<Object, Object> map = new HashMap<Object, Object>();
                BigDecimal price = offer.getPrice();//应约价
                BigDecimal sellerPrice = offer.getSellerPrice();//卖家报价
                BigDecimal payAble;
                if(orderInfoSeller.getSellerServiceRate()==null){
                    payAble = sellerPrice.add((BigDecimal) list.get(1));
                }else{
                    payAble = sellerPrice.multiply(new BigDecimal(1).add(orderInfoSeller.getSellerServiceRate().multiply(new BigDecimal(0.01))));
                }
                map.put("orderInfoOfferId", offer.getOrderInfoOfferId());
                map.put("priceName", offer.getPriceName());
                map.put("price", price);
                map.put("sellerPrice", sellerPrice);
                map.put("payAble", payAble);
                //map.put("invoicePrice", Math.ceil(invoicePrice.doubleValue()));
            feeList.add(map);
        }
        orderInfoSellerInfo.put("OrderInfoOfferList", feeList);
        orderInfoSellerInfo.put("serviceFee", new BigDecimal(String.valueOf(list.get(1))));
        //orderInfoSellerInfo.put("invoiceRate", new BigDecimal(String.valueOf(list.get(2))));
        return orderInfoSellerInfo;
    }

    /**
     * 封装数据—后台—通过需求Id获取订单列表
     *
     * @param orderInfo
     * @return
     */
    public static JSONObject packageOrderData(OrderInfoSeller orderInfo) {
        JSONObject result = new JSONObject();
        //订单Id
        result.put("orderInfoSellerId", orderInfo.getOrderInfoSellerId());
        //订单号
        result.put("orderSn", orderInfo.getOrderSn());
        //订单状态id
        result.put("orderStatus", orderInfo.getOrderStatusValue());
        if (orderInfo.getOrderStatusValue() != null && orderInfo.getOrderStatusValue().compareTo(EnumOrderSellerStatus.卖家拒约.getIndex()) == 0) {
            result.put("rejectReason", orderInfo.getRejectReason());//拒约原因
            result.put("rejectRemark", orderInfo.getRejectRemark());//拒约备注
        }
        if (orderInfo.getOrderStatusValue() != null && orderInfo.getOrderStatusValue().compareTo(EnumOrderSellerStatus.买家拒绝.getIndex()) == 0) {
            result.put("refuseReason", orderInfo.getRefuseReason());//拒绝原因
        }
        //订单状态
        result.put("orderStatusValue", orderInfo.getOrderStatus().getName());
        //用户简称
        result.put("userNickName", orderInfo.getSaler() == null ? "" : orderInfo.getSaler().getNickName());
        //用户Id
        result.put("userId", orderInfo.getSaler() == null ? "" : orderInfo.getSaler().getRecID());
        //应约回复
        JSONObject orderReply = new JSONObject();
        if (CollectionUtil.size(orderInfo.getOrderInfoOfferList()) > 0) {
            //报价封装
            JSONArray offerList = new JSONArray();
            for (OrderInfoOffer offer : orderInfo.getOrderInfoOfferList()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("price", offer.getPrice());
                jsonObject.put("priceName", offer.getPriceName());
                jsonObject.put("orderInfoOfferId", offer.getOrderInfoOfferId());
                offerList.add(jsonObject);
            }
            orderReply.put("offerList", offerList);
        }
        //可用排期、创作耗时
        orderReply.put("usableDate", orderInfo.getUsableDate());
        //应约备注
        orderReply.put("acceptRemark", orderInfo.getAcceptRemark());
        result.put("orderReply", orderReply);
        //执行结果
        if (StringUtil.isNotBlank(orderInfo.getReturnUrl()) || StringUtil.isNotBlank(orderInfo.getReturnImg())) {
            JSONObject feedback = new JSONObject();
            feedback.put("returnUrl", orderInfo.getReturnUrl());//执行链接
            feedback.put("returnImg", orderInfo.getReturnImg());//执行图片
            result.put("feedback", feedback);
        }
        if (orderInfo.getOrderTypeValue() == EnumOrderSellerType.营销分发.getIndex()) {
            //账号名称
            result.put("accountName", orderInfo.getSnapshotAccount() == null ? "" : orderInfo.getSnapshotAccount().getName());
            //账号头图
            result.put("accountImage", orderInfo.getSnapshotAccount() == null ? "" : orderInfo.getSnapshotAccount().getHeadImageUrlLocal());
            //账号平台路径
            result.put("platformLogo", orderInfo.getSnapshotAccount() == null ? "" : orderInfo.getSnapshotAccount().getPlatformLogo());
            //账号报价有效期
            try {
                result.put("accountInvalidTime", orderInfo.getSnapshotAccount() == null ? "" : orderInfo.getSnapshotAccount().getInvalidTime() == null ? "" : DateUtil.format(DateUtil.parseDate(orderInfo.getSnapshotAccount().getInvalidTime(), "yyyy-MM-dd"), "yyyy-MM-dd"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //账号报价
            result.put("accountPrice", orderInfo.getSnapshotAccount() == null ? "" : JSONObject.parse(orderInfo.getSnapshotAccount().getPriceInfo()));
        } else if (orderInfo.getOrderTypeValue() == EnumOrderSellerType.定制内容.getIndex()) {
            //创作者Id
            result.put("authorId", orderInfo.snapshotYrAuthor == null ? "" : orderInfo.snapshotYrAuthor.getYrAuthorId());
            //创作者名称
            result.put("authorNickname", orderInfo.snapshotYrAuthor == null ? "" : orderInfo.snapshotYrAuthor.getAuthorNickname());
            //创作者头像
            result.put("authorImg", orderInfo.snapshotYrAuthor == null ? "" : orderInfo.snapshotYrAuthor.getAuthorImg());
            //创作者参考价
            result.put("authorPrice", orderInfo.snapshotYrAuthor == null ? "" : orderInfo.snapshotYrAuthor.getPriceInfo());
        }
        //订单金额
        /*if (orderInfo.getOrderStatusValue().compareTo(EnumOrderSellerStatus.待执行.getIndex()) == 0
                || orderInfo.getOrderStatusValue().compareTo(EnumOrderSellerStatus.待确认执行.getIndex()) == 0
                || orderInfo.getOrderStatusValue().compareTo(EnumOrderSellerStatus.已完成.getIndex()) == 0) {//判断状态显示金额
            JSONObject costInfo = new JSONObject();
            costInfo.put("priceInfo", orderInfo.getProduction());
            if (CollectionUtil.size(orderInfo.getOrderCostInfoList()) > 0) {
                for (OrderCostInfo cost : orderInfo.getOrderCostInfoList()) {
                    if(cost.getCostTypeIndex()==2) {
                        switch (cost.getCostId()) {
                            case 240://成本价
                                costInfo.put("costPrice", cost.getMoney() == null ? 0 : cost.getMoney());
                                break;
                            case 241://服务费
                                costInfo.put("servicePrice", cost.getMoney() == null ? 0 : cost.getMoney());
                                break;
                            case 242://发票费
                                costInfo.put("invoicePrice", cost.getMoney() == null ? 0 : cost.getMoney());
                                break;
                        }
                    }
                }
                costInfo.put("servicePrice", new BigDecimal(costInfo.getString("costPrice")).add(new BigDecimal(costInfo.getString("servicePrice"))).setScale(0, BigDecimal.ROUND_UP));
                costInfo.put("invoicePrice", new BigDecimal(costInfo.getString("invoicePrice")).add(new BigDecimal(costInfo.getString("servicePrice"))).setScale(0, BigDecimal.ROUND_UP));
                result.put("costInfo", costInfo);
            }
        }*/
        return result;
    }



    /**
     * 封装到买卖家中心的报名记录
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/9/15 11:15
     */
    public static JSONObject packageToMy(OrderInfoSeller orderInfo, CommonSearchClass commonSearchClass) {
        JSONObject result = new JSONObject();
        //流水号
        result.put("orderSn", orderInfo.getOrderSn());
        //状态
        result.put("orderStatusValue", orderInfo.getOrderStatusValue());
        result.put("orderStatus",orderInfo.getOrderStatus());
        if (orderInfo.getOrderStatusValue() ==EnumOrderSellerStatus.卖家拒约.getIndex() ) {
            result.put("rejectReason", orderInfo.getRejectReason());//拒约原因
        }
        if (orderInfo.getOrderStatusValue()==EnumOrderSellerStatus.买家拒绝.getIndex())  {
            result.put("refuseReason", orderInfo.getRefuseReason()==null||orderInfo.getRefuseReason().equals("")?"买家拒绝":orderInfo.getRefuseReason());//拒绝原因
        }
        //待买家确认状态的价格处理
        if(EnumOrderSellerStatus.待买家确认.getIndex() == orderInfo.getOrderStatusValue().intValue()||
                EnumOrderSellerStatus.买家拒绝.getIndex() == orderInfo.getOrderStatusValue().intValue()){
            if(!orderInfo.getOrderType().equals(EnumOrderSellerType.原创征稿)){
                JSONArray jsonArray = new JSONArray();
                if(CollectionUtil.size(orderInfo.getOrderInfoOfferList())>0){
                    for (OrderInfoOffer orderInfoOffer : orderInfo.getOrderInfoOfferList()
                            ) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("production", orderInfoOffer.getPriceName());
                        //报价
                        BigDecimal price = orderInfoOffer.getExecutePrice();
                        BigDecimal invoicePrice = price.multiply(new BigDecimal(commonSearchClass.getInvoiceAuthor())).divide(new BigDecimal("100"));
                        jsonObject.put("price", price);
                        jsonObject.put("invoicePrice", invoicePrice);
                        jsonArray.add(jsonObject);
                    }
                }
                result.put("priceName", jsonArray);
            }else {
                JSONArray jsonArray1 = new JSONArray();
                //订单金额
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("price", orderInfo.getBuyerOrderPrice());
                BigDecimal invoicePrice = orderInfo.getBuyerOrderPrice().multiply(new BigDecimal(commonSearchClass.getInvoiceCOLLECTION())).divide(new BigDecimal("100")).setScale(2,BigDecimal.ROUND_UP);
                jsonObject1.put("invoicePrice",invoicePrice);
                //订单总计
                jsonObject1.put("totalPrice",orderInfo.getBuyerOrderPrice().add(invoicePrice));
                jsonArray1.add(jsonObject1);
                result.put("priceName", jsonArray1);
            }
        }
        //买家确认状态的价格处理
        if (EnumOrderSellerStatus.已生成订单.getIndex() == orderInfo.getOrderStatusValue().intValue()) {
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("price", orderInfo.getBuyerOrderPrice());
            jsonObject.put("production", orderInfo.getProduction());
            List<OrderCostInfo> costInfoList = orderInfo.getOrderCostInfoList();
            BigDecimal invoice = new BigDecimal("0");
            if (CollectionUtil.size(costInfoList) > 0) {
                for (OrderCostInfo cost : costInfoList) {
                    if (cost != null && cost.getCostId() == 242 && cost.getCostTypeIndex().compareTo(EnumCostType.作品买家订单.getIndex()) == 0) {
                        invoice = cost.getMoney();
                    }
                }
            }
            jsonObject.put("invoicePrice", invoice);
            if(orderInfo.getOrderType().equals(EnumOrderSellerType.原创征稿)){
                jsonObject.put("totalPrice", orderInfo.getOrderInfoBuyer().getTotalMoney());
            }
            jsonArray.add(jsonObject);
            result.put("priceName", jsonArray);
            if(orderInfo.getOrderType().equals(EnumOrderSellerType.原创征稿)){
                result.put("payStatus", orderInfo.getOrderInfoBuyer().getPayStatusValue());
            }
        }
        if (orderInfo.getOrderType().equals(EnumOrderSellerType.定制内容)) {
            result.put("headImageUrlLocal", orderInfo.getyRAuthor().getAuthorImg());
            result.put("name", orderInfo.getyRAuthor().getAuthorNickname());
            result.put("usableDate", orderInfo.getUsableDate());
        }
        if (orderInfo.getOrderType().equals(EnumOrderSellerType.营销分发)) {
            result.put("name", orderInfo.getPlatformIPAccount().getName());
            //平台logo
            result.put("platformLogo", orderInfo.getPlatformIPAccount().getShortVideoPlatformInfo().getIcoUrl());
            //账号头像
            result.put("headImageUrlLocal", orderInfo.getPlatformIPAccount().getHeadImageUrlLocal());
            //账号id
            result.put("accountID", orderInfo.getPlatformIPAccount().getAccountID());
            result.put("fans", orderInfo.getPlatformIPAccount().getFans());
            result.put("usableDate", orderInfo.getUsableDate());
        }
        if (orderInfo.getOrderType().equals(EnumOrderSellerType.原创征稿)) {
            result.put("name", orderInfo.getyRProduction().getTitle());
            result.put("imgNum", orderInfo.getyRProduction().getImgNum());
            result.put("contentSize",orderInfo.getyRProduction().getWordNum());
            result.put("usableDate", orderInfo.getCreatedTime() == null ? "" : DateUtil.format(orderInfo.getCreatedTime(), "yyyy-MM-dd HH:mm:ss"));
            result.put("YRProductionID",orderInfo.getyRProduction().getRecId());
            result.put("yrProductionStatusIndex",orderInfo.getyRProduction().getYrProductionStatusIndex());
            result.put("yrProductionStatus",orderInfo.getyRProduction().getYrProductionStatus());
        }
        return result;
    }
}
