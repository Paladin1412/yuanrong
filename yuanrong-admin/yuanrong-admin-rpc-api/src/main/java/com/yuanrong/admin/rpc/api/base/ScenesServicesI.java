package com.yuanrong.admin.rpc.api.base;
import com.yuanrong.admin.bean.base.Scenes;
import com.yuanrong.admin.rpc.BaseServicesI;

import java.util.List;
import java.util.Map;

/**
 * 使用场景的services接口
 * Created MDA
 */
public interface ScenesServicesI extends BaseServicesI<Scenes> {
    /**
     * 获取使用场景
     * @return
     */
    List<Scenes> list();

    /**
     * 使用场景—前台
     * @param statusValue
     * @return
     */
    List<Scenes> list(Integer statusValue);

    /**
     * 通过名称获取使用场景
     * @param scenesName
     * @return
     */
    Scenes getByName(String scenesName);

    /**
     * 前台商城——获取创作者使用场景
     * @return
     */
    List<Map<String,Object>> getAuthorScenes();
}
