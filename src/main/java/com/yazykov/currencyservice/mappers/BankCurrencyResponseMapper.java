package com.yazykov.currencyservice.mappers;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.dto.CurrencyUnit;
import com.yazykov.currencyservice.model.Currency;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BankCurrencyResponseMapper {

    public Currency bankCurrencyResponseToCurrency(BankCurrencyResponse response){
        Currency currency = new Currency();

        currency.setCheckedAt(response.getDate().atTime(LocalTime.now()));
        List<CurrencyUnit> rates = response.getRates();
        rates.add(0,new CurrencyUnit(response.getBase(), BigDecimal.ONE));
        currency.setRates(rates);

        return currency;
    };
}
