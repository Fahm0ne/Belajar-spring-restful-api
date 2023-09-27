package com.mff.restfulapi.repository;

import com.mff.restfulapi.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movies , String> {
}
