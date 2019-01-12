package com.yuanrong.admin.web.controller.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.bean.base.Lable;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.common.util.ResultTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 标签的controller
 * Created MDA
 */
@Controller
@RequestMapping("lable")
public class LableController extends BaseController {
    private static final Logger logger = Logger.getLogger(LableController.class);

    /**
     * 标签列表
     * @return
     */
    @RequestMapping( value = "exs_lableList")
    @ResponseBody
    public ResultTemplate list(String typeId){
        List<Lable> lableList = lableServicesI.list(typeId);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(lableList) >0){
            for (int i=0;i<lableList.size();i++){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id" , lableList.get(i).getId());
                jsonObject.put("name" , lableList.get(i).getLableName());
                result.add(jsonObject);
            }
        }else{
            return new ResultTemplate("数据不存在!" , null);

        }
        return new ResultTemplate("",result);
    }


}
