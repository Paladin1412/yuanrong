package com.yuanrong.admin.dao.demand;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.demand.DemandFast;
import com.yuanrong.admin.dao.BaseDaoI;
import com.yuanrong.admin.result.DemandFastListResult;
import com.yuanrong.admin.seach.DemandListParamSearch;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 快速需求的dao
 * Created MDA
 */
@Repository
public interface DemandFastDaoI extends BaseDaoI<DemandFast> {
    /**
     * 后台—快速需求列表查询
     * @param data
     * @return
     */
    List<DemandFastListResult> demandFastList(@Param("data") DemandListParamSearch data);
    /**
    * 根据注册用户id获取其需求数
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/7/10 14:25
    */
    List<Map<String,Object>> countDemandFastByRegisteredUserID(@Param("registeredUserInfoId") Integer registeredUserInfoId);

    /**
     * 更新拒绝原因状态
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/7/10 16:02
     */
    void updateStatus(DemandFast demandFast);

    /**
     * 删除快速需求—假删
     * @param demandFastId
     */
    void deleteDemandFast(@Param("demandFastId") Integer demandFastId);
}
