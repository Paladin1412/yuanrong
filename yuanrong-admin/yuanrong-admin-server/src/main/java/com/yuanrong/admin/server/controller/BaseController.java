package com.yuanrong.admin.server.controller;

import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.rpc.api.account.IPServicesI;
import com.yuanrong.admin.rpc.api.account.PlatformIPAccountPriceNameRelationServicesI;
import com.yuanrong.admin.rpc.api.account.PlatformIPAccountPriceServicesI;
import com.yuanrong.admin.rpc.api.account.PlatformIPAccountServicesI;
import com.yuanrong.admin.rpc.api.author.YRAuthorServicesI;
import com.yuanrong.admin.rpc.api.author.YRProductionServicesI;
import com.yuanrong.admin.rpc.api.base.SystemLogServicesI;
import com.yuanrong.admin.rpc.api.base.*;
import com.yuanrong.admin.rpc.api.bug.BugInfoServicesI;
import com.yuanrong.admin.rpc.api.cart.ShoppingCartServicesI;
import com.yuanrong.admin.rpc.api.config.ConfigurationServicesI;
import com.yuanrong.admin.rpc.api.data.DataProcessServicesI;
import com.yuanrong.admin.rpc.api.demand.DemandFastServicesI;
import com.yuanrong.admin.rpc.api.demand.DemandProductionServicesI;
import com.yuanrong.admin.rpc.api.demand.DemandServicesI;
import com.yuanrong.admin.rpc.api.fiance.UserBalanceDetailsServicesI;
import com.yuanrong.admin.rpc.api.mq.RabbitMQServicesI;
import com.yuanrong.admin.rpc.api.notice.WeChatServicesI;
import com.yuanrong.admin.rpc.api.order.OrderCostInfoServicesI;
import com.yuanrong.admin.rpc.api.order.OrderInfoOfferServicesI;
import com.yuanrong.admin.rpc.api.order.OrderInfoSellerServicesI;
import com.yuanrong.admin.rpc.api.order.SnapshotYrAuthorServicesI;
import com.yuanrong.admin.rpc.api.order.OrderInfoBuyerServicesI;
import com.yuanrong.admin.rpc.api.order.*;
import com.yuanrong.admin.rpc.api.pay.PayServiceI;
import com.yuanrong.admin.rpc.api.pay.PayWaterServicesI;
import com.yuanrong.admin.rpc.api.rank.ListArticleServicesI;
import com.yuanrong.admin.rpc.api.rank.ListIPCreativityServicesI;
import com.yuanrong.admin.rpc.api.resources.UserImagesServicesI;
import com.yuanrong.admin.rpc.api.system.*;
import com.yuanrong.admin.rpc.api.system.AdminUserServicesI;
import com.yuanrong.admin.rpc.api.system.SystemMenuServicesI;
import com.yuanrong.admin.rpc.api.system.SystemRoleServicesI;
import com.yuanrong.admin.rpc.api.system.SystemUserServicesI;
import com.yuanrong.admin.rpc.api.trading.TradingRecordServicesI;
import com.yuanrong.admin.rpc.api.upload.UploadServicesI;
import com.yuanrong.admin.rpc.api.usermanagement.RegisteredUserInfoServiceI;
import com.yuanrong.common.AllController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhonghang on 2018/4/11.
 */
public class BaseController extends AllController{
    private static final Logger logger = Logger.getLogger(BaseController.class);

    @Autowired
    protected SystemUserServicesI systemUserServicesI;

    @Autowired
    protected SystemMenuServicesI systemMenuServicesI;

    @Autowired
    protected SystemRoleServicesI systemRoleServicesI;

    @Autowired
    protected RegisteredUserInfoServiceI registeredUserInfoService;
    @Autowired
    protected PlatformIPAccountServicesI platformIPAccountServicesI;

    @Autowired
    protected IPServicesI ipServicesI;
    @Autowired
    protected AdminUserServicesI adminUserServicesI;

    @Autowired
    public PlatformIPAccountPriceServicesI platformIPAccountPriceServicesI;

    @Autowired
    public PlatformIPAccountPriceNameRelationServicesI platformIPAccountPriceNameRelationServicesI;

    @Autowired
    public DictInfoServicesI dictInfoServicesI;

    @Autowired
    public AreaCityServicesI areaCityServicesI;

    @Autowired
    public YRAuthorServicesI yRAuthorServicesI;

    @Autowired
    public ContentFormServicesI contentFormServicesI;

    @Autowired
    public IPLableServicesI iPLableServicesI;

    @Autowired
    public LableServicesI lableServicesI;

    @Autowired
    public ScenesServicesI scenesServicesI;
    @Autowired
    public SystemLogServicesI systemLogServicesI;
    @Autowired
    public YRProductionServicesI yRProductionServicesI;

    @Autowired
    public BugInfoServicesI bugInfoServicesI;

    @Autowired
    public UserImagesServicesI userImagesServicesI;
    //购物车
    @Autowired
    public ShoppingCartServicesI shoppingCartServicesI;

    @Autowired
    public OrderInfoBuyerServicesI orderInfoBuyerServicesI;
    /*基本需求信息*/
    @Autowired
    public DemandServicesI demandServicesI;

    /*作者快照*/
    @Autowired
    public SnapshotYrAuthorServicesI snapshotYrAuthorServicesI;
    /*快速需求*/
    @Autowired
    public DemandFastServicesI demandFastServicesI;

    //买家订单附加费用
    @Autowired
    public OrderCostInfoServicesI orderCostInfoServicesI;

    //卖家订单报价
    @Autowired
    public OrderInfoOfferServicesI orderInfoOfferServicesI;
    //系统配置
    @Autowired
    public ConfigurationServicesI configurationServicesI;

    //卖家订单
    @Autowired
    public OrderInfoSellerServicesI orderInfoSellerServicesI;

    //作品快照
    @Autowired
    public SnapshotYrProductionServicesI snapshotYrProductionServicesI;

    //账号快照
    @Autowired
    public SnapshotAccountServicesI snapshotAccountServicesI;

    //上传文件
    @Autowired
    public UploadServicesI uploadServicesI;
    //支付
    @Autowired
    public PayServiceI payServiceI;

    //支付流水
    @Autowired
    public PayWaterServicesI payWaterServicesI;

    @Autowired
    public AdminRoleServicesI adminRoleServicesI;

    @Autowired
    public ListIPCreativityServicesI listIPCreativityServicesI;

    @Autowired
    public ListArticleServicesI listArticleServicesI;

    @Autowired
    public SmsRecordServicesI smsRecordServicesI;

    //消息队列
    @Autowired
    public RabbitMQServicesI rabbitMQServicesI;
    @Autowired
    public WeChatServicesI wechatServicesI;

    //交易记录
    @Autowired
    public TradingRecordServicesI tradingRecordServicesI;
    //用户余额流水
    @Autowired
    public UserBalanceDetailsServicesI userBalanceDetailsServicesI;

    //作品报名
    @Autowired
    public DemandProductionServicesI demandProductionServicesI;

    //订单明细
    @Autowired
    public OrderDetailServicesI orderDetailServicesI;

    //卖家订单
    @Autowired
    public SellerOrderServicesI sellerOrderServicesI;
    //登录日志
    @Autowired
    public LoginDetailServicesI loginDetailServicesI;

    //历史数据处理
    @Autowired
    public DataProcessServicesI dataProcessServicesI;

    /**
     * 获得当前登录的用户信息
     *
     * @return
     */
    public AdminUser getUser() {
        AdminUser systemUser = (AdminUser) getSession().getAttribute("admin");
        return systemUser;
    }

    //订单号生成
    @Autowired
    public OrderSnFactoryServicesI orderSnFactoryServicesI;



}
