package com.yuanrong.admin.server.controller.demand;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumDemandFastStatus;
import com.yuanrong.admin.bean.demand.DemandFast;
import com.yuanrong.admin.result.DemandFastListResult;
import com.yuanrong.admin.rpc.api.demand.DemandFastServicesI;
import com.yuanrong.admin.seach.DemandListParamSearch;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 快速需求的controller
 * Created MDA
 */
@Controller
@RequestMapping("demand")
public class DemandFastController extends BaseController {
    private static final Logger logger = Logger.getLogger(DemandFastController.class);

    /**
     * 后台—快速需求列表查询
     * @param data
     * @param baseModel
     * @return
     */
    @RequestMapping( value = "demandFast_list" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(DemandListParamSearch data , BaseModel baseModel){

        PageInfo<DemandFastListResult> demandFastPageInfo = demandFastServicesI.demandFastList(data , baseModel);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(demandFastPageInfo.getList()) > 0){
            for (DemandFastListResult demandFast : demandFastPageInfo.getList()){
                result.add(DemandFastListResult.packageDemandFast(demandFast));
            }
        }
        return  new ResultTemplate(demandFastPageInfo , result);
    }

    /**
     * 获取快速需求状态
     */
    @RequestMapping("exs_getDemandFastStatus")
    @ResponseBody
    public ResultTemplate getDemandFastStatus(){
        return  new ResultTemplate("", EnumDemandFastStatus.getMapInfo());
    }

    /**
     * 更新 快速需求的拒绝原因
     * @author      ShiLinghuai
     * @param
     * @return
     * @exception
     * @date        2018/7/10 16:07
     */
    @RequestMapping("demandFast_updateStatus")
    @ResponseBody
    public ResultTemplate update(DemandFast demandFast){
        if(demandFast.getDemandFastId() == null){
            return new ResultTemplate("快速需求Id有误");
        }
        if(demandFastServicesI.getById(demandFast.getDemandFastId()) == null){
            return new ResultTemplate("快速需求信息不存在");
        }
        if(demandFast.getDemandFastStatusIndex() ==null ||  demandFast.getDemandFastStatusIndex().compareTo(EnumDemandFastStatus.已拒绝.getIndex()) !=0){
            return new ResultTemplate("快速需求状态值有误");
        }
        if(StringUtil.isBlank(demandFast.getRefuseReason())){
            return new ResultTemplate("请选择拒绝原因");
        }
        demandFastServicesI.updateStatus(demandFast,getUser().getRealName());
        return  new ResultTemplate("",null);
    }

    /**
     * 后台—快速需求的详情
     * @param demandFastId
     * @return
     */
    @RequestMapping("demandFast_detail")
    @ResponseBody
    public ResultTemplate demandFastDetail(Integer demandFastId){
        if(demandFastId == null){
            return new ResultTemplate("快速需求的Id有误");
        }
        DemandFast demandFast =demandFastServicesI.getById(demandFastId);
        if (demandFast==null){
            return new ResultTemplate("快速需求信息不存在");
        }
        return new ResultTemplate(DemandFast.dealDemandFast(demandFast));
    }

    /**
     * 删除快速需求—假删
     * @param demandFastId
     * @return
     */
    @RequestMapping(value = "deleteDemandFast",method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate deleteDemandFast(Integer demandFastId){

        if(demandFastId == null){
            new ResultTemplate("快速需求ID不能为空");
        }
        if(demandFastServicesI.getById(demandFastId) == null){
            new ResultTemplate("快速需求信息不存在");
        }
        demandFastServicesI.deleteDemandFast(demandFastId,getUser().getRealName());
        return new ResultTemplate();
    }
}
