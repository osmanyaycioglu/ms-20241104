package org.training.microservice.msnotificaiton;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HandleMessageEvent {


    @RabbitListener(queues = {"my-queue"})
    public void method(String message){
    }

}
