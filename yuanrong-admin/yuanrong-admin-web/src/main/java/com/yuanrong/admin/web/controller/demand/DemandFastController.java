package com.yuanrong.admin.web.controller.demand;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.EnumDemandFastStatus;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.demand.DemandFast;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
/**
 * 快速需求的controller
 * Created MDA
 */
@Controller
@RequestMapping("demandFast")
public class DemandFastController extends BaseController {
    private static final Logger logger = Logger.getLogger(DemandFastController.class);

    /**
     * 快速需求列表
     * @param
     * @param baseModel`
     * @return
     */
    @RequestMapping( value = "demandFast_list" , method = RequestMethod.POST)
    @ResponseBody
//    public void list( BaseModel baseModel,String demandSn ,String startTime, String endTime){
      public ResultTemplate list(BaseModel baseModel, DemandFast data , HttpSession session){
        if(getWebUser(session)==null){
            return new ResultTemplate("该用户未登录",null);
            
        }
        int registeredUserInfoId =  getWebUser(session).getRecID();
        data.setRegisteredUserInfoId(registeredUserInfoId);
        PageInfo<JSONObject> pageInfo = demandFastServicesI.list(data , baseModel);
        JSONArray jsonArray = new JSONArray();
        if(CollectionUtil.size(pageInfo.getList())>0){
            for(JSONObject jsonObject : pageInfo.getList()){
                JSONObject object = new JSONObject();
                object.put("createdTime",jsonObject.getString("createdTime"));
                object.put("demandFastStatus",jsonObject.getString("demandFastStatus"));
                object.put("demandFastSn",jsonObject.getString("demandSn"));
                object.put("nickname",jsonObject.getString("nickname"));
                object.put("refuseReason",jsonObject.getString("refuseReason"));
                object.put("demandType",jsonObject.getString("demandType"));
                object.put("demandSn",jsonObject.getString("demand_SN"));
                object.put("demandTypeIndex",jsonObject.getString("demandTypeIndex"));
                object.put("demandFastStatusIndex",jsonObject.getString("demandFastStatusIndex"));
                object.put("demandId",jsonObject.getString("demandId"));
                jsonArray.add(object);
            }
        }

        return new ResultTemplate( pageInfo,jsonArray);
        
    }


}
