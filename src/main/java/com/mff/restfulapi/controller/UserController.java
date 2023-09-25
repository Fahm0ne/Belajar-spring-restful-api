package com.mff.restfulapi.controller;

import com.mff.restfulapi.entity.Users;
import com.mff.restfulapi.model.RegisterUserRequest;
import com.mff.restfulapi.model.UpdateUsersRequest;
import com.mff.restfulapi.model.UserResponse;
import com.mff.restfulapi.model.WebResponse;
import com.mff.restfulapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(
            path = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> userRegist (@RequestBody RegisterUserRequest request){
        userService.register(request);
        return WebResponse.<String>builder().data("ok").build();
    }
    @GetMapping(
            path = "/api/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE  )
    public WebResponse<UserResponse> get (Users users){
       UserResponse userResponse = userService.getUser(users);
       return WebResponse.<UserResponse>builder()
               .data(userResponse)
               .build();
    }
    @PatchMapping(
            path = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> update (Users users ,@RequestBody UpdateUsersRequest request){
        UserResponse response = userService.UpdateUser(users , request);

        return WebResponse.<UserResponse>builder().data(response).build();
    }

}
