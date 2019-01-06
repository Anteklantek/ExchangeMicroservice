package com.anteklantek.Exchange;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RateControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getPLNRateReturnsFixedRate() throws Exception {
        mvc.perform(get("/rate?code=PLN").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rateValue", is(1)))
                .andExpect(jsonPath("$.tableEffectiveDate", is(LocalDate.now().toString())));
    }

    @Test
    public void getUnknownCurrencyReturnsNotFoundStatus() throws Exception {
        mvc.perform(get("/rate?code=NOT_REALLY_A_CURRENCY").contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
