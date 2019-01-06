package com.anteklantek.Exchange.repository;

import com.anteklantek.Exchange.model.CurrencyExchangeTable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CurrencyExchangeTableRepository extends JpaRepository<CurrencyExchangeTable, Long> {

    CurrencyExchangeTable findFirstByOrderByEffectiveDateDesc();
}
