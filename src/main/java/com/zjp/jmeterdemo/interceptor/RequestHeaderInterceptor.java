package com.zjp.jmeterdemo.interceptor;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * ClassName: RequestHeaderInterceptor
 * Package: com.zjp.jemeterdemo.interceptor
 * Description:
 *
 * @author zjp
 * @version 1.0
 */
@Component
public class RequestHeaderInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestHeaderInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        // 打印请求头信息
        logger.info("Request URL: {}", request.getRequestURL());
        logger.info("Request Method: {}", request.getMethod());

        // 打印所有请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        List<String> headerNameList = Collections.list(headerNames);
        for (String headerName : headerNameList) {
            String headerValue = request.getHeader(headerName);
            logger.info("Header {}: {}", headerName, headerValue);
        }


        // 打印所有Cookie
        if (request.getCookies() != null) {
            for (javax.servlet.http.Cookie cookie : request.getCookies()) {
                logger.info("Cookie Name: {}, Value: {}", cookie.getName(), cookie.getValue());
            }
        }

        return true; // 继续处理请求
    }
}
