package com.yuanrong.admin.rpc.service.impl.base;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.base.ContentForm;
import com.yuanrong.admin.rpc.api.base.ContentFormServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * 内容形式的services实现类
 * Created MDA
 */
@Service
public class ContentFormServicesImpl extends BaseServicesAbstract<ContentForm> implements ContentFormServicesI {
    @Override
    public ContentForm getById(Integer id) {
        return contentFormDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        contentFormDaoI.deleteById(id);
    }
    @Override
    public void save(ContentForm object) {
        contentFormDaoI.save(object);
    }
    @Override
    public List<ContentForm> getAll() {
        return contentFormDaoI.getAll();
    }

    /**
     * 获取内容形式列表
     * @param data
     * @param baseModel
     * @return
     */
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        return null;
    }

    /**
     * 获取内容形式列表—后台
     * @return
     */
    @Override
    public List<ContentForm> list() {
        return this.list(null);
    }

    /**
     * 获取内容形式列表—前台
     * @param statusValue
     * @return
     */
    @Override
    public List<ContentForm> list(Integer statusValue) {
        return contentFormDaoI.list(statusValue);
    }

    /**
     * 通过名称获取内容形式
     * @param contentFormName
     * @return
     */
    @Override
    public ContentForm getByName(String contentFormName) {
        return contentFormDaoI.getByName(contentFormName);
    }

    /**
     * 前台商城——根据作者数量获取内容形式字典
     * @return
     */
    @Override
    public List<Map<String, Object>> getAuthorContentForm() {
        return contentFormDaoI.getAuthorContentForm();
    }
}
