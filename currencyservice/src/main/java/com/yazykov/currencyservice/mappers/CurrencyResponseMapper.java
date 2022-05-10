package com.yazykov.currencyservice.mappers;

import com.yazykov.currencyservice.dto.CurrencyResponse;
import com.yazykov.currencyservice.model.Currency;
import org.mapstruct.Mapper;

@Mapper
public interface CurrencyResponseMapper {

    CurrencyResponse currencyToCurrencyResponse(Currency currency);

    Currency currencyResponseToCurrency(CurrencyResponse currencyResponse);
}
