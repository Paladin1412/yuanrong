package com.yuanrong.admin.server.controller.account;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.rpc.api.account.PlatformIPAccountServicesI;
import com.yuanrong.admin.seach.PlatformIpAccountSeach;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/*
 *@author songwq
 *@date 2018/8/31
 *@time 18:23
 *@description
 */
@Controller
@RequestMapping("account")
public class PlatformIPAccountAgentController extends BaseController {

    /**
     *@author songwq
     *@param
     *@date 2018/9/3
     *@description 行业分类
    */
    @RequestMapping("getCategoryList")
    @ResponseBody
    public ResultTemplate getCategoryList(PlatformIpAccountSeach platformIpAccountSeach, BaseModel baseModel){
        String type = platformIpAccountSeach.getPlatformID();
        if(type==null){
            return new ResultTemplate("平台不能为空");
        }
        Integer platformID = Integer.valueOf(type);
        if(platformID==12 || platformID==13){
            platformID=14;//圆融分类
        }else if(platformID==1 || platformID==2 || platformID==3 || platformID==10 ){
            platformID=4;//短视频分类
        }
        List<DictInfo> dictInfoList = dictInfoServicesI.getDictInfoByType(platformID);
        return new ResultTemplate(DictInfo.pareToJSONObject(dictInfoList));
    }

    /**
     *@author songwq
     *@param
     *@date 2018/8/31
     *@description 查询代理权管理列表
    */ 
    @RequestMapping("getAgentList")
    @ResponseBody
    public ResultTemplate getAgentList(PlatformIpAccountSeach platformIpAccountSeach, BaseModel baseModel){
        if(platformIpAccountSeach.getOrderByField()==null){
            platformIpAccountSeach.setOrderByField("createdTime");
        }
        if(platformIpAccountSeach.getCategoryID()!=null){
            if(platformIpAccountSeach.getCategoryID()<Integer.MIN_VALUE || platformIpAccountSeach.getCategoryID()>Integer.MAX_VALUE){
                return new ResultTemplate("categoryID参数的不在整形范围内");
            }
        }
        platformIpAccountSeach.setIsAgent(1);//只展示有代理权的账号
        PageInfo<Map> result = platformIPAccountServicesI.getList(platformIpAccountSeach , baseModel);
        JSONArray resultArray = new JSONArray();
        if(result!=null||!"".equals(result)||!"null".equals(result))
            if(CollectionUtil.size(result.getList()) > 0){
                for(Map<String ,Object> ele : result.getList()){
                    JSONObject jsonObject = PlatformIPAccount.mapParerPlatformAgent(ele);
                    resultArray.add(jsonObject);
                }
            }
        return new ResultTemplate(result , resultArray);
    }

    /**
     *@author songwq
     *@param
     *@date 2018/8/31
     *@description 根据账号ID取消代理权
    */
    @RequestMapping("cancelAgentPermission")
    @ResponseBody
    public ResultTemplate cancelAgentPermission(PlatformIpAccountSeach data){
        if(StringUtils.isEmpty(data)||StringUtils.isEmpty(data.getId())){
            return new ResultTemplate("系统ID参数不能为空");
        }
        PlatformIPAccount platformIPAccount = platformIPAccountServicesI.getById(Integer.valueOf(data.getId()));
        if(StringUtils.isEmpty(platformIPAccount)){
            return new ResultTemplate("无法获取相关账户信息");
        }
        if(StringUtils.isEmpty(data.getIsAgent())){
            return new ResultTemplate("参数isAgent不能为空");
        }
        if(data.getAgentCoopBrand()!=null && data.getAgentCoopBrand().length()>200){
            return new ResultTemplate("参数agentCoopBrand的长度为0-200");
        }
        if(data.getAgentCoopCondition()!=null && data.getAgentCoopCondition().length()>200){
            return new ResultTemplate("参数agentCoopCondition的长度为0-200");
        }
        platformIPAccountServicesI.updateAgent(data);
        return new ResultTemplate();
    }
}
