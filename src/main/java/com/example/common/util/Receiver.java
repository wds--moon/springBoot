package com.example.common.util;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by liaoqianyang on 2016/11/17.
 */

@Component
public class Receiver {
    /*
        @RabbitListener(queues = "test_queue")
        @RabbitHandler
        public void onMessage(User user) throws IOException {
            System.out.println("==============" + user);
        }*/
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //@Bean
    public SimpleMessageListenerContainer messageContainer() {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(rabbitTemplate.getConnectionFactory());
        container.setQueueNames("test_queue");
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(new ChannelAwareMessageListener() {

            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                System.out.println("receive msg : " + new String(body, "utf-8"));
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true); //确认消息成功消费
            }
        });
        return container;
    }
}
