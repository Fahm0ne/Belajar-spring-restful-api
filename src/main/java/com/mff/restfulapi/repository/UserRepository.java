package com.mff.restfulapi.repository;

import com.mff.restfulapi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users , String > {



}
