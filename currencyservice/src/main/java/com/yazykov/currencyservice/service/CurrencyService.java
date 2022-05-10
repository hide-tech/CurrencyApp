package com.yazykov.currencyservice.service;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.dto.CurrencyResponse;
import com.yazykov.currencyservice.mappers.BankCurrencyResponseMapper;
import com.yazykov.currencyservice.mappers.CurrencyResponseMapper;
import com.yazykov.currencyservice.model.Currency;
import com.yazykov.currencyservice.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyService {

    private final BankExchangeClient client;
    private final CurrencyRepository repository;
    private final BankCurrencyResponseMapper bankMapper;
    private final CurrencyResponseMapper currencyResponseMapper;

    public CurrencyResponse getLatestCurrency(){
        checkAndSetCurrency();
        Currency latestCurrency = repository.findFirst();
        return currencyResponseMapper.currencyToCurrencyResponse(latestCurrency);
    }

    public void checkAndSetCurrency(){
        LocalDateTime nowTime = LocalDateTime.now();
        setCheckTimeAndSaveData();
    }

    private void setCheckTimeAndSaveData(){
        BankCurrencyResponse response = client.getCurrencyFromBank();
        if (response==null){
            log.error("Something wrong with connection with bank");
        }
        Currency currency = bankMapper.bankCurrencyResponseToCurrency(response);
        currency = repository.save(currency);
        log.info("Currency with id: {} has been saved successfully",currency.getId());
    }
}
