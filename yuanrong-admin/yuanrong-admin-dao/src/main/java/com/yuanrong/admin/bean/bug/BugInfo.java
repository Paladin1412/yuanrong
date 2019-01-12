package com.yuanrong.admin.bean.bug;

import java.io.Serializable;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.base.UserInfo;
import com.yuanrong.admin.util.CollectionUtil;
import com.yuanrong.common.util.DateUtil;
import com.yuanrong.common.util.RequestUtil;
import com.yuanrong.common.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * bug信息的实体类
 *
 * @author MDA
 *
 */
public class BugInfo extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private int bugInfoId;
    /*****自定义属性区域begin******/
   
   
    /**
     * 操作人
     * 操作人
     */
    private String operUser;
   
    /**
     * 错误头部信息
     * 错误头部信息
     */
    private String errorTitle;
   
    /**
     * 错误详情
     * 错误详情
     */
    private String errorDescription;
   
    /**
     * 域名
     * 域名
     */
    private String domain;

    /**
     * 请求参数
     */
    private String parameter;

    /**
     * 请求路径
     */
    private String requestUrl;

    /**
     * 用户IP
     */
    private String ip;

    public BugInfo(){}

    public BugInfo(String operUser, String errorTitle, String errorDescription, String domain, String parameter) {
        this.operUser = operUser;
        this.errorTitle = errorTitle;
        this.errorDescription = errorDescription;
        this.domain = domain;
        this.parameter = parameter;
    }

    public int getBugInfoId() {
        return this.bugInfoId;
    }
    public void setBugInfoId(int bugInfoId) {
        this.bugInfoId = bugInfoId;
    }
    /*****自定义属性区域begin.get/set******/
   
    public String getOperUser() {
        return this.operUser;
    }
    public void setOperUser(String operUser) {
        this.operUser = operUser;
    }
    public String getErrorTitle() {
        return this.errorTitle;
    }
    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }
    public String getErrorDescription() {
        return this.errorDescription;
    }
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
    public String getDomain() {
        return this.domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public static BugInfo getBugInfoByErrorInfo(HttpServletRequest httpServletRequest, Exception e){
        StringBuffer sb = new StringBuffer();
        for(StackTraceElement ele : e.getStackTrace()){
            sb.append(ele.toString()).append("\n");
        }
        String domain = httpServletRequest.getServerName();

        StringBuffer parameter= new StringBuffer();
        String queryString = httpServletRequest.getQueryString();
        if(StringUtil.isNotBlank(queryString)){
            queryString.replaceAll("&" , "\n");
            parameter.append(queryString);
        }
        Map<String ,Object> param = httpServletRequest.getParameterMap();
        if( param != null && param.size() > 0){
            parameter.append(JSONObject.toJSONString(httpServletRequest.getParameterMap()));
        }
        BugInfo bugInfo = new BugInfo("" ,e.toString() ,sb.toString() , domain ,parameter.toString() );
        bugInfo.setIp(RequestUtil.getIpAddress(httpServletRequest));
        bugInfo.setRequestUrl(httpServletRequest.getRequestURL().toString());
        return bugInfo;
    }


}
