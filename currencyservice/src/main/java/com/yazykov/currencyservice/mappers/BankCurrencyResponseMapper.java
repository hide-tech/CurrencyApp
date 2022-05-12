package com.yazykov.currencyservice.mappers;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.dto.CurrencyUnitDto;
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
        CurrencyUnitDto eur = response.getRates().stream().filter(unit -> unit.getName().equals("EUR"))
                .findAny().orElse(null);
        if (eur!=null) {
            currency.setEurValue(eur.getValue());
        } else {
            currency.setEurValue(BigDecimal.ZERO);
        }
        CurrencyUnitDto gbp = response.getRates().stream().filter(unit -> unit.getName().equals("GBP"))
                .findAny().orElse(null);
        if (gbp!=null) {
            currency.setGbpValue(gbp.getValue());
        } else {
            currency.setGbpValue(BigDecimal.ZERO);
        }
        CurrencyUnitDto jpy = response.getRates().stream().filter(unit -> unit.getName().equals("JPY"))
                .findAny().orElse(null);
        if (jpy!=null) {
            currency.setJpyValue(jpy.getValue());
        } else {
            currency.setJpyValue(BigDecimal.ZERO);
        }

        return currency;
    }

    public abstract BankCurrencyResponse currencyToBankCurrencyResponse(Currency currency);
}
