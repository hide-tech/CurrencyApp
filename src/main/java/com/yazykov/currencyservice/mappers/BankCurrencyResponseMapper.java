package com.yazykov.currencyservice.mappers;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.model.Currency;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Mapper(componentModel = "spring")
public abstract class BankCurrencyResponseMapper {

    public Currency bankCurrencyResponseToCurrency(BankCurrencyResponse response){
        log.info("into custom mapper method bankCurrencyResponseToCurrency");

        Currency currency = new Currency();

        currency.setCheckedAt(response.getDate().atTime(LocalTime.now()));

        Map<String, BigDecimal> rates = new HashMap<>(Map.of(response.getBase(), BigDecimal.ONE));
        rates.putAll(response.getRates());
        currency.setRates(rates);

        return currency;
    };
}
