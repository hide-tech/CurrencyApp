package com.yazykov.currencyservice.service;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.dto.CurrencyResponse;
import com.yazykov.currencyservice.mappers.BankCurrencyResponseMapper;
import com.yazykov.currencyservice.mappers.CurrencyResponseMapper;
import com.yazykov.currencyservice.model.Currency;
import com.yazykov.currencyservice.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyService {

    private final BankExchangeClient client;
    private final CurrencyRepository repository;
    private final CurrencyResponseMapper currencyResponseMapper;
    private final BankCurrencyResponseMapper bankCurrencyResponseMapper;

    public CurrencyResponse getLatestCurrency(){
        Currency latestCurrency = repository.findFirstByOrderByCheckedAtDesc();
        return currencyResponseMapper.currencyToCurrencyResponse(latestCurrency);
    }

    @Scheduled(fixedRate = 30000000)
    private void setCheckTimeAndSaveData(){

        BankCurrencyResponse response = client.getCurrencyFromBank();

        if (response==null){
            log.error("Something wrong with connection to bank");
        } else {

            Currency currency = bankCurrencyResponseMapper.bankCurrencyResponseToCurrency(response);
            currency = repository.save(currency);

            log.info("Currency with id: {} has been saved successfully",currency.getId());
        }
    }
}
