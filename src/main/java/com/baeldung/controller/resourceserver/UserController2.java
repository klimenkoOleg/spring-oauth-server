package com.baeldung.controller.resourceserver;

import com.baeldung.dto.CustomUserDetails;
import com.baeldung.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/user")
public class UserController2 {

    @Autowired
    private UserManagerService userManagerService;

    @PostMapping("/register")
    public void registerUser(@RequestBody CustomUserDetails customUserDetails) {
        System.out.println("sdadaf");
        userManagerService.registerNewUser(customUserDetails);
    }

    @GetMapping("/users/extra")
    @PreAuthorize("#oauth2.hasScope('read')")
    public CustomUserDetails getExtraInfo(@RequestParam Integer userId) {
        return userManagerService.findById(userId);
    }
}
