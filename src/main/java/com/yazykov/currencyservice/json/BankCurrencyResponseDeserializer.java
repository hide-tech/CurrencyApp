package com.yazykov.currencyservice.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.dto.CurrencyUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@JsonComponent
public class BankCurrencyResponseDeserializer extends JsonDeserializer<BankCurrencyResponse> {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public BankCurrencyResponse deserialize(JsonParser jsonParser,
                                            DeserializationContext deserializationContext) throws IOException, JacksonException {
        BankCurrencyResponse response = new BankCurrencyResponse();

        List<CurrencyUnit> result = new LinkedList<>();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        if (node == null){
            log.error("BankCurrencyResponse parsing fault");
        }
        response.setBase(node.get("base").asText());
        response.setDate(LocalDate.parse(node.get("date").asText(),formatter));
        response.setSuccess(node.get("success").asBoolean());
        response.setTimestamp(node.get("timestamp").asLong());

        Iterator<Map.Entry<String, JsonNode>> fields = node.get("rates").fields();
        while (fields.hasNext()){
            Map.Entry<String, JsonNode> jsonField = fields.next();
            result.add(new CurrencyUnit((String) jsonField.getKey(),
                    BigDecimal.valueOf(jsonField.getValue().asDouble())));
        }
        response.setRates(result);

        return response;
    }
}
