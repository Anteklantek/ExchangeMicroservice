package com.anteklantek.Exchange.viewmodel;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RateViewModel {

    private String code;
    private BigDecimal rateValue;
    private LocalDate tableEffectiveDate;
}
