package com.yazykov.currencyservice.mappers;

import com.yazykov.currencyservice.dto.CurrencyResponse;
import com.yazykov.currencyservice.model.Currency;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-14T20:41:31+0300",
    comments = "version: 1.5.0.RC1, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class CurrencyResponseMapperImpl implements CurrencyResponseMapper {

    @Override
    public CurrencyResponse currencyToCurrencyResponse(Currency currency) {
        if ( currency == null ) {
            return null;
        }

        CurrencyResponse currencyResponse = new CurrencyResponse();

        currencyResponse.setCheckedAt( currency.getCheckedAt() );
        currencyResponse.setUsdValue( currency.getUsdValue() );
        currencyResponse.setEurValue( currency.getEurValue() );
        currencyResponse.setGbpValue( currency.getGbpValue() );
        currencyResponse.setJpyValue( currency.getJpyValue() );

        return currencyResponse;
    }

    @Override
    public Currency currencyResponseToCurrency(CurrencyResponse currencyResponse) {
        if ( currencyResponse == null ) {
            return null;
        }

        Currency currency = new Currency();

        currency.setCheckedAt( currencyResponse.getCheckedAt() );
        currency.setUsdValue( currencyResponse.getUsdValue() );
        currency.setEurValue( currencyResponse.getEurValue() );
        currency.setGbpValue( currencyResponse.getGbpValue() );
        currency.setJpyValue( currencyResponse.getJpyValue() );

        return currency;
    }
}
