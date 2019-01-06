package com.anteklantek.Exchange.service;

import com.anteklantek.Exchange.controller.viewmodel.RateViewModel;
import com.anteklantek.Exchange.model.CurrencyExchangeRate;
import com.anteklantek.Exchange.model.CurrencyExchangeTable;
import com.anteklantek.Exchange.repository.CurrencyExchangeTableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@Slf4j
public class CurrencyRatesService {

    @Autowired
    private CurrencyExchangeTableRepository tableRepository;

    public RateViewModel getRateForCode(String currencyCode){
        if (currencyCode.equals("PLN")) {
            return createPLNRateViewModel();
        }
        final CurrencyExchangeTable currencyExchangeTable = tableRepository.findFirstByOrderByEffectiveDateDesc();
        if (currencyExchangeTable != null) {
            return createRateViewModel(currencyExchangeTable, currencyCode);
        } else {
            log.error("Did not find any rate table");
            return null;
        }
    }

    private RateViewModel createPLNRateViewModel() {
        return new RateViewModel("PLN",BigDecimal.ONE,LocalDate.now());
    }

    private RateViewModel createRateViewModel(CurrencyExchangeTable currencyExchangeTable, String code) {
        final CurrencyExchangeRate rate = currencyExchangeTable.getRates().stream().
                filter(e -> code.equals(e.getCode())).findFirst().orElse(null);
        if (rate != null) {
            return new RateViewModel(code,rate.getMid(),currencyExchangeTable.getEffectiveDate());
        } else {
            return new RateViewModel();
        }
    }
}
