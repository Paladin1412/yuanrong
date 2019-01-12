package com.yuanrong.admin.server.interceptor;

import com.yuanrong.admin.bean.system.AdminUser;
import com.yuanrong.admin.bean.system.SystemMenu;
import com.yuanrong.common.util.StringUtil;
import com.yuanrong.admin.util.WebUtil;
import org.apache.log4j.Logger;
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
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = Logger.getLogger(SecurityInterceptor.class);

    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true
     *    执行下一个拦截器,直到所有的拦截器都执行完毕
     *    再执行被拦截的Controller
     *    然后进入拦截器链,
     *    从最后一个拦截器往回执行所有的postHandle()
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());

        log.info("requestUri:" + requestUri);
        log.info("contextPath:" + contextPath);
        log.info("url:"+url);
        //如果包含指定ex_ 不需要验证权限
        if(url.split("/").length >=3 && url.split(("/"))[2].contains("ex_")){
            return true;
        }
        AdminUser user =  (AdminUser)request.getSession().getAttribute("admin");

        if(user == null){

            log.info("Interceptor：跳转到login页面！");
            //如果是ajax请求
            /*if(isAjax(request)){
                writer(response , "登录超时，请重新登录" , "401");
            }else{
                //如果为网页点击请求
                request.setAttribute("error", StringUtil.alert("登录超时，请重新登录", "back"));
                request.getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(request, response);
            }*/
            WebUtil.writer(response , "登录超时，请重新登录" , "401");
            return false;
        }else{
            //判断是否有权限
            if (haveAuthority(request.getSession() , url)) {
                return true;
            }

            //如果包含指定exs_ 只需要验证登陆状态，不需要验证权限
            if(url.split("/").length ==3 && url.split(("/"))[2].contains("exs_")){
                return true;
            }

            String errMsg = "没有此功能权限！功能路径为[" + url + "]，请联系产品处理。";
            log.info(errMsg);
            /*if(isAjax(request)){
                writer(response , errMsg, "403");
            }else{
                request.setAttribute("error", StringUtil.alert(errMsg, "back"));
                request.getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(request, response);
            }*/
            WebUtil.writer(response , errMsg, "403");
            return false;
        }
    }
    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if(modelAndView != null){  //加入当前时间
			modelAndView.addObject("var", "测试postHandle");
		}
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
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
        if(session == null){
            return false;
        }
        for(SystemMenu systemMenu : (List<SystemMenu>)session.getAttribute("menus")){
             if(StringUtil.isNoneBlank(systemMenu.getMenuPath()) && systemMenu.getMenuPath().equals(url)){
                return true;
             }
        }
        return false;
    }
}
