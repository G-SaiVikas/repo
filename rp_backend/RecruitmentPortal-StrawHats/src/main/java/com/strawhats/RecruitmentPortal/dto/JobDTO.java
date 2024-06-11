package com.strawhats.RecruitmentPortal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {
    private Long id;
    private String company;
    private String jobTitle;
    private String jobDescription;
    private String skillsRequired;
    private String location;
    private String salary;
}
