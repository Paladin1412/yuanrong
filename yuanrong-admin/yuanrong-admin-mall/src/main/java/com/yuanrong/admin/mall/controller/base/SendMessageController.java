package com.yuanrong.admin.mall.controller.base;

import com.yuanrong.admin.bean.base.SmsRecord;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.common.module.shortmessage.SMSSend;
import com.yuanrong.common.util.ResultTemplate;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("sms")
public class SendMessageController extends BaseController {
    private static final Logger logger = Logger.getLogger(SendMessageController.class);

    @RequestMapping(value = "sendMessage")
    @ResponseBody
    public ResultTemplate sendMessage(String iphoneNum, String token, String user , String content, String source){
         if(StringUtil.isBlank(iphoneNum)){
             return new ResultTemplate("手机号不能为空",null);
         }
        if(StringUtil.isBlank(token)){
            return new ResultTemplate("token不能为空",null);
        }
        if(!"yx-admin-mall".equals(token)){
            return new ResultTemplate("token错误",null);
        }
        if(StringUtil.isBlank(user)){
            return new ResultTemplate("用户名不能为空",null);
        }
        if(StringUtil.isBlank(content)){
            return new ResultTemplate("发送内容不能为空",null);
        }
        SmsRecord smsRecord = new SmsRecord();
         smsRecord.setMobile(iphoneNum);
         smsRecord.setStatus(1);
         smsRecord.setSource(source);
         try {
             smsRecordServicesI.insertSendMessage(smsRecord,content);
             return new ResultTemplate("",null);
         }catch (Exception e){
             e.printStackTrace();
            logger.error(e.getMessage());
             return new ResultTemplate("调用发送短信失败",null);
         }


    }
}
