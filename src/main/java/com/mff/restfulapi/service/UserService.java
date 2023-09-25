package com.mff.restfulapi.service;

import com.mff.restfulapi.entity.Users;
import com.mff.restfulapi.model.*;

public interface UserService {

    void register(RegisterUserRequest request);

    UserResponse getUser(Users users);

    UserResponse UpdateUser(Users users, UpdateUsersRequest request);



}
