package com.yuanrong.admin.dao.order;
import com.yuanrong.admin.bean.order.SnapshotYrProduction;
import com.yuanrong.admin.dao.BaseDaoI;
import org.springframework.stereotype.Repository;
/**
 * 作品快照的dao
 * Created MDA
 */
@Repository
public interface SnapshotYrProductionDaoI extends BaseDaoI<SnapshotYrProduction> {

    /**
     *@author songwq
     *@param buyerOrderSn
     *@data 2018/6/29
     *@description 买家中心已购买作品下载
     */
    SnapshotYrProduction getSellerProductInfo(String buyerOrderSn);
}
