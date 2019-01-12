package com.yuanrong.admin.rpc.service.impl.order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.order.SnapshotYrAuthor;
import com.yuanrong.admin.rpc.api.order.SnapshotYrAuthorServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * 作者快照的services实现类
 * Created MDA
 */
@Service
public class SnapshotYrAuthorServicesImpl extends BaseServicesAbstract<SnapshotYrAuthor> implements SnapshotYrAuthorServicesI {
    @Override
    public SnapshotYrAuthor getById(Integer id) {
        return snapshotYrAuthorDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        snapshotYrAuthorDaoI.deleteById(id);
    }
    @Override
    public void save(SnapshotYrAuthor object) {
        snapshotYrAuthorDaoI.save(object);
    }
    @Override
    public List<SnapshotYrAuthor> getAll() {
        return snapshotYrAuthorDaoI.getAll();
    }
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "snapshotYrAuthorId desc");
        List<SnapshotYrAuthor> result = snapshotYrAuthorDaoI.list(data);
        return new PageInfo(result);
    }
}
