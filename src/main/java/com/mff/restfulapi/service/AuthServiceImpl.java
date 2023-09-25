package com.mff.restfulapi.service;

import com.mff.restfulapi.entity.Users;
import com.mff.restfulapi.model.LoginUserName;
import com.mff.restfulapi.model.TokenResponse;
import com.mff.restfulapi.repository.UserRepository;
import com.mff.restfulapi.security.BCrypt;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private ValidationService validationService;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public TokenResponse login(LoginUserName request) {

    validationService.validate(request);

    Users users = userRepository.findById(request.getUsername()).
            orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED , "username is wrong"));

        if (BCrypt.checkpw(request.getPassword(), users.getPassword())) {
            users.setToken(UUID.randomUUID().toString());
            users.setTokenExpredAt(System.currentTimeMillis() + (1000 * 60 * 60 * 24));

            userRepository.save(users);

            return TokenResponse.builder()
                    .token(users.getToken())
                    .expiredAt(users.getTokenExpredAt())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED , "password is wrong");
        }
    }

    @Override
    @Transactional
    public void logout(Users users) {

        users.setToken(null);
        users.setTokenExpredAt(null);

        userRepository.save(users);

    }
}
