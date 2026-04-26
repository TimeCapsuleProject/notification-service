package br.com.timecapsuleproject.notificationService.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static br.com.timecapsuleproject.timeCapsuleService.constants.RabbitMqConstants.*;

@Configuration
public class RabbitMqConfig {

    public static final String DLQ_NAME = "notification.dlq";
    public static final String DLX_NAME = "notification.dlx";

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DLX_NAME);
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DLQ_NAME);
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DLQ_NAME);
    }

    @Bean
    public Declarables queuesWithDlq() {
        return new Declarables(
                createQueueWithDlq(QUEUE_USER_INVITATION),
                createQueueWithDlq(QUEUE_USER_TURN_ADMIN),
                createQueueWithDlq(QUEUE_USER_LEAVE_NOTIFICATION),
                createQueueWithDlq(QUEUE_CAPSULE_OPENED),
                createQueueWithDlq(QUEUE_CAPSULE_CREATION_NOTIFICATION),
                createQueueWithDlq(QUEUE_NEW_MESSAGE_NOTIFICATION)
        );
    }

    private Queue createQueueWithDlq(String queueName) {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DLX_NAME);
        args.put("x-dead-letter-routing-key", DLQ_NAME);
        return new Queue(queueName, true, false, false, args);
    }
}
