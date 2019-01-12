package com.yuanrong.admin.mall.controller.rank;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.mall.controller.resources.UserImagesController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
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
public class ListArticleController extends BaseController{
    private static final Logger logger = Logger.getLogger(ListArticleController.class);
    /**
     * 文章榜单
     * @param request
     * @param baseModel
     */
    @RequestMapping(value = "selectAllListArticle", method = { RequestMethod.POST, RequestMethod.GET, RequestMethod.HEAD })
    @ResponseBody
    public ResultTemplate selectAllListArticle(HttpServletRequest request,BaseModel baseModel){
        String typeName = request.getParameter("typeName");
        String rangeTime =  request.getParameter("rangeTime");
//            String statusValue = request.getParameter("statusValue");
        Map<String,Object> map = new HashMap<>();
        map.put("typeName",typeName);
        map.put("rangeTime",rangeTime);
        map.put("statusValue",1);
        map.put("showNum",50);

        PageInfo<Map<String,Object>> listArticlePageInfo  =   listArticleServicesI.selectAllListArticle(baseModel,map);
        return new ResultTemplate(listArticlePageInfo);

    }


}
