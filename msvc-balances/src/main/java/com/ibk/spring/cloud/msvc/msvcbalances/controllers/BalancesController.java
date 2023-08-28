package com.ibk.spring.cloud.msvc.msvcbalances.controllers;


import com.ibk.spring.cloud.msvc.msvcbalances.models.dto.BalanceResponse;
import com.ibk.spring.cloud.msvc.msvcbalances.models.dto.ExchangeRateRequest;
import com.ibk.spring.cloud.msvc.msvcbalances.services.BalanceService;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/balances/balance")
public class BalancesController {

    private final BalanceService balanceService;


    @Autowired
    public BalancesController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @PostMapping
    public ResponseEntity<?> saveBalance(@Valid @RequestBody ExchangeRateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return valid_data(result);
        }

        Optional<BalanceResponse> b;
        try {
            b = balanceService.saveBalance(request);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", "No existe el usuario por el id o error en la comunicacion: " + e.getMessage()));
        }

        if (b.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(b.get());
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

}
