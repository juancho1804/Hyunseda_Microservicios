package com.api.productservice.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ErrorsPayload {

    public final List<ErrorJSON> errors;

    public ErrorsPayload(List<ProductError> applicationErrors) {
        this.errors = new ArrayList<>();
        applicationErrors.forEach(applicationError -> errors.add(fromApplicationError(applicationError)));
    }

    private ErrorJSON fromApplicationError(ProductError error) {
        return new ErrorJSON(error.code, error.field, error.description);
    }
}
