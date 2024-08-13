package com.jjmontenegrop.springbootinterceptor.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        logger.info("(preHandle) LoadingTimeInterceptor: entrando al método: " + method.getMethod().getName());

        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        Random random = new Random();
        int delay = random.nextInt(500);
        Thread.sleep(delay);

//        Map<String, String> json = new HashMap<>();
//        json.put("error", "no tienes permiso para acceder a este recurso");
//        json.put("date", new Date().toString());
//
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(json);
//
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.setContentType("application/json");
//        response.getWriter().write(jsonString);
//
//        return false;
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        HandlerMethod method = (HandlerMethod) handler;

        Long endTime = System.currentTimeMillis();
        Long startTime = (Long) request.getAttribute("startTime");

        Long loadingTime = endTime - startTime;

        logger.info("(PostHandle) LoadingTimeInterceptor: tiempo de carga del método " + method.getMethod().getName() + ": " + loadingTime + " ms");
        logger.info("(PostHandle) LoadingTimeInterceptor saliendo del método: " + method.getMethod().getName());
    }
}
