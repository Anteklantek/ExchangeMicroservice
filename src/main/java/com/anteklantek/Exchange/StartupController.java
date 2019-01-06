package com.anteklantek.Exchange;

import com.anteklantek.Exchange.service.NBPRatesFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupController implements CommandLineRunner {

    @Autowired
    private NBPRatesFetchService nbpRatesFetchService;

    @Override
    public void run(String... args) throws Exception {
            nbpRatesFetchService.fetchAndSaveRates();
    }
}
