package readyInterview.springclaudeproject.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import readyInterview.springclaudeproject.configure.RabbitMQConfig;

@Component
public class NotificationConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveNotification(String message){
        System.out.println("Received Notification: "+message);
    }
}
