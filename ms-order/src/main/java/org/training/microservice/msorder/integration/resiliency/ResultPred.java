package org.training.microservice.msorder.integration.resiliency;

import java.util.function.Predicate;

public class ResultPred implements Predicate<String> {

    @Override
    public boolean test(final String stringParam) {
        System.out.println("--------------- Result Predicate : " + stringParam);
        if (stringParam.contains("abc")) {
            return true;
        }
        return false;
    }
}
