package com.anteklantek.Exchange.controller;

import com.anteklantek.Exchange.controller.viewmodel.RateViewModel;
import com.anteklantek.Exchange.service.CurrencyRatesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class RateController {

    @Autowired
    CurrencyRatesService currencyRatesService;

    @GetMapping(path = "/rate", produces = "application/json")
    public ResponseEntity<RateViewModel> getRate(@RequestParam String code) {

        final RateViewModel rateViewModel = currencyRatesService.getRateForCode(code);

        if (rateViewModel == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (rateViewModel.getRateValue() != null) {
            return new ResponseEntity<>(rateViewModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
