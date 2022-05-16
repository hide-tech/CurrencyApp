package com.yazykov.currencyservice.controller;

import com.yazykov.currencyservice.dto.CurrencyResponse;
import com.yazykov.currencyservice.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency/latest")
@RequiredArgsConstructor
@Slf4j
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public CurrencyResponse getCurrency(){
        log.info("into controller method getCurrency");
        return currencyService.getLatestCurrency();
    }

}
