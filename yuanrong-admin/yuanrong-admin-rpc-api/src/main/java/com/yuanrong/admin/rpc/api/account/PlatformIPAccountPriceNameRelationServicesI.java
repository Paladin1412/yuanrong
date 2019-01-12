package com.yuanrong.admin.rpc.api.account;

import com.yuanrong.admin.bean.account.PlatformIPAccountPriceNameRelation;
import com.yuanrong.admin.rpc.BaseServicesI;

import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/4/27.
 */
public interface PlatformIPAccountPriceNameRelationServicesI extends BaseServicesI<PlatformIPAccountPriceNameRelation> {
    /**
     * 通过平台ID查询平台的报价名称
     * @param platformInfoIDs
     * @return
     */
    public List<Map<String , Object>> getByShortVideoPlatformInfoIDs (int[] platformInfoIDs);
    /**
     * 通过平台ID和自增ID查询该平台是否有此报价
     * @param data
     * @return
     */
    public PlatformIPAccountPriceNameRelation getByPlatformIdAndID(PlatformIPAccountPriceNameRelation data);

    /**
     * 查询报价名称是否存在
     * @param id
     * @return
     */
    Map<String , Object> getPlatformPriceNameByID(int id);

    /**
     * 通过名称查找平台是否存在
     * @param name
     * @return
     */
    Map<String , Object>  getPlatFormByName (String name);
}
