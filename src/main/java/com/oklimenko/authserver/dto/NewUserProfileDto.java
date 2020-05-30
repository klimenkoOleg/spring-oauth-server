package com.oklimenko.authserver.dto;

import lombok.Data;

@Data
public class NewUserProfileDto {
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private String name;
    private String department;

}
