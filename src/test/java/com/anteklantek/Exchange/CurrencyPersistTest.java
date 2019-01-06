package com.anteklantek.Exchange;

import com.anteklantek.Exchange.model.CurrencyExchangeTable;
import com.anteklantek.Exchange.repository.CurrencyExchangeTableRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyPersistTest {

    @Autowired
    CurrencyExchangeTableRepository currencyExchangeTableRepository;

    @Test(expected = DataIntegrityViolationException.class)
    public void whenSavingTwoRatesWithSameCodeAndRatesTableThrowException(){
        CurrencyExchangeTable currencyExchangeTable = TestObjectsFactory.getTestCurrrencyExchangeTableWithDuplicateRate();
        currencyExchangeTableRepository.save(currencyExchangeTable);
    }

    @Test
    @Transactional
    public void whenSavingRateTableSaveAlsoItsChildrenRatesWithForeignKey(){
        CurrencyExchangeTable currencyExchangeTable = TestObjectsFactory.getTestCurrrencyExchangeTableWithOneRate();
        CurrencyExchangeTable currencyExchangeTableSaved = currencyExchangeTableRepository.save(currencyExchangeTable);
        CurrencyExchangeTable currencyExchangeTable1FromRepo = currencyExchangeTableRepository.findById(currencyExchangeTableSaved.getId()).orElse(null);
        assertNotNull(currencyExchangeTable1FromRepo);
        assertEquals(currencyExchangeTable.getRates().get(0),currencyExchangeTable1FromRepo.getRates().get(0));
    }


}
