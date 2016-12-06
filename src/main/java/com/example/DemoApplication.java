package com.example;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 要部署在自己的服务器中需要继承SpringBootServletInitializer
 * 并且覆盖configure方法
 *EnableTransactionManagement 开启事务
 * EnableRetry 开启重试功能
 *
 */
@Configuration
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("com.example.mapper")
@SpringBootApplication
@EnableRetry
public class DemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * 继承SpringBootServletInitializer, 覆盖该方法,打包方式设置为war, 便可以打成war包部署在自己的web服务器中
	 * @param application
	 * @return
     */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}




}
