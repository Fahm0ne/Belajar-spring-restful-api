package com.mff.restfulapi.service;

import com.mff.restfulapi.entity.Movies;
import com.mff.restfulapi.model.MovieTitleResponse;
import com.mff.restfulapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public MovieTitleResponse save (String title) {

        String apiUrl = "http://www.omdbapi.com/?apikey=2d8d4885&t="+title;

        ResponseEntity<MovieTitleResponse> response = restTemplate.getForEntity(apiUrl , MovieTitleResponse.class);

        if (movieRepository.existsById(response.getBody().getImdbID())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Film dengan IMDb ID tersebut sudah terdaftar.");
        } else if (response.getStatusCode().is2xxSuccessful()) {
            Movies movies = new Movies();
            movies.setImdbID(response.getBody().getImdbID());
            movies.setYear(response.getBody().getYear());
            movies.setRated(response.getBody().getRated());
            movies.setReleased(response.getBody().getReleased());
            movies.setRuntime(response.getBody().getRuntime());
            movies.setGenre(response.getBody().getGenre());
            movies.setDirector(response.getBody().getDirector());
            movies.setWriter(response.getBody().getWriter());
            movies.setActors(response.getBody().getActors());
            movies.setPlot(response.getBody().getPlot());
            movies.setLanguage(response.getBody().getLanguage());
            movies.setCountry(response.getBody().getCountry());
            movies.setAwards(response.getBody().getAwards());
            movies.setPoster(response.getBody().getPoster());
            movies.setImdbRating(response.getBody().getImdbRating());
            movies.setImdbVotes(response.getBody().getImdbVotes());
            movies.setType(response.getBody().getType());
            movies.setResponse(response.getBody().getResponse());
            movies.setTitle(response.getBody().getTitle());
            movies.setRating(response.getBody().getRating());

            // Simpan data film ke dalam database
            movieRepository.save(movies);

            return response.getBody();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Gagal mengambil data film dari API.");
        }


    }

    @Transactional(readOnly = true)
    @Override
    public MovieTitleResponse getMovies(String imdbId) {

        Movies movies =  movieRepository.findById(imdbId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "imdbId not found"));

        MovieTitleResponse response = MovieTitleResponse.builder()
                .imdbID(movies.getImdbID())
                .title(movies.getTitle())
                .year(movies.getYear())
                .rated(movies.getRated())
                .released(movies.getReleased())
                .runtime(movies.getRuntime())
                .genre(movies.getGenre())
                .director(movies.getDirector())
                .writer(movies.getWriter())
                .actors(movies.getActors())
                .plot(movies.getPlot())
                .language(movies.getLanguage())
                .country(movies.getCountry())
                .awards(movies.getAwards())
                .poster(movies.getPoster())
                .imdbRating(movies.getImdbRating())
                .imdbVotes(movies.getImdbVotes())
                .type(movies.getType())
                .rating(movies.getRating())
                .response(movies.getResponse())
                .build();

        return response;
    }

}
