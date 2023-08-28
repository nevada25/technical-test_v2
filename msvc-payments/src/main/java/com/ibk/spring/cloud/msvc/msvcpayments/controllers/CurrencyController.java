package com.ibk.spring.cloud.msvc.msvcpayments.controllers;


import com.ibk.spring.cloud.msvc.msvcpayments.models.entity.Currency;
import com.ibk.spring.cloud.msvc.msvcpayments.response.ApiResponseUser;
import com.ibk.spring.cloud.msvc.msvcpayments.services.CurrencyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;
    @GetMapping
        public ResponseEntity<ApiResponseUser> listAll() {
            List<Currency> currencies = this.currencyService.findAllCurrency();
            ApiResponseUser response = new ApiResponseUser().builder()
                    .success(true)
                    .message("Los datos de la moneda se estan listando correctamente")
                    .status(HttpStatus.OK)
                    .data(currencies)
                    .build();
            return ResponseEntity.ok(response);
        }


        @PostMapping
        public ResponseEntity<?> saveCurrency(@Valid @RequestBody Currency currency, BindingResult result) {
            if (result.hasErrors()) {
                return valid_data(result);
            }
            Currency currencyRequest = currencyService.saveCurrency(currency);

            ApiResponseUser response = new ApiResponseUser().builder()
                    .success(true)
                    .message("La moneda se ha creado correctamente")
                    .status(HttpStatus.OK)
                    .data(currencyRequest)
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteCurrency(@PathVariable Long id) {
            Optional<Currency> o = currencyService.findById(id);
            if (o.isPresent()) {
                currencyService.deleteCurrency(id);
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
}
