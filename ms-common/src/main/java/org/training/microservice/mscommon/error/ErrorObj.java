package org.training.microservice.mscommon.error;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ErrorObj {
    private List<ErrorObj> subErrors;
    private String desc;
    private Integer errorCode;


    public ErrorObj() {
    }

    @Builder(setterPrefix = "with")
    public ErrorObj(final List<ErrorObj> subErrorsParam,
                    final String descParam,
                    final Integer errorCodeParam) {
        subErrors = subErrorsParam;
        desc      = descParam;
        errorCode = errorCodeParam;
    }
}
