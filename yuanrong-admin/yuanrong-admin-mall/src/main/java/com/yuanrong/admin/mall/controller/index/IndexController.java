package com.yuanrong.admin.mall.controller.index;

import com.yuanrong.admin.mall.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhonghang on 2018/6/29.
 */
@Controller
public class IndexController extends BaseController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
