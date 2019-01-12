package com.yuanrong.admin.dao.base;

import com.yuanrong.admin.bean.base.AreaCity;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhonghang on 2018/4/28.
 */
@Repository
public interface AreaCityDaoI extends BaseDaoI<AreaCity> {
    /**
     * 通过父级查找城市信息
     * @param padcd
     * @return
     */
    public List<AreaCity> getAreaCityByParentID (@Param("padcd") String padcd);
}
