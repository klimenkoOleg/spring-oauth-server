package com.baeldung.repository;

import com.baeldung.model.userslogins.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority, Integer> {
    public Authority findByName(String name);
}
