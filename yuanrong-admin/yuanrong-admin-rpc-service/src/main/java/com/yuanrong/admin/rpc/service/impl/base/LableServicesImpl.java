package com.yuanrong.admin.rpc.service.impl.base;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.base.Lable;
import com.yuanrong.admin.rpc.api.base.LableServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * 标签的services实现类
 * Created MDA
 */
@Service
public class LableServicesImpl extends BaseServicesAbstract<Lable> implements LableServicesI {
    @Override
    public Lable getById(Integer id) {
        return lableDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        lableDaoI.deleteById(id);
    }
    @Override
    public void save(Lable object) {
        lableDaoI.save(object);
    }
    @Override
    public List<Lable> getAll() {
        return lableDaoI.getAll();
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        return null;
    }

    /**
     * 标签列表
     * @param typeId
     * @return
     */
    @Override
    public List<Lable> list(String typeId) {
        return lableDaoI.list(typeId);
    }

    /**
     * 根据作者数量获取创作风格字典
     * @return
     */
    @Override
    public List<Map<String, Object>> getAuthorContentStyle() {
        return lableDaoI.getAuthorContentStyle();
    }
}
