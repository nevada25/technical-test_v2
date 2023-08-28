package com.ibk.spring.cloud.msvc.msvcpayments.services;


import com.ibk.spring.cloud.msvc.msvcpayments.models.entity.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {

    List<Currency> findAllCurrency();

    Optional<Currency> findById(Long id);

    Currency findByCode(String code);

    Currency saveCurrency(Currency currency);

    void deleteCurrency(Long id);

}
