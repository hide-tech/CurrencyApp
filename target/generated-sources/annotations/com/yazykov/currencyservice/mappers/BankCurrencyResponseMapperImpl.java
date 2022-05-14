package com.yazykov.currencyservice.mappers;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.model.Currency;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-14T20:41:31+0300",
    comments = "version: 1.5.0.RC1, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class BankCurrencyResponseMapperImpl extends BankCurrencyResponseMapper {

    @Override
    public BankCurrencyResponse currencyToBankCurrencyResponse(Currency currency) {
        if ( currency == null ) {
            return null;
        }

        BankCurrencyResponse bankCurrencyResponse = new BankCurrencyResponse();

        return bankCurrencyResponse;
    }
}
