package com.anteklantek.Exchange.controller.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateViewModel {

    private String code;
    private BigDecimal rateValue;
    private LocalDate tableEffectiveDate;
}
