package com.yazykov.currencyservice.repository;

import com.yazykov.currencyservice.model.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {

    Currency findFirstByOrderByCheckedAtDesc();
}
