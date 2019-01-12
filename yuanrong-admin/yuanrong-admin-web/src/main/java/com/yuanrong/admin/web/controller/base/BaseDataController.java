package com.yuanrong.admin.web.controller.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumPlatformIPAccountStatus;
import com.yuanrong.admin.bean.account.ShortVideoPlatformInfo;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.common.util.ResultTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhonghang on 2018/5/29.
 */
@Controller
@RequestMapping("base")
public class BaseDataController extends BaseController{
    @RequestMapping("getPlatFormInfo")
    @ResponseBody
    public ResultTemplate getPlatFormInfo(){
        List<ShortVideoPlatformInfo> result = platformIPAccountServicesI.getPlatFormInfo();
        JSONArray jsonArray = new JSONArray();
        if(CollectionUtil.size(result) <= 0){
            return new ResultTemplate("没有平台数据！",null);
        }else{
            for(ShortVideoPlatformInfo shortVideoPlatformInfo : result){
                JSONObject object = new JSONObject();
                object.put("id" , shortVideoPlatformInfo.getRecid());
                object.put("name" , shortVideoPlatformInfo.getPlatformname());
                object.put("icoUrl" , shortVideoPlatformInfo.getIcoUrl());
                jsonArray.add(object);
            }
            return new ResultTemplate("" , jsonArray);
        }
    }

    @RequestMapping("getAccountStatus")
    @ResponseBody
    public ResultTemplate getAccountStatus(){
        return new ResultTemplate("" , EnumPlatformIPAccountStatus.getMapInfo());
    }
    /**
    * 获取
    * @author      ShiLinghuai
    * @param
    * @return      
    * @exception   
    * @date        2018/6/1 18:16
    */
    @RequestMapping("exs_getPublishStatus")
    @ResponseBody
    public ResultTemplate contentFormList(){
        return new ResultTemplate("" , EnumPlatformIPAccountStatus.getMapInfo());
    }
}
