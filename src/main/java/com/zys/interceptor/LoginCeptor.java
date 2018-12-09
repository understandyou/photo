package com.zys.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCeptor implements HandlerInterceptor {
    /**
     * 进入控制器前
     * @param request
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = request.getSession();
        //如果url中存在制定的url则为公开地址不用拦截
        //不拦截规则已经配置在xml的handlInterceptor中
//        if(request.getRequestURI().indexOf("")>=0){
//            return true;
//        }
        System.out.println("---");
        //回发回来的key和session中的key对比，一致则ok
        if(request.getParameter("userKey")!=null && request.getParameter("userKey").equals(session.getAttribute("userKey"))){
           return true;
        }
        return false;
    }

    /**
     * 进入控制器后，返回模型视图前
     * 可以将公用的modelAndView在这里统一加入
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在handler完成后执行此方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
