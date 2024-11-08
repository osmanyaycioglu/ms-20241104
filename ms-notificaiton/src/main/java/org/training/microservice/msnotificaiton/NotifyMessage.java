package org.training.microservice.msnotificaiton;

import lombok.Data;

@Data
public class NotifyMessage
{
    private String msg;
    private String dest;
}
