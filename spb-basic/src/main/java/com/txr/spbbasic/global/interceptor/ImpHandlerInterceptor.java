package com.txr.spbbasic.global.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xinrui.tian on 2019/4/7.
 */
public class ImpHandlerInterceptor implements  HandlerInterceptor {

    /** 实现 HandlerInterceptor  */

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("拦截器 preHandle ==============");
        return true;
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器 postHandle ===============");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截器 afterCompletion ===============");
    }

}
