package com.ibk.spring.cloud.msvc.msvcbalances.repositories;

import com.ibk.spring.cloud.msvc.msvcbalances.models.entity.Balance;
import org.springframework.data.repository.CrudRepository;

public interface BalanceRepository extends CrudRepository<Balance, Long> {
}
