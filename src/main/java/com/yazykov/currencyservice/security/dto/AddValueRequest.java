package com.yazykov.currencyservice.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class AddValueRequest {
    private Long id;
    private String base;
    private BigDecimal value;
}
