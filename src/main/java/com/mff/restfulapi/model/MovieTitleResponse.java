package com.mff.restfulapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieTitleResponse {

    private String imdbID;

    @JsonProperty(value ="Title")
    private String title;

    @JsonProperty(value = "Year")
    private String year;

    @JsonProperty(value = "Rated" )
    private String rated;

    @JsonProperty(value = "Released" )
    private String released;

    @JsonProperty(value = "Runtime")
    private String runtime;

    @JsonProperty(value = "Genre" )
    private String genre;

    @JsonProperty(value = "Director" )
    private String director;

    @JsonProperty(value = "Writer" )
    private String writer;

    @JsonProperty(value = "Actors")
    private String actors;

    @JsonProperty(value = "Plot" )
    private String plot;

    @JsonProperty(value = "Language" )
    private String language;

    @JsonProperty(value = "Country")
    private String country;

    @JsonProperty(value = "Awards" )
    private String awards;

    @JsonProperty(value = "Poster")
    private String poster;

    @JsonProperty(value = "imdbRating")
    private String imdbRating;

    private Integer imdbVotes;

    @JsonProperty(value = "Type" )
    private String type;

    @JsonProperty(value = "Response" )
    private String response;

}
