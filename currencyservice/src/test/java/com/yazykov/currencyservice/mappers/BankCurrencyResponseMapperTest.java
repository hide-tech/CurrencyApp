package com.yazykov.currencyservice.mappers;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.dto.CurrencyUnitDto;
import com.yazykov.currencyservice.model.Currency;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankCurrencyResponseMapperTest {

    private BankCurrencyResponseMapper mapper = Mappers.getMapper(BankCurrencyResponseMapper.class);

    @Test
    void bankCurrencyResponseToCurrencyCorrect() {
        //given
        BankCurrencyResponse response = new BankCurrencyResponse("USD", LocalDate.now(),
                List.of(new CurrencyUnitDto("EUR",new BigDecimal("1.1")),
                        new CurrencyUnitDto("GBP",new BigDecimal("1.2")),
                        new CurrencyUnitDto("JPY",new BigDecimal("1.3")))
                , true,12345L);
        //when
        Currency currency = mapper.bankCurrencyResponseToCurrency(response);
        //then
        assertEquals(currency.getEurValue(),new BigDecimal("1.1"));
        assertEquals(currency.getUsdValue(),new BigDecimal("1.0"));
        assertEquals(currency.getGbpValue(),new BigDecimal("1.2"));
        assertEquals(currency.getJpyValue(),new BigDecimal("1.3"));
    }

    @Test
    void bankCurrencyResponseToCurrencyCorrectWithNullFields() {
        //given
        BankCurrencyResponse response = new BankCurrencyResponse("USD", LocalDate.now(),
                new ArrayList<CurrencyUnitDto>(), true,12345L);
        //when
        Currency currency = mapper.bankCurrencyResponseToCurrency(response);
        //then
        assertEquals(currency.getUsdValue(),new BigDecimal("1.0"));
        assertEquals(currency.getEurValue(),BigDecimal.ZERO);
        assertEquals(currency.getGbpValue(),BigDecimal.ZERO);
        assertEquals(currency.getJpyValue(),BigDecimal.ZERO);
    }
}