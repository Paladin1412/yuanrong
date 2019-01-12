package com.yuanrong.admin.rpc.service.impl.base;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.rpc.api.base.DictInfoServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/4/28.
 */
@Service
public class DictInfoServicesImpl extends BaseServicesAbstract<DictInfo> implements DictInfoServicesI {
    @Override
    public DictInfo getById(Integer id) {
        return dictInfoDaoI.getById(id);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void save(DictInfo object) {

    }

    @Override
    public List<DictInfo> getAll() {
        return null;
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        return null;
    }

    @Override
    public List<DictInfo> getDictInfoByType(int type) {
        return dictInfoDaoI.getDictInfoByType(type);
    }

    @Override
    public List<DictInfo> getDictInfoByTypes(Integer[] dictInfos) {

        return dictInfoDaoI.getDictInfoByTypeS(dictInfos);
    }

    @Override
    public DictInfo getDictInfoByTypeAndName(int type, String name) {
        return dictInfoDaoI.getDictInfoByTypeAndName(type , name);
    }

    @Override
    public DictInfo getDictInfoByTypeAndID(int type, int id) {
        return dictInfoDaoI.getDictInfoByTypeAndID(type , id);
    }

    /**
     * 前台商城——根据作者数量获取内容分类字典
     * @param type
     * @return
     */
    @Override
    public List<Map<String, Object>> getAuthorContentType(Integer type) {
        return dictInfoDaoI.getAuthorContentType(type);
    }
}
