package org.training.microservice.msorder.input.models;

public class Response<T> {
    private boolean errorOccurred;
    private String  errorDesc;
    private Integer errorCode;
    private T       response;
}
