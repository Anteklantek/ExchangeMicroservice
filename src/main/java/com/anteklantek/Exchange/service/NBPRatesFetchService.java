package com.anteklantek.Exchange.service;

import com.anteklantek.Exchange.properties.NBPRestApiProperties;
import com.anteklantek.Exchange.model.CurrencyExchangeRate;
import com.anteklantek.Exchange.model.CurrencyExchangeTable;
import com.anteklantek.Exchange.repository.CurrencyExchangeTableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class NBPRatesFetchService {

    @Autowired
    private NBPRestApiProperties nbpRestApiProperties;

    @Autowired
    private CurrencyExchangeTableRepository tableRepository;

    private CurrencyExchangeTable fetchCurrentCurrencyData() {
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<List<CurrencyExchangeTable>> response = restTemplate.exchange(
                nbpRestApiProperties.getATableRestApiUrl(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CurrencyExchangeTable>>() {
                });
        if (response.getStatusCode().is2xxSuccessful()) {
            List<CurrencyExchangeTable> list = response.getBody();
            return list != null && !list.isEmpty() ? list.get(0) : null;
        } else {
            log.warn("Could not fetch rates table" + response.toString());
            return null;
        }
    }

    private void saveTable(CurrencyExchangeTable currencyExchangeTable) {
        for (CurrencyExchangeRate currencyExchangeRate : currencyExchangeTable.getRates()) {
            currencyExchangeRate.setCurrencyExchangeTable(currencyExchangeTable);
        }
        tableRepository.save(currencyExchangeTable);
    }

    public void fetchAndSaveRates() {
        CurrencyExchangeTable currencyExchangeTable = fetchCurrentCurrencyData();
        if (currencyExchangeTable != null) {
            saveTable(currencyExchangeTable);
        }
    }
}
