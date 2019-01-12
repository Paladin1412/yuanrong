package com.yuanrong.admin.server.controller.base;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.base.IPLable;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * IP标签的controller
 * Created MDA
 */
@Controller
@RequestMapping("ipLable")
public class IPLableController extends BaseController {
    private static final Logger logger = Logger.getLogger(IPLableController.class);

    /**
     * 获取IP标签列表
     * @param data
     * @param baseModel
     */
    @RequestMapping( value = "exs_iPLableList")
    @ResponseBody
    public ResultTemplate list(IPLable data , BaseModel baseModel){

        PageInfo<IPLable> iPLablePageInfo = iPLableServicesI.list(data , baseModel);
        return new ResultTemplate("" , iPLablePageInfo);
    }

    /**
     * 通过parentID获取IPLabe
     * @param parentId
     */
    @RequestMapping("exs_iPLable")
    @ResponseBody
    public ResultTemplate getByParentId(String parentId){
        List<IPLable> resultLable = iPLableServicesI.getIPLableByParentId(parentId);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(resultLable) >0){
            for (IPLable cont : resultLable){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id" , cont.getRecID());
                jsonObject.put("name" , cont.getTagName());
                result.add(jsonObject);
            }
        }else{
            return new ResultTemplate("数据不存在!" , null);
        }
        return new ResultTemplate("",result);
    }

    @RequestMapping( value = "iPLable_saveOk" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate saveOk(IPLable theIPLable){
        iPLableServicesI.save(theIPLable);
        return new ResultTemplate();
    }

}
