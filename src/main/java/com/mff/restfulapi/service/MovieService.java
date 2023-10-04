package com.mff.restfulapi.service;

import com.mff.restfulapi.model.MovieTitleResponse;

public interface MovieService {

    MovieTitleResponse save (String title);

   MovieTitleResponse getMovies(String imdbId);

}
