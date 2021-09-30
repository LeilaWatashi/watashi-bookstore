package com.watashi.bookstore.util.validador;

import org.springframework.stereotype.Component;

@Component
public interface IValidator {
    String validar(Object o, String fieldName);
}