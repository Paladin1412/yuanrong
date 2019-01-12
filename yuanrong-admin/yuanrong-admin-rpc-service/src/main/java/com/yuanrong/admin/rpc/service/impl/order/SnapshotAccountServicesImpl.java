package com.yuanrong.admin.rpc.service.impl.order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.order.SnapshotAccount;
import com.yuanrong.admin.rpc.api.order.SnapshotAccountServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * 账号快照的services实现类
 * Created MDA
 */
@Service
public class SnapshotAccountServicesImpl extends BaseServicesAbstract<SnapshotAccount> implements SnapshotAccountServicesI {
    @Override
    public SnapshotAccount getById(Integer id) {
        return snapshotAccountDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        snapshotAccountDaoI.deleteById(id);
    }
    @Override
    public void save(SnapshotAccount object) {
        snapshotAccountDaoI.save(object);
    }
    @Override
    public List<SnapshotAccount> getAll() {
        return snapshotAccountDaoI.getAll();
    }
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "snapshotAccountId desc");
        List<SnapshotAccount> result = snapshotAccountDaoI.list(data);
        return new PageInfo(result);
    }
}
