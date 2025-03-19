package com.example.blogpost.berkayerol.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class RequestInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        String requestURI = httpServletRequest.getRequestURI();
        String method = httpServletRequest.getMethod();
        String environment = System.getProperty("spring.profiles.active", "default");

        logger.info("Incoming request: Method: {}, URI: {}, Environment: {}", method, requestURI, environment);
        return true;
    }

}
