package com.yuanrong.admin.web.controller.personalcenter;

import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.demand.AdvertiserReleaseRequirement;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.common.util.ResultTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人中心-买家的Controller
 * Created Tangzheng
 */
@Controller
@RequestMapping("buyer")
public class BuyerController extends BaseController {
    private static final Logger logger = Logger.getLogger(BuyerController.class);

    /**
     * @author Tangzheng
     * @param
     * @Data 2018-5-23
     * 需求列表
     *
     */
    @RequestMapping(value = "demandList")
    @ResponseBody
    public ResultTemplate demandList(AdvertiserReleaseRequirement data, BaseModel baseModel, HttpSession session){
        Integer RegisteredUserInfoID = null;
        if(getWebUser(session)!=null){
            RegisteredUserInfoID = getWebUser(session).getRecID();
        }
        Map<String,Object> map = new HashMap<>();
        //传入一个值标识：不需要围观人数图像
        map.put("flag","no");
        map.put("StatusValue",0);
        map.put("RegisteredUserInfoID",RegisteredUserInfoID);
        PageInfo<Map<String,Object>>   demandPageInfo = this.advertiserReleaseRequirementServicesI.lastDemand(data, baseModel,map);
        return new ResultTemplate("",demandPageInfo);
    }




}
