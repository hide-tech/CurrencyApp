package com.yazykov.currencyservice.service;

import com.yazykov.currencyservice.mappers.BankCurrencyResponseMapper;
import com.yazykov.currencyservice.mappers.CurrencyResponseMapper;
import com.yazykov.currencyservice.repository.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private CurrencyRepository repository;
    private BankExchangeClient client;
    private CurrencyResponseMapper mapper = Mappers.getMapper(CurrencyResponseMapper.class);
    private BankCurrencyResponseMapper bankMapper = Mappers.getMapper(BankCurrencyResponseMapper.class);

    private CurrencyService service;

    @BeforeEach
    void setUp() {
        service = new CurrencyService(client, repository, mapper, bankMapper);
    }

    @Test
    void getLatestCurrency() {
        //when
        service.getLatestCurrency();
        //then
        verify(repository).findFirstByOrderByCheckedAtDesc();
    }
}