package com.yuanrong.admin.rpc.service.impl.base;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.base.AreaCity;
import com.yuanrong.admin.rpc.api.base.AreaCityServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhonghang on 2018/4/28.
 */
@Service
public class AreaCityServicesImpl extends BaseServicesAbstract<AreaCity> implements AreaCityServicesI {
    @Override
    public AreaCity getById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void save(AreaCity object) {

    }

    @Override
    public List<AreaCity> getAll() {
        return null;
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        return null;
    }

    @Override
    public List<AreaCity> getAreaCityByParentID(String padcd) {
        return areaCityDaoI.getAreaCityByParentID(padcd);
    }
}
