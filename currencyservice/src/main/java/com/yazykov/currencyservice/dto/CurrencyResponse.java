package com.yazykov.currencyservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class CurrencyResponse {
    private LocalDateTime checkingTime;
    private BigDecimal usd;
    private BigDecimal eur;
    private BigDecimal gbp;
    private BigDecimal jpy;
}
