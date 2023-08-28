package com.ibk.spring.cloud.msvc.msvcpayments.services;

import com.ibk.spring.cloud.msvc.msvcpayments.models.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> all();
    User byId(String userId);



}
