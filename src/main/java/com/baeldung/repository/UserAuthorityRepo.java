package com.baeldung.repository;

import com.baeldung.model.userslogins.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityRepo extends JpaRepository<UserAuthority, Integer> {
}
