package com.yazykov.currencyservice.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.yazykov.currencyservice.dto.CurrencyResponse;
import com.yazykov.currencyservice.dto.CurrencyUnit;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

@JsonComponent
public class CurrencyResponseSerializer extends JsonSerializer<CurrencyResponse> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss");

    @Override
    public void serialize(CurrencyResponse currencyResponse, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("checkedAt",
                FORMATTER.format(currencyResponse.getCheckedAt()));
        jsonGenerator.writeArrayFieldStart("rates");
        jsonGenerator.writeStartArray();
        for (CurrencyUnit unit: currencyResponse.getRates()){
            jsonGenerator.writeStringField(unit.getName(), unit.getValue().toString());
        }
        jsonGenerator.writeEndArray();
    }
}
