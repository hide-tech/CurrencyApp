package com.yazykov.currencyservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class CurrencyUnitDto {
    private String name;
    private BigDecimal value;
}
