package com.yazykov.currencyservice.bot.service;

import com.yazykov.currencyservice.model.Curr;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyModeService {

    private final Map<Long, Curr> originalCurrency = new HashMap<>();
    private final Map<Long, Curr> targetCurrency = new HashMap<>();

    public Curr getOriginalCurrency(Long chatId){
        return originalCurrency.getOrDefault(chatId, Curr.USD);
    }

    public Curr getTargetCurrency(Long chatId){
        return targetCurrency.getOrDefault(chatId, Curr.USD);
    }

    public void setOriginalCurrency(Long chatId, Curr currency){
        originalCurrency.put(chatId, currency);
    }

    public void setTargetCurrency(Long chatId, Curr currency){
        targetCurrency.put(chatId, currency);
    }
}
