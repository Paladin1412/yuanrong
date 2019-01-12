package com.yuanrong.admin.dao.order;
import com.yuanrong.admin.bean.order.OrderDetail;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 订单明细的dao
 * Created MDA
 */
@Repository
public interface OrderDetailDaoI extends BaseDaoI<OrderDetail> {
    /**
     * 通过订单Id获取订单明细
     * @param orderBuyerId
     * @return
     */
    OrderDetail getByOrderBuyerId(@Param("orderBuyerId") Integer orderBuyerId);

    /**
     *
     * 订单明细修改
     * @param orderDetail
     */
    void updateDetail(@Param("data") OrderDetail orderDetail);
}
