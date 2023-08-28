package com.ibk.spring.cloud.msvc.msvcbalances.services.impl;



import com.ibk.spring.cloud.msvc.msvcbalances.clients.PaymentClientRest;
import com.ibk.spring.cloud.msvc.msvcbalances.models.dto.BalanceResponse;
import com.ibk.spring.cloud.msvc.msvcbalances.models.dto.ExchangeRateRequest;
import com.ibk.spring.cloud.msvc.msvcbalances.models.dto.ExchangeRateResponse;
import com.ibk.spring.cloud.msvc.msvcbalances.models.entity.Balance;
import com.ibk.spring.cloud.msvc.msvcbalances.repositories.BalanceRepository;
import com.ibk.spring.cloud.msvc.msvcbalances.services.BalanceService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceRepository repository;

    @Autowired
    private PaymentClientRest client;


    @Override
    @Transactional
    public Optional<BalanceResponse> saveBalance(ExchangeRateRequest request) {

        ResponseEntity<ExchangeRateResponse> response = client.exchangeRates(request);
        ExchangeRateResponse body = response.getBody();

        Balance balance = new Balance();
        balance.setAmount(request.getAmount());
        log.info("data-feign:{},{}",response.getStatusCode(), response.getBody());
        if (response.getStatusCode() == HttpStatus.CREATED && body != null) {
            balance.setAmount_change(body.getAmount_to_change());
            balance.setId_exchange_rate(body.getExchangeRate().getId());
        } else {
            balance.setAmount_change(BigDecimal.ZERO);
        }
        repository.save(balance);
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setTotal(balance.getAmount());
        balanceResponse.setTotal_exchange(balance.getAmount_change());
        return Optional.of(balanceResponse);
    }

}
