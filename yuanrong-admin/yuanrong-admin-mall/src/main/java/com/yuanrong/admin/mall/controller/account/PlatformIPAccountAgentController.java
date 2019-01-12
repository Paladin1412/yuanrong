package com.yuanrong.admin.mall.controller.account;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumDictInfoType;
import com.yuanrong.admin.Enum.EnumYesOrNo;
import com.yuanrong.admin.bean.account.PlatformIPAccount;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.result.PlatformIPAccountResult;
import com.yuanrong.admin.seach.PlatformIpAccountSeach;
import com.yuanrong.admin.seach.PlatformIpAccountSearchMall;
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
 *@author ShiLinghuai
 *@date 2018/8/31
 *@time 18:23
 *@description
 */
@Controller
@RequestMapping("account")
public class PlatformIPAccountAgentController extends BaseController {

    /**
     *@author 师令????
     *@param
     *@date 2018/9/3
     *@description 行业分类
     */
    @RequestMapping("getCategoryList")
    @ResponseBody
    public ResultTemplate getCategoryList(PlatformIpAccountSearchMall data, BaseModel baseModel){
        data.setIsSelectAll(EnumYesOrNo.NORMAL.getIndex());
        data.setIsAgent(EnumYesOrNo.NORMAL.getIndex());
        PageInfo<PlatformIPAccountResult> result = platformIPAccountServicesI.listMall(data , baseModel);
        JSONArray resultArray = new JSONArray();
        if(CollectionUtil.size(result.getList()) > 0){
            for(PlatformIPAccountResult ele : result.getList()){
                if(ele.getDictInfoCategory()!=null){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id" , ele.getCategoryID());
                    jsonObject.put("name" , ele.getDictInfoCategory().getName());
                    boolean flag = true;
                    for(int i = 0;i<resultArray.size();i++){
                        JSONObject jsonObject1 = resultArray.getJSONObject(i);
                        if(ele.getCategoryID() == (Integer) jsonObject1.get("id")){
                            flag = false;
                        }
                    }
                    if(flag){
                        resultArray.add(jsonObject);
                    }
                }
            }
        }
        return  new ResultTemplate(result , resultArray);

    }

    /**
     *@author ShiLinghuai
     *@param
     *@date 2018/8/31
     *@description 查询代理权管理列????
     */
    @RequestMapping("getAgentList")
    @ResponseBody
    public ResultTemplate getAgentList(PlatformIpAccountSearchMall data, BaseModel baseModel){
        data.setIsAgent(EnumYesOrNo.NORMAL.getIndex());
        PageInfo<PlatformIPAccountResult> result = platformIPAccountServicesI.listMall(data , baseModel);
        JSONArray resultArray = new JSONArray();
        if(CollectionUtil.size(result.getList()) > 0){
            for(PlatformIPAccountResult ele : result.getList()){
                resultArray.add(PlatformIPAccountResult.mapParerPlatformAgent(ele));
            }
        }
        return  new ResultTemplate(result , resultArray);
    }
}
