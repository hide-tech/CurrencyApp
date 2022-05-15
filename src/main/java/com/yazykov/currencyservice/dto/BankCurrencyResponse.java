package com.yazykov.currencyservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yazykov.currencyservice.json.BankCurrencyResponseDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = BankCurrencyResponseDeserializer.class)
public class BankCurrencyResponse {
    private String base;
    private LocalDate date;
    private List<CurrencyUnit> rates;
    private Boolean success;
    private Long timestamp;
}
