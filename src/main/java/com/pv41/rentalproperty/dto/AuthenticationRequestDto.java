package com.pv41.rentalproperty.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
}
