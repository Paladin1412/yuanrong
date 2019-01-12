package com.yuanrong.admin.dao.order;
import com.yuanrong.admin.bean.order.SnapshotAccount;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * 账号快照的dao
 * Created MDA
 */
@Repository
public interface SnapshotAccountDaoI extends BaseDaoI<SnapshotAccount> {
    /**
     * 通过订单Id获取账号快照
     * @param orderInfoSellerId
     * @return
     */
    SnapshotAccount getByOrderInfoSellerId(@Param("orderInfoSellerId") Integer orderInfoSellerId);

    /**
     * 通过订单Id获取账号快照
     * @param orderDetailId
     * @return
     */
    SnapshotAccount getByOrderDetailId(@Param("orderDetailId") Integer orderDetailId);
}
