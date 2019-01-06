package com.anteklantek.Exchange.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
public class CurrencyExchangeTable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private String no;

    @NotNull
    private TableType table;

    @NotNull
    private LocalDate effectiveDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "currencyExchangeTable")
    private List<CurrencyExchangeRate> rates;

}
