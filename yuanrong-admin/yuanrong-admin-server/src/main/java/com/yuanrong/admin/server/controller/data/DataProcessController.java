package com.yuanrong.admin.server.controller.data;

import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.common.util.ResultTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

/**
 * Created by zhonghang on 2018/10/24.
 */
@Controller
@RequestMapping("data")
public class DataProcessController extends BaseController {
    /*@RequestMapping("ex_deal/V1.7Data")
    @ResponseBody
    public ResultTemplate dealV1_7Data() throws ParseException {
        dataProcessServicesI.updateDataProcessingV1_6();
        return new ResultTemplate();
    }*/
}
