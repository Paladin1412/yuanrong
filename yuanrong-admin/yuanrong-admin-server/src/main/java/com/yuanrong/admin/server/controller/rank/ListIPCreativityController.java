package com.yuanrong.admin.server.controller.rank;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.rank.ListIPCreativity;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.ResultTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("listIPCreativity")
public class ListIPCreativityController  extends BaseController {
    private static final Logger logger = Logger.getLogger(ListArticleController.class);
    /**
     * IP创作者榜单
     * @param
     * @return
     */
    @RequestMapping(value = "exs_selectAllListIPCreativity")
    @ResponseBody
    public ResultTemplate selectAllListIPCreativity(HttpServletRequest request, BaseModel baseModel){
        try{
            String typeName = request.getParameter("typeName");
            String rangeTime =  request.getParameter("rangeTime");
            String statusValue = request.getParameter("statusValue");
            Map<String,Object> map = new HashMap<>();
            map.put("rangeTime",rangeTime);
            map.put("typeName",typeName);
            map.put("statusValue",statusValue);
            map.put("showNum",150);

            PageInfo<Map<String,Object>> ipCreativityPageInfo  = listIPCreativityServicesI.selectAllListIPCreativity(baseModel,map);
            return new ResultTemplate(ipCreativityPageInfo);
        }catch (Exception e){
            e.getMessage();
            return new ResultTemplate("获取IP创作力榜单数据出错",null);
        }

    }

    /**
     * 更新 文章榜单
     * @param listIPCreativity
     */
    @RequestMapping(value = "exs_updateListIPCreativity")
    @ResponseBody
    public ResultTemplate updateListIPCreativity(ListIPCreativity listIPCreativity){
        if(listIPCreativity.getRecID()==null){
            return new ResultTemplate("ip创作力Id不能为null",null);
        }
        try {
            listIPCreativityServicesI.updateListIPCreativity(listIPCreativity);
            return new ResultTemplate("",null);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResultTemplate("更改文章榜单失败",null);
        }
    }
}
