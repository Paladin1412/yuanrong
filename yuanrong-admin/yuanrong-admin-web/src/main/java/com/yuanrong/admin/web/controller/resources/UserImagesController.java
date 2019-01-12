package com.yuanrong.admin.web.controller.resources;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.resources.UserImages;
import com.yuanrong.admin.seach.UserImagesSearch;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 用户图片的controller
 * Created MDA
 */
@Controller
@RequestMapping("resources")
public class UserImagesController extends BaseController {
    private static final Logger logger = Logger.getLogger(UserImagesController.class);

    @RequestMapping(value = "getMyImages" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getMyImages(BaseModel baseModel, HttpSession session){
        UserImagesSearch data = new UserImagesSearch();
        data.setWebUser(getWebUser(session));
        PageInfo<UserImages> userImagesPageInfo = userImagesServicesI.list(data , baseModel);
        return  new ResultTemplate(userImagesPageInfo , UserImages.getArray(userImagesPageInfo.getList()));
    }
}
