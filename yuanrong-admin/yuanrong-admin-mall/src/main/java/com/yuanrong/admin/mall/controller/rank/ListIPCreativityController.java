package com.yuanrong.admin.mall.controller.rank;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("listIPCreativity")
public class ListIPCreativityController  extends BaseController {

    /**
     * IP创作者榜单
     * @param
     * @return
     */
    @RequestMapping(value = "selectAllListIPCreativity")
    @ResponseBody
    public  ResultTemplate   selectAllListIPCreativity(HttpServletRequest request, BaseModel baseModel){
        String typeName = request.getParameter("typeName");
        String rangeTime =  request.getParameter("rangeTime");
    //            String statusValue = request.getParameter("statusValue");
        Map<String,Object> map = new HashMap<>();
        map.put("typeName",typeName);
        map.put("rangeTime",rangeTime);
        map.put("statusValue",1);
        map.put("showNum",50);
        PageInfo<Map<String,Object>> ipCreativityPageInfo  = listIPCreativityServicesI.selectAllListIPCreativity(baseModel,map);
        return new ResultTemplate(ipCreativityPageInfo);

    }


}
