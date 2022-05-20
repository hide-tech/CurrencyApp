package com.yazykov.currencyservice.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ChangeBaseRequest {
    private Long id;
    private String baseFrom;
    private String baseTo;
    private BigDecimal newAmount;
}
