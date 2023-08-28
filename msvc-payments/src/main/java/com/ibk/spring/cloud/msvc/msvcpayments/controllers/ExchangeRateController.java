package com.ibk.spring.cloud.msvc.msvcpayments.controllers;


import com.ibk.spring.cloud.msvc.msvcpayments.models.dto.ExchangeRateRequest;
import com.ibk.spring.cloud.msvc.msvcpayments.models.dto.ExchangeRateResponse;
import com.ibk.spring.cloud.msvc.msvcpayments.models.entity.Currency;
import com.ibk.spring.cloud.msvc.msvcpayments.models.entity.ExchangeRate;
import com.ibk.spring.cloud.msvc.msvcpayments.response.ApiResponseUser;
import com.ibk.spring.cloud.msvc.msvcpayments.services.CurrencyService;
import com.ibk.spring.cloud.msvc.msvcpayments.services.ExchangeRateService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments/exchangeRate")
@Slf4j
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;
    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<ApiResponseUser> listAll() {
        List<ExchangeRate> currencies = this.exchangeRateService.findAllExchangeRate();


        ApiResponseUser response = new ApiResponseUser().builder()
                .success(true)
                .message("Los datos del tipo de cambio  se estan listando correctamente")
                .status(HttpStatus.OK)
                .data(currencies)
                .build();
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<?> saveExchangeRate(@Valid @RequestBody ExchangeRate ExchangeRate, BindingResult result) {
        if (result.hasErrors()) {
            return valid_data(result);
        }

        ExchangeRate ExchangeRateRequest = exchangeRateService.saveExchangeRate(ExchangeRate);

        ApiResponseUser response = new ApiResponseUser().builder()
                .success(true)
                .message("El tipo de cambio se ha creado correctamente")
                .status(HttpStatus.OK)
                .data(ExchangeRateRequest)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/change")
    public ResponseEntity<?> updateExchangeRate(@Valid @RequestBody ExchangeRateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return valid_data(result);
        }


        Currency currency_to = currencyService.findByCode(request.getTo());
        Currency currency_from = currencyService.findByCode(request.getFrom());
        if (currency_to == null) {
            return responseNotFound("El tipo de cambios de inicio no esta registrado o fue eliminada");
        }

        if (currency_from == null) {
            return responseNotFound("El tipo de cambios con el que desea cambiar no esta registrado o fue eliminada");
        }

        ExchangeRate exchangeRateByFromTo = exchangeRateService.findByToAndFrom(currency_to.getId(), currency_from.getId());
        log.info("to: code: {},id:{}", currency_to.getCode(), currency_to.getId());
        log.info("from: code: {},id:{}", currency_from.getCode(), currency_from.getId());


        if (exchangeRateByFromTo == null) {
            return responseNotFound("El tipo de cambio no esta registrado o fue eliminada");
        }


        BigDecimal total = BigDecimal.ONE;
        if (request.getAmount().intValue() > 0) {
            total = request.getAmount();
        }
        exchangeRateByFromTo.setAmount(total);


        ApiResponseUser response = new ApiResponseUser().builder()
                .success(true)
                .message("El tipo de cambio se ha modificado correctamente")
                .status(HttpStatus.OK)
                .data(exchangeRateService.saveExchangeRate(exchangeRateByFromTo))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/consult")
    public ResponseEntity<?> exchangeRates(@Valid @RequestBody ExchangeRateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return valid_data(result);
        }

        Currency currency_to = currencyService.findByCode(request.getTo());
        Currency currency_from = currencyService.findByCode(request.getFrom());
        if (currency_to == null) {
            return responseNotFound("El tipo de cambios de inicio no esta registrado o fue eliminada");
        }

        if (currency_from == null) {
            return responseNotFound("El tipo de cambios con el que desea cambiar no esta registrado o fue eliminada");
        }


        ExchangeRate exchangeRateByFromTo = exchangeRateService.findByToAndFrom(currency_to.getId(), currency_from.getId());

        if (exchangeRateByFromTo == null) {
            return responseNotFound("El tipo de cambio no existe o fue eliminada");
        }

        BigDecimal total = BigDecimal.ZERO;
        if (request.getAmount().intValue() > 0) {
            total = exchangeRateByFromTo.getAmount().multiply(request.getAmount());
        }

        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse().builder()
                .to(request.getTo())
                .from(request.getFrom())
                .amount(request.getAmount())
                .amount_to_change(total)
                .exchangeRate(exchangeRateByFromTo)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(exchangeRateResponse);


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExchangeRate(@PathVariable Long id) {
        Optional<ExchangeRate> o = exchangeRateService.findById(id);
        if (o.isPresent()) {
            exchangeRateService.deleteExchangeRate(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    private ResponseEntity<Map<String, String>> valid_data(BindingResult result) {
        Map<String, String> errs = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errs.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errs);
    }


    private ResponseEntity<ApiResponseUser> responseNotFound(String message) {
        ApiResponseUser response = new ApiResponseUser().builder()
                .success(false)
                .message(message)
                .status(HttpStatus.NOT_FOUND)
                .data("")
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
