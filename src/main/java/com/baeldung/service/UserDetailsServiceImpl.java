package com.baeldung.service;

import com.baeldung.dto.CustomGrantedAuthority;
import com.baeldung.dto.CustomUserDetails;
import com.baeldung.model.userslogins.User;
import com.baeldung.model.userslogins.UserAuthority;
import com.baeldung.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Primary
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user != null) {
            CustomUserDetails customUserDetails = new CustomUserDetails();
            customUserDetails.setUserName(user.getUserName());
            customUserDetails.setPassword(user.getPassword());
            Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
            for (UserAuthority authority : user.getUserAuthorities()) {
                authorities.add(new CustomGrantedAuthority(authority.getAuthority().getName()));
            }
            customUserDetails.setGrantedAuthorities(authorities);
            return customUserDetails;
        }
        throw new UsernameNotFoundException(username);
    }

}