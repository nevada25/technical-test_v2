package com.ibk.spring.cloud.msvc.msvcpayments.services.impl;

import com.ibk.spring.cloud.msvc.msvcpayments.exceptions.ResourceNotFoundException;
import com.ibk.spring.cloud.msvc.msvcpayments.models.entity.User;
import com.ibk.spring.cloud.msvc.msvcpayments.repositories.UserRepository;
import com.ibk.spring.cloud.msvc.msvcpayments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> all() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User byId(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el id: " + userId));
    }
}
