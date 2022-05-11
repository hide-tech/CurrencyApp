package com.yazykov.currencyservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CurrencyResponse {
    private LocalDateTime checkedAt;
    private BigDecimal usdValue;
    private BigDecimal eurValue;
    private BigDecimal gbpValue;
    private BigDecimal jpyValue;
}
