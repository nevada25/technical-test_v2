package com.ibk.spring.cloud.msvc.msvcpayments.repositories;

import com.ibk.spring.cloud.msvc.msvcpayments.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {


    @Query(nativeQuery = true, value = "SELECT * FROM users where username=:username")
    User finByUsername(String username);
}
