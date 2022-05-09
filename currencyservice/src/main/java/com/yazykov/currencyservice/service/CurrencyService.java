package com.yazykov.currencyservice.service;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.dto.CurrencyRequest;
import com.yazykov.currencyservice.model.Currency;
import com.yazykov.currencyservice.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyService {

    private BankExchangeClient client;

    private CurrencyRepository repository;

    private LocalDateTime checkTime;

    public CurrencyRequest getLatestCurrency(){
        Currency latestCurrency = repository.findFirstByOrderByCheckedAtDesc();
        return mapToDto(latestCurrency);
    }

    private CurrencyRequest mapToDto(Currency currency) {
        return CurrencyRequest.builder()
                .checkingTime(currency.getCheckedAt())
                .usd(currency.getUsdValue())
                .eur(currency.getEurValue())
                .gbp(currency.getGbpValue())
                .jpy(currency.getJpyValue()).build();

    }

    @PostConstruct
    public void checkAndSetCurrency(){
        LocalDateTime nowTime = LocalDateTime.now();
        setCheckTimeAndSaveData();

        while (true){
            if (nowTime.isAfter(checkTime)){
                setCheckTimeAndSaveData();
            }
        }
    }

    private void setCheckTimeAndSaveData(){
        checkTime = LocalDateTime.now().plusDays(1L);
        BankCurrencyResponse response = client.getCurrencyFromBank();
        if (response==null){
            log.error("Something wrong with connection with bank");
        }
        Currency currency = mapToCurrency(response);
        currency = repository.save(currency);
        log.info("Currency with id: {} has been saved successfully",currency.getId());
    }

    private Currency mapToCurrency(BankCurrencyResponse response){
        Currency currency = new Currency();
        LocalDateTime currencyTimestamp = response.getDate().atTime(response.getTimestamp());
        currency.setCheckedAt(currencyTimestamp);
        currency.setUsdValue(1.0);
        currency.setJpyValue(response.getRates().get("JPY"));
        currency.setEurValue(response.getRates().get("EUR"));
        currency.setGbpValue(response.getRates().get("GBP"));
        return currency;
    }
}
