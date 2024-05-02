package com.api.productservice.exceptions;

import java.util.List;

public class ProductDomainException extends Exception {
    /**
     * Listado de errores
     */
    public final List<ProductError> errors;

    public ProductDomainException(List<ProductError> errors) {
        this.errors = errors;
    }

}
