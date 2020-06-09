package com.myproject.springmybatis.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/8 16:20
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Object userName = request.getSession().getAttribute("userName");
        System.out.println("postHandle---"+userName+"==="+request.getRequestURL());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Object userName = request.getSession().getAttribute("userName");
        System.out.println("afterCompletion---"+userName+"==="+request.getRequestURL());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object userName = request.getSession().getAttribute("userName");
        System.out.println("afterCompletion---"+userName+"==="+request.getRequestURL());
        if (userName == null) {
            request.setAttribute("msg","请先登录账号");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }
        return true;
    }
}
