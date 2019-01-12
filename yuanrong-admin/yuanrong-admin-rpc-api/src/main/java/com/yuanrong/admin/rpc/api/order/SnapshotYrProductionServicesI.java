package com.yuanrong.admin.rpc.api.order;
import com.yuanrong.admin.bean.order.SnapshotYrProduction;
import com.yuanrong.admin.rpc.BaseServicesI;
/**
 * 作品快照的services接口
 * Created MDA
 */
public interface SnapshotYrProductionServicesI extends BaseServicesI<SnapshotYrProduction> {

    /**
     * 买家中心已购买作品下载
     * @param buyerOrderSn
     * @return
     */
    SnapshotYrProduction getDownloadInfo(String buyerOrderSn);


}
