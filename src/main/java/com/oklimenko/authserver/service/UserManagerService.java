package com.oklimenko.authserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oklimenko.authserver.dto.NewUserProfileDto;
import com.oklimenko.authserver.dto.UserProfileDto;
import com.oklimenko.authserver.model.oauth.Authority;
import com.oklimenko.authserver.model.oauth.User;
import com.oklimenko.authserver.model.oauth.UserAuthority;
import com.oklimenko.authserver.model.userprofile.UserProfile;
import com.oklimenko.authserver.repository.AuthorityRepo;
import com.oklimenko.authserver.repository.UserAuthorityRepo;
import com.oklimenko.authserver.repository.UserProfileRepository;
import com.oklimenko.authserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Transactional(Transactional.TxType.REQUIRES_NEW)
@Service
public class UserManagerService {

    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_NAME = "user_name";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthorityRepo userAuthorityRepo;

    @Autowired
    private AuthorityRepo authorityRepo;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void changeUserProfile(UserProfileDto userProfileDto) {
        UserProfile userProfile = userProfileRepository.getOne(userProfileDto.getId());

        userProfile.setName(userProfileDto.getName());
//        userProfile.setUserName(userProfileDto.getUserName());
        userProfile.setEmail(userProfileDto.getEmail());
        userProfile.setName(userProfileDto.getName());
        userProfile.setDepartment(userProfileDto.getDepartment());
        userProfileRepository.save(userProfile);
    }

    public Integer registerNewUser(NewUserProfileDto newUserDetails) {
        UserAuthority userAuthority = new UserAuthority();

        Authority authority = authorityRepo.findByName(ADMIN_ROLE);

        User user = new User();
        user.setAccountExpired(false);
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(newUserDetails.getPassword()));
        user.setUserName(newUserDetails.getUserName());
        Set<UserAuthority> userAuthorities = new HashSet<>();
        userAuthorities.add(userAuthority);
        user.setUserAuthorities(userAuthorities);

        userAuthority.setUser(user);
        userAuthority.setAuthority(authority);
        userAuthorityRepo.save(userAuthority);
        userRepository.save(user);


        UserProfile userProfile = new UserProfile();
        userProfile.setName(newUserDetails.getName());
        userProfile.setUserName(newUserDetails.getUserName());
        userProfile.setEmail(newUserDetails.getEmail());
        userProfile.setName(newUserDetails.getName());
        userProfile.setDepartment(newUserDetails.getDepartment());
        userProfileRepository.save(userProfile);

        return userProfile.getId();
    }

    public UserProfileDto findById(Integer userId) {
        UserProfile user = userProfileRepository.getOne(userId);
        if (!user.getUserName().equals(getRequestingOAuthUserName())) {
            throw new InvalidTokenException("Unauthorized: token does not belong to the user");
        }
        UserProfileDto userProfile = new UserProfileDto();
        userProfile.setId(user.getId());
        userProfile.setUserName(user.getUserName());
        userProfile.setEmail(user.getEmail());
        userProfile.setName(user.getName());
        userProfile.setDepartment(user.getDepartment());
        return userProfile;
    }

    private String getRequestingOAuthUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object details = authentication.getDetails();
        if ( details instanceof OAuth2AuthenticationDetails){
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails)details;
            Jwt jwtToken = JwtHelper.decode(oAuth2AuthenticationDetails.getTokenValue());
            String
                    claims = jwtToken.getClaims();
            HashMap claimsMap = null;
            try {
                claimsMap = new ObjectMapper().readValue(claims, HashMap.class);
                return String.valueOf(claimsMap.get(USER_NAME));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
