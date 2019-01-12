package com.yuanrong.admin.server.controller.usermanagement;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yuanrong.admin.Enum.*;
import com.yuanrong.admin.bean.base.DictInfo;
import com.yuanrong.admin.bean.base.SmsRecord;
import com.yuanrong.admin.bean.demand.Demand;
import com.yuanrong.admin.bean.demand.DemandFast;
import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserExtCompany;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.exception.ParmException;
import com.yuanrong.admin.rpc.exception.YRParamterException;
import com.yuanrong.admin.seach.BatchUpdateSaleOrMedia;
import com.yuanrong.admin.seach.RegisterUserSearch;
import com.yuanrong.admin.seach.RegisteredUserAndCompany;
import com.yuanrong.admin.server.controller.BaseController;
import com.yuanrong.admin.util.BaseModel;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.admin.util.RegisteredUserUtil;
import com.yuanrong.common.util.*;
import com.yuanrong.common.module.shortmessage.SMSSend;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: ShiLinghuai
 * @CreateDate: 2018/5/7 11:49
 * @UpdateUser: ShiLinghuai
 * @UpdateDate: 2018/5/7 11:49
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Controller
@RequestMapping("userManagement")
public class RegisteredUserInfoController extends BaseController {
    private static final Logger logger = Logger.getLogger(RegisteredUserInfoController.class);

    /**
     * 批量审核失败
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/9 10:45
     */
    @RequestMapping(value = "exs_batchCheckedFail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate batchCheckedFail(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia) {
        //解析前端传来的字符串id为List类型的ids
        List<Integer> list = new ArrayList<Integer>();
        try {
            String[] idstr = batchUpdateSaleOrMedia.getId().split(",");
            for (int i = 0; i < idstr.length; i++) {
                list.add(Integer.parseInt(idstr[i]));
            }
            batchUpdateSaleOrMedia.setIdss(list);
            //验证这些id的参数是否齐全，如果齐全进行批量通过操作。----业务处理
            this.registeredUserInfoService.batchCheckedFaid(batchUpdateSaleOrMedia);
            return new ResultTemplate("", null);
        } catch (NullPointerException e) {
            return new ResultTemplate("id参数未传");
        } catch (ParmException e) {
            return new ResultTemplate(e.getData().getMsg(), null);
        } catch (Exception e) {
            return new ResultTemplate("系统异常", null);
        }

    }

    /**
     * 批量审核通过
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/9 10:45
     */
    @RequestMapping(value = "exs_batchCheckedPass", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate batchCheckedPass(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia) {
        //解析前端传来的字符串id为List类型的ids
        List<Integer> list = new ArrayList<Integer>();
        String[] idstr = batchUpdateSaleOrMedia.getId().split(",");
        for (int i = 0; i < idstr.length; i++) {
            list.add(Integer.parseInt(idstr[i]));
        }
        batchUpdateSaleOrMedia.setIdss(list);
        //验证这些id的参数是否齐全，如果齐全进行批量通过操作。----业务处理
        registeredUserInfoService.batchCheck(batchUpdateSaleOrMedia.getIdss(), EnumUserRoleLicenseStatus.审核成功.getIndex());
        return new ResultTemplate("", null);

    }

    /**
     * 获取用户状态
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/9 10:45
     */
    @RequestMapping(value = "exs_getUserStatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate getUserStatus(RegisteredUserAndCompany registeredUserAndCompany) {
        return new ResultTemplate("", EnumUserRoleLicenseStatus.getMapInfo());
    }

    /**
     * 详情
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/10 9:53
     */
    @RequestMapping(value = "getUserDetailByID", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate getUserDetailByID(RegisteredUserAndCompany registeredUserAndCompany) {
        if (registeredUserAndCompany.getRecID() == null) {
            return new ResultTemplate("参数错误", null);
        }
        JSONObject data = this.registeredUserInfoService.getUserDetailByID(registeredUserAndCompany.getRecID());
        if (data == null) {
            return new ResultTemplate("此用户数据库中数据异常", null);
        } else {
            return new ResultTemplate("", data);

        }


    }

    private ResultTemplate updateBySellerUserType(RegisteredUserAndCompany registeredUserAndCompany) {
        //判断卖家类型是什么
        //如果是企业保存企业
        //如果是个人保存个人
        if (registeredUserAndCompany.getSellerUserType() == EnumUserSellerAndBuyerUserType.个人.getIndex()) {
            this.registeredUserInfoService.updatePersonUserThirdVersion(registeredUserAndCompany);
            return new ResultTemplate("", null);

        } else {
            this.registeredUserInfoService.updateCompanyUserThirdVersion(registeredUserAndCompany);
            return new ResultTemplate("", null);
        }
    }

    private ResultTemplate updateByBuyerUserType(RegisteredUserAndCompany registeredUserAndCompany) {
        //判断买家类型是什么
        //如果是企业保存企业
        //如果是个人保存个人
        if (registeredUserAndCompany.getBuyerUserType() == EnumUserSellerAndBuyerUserType.个人.getIndex()) {
            this.registeredUserInfoService.updatePersonUserThirdVersion(registeredUserAndCompany);
            return new ResultTemplate("", null);

        } else {
            this.registeredUserInfoService.updateCompanyUserThirdVersion(registeredUserAndCompany);
            return new ResultTemplate("", null);
        }
    }


    /**
     * 批量更换销售或媒介
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/7 11:48
     */
    @RequestMapping(value = "batchUpdateSaleOrMedia", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate batchUpdateSaleOrMedia(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia) {
        //验证数据真实性
        Integer[] ids = null;
        String[] idstr = batchUpdateSaleOrMedia.getId().split(",");

        ids = new Integer[idstr.length];
        for (int i = 0; i < idstr.length; i++) {
            ids[i] = Integer.parseInt(idstr[i]);
        }
        batchUpdateSaleOrMedia.setIds(ids);
        this.registeredUserInfoService.batchUpdateSaleOrMediaForUser(batchUpdateSaleOrMedia);
        return new ResultTemplate("", null);


    }

    /**
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/7 15:34
     */
    @RequestMapping(value = "getAllSaleOrMedia", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate getAllSaleOrMedia(Integer roleID) {
        //验证数据真实性
        List<AdminUser> list = this.registeredUserInfoService.findListByRoleID(roleID);
        JSONArray jsonArray = new JSONArray();
        if (CollectionUtil.size(list) > 0) {
            for (AdminUser adminUser : list) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", adminUser.getRecID());
                jsonObject.put("name", adminUser.getRealName());
                jsonArray.add(jsonObject);
            }
        }
        return new ResultTemplate("", jsonArray);


    }

    /**
     * 批量重置密码
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/7 11:48
     */
    @RequestMapping(value = "batchResetPassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate batchResetPassword(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia) {
        //验证数据真实性
        Integer[] ids = null;
        String[] idstr = batchUpdateSaleOrMedia.getId().split(",");
        ids = new Integer[idstr.length];
        for (int i = 0; i < idstr.length; i++) {
            ids[i] = Integer.parseInt(idstr[i]);
        }
        batchUpdateSaleOrMedia.setIds(ids);
        batchUpdateSaleOrMedia.setResetPassword(EncryptUtil.encryptString("666666"));
        this.registeredUserInfoService.batchResetPassword(batchUpdateSaleOrMedia);
        return new ResultTemplate("", null);


    }

    /**
     * 按条件查询出用户的信息
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/7 11:48
     */
    @RequestMapping(value = "listByCondition", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate listByCondition(RegisterUserSearch registerUserSearch, BaseModel baseModel) {
        try {
            RegisteredUserUtil.paramDeal(registerUserSearch);
        }catch (NumberFormatException e){
            return new ResultTemplate("userID过长，请重新输入！",null);
        }        registerUserSearch.setCurrLoginUser(getUser());
        JSONArray data = new JSONArray();
        baseModel.setFields("ru.RecID desc");
        PageInfo<RegisteredUserInfo> pageInfo = this.registeredUserInfoService.listByCondition(registerUserSearch, baseModel);
        if (CollectionUtil.size(pageInfo.getList()) > 0) {
            for (RegisteredUserInfo registeredUserInfo : pageInfo.getList()
                    ) {
                data.add(RegisteredUserInfo.packageRegisteredUser(registeredUserInfo));
            }
        }
        return new ResultTemplate(pageInfo, data);

    }


    /**
     * 保存用户（企业用户）
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/7 11:48
     */
    @RequestMapping(value = "saveUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate saveUser(RegisteredUserAndCompany registeredUserAndCompany) {
        //检查参数完整性
        if (!LogicUtil.isAllHasValue(registeredUserAndCompany.getUserMobile(), registeredUserAndCompany.getNickName(),
                registeredUserAndCompany.getRole(), registeredUserAndCompany.getUserType(), registeredUserAndCompany.getCheckCode())) {
            return new ResultTemplate("基础信息数据不完整，请检查必填项", null);
        } else {
            if (registeredUserAndCompany.getUserType().equals(EnumUserType.个人.getIndex())) {
                //检查个人信息的参数完整性
                if (!LogicUtil.isAllHasValue(registeredUserAndCompany.getRealName())) {
                    return new ResultTemplate("realName参数未传", null);
                }
            } else {
                //检查企业信息参数的完整性
                if (!LogicUtil.isAllHasValue(registeredUserAndCompany.getCompanyName(),
                        registeredUserAndCompany.getLeaderName(), registeredUserAndCompany.getRoleID())) {
                    return new ResultTemplate("企业信息必填项不完整，请检查必填项", null);
                }
            }
        }

        //验证保存用户的参数
        if (StringUtil.isBlank(registeredUserAndCompany.getUserMobile())) {
            return new ResultTemplate("参数错误", null);
        } else if (!validatesmsCode(registeredUserAndCompany, request)) {
            return new ResultTemplate("验证码非法", null);
        } else {
            Integer countPhone = this.registeredUserInfoService.getCountPhone(registeredUserAndCompany.getUserMobile());
            if (countPhone >= 1) {
                return new ResultTemplate("该手机已注册", null);
            }
        }
        ;
        //设置currentRole和卖家买家类型
        setCurrentRoleAndBuyerTypeSellerType(registeredUserAndCompany);
        //设置审核状态
        com.yuanrong.admin.util.RegisteredUserUtil.setCheckStatusByType(registeredUserAndCompany);
        //根据用户的chushi
        AdminUser adminUser = getUser();
        //为买家和卖家赋销售和媒介
        setMideaAndSalesByAdmin(registeredUserAndCompany, adminUser);
        registeredUserAndCompany.setAdminUserCreaterID(adminUser.getRecID());
        //密码加密
        //registeredUserAndCompany.setPassword( MD5Util.md5(MD5Util.md5("666666"+"#yr")));
        String passwordEncrypt = SMSSend.getRandomSix();
        registeredUserAndCompany.setPassword(EncryptUtil.encryptString(passwordEncrypt));
        registeredUserInfoService.saveUser(registeredUserAndCompany);
        //发送短信
        String mobile = registeredUserAndCompany.getUserMobile();
        String MessageCotent = "欢迎您注册圆融平台www.yuanrongbank.com，手机号是：" + registeredUserAndCompany.getUserMobile() + "，密码是：" + passwordEncrypt + "。";
        try {
            MessageCotent = URLEncoder.encode(MessageCotent, "UTF-8");
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        SmsRecord smsRecord = new SmsRecord();
        smsRecord.setMobile(mobile);
        smsRecord.setCreateTime(new Date());
        smsRecord.setSource("后台添加用户");
        Boolean sendSuccessFlag = smsRecordServicesI.insertSendMessage(smsRecord,MessageCotent);
        //发送结果
        if (sendSuccessFlag) {

            return new ResultTemplate("", null);
        } else {
            return new ResultTemplate("短信发送失败");
        }


    }

    private void setMideaAndSalesByAdmin(RegisteredUserAndCompany registeredUserAndCompany, AdminUser adminUser) {
        if (adminUser.getAdminRoleID() == EnumAdminUserRole.销售经理.getIndex()) {
            registeredUserAndCompany.setAdminUserSalesID(adminUser.getRecID());
            registeredUserAndCompany.setAdminUserMediaID(Integer.parseInt(configurationServicesI.getbyKey(SystemParam.MEDIA_ID)));

        } else if (adminUser.getAdminRoleID() == EnumAdminUserRole.媒介经理.getIndex()) {
            registeredUserAndCompany.setAdminUserMediaID(adminUser.getRecID());
            registeredUserAndCompany.setAdminUserSalesID(Integer.parseInt(configurationServicesI.getbyKey(SystemParam.SALE_ID)));
        } else {
            registeredUserAndCompany.setAdminUserSalesID(Integer.parseInt(configurationServicesI.getbyKey(SystemParam.SALE_ID)));
            registeredUserAndCompany.setAdminUserMediaID(Integer.parseInt(configurationServicesI.getbyKey(SystemParam.MEDIA_ID)));
        }


    }

    private void setCurrentRoleAndBuyerTypeSellerType(RegisteredUserAndCompany registeredUserAndCompany) {
        registeredUserAndCompany.setBuyerUserType(registeredUserAndCompany.getUserType());
        registeredUserAndCompany.setSellerUserType(registeredUserAndCompany.getUserType());
        registeredUserAndCompany.setCurrentRole(EnumUserCurrentRole.双身份.getIndex());

    }


    /**
     * 获取公司职位列表
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/7 11:48
     */
    @RequestMapping(value = "getCompanyPositionList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate getCompanyPositionList() {
        List<DictInfo> list = this.registeredUserInfoService.gainCompanyPositionList();
        JSONArray jsonArray = new JSONArray();
        if (CollectionUtil.size(list) > 0) {
            for (DictInfo dictInfo : list) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", dictInfo.getId());
                jsonObject.put("name", dictInfo.getName());
                jsonObject.put("memo", dictInfo.getMemo());
                jsonObject.put("status", dictInfo.getStatus());
                jsonArray.add(jsonObject);
            }
        }
        return new ResultTemplate("", jsonArray);

    }

    /**
     * 发送短信
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/7 11:48
     */
    @RequestMapping(value = "sendMessage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate sendMessage(String mobile, HttpServletRequest request) {
        if(mobile==null){
            return new ResultTemplate("手机号必传", null);
        }
        HttpSession session = request.getSession();
        boolean isMobile = SMSSend.isMobile(mobile);
        if (!isMobile) {
            return new ResultTemplate("手机号格式非法", null);
        }
        Integer countPhone = this.registeredUserInfoService.getCountPhone(mobile);
        if (countPhone >= 1) {
            return new ResultTemplate("该手机已注册", null);
        }
        String smsCode = SMSSend.getRandom();
        String MessageCotent = "验证码 " + smsCode + " 有效期为5分钟，验证码仅用于本次圆融账号注册、登录使用,如非本人操作请忽略，本条信息免费。";
        try {
            MessageCotent = URLEncoder.encode(MessageCotent, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            return new ResultTemplate("短信文本字符格式转换错误", null);
        }
        SmsRecord smsRecord = new SmsRecord();
        smsRecord.setMobile(mobile);
        smsRecord.setCreateTime(new Date());
        smsRecord.setSource("后台添加圆融用户验证码");
        Boolean sendSuccessFlag = smsRecordServicesI.insertSendMessage(smsRecord,MessageCotent);
        if (sendSuccessFlag) {
            session.setAttribute("phone", mobile);
            session.setAttribute("smsCode", smsCode);
            return new ResultTemplate("", null);
        } else {
            return new ResultTemplate("短信发送失败", null);
        }

    }

    /**
     * 验证短信
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/7 11:49
     */
    @RequestMapping(value = "validateCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate validateCode(String mobile, String code, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionSmsCode = (String) session.getAttribute("smsCode");
        String phone = (String) session.getAttribute("phone");
        if (LogicUtil.allAnd(StringUtils.equalsIgnoreCase(code, sessionSmsCode),
                StringUtils.equalsIgnoreCase(phone, mobile))) {
            return new ResultTemplate("", null);
        } else {
            return new ResultTemplate("验证码非法", null);
        }
    }

    public boolean validatesmsCode(RegisteredUserAndCompany registeredUserAndCompany, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionSmsCode = (String) session.getAttribute("smsCode");
        String phone = (String) session.getAttribute("phone");
        String checkCode = registeredUserAndCompany.getCheckCode();
        String mobile = registeredUserAndCompany.getUserMobile();
        if (StringUtils.equalsIgnoreCase(checkCode, sessionSmsCode) && StringUtils.equalsIgnoreCase(phone, mobile)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 获取当前角色--（后台用户管理二期）
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/7 11:48
     */
    @RequestMapping(value = "exs_getCurrentRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate getCurrentRole(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia) {
        return new ResultTemplate("", EnumUserCurrentRole.getMapInfo());
    }


    /**
     * 验证简称
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/7 11:49
     */
    @RequestMapping(value = "exs_validateNickName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate validateNickName(String nickName, Integer recID) {
        if (StringUtils.isBlank(nickName)) {
            //如果是空直接返回
            return new ResultTemplate("", null);
        } else {
            //查重
            int count = registeredUserInfoService.getCountNickName(nickName, recID);
            if (count >= 1) {
                return new ResultTemplate("用户简称重复", null);
            } else {
                return new ResultTemplate("", null);
            }
        }


    }

    /**
     * 通过用户简称获取用户
     *
     * @param userInfo
     */
    @RequestMapping(value = "getUserByName", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getUserByName(RegisteredUserInfo userInfo) {

        List<RegisteredUserInfo> userInfos = registeredUserInfoService.getUserByName(userInfo);
        JSONArray result = new JSONArray();
        if (CollectionUtil.size(userInfos) > 0) {
            for (RegisteredUserInfo ele : userInfos) {
                JSONObject object = new JSONObject();
                object.put("userId", ele.getRecID());
                object.put("userName", ele.getNickName());
                result.add(object);
            }
        }
        return new ResultTemplate("", result);
    }

    @RequestMapping(value = "exs_getById")
    @ResponseBody
    public ResultTemplate getById(Integer id) {
        if (id == null) {
            return new ResultTemplate("参数Id不能为null", null);
        }

        RegisteredUserInfo registeredUserInfo = registeredUserInfoService.getById(id);
        if(registeredUserInfo==null){
            return new ResultTemplate("该id不存在", null);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", registeredUserInfo.getUserName());
        jsonObject.put("userNickName", registeredUserInfo.getNickName());
        jsonObject.put("mobile", registeredUserInfo.getMobile());
        return new ResultTemplate("", jsonObject);

    }


    /**
     * 审核
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/9 10:45
     */
    @RequestMapping(value = "checkUserInformation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate checkUserInformation(RegisteredUserAndCompany registeredUserAndCompany) {
        if (!LogicUtil.isAllHasValue(registeredUserAndCompany.getRecID(), registeredUserAndCompany.getSellerStatusValue())) {
            return new ResultTemplate("请检查必填项", null);
        } else {

        }
        logger.info("为"+registeredUserAndCompany.getRecID()+"审核,审核状态为"+registeredUserAndCompany.getSellerStatusValue()+"。审核失败原因"+registeredUserAndCompany.getSellerCheckFailedReasonID());
        RegisteredUserInfo registeredUserInfoSelected = registeredUserInfoService.getById(registeredUserAndCompany.getRecID());
        if(registeredUserInfoSelected.getSellerStatusValue()!=null){
            if(!registeredUserInfoSelected.getSellerStatusValue().equals(EnumUserRoleLicenseStatus.待审核.getIndex())){
                return new ResultTemplate("用户不需要审核",null);
            }
        }
        //已有卖家状态了，再更新下买家。
        registeredUserAndCompany.setBuyerStatusValue(registeredUserAndCompany.getSellerStatusValue());
        registeredUserAndCompany.setBuyerCheckFailedReasonID(registeredUserAndCompany.getSellerCheckFailedReasonID());
        //设置审核人
        registeredUserAndCompany.setBuyerAuditUser(getUser().getRealName());
        registeredUserAndCompany.setSellerAuditUser(getUser().getRealName());
        if (registeredUserAndCompany.getSellerStatusValue().equals(EnumUserRoleLicenseStatus.审核成功.getIndex())) {
            Boolean flag = registeredUserInfoService.canAuditSuccess(registeredUserAndCompany.getRecID());
            if(flag==false){
                return new ResultTemplate("身份证已于审核成功的人重复",null);
            }
        }
        this.registeredUserInfoService.updatePersonUserThirdVersion(registeredUserAndCompany);
        RegisteredUserInfo registeredUserInfo = registeredUserInfoService.getById(registeredUserAndCompany.getRecID());
        String mobile = registeredUserInfo.getMobile();
        String MessageCotent = "";
        if (registeredUserAndCompany.getSellerStatusValue().equals(EnumUserRoleLicenseStatus.审核失败.getIndex())) {
            MessageCotent += "亲爱的用户，您在圆融平台的注册信息审核不通过，请登录www.yuanrongbank.com查看详情。";
            systemLogServicesI.log(RegisteredUserInfo.class.getName(),registeredUserAndCompany.getRecID() ,"将用户状态修改为"+EnumUserRoleLicenseStatus.审核失败.getName()+",失败原因："+registeredUserAndCompany.getSellerCheckFailedReasonID(),getUser().getRealName());
        } else {
            MessageCotent += "亲爱的用户，您在圆融平台的注册信息审核通过，请登录www.yuanrongbank.com查看详情。";
            systemLogServicesI.log(RegisteredUserInfo.class.getName(),registeredUserAndCompany.getRecID() ,"将用户状态修改为"+EnumUserRoleLicenseStatus.审核成功.getName(),getUser().getRealName());
        }
        try {
            MessageCotent = URLEncoder.encode(MessageCotent, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return new ResultTemplate("短信文本字符格式转换错误", null);
        }
        SmsRecord smsRecord = new SmsRecord();
        smsRecord.setMobile(mobile);
        smsRecord.setCreateTime(new Date());
        smsRecord.setSource("后台审核用户");
        boolean sendSuccessFlag = smsRecordServicesI.insertSendMessage(smsRecord, MessageCotent);
        if (sendSuccessFlag) {

        } else {
            return new ResultTemplate("短信发送失败", null);
        }
        return new ResultTemplate("", null);
    }

    @RequestMapping(value = "auditList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate auditList(RegisterUserSearch registerUserSearch, BaseModel baseModel) {
        if (registerUserSearch.getStatusValue() == null) {
            return new ResultTemplate("statusValue未传", null);
        }
        try {
            RegisteredUserUtil.paramDeal(registerUserSearch);
        } catch (NumberFormatException e) {
            return new ResultTemplate("userID过长，请重新输入！", null);
        }
        baseModel.setFields("ru.sellerAskAudit desc,ru.RecID desc");
        PageInfo<RegisteredUserInfo> pageInfo = this.registeredUserInfoService.listByCondition(registerUserSearch, baseModel);
        JSONArray data = new JSONArray();
        if (CollectionUtil.size(pageInfo.getList()) > 0) {
            for (RegisteredUserInfo registeredUserInfo : pageInfo.getList()
                    ) {
                data.add(RegisteredUserInfo.packageRegisteredUserAudit(registeredUserInfo));
            }
        }
        return new ResultTemplate(pageInfo, data);
    }


    @RequestMapping(value = "exs_getbyNickName", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate getbyNickName(String nickName) {
        if (StringUtil.isBlank(nickName)) {
            return new ResultTemplate("用户昵称为空", null);
        }
        List<RegisteredUserInfo> registeredUserInfos = registeredUserInfoService.getbyNickName(nickName);
        JSONArray jsonArray = new JSONArray();
        if (CollectionUtil.size(registeredUserInfos) > 0) {
            for (RegisteredUserInfo registeredUserInfo : registeredUserInfos) {
                JSONObject ele = new JSONObject();
                ele.put("id", registeredUserInfo.getRecID());
                ele.put("nickName", registeredUserInfo.getNickName());
                jsonArray.add(ele);
            }
        }
        return new ResultTemplate("", jsonArray);
    }

    /**
     * 更新用户信息
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/9 10:45
     */
    @RequestMapping(value = "updateUserInformation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate update(RegisteredUserAndCompany registeredUserAndCompany) {
        AdminUser adminUser = getUser();
        //验证参数是否传对
        if (registeredUserAndCompany.getBuyerUserType() == null && registeredUserAndCompany.getSellerUserType() == null) {
            return new ResultTemplate("buyerUserType和sellerUserType都为null", null);
        } else if (registeredUserAndCompany.getBuyerUserType() != null && registeredUserAndCompany.getSellerUserType() != null) {
            return new ResultTemplate("buyerUserType和sellerUserType都传了", null);
        } else if (registeredUserAndCompany.getRecID() == null) {
            return new ResultTemplate("主键未传", null);
        } else if (registeredUserAndCompany.getSellerStatusValue() != null || registeredUserAndCompany.getBuyerStatusValue() != null) {
            return new ResultTemplate("传了审核状态", null);
        }
        //判断是否需要更改审核状态
        boolean auditFlag = registeredUserInfoService.isNeedChangeAuditStatus(registeredUserAndCompany);
        //不涉及到身份信息的更改
        if (!auditFlag) {
            //更新买家
            if (registeredUserAndCompany.getBuyerUserType() != null) {
                return updateByBuyerUserType(registeredUserAndCompany);
            } else {
                return updateBySellerUserType(registeredUserAndCompany);
            }
        } else {
            //涉及到身份信息的更改
            //更新买家
            if (registeredUserAndCompany.getBuyerUserType() != null) {
                com.yuanrong.admin.util.RegisteredUserUtil.setCheckStatusByBSType(registeredUserAndCompany);
                registeredUserAndCompany.setSellerUserType(registeredUserAndCompany.getBuyerUserType());
                return updateByBuyerUserType(registeredUserAndCompany);
            } else {
                //进入卖家类型逻辑
                com.yuanrong.admin.util.RegisteredUserUtil.setCheckStatusByBSType(registeredUserAndCompany);
                registeredUserAndCompany.setBuyerUserType(registeredUserAndCompany.getSellerUserType());
                return updateBySellerUserType(registeredUserAndCompany);
            }
        }


    }



    @RequestMapping(value = "exs_updatePassWord")
    @ResponseBody
    public ResultTemplate updatePassWord(AdminUser adminUser,String newPassword){
        //验证参数
        if(StringUtil.isBlank(adminUser.getRecID().toString())){
            return  new ResultTemplate("用户id不能为空");
        }
        if(StringUtil.isBlank(adminUser.getPassWord())){
            return  new ResultTemplate("旧密码不能为空");
        }
        if(StringUtil.isBlank(newPassword)&& newPassword.length()>6){
            return  new ResultTemplate("新密码不能为空/字符长度大于6");
        }
        AdminUser rntAdminUser = adminUserServicesI.getById(adminUser.getRecID());
        //输入的密码是否正确
        if(!MD5Util.md5(MD5Util.md5(adminUser.getPassWord()+"#yr")).equals(rntAdminUser.getPassWord())){
            return  new ResultTemplate("输入的旧密码错误");
        }
        if(MD5Util.md5(MD5Util.md5(newPassword+"#yr")).equals(rntAdminUser.getPassWord())){
            return  new ResultTemplate("新密码与旧密码相同");
        }
        adminUser.setPassWord(MD5Util.md5(MD5Util.md5(newPassword+"#yr")));
        adminUserServicesI.updateAdminUserByID(adminUser);
        //newPassword更新到表中
        return  new ResultTemplate("",null);
    }
    /**
     * 获取用户来源
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/7 11:48
     */
    @RequestMapping(value = "exs_getSourceID", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate getSourceID(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia) {
        return new ResultTemplate("", EnumUserSourseID.getMapInfo());
    }
    /**
     * 获取用户来源
     *
     * @param
     * @return
     * @throws
     * @author ShiLinghuai
     * @date 2018/5/7 11:48
     */
    @RequestMapping(value = "exs_getRoleID", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultTemplate getRole(BatchUpdateSaleOrMedia batchUpdateSaleOrMedia) {
        return new ResultTemplate("", EnumUserRole.getMapInfo());
    }
}
