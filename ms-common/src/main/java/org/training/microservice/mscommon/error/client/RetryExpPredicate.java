package org.training.microservice.mscommon.error.client;

import org.springframework.web.client.RestClientResponseException;
import org.training.microservice.mscommon.error.ErrorObj;

import java.util.function.Predicate;

public class RetryExpPredicate implements Predicate<Throwable> {

    @Override
    public boolean test(final Throwable throwableParam) {
        System.out.println("*********** Retry check : " + throwableParam.getMessage());
        if (throwableParam instanceof RestClientResponseException) {
            RestClientResponseException rc            = (RestClientResponseException) throwableParam;
            ErrorObj responseBodyAsLoc = rc.getResponseBodyAs(ErrorObj.class);
            return switch (responseBodyAsLoc.getErrorCode()) {
                case 1024, 1025, 1048 -> false;
                default -> true;
            };
        }
        return true;
    }
}
