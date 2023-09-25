package com.mff.restfulapi.service;

import com.mff.restfulapi.entity.Users;
import com.mff.restfulapi.model.LoginUserName;
import com.mff.restfulapi.model.TokenResponse;

public interface AuthService {
     TokenResponse login (LoginUserName request);

     void logout(Users users);

}
