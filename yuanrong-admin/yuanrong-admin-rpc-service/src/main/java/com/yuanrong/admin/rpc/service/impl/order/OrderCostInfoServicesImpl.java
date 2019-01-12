package com.yuanrong.admin.rpc.service.impl.order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.order.OrderCostInfo;
import com.yuanrong.admin.rpc.api.order.OrderCostInfoServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * 买家订单附加费用的services实现类
 * Created MDA
 */
@Service
public class OrderCostInfoServicesImpl extends BaseServicesAbstract<OrderCostInfo> implements OrderCostInfoServicesI {
    @Override
    public OrderCostInfo getById(Integer id) {
        return orderCostInfoDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        orderCostInfoDaoI.deleteById(id);
    }
    @Override
    public void save(OrderCostInfo object) {
        orderCostInfoDaoI.save(object);
    }
    @Override
    public List<OrderCostInfo> getAll() {
        return orderCostInfoDaoI.getAll();
    }
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "orderCostInfoId desc");
        List<OrderCostInfo> result = orderCostInfoDaoI.list(data);
        return new PageInfo(result);
    }

    @Override
    public List<OrderCostInfo> getOrderCostInfoList(Integer orderInfoId) {
        List<OrderCostInfo> result = orderCostInfoDaoI.getOrderCostInfoList(orderInfoId);
        return result;
    }
}
