package com.yuanrong.admin.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhonghang on 2018/4/17.
 */
@Controller
public class IndexController extends BaseController {
    @RequestMapping(value = "/index" , method = RequestMethod.GET)
    public String index(){
        if(getUser() == null){
            return "login";
        }
        return "index";
    }

    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }
}
