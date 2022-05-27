package com.yazykov.currencyservice.throwable;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionHandler extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public ExceptionHandler(String message) {
        super();
        this.message = message;
    }
}
