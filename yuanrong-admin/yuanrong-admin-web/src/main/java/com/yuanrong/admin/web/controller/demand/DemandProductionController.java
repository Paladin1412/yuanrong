package com.yuanrong.admin.web.controller.demand;

import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.common.util.ResultTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Tangz
 * @date 2018/9/7 20:54
 */
@Controller
@RequestMapping("demandProduction")
public class DemandProductionController  extends BaseController {
    private static final Logger logger = Logger.getLogger(DemandProductionController.class);

    @RequestMapping("getById")
    @ResponseBody
    public ResultTemplate getById(Integer id){

        return null;
    }

}
