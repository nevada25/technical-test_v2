package com.ibk.spring.cloud.msvc.msvcpayments.repositories;

import com.ibk.spring.cloud.msvc.msvcpayments.models.entity.ExchangeRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM exchange_rates WHERE id_currency_to=:to AND id_currency_from=:from")
    ExchangeRate findByToAndFrom(Long to, Long from);




}
