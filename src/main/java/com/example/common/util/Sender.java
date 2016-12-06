package com.example.common.util;

import com.alibaba.fastjson.JSON;
import com.example.entity.User;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;


/**
 * Created by liaoqianyang on 2016/11/17.
 *
 * @EnableScheduling 定时任务注解器
 */
@Component
public class Sender implements RabbitTemplate.ConfirmCallback {


    /**
     * 注解bean的时候不要注解在需要初始化连接mq,不然会造成项目部署失败
     * @return
     */

    @Bean
    public RetryTemplate getRetryTemplate() {
        return new RetryTemplate();
    }

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
    }

    @Bean
    public Queue queue() {
        return new Queue("test_queue");
    }

    /**
     * 这个bean 是初始化的时候已经建立mq连接
     * @return
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange("test_exchange");
    }

    /**
     * 设置为广播方式
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("test_fanoutExchange");
    }

    /**
     * 设置为主题,匹配表达式
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("test_topicExchange");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with("moon_routingKey");
    }

    public void send(String exchange, String routingKey, Object sendData) {

        rabbitTemplate.setRetryTemplate(getRetryTemplate());
        rabbitTemplate.convertAndSend(exchange, routingKey, sendData, new CorrelationData(rabbitTemplate.getUUID()));
    }

//     @Scheduled(fixedDelay = 3000)

    public void send() {
        send("test_exchange", "test_routingKey", JSON.toJSONString(new User("张三", "123456")));
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println(" 回调id:" + correlationData);
        if (ack) {
            System.out.println("消息成功消费");
        } else {
            System.out.println("消息消费失败:" + cause);
        }
    }
}
