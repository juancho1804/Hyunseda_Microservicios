package com.api.usuarioservice.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ErrorsPayload {

    public final List<ErrorJSON> errors;

    public ErrorsPayload(List<UserError> applicationErrors) {
        this.errors = new ArrayList<>();
        applicationErrors.forEach(applicationError -> errors.add(fromApplicationError(applicationError)));
    }

    private ErrorJSON fromApplicationError(UserError error) {
        return new ErrorJSON(error.code, error.field, error.description);
    }
}
