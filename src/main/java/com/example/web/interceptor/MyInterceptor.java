package com.example.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wendongshan on 2016/11/7.
 * 有了这个可以去掉Filter
 */
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 处理方法在这里写.一般是验证登陆与否,或者登陆是否失效
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //一般是获取用户的id 去缓存框架(redis,memcached)里面去验证是否存在.
        System.out.println("++++++++++++myInterceptor++++++++++++++++++++++");
        String requestURI = httpServletRequest.getRequestURI();
        /*boolean isUserInfo= requestURI.contains("/user");
        if(isUserInfo){
            ///httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
            httpServletResponse.getWriter().print("请求接口无法直接访问!");
            return false;
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
