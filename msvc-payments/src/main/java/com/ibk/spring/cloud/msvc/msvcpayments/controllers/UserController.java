package com.ibk.spring.cloud.msvc.msvcpayments.controllers;


import com.ibk.spring.cloud.msvc.msvcpayments.models.entity.User;
import com.ibk.spring.cloud.msvc.msvcpayments.response.ApiResponseUser;
import com.ibk.spring.cloud.msvc.msvcpayments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponseUser> saveUser(@RequestBody User user) {
        User userRequest = userService.saveUser(user);
        ApiResponseUser response = new ApiResponseUser().builder()
                .success(true)
                .message("El usuario se ha creado correctamente")
                .status(HttpStatus.OK)
                .data(userRequest)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseUser> getUser(@PathVariable String userId) {
        User user = userService.byId(userId);
        ApiResponseUser response = new ApiResponseUser().builder()
                .success(true)
                .message("El usuario se esta mostrando correctamente")
                .status(HttpStatus.OK)
                .data(user)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponseUser> listAll() {
        List<User> users = userService.all();
        ApiResponseUser response = new ApiResponseUser().builder()
                .success(true)
                .message("Se Estan mostrando todos los usuario correctamente")
                .status(HttpStatus.OK)
                .data(users)
                .build();
        return ResponseEntity.ok(response);
    }

}

