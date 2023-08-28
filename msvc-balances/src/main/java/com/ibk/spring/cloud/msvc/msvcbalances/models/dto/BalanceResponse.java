package com.ibk.spring.cloud.msvc.msvcbalances.models.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BalanceResponse {

    private BigDecimal total;

    private BigDecimal total_exchange;


}
