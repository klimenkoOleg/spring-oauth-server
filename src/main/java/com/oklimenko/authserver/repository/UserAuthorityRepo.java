package com.oklimenko.authserver.repository;

import com.oklimenko.authserver.model.oauth.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityRepo extends JpaRepository<UserAuthority, Integer> {
}
