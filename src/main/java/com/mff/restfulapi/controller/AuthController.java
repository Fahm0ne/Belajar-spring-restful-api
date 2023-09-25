package com.mff.restfulapi.controller;

import com.mff.restfulapi.entity.Users;
import com.mff.restfulapi.model.LoginUserName;
import com.mff.restfulapi.model.TokenResponse;
import com.mff.restfulapi.model.WebResponse;
import com.mff.restfulapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;
    @PostMapping(
            path = "/api/users/current",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TokenResponse> login (@RequestBody LoginUserName request) {

        TokenResponse tokenResponse =  authService.login(request);

        return WebResponse.<TokenResponse>builder().data(tokenResponse).build();

    }

    @DeleteMapping(
            path = "/api/auth"
    )
    public WebResponse<String> logout (Users users) {
        return WebResponse.<String>builder().data("ok").build();
    }

}
