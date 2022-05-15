package com.yazykov.currencyservice.mappers;

import com.yazykov.currencyservice.dto.CurrencyResponse;
import com.yazykov.currencyservice.dto.CurrencyUnit;
import com.yazykov.currencyservice.model.Currency;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-15T21:27:48+0300",
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
        List<CurrencyUnit> list = currency.getRates();
        if ( list != null ) {
            currencyResponse.setRates( new ArrayList<CurrencyUnit>( list ) );
        }

        return currencyResponse;
    }

    @Override
    public Currency currencyResponseToCurrency(CurrencyResponse currencyResponse) {
        if ( currencyResponse == null ) {
            return null;
        }

        Currency currency = new Currency();

        currency.setCheckedAt( currencyResponse.getCheckedAt() );
        List<CurrencyUnit> list = currencyResponse.getRates();
        if ( list != null ) {
            currency.setRates( new ArrayList<CurrencyUnit>( list ) );
        }

        return currency;
    }
}
