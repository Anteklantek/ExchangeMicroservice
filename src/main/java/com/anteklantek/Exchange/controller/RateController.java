package com.anteklantek.Exchange.controller;

import com.anteklantek.Exchange.model.CurrencyExchangeRate;
import com.anteklantek.Exchange.model.CurrencyExchangeTable;
import com.anteklantek.Exchange.repository.CurrencyExchangeTableRepository;
import com.anteklantek.Exchange.viewmodel.RateViewModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class RateController {

    @Autowired
    private CurrencyExchangeTableRepository tableRepository;

    @GetMapping(path = "/rate", produces = "application/json")
    public ResponseEntity<RateViewModel> getRate(@RequestParam String code) {

        CurrencyExchangeTable currencyExchangeTable = tableRepository.findFirstByOrderByEffectiveDateDesc();
        if (currencyExchangeTable != null) {
            return createResponseRateViewModel(currencyExchangeTable, code);
        } else {
            log.error("Did not find any rate table");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<RateViewModel> createResponseRateViewModel(CurrencyExchangeTable
                                                                              currencyExchangeTable, String code) {
        RateViewModel rateViewModel = new RateViewModel();
        rateViewModel.setCode(code);

        final CurrencyExchangeRate rate = currencyExchangeTable.getRates().stream().
                filter(e -> code.equals(e.getCode())).findFirst().orElse(null);
        if (rate != null) {
            rateViewModel.setRateValue(rate.getMid());
            rateViewModel.setTableEffectiveDate(currencyExchangeTable.getEffectiveDate());
            return new ResponseEntity<>(rateViewModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
