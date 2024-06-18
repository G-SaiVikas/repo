package com.strawhats.RecruitmentPortal.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private Date dateOfBirth;
    private MultipartFile profilePic;
    private MultipartFile resume;
}