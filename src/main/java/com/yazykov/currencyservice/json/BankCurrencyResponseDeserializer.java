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

        BigDecimal eur, usd, gbp, jpy;
        TreeNode usdNode = treeNode.get("rates").get("USD");
        if (usdNode == null){
            usd = BigDecimal.ZERO;
        } else {
            usd = BigDecimal.valueOf(((DoubleNode) usdNode).asDouble());
        }
        TreeNode eurNode = treeNode.get("rates").get("EUR");
        if (eurNode == null){
            eur = BigDecimal.ZERO;
        } else {
            eur = BigDecimal.valueOf(((DoubleNode) eurNode).asDouble());
        }
        TreeNode gbpNode = treeNode.get("rates").get("GBP");
        if (gbpNode == null){
            gbp = BigDecimal.ZERO;
        } else {
            gbp = BigDecimal.valueOf(((DoubleNode) gbpNode).asDouble());
        }
        TreeNode jpyNode = treeNode.get("rates").get("JPY");
        if (jpyNode == null){
            jpy = BigDecimal.ZERO;
        } else {
            jpy = BigDecimal.valueOf(((DoubleNode) jpyNode).asDouble());
        }
        List<CurrencyUnitDto> rates = List.of(new CurrencyUnitDto("EUR",eur),
                new CurrencyUnitDto("GBP",gbp), new CurrencyUnitDto("JPY", jpy),
                new CurrencyUnitDto("USD",usd));
        response.setRates(rates);

        return response;
    }
}
