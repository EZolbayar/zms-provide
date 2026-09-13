package com.example.terguun.service;
import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.terguun.dto.Login;
import com.example.terguun.model.User;
import com.example.terguun.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2 
@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Login.Response login(Login.Request request) {

        log.info("Attempting login for request: {}", request.toString());
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Хэрэглэгч олдсонгүй"));
 
        if (Boolean.TRUE.equals(user.getIsDisabled())) {
            throw new IllegalStateException("Энэ хэрэглэгч түгжигдсэн байна");
        }
      
        // String encodedPassword = passwordEncoder.encode(request.getPassword());
        // log.info("Encoded password = {}", encodedPassword);
 
        if (!user.getUserCode().equalsIgnoreCase(request.getPassword())) {
            log.warn("Failed login attempt for user: {}", request.getUserId());
            log.warn("Provided password: {}", request.getPassword());
            log.warn("Expected password (encoded): {}", user.getUserCode());
            throw new IllegalArgumentException("Нэвтрэх нэр эсвэл нууц үг буруу байна");
        }
 
        user.setIsLoggedIn(true);
        user.setTimeLoggedIn(LocalDateTime.now());
        userRepository.save(user);
 
        log.info("User logged in successfully: {}", user.getUserId());
        return Login.Response.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .timeLoggedIn(user.getTimeLoggedIn())
                .isAdmin(user.getIsAdmin())
                .build();
    }

    @Transactional
    public void logout(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Хэрэглэгч олдсонгүй"));
 
        user.setIsLoggedIn(false);
        user.setTimeLoggedOut(LocalDateTime.now());
        userRepository.save(user);
    }

}
