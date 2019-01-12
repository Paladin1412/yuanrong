package com.yuanrong.admin.rpc.service.impl.order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.order.OrderDetail;
import com.yuanrong.admin.rpc.api.order.OrderDetailServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * 订单明细的services实现类
 * Created MDA
 */
@Service
public class OrderDetailServicesImpl extends BaseServicesAbstract<OrderDetail> implements OrderDetailServicesI {
    @Override
    public OrderDetail getById(Integer id) {
        return orderDetailDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        orderDetailDaoI.deleteById(id);
    }
    @Override
    public void save(OrderDetail object) {
        orderDetailDaoI.save(object);
    }
    @Override
    public List<OrderDetail> getAll() {
        return orderDetailDaoI.getAll();
    }
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "orderDetailId desc");
        List<OrderDetail> result = orderDetailDaoI.list(data);
        return new PageInfo(result);
    }
}
