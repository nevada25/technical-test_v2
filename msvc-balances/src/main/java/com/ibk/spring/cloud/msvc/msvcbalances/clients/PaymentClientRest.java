package com.ibk.spring.cloud.msvc.msvcbalances.clients;


import com.ibk.spring.cloud.msvc.msvcbalances.models.dto.ExchangeRateRequest;
import com.ibk.spring.cloud.msvc.msvcbalances.models.dto.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "msvc-payments", url = "http://localhost:8081")
public interface PaymentClientRest {

    @PostMapping("/api/payments/exchangeRate/consult")
    ResponseEntity<ExchangeRateResponse> exchangeRates(@RequestBody ExchangeRateRequest request);



}
