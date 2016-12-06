package com.example.common.config;

import com.example.common.util.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * Created by wendongshan on 2016/11/2.
 * 重试代理类
 */

@Service
public class RetryHttpClientProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryHttpClientProxy.class);


    @Retryable(value= {RemoteAccessException.class},maxAttempts = 3,backoff = @Backoff(delay = 5000,multiplier = 1))
    public void getCall(String url) throws Exception {
        LOGGER.info("do get.请求..");
        String result= HttpClientUtil.getHttpUrl(url);
        if(StringUtils.isBlank(result)){
            throw new RemoteAccessException("接口调用异常,返回值为null!");
        }

    }

    @Retryable(value= {RemoteAccessException.class},maxAttempts = 3,backoff = @Backoff(delay = 5000,multiplier = 1))
    public void postCall(String url,String data) throws Exception {
        LOGGER.info("do post...");
        String result=HttpClientUtil.postHttpUrl(url,data);
        if(StringUtils.isBlank(result)){
            throw new RemoteAccessException("接口调用异常,返回值为null!");
        }

    }

    @Recover
    public void recover(RemoteAccessException e) {
        System.out.println("异常信息:"+e.getMessage());
    }
}
