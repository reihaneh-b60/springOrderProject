package readyInterview.springclaudeproject.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import readyInterview.springclaudeproject.configure.RabbitMQConfig;

@Service
public class NotificationProducer {

    private final RabbitTemplate rabbitTemplate;

    public NotificationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNotification(String message){
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME,message);
    }

}
