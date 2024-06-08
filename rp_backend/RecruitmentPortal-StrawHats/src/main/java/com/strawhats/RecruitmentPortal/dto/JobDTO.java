package com.strawhats.RecruitmentPortal.dto;

import lombok.Data;

@Data
public class JobDTO {
    private Long id;
    private String company;
    private String jobTitle;
    private String jobDescription;
    private String skillsRequired;
    private String location;
    private String salary;
}
