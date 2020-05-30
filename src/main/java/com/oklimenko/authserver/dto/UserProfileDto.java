package com.oklimenko.authserver.dto;

import lombok.Data;

@Data
public class UserProfileDto {
    private Integer id;
    private String userName;
    private String email;
    private String name;
    private String department;
}
