package com.baeldung.repository;

import com.baeldung.model.userslogins.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT DISTINCT u FROM User u WHERE u.userName = :username")
    User findByUsername(@Param("username") String username);

}