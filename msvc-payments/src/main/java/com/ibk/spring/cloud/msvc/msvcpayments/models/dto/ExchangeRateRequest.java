package com.ibk.spring.cloud.msvc.msvcpayments.models.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ExchangeRateRequest {

    @NotNull(message = "El tipo de moneda del que deseas cambiar es requerido")
    private String to;
    @NotNull(message = "El tipo de moneda a cambiar es requerido")
    private String from;
    @NotNull(message = "El monto es requerido")
    private BigDecimal amount;


}
