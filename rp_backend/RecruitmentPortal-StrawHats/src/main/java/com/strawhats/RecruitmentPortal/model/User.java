package com.strawhats.RecruitmentPortal.model;
import java.util.List;

import com.strawhats.RecruitmentPortal.model.Job;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`user`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    
    @ManyToMany(mappedBy = "applicants")
    private List<Job> appliedJobs;
    
    @ManyToMany(mappedBy = "savedJobsApplicants")
    private List<Job> savedJobs;
    
}
