package com.yuanrong.admin.mall.controller.usermanagement;

import com.yuanrong.admin.bean.base.SMSValidateCode;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.common.module.shortmessage.SMSSend;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.common.util.ValidateCode;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

@Controller
@RequestMapping("registeredUserInfo")
public class RegisteredUserInfoController  extends BaseController {
    private static final Logger logger = Logger.getLogger(RegisteredUserInfoController.class);
    /**
     * 图片响应验证码页面
     */
    @RequestMapping(value = "validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response){
        ValidateCode vCode = new ValidateCode(160,40,4,50);
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        getSession().setAttribute("code",vCode.getCode().toString());
        try {
            vCode.write(response.getOutputStream());
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }


    /**
     * 发短信: 先验证图像验证是否正确，若正确在发短信
     */
    @RequestMapping(value = "getSMS", method = { RequestMethod.POST, RequestMethod.GET, RequestMethod.HEAD })
    @ResponseBody
    public ResultTemplate getSMS(HttpServletRequest request, HttpServletResponse response,String source){
        // 获取输入的手机号
        String phone = String.valueOf(request.getParameter("phone"));
        //判断是否是手机号码
        boolean mobile = SMSSend.isMobile(phone);
        if(!mobile){
            String errmsgStr = "该号码格式不对："+phone;
            return new ResultTemplate(errmsgStr,null);
        }
        //获取输入的图像验证码
        String  code = String.valueOf(request.getParameter("code")).toLowerCase();
        //获取输入的页面来源
        String Source = String.valueOf(request.getParameter("source"));
        //获取session中的图像验证码
        String sessionCode = (String) session.getAttribute("code");
        sessionCode = sessionCode.toLowerCase();

       //验证参数是否合规
        if(StringUtil.isBlank(code)){
            return new ResultTemplate("输入的图像验证码不能为空",null);
        }
        if(StringUtil.isBlank(Source)){
            return new ResultTemplate("输入的页面来源不能为空",null);
        }
        if(StringUtil.isBlank(sessionCode)){
            return new ResultTemplate("sessionCode中的未存储图像验证码",null);
        }

        // 验证sessionCode是否等于code
        if(!code.equals(sessionCode)){
            return new ResultTemplate("输入的图像验证码错误",null);
        }

        String smsCode = SMSSend.getRandom();
        String messageContent = "验证码 " + smsCode + " 有效期为5分钟，验证码仅用于本次圆融账号注册、登录使用,如非本人操作请忽略，本条信息免费。";
        try {
            messageContent = URLEncoder.encode(messageContent,"UTF-8");
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String restr = SMSSend.sendSMS(messageContent,phone);
        if(SMSSend.isSendSuccess(restr)){
            Date date = new Date();
            SMSValidateCode smsValidateCode = new SMSValidateCode();
            smsValidateCode.setMobile(phone);
            smsValidateCode.setSmsCode(smsCode);
            smsValidateCode.setDate(date);
            session.setAttribute("smsValidateCode",smsValidateCode);
            return new ResultTemplate("",null);
        }

        return new ResultTemplate("发送失败");

    }

    /**
     * 退出登录
     */
    @RequestMapping("logout")
    @ResponseBody
    public ResultTemplate logout(){
        getSession().invalidate();
        return  new ResultTemplate("",null);
    }

}
