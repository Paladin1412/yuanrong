package com.yuanrong.admin.mall.controller.base;

import com.sun.org.apache.regexp.internal.RE;
import com.yuanrong.admin.bean.base.SMSValidateCode;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.common.module.shortmessage.SMSSend;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.common.util.ValidateCode;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * 验证码Controller
 */
@Controller
@RequestMapping("verificationCode")
public class VerificationCodeController extends BaseController{
    private static final Logger logger = Logger.getLogger(VerificationCodeController.class);
    /**
     * 获取图像验证码
     */
    @RequestMapping(value = "getValidateImg")
    public void getValidateImg(HttpServletResponse response) throws IOException {
        ValidateCode vCode = new ValidateCode(160,40,4,50);
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        getSession().setAttribute("getImgValidateCode",vCode.getCode().toString());
        if(vCode!=null){
            vCode.write(response.getOutputStream());
        }else{

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
            return new ResultTemplate("图像验证为空,请重新获取验证码",null);
        }
        //验证参数是否正确
        if(StringUtil.isBlank(imgCode)){
            return new ResultTemplate("输入的验证不能为空",null);
        }
        if(sessionImgCode.toLowerCase().equals(imgCode.toLowerCase())){
            return new ResultTemplate("",null);
        }else{
            return new ResultTemplate("输入的验证码错误",null);
        }
    }

    /**
     * 发送短信
     */
    @RequestMapping(value = "sendMessage")
    @ResponseBody
    public ResultTemplate sendMessage(String mobile,HttpServletRequest request){
//        HttpSession session = request.getSession();
        // 调用短信验证码之前需要验证：图像验证是否正确， 正确则下一步， 不正确则return
        String imgCode = request.getParameter("imgCode");
        String imgCodeSession = (String) getSession().getAttribute("getImgValidateCode");
        //Session中存储的图像验证码不能为空                                getImgValidateCode
        if(StringUtil.isBlank(imgCodeSession)){
            return new ResultTemplate("imgCodeSession为空，请先点击图像验证码", null);
        }

        //验证输入的图像验证码不能为空
        if(StringUtil.isBlank(imgCode)){
            return new ResultTemplate("输入的图像验证码不能为空", null);
        }

        imgCodeSession = imgCodeSession.toLowerCase();
        imgCode = imgCode.toLowerCase();

        //验证输入的图像验证是否等于存储在Session中的图像验证码
        if(!imgCodeSession.equals(imgCode)){
            return new ResultTemplate("输入的验证码不正确", null);
        }
        //清除图像验证码
        getSession().removeAttribute("getImgValidateCode");
        //==========
        boolean isMobile = SMSSend.isMobile(mobile);
        if(!isMobile){
            return new ResultTemplate("手机号格式非法", null);
        }
        String smsCode = SMSSend.getRandom();
        String messageContent = "验证码 " + smsCode + " 有效期为5分钟，验证码仅用于本次圆融账号注册、登录使用,如非本人操作请忽略，本条信息免费。";
        try {
            messageContent = URLEncoder.encode(messageContent,"UTF-8");
        }catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String rst = SMSSend.sendSMS(messageContent,mobile);
        if(SMSSend.isSendSuccess(rst)){
            Date date = new Date();
            SMSValidateCode smsValidateCode = new SMSValidateCode();
            smsValidateCode.setDate(date);
            smsValidateCode.setSmsCode(smsCode);
            smsValidateCode.setMobile(mobile);
            session.setAttribute("smsValidateCode",smsValidateCode);
            return new ResultTemplate("",null);
        }
        return new ResultTemplate("发送失败！");
    }

}
