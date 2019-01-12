package com.yuanrong.admin.bean.order;

import java.util.*;
import java.io.Serializable;
import java.math.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.util.EnumUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.StringUtil;

/**
 * 买家订单的实体类
 *
 * @author MDA
 */
public class OrderInfoBuyer extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增类型
     */
    private Integer orderInfoBuyerId;
    /*****自定义属性区域begin******/

    /**
     * 订单号
     * 订单号
     */
    private String orderSn;

    /**
     * 订单总金额
     * 订单总金额
     */
    private BigDecimal totalMoney;

    /**
     * 订单应收金额
     * 订单应收金额
     */
    private BigDecimal receivableMoney;

    /**
     * 订单实收金额
     * 订单实收金额
     */
    private BigDecimal amountCollected;

    /**
     * 订单状态
     * 订单状态
     */
    private EnumOrderBuyerStatus orderStatus;
    /*
     * 用于接收前台数据
     */
    private Integer orderStatusValue;

    /**
     * 支付时间
     * 支付时间
     */
    private String payTime;

    /**
     * 支付状态
     * 支付状态
     */
    private EnumPayStatus payStatus;
    /*
     * 用于接收前台数据
     */
    private Integer payStatusValue;

    /**
     * 买家注册用户
     * 买家注册用户
     */
    private Integer registeredUserInfoId;

    private RegisteredUserInfo registeredUserInfo;

    /**
     * 取消原因- 作品
     * 取消原因- 作品
     */
    private String cancelReason;

    /**
     * 附加费用名称
     * 附加费用名称
     */
    private DictInfo dictFee;

    /**
     * 支付有效期
     * 支付有效期
     */
    private String payInvalidTime;

    /**
     * 报名记录
     * @return
     */
    private OrderInfoSeller  orderInfoSeller ;
    /**
     * 卖家作品集合
     * @return
     */
    private List<OrderInfoSeller>  orderInfoSellerList ;

    /**
     * 卖家作品
     * @return
     */
    private SnapshotYrProduction  snapshotYrProduction ;

    /**
     * 费用明细
     * @return
     */
    private OrderCostInfo  orderCostInfo ;
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
     * 订单费用拼接
     */
    private String moneyDetail;

    /**
     * 订单类型(1.作品订单；2. 营销分发(账号)；3. 定制内容(创作者),4、原创征稿)
     */
    private Integer orderInfoType;
    private EnumOrderSellerType enumOrderSellerType;

    /**
     * 买家服务费率
     */
    private BigDecimal  buyerServiceRate;
    /**
     * 关联产生订单对象(报名信息)
     */
    private Integer refreId;
    /**
     * 作品详情表
     */
    private OrderDetail orderDetail;
    /**
     * 支付方式
     */
    private EnumPayType payType;
    private Integer payTypeIndex;

    private SnapshotAccount snapshotAccount;
    //作者快照
    private SnapshotYrAuthor snapshotYrAuthor;
    //销售经理
    private Integer saleUserId;
    /**
     * 卖家订单
     */
    private SellerOrder sellerOrder;
    /**
     * 费用明细列表
     */
    private List<OrderCostInfo>  orderCostInfoList ;
    /**
     * 发票税率
     */
    private BigDecimal invoiceRate;

    private String [] orderSns;
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

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getOrderInfoBuyerId() {
        return this.orderInfoBuyerId;
    }

    public void setOrderInfoBuyerId(Integer orderInfoBuyerId) {
        this.orderInfoBuyerId = orderInfoBuyerId;
    }

    /*****自定义属性区域begin.get/set******/

    public String getOrderSn() {
        return this.orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public BigDecimal getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getReceivableMoney() {
        return this.receivableMoney;
    }

    public void setReceivableMoney(BigDecimal receivableMoney) {
        this.receivableMoney = receivableMoney;
    }

    public BigDecimal getAmountCollected() {
        return this.amountCollected;
    }

    public void setAmountCollected(BigDecimal amountCollected) {
        this.amountCollected = amountCollected;
    }

    public EnumOrderBuyerStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(EnumOrderBuyerStatus orderStatus) {
        this.orderStatus = orderStatus;
        this.orderStatusValue = orderStatus.getIndex();
    }


    public Integer getOrderStatusValue() {
        return orderStatusValue;
    }

    public void setOrderStatusValue(Integer orderStatusValue) {
        this.orderStatusValue = orderStatusValue;
        this.orderStatus = (EnumOrderBuyerStatus) EnumUtil.valueOf(EnumOrderBuyerStatus.class, orderStatusValue);
    }

    public String getPayTime() {
        return this.payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public EnumPayStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(EnumPayStatus payStatus) {
        this.payStatus = payStatus;
        this.payStatusValue = payStatus.getIndex();
    }

    public Integer getPayStatusValue() {
        return payStatusValue;
    }
    public void setPayStatusValue(Integer payStatusValue) {
        this.payStatusValue = payStatusValue;
        this.payStatus = (EnumPayStatus) EnumUtil.valueOf(EnumPayStatus.class, payStatusValue);
    }

    public String getCancelReason() {
        return this.cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getPayInvalidTime() {
        return this.payInvalidTime;
    }

    public void setPayInvalidTime(String payInvalidTime) {
        this.payInvalidTime = payInvalidTime;
    }


    public OrderInfoSeller getOrderInfoSeller() {
        return orderInfoSeller;
    }

    public void setOrderInfoSeller(OrderInfoSeller orderInfoSeller) {
        this.orderInfoSeller = orderInfoSeller;
    }

    public SnapshotYrProduction getSnapshotYrProduction() {
        return snapshotYrProduction;
    }

    public void setSnapshotYrProduction(SnapshotYrProduction snapshotYrProduction) {
        this.snapshotYrProduction = snapshotYrProduction;
    }

    public OrderCostInfo getOrderCostInfo() {
        return orderCostInfo;
    }

    public void setOrderCostInfo(OrderCostInfo orderCostInfo) {
        this.orderCostInfo = orderCostInfo;
    }

    public Integer getRegisteredUserInfoId() {
        return registeredUserInfoId;
    }

    public void setRegisteredUserInfoId(Integer registeredUserInfoId) {
        this.registeredUserInfoId = registeredUserInfoId;
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

    public DictInfo getDictFee() {
        return dictFee;
    }

    public void setDictFee(DictInfo dictFee) {
        this.dictFee = dictFee;
    }

    public List<OrderInfoSeller> getOrderInfoSellerList() {
        return orderInfoSellerList;
    }

    public void setOrderInfoSellerList(List<OrderInfoSeller> orderInfoSellerList) {
        this.orderInfoSellerList = orderInfoSellerList;
    }

    public String getMoneyDetail() {
        return moneyDetail;
    }

    public void setMoneyDetail(String moneyDetail) {
        this.moneyDetail = moneyDetail;
    }

    public Integer getOrderInfoType() {
        return orderInfoType;
    }

    public void setOrderInfoType(Integer orderInfoType) {
        this.orderInfoType = orderInfoType;
        this.enumOrderSellerType = (EnumOrderSellerType) EnumUtil.valueOf(EnumOrderSellerType.class, orderInfoType);
    }

    public EnumOrderSellerType getEnumOrderSellerType() {
        return enumOrderSellerType;
    }

    public void setEnumOrderSellerType(EnumOrderSellerType enumOrderSellerType) {
        this.enumOrderSellerType = enumOrderSellerType;
        this.orderInfoType = enumOrderSellerType.getIndex();
    }

    public BigDecimal getBuyerServiceRate() {
        return buyerServiceRate;
    }

    public void setBuyerServiceRate(BigDecimal buyerServiceRate) {
        this.buyerServiceRate = buyerServiceRate;
    }

    public Integer getRefreId() {
        return refreId;
    }

    public void setRefreId(Integer refreId) {
        this.refreId = refreId;
    }

    public EnumPayType getPayType() {
        return payType;
    }

    public void setPayType(EnumPayType payType) {
        this.payType = payType;
        this.payTypeIndex = payType.getIndex();
    }

    public Integer getPayTypeIndex() {
        return payTypeIndex;
    }

    public void setPayTypeIndex(Integer payTypeIndex) {
        this.payTypeIndex = payTypeIndex;
        this.payType = (EnumPayType) EnumUtil.valueOf(EnumPayType.class , payTypeIndex);
    }

    public Integer getSaleUserId() {
        return saleUserId;
    }

    public void setSaleUserId(Integer saleUserId) {
        this.saleUserId = saleUserId;
    }

    public SellerOrder getSellerOrder() {
        return sellerOrder;
    }

    public void setSellerOrder(SellerOrder sellerOrder) {
        this.sellerOrder = sellerOrder;
    }

    public List<OrderCostInfo> getOrderCostInfoList() {
        return orderCostInfoList;
    }

    public void setOrderCostInfoList(List<OrderCostInfo> orderCostInfoList) {
        this.orderCostInfoList = orderCostInfoList;
    }

    public String[] getOrderSns() {
        return orderSns;
    }

    public void setOrderSns(String[] orderSns) {
        this.orderSns = orderSns;
    }

    public BigDecimal getInvoiceRate() {
        return invoiceRate;
    }

    public void setInvoiceRate(BigDecimal invoiceRate) {
        this.invoiceRate = invoiceRate;
    }

    /**
     * 后台—作品订单信息封装
     * @param buyerOrderInfo
     * @return
     */
    public static JSONObject packageOrderBuyerInfo(OrderInfoBuyer buyerOrderInfo) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderSn",buyerOrderInfo.getOrderSn());
        jsonObject.put("userNickName",buyerOrderInfo.getRegisteredUserInfo() != null ? buyerOrderInfo.getRegisteredUserInfo().getNickName() : "");
        jsonObject.put("userId",buyerOrderInfo.getRegisteredUserInfoId());
        jsonObject.put("createUser",buyerOrderInfo.getCreateUser());
        jsonObject.put("payStatusValue",buyerOrderInfo.getPayStatusValue());
        jsonObject.put("payStatus",buyerOrderInfo.getPayStatusValue() == null? "" : EnumUtil.valueOf(EnumPayStatus.class,buyerOrderInfo.getPayStatusValue()));
        return jsonObject;
    }
    /**
     * 买卖家中心—作品订单信息封装
     * @param buyerOrderInfo
     * @return
     */
    public static JSONObject packageToMy(OrderInfoBuyer buyerOrderInfo) {
        JSONObject result = new JSONObject();
        //订单Id
        result.put("orderInfoSellerId", buyerOrderInfo.getOrderInfoBuyerId());
        //订单号
        result.put("orderSn",buyerOrderInfo.getOrderSn());
        //订单状态id
        result.put("orderStatus", buyerOrderInfo.getOrderStatus());
        //订单状态
        result.put("orderStatusValue", buyerOrderInfo.getOrderStatusValue());
        result.put("payStatusValue", buyerOrderInfo.getPayStatusValue());
        result.put("payStatus",buyerOrderInfo.getPayStatus());
        result.put("orderTypeValue",buyerOrderInfo.getOrderInfoType());
        result.put("orderType",buyerOrderInfo.getEnumOrderSellerType().getName());
        result.put("returnUrl", buyerOrderInfo.getOrderInfoSeller()==null?null:buyerOrderInfo.getOrderInfoSeller().getReturnUrl());//执行链接
        result.put("returnImg", buyerOrderInfo.getOrderInfoSeller()==null?null:buyerOrderInfo.getOrderInfoSeller().getReturnImg());//执行图片
        result.put("executeOver", buyerOrderInfo.getCancelReason());
        result.put("payTime", buyerOrderInfo.getPayTime());
        result.put("orderCreateTime", DateUtil.format(buyerOrderInfo.getCreatedTime(), "yyyy-MM-dd HH:mm:ss"));
        if (buyerOrderInfo.getOrderInfoType() == EnumOrderSellerType.营销分发.getIndex()) {
            //账号名称
            result.put("name", buyerOrderInfo.getSnapshotAccount() == null ? "" : buyerOrderInfo.getSnapshotAccount().getName());
            //账号头图
            result.put("headImageUrlLocal", buyerOrderInfo.getSnapshotAccount() == null ? "" : buyerOrderInfo.getSnapshotAccount().getHeadImageUrlLocal());
            //账号id
            result.put("accountID", buyerOrderInfo.getSnapshotAccount() == null ? "" : buyerOrderInfo.getSnapshotAccount().getAccountID());
            //账号平台路径
            result.put("platformLogo", buyerOrderInfo.getSnapshotAccount() == null ? "" : buyerOrderInfo.getSnapshotAccount().getPlatformLogo());
            //执行时间
            result.put("executeTime", buyerOrderInfo.getOrderInfoSeller().getExecuteTime());
            //确认时间
            result.put("confirmTime", buyerOrderInfo.getOrderInfoSeller().getConfirmTime());

        } else if (buyerOrderInfo.getOrderInfoType() == EnumOrderSellerType.定制内容.getIndex()) {
            //创作者名称
            result.put("name", buyerOrderInfo.snapshotYrAuthor == null ? "" : buyerOrderInfo.snapshotYrAuthor.getAuthorNickname());
            //创作者头像
            result.put("headImageUrlLocal", buyerOrderInfo.snapshotYrAuthor == null ? "" : buyerOrderInfo.snapshotYrAuthor.getAuthorImg());
            //完成时间
            result.put("executeTime", buyerOrderInfo.getOrderInfoSeller().getExecuteTime());

        }else if (buyerOrderInfo.getOrderInfoType() == EnumOrderSellerType.原创征稿.getIndex()||buyerOrderInfo.getOrderInfoType() == EnumOrderSellerType.作品订单.getIndex()) {
            //创作者Id
            result.put("name", buyerOrderInfo.snapshotYrProduction == null ? "" : buyerOrderInfo.snapshotYrProduction.getTitle());
            //图片数
            result.put("imgNum", buyerOrderInfo.snapshotYrProduction == null ? "" : buyerOrderInfo.snapshotYrProduction.getImgNum());
            //字数
            result.put("contentSize", buyerOrderInfo.snapshotYrProduction == null ? "" : buyerOrderInfo.snapshotYrProduction.getWordNum());
            //提交时间
            if(buyerOrderInfo.getOrderInfoType() == EnumOrderSellerType.原创征稿.getIndex()){
                result.put("executeTime", buyerOrderInfo.getOrderInfoSeller()==null?null:(buyerOrderInfo.getOrderInfoSeller().getCreatedTime() == null ? "" : DateUtil.format(buyerOrderInfo.getOrderInfoSeller().getCreatedTime(), "yyyy-MM-dd HH:mm:ss")));
            }else {
                result.put("executeTime", buyerOrderInfo.getOrderInfoSeller()==null?null:(buyerOrderInfo.getOrderInfoSeller().getExecuteTime() == null ? "" : DateUtil.format(buyerOrderInfo.getOrderInfoSeller().getCreatedTime(), "yyyy-MM-dd HH:mm:ss")));
            }
            //作品id
            result.put("YRProductionID",buyerOrderInfo.snapshotYrProduction == null ? "" : buyerOrderInfo.snapshotYrProduction.getYrProductionId());
        }
        //重新购买
        if(!(buyerOrderInfo.getOrderInfoType() == EnumOrderSellerType.作品订单.getIndex())){
            result.put("orderInfoSellerSn", buyerOrderInfo.getOrderInfoSeller().getOrderSn());
        }
        //价格项
        result.put("production",buyerOrderInfo.getOrderInfoSeller()==null?null:buyerOrderInfo.getOrderInfoSeller().getProduction());
        //订单金额
        result.put("price", buyerOrderInfo.getOrderDetail()==null?null:buyerOrderInfo.getOrderDetail().getPrice());
        //result.put("invoicePrice", buyerOrderInfo.);
        List<OrderCostInfo> costInfoList = buyerOrderInfo.getOrderCostInfoList();
        BigDecimal invoice = new BigDecimal("0");
        if(CollectionUtil.size(costInfoList) >0){
            for (OrderCostInfo cost : costInfoList){
                if(cost != null && cost.getCostId() == 242 && cost.getCostTypeIndex().compareTo(EnumCostType.作品买家订单.getIndex()) == 0){
                    invoice = cost.getMoney();
                }
            }
        }
        result.put("invoicePrice",invoice);
        //订单总计
        result.put("totalPrice",buyerOrderInfo.getReceivableMoney());
        return result;
    }
}
