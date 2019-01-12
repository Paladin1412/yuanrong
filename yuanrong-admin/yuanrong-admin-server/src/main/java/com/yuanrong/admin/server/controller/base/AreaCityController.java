package com.yuanrong.admin.server.controller.base;

import com.yuanrong.admin.bean.base.AreaCity;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhonghang on 2018/4/28.
 */
@Controller
@RequestMapping("areaCity")
public class AreaCityController extends BaseController {
    @RequestMapping("exs_getByParentId")
    @ResponseBody
    public ResultTemplate getByParentId(String id){
        List<AreaCity> result = areaCityServicesI.getAreaCityByParentID(id);
        if(CollectionUtil.size(result) <= 0){
            return new ResultTemplate("没有查到数据" , null);
        }else{
            return new ResultTemplate("" , result);
        }
    }
}
