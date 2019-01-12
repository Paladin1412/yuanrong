package com.yuanrong.admin.rpc.api.order;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.order.OrderCostInfo;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.util.BaseModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 买家订单附加费用的services接口
 * Created MDA
 */
public interface OrderCostInfoServicesI extends BaseServicesI<OrderCostInfo> {
    /**
     *@author songwq
     *@param
     *@date 2018/8/23
     *@description 根据订单ID查询相应的费用
     */
    List<OrderCostInfo> getOrderCostInfoList(Integer orderInfoId);
}
