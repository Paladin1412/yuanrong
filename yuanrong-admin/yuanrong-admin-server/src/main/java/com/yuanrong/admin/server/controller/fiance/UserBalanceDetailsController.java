package com.yuanrong.admin.server.controller.fiance;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.fiance.UserBalanceDetails;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yuanrong.common.util.ResultTemplate;

/**
 * 用户余额流水的controller
 * Created MDA
 */
@Controller
@RequestMapping("fiance")
public class UserBalanceDetailsController extends BaseController {
    private static final Logger logger = Logger.getLogger(UserBalanceDetailsController.class);

    @RequestMapping(value = "userBalanceDetails_list", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate list(UserBalanceDetails data, BaseModel baseModel) {
        PageInfo userBalanceDetailsPageInfo = userBalanceDetailsServicesI.list(data, baseModel);
        JSONArray array = new JSONArray();
        if (CollectionUtil.size(userBalanceDetailsPageInfo.getList()) > 0) {
        }
        return new ResultTemplate(userBalanceDetailsPageInfo, array);
    }

    @RequestMapping("userBalanceDetails_save")
    public String save(UserBalanceDetails user) {

        return "fiance/systemUser_save";
    }

    @RequestMapping(value = "userBalanceDetails_saveOk", method = RequestMethod.POST)
    public void saveOk(UserBalanceDetails theUserBalanceDetails) {
        userBalanceDetailsServicesI.save(theUserBalanceDetails);
    }

    @RequestMapping("userBalanceDetails_update")
    public String update() {
        return "fiance/userBalanceDetails_update";
    }

    @RequestMapping(value = "userBalanceDetails_updateOk", method = RequestMethod.POST)
    public void updateOk() {
    }
}
