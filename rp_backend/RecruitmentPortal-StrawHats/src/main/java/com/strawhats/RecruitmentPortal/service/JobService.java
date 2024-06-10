package com.strawhats.RecruitmentPortal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strawhats.RecruitmentPortal.dto.JobDTO;
import com.strawhats.RecruitmentPortal.model.Job;
import com.strawhats.RecruitmentPortal.model.User;
import com.strawhats.RecruitmentPortal.repo.JobRepository;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    
    public Job registerJob(Job job) {
        return jobRepository.save(job);
    }
    
    public List<JobDTO> getAllJobs() {
        return jobRepository.findAll().stream()
                             .map(this::convertToDTO)
                             .collect(Collectors.toList());
    }
    
    private JobDTO convertToDTO(Job job) {
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
    
    
    public List<Job> searchJob(String skill){
    	return jobRepository.findBySkillsRequiredContaining(skill);
    }

	public List<Job> getAppliedJobs(User user) {
		
		return jobRepository.findByApplicants(user);
	}
    
}
