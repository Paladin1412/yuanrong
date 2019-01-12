package com.yuanrong.admin.dao.demand;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.demand.DemandAccountRelation;
import com.yuanrong.admin.bean.demand.DemandYrAuthorRelation;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.dao.BaseDaoI;
import com.yuanrong.admin.result.DemandListResult;
import com.yuanrong.admin.result.DemandOrderListResult;
import com.yuanrong.admin.result.DemandSignListResult;
import com.yuanrong.admin.seach.DemandListParamSearch;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 基本需求信息的dao
 * Created MDA
 */
@Repository
public interface DemandDaoI extends BaseDaoI<Demand> {
    /**
    * 根据需求id查询需求。
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/7/5 18:21
    */
    Demand getDetailByDemand(Demand demand);


    /**
     * 后台—需求列表查询
     * @param data
     * @return
     */
    List<DemandListResult> demandList(@Param("data") DemandListParamSearch data);

    /**
     * 查询状态个数（待审核、待推荐）
     * @return
     */
    List<Map<String,Object>> statusNum();

    /**
     * 后台—通过需求Id获取信息
     * @param demandId
     * @return
     */
    Demand findById(@Param("demandId") Integer demandId);

    /**
     * 后台—编辑修改需求
     * @param demand
     */
    void updateDemand(@Param("data") Demand demand);

    /**
     * 后台—修改需求状态
     * @param demand
     */
    void updateDemandStatus(@Param("data") Demand demand);

    /**
     * 新增需求和账号关系表
     * @param demandAccountList
     */
    void insertBatchDemandAccountRelation(@Param(value = "demandAccountList")List<DemandAccountRelation> demandAccountList);

    /**
     * 新增需求 和 圆融创作者关系
     * @param demandYrAuthorList
     */
    void insertBatchDemandYrAuthorRelation(@Param(value = "demandYrAuthorList")List<DemandYrAuthorRelation> demandYrAuthorList);

    /**
     * 后台—获取需求的选购商品(前台选购的创作者/账号)
     * @param demandId
     * @param demandTypeIndex
     * @return
     */
    List<Map<String,Object>> getDemandGoods(@Param("demandId") Integer demandId, @Param("demandTypeIndex") Integer demandTypeIndex);

    /**
     * 后台—通过需求Id获取订单列表
     * @param demandId
     * @param orderType
     * @return
     */
    List<DemandOrderListResult> getOrderListByDemandId(@Param("demandId") Integer demandId, @Param("orderType") Integer orderType);

    /**
     * 前台—获取需求的选购商品(前台选购的创作者/账号)
     * @param demandId
     * @param demandTypeIndex
     * @return
     */
    List<Map<String,Object>> getDemandGoodsFront(@Param("demandId") Integer demandId, @Param("demandTypeIndex") Integer demandTypeIndex);

    /**
     * 删除需求—假删
     * @param demandId
     */
    void deleteDemand(@Param("demandId") Integer demandId);

    /**
     * 前台需求大厅
     * @param data
     * @return
     */
    List<Demand> getDemandHall(@Param("data") Demand data);

    /**
     * 依据需求单获取需求详情
     * @param data
     * @return
     */
    Demand getByDemandSn(@Param("data")String data);

    /**
     * 后台—获取需求的报名记录
     * @param demandId
     * @param orderType
     * @return
     */
    List<DemandSignListResult> getDemandSignList(@Param("demandId") Integer demandId, @Param("orderType") Integer orderType);

    /**
     * 更改需求状态为已完成：（截止日期-当前时间）< 0
     */
    void updateDemandStatusIndex();

    /**
     * 需求大厅统计类型数据
     */
     List<Map<String,Integer>> demandTypeCnt();

}
