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
        final RateViewModel rateViewModel = new RateViewModel();
        rateViewModel.setCode("PLN");
        rateViewModel.setRateValue(BigDecimal.ONE);
        rateViewModel.setTableEffectiveDate(LocalDate.now());
        return rateViewModel;
    }

    private RateViewModel createRateViewModel(CurrencyExchangeTable currencyExchangeTable, String code) {
        final CurrencyExchangeRate rate = currencyExchangeTable.getRates().stream().
                filter(e -> code.equals(e.getCode())).findFirst().orElse(null);
        if (rate != null) {
            final RateViewModel rateViewModel = new RateViewModel();
            rateViewModel.setCode(code);
            rateViewModel.setRateValue(rate.getMid());
            rateViewModel.setTableEffectiveDate(currencyExchangeTable.getEffectiveDate());
            return rateViewModel;
        } else {
            return new RateViewModel();
        }
    }


}
