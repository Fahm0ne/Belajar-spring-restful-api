package com.mff.restfulapi.service;

import com.mff.restfulapi.entity.Users;
import com.mff.restfulapi.model.*;
import com.mff.restfulapi.repository.UserRepository;
import com.mff.restfulapi.security.BCrypt;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ValidationService validationService;

    @Transactional
    @Override
    public void register(RegisterUserRequest request) {

        validationService.validate(request);

        if(userRepository.existsById(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "Username Already Registered");
        }
        Users users = new Users();
        users.setUsername(request.getUsername());
        users.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        users.setName(request.getName());
        userRepository.save(users);

    }

    @Override
    public UserResponse getUser(Users users) {
        return UserResponse.builder()
                .name(users.getName())
                .username(users.getUsername())
                .build();
    }

    @Override
    public UserResponse UpdateUser(Users users, UpdateUsersRequest request) {

        validationService.validate(request);

        if (Objects.nonNull(request.getName())) {
            users.setName(request.getName());
        }
        if(Objects.nonNull(request.getPassword())){
            users.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }

        userRepository.save(users);
        return UserResponse.builder()
                .username(users.getUsername())
                .name(users.getName())
                .build();

    }

}
