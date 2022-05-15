package com.yazykov.currencyservice.mappers;

import com.yazykov.currencyservice.dto.CurrencyResponse;
import com.yazykov.currencyservice.model.Currency;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyResponseMapperTest {

    private CurrencyResponseMapper mapper = Mappers.getMapper(CurrencyResponseMapper.class);

    @Test
    void currencyToCurrencyResponse() {
        //given

        //when

        //then

    }

    @Test
    void currencyResponseToCurrency() {
        //given

        //when

        //then

    }

    void checkResponses(Currency currency, CurrencyResponse response){

    }
}