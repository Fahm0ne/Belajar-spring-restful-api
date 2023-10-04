package com.mff.restfulapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "movie_detail")
public class Movies {
    @Id
    private String imdbID;
    private String title;
    private String year;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;

    @Column(name = "imdb_rating")
    private String imdbRating;
    @Column(name = "imdb_votes")
    private String imdbVotes;
    private String type;

    private String response;

    @OneToMany(mappedBy = "movies")
    private List<Rating> rating;

}
