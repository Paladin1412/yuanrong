package com.yuanrong.admin.rpc.service.impl.order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.order.SnapshotYrProduction;
import com.yuanrong.admin.dao.order.SnapshotYrProductionDaoI;
import com.yuanrong.admin.rpc.api.order.SnapshotYrProductionServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
/**
 * 作品快照的services实现类
 * Created MDA
 */
@Service
public class SnapshotYrProductionServicesImpl extends BaseServicesAbstract<SnapshotYrProduction> implements SnapshotYrProductionServicesI {
    @Override
    public SnapshotYrProduction getById(Integer id) {
        return snapshotYrProductionDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        snapshotYrProductionDaoI.deleteById(id);
    }
    @Override
    public void save(SnapshotYrProduction object) {
        snapshotYrProductionDaoI.save(object);
    }
    @Override
    public List<SnapshotYrProduction> getAll() {
        return snapshotYrProductionDaoI.getAll();
    }
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "snapshotYrProductionId desc");
        List<SnapshotYrProduction> result = snapshotYrProductionDaoI.list(data);
        return new PageInfo(result);
    }

    /**
     * 买家中心已购买作品下载
     * @param buyerOrderSn
     * @return
     */
    @Override
    public SnapshotYrProduction getDownloadInfo(String buyerOrderSn) {
        SnapshotYrProduction  snapshotYrProduction = snapshotYrProductionDaoI.getSellerProductInfo(buyerOrderSn);

        return snapshotYrProduction;
    }


}
