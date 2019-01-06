package com.anteklantek.Exchange;

import com.anteklantek.Exchange.controller.viewmodel.RateViewModel;
import com.anteklantek.Exchange.repository.CurrencyExchangeTableRepository;
import com.anteklantek.Exchange.service.CurrencyRatesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyRatesServiceTest {

    @Mock
    CurrencyExchangeTableRepository currencyExchangeTableRepository;

    @InjectMocks
    CurrencyRatesService currencyRatesService;

    @Before
    public void setUp(){
        Mockito.when(currencyExchangeTableRepository.findFirstByOrderByEffectiveDateDesc()).thenReturn(TestObjectsFactory.getTestCurrrencyExchangeTableWithOneRate());
    }

    @Test
    public void whenGettingValidCurrencyWeGetFilledRateViewModel(){
        RateViewModel gpRateViewModel = currencyRatesService.getRateForCode("GP");
        assertNotNull(gpRateViewModel.getRateValue());
        assertNotNull(gpRateViewModel.getCode());
        assertNotNull(gpRateViewModel.getTableEffectiveDate());
    }

    @Test
    public void whenGettingUnexistingCurrencyWeGetEmptyRateViewModel(){
        RateViewModel gpRateViewModel = currencyRatesService.getRateForCode("NOT_EXISTING_CURRENCY");
        assertNull(gpRateViewModel.getRateValue());
        assertNull(gpRateViewModel.getCode());
        assertNull(gpRateViewModel.getTableEffectiveDate());
    }



}
