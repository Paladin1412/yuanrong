package com.yuanrong.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by zhonghang on 2018/5/17.
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping("/")
    public String index( HttpSession session){
        if(getWebUser(session) == null){
            return "login";
        }
        return "index";
    }



}
