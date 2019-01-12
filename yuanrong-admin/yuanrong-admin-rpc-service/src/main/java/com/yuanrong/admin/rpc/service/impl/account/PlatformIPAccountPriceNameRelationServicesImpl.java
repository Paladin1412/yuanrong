package com.yuanrong.admin.rpc.service.impl.account;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.account.PlatformIPAccountPriceNameRelation;
import com.yuanrong.admin.rpc.api.account.PlatformIPAccountPriceNameRelationServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.util.BaseModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/4/27.
 */
@Service
public class PlatformIPAccountPriceNameRelationServicesImpl extends BaseServicesAbstract<PlatformIPAccountPriceNameRelation> implements PlatformIPAccountPriceNameRelationServicesI {
    @Override
    public PlatformIPAccountPriceNameRelation getById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void save(PlatformIPAccountPriceNameRelation object) {

    }

    @Override
    public List<PlatformIPAccountPriceNameRelation> getAll() {
        return null;
    }

    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        return null;
    }

    @Override
    public List<Map<String , Object>> getByShortVideoPlatformInfoIDs(int[] platformInfoIDs) {
        return platformIPAccountPriceNameDaoI.getByShortVideoPlatformInfoIDs(platformInfoIDs);
    }

    @Override
    public PlatformIPAccountPriceNameRelation getByPlatformIdAndID(PlatformIPAccountPriceNameRelation data) {
        return platformIPAccountPriceNameDaoI.getByPlatformIdAndID(data);
    }

    @Override
    public Map<String, Object> getPlatformPriceNameByID(int id) {
        return platformIPAccountPriceNameDaoI.getPlatformPriceNameByID(id);
    }

    @Override
    public Map<String, Object> getPlatFormByName(String name) {
        return platformIPAccountPriceNameDaoI.getPlatFormByName(name);
    }
}
