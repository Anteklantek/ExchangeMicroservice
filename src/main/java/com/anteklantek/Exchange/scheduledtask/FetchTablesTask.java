package com.anteklantek.Exchange.scheduledtask;

import com.anteklantek.Exchange.repository.CurrencyExchangeTableRepository;
import com.anteklantek.Exchange.service.NBPRatesFetchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
public class FetchTablesTask {

    @Autowired
    private NBPRatesFetchService nbpRatesFetchService;
    @Autowired
    private CurrencyExchangeTableRepository currencyExchangeTableRepository;

    @Scheduled(cron = "1 45 11 * * MON-FRI")
    public void reportCurrentTime() {
        nbpRatesFetchService.fetchAndSaveRates();
        while (!currencyExchangeTableRepository.findFirstByOrderByEffectiveDateDesc().getEffectiveDate().equals(LocalDate.now())) {
            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                log.error(e.toString());
            }
            nbpRatesFetchService.fetchAndSaveRates();
        }
    }
}
