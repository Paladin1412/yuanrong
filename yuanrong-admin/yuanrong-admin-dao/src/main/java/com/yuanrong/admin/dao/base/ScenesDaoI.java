package com.yuanrong.admin.dao.base;
import com.yuanrong.admin.bean.base.Scenes;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 使用场景的dao
 * Created MDA
 */
@Repository
public interface ScenesDaoI extends BaseDaoI<Scenes> {
    /**
     * 获取使用场景
     * @param statusValue
     * @return
     */
    List<Scenes> list(@Param("statusValue") Integer statusValue);

    /**
     * 通过名字获取使用场景
     * @param scenesName
     * @return
     */
    Scenes getByName(@Param("scenesName") String scenesName);

    /**
     * 前台商城——获取创作者使用场景
     * @return
     */
    List<Map<String,Object>> getAuthorScenes();
}
