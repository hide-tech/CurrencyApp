package com.yazykov.currencyservice.service;

import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.dto.CurrencyResponse;
import com.yazykov.currencyservice.dto.CurrencyUnitDto;
import com.yazykov.currencyservice.model.Currency;
import com.yazykov.currencyservice.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyService {

    private final BankExchangeClient client;

    private final CurrencyRepository repository;

    private LocalDateTime checkTime;

    public CurrencyResponse getLatestCurrency(){
        checkAndSetCurrency();
        Currency latestCurrency = repository.findFirst();
        return mapToDto(latestCurrency);
    }

    private CurrencyResponse mapToDto(Currency currency) {
        return CurrencyResponse.builder()
                .checkingTime(currency.getCheckedAt())
                .usd(currency.getUsdValue())
                .eur(currency.getEurValue())
                .gbp(currency.getGbpValue())
                .jpy(currency.getJpyValue()).build();

    }

    public void checkAndSetCurrency(){
        LocalDateTime nowTime = LocalDateTime.now();
        setCheckTimeAndSaveData();

//        Runnable dailyCheck = () -> {
//            while (true){
//                if (nowTime.isAfter(checkTime)){
//                    setCheckTimeAndSaveData();
//                }
//            }
//        };
//        Thread runDailyCheck = new Thread(dailyCheck);
//        runDailyCheck.start();
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
        LocalDateTime currencyTimestamp = response.getDate().atTime(LocalTime.now());
        currency.setCheckedAt(currencyTimestamp);
        currency.setUsdValue(new BigDecimal("1.0"));
//        List<CurrencyUnitDto> rates = response.getRates();
//        rates.stream().
        return currency;
    }
}
