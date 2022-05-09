package com.yazykov.currencyservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CurrencyRequest {
    private LocalDateTime checkingTime;
    private Double usd;
    private Double eur;
    private Double gbp;
    private Double jpy;
}
