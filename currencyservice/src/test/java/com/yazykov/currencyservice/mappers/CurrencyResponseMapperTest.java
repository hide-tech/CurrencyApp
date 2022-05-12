package com.yazykov.currencyservice.mappers;

import com.yazykov.currencyservice.dto.CurrencyResponse;
import com.yazykov.currencyservice.model.Currency;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyResponseMapperTest {

    private CurrencyResponseMapper mapper = Mappers.getMapper(CurrencyResponseMapper.class);

    @Test
    void currencyToCurrencyResponse() {
        Currency currency = new Currency(15L, LocalDateTime.now(), new BigDecimal("1.0"),
                new BigDecimal("1.1"), new BigDecimal("1.2"), new BigDecimal("1.3"));

        CurrencyResponse response = mapper.currencyToCurrencyResponse(currency);

        checkResponses(currency, response);
    }

    @Test
    void currencyResponseToCurrency() {
        CurrencyResponse response = new CurrencyResponse(LocalDateTime.now(), new BigDecimal("1.0"),
                new BigDecimal("1.1"), new BigDecimal("1.2"), new BigDecimal("1.3"));

        Currency currency = mapper.currencyResponseToCurrency(response);

        checkResponses(currency, response);
    }

    void checkResponses(Currency currency, CurrencyResponse response){
        assertEquals(currency.getCheckedAt(), response.getCheckedAt());
        assertEquals(currency.getUsdValue(), response.getUsdValue());
        assertEquals(currency.getEurValue(), response.getEurValue());
        assertEquals(currency.getGbpValue(), response.getGbpValue());
        assertEquals(currency.getJpyValue(), response.getJpyValue());
    }
}