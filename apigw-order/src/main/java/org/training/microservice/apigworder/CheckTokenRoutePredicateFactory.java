package org.training.microservice.apigworder;

import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

@Component
public class CheckTokenRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckTokenRoutePredicateFactory.TokenCheckConfig> {

    public CheckTokenRoutePredicateFactory() {
        super(TokenCheckConfig.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(final TokenCheckConfig config) {
        return swe -> {
            System.out.println("Predicate run with config : " + config);
            return true;
        };
    }

    @Data
    public static class TokenCheckConfig {
        private boolean checkAll = false;
        private String subject;
    }

}
