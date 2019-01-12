package com.yuanrong.admin.rpc.api.demand;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.demand.DemandFast;
import com.yuanrong.admin.result.DemandFastListResult;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.seach.DemandListParamSearch;
import com.yuanrong.admin.util.BaseModel;
/**
 * 快速需求的services接口
 * Created MDA
 */
public interface DemandFastServicesI extends BaseServicesI<DemandFast> {
    /**
     * 后台—快速需求列表查询
     * @param data
     * @param baseModel
     * @return
     */
    PageInfo<DemandFastListResult> demandFastList(DemandListParamSearch data, BaseModel baseModel);
    /**
    * 根据注册用户查是否有快速需求
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/7/10 14:45
    */
    boolean isHaveDemandFast(Integer registeredUserID);
    /**
     * 更新拒绝状态
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/7/10 16:04
     */
    void updateStatus(DemandFast demandFast,String userName);

    /**
     * 删除快速需求—假删
     * @param demandFastId
     */
    void deleteDemandFast(Integer demandFastId,String userName);
}
