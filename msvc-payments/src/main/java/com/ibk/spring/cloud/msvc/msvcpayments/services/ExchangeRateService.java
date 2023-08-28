package com.ibk.spring.cloud.msvc.msvcpayments.services;



import com.ibk.spring.cloud.msvc.msvcpayments.models.entity.ExchangeRate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ExchangeRateService {

    List<ExchangeRate> findAllExchangeRate();

    Optional<ExchangeRate> findById(Long id);

    ExchangeRate saveExchangeRate(ExchangeRate exchangeRate);
    ExchangeRate findByToAndFrom(Long to, Long from);
    void deleteExchangeRate(Long id);
}
