package com.yuanrong.common.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhonghang on 2018/6/7.
 */
public class WebUtil {
    public static void addCookie(HttpServletRequest request,HttpServletResponse response,String name,String value , int maxAge){
        Cookie[] cookies = request.getCookies();
        boolean flage = true;
        if (cookies != null) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(name)){
                    cookie.setValue(value);
                    cookie.setPath("/");
                    cookie.setMaxAge(maxAge);
                    cookie.setDomain(getTopDomain(request.getServerName()));
                    response.addCookie(cookie);
                    flage = false;
                    break;
                }
            }
        }
        if(flage){
            Cookie cookie = new Cookie(name.trim(), value.trim());
            cookie.setMaxAge(maxAge);// 秒
            cookie.setPath("/");
            cookie.setDomain(getTopDomain(request.getServerName()));
            response.addCookie(cookie);
        }
    }

    public static void delCookie(HttpServletRequest request,HttpServletResponse response,String name){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(name)){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);// 立即销毁cookie
                    cookie.setPath("/");
                    cookie.setDomain(getTopDomain(request.getServerName()));
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    /**
     * 根据名字获取cookie
     * @param request
     * @param name cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = cookieMap.get(name);
            return cookie;
        }else{
            return null;
        }
    }

    public static String getCookieValueByName(HttpServletRequest request,String name){
        Cookie cookie = getCookieByName(request , name);
        return cookie == null ? null : cookie.getValue();
    }

    /**
     * 将cookie封装到Map里面
     * @param request
     * @return
     */
    private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    private static String getTopDomain(String domain){
        String[] spl = domain.split("\\.");
        if(spl.length <=2){
            return domain;
        }
        String result = spl[spl.length-2]+"."+spl[spl.length-1];
        return result;
    }

}
