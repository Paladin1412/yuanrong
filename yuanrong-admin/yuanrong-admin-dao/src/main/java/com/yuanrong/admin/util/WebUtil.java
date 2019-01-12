package com.yuanrong.admin.util;

import com.yuanrong.admin.bean.base.UserInfo;
import com.alibaba.fastjson.JSONObject;
import com.yuanrong.common.util.MD5Util;
import com.yuanrong.common.util.RequestUtil;
import com.yuanrong.common.util.StringUtil;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhonghang on 2018/6/7.
 */
public class WebUtil {
    public static void writer(HttpServletResponse response , String msg , String code) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status" , code);
        jsonObject.put("msg" , msg);
//        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        if(jsonObject!=null||!"".equals(jsonObject)||!"null".equals(jsonObject)) {
            try {
                response.getWriter().print(jsonObject.toJSONString());
                response.getWriter().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean checkLogin(RedisTemplate redisTemplate , HttpServletRequest request, HttpServletResponse response){
        if(request.getSession().getAttribute("userInfoJSON") == null){
            return false;
        }
        UserInfo userInfo = JSONObject.toJavaObject((JSONObject) request.getSession().getAttribute("userInfoJSON") , UserInfo.class);
        if(userInfo == null){
            return false;
        }

        String psw = (String) redisTemplate.opsForValue().get("RegisteredUserInfo:p:"+userInfo.getRecID().intValue());
        if(StringUtil.isBlank(psw)){
            request.getSession().invalidate();
            return false;
        }

        if(!StringUtil.equals(userInfo.getPassword(),psw)){
            request.getSession().invalidate();
            return false;
        }
        return true;
    }

    public static void returnLogin(HttpServletRequest request, HttpServletResponse response ){
        if(isAjax(request)){
            WebUtil.writer(response , "登录失效，请重新登录" , "401");
        }else{
            response.setStatus(302);//向浏览器发送302状态码
            String callback = "?callback="+request.getRequestURL()+(StringUtil.isBlank(request.getQueryString()) ? "" : "?"+request.getQueryString());
            if(request.getServerName().contains("yuanrongbank.com")){
                response.setHeader("location","http://my.yuanrongbank.com/#/login"+callback);//发送响应头Location为所给路径
            }else if(request.getServerName().contains("v6")){
                response.setHeader("location","http://v6my.t.ifi6.com/#/login"+callback);//发送响应头Location为所给路径
            }else{
                response.setHeader("location","http://my.t.ifi6.com/#/login"+callback);//发送响应头Location为所给路径
            }
        }
    }

    public static boolean tokenLogin(HttpServletRequest request, RedisTemplate redisTemplate ){
        //通过token登录
        String uk = com.yuanrong.common.util.WebUtil.getCookieValueByName(request , "uk");
        if(StringUtil.isNotBlank(uk)){
            String t = com.yuanrong.common.util.WebUtil.getCookieValueByName(request , "t");
            String u = com.yuanrong.common.util.WebUtil.getCookieValueByName(request , "u");
            String ui = com.yuanrong.common.util.WebUtil.getCookieValueByName(request , "ui");
            String psw = (String) redisTemplate.opsForValue().get("RegisteredUserInfo:p:"+ui);
            if(StringUtil.isNotBlank(psw) && StringUtil.isNotBlank(t)&& StringUtil.isNotBlank(u)&& StringUtil.isNotBlank(ui)){
                String token = MD5Util.md5(MD5Util.md5(u+ui+RequestUtil.getIpAddress(request)+request.getHeader("User-Agent")+t)+psw);
                if(token.equals(uk)){
                    UserInfo userInfo = new UserInfo(Integer.parseInt(ui) , null, u , RequestUtil.getIpAddress(request),
                            request.getHeader("User-Agent") , psw);
                    request.getSession().setAttribute("userInfoJSON",JSONObject.toJSON(userInfo));
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否为ajax请求
     */
    static boolean  isAjax(HttpServletRequest request){
        return  (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString())) ;
    }

    public static JSONObject getJSONObject(String msg ){
        JSONObject result = new JSONObject();
        result.put("status" , 0);
        result.put("msg" , msg);
        result.put("data" ,new JSONObject());
        return result;
    }

}
