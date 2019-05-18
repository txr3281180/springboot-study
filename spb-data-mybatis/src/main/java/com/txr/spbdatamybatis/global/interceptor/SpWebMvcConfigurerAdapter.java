package com.txr.spbdatamybatis.global.interceptor;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.txr.spbdatamybatis.global.resolver.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by xinrui.tian on 2019/1/2
 */
@Component
public class SpWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] text = new String[] {"/css/**", "js/**" , "/img/**","/webjars/**","/bond/js/**" ,"/error"};
        registry.addInterceptor(new RequestTimingInterceptor()).addPathPatterns("/**").excludePathPatterns(text);
        //拦截非登录请求，查看是否登录
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/", "/index", "/login")  //排除登录页面的请求
                .excludePathPatterns(text);
    }

    //视图解析配置方式1
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/main").setViewName("index");
        registry.addViewController("/hello").setViewName("hello");
    }

    /*配置国际化*/
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter mappingJsonConverter = (MappingJackson2HttpMessageConverter) converter;
                mappingJsonConverter.getObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS); //json 解析规则
            }
        }
    }
}
