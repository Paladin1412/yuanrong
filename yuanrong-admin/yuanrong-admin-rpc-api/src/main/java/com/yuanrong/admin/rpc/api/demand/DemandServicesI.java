package com.yuanrong.admin.rpc.api.demand;
import com.github.pagehelper.PageInfo;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.bean.base.UserInfo;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.demand.DemandAccountRelation;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;
import com.yuanrong.admin.bean.order.OrderInfoSeller;
import com.yuanrong.admin.result.DemandListResult;
import com.yuanrong.admin.result.DemandOrderListResult;
import com.yuanrong.admin.result.DemandSignListResult;
import com.yuanrong.admin.rpc.BaseServicesI;
import com.yuanrong.admin.seach.DemandListParamSearch;
import com.yuanrong.admin.seach.OrderOfferParam;
import com.yuanrong.admin.seach.PlatformIpAccountSeach;
import com.yuanrong.admin.util.BaseModel;

import java.util.List;
import java.util.Map;

/**
 * 基本需求信息的services接口
 * Created MDA
 */
public interface DemandServicesI extends BaseServicesI<Demand> {
    /**
    * 根据需求id和当前注册用户去查需求详情，带订单.买家中心。
    * @author      ShiLinghuai
    * @param
    * @return
    * @exception
    * @date        2018/7/6 11:52
    */
    Demand getDetailByDemand(Demand demand);

    /**
     * 后台—需求列表查询
     * @param data
     * @param baseModel
     * @return
     */
    PageInfo<DemandListResult> demandList(DemandListParamSearch data, BaseModel baseModel);

    /**
     * 查询状态个数（待审核、待推荐）
     * @return
     */
    List<Map<String,Object>> statusNum();

    /**
     * 通过需求Id获取信息
     * @param demandId
     * @return
     */
    Demand findById(Integer demandId);

    /**
     * 后台—编辑修改需求
     * @param demand
     */
    void updateDemand(Demand demand,String userName);

    /**
     * 后台—修改需求状态
     * @param demand
     * @param userName
     */
    void updateDemandStatus(Demand demand,String userName);

    /**
     * 新增 并返回主键
     * @param demand
     * @return
     */
    int saveGetKey(Demand demand,String userName);

    /**
     *  需求和账号关系表
     * @param demandId 需求id，
     * @param  ids IPAccount id
     */
    void insertBatchDemandAccountRelation(int demandId,String ids);

    /**
     *  需求和创作者关系表
     * @param demandId 需求id，
     * @param  yrAuthotIds  创作者ids
     */
    void insertBatchDemandYrAuthorRelation(int demandId,String yrAuthotIds);


    /**
     * 后台—需求推荐—营销分发
     * @param orderOfferParam
     */
    void saveDemandOrderAndSnap(OrderOfferParam orderOfferParam);

    /**
     * 后台—获取需求的选购商品(前台选购的创作者/账号)
     * @param demandId
     * @param demandTypeIndex
     * @return
     */
    List<Map<String,Object>> getDemandGoods(Integer demandId, Integer demandTypeIndex);

    /**
     * 后台—通过需求Id获取订单列表—账号/创作者/原创征稿
     * @param demandId
     * @param baseModel
     * @return
     */
    List<DemandOrderListResult> getOrderListByDemandId(Integer demandId,BaseModel baseModel);

    void batchSave(Demand demand, String authorIds, String accountIPIds, String shopCartIds,UserInfo userInfo);

    /**
     * 前台—获取需求的选购商品(前台选购的创作者/账号)
     * @param demandId
     * @param demandTypeIndex
     * @return
     */
    List<Map<String,Object>> getDemandGoodsFront(Integer demandId, Integer demandTypeIndex);

    /**
     * 需求选购账号
     * @param accountSeach
     * @param baseModel
     * @return
     */
    PageInfo<Map> getDemandAccount(PlatformIpAccountSeach accountSeach, BaseModel baseModel);

    /**
     * 删除需求—假删
     * @param demandId
     */
    void deleteDemand(Integer demandId);

    /**
     * 前台需求大厅
     * @param data
     * @param baseModel
     * @return
     */
    PageInfo<Demand> getDemandHall(Demand data,BaseModel baseModel);

    /**
     * 依据需求单获取需求详情
     * @param demandSn
     * @return
     */
    Demand getByDemandSn(String demandSn);

    /**
     * 后台—获取需求的报名记录
     * @param demandId
     * @param orderType
     * @param baseModel
     * @return
     */
    List<DemandSignListResult> getDemandSignList(Integer demandId, Integer orderType, BaseModel baseModel);

    /**
     * 后台—保存报名记录
     * @param orderOfferParam
     * @param userName
     */
    void saveSignRecord(OrderOfferParam orderOfferParam,String userName);

    /**
     * 确认使用生成订单
     * @param orderOfferParam
     * @param userName
     */
    void saveConfirmUseOk(OrderOfferParam orderOfferParam,String userName);
    /**
     * 买家中心需求详情订单列表
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/7/6 11:52
     */
    PageInfo<OrderInfoBuyer> getOrderList(Demand demand, BaseModel baseModel);
    /**
     * 买家中心需求详情报名列表
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/7/6 11:52
     */
    PageInfo<OrderInfoSeller> getApplyList(Demand demand,BaseModel baseModel);

    /**
     * 后台—原创征稿生成订单
     * @param orderInfoSellerId
     * @param userName
     */
    void saveProOrder(Integer orderInfoSellerId,String userName);

    /**
     * 更改需求状态为已完成：（截止日期-当前时间）< 0
     */
    void updateDemandStatusIndex();

    /**
     * 前台——生成订单—原创征稿
     * 批量
     * @param orderSn
     * @param userName
     */
    List<OrderInfoBuyer> saveProOrderSn(String[] orderSn, String userName);

    /**
     * 需求大厅统计类型数据
     */
    List<Map<String,Integer>> demandTypeCnt();
}
