package com.yazykov.currencyservice.mappers;

import com.yazykov.currencyservice.dto.CurrencyResponse;
import com.yazykov.currencyservice.model.Currency;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-27T10:56:50+0300",
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
        Map<String, BigDecimal> map = currency.getRates();
        if ( map != null ) {
            currencyResponse.setRates( new LinkedHashMap<String, BigDecimal>( map ) );
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
        Map<String, BigDecimal> map = currencyResponse.getRates();
        if ( map != null ) {
            currency.setRates( new LinkedHashMap<String, BigDecimal>( map ) );
        }

        return currency;
    }
}
