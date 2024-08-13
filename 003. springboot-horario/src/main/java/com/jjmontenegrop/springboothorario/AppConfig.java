package com.jjmontenegrop.springboothorario;

import com.jjmontenegrop.springboothorario.controllers.interceptors.interceptors.CalendarInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    private final HandlerInterceptor calendarInterceptor;

    @Autowired
    public AppConfig(CalendarInterceptor calendarInterceptor) {
        this.calendarInterceptor = calendarInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(calendarInterceptor).addPathPatterns("/index");
    }
}
