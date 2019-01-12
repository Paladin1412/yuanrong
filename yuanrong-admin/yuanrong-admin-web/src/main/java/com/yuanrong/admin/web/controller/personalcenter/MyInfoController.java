package com.yuanrong.admin.web.controller.personalcenter;


import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumUserCurrentRole;
import com.yuanrong.admin.Enum.EnumUserSellerAndBuyerUserType;
import com.yuanrong.admin.Enum.EnumUserRoleLicenseStatus;
import com.yuanrong.admin.bean.base.SMSValidateCode;
import com.yuanrong.admin.bean.base.UserInfo;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.seach.RegisteredUserAndCompany;
import com.yuanrong.admin.web.controller.BaseController;

import com.yuanrong.common.module.shortmessage.SMSSend;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 个人中心-我的信息Controller
 * Created by Tangzheng
 */
@Controller
@RequestMapping("myInfo")
public class MyInfoController extends BaseController {

    private static final Logger logger = Logger.getLogger(MyInfoController.class);

    /**
     * @param
     * @author ShiLinghuai
     * @Data 2018-5-23
     * 完善我的信息： 依据ID查询信息
     */
    @RequestMapping(value = "userMyInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getMyInfoByID(RegisteredUserAndCompany registeredUserAndCompany, HttpSession session) {
        if (registeredUserAndCompany.getCurrentRole() == null) {
            return new ResultTemplate("currentRole字段为空", null);
        }
        UserInfo userInfo = getWebUser(session);
        registeredUserAndCompany.setRecID(userInfo.getRecID());
        JSONObject jsonObject = registeredUserInfoService.getUserDetailByIDFront(registeredUserAndCompany.getRecID(), registeredUserAndCompany);
        return new ResultTemplate("", jsonObject);
    }

    /**
     * 前台个人中心，依据用户ID，更新个人相关信息
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/26 15:31
     */
    @RequestMapping(value = "updateMyInfoRegisterUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate updateMyInfoRegisterUserInfo(RegisteredUserAndCompany registeredUserAndCompany,HttpSession session) {
        if(registeredUserAndCompany.getFromWhichCenter()==null){
            return new ResultTemplate("fromWhichCenter参数没有传", null);
        }else {
            if(registeredUserAndCompany.getFromWhichCenter()==EnumUserCurrentRole.当前身份买家.getIndex()){
                if(registeredUserAndCompany.getBuyerUserType()==null){
                    return new ResultTemplate("买家类型没有传", null);
                }
            }
            if(registeredUserAndCompany.getFromWhichCenter()==EnumUserCurrentRole.当前身份卖家.getIndex()){
                if(registeredUserAndCompany.getSellerUserType()==null){
                    return new ResultTemplate("卖家类型没有传", null);
                }
            }
        }
        UserInfo userInfo = getWebUser(session);
        //赋值主键
        if (userInfo != null) {
            registeredUserAndCompany.setRecID(userInfo.getRecID());
        } else {
            return new ResultTemplate("当前用户未登录", null);
        }
        //如果要更新的身份状态是审核失败或者null才可以更新(null，查询返回来的是注册成功)
        //从数据库里获取该用户的信息
        RegisteredUserInfo registeredUserInfo = registeredUserInfoService.getById(userInfo.getRecID());
        if(registeredUserInfo.getSellerStatus()!=null){
            if(registeredUserInfo.getSellerStatus().equals(EnumUserRoleLicenseStatus.待审核.getIndex())||
                    registeredUserInfo.getSellerStatus().equals(EnumUserRoleLicenseStatus.审核成功.getIndex())){
                return new ResultTemplate("当前用户身份为待审核或审核成功不能更改信息", null);
            }
        }
        //设置currentRole字段
        registeredUserAndCompany.setCurrentRole(EnumUserCurrentRole.双身份.getIndex());
        //设置将要更新的用户身份为待审核
        registeredUserAndCompany.setBuyerStatusValue(EnumUserRoleLicenseStatus.待审核.getIndex());
        registeredUserAndCompany.setSellerStatusValue(EnumUserRoleLicenseStatus.待审核.getIndex());
        registeredUserAndCompany.setSellerAskAudit(new Timestamp(new Date().getTime()));
        registeredUserAndCompany.setBuyerAskAudit(new Timestamp(new Date().getTime()));
        if (registeredUserAndCompany.getSellerUserType() != null) {
            registeredUserAndCompany.setSellerUserType(registeredUserAndCompany.getSellerUserType());
            registeredUserAndCompany.setBuyerUserType(registeredUserAndCompany.getSellerUserType());
        }
        if (registeredUserAndCompany.getBuyerUserType() != null) {
            registeredUserAndCompany.setSellerUserType(registeredUserAndCompany.getBuyerUserType());
            registeredUserAndCompany.setBuyerUserType(registeredUserAndCompany.getBuyerUserType());
        }

        if (registeredUserAndCompany.getFromWhichCenter() == EnumUserCurrentRole.当前身份买家.getIndex()) {
            if (registeredUserAndCompany.getBuyerUserType() == EnumUserSellerAndBuyerUserType.个人.getIndex()) {
                this.registeredUserInfoService.updatePersonUserThirdVersion(registeredUserAndCompany);
                return new ResultTemplate();
            } else {
                this.registeredUserInfoService.updateCompanyUserThirdVersion(registeredUserAndCompany);
                return new ResultTemplate("", null);

            }
        } else if (registeredUserAndCompany.getFromWhichCenter() == EnumUserCurrentRole.当前身份卖家.getIndex()) {
            if (registeredUserAndCompany.getSellerUserType() == EnumUserSellerAndBuyerUserType.个人.getIndex()) {
                this.registeredUserInfoService.updatePersonUserThirdVersion(registeredUserAndCompany);
                return new ResultTemplate();

            } else {
                this.registeredUserInfoService.updateCompanyUserThirdVersion(registeredUserAndCompany);
                return new ResultTemplate();
            }
        }
        return new ResultTemplate();
    }



    /**
     * @param ssmCode 短验证码；imgCode 图像验证码；type=0时只验证，type=1时只验证并保存，
     * @author Tangzheng
     * @Data 2018-5-30
     * 前台我的信息：更改手机号
     * 更新手机：
     * 1）、新手机号不能是原手机号
     * 2）、新手机号不能再表里面存在
     */
    @RequestMapping(value = "updateRegisteredUserMobile", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate  updateRegisteredUserMobile(RegisteredUserInfo registeredUserInfo,HttpSession session, String ssmCode, String imgCode, Integer type, String replaceMobile) {
        if (getWebUser(session) == null) {
            return new ResultTemplate("用户未登陆");
        }
        //RegisteredUserInfo set 用户ID
        registeredUserInfo.setRecID(getWebUser(session).getRecID());

        if (registeredUserInfo.getRecID() == null) {
            return new ResultTemplate("无用户ID，该用户没有登录");
        }
        if (type == 0) {

            if (registeredUserInfo.getMobile() == null) {
                return new ResultTemplate("手机号参数错误");
            }
        }
        if (ssmCode == null) {
            return new ResultTemplate("短信验证参数错误");
        }
//        if( imgCode==null){
//            writeString("图像参数错误",null);
//            return;
//        }
        if (type == null) {
            return new ResultTemplate("类型参数错误");
        }

        boolean flag = false;
        //从session中获取短信验证码
        SMSValidateCode smsValidateCode = (SMSValidateCode) getSession().getAttribute("smsValidateCode");
        if(smsValidateCode==null || StringUtil.isBlank(smsValidateCode.getSmsCode())){
            return  new ResultTemplate("请先获取短信验证码!");
        }
        //从session中获取图像验证码
//        String imgValidateCode =(String) getSession().getAttribute("getImgValidateCode");

        if (type == 0) {
            if (!smsValidateCode.getMobile().equals(registeredUserInfo.getMobile())) {
                String rntStr = "发短信验证码的手机号:" + smsValidateCode.getMobile() +
                        "，不是该注册号码:" + registeredUserInfo.getMobile() + "!";
                return new ResultTemplate(rntStr);
            }

        } else if (type == 1) {
            if (!smsValidateCode.getMobile().equals(replaceMobile)) {
                String rntStr = "发短信验证码的手机号:" + smsValidateCode.getMobile() +
                        "，不是该需要更新的号码:" + replaceMobile + "!";
                return new ResultTemplate(rntStr);
            }
        }

        // 验证从Session中获取的短信验证码是否等于用户输入的验证码
        if (!smsValidateCode.getSmsCode().toLowerCase().equals(ssmCode.toLowerCase())) {
            return new ResultTemplate("短信验证码错误");
        }
        // 验证从Session中获取的图像验证码是否等于用户输入的验证码
//        if(!imgValidateCode.toLowerCase().equals(imgCode.toLowerCase())){
//            writeString("图像验证码错误",null);
//            return;
//        }
        if (type == 0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("validate", true);
            return new ResultTemplate("", jsonObject);
        } else if (type == 1) {
            // 更新手机号：验证新手机号不为空、不等于原手机号
            if (replaceMobile == null) {
                return new ResultTemplate("新手机号不为空");
            }

            boolean isMobile = SMSSend.isMobile(replaceMobile);

            if (!isMobile) {
                return new ResultTemplate("新手机号格式错误");
            }

            if (registeredUserInfo.getMobile().equals(replaceMobile)) {
                return new ResultTemplate("更换新手机号不能和旧手机号相同");
            }

            // 更换的新手机号，不能再库中存在
            Map<String, Object> mapReplaceMobile = new HashMap<>();
            mapReplaceMobile.put("Mobile", replaceMobile);
            mapReplaceMobile.put("replaceMobile", replaceMobile);
            mapReplaceMobile.put("recID", registeredUserInfo.getRecID());
            int cnt = registeredUserInfoService.getCountMobile(mapReplaceMobile);
            if (cnt > 0) {
                return new ResultTemplate("更换的新手机号已注册");
            }
//            try {
                int cntMobile = registeredUserInfoService.updateMobileByRecID(mapReplaceMobile);
                JSONObject jsonObject = new JSONObject();
                if (cntMobile > 0) {
                    jsonObject.put("updateMobile", true);
                    return new ResultTemplate("", jsonObject);
                } else {
                    jsonObject.put("updateMobile", false);
//                    writeString("更新手机号失败", jsonObject);
                    return new ResultTemplate("更新手机号失败");
                }
//            } catch (Exception e) {
//                return new ResultTemplate("更新失败");
//            }
        }
        return new ResultTemplate();
    }


}
