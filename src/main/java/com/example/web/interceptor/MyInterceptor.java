package com.example.web.interceptor;

import com.example.common.auth.AuthLogin;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            AuthLogin authLogin=((HandlerMethod) handler).getMethodAnnotation(AuthLogin.class);
            //不需要登陆
            if(authLogin==null||authLogin.validateIsLogin()==false){
                return true;
            }else{
                /**
                 * TODO 这个地方判断是否需要登陆,如果需要登陆那么就获取sessionID
                 * 或者token来获取用户登陆依据,如果都失败跳转到登陆界面,需要记录当前请求id,如果登陆成功还跳转到当前请求地址上面来
                 */
                httpServletRequest.setAttribute("callbackAddress",httpServletRequest.getRequestURL());
                httpServletResponse.sendRedirect("my/login");
                return false;


            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
