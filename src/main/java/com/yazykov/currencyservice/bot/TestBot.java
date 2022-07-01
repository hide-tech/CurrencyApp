package com.yazykov.currencyservice.bot;

import com.yazykov.currencyservice.bot.service.CurrencyModeService;
import com.yazykov.currencyservice.dto.CurrencyResponse;
import com.yazykov.currencyservice.model.Curr;
import com.yazykov.currencyservice.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Component
@RequiredArgsConstructor
public class TestBot extends TelegramLongPollingBot {

    private final CurrencyService service;
    private final CurrencyModeService modeService;

    @Override
    public String getBotUsername() {
        return "@ForTestMyFirstBot";
    }

    @Override
    public String getBotToken() {
        return "***";
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()){
            handleCallbackQuery(update.getCallbackQuery());
        } else if (update.hasMessage()){
            handleMessage(update.getMessage());
        }
    }

    @SneakyThrows
    private void handleCallbackQuery(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String[] param = callbackQuery.getData().split(":");
        String action = param[0];
        Curr newCurrency = Curr.valueOf(param[1]);
        switch (action){
            case "ORIGINAL" -> modeService.setOriginalCurrency(message.getChatId(), newCurrency);
            case "TARGET" -> modeService.setTargetCurrency(message.getChatId(), newCurrency);
        }
        List<List<InlineKeyboardButton>> buttons = getButtons(message);
        execute(EditMessageReplyMarkup.builder().chatId(message.getChatId().toString())
                .messageId(message.getMessageId())
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                .build());
    }

    @SneakyThrows
    private void handleMessage(Message message) {
        if (message.hasText() && message.hasEntities()){
            Optional<MessageEntity> messageEntity = message.getEntities()
                    .stream().filter(entity -> "bot_command".equals(entity.getType())).findFirst();
            if (messageEntity.isPresent()){
                String command = message.getText()
                        .substring(messageEntity.get().getOffset(), messageEntity.get().getLength());
                switch (command){
                    case "/currency" -> {
                        List<List<InlineKeyboardButton>> buttons = getButtons(message);
                        execute(SendMessage.builder().chatId(message.getChatId().toString())
                                .text("Please choose your original and target currencies")
                                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build()).build());
                    }
                }
            }
        }
        if (message.hasText()){
            String textMessage = message.getText();
            Optional<BigDecimal> value = parseBigDecimal(textMessage);
            Curr originalCurrency = modeService.getOriginalCurrency(message.getChatId());
            Curr targetCurrency = modeService.getTargetCurrency(message.getChatId());
            BigDecimal result = countResultAmount(originalCurrency, targetCurrency, value.get());
            if (value.isPresent()){
                execute(SendMessage.builder()
                        .chatId(message.getChatId())
                        .text(String.format("%4.2f %s is %4.2f %s", value.get(), originalCurrency.name()
                        , result, targetCurrency.name()))
                        .build());
            }
        }
    }

    private BigDecimal countResultAmount(Curr originalCurrency, Curr targetCurrency, BigDecimal value) {
        CurrencyResponse response = service.getLatestCurrency();
        Map<String, BigDecimal> rates = response.getRates();
        BigDecimal ratio = rates.get(targetCurrency.name())
                .divide(rates.get(originalCurrency.name()), RoundingMode.HALF_UP);
        BigDecimal result = value.multiply(ratio);
        return result;
    }

    private Optional<BigDecimal> parseBigDecimal(String textMessage) {
        try {
            return Optional.of(new BigDecimal(textMessage));
        } catch (Exception e){
            return Optional.empty();
        }
    }

    private List<List<InlineKeyboardButton>> getButtons(Message message) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        Curr originalCurrency = modeService.getOriginalCurrency(message.getChatId());
        Curr targetCurrency = modeService.getTargetCurrency(message.getChatId());

        for (Curr curr : Curr.values()) {
            buttons.add(
                    Arrays.asList(
                            InlineKeyboardButton.builder()
                                    .text(getCurrencyButton(originalCurrency, curr))
                                    .callbackData("ORIGINAL:" + curr).build(),
                            InlineKeyboardButton.builder()
                                    .text(getCurrencyButton(targetCurrency, curr))
                                    .callbackData("TARGET:" + curr).build()
                    ));
        }
        return buttons;
    }

    private String getCurrencyButton(Curr saved, Curr current){
        return current == saved ? current + "*" : current.name();
    }
}
