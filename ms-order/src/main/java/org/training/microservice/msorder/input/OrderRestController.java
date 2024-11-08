package org.training.microservice.msorder.input;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RefreshScope
public class OrderRestController {

    @Value("${a.b.c}")
    private String abc;

    @GetMapping("/test1")
    public String method(){
        return abc;
    }

    public void insert(){
    }

    public void update(){
    }

    public void delete(){
    }

    public void query(){
    }

    public void insert2(){
    }

    public void update2(){
    }

    public void delete2(){
    }

    public void query2(){
    }

}
