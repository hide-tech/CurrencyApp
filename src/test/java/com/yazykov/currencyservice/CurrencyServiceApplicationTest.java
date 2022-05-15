package com.yazykov.currencyservice;

import com.yazykov.currencyservice.controller.CurrencyController;
import com.yazykov.currencyservice.dto.CurrencyResponse;
import com.yazykov.currencyservice.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

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

        //when
        CurrencyResponse actual = controller.getCurrency();
        //then

    }

}