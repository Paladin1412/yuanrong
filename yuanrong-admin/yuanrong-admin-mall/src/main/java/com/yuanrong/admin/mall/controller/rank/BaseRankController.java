package com.yuanrong.admin.mall.controller.rank;

import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("baseRank")
public class BaseRankController extends BaseController {

    @RequestMapping(value = "selectRangeTime", method = { RequestMethod.POST, RequestMethod.GET, RequestMethod.HEAD })
    @ResponseBody
    public ResultTemplate selectRangeTime (HttpServletRequest request,Integer statusValue){
        String typeName = request.getParameter("typeName");
        if(StringUtil.isBlank(typeName)){
            return new ResultTemplate("typeName不能为空，请选择 listArticle、listIPCreativity",null);
        }
        if(statusValue==null){
            statusValue = 1;
        }
        if("listArticle".equals(typeName)){
            List<String> rntList = listArticleServicesI.selectRangeTimeListArticle(statusValue);
            return new ResultTemplate("",rntList);

        }else if("listIPCreativity".equals(typeName)){
            List<String> rntList =  listIPCreativityServicesI.selectRangeTimeListIPCreativity(statusValue);
            return new ResultTemplate("",rntList);
        }
        return new ResultTemplate("参数错误");
    }
}
