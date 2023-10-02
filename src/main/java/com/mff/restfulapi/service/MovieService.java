package com.mff.restfulapi.service;

import com.mff.restfulapi.model.MovieTitleResponse;

public interface MovieService {

    MovieTitleResponse get(String title);

}
