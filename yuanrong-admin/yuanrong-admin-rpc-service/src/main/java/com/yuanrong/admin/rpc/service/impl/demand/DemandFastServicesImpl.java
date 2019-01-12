package com.yuanrong.admin.rpc.service.impl.demand;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumDemandType;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.demand.DemandFast;
import com.yuanrong.admin.result.DemandFastListResult;
import com.yuanrong.admin.rpc.api.demand.DemandFastServicesI;
import com.yuanrong.admin.rpc.service.BaseServicesAbstract;
import com.yuanrong.admin.seach.DemandListParamSearch;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.DateUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 快速需求的services实现类
 * Created MDA
 */
@Service
public class DemandFastServicesImpl extends BaseServicesAbstract<DemandFast> implements DemandFastServicesI {
    @Override
    public DemandFast getById(Integer id) {
        return demandFastDaoI.getById(id);
    }
    @Override
    public void deleteById(Integer id) {
        demandFastDaoI.deleteById(id);
    }
    @Override
    public void save(DemandFast object) {
        demandFastDaoI.save(object);
    }
    @Override
    public List<DemandFast> getAll() {
        return demandFastDaoI.getAll();
    }
    @Override
    public PageInfo list(Object data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "demandFastId desc");
        List<DemandFast> result = demandFastDaoI.list(data);
        List<JSONObject> rntList = new ArrayList<JSONObject>();
        for (DemandFast  demandFast : result){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("demandSn",demandFast.getDemandSn());
            jsonObject.put("nickname",demandFast.getNickname());
            EnumDemandType.营销分发.getName();
            String demandType="";
            if(demandFast.getDemandTypeIndex() == EnumDemandType.定制内容.getIndex() ){
                demandType = EnumDemandType.营销分发.getName();
            }
            if(demandFast.getDemandTypeIndex() == EnumDemandType.ip代理.getIndex()){
                demandType = EnumDemandType.ip代理.getName();
            }
            if(demandFast.getDemandTypeIndex() == EnumDemandType.营销分发.getIndex()){
                demandType = EnumDemandType.营销分发.getName();
            }
            jsonObject.put("demandTypeIndex",demandFast.getDemandTypeIndex());
            jsonObject.put("demandFastStatusIndex",demandFast.getDemandFastStatusIndex());
            jsonObject.put("demandType",demandType);
            jsonObject.put("demandFastStatus",demandFast.getDemandFastStatus());
            jsonObject.put("createdTime", DateUtil.format(demandFast.getCreatedTime(),"yyyy-MM-dd HH:mm:ss"));
            jsonObject.put("refuseReason",demandFast.getRefuseReason());
            jsonObject.put("demandId",demandFast.getDemandId());
            Demand demand = demandDaoI.getById(demandFast.getDemandId());
            if(demand!=null){
                jsonObject.put("demand_SN",demand.getDemandSn());
            }
            rntList.add(jsonObject);
        }

        return new PageInfo(rntList);
    }

    /**
     * 后台—快速需求列表查询
     * @param data
     * @param baseModel
     * @return
     */
    @Override
    public PageInfo demandFastList(DemandListParamSearch data, BaseModel baseModel) {
        PageHelper.startPage(baseModel.getCp() , baseModel.getRows() , "df.createdTime desc");
        List<DemandFastListResult> result = demandFastDaoI.demandFastList(data);
        return new PageInfo(result);
    }

    @Override
    public boolean isHaveDemandFast(Integer registeredUserID) {
        List<Map<String,Object>> list = demandFastDaoI.countDemandFastByRegisteredUserID(registeredUserID);
        if((Long)(list.get(0).get("coNum"))== 0L){
            return false;
        }else {
            return true;
        }

    }

    /**
     * 修改快速需求的状态—拒绝
     * @param demandFast
     */
    @Override
    public void updateStatus(DemandFast demandFast,String userName) {
        demandFastDaoI.updateStatus(demandFast);
        systemLogDaoI.log(DemandFast.class.getName(),demandFast.getDemandFastId() ,"修改快速需求",userName);
    }

    /**
     * 删除快速需求—假删
     * @param demandFastId
     */
    @Override
    public void deleteDemandFast(Integer demandFastId,String userName) {
        demandFastDaoI.deleteDemandFast(demandFastId);
        systemLogDaoI.log(DemandFast.class.getName(),demandFastId ,"删除快速需求—假删",userName);
    }
}
