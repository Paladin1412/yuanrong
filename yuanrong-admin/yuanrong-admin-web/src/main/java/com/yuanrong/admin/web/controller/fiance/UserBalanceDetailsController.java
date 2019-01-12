package com.yuanrong.admin.web.controller.fiance;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.fiance.UserBalanceDetails;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yuanrong.common.util.ResultTemplate;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户余额流水的controller
 * Created MDA
 */
@Controller
@RequestMapping("fiance")
public class UserBalanceDetailsController extends BaseController {
    private static final Logger logger = Logger.getLogger(UserBalanceDetailsController.class);

}
