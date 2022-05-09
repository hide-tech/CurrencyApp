package com.yazykov.currencyservice.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class BankCurrencyResponse {
    private String base;
    private LocalDate date;
    private Map<String, Double> rates;
    private Boolean success;
    private Long timestamp;
}
