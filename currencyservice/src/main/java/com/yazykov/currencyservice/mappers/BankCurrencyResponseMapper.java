package com.yazykov.currencyservice.mappers;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.model.Currency;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.time.LocalTime;

@Mapper(componentModel = "spring")
public abstract class BankCurrencyResponseMapper {

    public Currency bankCurrencyResponseToCurrency(BankCurrencyResponse response){
        if (response==null)
            return null;

        Currency currency = new Currency();
        currency.setCheckedAt(response.getDate().atTime(LocalTime.now()));
        currency.setUsdValue(new BigDecimal("1.0"));
        currency.setEurValue(response.getRates().stream().filter(unit -> unit.getName().equals("EUR"))
                .findAny().get().getValue());
        currency.setGbpValue(response.getRates().stream().filter(unit -> unit.getName().equals("GBP"))
                .findAny().get().getValue());
        currency.setJpyValue(response.getRates().stream().filter(unit -> unit.getName().equals("JPY"))
                .findAny().get().getValue());

        return currency;
    }

    public abstract BankCurrencyResponse currencyToBankCurrencyResponse(Currency currency);
}
