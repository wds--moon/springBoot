package com.example.common.timetask;

import com.example.entity.User;
import com.example.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * 定时任务
 * 时分秒日月周
 * Created by liaoqianyang on 2016/10/28.
 * 要使用将下面的注解打开
 */
//@Configuration
//@EnableScheduling
public class SchedulingConfiguration {

    private static Logger logger = LoggerFactory.getLogger(SchedulingConfiguration.class);

    @Autowired
    private IUserService userService;

    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduler() {
        logger.info("============开始执行定时任务:每隔10秒查询用户信息=============");
        List<User> all = userService.findAll();
        logger.info("============获取的user信息:{}", all);
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduler2() {
        logger.info("============scheduler2开始执行定时任务:每隔10秒查询用户信息=============");
    }
}
