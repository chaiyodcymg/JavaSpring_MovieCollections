package com.chaiyot.javaspringmoviecollection;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

    	System.out.println(request.getRequestURI());
        if (request.getSession(false) == null || request.getSession().getAttribute("session") == null && request.getRequestURI().equals("/admin/addmovies") ) {
            response.sendRedirect("/");
            return false;
        }
        else if(request.getSession().getAttribute("session") != null && request.getRequestURI().equals("/admin/login") ) {
        	response.sendRedirect("/"); 
        	return false;
        }

        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//            ModelAndView modelAndView) throws Exception {
//        // Do nothing
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
//            Exception exception) throws Exception {
//        // Do nothing
//    }
}

