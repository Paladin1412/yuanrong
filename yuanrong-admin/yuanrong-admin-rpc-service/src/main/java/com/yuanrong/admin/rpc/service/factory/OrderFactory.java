package com.yuanrong.admin.rpc.service.factory;

import com.yuanrong.admin.Enum.EnumOrderSellerType;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.rpc.exception.YuanRongException;

/**
 * 订单工厂
 * Created by zhonghang on 2018/09/14.
 */
public class OrderFactory {
    public static OrderModel getOrder(EnumOrderSellerType orderType){
        if(orderType == null){
            throw new YuanRongException("订单类型错误");
        }
        switch (orderType.getIndex()){
            case 1:
                return new ProductionOrder();
            case 2:
                return new AccountOrder();
            case 3:
                return new AuthorOrder();
            case 4:
                return new CollectionProductionOrder();
            default:
                throw new YuanRongException("订单类型错误");

        }
    }
}
