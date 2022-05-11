package com.yazykov.currencyservice.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.*;
import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.dto.CurrencyUnitDto;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@JsonComponent
public class BankCurrencyResponseDeserializer extends JsonDeserializer<BankCurrencyResponse> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public BankCurrencyResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);

        BankCurrencyResponse response = new BankCurrencyResponse();
        response.setBase(((TextNode) treeNode.get("base")).asText());
        String date = ((TextNode) treeNode.get("date")).asText();
        response.setDate(LocalDate.parse(date,FORMATTER));
        response.setSuccess(((BooleanNode) treeNode.get("success")).asBoolean());
        Long timestamp = ((IntNode) treeNode.get("timestamp")).asLong();
        response.setTimestamp(timestamp);

        BigDecimal eur = BigDecimal.valueOf(((DoubleNode) treeNode.get("rates").get("EUR")).asDouble());
        BigDecimal gbp = BigDecimal.valueOf(((DoubleNode) treeNode.get("rates").get("GBP")).asDouble());
        BigDecimal jpy = BigDecimal.valueOf(((DoubleNode) treeNode.get("rates").get("JPY")).asDouble());
        List<CurrencyUnitDto> rates = List.of(new CurrencyUnitDto("EUR",eur),
                new CurrencyUnitDto("GBP",gbp), new CurrencyUnitDto("JPY", jpy));
        response.setRates(rates);

        return response;
    }
}
