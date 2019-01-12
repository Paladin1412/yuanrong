package com.yuanrong.admin.mall.controller.base;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.base.LoginDetail;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
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

}
