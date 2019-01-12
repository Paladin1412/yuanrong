package com.yuanrong.admin.rpc.service.impl.config;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.config.Configuration;
import com.yuanrong.admin.rpc.api.config.ConfigurationServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * 系统配置的services实现类
 * Created MDA
 */
@Service
public class ConfigurationServicesImpl extends BaseServicesAbstract<Configuration> implements ConfigurationServicesI {
    @Override
    public Configuration getById(Integer id) {
        return configurationDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        configurationDaoI.deleteById(id);
    }
    @Override
    public void save(Configuration object) {
        configurationDaoI.save(object);
    }
    @Override
    public List<Configuration> getAll() {
        return configurationDaoI.getAll();
    }
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "configurationId desc");
        List<Configuration> result = configurationDaoI.list(data);
        return new PageInfo(result);
    }

    @Override
    public String getbyKey(String key) {
        return configurationDaoI.getbyKey(key);
    }
}
