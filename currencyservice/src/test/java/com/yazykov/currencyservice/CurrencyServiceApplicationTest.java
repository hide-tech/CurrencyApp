package com.yazykov.currencyservice;

import com.yazykov.currencyservice.controller.CurrencyController;
import com.yazykov.currencyservice.dto.CurrencyResponse;
import com.yazykov.currencyservice.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
class CurrencyServiceApplicationTest {

    private CurrencyController controller;

    @Autowired
    public CurrencyServiceApplicationTest(CurrencyService service){
        controller = new CurrencyController(service);
    }

    @Test
    void contextLoads() throws Exception {
        //given
        CurrencyResponse expected = new CurrencyResponse(LocalDateTime.now(), new BigDecimal("4.0"),
                new BigDecimal("4.15"), new BigDecimal("4.76"), new BigDecimal("420.0087"));
        //when
//        CurrencyResponse actual = controller.getCurrency();
//        //then
//        assertEquals(expected.getUsdValue(), actual.getUsdValue());
//        assertEquals(expected.getEurValue(), actual.getEurValue());
//        assertEquals(expected.getGbpValue(), actual.getGbpValue());
//        assertEquals(expected.getJpyValue(), actual.getJpyValue());
    }

}