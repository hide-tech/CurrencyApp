package com.yazykov.currencyservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yazykov.currencyservice.json.BankCurrencyResponseDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize(using = BankCurrencyResponseDeserializer.class)
public class BankCurrencyResponse {
    private String base;
    private LocalDate date;
    private List<CurrencyUnitDto> rates;
    private Boolean success;
    private Long timestamp;
}
