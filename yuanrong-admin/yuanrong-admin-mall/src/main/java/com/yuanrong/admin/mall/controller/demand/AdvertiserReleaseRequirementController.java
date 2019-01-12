package com.yuanrong.admin.mall.controller.demand;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.demand.AdvertiserReleaseRequirement;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.ResultTemplate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName AdvertiserReleaseRequirementController
 * @Author Tangzheng
 * @Date 2018/6/2
 */
@Controller
@RequestMapping("advertiserReleaseRequirement")
public class AdvertiserReleaseRequirementController extends BaseController {
    private static final Logger logger = Logger.getLogger(AdvertiserReleaseRequirementController.class);

    /**
     *  圆融内容银行 发布需求
     * @param ScenesID 使用场景
     * @param ProType 内容形式
     * @param CategoryID 目标领域
     * @param ModifyNum 修改次数
     * @param Validity 完成天数
     * @param Author  意向创作者
     * @param AuthorID  意向创作者ID
     * @param ProName 需求名称
     * @param ProDescription 需求描述
     * @param ProBudget  需求预算
     * @param IPhoneNum 手机号码
     * @param VerCode 手机验证码
     * @param CreateTime 创建时间
     */
    @RequestMapping(value = "addReleaseRequirement", method = { RequestMethod.POST, RequestMethod.GET, RequestMethod.HEAD })
    @ResponseBody
    public ResultTemplate addReleaseRequirement(Integer ScenesID, Integer ProType, Integer CategoryID, Integer ModifyNum,
                                                Integer Validity, String Author, Integer AuthorID, String ProName,
                                                String ProDescription, BigDecimal ProBudget,
                                                String IPhoneNum, String VerCode, String CreateTime)
    {

//        Integer ScenesID =  Integer.parseInt( request.getParameter("ScenesID"));
//        Integer ProType =  Integer.parseInt( request.getParameter("ProType"));
//        Integer CategoryID =  Integer.parseInt( request.getParameter("CategoryID"));
//        Integer ModifyNum =  Integer.parseInt( request.getParameter("ModifyNum"));
//        Integer Validity =  Integer.parseInt( request.getParameter("Validity"));
//        String Author  = request.getParameter("Author");
//        Integer AuthorID =  Integer.parseInt( request.getParameter("AuthorID"));
//        String ProName  = request.getParameter("ProName");
//        String ProDescription  = request.getParameter("ProDescription");
//        BigDecimal ProBudget = BigDecimal.valueOf(Double.parseDouble(request.getParameter("ProBudget")));
//        String IPhoneNum  = request.getParameter("IPhoneNum");
//        String VerCode  = request.getParameter("VerCode");

        //判断用户是否登录，如果登录则获取用户ID
        Integer RegisteredUserInfoID = null;
        if(getWebUser()!=null){
            RegisteredUserInfoID = getWebUser().getRecID();
        }

        Map<String,Object> map = new HashMap<>();
        map.put("RecID",null);
        map.put("CreateTime", DateUtil.getNowDateTime());
        map.put("LastModifyTime",DateUtil.getNowDateTime());
        map.put("StatusValue",1);

        map.put("ScenesID",ScenesID);
        map.put("ProType",ProType);
        map.put("CategoryID",CategoryID);
        map.put("ModifyNum",ModifyNum);
        map.put("Validity",Validity);
        map.put("Author",Author);
        map.put("AuthorID",AuthorID);
        map.put("ProName",ProName);
        map.put("ProDescription",ProDescription);
        map.put("ProBudget",ProBudget);
        map.put("IPhoneNum",IPhoneNum);
        map.put("VerCode",VerCode);
        map.put("RegisteredUserInfoID",RegisteredUserInfoID);

        Map<String, Object> regMap =new HashMap<String, Object>();
        regMap.put("Mobile",request.getParameter("IPhoneNum"));
//        regMap.put("UserName",request.getParameter("UserName"));
        regMap.put("PageSource","分布需求");

        try {
            //插入的主键
            int affectRowsKey =  advertiserReleaseRequirementServicesI.addReleaseRequirement(map);

            //先判断手机号码是否存在，若存在不保存。
            int countMobile = registeredUserInfoService.getCountMobile(regMap);
            if( countMobile < 1 ){
                int regRows = registeredUserInfoService.addRegisteredUserInfo(regMap);
            }
            if(affectRowsKey > 0){
                // 返回的主键 和 内容形式 的关系插入到AdvertiserReleaseRequirement_contentForm
                Map<String,Object> keyMap = new HashMap<>();
                keyMap.put("advertiserReleaseRequirementId",affectRowsKey);
                keyMap.put("contentFormId",ProType);
                advertiserReleaseRequirementServicesI.insertAdvertiserReleaseRequirement_contentForm(keyMap);

                return new ResultTemplate("",null);
            }else {
                return new ResultTemplate("发布需求失败",null);
            }

        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return new ResultTemplate("发布需求失败",null);
    }


    /**
     * 获取需求列表
     * @author Tangzheng
     * @param
     * @Data 2018-5-23
     * 最新需求
     *
     */
    @RequestMapping(value = "getDemandHall")
    @ResponseBody
    public ResultTemplate lastDemand(BaseModel baseModel){
        AdvertiserReleaseRequirement data = new AdvertiserReleaseRequirement() ;
        Map<String,Object> map = new HashMap<>();
//        map.put("dictInfoValue",9);
        map.put("flag","yes");
        map.put("StatusValue",1);
        PageInfo<Map<String,Object>> demandPageInfo = this.advertiserReleaseRequirementServicesI.lastDemand(data, baseModel,map);
        return new ResultTemplate(demandPageInfo);
    }




}
