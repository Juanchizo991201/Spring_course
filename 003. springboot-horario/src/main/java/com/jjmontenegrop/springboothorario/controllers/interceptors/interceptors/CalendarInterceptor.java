package com.jjmontenegrop.springboothorario.controllers.interceptors.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class CalendarInterceptor implements HandlerInterceptor {

    @Value("${config.calendar.open}")
    private Integer open;

    @Value("${config.calendar.close}")
    private Integer close;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println("Hour: " + hour);

        if (hour >= open && hour < close) {

            String message = "Welcome to the care system schedule! " + "We are open from " +
                    open +
                    ":00 to " +
                    close +
                    ":00.";
            request.setAttribute("message", message);
            return true;
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = new HashMap<>();

        String message = "Closed!, we are open from " + open +
                ":00 to " +
                close +
                ":00.";

        data.put("message", message);
        data.put("date", new Date());

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(mapper.writeValueAsString(data));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
