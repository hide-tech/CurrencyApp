package com.yazykov.currencyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankCurrencyResponse {
    private String base;
    private LocalDate date;
    private Map<String, BigDecimal> rates;
    private Boolean success;
    private Long timestamp;
}
