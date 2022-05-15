package com.yazykov.currencyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyResponse {
    private LocalDateTime checkedAt;
    private List<CurrencyUnit> rates;
}
