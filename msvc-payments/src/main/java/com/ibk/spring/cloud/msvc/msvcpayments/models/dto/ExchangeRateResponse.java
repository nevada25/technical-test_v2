package com.ibk.spring.cloud.msvc.msvcpayments.models.dto;

import com.ibk.spring.cloud.msvc.msvcpayments.models.entity.ExchangeRate;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeRateResponse {

    private BigDecimal amount_to_change;
    private String to;
    private String from;
    private BigDecimal amount;
    private ExchangeRate exchangeRate;

}
