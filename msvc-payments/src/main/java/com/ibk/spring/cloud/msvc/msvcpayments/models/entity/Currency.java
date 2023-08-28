package com.ibk.spring.cloud.msvc.msvcpayments.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "currencies")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El codigo no puede estar en blanco")
    @NotNull(message = "El codigo es obligatorio")
    private String code;

    @NotBlank(message = "El nombre no puede estar en blanco")
    @NotNull(message = "El nombre es obligatorio")
    private String name;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
