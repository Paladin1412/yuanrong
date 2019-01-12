package com.yuanrong.admin.web.controller.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.common.util.ResultTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhonghang on 2018/5/22.
 */
@Controller
@RequestMapping("dictInfo")
public class DictInfoController extends BaseController {
    @RequestMapping("getDictInfoByType")
    @ResponseBody
    public ResultTemplate getDictInfoByType(DictInfo dictInfo){
        if(dictInfo == null || dictInfo.getDictInfoType().getIndex() < 0){
            return new ResultTemplate("参数错误" , null);

        }
        List<DictInfo> result =  dictInfoServicesI.getDictInfoByType(dictInfo.getDictInfoType().getIndex());
        if(CollectionUtil.size(result) <= 0){
            return new ResultTemplate("没有查到数据" , null);
        }else{
            return new ResultTemplate("" ,DictInfo.pareToJSONObject(result));
        }
    }
}
