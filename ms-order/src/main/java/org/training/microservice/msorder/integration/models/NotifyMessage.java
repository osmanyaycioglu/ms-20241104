package org.training.microservice.msorder.integration.models;

import lombok.Data;

@Data
public class NotifyMessage
{
    private String msg;
    private String dest;
}
