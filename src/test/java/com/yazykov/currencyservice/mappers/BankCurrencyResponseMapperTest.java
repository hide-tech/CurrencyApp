package com.yazykov.currencyservice.mappers;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.model.Currency;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BankCurrencyResponseMapperTest {

    BankCurrencyResponseMapper mapper = Mappers.getMapper(BankCurrencyResponseMapper.class);

    @Test
    void bankCurrencyResponseToCurrency_thenOk() {
        //given
        BankCurrencyResponse response = new BankCurrencyResponse("USD",
                LocalDate.of(2020,10,3), Map.of("EUR", new BigDecimal("0.9"),
                "JPY", new BigDecimal("120.21")), true, 150L);
        //when
        Currency currency = mapper.bankCurrencyResponseToCurrency(response);
        //then
        assertEquals(response.getDate(), currency.getCheckedAt().toLocalDate());
        assertEquals(response.getRates().get("EUR"), currency.getRates().get("EUR"));
        assertEquals(currency.getRates().get(response.getBase()), BigDecimal.ONE);
        assertEquals(response.getRates().get("JPY"), currency.getRates().get("JPY"));
    }
}