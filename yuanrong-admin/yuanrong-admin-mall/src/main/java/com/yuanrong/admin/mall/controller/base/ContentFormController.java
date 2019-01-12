package com.yuanrong.admin.mall.controller.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumYesOrNo;
import com.yuanrong.admin.bean.base.ContentForm;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.ResultTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内容形式的controller
 * Created MDA
 */
@Controller
@RequestMapping("contentForm")
public class ContentFormController extends BaseController {
    private static final Logger logger = Logger.getLogger(ContentFormController.class);

    /**
     * 内容形式列表
     * @return
     */
    @RequestMapping( value = "contentFormList")
    @ResponseBody
    public ResultTemplate list(){
        List<ContentForm> contentFormList = contentFormServicesI.list(EnumYesOrNo.NORMAL.getIndex());
        JSONArray result = new JSONArray();
        if(CollectionUtil.size(contentFormList) >0){
            result.addAll(ContentForm.packageContentForm(contentFormList));
        }else{
            return new ResultTemplate("数据不存在!" , null);
        }
        return new ResultTemplate("" , result);
    }

}
