package com.yuanrong.admin.server.controller.rank;


import com.github.pagehelper.PageInfo;

import com.yuanrong.admin.bean.rank.ListArticle;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.ResultTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("listArticle")
public class ListArticleController extends BaseController {
    private static final Logger logger = Logger.getLogger(ListArticleController.class);
    /**
     * 文章榜单
     * @param request
     * @param baseModel
     */
    @RequestMapping(value = "exs_selectAllListArticle", method = { RequestMethod.POST, RequestMethod.GET, RequestMethod.HEAD })
    @ResponseBody
    public ResultTemplate selectAllListArticle(HttpServletRequest request, BaseModel baseModel){
        try{
            String rangeTime =  request.getParameter("rangeTime");
            String typeName = request.getParameter("typeName");
            String statusValue = request.getParameter("statusValue");
            Map<String,Object> map = new HashMap<>();
            map.put("typeName",typeName);
            map.put("rangeTime",rangeTime);
            map.put("statusValue",statusValue);
            map.put("showNum",150);

            PageInfo<Map<String,Object>> listArticlePageInfo  =   listArticleServicesI.selectAllListArticle(baseModel,map);
            return new ResultTemplate(listArticlePageInfo);
        }catch(Exception e){
            logger.error(e.getMessage());
            return new ResultTemplate("获取文章榜单出错",null);
        }

    }

     /**
     * 更新 文章榜单
     * @param listArticle
     */
     @RequestMapping(value = "exs_updateListArticle")
     @ResponseBody
    public ResultTemplate updateListArticle(ListArticle listArticle){
        if(listArticle.getRecID()==null){
            return new ResultTemplate("您传的参数不对",null);
        }
        try {
            listArticleServicesI.updateListArticle(listArticle);
            return new ResultTemplate("",null);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResultTemplate("更改文章榜单失败",null);
        }
    }

}
