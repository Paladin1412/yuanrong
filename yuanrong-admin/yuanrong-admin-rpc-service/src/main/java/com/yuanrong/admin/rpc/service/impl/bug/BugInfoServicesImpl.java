package com.yuanrong.admin.rpc.service.impl.bug;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.bug.BugInfo;
import com.yuanrong.admin.rpc.api.bug.BugInfoServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * bug信息的services实现类
 * Created MDA
 */
@Service
public class BugInfoServicesImpl extends BaseServicesAbstract<BugInfo> implements BugInfoServicesI {
    @Override
    public BugInfo getById(Integer id) {
        return bugInfoDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        bugInfoDaoI.deleteById(id);
    }
    @Override
    public void save(BugInfo object) {
        bugInfoDaoI.save(object);
    }
    @Override
    public List<BugInfo> getAll() {
        return bugInfoDaoI.getAll();
    }
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "bugInfoId desc");
        List<BugInfo> result = bugInfoDaoI.list(data);
        return new PageInfo(result);
    }

    @Override
    public BugInfo error(BugInfo bugInfo) {
        bugInfoDaoI.save(bugInfo);
        return bugInfo;
    }
}
