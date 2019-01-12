package com.yuanrong.admin.rpc.api.base;

import com.yuanrong.admin.bean.base.AreaCity;
import com.yuanrong.admin.rpc.BaseServicesI;

import java.util.List;

/**
 * Created by zhonghang on 2018/4/28.
 */
public interface AreaCityServicesI extends BaseServicesI<AreaCity> {
    /**
     * 通过父级查找城市信息
     * @param padcd
     * @return
     */
    public List<AreaCity> getAreaCityByParentID (String padcd);
}
