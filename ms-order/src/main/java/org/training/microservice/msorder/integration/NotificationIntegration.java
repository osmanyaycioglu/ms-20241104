package org.training.microservice.msorder.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.training.microservice.msorder.integration.models.NotifyMessage;

@Service
@RequiredArgsConstructor
public class NotificationIntegration {
    private final RabbitTemplate rabbitTemplate;


    public void sendEmail(String message,
                          String dest) {
        NotifyMessage notifyMessageLoc = new NotifyMessage();
        notifyMessageLoc.setMsg(message);
        notifyMessageLoc.setDest(dest);
        rabbitTemplate.convertAndSend("notify-exchange-topic",
                                      "eu.west.turkiye.email.nofication.group5.pr1",
                                      notifyMessageLoc);
    }
}
