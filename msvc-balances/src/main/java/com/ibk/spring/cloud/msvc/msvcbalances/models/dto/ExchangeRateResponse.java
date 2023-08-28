package com.ibk.spring.cloud.msvc.msvcbalances.models.dto;


import com.ibk.spring.cloud.msvc.msvcbalances.models.ExchangeRate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ExchangeRateResponse {

    private BigDecimal amount_to_change;
    private String to;
    private String from;
    private BigDecimal amount;
    private ExchangeRate exchangeRate;

}