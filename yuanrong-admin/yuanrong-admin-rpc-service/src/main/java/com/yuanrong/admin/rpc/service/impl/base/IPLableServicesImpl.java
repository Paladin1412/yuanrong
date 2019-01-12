package com.yuanrong.admin.rpc.service.impl.base;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.base.IPLable;
import com.yuanrong.admin.rpc.api.base.IPLableServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * IP标签的services实现类
 * Created MDA
 */
@Service
public class IPLableServicesImpl extends BaseServicesAbstract<IPLable> implements IPLableServicesI {
    @Override
    public IPLable getById(Integer id) {
        return iPLableDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        iPLableDaoI.deleteById(id);
    }
    @Override
    public void save(IPLable object) {
        iPLableDaoI.save(object);
    }
    @Override
    public List<IPLable> getAll() {
        return iPLableDaoI.getAll();
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "recID desc");
        List<IPLable> result = iPLableDaoI.list(data);
        return new PageInfo(result);
    }

    /**
     * 通过parentID获取IPLabe
     * @param parentId
     * @return
     */
    @Override
    public List<IPLable> getIPLableByParentId(String parentId) {
        return iPLableDaoI.getIPLableByParentId(parentId);
    }
}
