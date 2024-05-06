package com.example.usuarioservice.exceptions;

public class UserError {
    /**
     * Codigo del error
     */
    public final EnumErrorCodes code;
    /**
     * Campo del error
     */
    public final String field;
    /**
     * Descripción del error
     */
    public final String description;

    public UserError(EnumErrorCodes code, String field, String description) {
        this.code = code;
        this.field = field;
        this.description = description;
    }
}
