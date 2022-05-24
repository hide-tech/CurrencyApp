package com.yazykov.currencyservice.service;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.throwable.ConnectionToBankException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
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

    public BankCurrencyResponse getCurrencyFromBank() {
        log.info("rest template method getCurrencyFromBank");

        RequestEntity<Void> request = RequestEntity.get(bankUri)
                .header(header, headerValue)
                .build();
        ResponseEntity<BankCurrencyResponse> response = restTemplate
                .exchange(request, BankCurrencyResponse.class);

        return response.getStatusCode() == HttpStatus.OK ? response.getBody() : null;
    }

}
