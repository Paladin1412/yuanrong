package com.yuanrong.admin.web.controller.account;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.bean.account.ShortVideoPlatformInfo;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by zhonghang on 2018/5/22.
 */
@Controller
@RequestMapping("account")
public class platformIPAccountPriceNameRelationController extends BaseController {
    @RequestMapping("getByShortVideoPlatformInfoID")
    @ResponseBody
    public ResultTemplate getByShortVideoPlatformInfoID(String platformInfoIDS){
        if(StringUtil.isBlank(platformInfoIDS)){
            return  new ResultTemplate("平台参数为空！" , null);
            
        }
        int[] ids = null;
        //校验数据
        try{
            String[] idstr = platformInfoIDS.split(",");
            ids = new int[idstr.length];
            for(int i = 0 ; i < idstr.length  ; i++){
                ids[i] = Integer.parseInt(idstr[i]);
            }
            List<Map<String , Object>> result = platformIPAccountPriceNameRelationServicesI.getByShortVideoPlatformInfoIDs(ids);
            return  new ResultTemplate("" , result);
        }catch (Exception e){
            return  new ResultTemplate("平台参数错误，请以逗号分隔！" , null);
            
        }
    }
}
