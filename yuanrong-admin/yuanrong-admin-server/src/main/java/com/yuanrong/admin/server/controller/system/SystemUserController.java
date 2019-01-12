package com.yuanrong.admin.server.controller.system;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.system.SystemMenu;
import com.yuanrong.admin.bean.system.SystemUser;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhonghang on 2018/4/11.
 */
@Controller
@RequestMapping("system")
public class SystemUserController extends BaseController {
    private static final Logger logger = Logger.getLogger(SystemUserController.class);

    @RequestMapping(value = "ex_login" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate login(String username , String password){
        SystemUser systemUser = systemUserServicesI.login(username , password);
        if(systemUser == null){
            return new ResultTemplate("用户名或密码错误" ,null);
        }else{
            getSession().setAttribute("admin" , systemUser);
            List<SystemMenu> menuList = systemUserServicesI.getMenuInfosBySystemUser(systemUser);
            getSession().setAttribute("menus" ,menuList );
            return new ResultTemplate("" , null);
        }
    }
    @RequestMapping(value = "registerSystemUser" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate registerSystemUser(SystemUser systemUser){
        if(StringUtil.isBlank(systemUser.getPassword()) || StringUtil.isBlank(systemUser.getPassword())
                || StringUtil.isBlank(systemUser.getRealName())){
            return new ResultTemplate("参数错误" ,null);
        }
        systemUserServicesI.save(systemUser);
        return new ResultTemplate("" ,null);
    }

    @RequestMapping("ex_logout")
    public String logout(){
        getSession().invalidate();
        return "redirect:/";
    }

    @RequestMapping("systemUser_list")
    @ResponseBody
    public ResultTemplate list(SystemUser data , BaseModel baseModel){
        /*if(StringUtil.isNoneBlank(getRequest().getParameter("first"))){
            return "system/systemUser_list";
        }*/
        PageInfo<SystemUser> systemUserPageInfo = systemUserServicesI.list(data , baseModel);
        return new ResultTemplate("" , systemUserPageInfo);
    }


    @RequestMapping( value = "systemUser_saveOk" , method = RequestMethod.POST)
    public void saveOk(SystemUser theSystemUser){
        systemUserServicesI.save(theSystemUser);
    }


}
