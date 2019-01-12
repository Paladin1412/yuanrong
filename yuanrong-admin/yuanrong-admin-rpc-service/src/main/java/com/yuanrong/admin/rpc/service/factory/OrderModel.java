package com.yuanrong.admin.rpc.service.factory;

import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.dao.account.PlatformIPAccountDaoI;
import com.yuanrong.admin.dao.account.PlatformIPAccountPriceDaoI;
import com.yuanrong.admin.dao.author.YRAuthorDaoI;
import com.yuanrong.admin.dao.author.YRProductionDaoI;
import com.yuanrong.admin.dao.base.DictInfoDaoI;
import com.yuanrong.admin.dao.config.ConfigurationDaoI;
import com.yuanrong.admin.dao.order.*;
import com.yuanrong.admin.rpc.api.order.OrderSnFactoryServicesI;
import com.yuanrong.admin.rpc.api.quartz.QuartzManagerI;

/**
 * Created by zhonghang on 2018/09/14.
 */
public abstract class OrderModel {
    public OrderSnFactoryServicesI orderSnFactoryServicesI;
    public OrderInfoBuyerDaoI orderInfoBuyerDaoI;
    public Object data;
    public Integer registerUserInfoId;
    public ConfigurationDaoI configurationDaoI;
    public DictInfoDaoI dictInfoDaoI;
    public OrderCostInfoDaoI orderCostInfoDaoI;
    public OrderDetailDaoI orderDetailDaoI;
    public SnapshotYrProductionDaoI snapshotYrProductionDaoI;
    public SellerOrderDaoI sellerOrderDaoI;
    public SnapshotYrAuthorDaoI snapshotYrAuthorDaoI;
    public SnapshotAccountDaoI snapshotAccountDaoI;
    public QuartzManagerI quartzManagerI;
    public YRProductionDaoI yRProductionDaoI;
    public YRAuthorDaoI yrAuthorDaoI;
    public PlatformIPAccountDaoI platformIPAccountDaoI;
    public PlatformIPAccountPriceDaoI platformIPAccountPriceDaoI;

    public void init(OrderSnFactoryServicesI orderSnFactoryServicesI, OrderInfoBuyerDaoI orderInfoBuyerDaoI ,Object data,Integer registerUserInfoId
     ,ConfigurationDaoI configurationDaoI,DictInfoDaoI dictInfoDaoI,OrderCostInfoDaoI orderCostInfoDaoI,OrderDetailDaoI orderDetailDaoI
             ,SnapshotYrProductionDaoI snapshotYrProductionDaoI,SellerOrderDaoI sellerOrderDaoI,SnapshotYrAuthorDaoI snapshotYrAuthorDaoI,
                     SnapshotAccountDaoI snapshotAccountDaoI,QuartzManagerI quartzManagerI,YRProductionDaoI yRProductionDaoI,
                     YRAuthorDaoI yrAuthorDaoI,PlatformIPAccountDaoI platformIPAccountDaoI ,PlatformIPAccountPriceDaoI platformIPAccountPriceDaoI) {
        this.orderSnFactoryServicesI = orderSnFactoryServicesI;
        this.orderInfoBuyerDaoI = orderInfoBuyerDaoI;
        this.data = data;
        this.registerUserInfoId = registerUserInfoId;
        this.configurationDaoI = configurationDaoI;
        this.dictInfoDaoI = dictInfoDaoI;
        this.orderCostInfoDaoI = orderCostInfoDaoI;
        this.orderDetailDaoI = orderDetailDaoI;
        this.snapshotYrProductionDaoI = snapshotYrProductionDaoI;
        this.sellerOrderDaoI = sellerOrderDaoI;
        this.snapshotAccountDaoI = snapshotAccountDaoI;
        this.snapshotYrAuthorDaoI = snapshotYrAuthorDaoI;
        this.quartzManagerI = quartzManagerI;
        this.yRProductionDaoI = yRProductionDaoI;
        this.yrAuthorDaoI = yrAuthorDaoI;
        this.platformIPAccountDaoI = platformIPAccountDaoI;
        this.platformIPAccountPriceDaoI = platformIPAccountPriceDaoI;
    }

    /**
     * 生成订单
     * @return
     */
    public abstract OrderInfoBuyer createOrder();
}
