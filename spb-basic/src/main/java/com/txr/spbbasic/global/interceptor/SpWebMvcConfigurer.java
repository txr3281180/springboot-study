package com.txr.spbbasic.global.interceptor;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by xinrui.tian on 2019/4/7.
 */

//@Component
public class SpWebMvcConfigurer implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ExtendsHandlerInterceptorAdapter()).addPathPatterns("/**");
        registry.addInterceptor(new ImpHandlerInterceptor()).addPathPatterns("/**");
    }

}
