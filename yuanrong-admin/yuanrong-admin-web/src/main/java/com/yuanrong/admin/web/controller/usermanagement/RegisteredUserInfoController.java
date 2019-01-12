package com.yuanrong.admin.web.controller.usermanagement;

import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumLoginMode;
import com.yuanrong.admin.Enum.EnumUserCurrentRole;
import com.yuanrong.admin.Enum.EnumUserSourseID;
import com.yuanrong.admin.bean.base.LoginDetail;
import com.yuanrong.admin.bean.base.SMSValidateCode;
import com.yuanrong.admin.bean.base.SmsRecord;
import com.yuanrong.admin.bean.base.UserInfo;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.web.controller.BaseController;
import com.yuanrong.common.module.shortmessage.SMSSend;
import com.yuanrong.common.util.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("userManagement")
public class RegisteredUserInfoController extends BaseController {
    private static final Logger logger = Logger.getLogger(RegisteredUserInfoController.class);

    /**
     * 用户登录时，获取获取用户的用户名、手机号、名称
     */
    @RequestMapping(value = "loginInfo")
    @ResponseBody
    public ResultTemplate loginInfo(HttpSession session){
        if(getWebUser(session)==null){
            return  new ResultTemplate ("用户未登陆",null);
        }
        Integer registeredUserInfoId =  getWebUser(session).getRecID();
        RegisteredUserInfo registeredUserInfo =  this.registeredUserInfoService.getById(registeredUserInfoId);
        if(registeredUserInfo!=null){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mobile",registeredUserInfo.getMobile());
            jsonObject.put("headImage",registeredUserInfo.getHeadImage());
            jsonObject.put("userName",registeredUserInfo.getUserName());
            jsonObject.put("userId" , registeredUserInfo.getRecID());
            return  new ResultTemplate("",jsonObject);
        }else {
            getSession().invalidate();
            return  new ResultTemplate("用户未登陆",null);
        }
    }

    /**
     * 判断用户是否注册
     * @author Tangzheng
     * @param registeredUserInfo
     * @Data 2018-5-22
     *
     */
    @RequestMapping(value = "judeRegisteredUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate judeRegisteredUserInfo(RegisteredUserInfo registeredUserInfo){
        JSONObject jsonObject = new JSONObject();
        if(registeredUserInfo.getMobile() == null){
            return  new ResultTemplate ("参数错误",jsonObject);
        }
        RegisteredUserInfo judeRegisteredUserInfo = this.registeredUserInfoService.judeRegisteredUserInfo(registeredUserInfo);
        if(judeRegisteredUserInfo!=null){
            jsonObject.put("registered",true);
            return  new ResultTemplate ("已注册",jsonObject);
        }else{
            jsonObject.put("registered",false);
            return  new ResultTemplate ("",jsonObject);
        }
    }

    /**
     * 添加注册用户
     * @author      Tangzheng
     * @param
     * @return
     * @exception
     * @date        2018/5/22 13:48
     */
    @RequestMapping(value = "saveRegisteredUser", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate saveRegisteredUser(RegisteredUserInfo registeredUserInfo,String ssmCode,String imgCode){
        registeredUserInfo.setCurrentRole(registeredUserInfo.getRole());
        JSONObject result = new JSONObject();
        if(registeredUserInfo.getMobile()==null ||registeredUserInfo.getMobile().equals("") ){
            return  new ResultTemplate("手机号不能为空",result);
        }
        //从session中获取短信验证码、已经对应的手机号
        SMSValidateCode smsValidateCode = (SMSValidateCode)getSession().getAttribute("smsValidateCode");
        //从session中获取图像验证码
        // 验证从Session中获取的短信验证码是否等于用户输入的短信验证码
        if(smsValidateCode==null || StringUtil.isBlank(smsValidateCode.getSmsCode())){
            return  new ResultTemplate("请先获取短信验证码!");
        }
        if(!smsValidateCode.getSmsCode().toLowerCase().equals(ssmCode.toLowerCase()) ){
            return  new ResultTemplate("短信验证码错误",null);
        }
        if(!smsValidateCode.getMobile().equals(registeredUserInfo.getMobile())){
            String sendMessageMobile = smsValidateCode.getMobile();
            String registeredMobile  =  registeredUserInfo.getMobile();
            String rntStr ="发短信验证码的手机号:"+sendMessageMobile+"，不是该注册号码:"+registeredMobile+"!";
            return  new ResultTemplate(rntStr,null);
        }
        RegisteredUserInfo judeRegisteredUserInfo = this.registeredUserInfoService.judeRegisteredUserInfo(registeredUserInfo);
        if(judeRegisteredUserInfo!=null){
            return  new ResultTemplate("手机号已注册",null);
        }
        if(registeredUserInfo.getPassword()==null || registeredUserInfo.getPassword().length()<6 ||registeredUserInfo.getPassword().equals("")){
            return  new ResultTemplate("密码不能为空、密码格式错误",result);
        }
        if(registeredUserInfo.getRole()==null||registeredUserInfo.getRole().equals("") ){
            return  new ResultTemplate("角色不能为空",result);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("mobile",registeredUserInfo.getMobile());
        map.put("password",registeredUserInfo.getPassword());
        map.put("role",registeredUserInfo.getRole());
        //      map.put("currentRole",registeredUserInfo.getCurrentRole());
        map.put("adminUserMediaID" ,configurationServicesI.getbyKey(SystemParam.MEDIA_ID));//初始化一个媒介经理
        map.put("adminUserSalesID" , configurationServicesI.getbyKey(SystemParam.SALE_ID));//初始化一个销售经理
        map.put("sourceID", EnumUserSourseID.前台创建.getIndex());
        if(StringUtil.isNotBlank(registeredUserInfo.getQq()) && StringUtil.format(registeredUserInfo.getQq()).length() >11){
            return new ResultTemplate("请输入合法的qq号");
        }
        if(StringUtil.isNotBlank(registeredUserInfo.getQq()) && StringUtil.format(registeredUserInfo.getQq()).length() >50){
            return new ResultTemplate("请输入合法的微信号");
        }
        map.put("qq" , registeredUserInfo.getQq());
        map.put("weixin" , registeredUserInfo.getWeixin());
        int keyNum =  this.registeredUserInfoService.saveRegisteredUser(map);
        logger.info("keyNum:"+keyNum);
        if(keyNum>0){
            result.put("register",true);
            return  new ResultTemplate("",result);
        }else{
            result.put("register",false);
            return  new ResultTemplate("注册失败",result);
        }
    }


    /**
     * 用户登录
     * @author      Tangzheng
     * @param way 0 账号密码登录， 1 手机动态登录11
     * @return
     * @exception
     * @date        2018/5/22 13:48
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResultTemplate accountLogin(RegisteredUserInfo registeredUserInfo,Integer way,String ssmCode,String imgCode , HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        if( StringUtil.isBlank(registeredUserInfo.getMobile())){
            return  new ResultTemplate("账号参数不能为空",jsonObject);
        }
        //0 账号密码登录
        if(way==0 && StringUtil.isBlank( registeredUserInfo.getPassword())){
            return  new ResultTemplate("密码参数不能为空",jsonObject);
        }
        if(way==0){
            RegisteredUserInfo judeRegisteredUserInfo = this.registeredUserInfoService.judeRegisteredUserInfo(registeredUserInfo);
            if(judeRegisteredUserInfo != null){
                jsonObject.put("login",true);
                //Integer recID, String userName, String mobile
                saveToRedis(judeRegisteredUserInfo);
                jsonObject.put("userHeadImg",judeRegisteredUserInfo.getHeadImage());
                jsonObject.put("mobile",judeRegisteredUserInfo.getMobile());
                jsonObject.put("userName",judeRegisteredUserInfo.getUserName());
                loginDetailServicesI.save(new LoginDetail(judeRegisteredUserInfo.getRecID() ,RequestUtil.getIpAddress(request), EnumLoginMode.PC_账号密码,request.getHeader("User-Agent")));
                return  new ResultTemplate("",jsonObject);
            }else{
                jsonObject.put("login",false);
                jsonObject.put("loginfailMsg","手机号或密码错误");
                return  new ResultTemplate("手机号或密码错误",jsonObject);
            }
        }else if(way==1){
            if(StringUtil.isBlank(ssmCode)){
                return  new ResultTemplate("短信验证码不能为空");
            }
            if(StringUtil.isBlank(imgCode)){
                return  new ResultTemplate("图像验证码不能为空");
            }
            //从session中获取短信验证码
            SMSValidateCode smsValidateCode = (SMSValidateCode)getSession().getAttribute("smsValidateCode");
            if(smsValidateCode==null || StringUtil.isBlank(smsValidateCode.getSmsCode())){
                return  new ResultTemplate("请先获取短信验证码!");
            }
            if(!smsValidateCode.getMobile().equals(registeredUserInfo.getMobile())){
                return  new ResultTemplate("请求短信验证码的号码："+smsValidateCode.getMobile()+"；与输入:"+registeredUserInfo.getMobile()+"号码不相同");
            }
            //（1）、判断 短信验证码5分钟之内有效； （2）删除存储在session中的短信验证码
            Date now = new Date();
            if((now.getTime() -smsValidateCode.getDate().getTime())>5*60*1000){
                getSession().removeAttribute("smsValidateCode");
                return  new ResultTemplate("该短信验证码已过期!");
            }else {
                getSession().removeAttribute("smsValidateCode");
            }
            // 验证从Session中获取的验证码是否等于用户输入的验证码  && imgValidateCode.toLowerCase().equals(imgCode.toLowerCase())
            if(smsValidateCode.getSmsCode().equals(ssmCode) ){
                int cnt_Mobile = registeredUserInfoService.getCountPhone(registeredUserInfo.getMobile());
                if(cnt_Mobile!=1){
                    //当号码不存在帮助用户注册
                    Map<String, Object> userRegistMap = new HashMap<>();
                    userRegistMap.put("mobile",registeredUserInfo.getMobile());
                    userRegistMap.put("adminUserSalesID" , configurationServicesI.getbyKey("SALE_ID"));//初始化一个销售经理
                    userRegistMap.put("adminUserMediaID", configurationServicesI.getbyKey("MEDIA_ID"));//初始化一个媒介经理
                    userRegistMap.put("role", EnumUserCurrentRole.当前身份买家.getIndex());
                    userRegistMap.put("sourceID", EnumUserSourseID.前台创建.getIndex());
                    //随机生成密码
                    String machinePassword = SMSSend.getRandomSix();
                    userRegistMap.put("password",machinePassword);
                    registeredUserInfoService.saveRegisteredUser(userRegistMap);
                    SMSSend.sendSMS("欢迎登录圆融平台www.yuanrongbank.com，手机号是：" + registeredUserInfo.getMobile() + "，密码是：" + machinePassword + "。",
                            registeredUserInfo.getMobile());
                }
                RegisteredUserInfo judeRegisteredUserInfo  = this.registeredUserInfoService.judeRegisteredUserInfo(registeredUserInfo);
                if(judeRegisteredUserInfo!=null){
                    if(StringUtil.isBlank(judeRegisteredUserInfo.getPassword())){
                        judeRegisteredUserInfo.setPassword("No Password");
                    }
                    jsonObject.put("login",true);
                    saveToRedis(judeRegisteredUserInfo);
                    jsonObject.put("userHeadImg",judeRegisteredUserInfo.getHeadImage());
                    jsonObject.put("userName",judeRegisteredUserInfo.getUserName());
                    jsonObject.put("mobile",judeRegisteredUserInfo.getMobile());
                    loginDetailServicesI.save(new LoginDetail(judeRegisteredUserInfo.getRecID() , RequestUtil.getIpAddress(request), EnumLoginMode.PC_短息验证码,request.getHeader("User-Agent")));
                    return  new ResultTemplate("",jsonObject);
                }else{
                    jsonObject.put("login",false);
                    return  new ResultTemplate("手机号或者密码不正确",jsonObject);
                }
            }else{
                return  new ResultTemplate("输入的短信验证码错误",null);
            }
        }else{
            return  new ResultTemplate("选择登录的方式不对",jsonObject);
        }
    }


    private void saveToRedis(RegisteredUserInfo registeredUserInfo){
        UserInfo userInfo = new UserInfo(registeredUserInfo.getRecID(),registeredUserInfo.getUserName(),registeredUserInfo.getMobile(),registeredUserInfo.getPassword());
        userInfo.setIp(RequestUtil.getIpAddress(request));
        userInfo.setUserAgent(request.getHeader("User-Agent"));
        userInfo.setPassword(registeredUserInfo.getPassword());
        redisTemplate.opsForValue().set("RegisteredUserInfo:p:"+registeredUserInfo.getRecID().intValue(),userInfo.getPassword());
        getSession().setAttribute("userInfoJSON",JSONObject.toJSON(userInfo));
        WebUtil.addCookie(request ,response , "u" , registeredUserInfo.getMobile() , 60*60*24*365);
        long time = System.currentTimeMillis();
        WebUtil.addCookie(request ,response , "t" , time+"" , 60*60*24*365);
        WebUtil.addCookie(request ,response , "uk" , userInfo.getToken(time) , 60*60*24*15);
        WebUtil.addCookie(request ,response , "ui" , userInfo.getRecID().toString() , 60*60*24*15);
    }

    /**
     * 获取图像验证码
     */
    @RequestMapping(value = "getValidateImg")
    public void getValidateImg(HttpServletResponse response){
        ValidateCode vCode = new ValidateCode(160,40,4,50);
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String imgcode = vCode.getCode().toString();
        getSession().setAttribute("getImgValidateCode",vCode.getCode().toString());
        String sessionImgCode  = (String) getSession().getAttribute("getImgValidateCode");
        if(vCode!=null){
            try{
                vCode.write(response.getOutputStream());
            }catch (Exception e){

            }
        }
    }

    /**
     * 验证前端返回的验证码
     * @param imgCode
     */
    @RequestMapping(value = "getValidateCode")
    @ResponseBody
    public ResultTemplate getValidateCode(String imgCode){
        String sessionImgCode = (String) getSession().getAttribute("getImgValidateCode");
        if(StringUtil.isBlank(sessionImgCode)){
            return  new ResultTemplate("请输入正确的图像验证码",null);
        }
        //验证码转小写
        sessionImgCode = sessionImgCode.toLowerCase();
        //验证参数是否正确
        if(StringUtil.isBlank(imgCode)){
            return  new ResultTemplate("输入图像验证码不能为空",null);
        }
        if(sessionImgCode.equals(imgCode.toLowerCase())){
            return  new ResultTemplate("",null);
        }else{
            return  new ResultTemplate("输入的图像验证码错误",null);
        }
    }

    /**
     * 发送短信
     */
    @RequestMapping(value = "sendMessage")
    @ResponseBody
    public ResultTemplate sendMessage(String mobile,HttpServletRequest request,String source){
        HttpSession session = request.getSession();
        // 调用短信验证码之前需要验证：图像验证是否正确， 正确则下一步， 不正确则return
        String imgCodeSession = (String) session.getAttribute("getImgValidateCode");
        String imgCode = request.getParameter("imgCode");

        //Session中存储的图像验证码不能为空
        if(StringUtil.isBlank(imgCodeSession)){
            return  new ResultTemplate("imgCodeSession为空，请先点击图像验证码", null);
        }

        //验证输入的图像验证码不能为空
        if(StringUtil.isBlank(imgCode)){
            return  new ResultTemplate("输入的图像验证码不能为空", null);
        }

        //验证输入的图像验证是否等于存储在Session中的图像验证码
        if(!imgCodeSession.toLowerCase().equals(imgCode.toLowerCase())){
            return  new ResultTemplate("图像验证码错误", null);
        }
        //清除图像验证码
        getSession().removeAttribute("getImgValidateCode");
        //==========
        boolean isMobile = SMSSend.isMobile(mobile);
        if(!isMobile){
            return  new ResultTemplate("手机号格式非法", null);
        }
        String smsCode = SMSSend.getRandom();
        String messageContent = "验证码 " + smsCode + " 有效期为5分钟，验证码仅用于本次圆融账号注册、登录和修改密码使用,如非本人操作请忽略，本条信息免费。";
        try {
            messageContent = URLEncoder.encode(messageContent,"UTF-8");
        }catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SmsRecord smsRecord = new SmsRecord();
        smsRecord.setMobile(mobile);
        smsRecord.setCreateTime(new Date());
        smsRecord.setSource(source);
        boolean  flag =  smsRecordServicesI.insertSendMessage(smsRecord, messageContent);
        if(flag){
            Date date = new Date();
            SMSValidateCode smsValidateCode = new SMSValidateCode();
            smsValidateCode.setSmsCode(smsCode);
            smsValidateCode.setMobile(mobile);
            smsValidateCode.setDate(date);
            session.setAttribute("smsValidateCode",smsValidateCode);
            return  new ResultTemplate("",null);
        }else{
            return  new ResultTemplate("短信发送失败！",null);
        }
    }

    @RequestMapping(value = "testSendMessage")
    @ResponseBody
    public ResultTemplate testSendMessage(){
        SMSValidateCode smsValidateCode = (SMSValidateCode)getSession().getAttribute("smsValidateCode");
        if(smsValidateCode!=null){
            String smsCode = smsValidateCode.getSmsCode();
            return new ResultTemplate("",smsCode);
        }
        return new ResultTemplate("testSendMessage出错");
    }

    /**
     * 退出登录
     */
    @RequestMapping("logout")
    @ResponseBody
    public ResultTemplate exs_logout(){
        WebUtil.delCookie(request,response,"uk");
        WebUtil.delCookie(request,response,"t");
        getSession().invalidate();
        return  new ResultTemplate("",null);


    }



}
