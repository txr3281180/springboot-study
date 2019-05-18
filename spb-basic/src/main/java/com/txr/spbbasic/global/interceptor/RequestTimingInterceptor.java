package com.txr.spbbasic.global.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by xinrui.tian on 2019/1/8
 */
public class RequestTimingInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(RequestTimingInterceptor.class);
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal("RequestStartTime");

    public RequestTimingInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        long beginTime = System.currentTimeMillis();
        this.startTimeThreadLocal.set(beginTime);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        long endTime = System.currentTimeMillis();
        long beginTime = (this.startTimeThreadLocal.get()).longValue();  //线程的开始时间
        long consumedTime = endTime - beginTime;
        String paramsString = "";
        if (this.logger.isDebugEnabled()) {
            StringBuilder buffer = new StringBuilder();
            Map<String, String[]> params = request.getParameterMap();
            buffer.append(" | Parameters:{");
            String[] keys = new String[params.size()];
            params.keySet().toArray(keys);

            for(int ii = 0; ii < keys.length; ++ii) {
                if (ii > 0) {
                    buffer.append(",");
                }

                String key = keys[ii];
                buffer.append(key + ": " + Arrays.toString(params.get(key)));
            }

            buffer.append("}");
            paramsString = buffer.toString();
        }

        String timingMsg = String.format("[%d ms] [%d] [%s] %s %s%s", consumedTime, response.getStatus(), this.getRemoteIP(request), request.getMethod(), request.getRequestURI(), paramsString);
        this.logger.info(timingMsg);
    }

    private String getRemoteIP(HttpServletRequest request) {
        String remoteIP = request.getHeader("x-forwarded-for");
        return remoteIP != null ? remoteIP : request.getRemoteAddr();
    }
}
