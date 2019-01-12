package com.yuanrong.admin.mall.controller.resources;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.resources.UserImages;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 用户图片的controller
 * Created MDA
 */
@Controller
@RequestMapping("resources")
public class UserImagesController extends BaseController {
    private static final Logger logger = Logger.getLogger(UserImagesController.class);
}
