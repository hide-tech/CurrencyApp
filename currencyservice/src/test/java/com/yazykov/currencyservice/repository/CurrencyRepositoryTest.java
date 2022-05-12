package com.yazykov.currencyservice.repository;

import com.yazykov.currencyservice.model.Currency;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CurrencyRepositoryTest {

    @Autowired
    private CurrencyRepository repository;

    @AfterEach
    void tearDown(){
        repository.deleteAll();
    }

    @Test
    void findFirstByOrderByCheckedAtDescTest() {
        //given
        Currency currency = new Currency(15L, LocalDateTime.now(), new BigDecimal("1.0"),
                new BigDecimal("1.1"), new BigDecimal("1.2"), new BigDecimal("1.3"));
        Currency currency2 = new Currency(16L, LocalDateTime.now().minusDays(1L), new BigDecimal("2.0"),
                new BigDecimal("2.1"), new BigDecimal("2.2"), new BigDecimal("2.3"));
        repository.save(currency);
        repository.save(currency2);
        //when
        Currency result = repository.findFirstByOrderByCheckedAtDesc();
        //then
        assertEquals(result.getCheckedAt(), currency.getCheckedAt());
        assertEquals(result.getUsdValue(), currency.getUsdValue());
        assertEquals(result.getEurValue(), currency.getEurValue());
        assertEquals(result.getGbpValue(), currency.getGbpValue());
        assertEquals(result.getJpyValue(), currency.getJpyValue());
    }
}