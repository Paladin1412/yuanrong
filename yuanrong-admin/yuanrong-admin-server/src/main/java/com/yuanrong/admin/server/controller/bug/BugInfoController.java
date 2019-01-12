package com.yuanrong.admin.server.controller.bug;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.bug.BugInfo;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * bug信息的controller
 * Created MDA
 */
@Controller
@RequestMapping("bug")
public class BugInfoController extends BaseController {
    private static final Logger logger = Logger.getLogger(BugInfoController.class);

    @RequestMapping("ex_bugInfo_getbyId")
    public String getbyId(BugInfo data , HttpServletRequest request){
        BugInfo bugInfo = bugInfoServicesI.getById(data.getBugInfoId());
        if(bugInfo == null){
            return null;
        }
        request.setAttribute("theBugInfo" , bugInfo);
        return "bug/bugInfo_info";
    }
}
