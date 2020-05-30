package com.oklimenko.authserver.controller.resourceserver;

import com.oklimenko.authserver.dto.NewUserProfileDto;
import com.oklimenko.authserver.dto.UserProfileDto;
import com.oklimenko.authserver.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserManagerService userManagerService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String registerUser(@RequestBody NewUserProfileDto newUserProfile) {
        Integer userId = userManagerService.registerNewUser(newUserProfile);
        return String.format("{\"userId\" : %s}", userId);
    }

    @PostMapping("/edit")
    @PreAuthorize("#oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.OK)
    public void registerUser(@RequestBody UserProfileDto userProfile) {
        userManagerService.changeUserProfile(userProfile);
    }

    @GetMapping("/extra")
    @PreAuthorize("#oauth2.hasScope('read')")
    @ResponseStatus(HttpStatus.OK)
    public UserProfileDto getExtraInfo(@RequestParam Integer userId) throws Exception {
        return userManagerService.findById(userId);
    }
}
