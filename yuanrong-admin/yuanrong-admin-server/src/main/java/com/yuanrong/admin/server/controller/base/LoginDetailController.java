package com.yuanrong.admin.server.controller.base;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.base.LoginDetail;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yuanrong.common.util.ResultTemplate;
import com.alibaba.fastjson.JSONArray;
import com.yuanrong.admin.util.CollectionUtil;
/**
 * 登录日志的controller
 * Created MDA
 */
@Controller
@RequestMapping("base")
public class LoginDetailController extends BaseController {
    private static final Logger logger = Logger.getLogger(LoginDetailController.class);
    @RequestMapping( value = "loginDetail_list" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(LoginDetail data , BaseModel baseModel){
        PageInfo loginDetailPageInfo = loginDetailServicesI.list(data , baseModel);
        JSONArray array = new JSONArray();
            if(CollectionUtil.size(loginDetailPageInfo.getList()) > 0){
                
            }
        return new ResultTemplate(loginDetailPageInfo , array);
    }
    
    @RequestMapping("loginDetail_save")
    public String save(LoginDetail user){
        
        return "base/systemUser_save";
    }
    
    @RequestMapping( value = "loginDetail_saveOk" , method = RequestMethod.POST)
    public void saveOk(LoginDetail theLoginDetail){
        loginDetailServicesI.save(theLoginDetail);
    }
    
    @RequestMapping("loginDetail_update")
    public String update(){
        return "base/loginDetail_update";
    }
    
    @RequestMapping( value = "loginDetail_updateOk"  , method = RequestMethod.POST)
    public void updateOk(){
    }
}
