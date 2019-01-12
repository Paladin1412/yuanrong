package com.yuanrong.admin.mall.controller.base;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.bean.base.Lable;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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
    @RequestMapping( value = "lableList")
    @ResponseBody
    public ResultTemplate list(String typeId){
        List<Lable> lableList = lableServicesI.list(null);
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(lableList) >0){
            result.addAll(Lable.packageLable(lableList));
        }else{
            return new ResultTemplate("数据不存在!" , null);
        }
        return new ResultTemplate("",result);
    }

    /**
     * 根据作者数量获取创作风格字典
     */
    @RequestMapping("authorLable")
    @ResponseBody
    public ResultTemplate authorLable(){
        JSONObject result = new JSONObject();
        JSONArray contentStyle = new JSONArray();
        //根据作者数量获取创作风格字典
        List<Map<String, Object>> contentStyleList = lableServicesI.getAuthorContentStyle();
        if(CollectionUtils.size(contentStyleList) <1){
            return new ResultTemplate("内容领域不存在",null);
        }
        contentStyle.addAll(contentStyleList);
        result.put("contentStyle" , contentStyle);
        return new ResultTemplate("",result);
    }
}
