package com.baeldung.service;

import com.baeldung.dto.CustomUserDetails;
import com.baeldung.model.userslogins.Authority;
import com.baeldung.model.userslogins.User;
import com.baeldung.model.userslogins.UserAuthority;
import com.baeldung.repository.AuthorityRepo;
import com.baeldung.repository.UserAuthorityRepo;
import com.baeldung.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Transactional(Transactional.TxType.REQUIRES_NEW)
@Service
public class UserManagerService {

    private static final String ADMIN_ROLE = "ADMIN";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthorityRepo userAuthorityRepo;

    @Autowired
    private AuthorityRepo authorityRepo;

    public void registerNewUser(CustomUserDetails customUserDetails) {
        UserAuthority userAuthority = new UserAuthority();

        Authority authority = authorityRepo.findByName(ADMIN_ROLE);

        User user = new User();
        user.setAccountExpired(false);
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);
        user.setEnabled(true);
        user.setPassword(customUserDetails.getPassword());
        user.setUserName(customUserDetails.getUsername());
        user.setUserAuthorities(Collections.singleton(userAuthority));
        userRepository.save(user);

        userAuthority.setUser(user);
        userAuthority.setAuthority(authority);
        userAuthorityRepo.save(userAuthority);
    }

    public CustomUserDetails findById(Integer userId) {
        User user = userRepository.getOne(userId);
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUserName(user.getUserName());
        return customUserDetails;
    }
}
