package com.yuanrong.admin.rpc.service.impl.order;

import com.yuanrong.admin.rpc.api.order.OrderSnFactoryServicesI;
import com.yuanrong.common.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhonghang on 2018/7/3.
 */
@Service
public class OrderSnFactoryServicesImpl implements OrderSnFactoryServicesI {
    //订单号生成
    @Autowired
    public IdWorker idWorker;

    /**
     * 订单号
     */
    @Override
    public String createBuyerOrderSn() {
        return "1"+idWorker.nextId();
    }
    /**
     * 报名单号
     */
    @Override
    public String createSellerOrderSn() {
        return "8"+idWorker.nextId();
    }
    /**
     * 需求单号
     */
    @Override
    public String createDemandOrderSn() {
        return "4"+idWorker.nextId();
    }

    @Override
    public String createdPayOrderSn() {
        return idWorker.nextId()+"";
    }
}
