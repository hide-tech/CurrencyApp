package com.yazykov.currencyservice.controller;

import com.yazykov.currencyservice.dto.CurrencyRequest;
import com.yazykov.currencyservice.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency/latest")
@RequiredArgsConstructor
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public CurrencyRequest getCurrency(){
        return currencyService.getLatestCurrency();
    }

}
