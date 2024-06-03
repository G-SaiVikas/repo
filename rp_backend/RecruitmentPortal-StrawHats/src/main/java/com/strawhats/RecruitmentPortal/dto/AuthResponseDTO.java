package com.strawhats.RecruitmentPortal.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";
    private String email;

    public AuthResponseDTO(String accessToken, String email) {
        this.accessToken = accessToken;
        this.email = email;
    }
}