package com.yuanrong.admin.mall.interceptor;

import com.yuanrong.admin.bean.system.SystemMenu;
import com.yuanrong.admin.util.WebUtil;
import com.yuanrong.common.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhonghang on 2018/4/11.
 */
public class ListPageInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = Logger.getLogger(ListPageInterceptor.class);
    @Autowired
    public RedisTemplate redisTemplate;
    /**
     * 在业务处理器处理请求之前被调�??
     * 如果返回false
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再�??出拦截器�??
     * 如果返回true
     *    执行下一个拦截器,直到�??有的拦截器都执行完毕
     *    再执行被拦截的Controller
     *    然后进入拦截器链,
     *    从最后一个拦截器�??回执行所有的postHandle()
     *    接着再从�??后一个拦截器�??回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        int cp = request.getParameter("cp") == null ? 0 : Integer.parseInt(request.getParameter("cp"));
        int rows = request.getParameter("rows") == null ? 0 : Integer.parseInt(request.getParameter("rows"));
        if(cp > 3 || rows > 20) {
            //监测登录状�??
            boolean flage = WebUtil.checkLogin(redisTemplate, request, response);
            //如果未登录，采用cookie登录
            if (flage == false) {
                flage = WebUtil.tokenLogin(request, redisTemplate);
            }
            //返回跳转方式
            if(flage == false){
                WebUtil.returnLogin(request , response);
            }
            return flage;
        }
         return true;
    }
    /**
     * 在业务处理器处理请求执行完成�??,生成视图之前执行的动�??
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if(modelAndView != null){  //加入当前时间
//			modelAndView.addObject("var", "测试postHandle");
		}
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调�??,可用于清理资源等
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
    /**
     * 判断是否为ajax请求
     */
    boolean isAjax(HttpServletRequest request){
        return  (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString())) ;
    }
    // org.springframework.web.context.request.WebRequest;
    public static boolean isAjaxRequest(WebRequest webRequest) {
        String requestedWith = webRequest.getHeader("X-Requested-With");
        return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
    }

    private boolean haveAuthority(HttpSession session , String url){
        for(SystemMenu systemMenu : (List<SystemMenu>)session.getAttribute("menus")){
             if(StringUtil.isNoneBlank(systemMenu.getMenuPath()) && systemMenu.getMenuPath().equals(url)){
                return true;
             }
        }
        return false;
    }
}
