package com.mff.restfulapi.repository;

import com.mff.restfulapi.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movies , String> {

    @Override
    Optional<Movies> findById(String imdbId);
}
