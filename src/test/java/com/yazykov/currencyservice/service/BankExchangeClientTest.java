package com.yazykov.currencyservice.service;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.throwable.ConnectionToBankException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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

    private BankExchangeClient client;

    private String bankUri = "uri";
    private String headerValue = "apiv";
    private String header = "api";

    @BeforeEach
    void setUp(){
        client = new BankExchangeClient(restTemplate);
    }

    @SneakyThrows
    @Test
    void getCurrencyFromBank_thenOk() {
        //init
        RequestEntity<Void> request = RequestEntity.get(bankUri)
                .header(header, headerValue)
                .build();
        Mockito.doReturn(ResponseEntity.of(Optional.of(new BankCurrencyResponse("USD",
                        LocalDate.of(2020, 10,5), Map.of("EUR",
                        new BigDecimal("1.1"),"JPY", new BigDecimal("120.15")),
                        true, 17645L))))
                .when(restTemplate)
                .exchange(request, BankCurrencyResponse.class);
        //when
        BankCurrencyResponse expected = client.getCurrencyFromBank();
        //then
        assertEquals("USD", expected.getBase());
        assertEquals(LocalDate.of(2020, 10, 5), expected.getDate());
        assertTrue(expected.getSuccess());
        assertEquals(Map.of("EUR",
                new BigDecimal("1.1"),"JPY", new BigDecimal("120.15")), expected.getRates());
        assertEquals(17645L, expected.getTimestamp());
    }

    @Test
    void getCurrencyFromBank_thenFault() {
        //init
        RequestEntity<Void> request = RequestEntity.get(bankUri)
                .header(header, headerValue)
                .build();
        Mockito.doReturn(ResponseEntity.of(Optional.empty()))
                .when(restTemplate)
                .exchange(request, BankCurrencyResponse.class);
        //then
        assertThrows(ConnectionToBankException.class, () -> client.getCurrencyFromBank());
    }
}