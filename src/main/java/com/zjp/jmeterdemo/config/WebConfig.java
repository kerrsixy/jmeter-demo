package com.zjp.jmeterdemo.config;

import com.zjp.jmeterdemo.interceptor.RequestHeaderInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebConfig
 * Package: com.zjp.jemeterdemo.config
 * Description:
 *
 * @author zjp
 * @version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    //自定义的拦截器对象
    @Autowired
    private RequestHeaderInterceptor requestHeaderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器对象
        registry.addInterceptor(requestHeaderInterceptor).addPathPatterns("/**");//设置拦截器拦截的请求路径（ /** 表示拦截所有请求）
    }
}
