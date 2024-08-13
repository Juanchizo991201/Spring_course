package com.jjmontenegrop.springbootinterceptor;

import com.jjmontenegrop.springbootinterceptor.interceptors.LoadingTimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    private final HandlerInterceptor loadingTimeInterceptor;

    @Autowired
    public AppConfig(LoadingTimeInterceptor loadingTimeInterceptor) {
        this.loadingTimeInterceptor = loadingTimeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loadingTimeInterceptor).excludePathPatterns("/app/foo");
    }
}
