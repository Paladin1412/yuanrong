package com.yuanrong.admin.mall.controller.upload;

import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.common.util.ResultTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhonghang on 2018/7/5.
 */
@Controller
@RequestMapping("upload")
public class UploadController extends BaseController {
    @RequestMapping(value="/uploadToken")
    @ResponseBody
    public String getUploadToken(){
        return uploadServicesI.getUploadToken();
    }
}
