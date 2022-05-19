package com.yazykov.currencyservice.mappers;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.dto.CurrencyResponse;
import com.yazykov.currencyservice.model.Currency;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyResponseMapperTest {

    private CurrencyResponseMapper mapper = Mappers.getMapper(CurrencyResponseMapper.class);

    @Test
    void currencyToCurrencyResponse_thenOk() {
        //given
        Currency currency = new Currency(15L, LocalDateTime.of(2021,5,
                24,16,25,34), Map.of("EUR", new BigDecimal("0.9"),
                "JPY", new BigDecimal("120.21"), "USD", BigDecimal.ONE));
        //when
        CurrencyResponse result = mapper.currencyToCurrencyResponse(currency);
        //then
        assertEquals(currency.getCheckedAt(), result.getCheckedAt());
        assertEquals(currency.getRates(), result.getRates());
    }

    @Test
    void currencyResponseToCurrency_thenOk() {
        //given
        CurrencyResponse response = new CurrencyResponse(LocalDateTime.of(2021,5,
                24,16,25,34), Map.of("EUR", new BigDecimal("0.9"),
                "JPY", new BigDecimal("120.21"), "USD", BigDecimal.ONE));
        //when
        Currency currency = mapper.currencyResponseToCurrency(response);
        //then
        assertEquals(response.getCheckedAt(), currency.getCheckedAt());
        assertEquals(response.getRates(), currency.getRates());
    }

}