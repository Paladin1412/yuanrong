package com.yuanrong.admin.mall.controller.redis;

import com.alibaba.fastjson.JSONObject;
import com.yuanrong.admin.mall.controller.BaseController;
import com.yuanrong.common.util.RequestUtil;
import com.yuanrong.common.util.ResultTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhonghang on 2018/10/27.
 */
@Controller
@RequestMapping("redis")
public class RedisController extends BaseController {
   /* @RequestMapping("incr")
    @ResponseBody
    public ResultTemplate incr(String key, long liveTime){
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();

//        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {//初始设置过期时间
//            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
//        }

        return new ResultTemplate(increment);
    }

    @RequestMapping("incrVule")
    @ResponseBody
    public ResultTemplate incrVule(String key, long liveTime){
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.get();

        Integer num =  Integer.parseInt( redisTemplate.opsForValue().get(key).toString());
//        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {//初始设置过期时间
//            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
//        }

        return new ResultTemplate(num);
    }

    @RequestMapping("addValue")
    @ResponseBody
    public ResultTemplate incrVule(String key, Integer value){
        redisTemplate.opsForValue().set(key , value+"");
        return new ResultTemplate();
    }

    @RequestMapping("select")
    @ResponseBody
    public ResultTemplate select(String key){
        //将redis中，作者，作品访问次数刷入数据库中
        yRProductionServicesI.updateFlushRedisAccessTimeToDb();
        yRAuthorServicesI.updateFlushRedisAccessTimeToDb();

        //计算作者作品排序
        yRProductionServicesI.updateCalculateSortScore();
        yRAuthorServicesI.updateCalculateSortScore();

        return new ResultTemplate();
    }*/

   @RequestMapping("ip")
   @ResponseBody
   public ResultTemplate getIp(HttpServletRequest request){
       JSONObject result = new JSONObject();
       result.put("x-forwarded-for" , request.getHeader("x-forwarded-for")) ;
       result.put("X-Real-IP" , request.getHeader("X-Real-IP")) ;
       result.put("remoteAddr" , request.getRemoteAddr()) ;
       result.put("default" , RequestUtil.getIpAddress(request));

       result.put("Proxy-Client-IP" , request.getHeader("Proxy-Client-IP"));
       result.put("WL-Proxy-Client-IP" , request.getHeader("WL-Proxy-Client-IP"));
       result.put("HTTP_CLIENT_IP" , request.getHeader("HTTP_CLIENT_IP"));
       result.put("HTTP_X_FORWARDED_FOR" , request.getHeader("HTTP_X_FORWARDED_FOR"));
       return new ResultTemplate(result);
   }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        System.out.println("x-forwarded-for ip: " + ip);
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            System.out.println("Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            System.out.println("WL-Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            System.out.println("HTTP_CLIENT_IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
            System.out.println("X-Real-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            System.out.println("getRemoteAddr ip: " + ip);
        }
        System.out.println("获取客户端ip: " + ip);
        return ip;
    }
}
