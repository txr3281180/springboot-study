package com.txr.spbdatamybatis.global.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xinrui.tian on 2019/1/8
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null) {
            //未登录，转发到登录界面
            request.setAttribute("msg", "未登录，请先登录！");
            request.getRequestDispatcher("/index").forward(request,response);
            return false;
        }
        return true;
    }

}
