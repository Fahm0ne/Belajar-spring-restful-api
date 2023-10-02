package com.mff.restfulapi.controller;

import com.mff.restfulapi.model.MovieTitleResponse;
import com.mff.restfulapi.model.WebResponse;
import com.mff.restfulapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoviesController {

    @Autowired
    private MovieService movieService;

    @PostMapping(
            path = "/api/movie",
            produces = MediaType.APPLICATION_JSON_VALUE
            )
    public WebResponse<MovieTitleResponse> save (@RequestParam String title){

       MovieTitleResponse response =  movieService.get(title);
       return WebResponse.<MovieTitleResponse>builder().data(response).build();
    }

}
