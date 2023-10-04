package com.mff.restfulapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @Column(name = "ID")
    private int id;

    @JsonProperty(value = "Source")
    private String source;

    @JsonProperty(value = "Value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "imdbID" , referencedColumnName = "imdbID")
    private Movies movies;

}
