package com.mff.restfulapi.service;

import com.mff.restfulapi.Exception.ApiException;
import com.mff.restfulapi.entity.Users;
import com.mff.restfulapi.model.RegisterUserRequest;
import com.mff.restfulapi.repository.UserRepository;
import com.mff.restfulapi.security.BCrypt;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Transactional
    @Override
    public void register(RegisterUserRequest request) {

        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);
        if(constraintViolations.size() != 0) {
            throw new ConstraintViolationException(constraintViolations);
        }

        if(userRepository.existsById(request.getUsername())){
            throw new ApiException("Username Already Registered");
        }

        Users users = new Users();
        users.setUsername(request.getUsername());
        users.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        users.setName(request.getName());

        userRepository.save(users);

    }
}
