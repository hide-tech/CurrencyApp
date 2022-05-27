package com.yazykov.currencyservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
    private ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    @Test
    void getCurrencyFromBank_thenOk() {
        //init
        RequestEntity<Void> request = RequestEntity.get(bankUri)
                .header(header, headerValue)
                .build();
        String json = "{\n" +
                "  \"base\": \"USD\",\n" +
                "  \"date\": \"2021-03-17\",\n" +
                "  \"rates\": {\n" +
                "    \"EUR\": 0.813399,\n" +
                "    \"GBP\": 0.72007,\n" +
                "    \"JPY\": 107.346001\n" +
                "  },\n" +
                "  \"success\": true,\n" +
                "  \"timestamp\": 1519296206\n" +
                "}";
        BankCurrencyResponse bankResponse = mapper.convertValue(json, BankCurrencyResponse.class);
        when(restTemplate.exchange(ArgumentMatchers.any(RequestEntity.class), BankCurrencyResponse.class))
                .thenReturn(new ResponseEntity<>(bankResponse, HttpStatus.OK));
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