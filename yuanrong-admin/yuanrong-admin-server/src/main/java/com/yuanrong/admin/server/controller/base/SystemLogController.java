package com.yuanrong.admin.server.controller.base;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.base.SystemLog;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 系统日志的controller
 * Created MDA
 */
@Controller
@RequestMapping("base")
public class SystemLogController extends BaseController {
    private static final Logger logger = Logger.getLogger(SystemLogController.class);

}
