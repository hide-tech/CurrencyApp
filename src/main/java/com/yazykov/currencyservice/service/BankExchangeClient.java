package com.yazykov.currencyservice.service;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class BankExchangeClient {

    private final RestTemplate restTemplate;

    @Value("${bank.api.uri}")
    private String bankUri;
    @Value("${bank.api.value}")
    private String headerValue;
    @Value("${bank.api.header}")
    private String header;

    public BankCurrencyResponse getCurrencyFromBank(){
        RequestEntity<Void> request = RequestEntity.get(bankUri)
                .header(header, headerValue)
                .build();
        ResponseEntity<BankCurrencyResponse> response = restTemplate
                .exchange(request, BankCurrencyResponse.class);
        return response.getBody();
    }

}
