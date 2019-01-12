package com.yuanrong.admin.server.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.system.AdminRole;
import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.ResultTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("adminRole")
public class AdminRoleController extends BaseController {
    private static final Logger logger = Logger.getLogger(AdminRoleController.class);



    /**
     * 用户角色列表
     */
    @RequestMapping(value = "exs_listAdminRole")
    @ResponseBody
    public ResultTemplate listAdminRole(){
        List<AdminRole> list =   adminRoleServicesI.listAdminRole();
        List<JSONObject> rntList = new ArrayList<>();
        for(AdminRole adminRole : list){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("roleName",adminRole.getRoleName());
            jsonObject.put("roleId",adminRole.getRoleID());
            rntList.add(jsonObject);
        }
        return new ResultTemplate("",rntList);
    }
}
