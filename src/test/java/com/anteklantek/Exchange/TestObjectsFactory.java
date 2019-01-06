package com.anteklantek.Exchange;

import com.anteklantek.Exchange.model.CurrencyExchangeRate;
import com.anteklantek.Exchange.model.CurrencyExchangeTable;
import com.anteklantek.Exchange.model.TableType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

public class TestObjectsFactory {

    public static CurrencyExchangeTable getTestCurrrencyExchangeTableWithOneRate(){
        CurrencyExchangeTable currencyExchangeTable = new CurrencyExchangeTable();
        currencyExchangeTable.setEffectiveDate(LocalDate.of(2010,10,10));
        currencyExchangeTable.setNo("A/003/2010");
        currencyExchangeTable.setTable(TableType.A);

        CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate();
        currencyExchangeRate.setMid(new BigDecimal("2.052"));
        currencyExchangeRate.setCode("GP");
        currencyExchangeRate.setCurrency("Gold");
        currencyExchangeRate.setCurrencyExchangeTable(currencyExchangeTable);
        currencyExchangeTable.setRates(Collections.singletonList(currencyExchangeRate));
        return currencyExchangeTable;
    }

    public static CurrencyExchangeTable getTestCurrrencyExchangeTableWithDuplicateRate(){
        CurrencyExchangeTable currencyExchangeTable = new CurrencyExchangeTable();
        currencyExchangeTable.setEffectiveDate(LocalDate.of(2010,10,10));
        currencyExchangeTable.setNo("A/003/2010");
        currencyExchangeTable.setTable(TableType.A);

        CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate();
        currencyExchangeRate.setMid(new BigDecimal("2.052"));
        currencyExchangeRate.setCode("GP");
        currencyExchangeRate.setCurrency("Gold");
        currencyExchangeRate.setCurrencyExchangeTable(currencyExchangeTable);

        CurrencyExchangeRate currencyExchangeRate2 = new CurrencyExchangeRate();
        currencyExchangeRate2.setMid(new BigDecimal("4.052"));
        currencyExchangeRate2.setCode("GP");
        currencyExchangeRate2.setCurrency("Gold");
        currencyExchangeRate2.setCurrencyExchangeTable(currencyExchangeTable);

        currencyExchangeTable.setRates(Arrays.asList(currencyExchangeRate,currencyExchangeRate2));

        return currencyExchangeTable;
    }


}
