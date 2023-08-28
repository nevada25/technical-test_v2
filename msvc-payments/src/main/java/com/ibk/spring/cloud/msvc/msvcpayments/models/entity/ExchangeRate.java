package com.ibk.spring.cloud.msvc.msvcpayments.models.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "exchange_rates")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDate date_registry;

    private BigDecimal amount;

    @Column(name = "id_currency_to")
    private Long id_currency_to;

    @ManyToOne
    @JoinColumn(name = "id_currency_to", insertable = false,updatable = false)
    private Currency currency_to;
    @Column(name = "id_currency_from")
    private Long id_currency_from;

    @ManyToOne
    @JoinColumn(name = "id_currency_from", insertable = false,updatable = false)
    private Currency currency_from;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate_registry() {
        return date_registry;
    }

    public void setDate_registry(LocalDate date_registry) {
        this.date_registry = date_registry;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getId_currency_to() {
        return id_currency_to;
    }

    public void setId_currency_to(Long id_currency_to) {
        this.id_currency_to = id_currency_to;
    }

    public Currency getCurrency_to() {
        return currency_to;
    }

    public void setCurrency_to(Currency currency_to) {
        this.currency_to = currency_to;
    }

    public Long getId_currency_from() {
        return id_currency_from;
    }

    public void setId_currency_from(Long id_currency_from) {
        this.id_currency_from = id_currency_from;
    }

    public Currency getCurrency_from() {
        return currency_from;
    }

    public void setCurrency_from(Currency currency_from) {
        this.currency_from = currency_from;
    }
}
