package com.yuanrong.admin.dao.account;

import com.yuanrong.admin.bean.account.PlatformIPAccountPriceNameRelation;
import com.yuanrong.admin.dao.BaseDaoI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/4/27.
 */
@Repository
public interface PlatformIPAccountPriceNameRelationDaoI extends BaseDaoI<PlatformIPAccountPriceNameRelationDaoI> {
    /**
     * 通过平台ID查询平台的报价名称
     * @param platformInfoIDs
     * @return
     */
    public List<Map<String , Object>> getByShortVideoPlatformInfoIDs (@Param("platformInfoIDs") int[] platformInfoIDs);

    /**
     * 通过平台ID和自增ID查询该平台是否有此报价
     * @param data
     * @return
     */
    public PlatformIPAccountPriceNameRelation getByPlatformIdAndID(@Param("data") PlatformIPAccountPriceNameRelation data);

    /**
     * 查询报价名称是否存在
     * @param id
     * @return
     */
    Map<String , Object> getPlatformPriceNameByID(@Param("id") int id);

    /**
     * 通过名称查找平台是否存在
     * @param name
     * @return
     */
    Map<String , Object>  getPlatFormByName (@Param("name") String name);
}
