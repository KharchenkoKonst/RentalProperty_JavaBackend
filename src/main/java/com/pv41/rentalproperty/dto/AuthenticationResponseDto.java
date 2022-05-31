package com.pv41.rentalproperty.dto;

import lombok.Data;

@Data
public class AuthenticationResponseDto {
    private String accessToken;
    private String refreshToken;
}
