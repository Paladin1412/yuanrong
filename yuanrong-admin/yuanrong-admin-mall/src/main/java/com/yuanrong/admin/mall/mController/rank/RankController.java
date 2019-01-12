package com.yuanrong.admin.mall.mController.rank;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhonghang on 2018/08/28.
 */
@Controller
@RequestMapping("rank")
public class RankController extends BaseController{
    @RequestMapping("index")
    public String rankIndex(){
        return "m/mRank/index";
    }

    @RequestMapping("description")
    public String rankDscription(){
        return "m/mRank/description";
    }

    /**
     * 移动端 --文章榜单
     * @param request
     * @param baseModel
     */
    @RequestMapping(value = "selectAllListArticle_M", method = { RequestMethod.POST, RequestMethod.GET, RequestMethod.HEAD })
    @ResponseBody
    public ResultTemplate selectAllListArticle_M(HttpServletRequest request, BaseModel baseModel){
        String typeName = request.getParameter("categoryName");
        String rangeTime =  request.getParameter("rangeTime");
//            String statusValue = request.getParameter("statusValue");
        Map<String,Object> map = new HashMap<>();
        map.put("statusValue",1);
        map.put("typeName",typeName);
        map.put("rangeTime",rangeTime);
        map.put("showNum",50);
        JSONArray jsonArray = new JSONArray();
        PageInfo<Map<String,Object>> listArticlePageInfo  =   listArticleServicesI.selectAllListArticle(baseModel,map);
        if(!CollectionUtil.isEmpty(listArticlePageInfo.getList())){
            for(int i=0 ; i<listArticlePageInfo.getList().size() ;i++){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("title",listArticlePageInfo.getList().get(i).get("Title"));
                jsonObject.put("link",listArticlePageInfo.getList().get(i).get("Link"));
                jsonObject.put("totalIndex",listArticlePageInfo.getList().get(i).get("TotalIndex"));
                jsonArray.add(jsonObject);
            }
        }
        return new ResultTemplate(listArticlePageInfo,jsonArray);

    }

    /**
     * 移动端--IP创作者榜单、、
     * @param
     * @return
     */
    @RequestMapping(value = "selectAllListIPCreativity_M")
    @ResponseBody
    public  ResultTemplate   selectAllListIPCreativity_M(HttpServletRequest request, BaseModel baseModel){
        String typeName = request.getParameter("categoryName");
        String rangeTime =  request.getParameter("rangeTime");
        //            String statusValue = request.getParameter("statusValue");
        Map<String,Object> map = new HashMap<>();
        map.put("statusValue",1);
        map.put("typeName",typeName);
        map.put("rangeTime",rangeTime);
        map.put("showNum",50);
        PageInfo<Map<String,Object>> ipCreativityPageInfo  = listIPCreativityServicesI.selectAllListIPCreativity(baseModel,map);

        JSONArray jsonArray = new JSONArray();
        if(!CollectionUtil.isEmpty(ipCreativityPageInfo.getList())){
            for(int i=0 ; i<ipCreativityPageInfo.getList().size() ;i++){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("headImg",ipCreativityPageInfo.getList().get(i).get("HeadImg"));
                jsonObject.put("totalIndex",ipCreativityPageInfo.getList().get(i).get("TotalIndex"));
                jsonObject.put("weixinId",ipCreativityPageInfo.getList().get(i).get("weixinId"));
                jsonObject.put("name",ipCreativityPageInfo.getList().get(i).get("Name"));
                jsonArray.add(jsonObject);
            }
        }
        return new ResultTemplate(ipCreativityPageInfo,jsonArray);

    }
}
