package com.yazykov.currencyservice.service;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.throwable.ConnectionToBankException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BankExchangeClientTest {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private BankExchangeClient client;
    @Value("${bank.api.uri}")
    private String bankUri;
    @Value("${bank.api.value}")
    private String headerValue;
    @Value("${bank.api.header}")
    private String header;

    @SneakyThrows
    @Test
    void getCurrencyFromBank_thenOk() {
        //init
        RequestEntity<Void> request = RequestEntity.get(bankUri)
                .header(header, headerValue)
                .build();
        BankCurrencyResponse bankResponse = new BankCurrencyResponse("USD",
                LocalDate.of(2020, 10, 5), Map.of("EUR",
                new BigDecimal("1.1")),
                true, 17645L);
        Mockito.when(restTemplate.exchange(request, BankCurrencyResponse.class))
                .thenReturn(new ResponseEntity<BankCurrencyResponse>(bankResponse, HttpStatus.OK));
        //when
        BankCurrencyResponse expected = client.getCurrencyFromBank();
        //then
        assertEquals(expected, bankResponse);
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(Mockito.eq(bankUri),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.eq(request),
                        Mockito.eq(BankCurrencyResponse.class));
    }
}