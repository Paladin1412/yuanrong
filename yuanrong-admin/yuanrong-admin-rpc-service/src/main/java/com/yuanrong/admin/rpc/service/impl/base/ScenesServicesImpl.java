package com.yuanrong.admin.rpc.service.impl.base;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.base.Scenes;
import com.yuanrong.admin.rpc.api.base.ScenesServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * 使用场景的services实现类
 * Created MDA
 */
@Service
public class ScenesServicesImpl extends BaseServicesAbstract<Scenes> implements ScenesServicesI {
    @Override
    public Scenes getById(Integer id) {
        return scenesDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        scenesDaoI.deleteById(id);
    }
    @Override
    public void save(Scenes object) {
        scenesDaoI.save(object);
    }
    @Override
    public List<Scenes> getAll() {
        return scenesDaoI.getAll();
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        return null;
    }

    /**
     * 使用场景
     * @return
     */
    @Override
    public List<Scenes> list() {
        List<Scenes> result = list(null);
        return result;
    }

    /**
     * 使用场景—前台
     * @param statusValue
     * @return
     */
    @Override
    public List<Scenes> list(Integer statusValue) {
        return scenesDaoI.list(statusValue);
    }

    /**
     * 通过名称获取使用场景
     * @param scenesName
     * @return
     */
    @Override
    public Scenes getByName(String scenesName) {
        return scenesDaoI.getByName(scenesName);
    }

    /**
     * 前台商城——获取创作者使用场景
     * @return
     */
    @Override
    public List<Map<String, Object>> getAuthorScenes() {
        return scenesDaoI.getAuthorScenes();
    }
}
