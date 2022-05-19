package com.yazykov.currencyservice.security.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AddValueRequest {
    private Long id;
    private String base;
    private BigDecimal value;
}
