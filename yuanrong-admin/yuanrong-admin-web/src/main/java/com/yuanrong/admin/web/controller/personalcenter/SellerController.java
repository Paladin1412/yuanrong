package com.yuanrong.admin.web.controller.personalcenter;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.bean.base.SMSValidateCode;
import com.yuanrong.admin.bean.base.SmsRecord;
import com.yuanrong.admin.bean.demand.AdvertiserReleaseRequirement;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.rpc.api.base.SmsRecordServicesI;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.common.module.shortmessage.SMSSend;
import com.yuanrong.common.util.MD5Util;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 个人中心-卖家的Controller
 * Created Tangzheng
 */
@Controller
@RequestMapping("seller")
public class SellerController extends BaseController {
    private static final Logger logger = Logger.getLogger(SellerController.class);

    /**
     * @author Tangzheng
     * @param
     * @Data 2018-5-23
     * 最新需求
     *
     */
    @RequestMapping(value = "lastDemand")
    @ResponseBody
    public ResultTemplate lastDemand(BaseModel baseModel){
        JSONObject jsonObject = new JSONObject();
        AdvertiserReleaseRequirement data = new AdvertiserReleaseRequirement() ;
//        BaseModel baseModel = new BaseModel();

        Map<String,Object> map = new HashMap<>();
        map.put("dictInfoValue",9);
        map.put("flag","yes");
        map.put("StatusValue",1);
        PageInfo<Map<String,Object>> demandPageInfo = this.advertiserReleaseRequirementServicesI.lastDemand(data, baseModel,map);
        return  new ResultTemplate("" ,demandPageInfo);
    }
    /**
     * @author Tangzheng
     * @param
     * @Data 2018-5-23
     * 统计该卖家：账号、创作者、作品
     *
     */
    @RequestMapping(value = "cnt_registeredUserInfo")
    @ResponseBody
    public ResultTemplate cnt_registeredUserInfo(HttpSession session){
        //单点登录，从getWebUser中获取registeredUserInfoID
        if(getWebUser(session)==null){
            return  new ResultTemplate("该用户未登陆");
        }
        int registeredUserInfoID = getWebUser(session).getRecID();

        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("registeredUserInfoID",registeredUserInfoID);
        Map<String,Object> rntMap = registeredUserInfoService.cnt_registeredUserInfo(map);
        jsonObject.put("cnt_registeredUserInfo",rntMap);
        return  new ResultTemplate("",jsonObject);

    }

    /**
     * @author Tangzheng
     * @param
     * @Data 2018-5-30
     * 卖家、买家：更改头图像
     *
     */
    @RequestMapping(value = "updateHeadImg")
    @ResponseBody
    public ResultTemplate updateHeadImg(RegisteredUserInfo registeredUserInfo,HttpSession session){

            if(getWebUser(session)==null){
                return  new ResultTemplate("用户未登陆");
            }
            Integer registeredUserInfoID =  getWebUser(session).getRecID();
            registeredUserInfo.setRecID(registeredUserInfoID);
            registeredUserInfoService.updateByID(registeredUserInfo);
            return new ResultTemplate("",null);

    }

    @RequestMapping(value = "updatePassWord")
    @ResponseBody
    public ResultTemplate updatePassWord(RegisteredUserInfo registeredUserInfo,String newPassword,HttpSession session){

            if(getWebUser(session)==null){
                return  new ResultTemplate("用户未登陆");
            }
            if(StringUtil.isBlank(registeredUserInfo.getPassword())){
                return  new ResultTemplate("输入的旧密码不能为空");
            }
            if(StringUtil.isBlank(newPassword)){
                return  new ResultTemplate("输入的新密码不能为空");
            }
            if(newPassword.length()<6){
                return  new ResultTemplate("新密码长度不能小于6个字符");
            }
            Integer registeredUserInfoID =  getWebUser(session).getRecID();
            registeredUserInfo.setRecID(registeredUserInfoID);
            RegisteredUserInfo rntRegisteredUserInfo = registeredUserInfoService.getById(registeredUserInfoID);
            if(!rntRegisteredUserInfo.getPassword().equals(MD5Util.md5(MD5Util.md5(registeredUserInfo.getPassword()+"#yr")))){
                return  new ResultTemplate("输入的旧密码错误");
            }
            if(rntRegisteredUserInfo.getPassword().equals(newPassword)){
                return  new ResultTemplate("输入的新密码与原密码相同");
            }

            registeredUserInfo.setPassword(MD5Util.md5(MD5Util.md5(newPassword+"#yr")));

            registeredUserInfoService.updateByID(registeredUserInfo);
            return  new ResultTemplate();

    }

    /**
     * 修改密码—通过手机验证码
     * @param userInfo
     * @return
     */
    @RequestMapping("updatePassword")
    @ResponseBody
    public ResultTemplate updatePassword(RegisteredUserInfo userInfo,HttpSession session){
        if(getWebUser(session)==null){
            return  new ResultTemplate("用户未登陆");
        }
        if(!getWebUser(session).getMobile().equals(userInfo.getMobile())){
            return  new ResultTemplate("手机号码错误");
        }
        if(StringUtil.isBlank(userInfo.getPassword())){
            return  new ResultTemplate("密码不能为空");
        }
        if(StringUtil.isBlank(userInfo.getCheckCode())){
            return  new ResultTemplate("手机验证码不能为空");
        }
        SMSValidateCode smsValidateCode =(SMSValidateCode) getSession().getAttribute("smsValidateCode");
        if(smsValidateCode==null || StringUtil.isBlank(smsValidateCode.getSmsCode())){
            return  new ResultTemplate("请先获取短信验证码!");
        }
        if(!userInfo.getCheckCode().toLowerCase().equals(smsValidateCode.getSmsCode().toLowerCase())){
            return new ResultTemplate("输入短信验证码错误！");
        }
        String rntPassword =userInfo.getPassword();
        userInfo.setPassword(MD5Util.md5(MD5Util.md5(userInfo.getPassword()+"#yr")));
        userInfo.setRecID(getWebUser(session).getRecID());

        registeredUserInfoService.updateByID(userInfo);
//        SMSSend.sendSMS("恭喜您密码修改成功，密码是：" + rntPassword + "。",
//                userInfo.getMobile());
        SmsRecord smsRecord = new SmsRecord();
        smsRecord.setMobile(userInfo.getMobile());
        smsRecord.setSource("用户修改密码");
        smsRecord.setStatus(1);
        smsRecord.setCreateTime(new Date());
//        redisTemplate.opsForValue().set("RegisteredUserInfo:p:"+list1.get(0).getRecID().intValue(),userInfo.getPassword());
        redisTemplate.opsForValue().set("RegisteredUserInfo:p:"+getWebUser(session).getRecID().intValue(),userInfo.getPassword());
        smsRecordServicesI.insertSendMessage(smsRecord,"恭喜您密码修改成功，如非本人操作，请联系圆融客服！" );

        return new ResultTemplate();
    }

}
