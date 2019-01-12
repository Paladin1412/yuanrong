package com.yuanrong.admin.mall.controller.demand;

import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.bean.demand.DemandProduction;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.common.util.ResultTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Tangz
 * @date 2018/9/7 20:04
 */
@Controller
@RequestMapping("demandProduction")
public class DemandProductionController  extends BaseController {
    private static final Logger logger = Logger.getLogger(DemandProductionController.class);

    @RequestMapping("getById")
    @ResponseBody
    public ResultTemplate getById(Integer id){
        if(id==null ||id<1){
            return  new ResultTemplate("参数错误",null);
        }

        DemandProduction demandProduction = demandProductionServicesI.getById(id);
        if(demandProduction==null){
            return  new ResultTemplate("id不存在",null);
        }
        JSONObject result = new JSONObject();
        result.put("demandId",demandProduction.getDemandId());
        return  new ResultTemplate("",result);
    }
}
