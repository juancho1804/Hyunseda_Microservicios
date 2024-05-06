package com.example.usuarioservice.exceptions;

import java.util.List;

public class UserDomainException extends Exception{
    /**
     * Listado de errores
     */
    public final List<UserError> errors;

    public UserDomainException(List<UserError> errors) {
        this.errors = errors;
    }

}
