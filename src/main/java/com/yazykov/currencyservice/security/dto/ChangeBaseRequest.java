package com.yazykov.currencyservice.security.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ChangeBaseRequest {
    private Long id;
    private String baseFrom;
    private String baseTo;
    private BigDecimal newAmount;
}
