package com.yuanrong.admin.web.controller.config;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.config.Configuration;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
/**
 * 系统配置的controller
 * Created MDA
 */
@Controller
@RequestMapping("config")
public class ConfigurationController extends BaseController {
    private static final Logger logger = Logger.getLogger(ConfigurationController.class);

}
