package com.ibk.spring.cloud.msvc.msvcpayments.services.impl;


import com.ibk.spring.cloud.msvc.msvcpayments.models.entity.ExchangeRate;
import com.ibk.spring.cloud.msvc.msvcpayments.repositories.ExchangeRateRepository;
import com.ibk.spring.cloud.msvc.msvcpayments.services.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    private ExchangeRateRepository repository;


    @Override
    @Transactional(readOnly = true)
    public List<ExchangeRate> findAllExchangeRate() {
        return (List<ExchangeRate>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExchangeRate> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public ExchangeRate saveExchangeRate(ExchangeRate exchangeRate) {

        return repository.save(exchangeRate);
    }


    @Override
    @Transactional(readOnly = true)
    public ExchangeRate findByToAndFrom(Long to, Long from) {
        return repository.findByToAndFrom(to, from);
    }

    @Override
    @Transactional
    public void deleteExchangeRate(Long id) {
        repository.deleteById(id);
    }
}
