package com.example.common.config;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by liaoqianyang on 2016/10/26.
 * mybatis分页插件
 */
@Configuration
public class MyBatisConfiguration {

    private static Logger logger = LoggerFactory.getLogger(MyBatisConfiguration.class);

    @Bean
    public PageHelper pageHelper() {
        logger.info("=================MyBatisConfiguration.pageHelper()================");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }

}
