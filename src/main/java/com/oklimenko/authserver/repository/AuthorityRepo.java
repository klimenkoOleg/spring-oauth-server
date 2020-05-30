package com.oklimenko.authserver.repository;

import com.oklimenko.authserver.model.oauth.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority, Integer> {
    Authority findByName(String name);
}
