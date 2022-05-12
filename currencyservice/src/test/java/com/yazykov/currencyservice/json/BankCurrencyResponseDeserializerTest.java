package com.yazykov.currencyservice.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.yazykov.currencyservice.dto.BankCurrencyResponse;
import com.yazykov.currencyservice.dto.CurrencyUnitDto;
import lombok.SneakyThrows;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankCurrencyResponseDeserializerTest {

    JsonDeserializer<BankCurrencyResponse> deserializer = new BankCurrencyResponseDeserializer();

    @Test
    @SneakyThrows
    void deserializeCorrect() {
        // given
        String json = "{\"base\": \"USD\",\n" +
                "  \"date\": \"2021-03-17\",\n" +
                "  \"rates\": {\n" +
                "    \"EUR\": 0.813399,\n" +
                "    \"GBP\": 0.72007,\n" +
                "    \"JPY\": 107.346001\n" +
                "  },\n" +
                "  \"success\": true,\n" +
                "  \"timestamp\": 1519296206}";
        //init
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(BankCurrencyResponse.class, deserializer);
        objectMapper.registerModule(module);
        //when
        BankCurrencyResponse response = objectMapper.readValue(json,BankCurrencyResponse.class);
        List<CurrencyUnitDto> units = response.getRates();
        CurrencyUnitDto eur = units.stream().filter(unit -> unit.getName().equals("EUR"))
                .findAny().orElse(null);
        CurrencyUnitDto gbp = units.stream().filter(unit -> unit.getName().equals("GBP"))
                .findAny().orElse(null);
        CurrencyUnitDto jpy = units.stream().filter(unit -> unit.getName().equals("JPY"))
                .findAny().orElse(null);
        //then
        assertEquals(response.getBase(),"USD");
        assertEquals(response.getDate(), LocalDate.of(2021,3,17));
        assertEquals(response.getSuccess(), true);
        assertEquals(response.getTimestamp(), 1519296206L);
        assert eur != null;
        assertEquals(eur.getValue(), new BigDecimal("0.813399"));
        assert gbp != null;
        assertEquals(gbp.getValue(), new BigDecimal("0.72007"));
        assert jpy != null;
        assertEquals(jpy.getValue(), new BigDecimal("107.346001"));
    }

    @Test
    @SneakyThrows
    void deserializeCorrectWithNullFields(){
        //given
        String json = "{\"base\": \"USD\",\n" +
                "  \"date\": \"2021-03-17\",\n" +
                "  \"rates\": {\n" +
                "    \"EUR\": 0.813399,\n" +
                "    \"JPY\": 107.346001\n" +
                "  },\n" +
                "  \"success\": true,\n" +
                "  \"timestamp\": 1519296206}";
        //init
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(BankCurrencyResponse.class, deserializer);
        objectMapper.registerModule(module);
        //when
        BankCurrencyResponse response = objectMapper.readValue(json,BankCurrencyResponse.class);
        List<CurrencyUnitDto> units = response.getRates();
        CurrencyUnitDto eur = units.stream().filter(unit -> unit.getName().equals("EUR"))
                .findAny().orElse(null);
        CurrencyUnitDto gbp = units.stream().filter(unit -> unit.getName().equals("GBP"))
                .findAny().orElse(null);
        CurrencyUnitDto jpy = units.stream().filter(unit -> unit.getName().equals("JPY"))
                .findAny().orElse(null);
        //then
        assertEquals(response.getBase(),"USD");
        assertEquals(response.getDate(), LocalDate.of(2021,3,17));
        assertEquals(response.getSuccess(), true);
        assertEquals(response.getTimestamp(), 1519296206L);
        assert eur != null;
        assertEquals(eur.getValue(), new BigDecimal("0.813399"));
        assert gbp != null;
        assertEquals(gbp.getValue(), BigDecimal.ZERO);
        assert jpy != null;
        assertEquals(jpy.getValue(), new BigDecimal("107.346001"));
    }
}