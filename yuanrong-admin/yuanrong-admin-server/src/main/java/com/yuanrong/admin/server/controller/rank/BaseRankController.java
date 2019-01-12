package com.yuanrong.admin.server.controller.rank;


import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.server.controller.resources.UserImagesController;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("baseRank")
public class BaseRankController extends BaseController {
    private static final Logger logger = Logger.getLogger(UserImagesController.class);
    @RequestMapping(value = "exs_selectRangeTime", method = { RequestMethod.POST, RequestMethod.GET, RequestMethod.HEAD })
    @ResponseBody
    public ResultTemplate selectRangeTime (HttpServletRequest request,Integer statusValue){
        String rankName = request.getParameter("rankName");
        if(StringUtil.isBlank(rankName)){
            return new ResultTemplate("您传的参数不对" , null);
        }
        if(statusValue==null){
            statusValue = 1;
        }
        if("listArticle".equals(rankName)){
            List<String> rntList = listArticleServicesI.selectRangeTimeListArticle(statusValue);
            return new ResultTemplate("",rntList);
        }else if("listIPCreativity".equals(rankName)){
            List<String> rntList =  listIPCreativityServicesI.selectRangeTimeListIPCreativity(statusValue);
            return new ResultTemplate("",rntList);
        }
        return null;
    }
}
