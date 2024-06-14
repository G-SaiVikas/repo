package com.strawhats.RecruitmentPortal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strawhats.RecruitmentPortal.dto.JobDTO;
import com.strawhats.RecruitmentPortal.model.Job;
import com.strawhats.RecruitmentPortal.repo.JobRepository;
import com.strawhats.RecruitmentPortal.repo.UserRepository;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    UserRepository userRepository;

    public Job registerJob(Job job) {
        return jobRepository.save(job);
    }

    public List<JobDTO> getAllJobs() {
        return jobRepository.findAll().stream()
                             .map(this::convertToDTO)
                             .collect(Collectors.toList());
    }

    public List<JobDTO> searchJob(String skill) {
        return jobRepository.findBySkillsRequiredContaining(skill).stream()
                             .map(this::convertToDTO)
                             .collect(Collectors.toList());
    }

    public JobDTO convertToDTO(Job job) {
        JobDTO dto = new JobDTO();
        dto.setId(job.getId());
        dto.setCompany(job.getCompany());
        dto.setJobTitle(job.getJobTitle());
        dto.setJobDescription(job.getJobDescription());
        dto.setSkillsRequired(job.getSkillsRequired());
        dto.setLocation(job.getLocation());
        dto.setSalary(job.getSalary());
        return dto;
    }

    public List<JobDTO> getAppliedJobs(Long user_id) {
        return jobRepository.findByApplicants(userRepository.findById(user_id).get()).stream()
                            .map(this::convertToDTO)
                            .collect(Collectors.toList());
    }
}
