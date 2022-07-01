package com.yazykov.currencyservice.config;

import com.yazykov.currencyservice.bot.TestBot;
import com.yazykov.currencyservice.bot.service.CurrencyModeService;
import com.yazykov.currencyservice.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@RequiredArgsConstructor
public class BotConfig {

    private final CurrencyService currencyService;
    private final CurrencyModeService modeService;

    @SneakyThrows
    @Bean
    public TelegramBotsApi telegramBotsApi(){
        TestBot bot = new TestBot(currencyService, modeService);
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(bot);
        return api;
    }
}
