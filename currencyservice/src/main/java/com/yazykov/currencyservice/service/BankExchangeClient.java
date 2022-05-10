package com.yazykov.currencyservice.service;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class BankExchangeClient {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${bank.api.uri}")
    private String bankUri;
    private String header = "nA1gAcB38WdIUNMKYYG8UfBmR9RIErVp";

    public BankCurrencyResponse getCurrencyFromBank(){
        RequestEntity<Void> request = RequestEntity.get(bankUri)
//                .accept(MediaType.APPLICATION_JSON)
                .header("apikey", header)
                .build();
        ResponseEntity<BankCurrencyResponse> response = restTemplate.exchange(request, BankCurrencyResponse.class);
        return response.getBody();
    }

}
