package com.yazykov.currencyservice.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class BankCurrencyResponse {
    private String base;
    private LocalDate date;
    private List<CurrencyUnitDto> rates;
    private Boolean success;
    private Long timestamp;
}
