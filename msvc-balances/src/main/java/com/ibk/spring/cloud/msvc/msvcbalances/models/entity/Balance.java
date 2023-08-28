package com.ibk.spring.cloud.msvc.msvcbalances.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "balances")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private BigDecimal amount_change;

    private Long id_exchange_rate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount_change() {
        return amount_change;
    }

    public void setAmount_change(BigDecimal amount_change) {
        this.amount_change = amount_change;
    }

    public Long getId_exchange_rate() {
        return id_exchange_rate;
    }

    public void setId_exchange_rate(Long id_exchange_rate) {
        this.id_exchange_rate = id_exchange_rate;
    }
}
