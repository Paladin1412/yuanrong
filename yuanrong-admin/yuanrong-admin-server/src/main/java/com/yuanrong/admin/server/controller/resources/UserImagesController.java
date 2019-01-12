package com.yuanrong.admin.server.controller.resources;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.base.UserInfo;
import com.yuanrong.admin.bean.resources.UserImages;
import com.yuanrong.admin.seach.UserImagesSearch;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.ResultTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户图片的controller
 * Created MDA
 */
@Controller
@RequestMapping("resources")
public class UserImagesController extends BaseController {
    private static final Logger logger = Logger.getLogger(UserImagesController.class);

    @RequestMapping(value = "exs_getMyImages" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getMyImages(Integer registerId , BaseModel baseModel){
        if(registerId == null || registerId <= 0){
            return new ResultTemplate("参数错误" ,null);
        }
        UserImagesSearch data = new UserImagesSearch();
        UserInfo userInfo = new UserInfo(registerId , null , null);
        data.setWebUser(userInfo);
        PageInfo<UserImages> userImagesPageInfo = userImagesServicesI.list(data , baseModel);
        return new ResultTemplate(userImagesPageInfo , UserImages.getArray(userImagesPageInfo.getList()));
    }
}
