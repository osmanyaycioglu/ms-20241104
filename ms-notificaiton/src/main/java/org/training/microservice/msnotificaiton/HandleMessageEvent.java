package org.training.microservice.msnotificaiton;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HandleMessageEvent {


    @RabbitListener(bindings = @QueueBinding(
            value =
            @Queue(value = "sms-notify-q", durable = "true", autoDelete = "false"),
            exchange =
            @Exchange(value = "notify-exchange", durable = "true", autoDelete = "false", type = ExchangeTypes.DIRECT),
            key = "sms-notify"
    )
    )
    public void handleSMS(NotifyMessage message){
        System.out.println("I got SMS : " + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value =
            @Queue(value = "email-notify-q", durable = "true", autoDelete = "false"),
            exchange =
            @Exchange(value = "notify-exchange", durable = "true", autoDelete = "false", type = ExchangeTypes.DIRECT),
            key = "email-notify"
    )
    )
    public void handleEMAIL(NotifyMessage message){
        System.out.println("I got EMAIL : " + message);
    }

    // eu.west.turkiye.email.nofication.group1.pr2
    @RabbitListener(bindings = @QueueBinding(
            value =
            @Queue(value = "europe-email-topic-notify-q", durable = "true", autoDelete = "false"),
            exchange =
            @Exchange(value = "notify-exchange-topic", durable = "true", autoDelete = "false", type = ExchangeTypes.TOPIC),
            key = "eu.west.turkiye.email.#"
    )
    )
    public void handleTopicEMAIL(NotifyMessage message){
        System.out.println("I got europe EMAIL : " + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value =
            @Queue(value = "all-email-topic-notify-q", durable = "true", autoDelete = "false"),
            exchange =
            @Exchange(value = "notify-exchange-topic", durable = "true", autoDelete = "false", type = ExchangeTypes.TOPIC),
            key = "*.*.*.email.#"
    )
    )
    public void handleTopicAllEMAIL(NotifyMessage message){
        System.out.println("I got Topic all EMAILS : " + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value =
            @Queue(value = "all-messages-topic-notify-q", durable = "true", autoDelete = "false"),
            exchange =
            @Exchange(value = "notify-exchange-topic", durable = "true", autoDelete = "false", type = ExchangeTypes.TOPIC),
            key = "#"
    )
    )
    public void handleTopicAll(NotifyMessage message){
        System.out.println("I got Topic all messages : " + message);
    }

}
