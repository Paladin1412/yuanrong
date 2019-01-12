package com.yuanrong.admin.dao.order;
import com.yuanrong.admin.bean.order.OrderCostInfo;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 买家订单附加费用的dao
 * Created MDA
 */
@Repository
public interface OrderCostInfoDaoI extends BaseDaoI<OrderCostInfo> {

    /**
     *@author songwq
     *@param
     *@date 2018/7/10
     *@description 订单管理--待确认使用--确认使用提交--新增orderCostInfo
     */
    void insertOrderCostInfo(@Param(value="orderCostInfoList") List<OrderCostInfo> orderCostInfoList);

    /**
     *@author songwq
     *@param
     *@date 2018/8/23
     *@description 根据订单ID查询相应的费用
    */
    List<OrderCostInfo> getOrderCostInfoList(@Param(value="orderInfoId")Integer orderInfoId);

    void updateMoneyByOrderInfoId(@Param(value="data")OrderCostInfo orderCostInfo);
}
