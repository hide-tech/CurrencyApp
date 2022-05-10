package com.yazykov.currencyservice.mappers;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.model.Currency;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.time.LocalTime;

@Mapper
public abstract class BankCurrencyResponseMapper {

    public Currency bankCurrencyResponseToCurrency(BankCurrencyResponse response){
        return Currency.builder()
                .checkedAt(response.getDate().atTime(LocalTime.now()))
                .usdValue(new BigDecimal("1.0"))
                .eurValue(response.getRates().stream().filter(unit -> unit.getName().equals("EUR")).findAny().get().getValue())
                .gbpValue(response.getRates().stream().filter(unit -> unit.getName().equals("GBP")).findAny().get().getValue())
                .jpyValue(response.getRates().stream().filter(unit -> unit.getName().equals("JPY")).findAny().get().getValue())
                .build();
    }
}
