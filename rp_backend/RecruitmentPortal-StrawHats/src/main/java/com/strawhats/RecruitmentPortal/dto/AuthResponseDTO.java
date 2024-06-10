package com.strawhats.RecruitmentPortal.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";
    private String email;
    private Long userId;

    public AuthResponseDTO(String accessToken, Long userId,String email) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.email = email;
    }
}