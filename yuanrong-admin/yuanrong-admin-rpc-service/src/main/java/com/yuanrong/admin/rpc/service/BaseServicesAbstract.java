package com.yuanrong.admin.rpc.service;

import com.yuanrong.admin.dao.account.IPDaoI;
import com.yuanrong.admin.dao.account.PlatformIPAccountDaoI;
import com.yuanrong.admin.dao.account.PlatformIPAccountPriceDaoI;
import com.yuanrong.admin.dao.account.PlatformIPAccountPriceNameRelationDaoI;
import com.yuanrong.admin.dao.author.YRProductionDaoI;
import com.yuanrong.admin.dao.base.AreaCityDaoI;
import com.yuanrong.admin.dao.base.DictInfoDaoI;
import com.yuanrong.admin.dao.base.SystemLogDaoI;
import com.yuanrong.admin.dao.author.YRAuthorDaoI;
import com.yuanrong.admin.dao.base.*;
import com.yuanrong.admin.dao.bug.BugInfoDaoI;
import com.yuanrong.admin.dao.cart.ShoppingCartDaoI;
import com.yuanrong.admin.dao.config.ConfigurationDaoI;
import com.yuanrong.admin.dao.data.DataProcessDaoI;
import com.yuanrong.admin.dao.demand.AdvertiserReleaseRequirementDaoI;
import com.yuanrong.admin.dao.demand.DemandFastDaoI;
import com.yuanrong.admin.dao.demand.DemandProductionDaoI;
import com.yuanrong.admin.dao.fiance.UserBalanceDetailsDaoI;
import com.yuanrong.admin.dao.order.*;
import com.yuanrong.admin.dao.demand.DemandDaoI;
import com.yuanrong.admin.dao.order.OrderInfoSellerDaoI;
import com.yuanrong.admin.dao.order.OrderInfoOfferDaoI;
import com.yuanrong.admin.dao.order.SnapshotYrAuthorDaoI;
import com.yuanrong.admin.dao.pay.PayWaterDaoI;
import com.yuanrong.admin.dao.rank.ListArticleDaoI;
import com.yuanrong.admin.dao.rank.ListIPCreativityDaoI;
import com.yuanrong.admin.dao.resources.UserImagesDaoI;
import com.yuanrong.admin.dao.system.*;
import com.yuanrong.admin.dao.trading.TradingRecordDaoI;
import com.yuanrong.admin.dao.usermanagement.RegisteredUserExtCompanyDAOI;
import com.yuanrong.admin.dao.usermanagement.RegisteredUserInfoDAOI;
import com.yuanrong.admin.dao.system.SystemRoleDaoI;
import com.yuanrong.admin.dao.system.SystemUserDaoI;
import com.yuanrong.admin.rpc.api.notice.WeChatServicesI;
import com.yuanrong.admin.rpc.api.order.OrderSnFactoryServicesI;
import com.yuanrong.admin.rpc.api.quartz.QuartzManagerI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhonghang on 2018/4/11.
 */
@Repository
public abstract class BaseServicesAbstract<T> {
    Logger logger = Logger.getLogger(BaseServicesAbstract.class);

    @Autowired
    public AdminUserDaoI adminUserDaoI;

    @Autowired
    public SystemUserDaoI systemUserDaoI;

    @Autowired
    public SystemRoleDaoI systemRoleDaoI;

    @Autowired
    public SystemMenuDaoI systemMenuDaoI;
    @Autowired
    public RegisteredUserInfoDAOI registeredUserInfoDAO;

    @Autowired
    public IPDaoI ipDaoI;
    @Autowired
    public YRProductionDaoI yRProductionDaoI;

    @Autowired
    public PlatformIPAccountDaoI platformIPAccountDaoI;

    @Autowired
    public PlatformIPAccountPriceDaoI platformIPAccountPriceDaoI;

    @Autowired
    public PlatformIPAccountPriceNameRelationDaoI platformIPAccountPriceNameDaoI;

    @Autowired
    public DictInfoDaoI dictInfoDaoI;

    @Autowired
    public AreaCityDaoI areaCityDaoI;

    @Autowired
    public RegisteredUserExtCompanyDAOI registeredUserExtCompanyDAOI;

    @Autowired
    public YRAuthorDaoI yRAuthorDaoI;

    @Autowired
    public ContentFormDaoI contentFormDaoI;

    @Autowired
    public IPLableDaoI iPLableDaoI;

    @Autowired
    public LableDaoI lableDaoI;

    @Autowired
    public ScenesDaoI scenesDaoI;

    @Autowired
    public SystemLogDaoI systemLogDaoI;

    @Autowired
    public AdvertiserReleaseRequirementDaoI advertiserReleaseRequirementDaoI;

    @Autowired
    public ListIPCreativityDaoI listIPCreativityDaoI;

    @Autowired
    public ListArticleDaoI listArticleDaoI;

    @Autowired
    public BugInfoDaoI bugInfoDaoI;

    @Autowired
    public UserImagesDaoI userImagesDaoI;
    //购物车
    @Autowired
    public ShoppingCartDaoI shoppingCartDaoI;

    @Autowired
    public OrderInfoBuyerDaoI orderInfoBuyerDaoI;
    /*基本需求信息*/
    @Autowired
    public DemandDaoI demandDaoI;

    /*作者快照*/
    @Autowired
    public SnapshotYrAuthorDaoI snapshotYrAuthorDaoI;
    //买家订单附加费用
    @Autowired
    public OrderCostInfoDaoI orderCostInfoDaoI;

    //卖家订单报价
    @Autowired
    public OrderInfoOfferDaoI orderInfoOfferDaoI;
    /*快速需求*/
    @Autowired
    public DemandFastDaoI demandFastDaoI;

    @Autowired
    public AdminRoleDaoI adminRoleDaoI;
    //系统配置
    @Autowired
    public ConfigurationDaoI configurationDaoI;

    @Autowired
    public SmsRecordDaoI smsRecordDaoI;
    //卖家订单
    @Autowired
    public OrderInfoSellerDaoI orderInfoSellerDaoI;

    //作品快照
    @Autowired
    public SnapshotYrProductionDaoI snapshotYrProductionDaoI;
    //账号快照
    @Autowired
    public SnapshotAccountDaoI snapshotAccountDaoI;

    //订单号生成
    @Autowired
    public OrderSnFactoryServicesI orderSnFactoryServicesI;

    //支付流水
    @Autowired
    public PayWaterDaoI payWaterDaoI;

    //定时任务
    @Autowired
    public QuartzManagerI quartzManagerI;

    //发送微信消息
    @Autowired
    public WeChatServicesI wechatServicesI;

    //交易记录
    @Autowired
    public TradingRecordDaoI tradingRecordDaoI;
    //用户余额流水
    @Autowired
    public UserBalanceDetailsDaoI userBalanceDetailsDaoI;

    //作品表名
    @Autowired
    public DemandProductionDaoI demandProductionDaoI;

    //订单明细
    @Autowired
    public OrderDetailDaoI orderDetailDaoI;

    //卖家订单
    @Autowired
    public SellerOrderDaoI sellerOrderDaoI;

    //登录日志
    @Autowired
    public LoginDetailDaoI loginDetailDaoI;

    @Autowired
    public DataProcessDaoI dataProcessDaoI;

    @Autowired
    public RedisTemplate redisTemplate;
    //redis自增
    public Long incr(String key, long liveTime){
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {//初始设置过期时间
            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
        }
        return increment;
    }

    public Long incr(String key){
        return incr(key,-1);
    }

    public Long getValue(String key){
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        return entityIdCounter.get();
    }
}
