package com.txr.spbbasic.global.interceptor;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by xinrui.tian on 2019/1/2
 */
@Component
public class SpWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    /**
     * 配置拦截器
     *   *  继承 HandlerInterceptorAdapter
     *   *  实现 HandlerInterceptor
     *
     *  spring 1.x 默认不拦截静态资源
     *  spring 2.x 会拦截静态资源需要手动排除静态资源拦截
     *
     *  注册 拦截器
     *      继承 WebMvcConfigurerAdapter（2.0将过时）
     *      实现 WebMvcConfigurer  见：SpWebMvcConfigurer
     *
     *  -addPathPatterns  添加链接url
     *  -excludePathPatterns 排除指定url
     *
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String [] swaggerUil = new String[]{"/swagger-resources/**", "/error"};
        //registry.addInterceptor(new ExtendsHandlerInterceptorAdapter()).addPathPatterns("/**");
        //registry.addInterceptor(new ImpHandlerInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new RequestTimingInterceptor()).addPathPatterns("/**").excludePathPatterns(swaggerUil);
    }

    /** 修改ObjectMapper默认配置 */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter mappingJsonConverter = (MappingJackson2HttpMessageConverter) converter;
                mappingJsonConverter.getObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS); //json 解析规则
            }
        }
    }

    //视图解析配置 （视图解析使用，国际化解析 详见：spring-mybatis）
/*
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }
*/

    //WebMvcConfigurerAdapter 配置方式2  (导致拦截器拦截两次请求)
/*
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter(){

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/index").setViewName("login");
                registry.addViewController("/login").setViewName("login");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new ExtendsHandlerInterceptorAdapter()).addPathPatterns("/**");
            }

        };
        return adapter;
    }
*/
}
