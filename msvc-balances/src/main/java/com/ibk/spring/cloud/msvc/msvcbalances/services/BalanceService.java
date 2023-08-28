package com.ibk.spring.cloud.msvc.msvcbalances.services;


import com.ibk.spring.cloud.msvc.msvcbalances.models.dto.BalanceResponse;
import com.ibk.spring.cloud.msvc.msvcbalances.models.dto.ExchangeRateRequest;

import java.util.Optional;

public interface BalanceService {

    Optional<BalanceResponse> saveBalance(ExchangeRateRequest request);

}
