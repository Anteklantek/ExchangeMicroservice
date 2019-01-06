package com.anteklantek.Exchange.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"currencyExchangeTableId", "code"})
})
public class CurrencyExchangeRate {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(nullable = false, precision=11, scale = 9)
    private BigDecimal mid;

    @NotNull
    private String code;

    @NotNull
    private String currency;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currencyExchangeTableId")
    private CurrencyExchangeTable currencyExchangeTable;
}
