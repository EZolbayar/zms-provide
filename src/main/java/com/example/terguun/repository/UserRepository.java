package com.example.terguun.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.terguun.model.User;

public interface UserRepository extends JpaRepository<User, String>  {

    Optional<User> findByUserId(String userId);
 
    Optional<User> findByUserIdAndIsDisabledFalse(String userId);

}
