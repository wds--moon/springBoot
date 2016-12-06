package com.example.common.config;

import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 *  修改springboot默认的编码为utf-8
 * Created by liaoqianyang on 2016/10/27.
 */
@Configuration
public class CharacterEncodingFilterConfiguration {

    private static Logger logger = LoggerFactory.getLogger(CharacterEncodingFilterConfiguration.class);

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        logger.info("===============characterEncodingFilter================");
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding(CharEncoding.UTF_8);
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
}
