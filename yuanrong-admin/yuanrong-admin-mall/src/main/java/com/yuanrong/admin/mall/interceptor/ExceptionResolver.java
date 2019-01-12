package com.yuanrong.admin.mall.interceptor;

import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.Enum.EnumWeChartType;
import com.yuanrong.admin.bean.base.UserInfo;
import com.yuanrong.admin.bean.bug.BugInfo;
import com.yuanrong.admin.rpc.api.bug.BugInfoServicesI;
import com.yuanrong.admin.rpc.api.config.ConfigurationServicesI;
import com.yuanrong.admin.rpc.api.notice.WeChatServicesI;
import com.yuanrong.admin.rpc.exception.YRParamterException;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.common.util.SystemParam;
import com.yuanrong.admin.util.WebUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhonghang on 2018/5/26.
 * 全局异常处理
 */
public class ExceptionResolver implements HandlerExceptionResolver {
    private static final Logger log = Logger.getLogger(ExceptionResolver.class);

    @Autowired
    BugInfoServicesI bugInfoServicesI;

    @Autowired
    ConfigurationServicesI configurationServicesI;
    @Autowired
    public WeChatServicesI wechatServicesI;
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //异常处理
        String errMsg;
        if(e instanceof RpcException){
            errMsg = "远程调用异常，请稍后重试.";
        }else if(e instanceof YRParamterException){
            return new ModelAndView(new MappingJackson2JsonView() , WebUtil.getJSONObject(e.getMessage()));
        }else if(e != null && StringUtil.isNotBlank(e.getMessage()) && e.getMessage().contains("org.springframework.dao.DuplicateKeyException")){
            return new ModelAndView(new MappingJackson2JsonView() , WebUtil.getJSONObject("不能多次添加"));
        }else if(e != null &&  StringUtil.isNotBlank(e.toString()) && e.toString().contains("org.apache.catalina.connector.ClientAbortException")){
            log.error(o , e);
            return null;
        }else{
            //系统异常
            errMsg = "系统出现异常，请稍后访问";
        }
        log.error(o , e);
        //发送消息给开发人员
        if(httpServletRequest.getServerName().contains("yuanrongbank.com")
                || httpServletRequest.getServerName().contains("ifi6.com")){
            BugInfo bugInfo = BugInfo.getBugInfoByErrorInfo(httpServletRequest, e);
            UserInfo userInfo = JSONObject.toJavaObject((JSONObject) httpServletRequest.getSession().getAttribute("userInfoJSON") , UserInfo.class);
            if(userInfo != null){
                bugInfo.setOperUser("[" + userInfo.getUserName() + "]" + userInfo.getMobile());
            }
            String content = "操作人："+ bugInfo.getOperUser() +
                    "\n操作时间："+ DateUtil.getNow()+
                    "\n错误信息："+ bugInfo.getErrorTitle()
                    +"\n错误详情："+bugInfo.getErrorDescription();
            bugInfo = bugInfoServicesI.error(bugInfo);
            wechatServicesI.sendBugToEngineer(EnumWeChartType.bug ,bugInfo.getDomain() , content,"",
                    configurationServicesI.getbyKey(SystemParam.BUG_SERVER_URL)+
                            "/bug/ex_bugInfo_getbyId?bugInfoId="+bugInfo.getBugInfoId());
        }
        return new ModelAndView(new MappingJackson2JsonView() , WebUtil.getJSONObject(errMsg));
    }
}
