package com.example.common.auth;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/8/7 0007.
 * 这样的登陆判断,一般是使用注解实现简单方便
 *
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthLogin {

    boolean validateIsLogin() default true;
}
