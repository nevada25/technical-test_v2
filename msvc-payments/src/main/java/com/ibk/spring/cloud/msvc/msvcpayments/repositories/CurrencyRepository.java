package com.ibk.spring.cloud.msvc.msvcpayments.repositories;

import com.ibk.spring.cloud.msvc.msvcpayments.models.entity.Currency;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {


    @Query(nativeQuery = true,value = "SELECT * FROM currencies WHERE code=:code")
    Currency findByCode(String code);

}
