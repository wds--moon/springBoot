package com.example.common.config;

import com.example.web.interceptor.MyInterceptor;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * Created by wendongshan on 2016/11/7.
 */
@Configuration
public class RemoteIpFilterConfig  extends WebMvcConfigurerAdapter {
    @Bean
    public RemoteIpFilter remoteIpFilter() {

        System.out.println("++++++++++++++remoteIpFilter+++++++++++++++++++++");
        return new RemoteIpFilter();
    }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        return new LocaleChangeInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new MyInterceptor());
    }

    /*@Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
    }*/


}
