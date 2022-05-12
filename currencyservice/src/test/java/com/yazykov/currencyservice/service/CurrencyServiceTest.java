package com.yazykov.currencyservice.service;

import com.yazykov.currencyservice.mappers.BankCurrencyResponseMapper;
import com.yazykov.currencyservice.mappers.CurrencyResponseMapper;
import com.yazykov.currencyservice.mappers.CurrencyResponseMapperImpl;
import com.yazykov.currencyservice.repository.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private CurrencyRepository repository;
    private BankExchangeClient client;
    private BankCurrencyResponseMapper bankMapper;
    private CurrencyResponseMapper mapper = new CurrencyResponseMapperImpl();

    private CurrencyService service;

    @BeforeEach
    void setUp() {
        service = new CurrencyService(client, repository, bankMapper, mapper);
    }

    @Test
    void getLatestCurrency() {
        //when
        service.getLatestCurrency();
        //then
        verify(repository).findFirstByOrderByCheckedAtDesc();
    }
}